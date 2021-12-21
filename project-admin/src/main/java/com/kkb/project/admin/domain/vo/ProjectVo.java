package com.kkb.project.admin.domain.vo;

import com.kkb.project.admin.domain.Client;
import com.kkb.project.admin.domain.Project;
import com.kkb.project.admin.domain.ProjectWorkStyle;
import com.kkb.project.admin.domain.ProjectWorkType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liusheng
 * @date 2021/04/23
 * ProjectVo类
 */
@Data
@AllArgsConstructor
@ApiModel(value = "ProjectVo对象", description = "包含Project对象与Client对象")
public class ProjectVo {

    /**
     * 项目
     */
    @ApiModelProperty(value = "项目对象")
    private Project project;

    /**
     * 委托方
     */
    @ApiModelProperty(value = "委托方对象")
    private Client client;

    /**
     * 项目类型
     */
    @ApiModelProperty("项目类型")
    private ProjectWorkType projectWorkType;

    /**
     * 项目工作方式
     */
    @ApiModelProperty("项目工作方式")
    private ProjectWorkStyle projectWorkStyle;

}
