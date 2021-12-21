package com.kkb.project.auth.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lemon
 * @version 1.0
 * @description UserRole实体
 * @date 2021/04/27
 */
@Data
@ApiModel(value = "UserRole", description = "用户角色关联表")
public class UserRole implements Serializable {
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "角色id")
    private Long roleId;

    private static final long serialVersionUID = 1L;
}