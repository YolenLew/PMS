package com.kkb.project.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description: 登录用户信息
 * @author: peng.ni
 * @date: 2021/04/07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Integer status;
    private String clientId;
    private List<String> roles;

}
