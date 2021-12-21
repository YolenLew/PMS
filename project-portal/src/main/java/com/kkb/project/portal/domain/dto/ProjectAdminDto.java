package com.kkb.project.portal.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * @Author ynb
 * @Date 2021/4/28
 * @Description 新建和update项目的post参数
 **/
@Data
public class ProjectAdminDto {
    @NotNull
    @ApiModelProperty(value = "项目标题")
    private String title;

    @NotNull
    @ApiModelProperty(value = "项目类型")
    private Long type;

    @NotNull
    @ApiModelProperty(value = "报名截止时间")
    private Date signupDeadline;

    @NotNull
    @ApiModelProperty(value = "工作方式")
    private Long workStyle;

    @ApiModelProperty(value = "项目需求描述")
    private String demandDescription;

    @ApiModelProperty(value = "项目标签")
    private String tags;

    public ProjectAdminDto(@NotNull String title, @NotNull Long type, @NotNull Date signupDeadline, @NotNull Long workStyle, String demandDescription, String tags) {
        this.title = title;
        this.type = type;
        this.signupDeadline = signupDeadline;
        this.workStyle = workStyle;
        this.demandDescription = demandDescription;
        this.tags = tags;
    }

    public ProjectAdminDto() {
    }
}
