package com.kkb.project.admin.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.kkb.project.admin.domain.InternalRecommendationProperty;
import com.kkb.project.admin.domain.UpdateGroup;
import com.kkb.project.admin.service.InternalRecommendationPropertyService;
import com.kkb.project.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 内推属性控制器
 *
 * @author Yolen
 * @since 2021-04-21
 */
@Api(value = "InternalRecommendationPropertyController", tags = "内推属性管理")
@RestController
@RequestMapping("/admin/internal/property")
public class InternalRecommendationPropertyController {

    @Autowired
    private InternalRecommendationPropertyService internalRecommendationPropertyService;

    @ApiOperation(value = "获取指定type的职位属性信息", notes = "根据type值获取对应的职位属性信息集合")
    @ApiImplicitParam(name = "type", value = "内推属性类型", required = true, dataType = "int")
    @GetMapping(value = "/type/{type}")
    public CommonResult<List<InternalRecommendationProperty>> findByType(@PathVariable(value = "type") Integer type) {
        List<InternalRecommendationProperty> internalRecommendationPropertyList = internalRecommendationPropertyService.findByType(type);
        return CommonResult.success(internalRecommendationPropertyList);
    }

    @ApiOperation(value = "获取所有职位属性信息", notes = "查询所有职位属性信息")
    @GetMapping(value = "/list")
    public CommonResult<List<InternalRecommendationProperty>> listAll() {
        List<InternalRecommendationProperty> internalRecommendationPropertyList = internalRecommendationPropertyService.listAll();
        return CommonResult.success(internalRecommendationPropertyList);
    }

    @ApiOperation(value = "获取指定id的职位属性信息", notes = "获取指定id的职位属性信息")
    @ApiImplicitParam(name = "id", value = "内推属性id", required = true, dataType = "long")
    @GetMapping(value = "/{id}")
    @ResponseBody
    public CommonResult<InternalRecommendationProperty> findById(@PathVariable(value = "id") Long id) {
        InternalRecommendationProperty internalRecommendationProperty = internalRecommendationPropertyService.findById(id);
        return CommonResult.success(internalRecommendationProperty);
    }

    @ApiOperation(value = "新增职位属性")
    @ApiOperationSupport(ignoreParameters = {"id"})
    @PostMapping("/add")
    public CommonResult<Integer> addInternalRecommendationProperty(@Validated InternalRecommendationProperty property) {
        internalRecommendationPropertyService.addInternalRecommendationProperty(property);
        return CommonResult.success(null, "添加成功");
    }

    @ApiOperation(value = "删除职位属性", notes = "通过id删除职位属性")
    @ApiImplicitParam(name = "id", value = "职位属性id", required = true, dataType = "long")
    @PostMapping("/delete/{id}")
    public CommonResult<Integer> deleteInternalRecommendationProperty(@PathVariable Long id) {
        internalRecommendationPropertyService.deleteInternalRecommendationProperty(id);
        return CommonResult.success(null, "删除成功");
    }

    @ApiOperation("修改职位属性")
    @PostMapping("/update")
    public CommonResult<Integer> updateInternalRecommendationProperty(@Validated(UpdateGroup.class) InternalRecommendationProperty property) {
        internalRecommendationPropertyService.updateInternalRecommendationProperty(property);
        return CommonResult.success(null, "修改成功");
    }
}

