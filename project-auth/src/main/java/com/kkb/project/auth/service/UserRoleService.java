package com.kkb.project.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.auth.domain.UserRole;

import java.util.List;

/**
 * @author lemon
 * @version 1.0
 * @description UserRoleService
 * @date 2021/04/27
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 通过用户id查询对应的角色
     * @param id
     * @return
     */
    List<String> getRolesById(Long id);
}
