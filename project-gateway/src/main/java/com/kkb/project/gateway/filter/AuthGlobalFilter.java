package com.kkb.project.gateway.filter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.kkb.project.common.constant.AuthConstant;
import com.nimbusds.jose.JWSObject;
import net.minidev.json.JSONObject;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import sun.security.util.SecurityConstants;

import java.text.ParseException;

/**
 * @author lemon
 * @version 1.0
 * @description 全局过滤器 解析token 将token信息放到请求头给下游服务器
 * @date 2021/05/01
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String token = request.getHeaders().getFirst(AuthConstant.JWT_TOKEN_HEADER);
        if (StrUtil.isBlank(token) || !StrUtil.startWithIgnoreCase(token, AuthConstant.JWT_TOKEN_PREFIX)) {
            return chain.filter(exchange);
        }
        String realToken = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
        try {
            JWSObject parse = JWSObject.parse(realToken);
            String tokenInfo = parse.getPayload().toString();
            ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().header(AuthConstant.USER_TOKEN_HEADER, tokenInfo).build();
            exchange = exchange.mutate().request(serverHttpRequest).build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
