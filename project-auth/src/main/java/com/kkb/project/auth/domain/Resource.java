package com.kkb.project.auth.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lemon
 * @version 1.0
 * @description resource实体
 * @date 2021/04/27
 */
@Data
@ApiModel(value = "resource", description = "资源表")
public class Resource implements Serializable {
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "资源名")
    private String name;

    @ApiModelProperty(value = "资源路径")
    private String url;

    @ApiModelProperty(value = "资源描述")
    private String description;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "所属角色")
    private String role;

    private static final long serialVersionUID = 1L;
}