package com.kkb.project.portal.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author __SAD_DOG__
 * @date 2021-04-27
 * 项目管理-主要流程页面的request参数
 */
@Data
public class ProjectManageParamDto {

    @NotNull
    @ApiModelProperty("当前页码")
    private Integer pageNum;

    @NotNull
    @ApiModelProperty("页面大小")
    private Integer pageSize;

    @NotNull
    @ApiModelProperty("用户Id")
    private Long userId;

    @NotNull
    @ApiModelProperty("项目状态")
    @Pattern(regexp = "[1234]")
    private Integer projectStatus;

    /**
     * 用户类型, 由枚举RoleType解释
     */
    @NotNull
    @ApiModelProperty("用户类型")
    @Pattern(regexp = "[01]")
    private Integer roleType;
}
