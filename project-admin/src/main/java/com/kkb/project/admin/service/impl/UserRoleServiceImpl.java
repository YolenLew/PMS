package com.kkb.project.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.admin.dao.UserRoleMapper;
import com.kkb.project.admin.domain.UserRole;
import com.kkb.project.admin.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
