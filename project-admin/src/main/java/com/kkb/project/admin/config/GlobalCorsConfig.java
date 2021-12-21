package com.kkb.project.admin.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


/**
 * @ClassName GlobalCorsConfig
 * @Author River
 * @Date 2021/4/25 21:23
 * @Description 全局跨域配置
 * @Version 1.0
 **/
@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许所有域名进行跨域调用
        corsConfiguration.addAllowedOrigin("*");
        // 允许跨域发送 cookie
        corsConfiguration.setAllowCredentials(true);
        // 放行全部的原始头信息
        corsConfiguration.addAllowedHeader("*");
        // 允许所有请求方法跨域调用
        corsConfiguration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsFilter(source);

    }


}
