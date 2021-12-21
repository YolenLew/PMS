package com.kkb.project.auth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.auth.dao.RoleMapper;
import com.kkb.project.auth.dao.UserRoleMapper;
import com.kkb.project.auth.domain.Role;
import com.kkb.project.auth.domain.UserRole;
import com.kkb.project.auth.service.UserRoleService;
import com.kkb.project.common.exception.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lemon
 * @version 1.0
 * @description 用户角色相关接口实现类
 * @date 2021/04/27
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 通过用户id查询角色信息
     * @param id 用户id
     * @return 角色列表
     */
    @Override
    public List<String> getRolesById(Long id) {
        List<UserRole> userRoles = this.list(new QueryWrapper<UserRole>().eq("user_id", id));
        if (ObjectUtil.isEmpty(userRoles)){
            Asserts.fail("获取用户角色信息失败");
        }
        List<Long> roleIds = new ArrayList<>(userRoles.size());
        for (UserRole userRole : userRoles){
            roleIds.add(userRole.getRoleId());
        }
        List<Role> roles = roleMapper.selectBatchIds(roleIds);
        if (ObjectUtil.isEmpty(roles)){
            Asserts.fail("获取角色信息失败");
        }
        List<String> roleNames = new ArrayList<>(roles.size());
        for (Role role : roles){
            roleNames.add(role.getRoleName());
        }
        return roleNames;
    }
}
