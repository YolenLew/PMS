package com.kkb.project.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author lemon
 * @version 1.0
 * @description RestTemplate demo for login，可删除
 * @date 2021/04/26
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
