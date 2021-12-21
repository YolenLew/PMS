package com.kkb.project.gateway.filter;

import com.kkb.project.common.constant.AuthConstant;
import com.kkb.project.gateway.config.IgnoreUrlsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

/**
 * @author lemon
 * @version 1.0
 * @description 当白名单内的url请求携带了过期的token或签名不正确的token时 访问该路径会报错 所以移除白名单的token
 * @date 2021/04/29
 */
@Component
public class IgnoreUrlsRemoveJwtFilter implements WebFilter {

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        List<String> urls = ignoreUrlsConfig.getUrls();
        for (String url : urls){
            if (pathMatcher.match(url, uri.getPath())){
                request = exchange.getRequest().mutate().header(AuthConstant.JWT_TOKEN_HEADER, "").build();
                exchange = exchange.mutate().request(request).build();
                return chain.filter(exchange);
            }
        }
        return chain.filter(exchange);
    }
}
