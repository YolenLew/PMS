package com.kkb.project.common.constant;

/**
 * @Description: 权限相关常量定义
 * @author: peng.ni
 * @date: 2021/04/07
 */
public interface AuthConstant {

    /**
     * JWT存储权限前缀
     */
    String AUTHORITY_PREFIX = "ROLE_";

    /**
     * JWT存储权限属性
     */
    String AUTHORITY_CLAIM_NAME = "authorities";

    /**
     * 后台管理client_id
     */
    String ADMIN_CLIENT_ID = "admin-app";

    /**
     * 前台商城client_id
     */
    String PORTAL_CLIENT_ID = "portal-app";

    /**
     * 后台管理接口路径匹配
     */
    String ADMIN_URL_PATTERN = "/kkb-admin/**";

    /**
     * Redis缓存权限规则key
     */
    String RESOURCE_ROLES_MAP_KEY = "auth:resourceRolesMap";

    /**
     * 认证信息Http请求头
     */
    String JWT_TOKEN_HEADER = "Authorization";

    /**
     * JWT令牌前缀
     */
    String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * 用户信息Http请求头
     */
    String USER_TOKEN_HEADER = "user";

    /**
     * 登录类型标识：密码登录
     */
    String PWD_LOGIN_TYPE_PREFIX = "pwd_";

    /**
     * 登录类型标识：验证码登录
     */
    String SMS_LOGIN_TYPE_PREFIX = "sms_";

    /**
     * Redis缓存短信验证码key前缀
     */
    String SMS_AUTH_CODE_KEY_PREFIX = "sms:authCode:";

}
