package com.kkb.project.admin.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.kkb.project.admin.domain.InternalRecommendationInfo;
import com.kkb.project.admin.domain.UpdateGroup;
import com.kkb.project.admin.service.InternalRecommendationInfoService;
import com.kkb.project.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 内推信息管理
 *
 * @author lemon
 * @version 1.0
 * @since 2021/04/14
 */
@RestController
@Api(value = "InternalRecommendationInfoController", tags = "内推管理")
@Validated
@RequestMapping("/admin/internal/recommendation")
public class InternalRecommendationInfoController {

    @Autowired
    private InternalRecommendationInfoService internalRecommendationInfoService;

    @ApiOperation(value = "新增内推信息")
    @ApiOperationSupport(ignoreParameters = {"id"})
    @PostMapping("/add")
    public CommonResult addInternalRecommendationInfo(@Validated InternalRecommendationInfo info) {
        internalRecommendationInfoService.addInternalRecommendationInfo(info);
        return CommonResult.success(null, "添加成功");
    }

    @ApiOperation(value = "删除内推信息", notes = "通过id删除内推信息")
    @ApiImplicitParam(name = "id", value = "内推信息id", required = true, dataType = "long")
    @PostMapping("/delete/{id}")
    public CommonResult deleteInternalRecommendationInfo(@PathVariable Long id) {
        internalRecommendationInfoService.deleteInternalRecommendationInfo(id);
        return CommonResult.success(null, "删除成功");
    }

    @ApiOperation(value = "批量删除内推信息", notes = "通过传入的id数组批量删除内推信息")
    @ApiImplicitParam(name = "ids", value = "内推信息id数组", required = true, allowMultiple = true, dataType = "long")
    @PostMapping("/delete")
    public CommonResult deleteInternalRecommendationInfo(@RequestBody @NotEmpty(message = "ids不能为空") Long[] ids) {
        internalRecommendationInfoService.deleteInternalRecommendationInfo(ids);
        return CommonResult.success(null, "删除成功");
    }

    @ApiOperation("修改内推信息")
    @PostMapping("/update")
    public CommonResult updateInternalRecommendationInfo(@Validated(UpdateGroup.class) InternalRecommendationInfo info) {
        internalRecommendationInfoService.updateInternalRecommendationInfo(info);
        return CommonResult.success(null, "修改成功");
    }

    @ApiOperation("添加用户的内推介绍浏览记录")
    @PostMapping("/history")
    public CommonResult addHistory(@RequestParam(name = "infoId") Integer infoId) {
        internalRecommendationInfoService.addHistory(infoId);
        return CommonResult.success(null, "添加成功");
    }

    @ApiOperation("查询用户的内推介绍浏览记录")
    @GetMapping("/history")
    public CommonResult<List<InternalRecommendationInfo>> getHistory() {
        List<InternalRecommendationInfo> internalRecommendationInfoList = internalRecommendationInfoService.getHistory();
        return CommonResult.success(internalRecommendationInfoList, "查询成功");
    }
}
