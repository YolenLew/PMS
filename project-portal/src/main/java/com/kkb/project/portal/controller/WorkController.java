package com.kkb.project.portal.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kkb.project.common.api.CommonResult;
import com.kkb.project.portal.domain.*;
import com.kkb.project.portal.domain.vo.ListWorkImageVo;
import com.kkb.project.portal.domain.vo.UserWorkImageVo;
import com.kkb.project.portal.service.*;
import com.kkb.project.portal.service.impl.UserWorkServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author River
 * @Date 2021/4/16 0:08
 * @Description 作品集管理controller层API
 * @Version 1.0
 **/
@Api(value = "WorkController", tags = "作品集管理")
@RestController
@RequestMapping("/work")
public class WorkController {

    @Autowired
    private WorkTypeService workTypeService;
    @Autowired
    private UploadWorkService uploadWorkService;
    @Autowired
    private UserWorkServiceImpl userWorkService;
    @Autowired
    private WorkDescriptionService workDescriptionService;
    @Autowired
    private WorkImageService workImageService;
    @Autowired
    private WorkPraiseService workPraiseService;
    @Autowired
    private WorkPraiseService praiseService;

    @ApiOperation(value = "查找作品类型", notes = "根据传入的类型ID查找作品类型")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @PostMapping("/{id}")
    public CommonResult<WorkType> findWorkTypeById(@PathVariable Long id) {
        WorkType result = workTypeService.findWorkTypeById(id);
        return CommonResult.success(result);
    }

    @ApiOperation(value = "查找所有作品类型", notes = "无需传入参数查找所有作品类型")
    @PostMapping("/list")
    public CommonResult<List<WorkType>> findAllWork() {
        List<WorkType> result = workTypeService.findAllWork();
        return CommonResult.success(result, "查询成功");
    }

    @ApiOperation(value = "作品上传", notes = "传入参数对象进行作品上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "typeId", value = "作品类型ID", required = true, dataType = "Long")
    })
    @PostMapping("/upload")
    public CommonResult uploadWork(@Validated @RequestParam Long userId,
                                   @Validated UserWork userWork,
                                   @RequestBody @Validated ListWorkImageVo listWorkImageVo,
                                   @Validated WorkDescription workDescription,
                                   @Validated @RequestParam(defaultValue = "1") Long typeId) {
        uploadWorkService.uploadWork(userId, userWork, listWorkImageVo, workDescription, typeId);
        return CommonResult.success(null, "作品上传成功");
    }

    @ApiOperation(value = "默认排序", notes = "作品集单纯的排序可以直接通过orderBy sequence,需要前端传递用户id")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long")
    @PostMapping("/sort/{userId}")
    public CommonResult<List<UserWork>> sortUserWorkBySequence(@PathVariable Long userId) {
        List<UserWork> userWorks = userWorkService.sortUserWorkByUserId(userId);
        List<WorkDescription> workDescriptions = workDescriptionService.listWorkDescriptionByUserId(userId);
        for (UserWork userWork : userWorks) {
            for (WorkDescription workDescription : workDescriptions) {
                if (workDescription.getUserWorkId().equals(userWork.getId())) {
                    userWork.setWorkDescription(workDescription);
                }
            }
        }
        return CommonResult.success(userWorks);
    }

    @ApiOperation(value = "手动排序", notes = "作品集重新排序，批量更改UserWork表的sequence字段值，需要前端传递List集合,参数需要@RequestBody修饰")
    @PostMapping("/rank")
    public CommonResult<Boolean> updateSequenceById(@Validated @RequestBody List<UserWork> userWorks) {
        boolean result = userWorkService.updateBatchById(userWorks, userWorks.size());
        return CommonResult.success(result);
    }

    @ApiOperation(value = "查找当前登录用户的作品列表", notes = "根据传入的类型id和当前登录用户的用户id查找当前登录用户的作品列表,类型id默认为0即查全部")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "typeId", value = "类型ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", defaultValue = "1", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", defaultValue = "10", dataType = "int")
    })
    @PostMapping("/type/{userId}")
    public CommonResult<IPage<UserWork>> findUserWorkByUserIdAndTypeId(@Validated @PathVariable Long userId,
                                                                       @Validated @RequestParam(defaultValue = "0") Long typeId,
                                                                       @Validated @RequestParam(defaultValue = "1") Integer pageNum,
                                                                       @Validated @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<UserWork> pageParam = new Page<>(pageNum, pageSize);
        IPage<UserWork> result = userWorkService.findUserWorkByUserIdAndTypeId(userId, typeId, pageParam);
        //获取用户作品描述信息，并转为Map格式 userWorkId -> WorkDescription
        List<WorkDescription> workDescriptions = workDescriptionService.listWorkDescriptionByUserId(userId);
        Map<Long, WorkDescription> descMap = workDescriptions.stream().collect(Collectors.toMap(WorkDescription::getUserWorkId, wd -> wd));
        Map<Long, List<WorkImage>> imagesMap = workImageService.listImagesByUserId(userId);
        Map<Long, WorkPraise> praiseMap = workPraiseService.listWorkPraiseByUserId(userId);
        for (UserWork userWork : result.getRecords()){
            userWork.setWorkDescription(descMap.getOrDefault(userWork.getId(),null));
            userWork.setWorkImages(imagesMap.getOrDefault(userWork.getId(),null));
            userWork.setWorkPraise(praiseMap.getOrDefault(userWork.getId(),null));
        }
        return CommonResult.success(result);
    }

    @ApiOperation(value = "删除作品", notes = "根据传入的作品id删除作品(逻辑删除)")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @PostMapping("/delete/{id}")
    public CommonResult deleteUserWorkById(@Validated @PathVariable Long id) {
        userWorkService.deleteUserWorkById(id);
        return CommonResult.success(null, "删除作品成功");
    }

    @ApiOperation(value = "查找作品集详情", notes = "根据作品集id查找作品图片、描述、点赞及创建时间")
    @ApiImplicitParam(name = "id", value = "作品集id", required = true, dataType = "Long")
    @PostMapping("/detail/{id}")
    public CommonResult<UserWorkImageVo> findUserWorkImageById(@PathVariable Long id) {
        List<WorkImage> workImageList = workImageService.findUserWorkImageById(id);
        UserWork userWork = userWorkService.findUserWorkById(id);
        WorkDescription workDescription = workDescriptionService.findWorkDescriptionById(id);
        WorkPraise workPraise = workPraiseService.findWorkPraisesById(id);
        UserWorkImageVo result = new UserWorkImageVo(workImageList, userWork, workDescription, workPraise);
        return CommonResult.success(result, "查询成功");
    }

    @ApiOperation(value = "更新点赞数", notes = "根据ID来查询点赞数以及更新点赞数")
    @ApiImplicitParam(name = "id", value = "作品ID", required = true, dataType = "Long")
    @PostMapping("/praises/{id}")
    public CommonResult<WorkPraise> updatePraisesById(@PathVariable Long id) {
        praiseService.updateWorkNumById(id);
        WorkPraise result = praiseService.findWorkPraisesById(id);
        return CommonResult.success(result);
    }
}

