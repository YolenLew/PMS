package com.kkb.project.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.portal.domain.Oauth2TokenDTO;
import com.kkb.project.portal.domain.UserInfo;
import com.kkb.project.portal.domain.UserRegister;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户注册服务类接口
 *
 * @author Yolen
 * @since 2021-04-29
 */
public interface UserRegisterService extends IService<UserRegister> {

    /**
     * 用户注册
     *
     * @param phone    手机号
     * @param password 密码
     */
    @Transactional(rollbackFor = Exception.class)
    void register(String phone, String password);

    /**
     * 密码登录
     *
     * @param phone    手机号
     * @param password 密码
     * @return 令牌
     */
    Oauth2TokenDTO login(String phone, String password);

    /**
     * 获取验证码
     *
     * @param phone 手机号
     * @return 验证码
     */
    String getCode(String phone);

    /**
     * 验证码登录
     *
     * @param phone    手机号
     * @param authCode 验证码
     * @return 令牌
     */
    Oauth2TokenDTO loginByCode(String phone, String authCode);

    /**
     * 获取当前登录用户信息
     *
     * @param loginId 注册用户id
     * @return 当前登录用户
     */
    UserInfo getUser(Long loginId);
}
