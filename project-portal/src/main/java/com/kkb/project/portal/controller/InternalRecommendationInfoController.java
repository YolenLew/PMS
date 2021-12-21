package com.kkb.project.portal.controller;

import com.kkb.project.common.api.CommonPage;
import com.kkb.project.common.api.CommonResult;
import com.kkb.project.portal.domain.InternalRecommendationInfo;
import com.kkb.project.portal.service.InternalRecommendationInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 内推信息查询
 *
 * @author lemon
 * @version 1.0
 * @since 2021/04/14
 */
@RestController
@Api(value = "InternalRecommendationInfoController", tags = "内推介绍")
@Validated
@RequestMapping("/internal/recommendation")
public class InternalRecommendationInfoController {

    @Autowired
    private InternalRecommendationInfoService internalRecommendationInfoService;

    @ApiOperation(value = "查询内推信息", notes = "通过id查询内推信息")
    @ApiImplicitParam(name = "id", value = "内推信息id", required = true, dataType = "long")
    @GetMapping("/get/{id}")
    public CommonResult<InternalRecommendationInfo> getInternalRecommendationInfo(@NotNull @PathVariable Long id) {
        InternalRecommendationInfo internalRecommendationInfo = internalRecommendationInfoService.getInternalRecommendationInfoById(id);
        return CommonResult.success(internalRecommendationInfo);
    }

    @ApiOperation(value = "查询内推列表", notes = "分页、条件查询内推信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyAddress", value = "公司地址"),
            @ApiImplicitParam(name = "publishTime", value = "发布时间"),
            @ApiImplicitParam(name = "salary", value = "薪资"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", defaultValue = "1", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", defaultValue = "10", dataType = "int")
    })
    @GetMapping("/list")
    public CommonResult<CommonPage<InternalRecommendationInfo>> getInternalRecommendationInfoList(
            @RequestParam(value = "companyAddress", required = false) String companyAddress,
            @RequestParam(value = "publishTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date publishTime,
            @RequestParam(value = "salary", required = false) String salary,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        CommonPage<InternalRecommendationInfo> infoPageInfo = internalRecommendationInfoService
                .getInternalRecommendationInfoList(pageNum, pageSize, companyAddress, publishTime, salary);
        return CommonResult.success(infoPageInfo);
    }
}

