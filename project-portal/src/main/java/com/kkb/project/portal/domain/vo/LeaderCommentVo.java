package com.kkb.project.portal.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author __SAD_DOG__
 * @date 2021-04-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("导师评论")
public class LeaderCommentVo {

    @NotNull
    @ApiModelProperty("项目id")
    private Long projectId;

    @NotNull
    @ApiModelProperty("导师id")
    private Long leaderId;

    @NotNull
    @ApiModelProperty("来自学生id")
    private Long commentFrom;

    @NotNull
    @ApiModelProperty("评价")
    private String comment;

    @NotNull
    @ApiModelProperty("是否匿名")
    Boolean isAnonymous;

    @NotNull
    @ApiModelProperty("专业能力")
    private Integer professionalAbility;

    @NotNull
    @ApiModelProperty("组织能力")
    private Integer organizationalAbility;

    @NotNull
    @ApiModelProperty("沟通能力")
    private Integer communicationAbility;
}
