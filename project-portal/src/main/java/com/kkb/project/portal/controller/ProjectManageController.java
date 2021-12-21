package com.kkb.project.portal.controller;

import com.kkb.project.common.api.CommonPage;
import com.kkb.project.common.api.CommonResult;
import com.kkb.project.portal.domain.constant.ProjectStatus;
import com.kkb.project.portal.domain.constant.RoleType;
import com.kkb.project.portal.domain.dto.ProjectManageParamDto;
import com.kkb.project.portal.service.ProjectManageFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author Lai Xiangdong
 * @Description 个人中心项目管理页Service
 * @createTime 2021-04-23 15:11:47
 * @updateBy __SAD_DOG__
 * @updateTime: 2021-04-27
 */
@RestController
@RequestMapping("/project/manage")
@Api(value = "projectManageController", tags = "项目管理")
public class ProjectManageController {

    @Autowired
    private ProjectManageFacade projectManageFacade;

    /**
     * 返回项目管理-主要流程页面需要查询的项目信息列表
     * @param projectManageParamDto 查询参数
     * @return 项目信息列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "查询项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectManageParamDto", value = "项目管理-主要流程页面查询参数", dataType = "ProjectManageParamDto"),
    })
    public CommonResult<CommonPage<?>> getProjectList(@NotNull ProjectManageParamDto projectManageParamDto) {
        CommonPage<?> projectManageVoList = projectManageFacade.getProjectManageVoList(
                (long) projectManageParamDto.getPageNum(),
                (long) projectManageParamDto.getPageSize(),
                projectManageParamDto.getUserId(),
                RoleType.ofValue(projectManageParamDto.getRoleType()),
                ProjectStatus.ofValue(projectManageParamDto.getProjectStatus())
        );
        return CommonResult.success(projectManageVoList);
    }

}
