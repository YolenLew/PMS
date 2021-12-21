package com.kkb.project.portal.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author __SAD_DOG__
 * @date 2021-04-27
 * 在项目管理-主要流程页面中用于学员的非审核中项目
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectManageParticipantVo {

    @ApiModelProperty("项目名")
    private String projectName;

    @ApiModelProperty("项目类型")
    private Long projectTypeId;

    @ApiModelProperty("委托方名")
    private String clientName;

    @ApiModelProperty("导师姓名")
    private String leaderName;
}
