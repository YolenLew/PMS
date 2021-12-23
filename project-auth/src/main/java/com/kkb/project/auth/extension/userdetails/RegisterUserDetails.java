package com.kkb.project.auth.extension.userdetails;

import com.kkb.project.auth.domain.UserRegister;
import com.kkb.project.common.constant.AuthConstant;
import jdk.nashorn.internal.runtime.GlobalConstants;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

/**
 * 用户认证信息
 *
 * @author Yolen
 * @date 2021/12/23
 */
@Data
public class RegisterUserDetails implements UserDetails {

    private Long userId;
    private String username;
    private Boolean enabled;
    /**
     * 认证方式
     */
    private String authenticationMode;

    /**
     * 用户认证信息
     *
     * @param userRegister 用户信息
     */
    public RegisterUserDetails(UserRegister userRegister) {
        this.setUserId(userRegister.getId());
        this.setUsername(userRegister.getPhone());
        this.setEnabled(AuthConstant.STATUS_YES.equals(userRegister.getIsDeleted()));
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new HashSet<>();
        return collection;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
