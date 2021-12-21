package com.kkb.project.search.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.kkb.project.common.config.BaseSwaggerConfig;
import com.kkb.project.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API 文档相关配置
 *
 * @author: peng.ni
 * @date: 2021/04/23
 */
@EnableKnife4j
@EnableSwagger2
@Configuration
public class Knife4jConfig extends BaseSwaggerConfig {
    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.kkb.project.search.controller")
                .title("project-search")
                .description("kkb 搜索相关接口文档")
                .contactName("kkb")
                .version("1.0")
                .enableSecurity(false)
                .build();
    }
}
