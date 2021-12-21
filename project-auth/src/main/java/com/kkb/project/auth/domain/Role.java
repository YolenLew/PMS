package com.kkb.project.auth.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lemon
 * @version 1.0
 * @description Role实体
 * @date 2021/04/27
 */
@Data
@ApiModel(value = "role", description = "角色表")
public class Role implements Serializable {
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "角色名")
    private String roleName;

    @ApiModelProperty(value = "角色描述")
    private String description;

    @ApiModelProperty(value = "软删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "更新人")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;

    @ApiModelProperty(value = "乐观锁")
    private Integer revision;

    private static final long serialVersionUID = 1L;
}