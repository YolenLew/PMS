package com.kkb.project.demo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kkb.project.common.api.CommonResult;
import com.kkb.project.demo.domain.WorkType;
import com.kkb.project.demo.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @ClassName DemoServiceImpl
 * @Author LZH
 * @Date 2021/4/26
 * @Description TODO 演示实现类类，不需要了请删除
 * @Version 1.0
 **/

@Api(value = "DemoController", tags = "admin演示接口")
@RestController
@RequestMapping("/admin/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    /**
     * @Author 李梓豪
     * @Description 单一查找
     * @Date 2021年04月27日  15:04:41
     **/
    @ApiOperation(value = "查找作品类型", notes = "通过ID来查找作品类型")
    @ApiImplicitParam(name = "id",value = "作品id",required = true,dataType = "Long")
    @PostMapping("/{id}")
    public CommonResult<WorkType> findWorkTypeById(@PathVariable Long id) {
        WorkType result = demoService.findWorkTypeById(id);
        return CommonResult.success(result, "查询成功");
    }

    /**
     * @Author 李梓豪
     * @Description 增加
     * @Date 2021年04月27日  15:04:51
     * @Param [workType]
     * @return com.kkb.project.common.api.CommonResult
     * @Date Modify in 2021年04月27日  15:04:51
     * @Modify Content:
     **/
    @ApiOperation(value = "新增作品类型", notes = "传入作品类型对象来新增作品")
    @ApiImplicitParam(name = "workType",value = "作品类型",required = true,dataType = "WorkType")
    @PostMapping("/insert")
    public CommonResult<?> insertWorkType(@Validated WorkType workType) {
        demoService.insertWorkType(workType);
        return CommonResult.success(null, "新增成功");
    }

    /**
     * @Author 李梓豪
     * @Description  查找所有
     * @Date 2021年04月27日  15:04:48
     **/
    @ApiOperation(value = "查找所有作品", notes = "不需要传入参数")
    @PostMapping("/list")
    public CommonResult<List<WorkType>> findAllWork() {
        List<WorkType> result = demoService.findAllWork();
        return CommonResult.success(result, "查找成功");
    }

    /**
     * @Author 李梓豪
     * @Description 分页显示
     * @Date 2021年04月27日  15:04:26
     **/
    @ApiOperation(value = "分页查询作品类型", notes = "分页查询默认从第一页开始每页显示5个")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "分页页数",required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize",value = "每页显示数",required = true,dataType = "Integer")
    })
    @PostMapping("/page/list")
    public CommonResult<Page<WorkType>> pageFindWork(@RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "5") Integer pageSize) {
        Page<WorkType> result = demoService.pageFindWork(pageNum, pageSize);
        return CommonResult.success(result, "查找成功");
    }

    /**
     * @Author 李梓豪
     * @Description 删除
     * @Date 2021年04月27日  15:04:26
     **/
    @ApiOperation(value = "删除作品", notes = "通过id删除作品")
    @ApiImplicitParam(name = "id",value = "作品id",required = true,dataType = "Long")
    @PostMapping("/delete/{id}")
    public CommonResult<?> deleteWorkTypeById(@PathVariable Long id) {
        demoService.deleteWorkTypeById(id);
        return CommonResult.success(null, "删除成功");
    }

    /**
     * @Author 李梓豪
     * @Description 修改
     * @Date 2021年04月27日  15:04:26
     **/
    @ApiOperation(value = "修改作品类型", notes = "传入作品类型对象进行修改")
    @ApiImplicitParam(name = "workType",value = "作品类型",required = true,dataType = "WorkType")
    @PostMapping("/update")
    public CommonResult<?> updateWorkTypeById(WorkType workType) {
        demoService.updateWorkTypeById(workType);
        return CommonResult.success(null, "修改作品类型成功");
    }
}
