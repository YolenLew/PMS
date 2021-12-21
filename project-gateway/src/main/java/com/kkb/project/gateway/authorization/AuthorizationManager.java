package com.kkb.project.gateway.authorization;

import cn.hutool.core.util.ObjectUtil;
import com.kkb.project.common.constant.AuthConstant;
import com.kkb.project.gateway.config.IgnoreUrlsConfig;
import com.nimbusds.jose.JWSObject;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lemon
 * @version 1.0
 * @description 鉴权管理器
 * @date 2021/04/26
 */
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        // 获取请求uri
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        // 放行白名单路径
        List<String> urls = ignoreUrlsConfig.getUrls();
        for (String url : urls){
            if (pathMatcher.match(url, uri.getPath())){
                return Mono.just(new AuthorizationDecision(true));
            }
        }
        // 对应跨域的预检请求直接放行
        if (request.getMethod() == HttpMethod.OPTIONS){
            return Mono.just(new AuthorizationDecision(true));
        }
        // 获取token
        String token = request.getHeaders().getFirst(AuthConstant.JWT_TOKEN_HEADER);
        if (ObjectUtil.isEmpty(token)){
            return Mono.just(new AuthorizationDecision(false));
        }
        // TODO 根据token查询redis，是否是登出过未过期的token
        // 从请求中获取的token格式为"Bearer " + token
        String realToken = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
        // 鉴权
        Map<Object, Object> resourceRolesMap = redisTemplate.opsForHash().entries(AuthConstant.RESOURCE_ROLES_MAP_KEY);
        Iterator<Object> iterator = resourceRolesMap.keySet().iterator();
        List<String> roleList = new ArrayList<>();
        while (iterator.hasNext()){
            String pattern = (String) iterator.next();
            if (pathMatcher.match(pattern, uri.getPath())){
                roleList = (List<String>) resourceRolesMap.get(pattern);
            }
        }
        roleList = roleList.stream().map(i -> i = AuthConstant.AUTHORITY_PREFIX + i).collect(Collectors.toList());
        // 从token获取用户权限
        JWSObject parse;
        try {
            parse = JWSObject.parse(realToken);
        } catch (ParseException e) {
            e.printStackTrace();
            return Mono.just(new AuthorizationDecision(false));
        }
        JSONObject jsonObject = parse.getPayload().toJSONObject();
        List<String> authorities = (List<String>) jsonObject.get("authorities");
        boolean contains = false;
        for (String role : authorities){
            if (roleList.contains(role)){
                contains = true;
                break;
            }
        }
        if (!contains){
            return Mono.just(new AuthorizationDecision(false));
        }
        return Mono.just(new AuthorizationDecision(true));
    }
}
