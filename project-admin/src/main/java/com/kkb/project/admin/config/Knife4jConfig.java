package com.kkb.project.admin.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.kkb.project.common.config.BaseSwaggerConfig;
import com.kkb.project.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName Knife4jConfig
 * @Author River
 * @Date 2021/4/21 14:48
 * @Description Knife4j 配置
 * @Version 1.0
 **/
@EnableKnife4j
@EnableSwagger2
@Configuration
public class Knife4jConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.kkb.project.admin")
                .title("project-admin")
                .description("真项目 【project-admin】相关接口文档")
                .contactName("kkb")
                .version("1.0")
                .enableSecurity(true)
                .groupName("project-admin")
                .build();
    }
}
