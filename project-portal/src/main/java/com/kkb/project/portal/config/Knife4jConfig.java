package com.kkb.project.portal.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.kkb.project.common.config.BaseSwaggerConfig;
import com.kkb.project.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @Author River
 * @Date 2021/4/16 0:08
 * @Description portal相关接口文档
 * @Version 1.0
 **/
@EnableKnife4j
@Configuration
@EnableSwagger2
public class Knife4jConfig extends BaseSwaggerConfig {
    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.kkb.project.portal")
                .title("project-portal")
                .description("真项目 【project-portal】相关接口文档")
                .contactName("kkb")
                .version("1.0")
                .enableSecurity(true)
                .groupName("project-portal")
                .build();
    }
}
