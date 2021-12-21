package com.kkb.project.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.auth.dao.UserRegisterMapper;
import com.kkb.project.auth.domain.UserRegister;
import com.kkb.project.auth.service.UserRegisterService;
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
}
