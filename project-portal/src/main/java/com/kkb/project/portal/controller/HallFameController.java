package com.kkb.project.portal.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kkb.project.common.api.CommonResult;
import com.kkb.project.portal.domain.*;
import com.kkb.project.portal.domain.vo.UserVo;
import com.kkb.project.portal.service.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author River
 * @Date 2021/4/20 11:08
 * @Description 名人堂controller层API
 * @Version 1.0
 **/
@Api(value = "HallFameController", tags = "名人堂")
@RestController
@RequestMapping("/hall/fame")
public class HallFameController {

    @Autowired
    private UserWorkService userWorkService;
    @Autowired
    private WorkImageService imageService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserExperienceService userExperienceService;
    @Autowired
    private UserSkillService userSkillService;

    @ApiOperation(value = "查寻名人堂人员", notes = "根据类型查找名人堂成员")

    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型ID", required = true, dataType = "Byte"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", defaultValue = "1", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", defaultValue = "10", dataType = "int")
    })
    @PostMapping("/{type}")
    public CommonResult<IPage<User>> findTypeFame(@PathVariable Byte type,
                                                  @RequestParam(defaultValue = "1") int pageNum,
                                                  @RequestParam(defaultValue = "10") int pageSize) {
        Page<User> pageParam = new Page<>(pageNum, pageSize);
        IPage<User> result = userService.findUserByType(type, pageParam);
        return CommonResult.success(result);
    }

    @ApiOperation(value = "查看名人堂用户信息", notes = "根据ID查询名人堂用户信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @PostMapping("/find/fame/info/{id}")
    public CommonResult<UserVo> findUserById(@PathVariable Long id) {
        User users = userService.findUserInfoById(id);
        List<UserWork> userWorks = userWorkService.findUserWorkByUserId(id);
        List<UserExperience> userExperiences = userExperienceService.findUserExperienceById(id);
        List<UserSkill> userSkills = userSkillService.findUserSkillById(id);
        List<WorkImage> workImages = imageService.findUserWorkImageById(id);
        UserVo result = new UserVo(users, userWorks, userExperiences, userSkills, workImages);
        return CommonResult.success(result, "查询成功");
    }

}
