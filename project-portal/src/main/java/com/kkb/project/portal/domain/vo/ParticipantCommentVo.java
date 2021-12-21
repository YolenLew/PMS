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
@ApiModel(value = "学生评论")
public class ParticipantCommentVo {

    @NotNull
    @ApiModelProperty("项目id")
    private Long projectId;

    @NotNull
    @ApiModelProperty("学生id")
    private Long participantId;

    @NotNull
    @ApiModelProperty("来自导师id")
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
    @ApiModelProperty("操作能力")
    private Integer operationalAbility;

    @NotNull
    @ApiModelProperty("理解能力")
    private Integer comprehensionAbility;

}
