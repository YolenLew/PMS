package com.kkb.project.auth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.kkb.project.auth.dao.ResourceMapper;
import com.kkb.project.auth.domain.Resource;
import com.kkb.project.auth.domain.UserRegister;
import com.kkb.project.auth.service.UserRegisterService;
import com.kkb.project.auth.service.UserRoleService;
import com.kkb.project.common.constant.AuthConstant;
import com.kkb.project.common.exception.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author lemon
 * @version 1.0
 * @description 根据spring security规范，自定义UserDetailsService进行用户信息认证
 * @date 2021/04/27
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserRegisterService userRegisterService;

    /**
     * 初始化资源角色对应关系 用于网关鉴权
     */
    @PostConstruct
    public void initData(){
        redisTemplate.delete(AuthConstant.RESOURCE_ROLES_MAP_KEY);
        List<Resource> resources = resourceMapper.selectList(null);
        if (ObjectUtil.isEmpty(resources)){
            Asserts.fail("获取资源信息失败");
        }
        Map<String, List<String>> resourceRolesMap = new HashMap<>(64);
        for (Resource resource : resources){
            List<String> roleList = Arrays.asList(resource.getRole().trim().split(","));
            resourceRolesMap.put(resource.getUrl(), roleList);
        }
        System.out.println(resourceRolesMap);
        redisTemplate.opsForHash().putAll(AuthConstant.RESOURCE_ROLES_MAP_KEY, resourceRolesMap);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 判断登录模式：密码模式or验证码模式
        String loginType = "";
        if (username.contains(AuthConstant.SMS_LOGIN_TYPE_PREFIX)) {
            loginType = AuthConstant.SMS_LOGIN_TYPE_PREFIX;
            username = username.replace(loginType, "");
        }
        UserRegister userRegister = userRegisterService.loadUserByUsername(username);
        if (ObjectUtil.isNull(userRegister)){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        // 验证码模式：需额外加密一次密码，便于后续校验
        if (AuthConstant.SMS_LOGIN_TYPE_PREFIX.equals(loginType)) {
            userRegister.setPassword(BCrypt.hashpw(userRegister.getPassword()));
        }
        // 通过用户id查询权限id
        Long id = userRegister.getId();
        List<String> roleNames = userRoleService.getRolesById(id);
        // 用户角色查询
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (String roleName : roleNames){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(AuthConstant.AUTHORITY_PREFIX + roleName);
            grantedAuthorities.add(grantedAuthority);
        }
        return new User(userRegister.getPhone(), userRegister.getPassword(), grantedAuthorities);
    }
}
