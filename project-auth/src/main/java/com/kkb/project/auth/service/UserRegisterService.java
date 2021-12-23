package com.kkb.project.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.auth.domain.UserRegister;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author lemon
 * @version 1.0
 * @description 用户信息相关接口
 * @date 2021/04/27
 */
public interface UserRegisterService extends IService<UserRegister> {

    /**
     * 通过用户信息查询
     * @param username
     * @return
     */
    UserRegister loadUserByUsername(String username);

    UserDetails loadUserByPhone(String phone);
}
