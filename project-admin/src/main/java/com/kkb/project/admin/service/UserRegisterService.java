package com.kkb.project.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.admin.domain.UserInfo;
import com.kkb.project.admin.domain.UserRegister;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户注册服务类接口
 *
 * @author Yolen
 * @since 2021-04-29
 */
@Transactional(rollbackFor = Exception.class)
public interface UserRegisterService extends IService<UserRegister> {

    /**
     * 完善身份资料
     *
     * @param userInfo 身份资料
     */
    void addIdentity(UserInfo userInfo);

    /**
     * 修改个人资料
     *
     * @param userInfo 个人资料
     */
    void updateUserInfo(UserInfo userInfo);
}
