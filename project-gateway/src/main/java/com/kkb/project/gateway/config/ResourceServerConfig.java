package com.kkb.project.gateway.config;

import cn.hutool.core.util.ArrayUtil;
import com.kkb.project.common.constant.AuthConstant;
import com.kkb.project.gateway.authorization.AuthorizationManager;
import com.kkb.project.gateway.component.RestAuthenticationEntryPoint;
import com.kkb.project.gateway.component.RestfulAccessDeniedHandler;
import com.kkb.project.gateway.filter.IgnoreUrlsRemoveJwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author lemon
 * @version 1.0
 * @description 资源服务器配置
 * @date 2021/04/26
 */
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {

    @Autowired
    private AuthorizationManager authorizationManager;
    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private IgnoreUrlsRemoveJwtFilter ignoreUrlsRemoveJwtFilter;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http){
        http
                // oauth2 resource server support
                .oauth2ResourceServer()
                // Enables JWT Resource Server support.
                .jwt()
                // 读取jwt载荷中的权限信息
                .jwtAuthenticationConverter(jwtAuthenticationConverter());
        //自定义处理JWT请求头过期或签名错误的结果
        http.oauth2ResourceServer().authenticationEntryPoint(restAuthenticationEntryPoint);
        // 移除白名单中的请求头
        http.addFilterBefore(ignoreUrlsRemoveJwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        http.authorizeExchange()
                // 放行白名单
                .pathMatchers(ArrayUtil.toArray(ignoreUrlsConfig.getUrls(), String.class)).permitAll()
                // 其他请求走鉴权管理器
                .anyExchange().access(authorizationManager)
                .and().exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and().csrf().disable();
        return http.build();
    }

    /**
     * ServerHttpSecurity.OAuth2ResourceServerSpec.JwtSpec中默认的ReactiveAuthenticationManager
     * 没有将jwt中authorities的负载部分当做Authentication的权限,我们重新定义一个ReactiveAuthenticationManager权限管理器
     * 默认的权限管理器使用jwtGrantedAuthoritiesConverter作为默认的转换器，就是这个转换器默认只读取"scope", "scp"这两个作为权限
     * spring security中的权限默认是ROLE_ + role name
     * @return
     */
    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthConstant.AUTHORITY_PREFIX);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthConstant.AUTHORITY_CLAIM_NAME);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}
