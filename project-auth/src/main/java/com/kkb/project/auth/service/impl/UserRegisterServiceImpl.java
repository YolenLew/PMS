package com.kkb.project.auth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.auth.dao.UserRegisterMapper;
import com.kkb.project.auth.domain.UserRegister;
import com.kkb.project.auth.extension.userdetails.RegisterUserDetails;
import com.kkb.project.auth.service.UserRegisterService;
import com.kkb.project.common.api.ResultCode;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author lemon
 * @version 1.0
 * @description 用户接口实现类
 * @date 2021/04/27
 */
@Service
public class UserRegisterServiceImpl extends ServiceImpl<UserRegisterMapper, UserRegister> implements UserRegisterService {

    /**
     * 通过用户信息查询 实际传值为phone
     * @param username
     * @return
     */
    @Override
    public UserRegister loadUserByUsername(String username) {
        return this.getOne(new QueryWrapper<UserRegister>().eq("phone", username));
    }

    @Override
    public UserDetails loadUserByPhone(String phone) {
        RegisterUserDetails userDetails = null;
        UserRegister userRegister = this.getOne(Wrappers.<UserRegister>lambdaQuery().eq(UserRegister::getPhone, phone));
        if (ObjectUtil.isNotNull(userRegister)) {
            userDetails = new RegisterUserDetails(userRegister);
            userDetails.setAuthenticationMode("mobile");
        }
        if (userDetails == null) {
            throw new UsernameNotFoundException("该账户不存在!");
        } else if (!userDetails.isEnabled()) {
            throw new DisabledException("该账户已被禁用!");
        } else if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定!");
        } else if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期!");
        }
        return userDetails;
    }
}
