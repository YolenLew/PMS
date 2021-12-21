package com.kkb.project.auth.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author lemon
 * @version 1.0
 * @description Oauth2获取Token返回信息封装
 * @date 2021/04/27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(description = "Oauth2获取Token返回信息封装")
@NoArgsConstructor
@AllArgsConstructor
public class Oauth2TokenDTO implements Serializable {

    @ApiModelProperty("访问令牌")
    private String token;
    @ApiModelProperty("刷令牌")
    private String refreshToken;
    @ApiModelProperty("访问令牌头前缀")
    private String tokenHead;
    @ApiModelProperty("有效时间（秒）")
    private Integer expiresIn;
}
