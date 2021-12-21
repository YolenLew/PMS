package com.kkb.project.auth.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lemon
 * @version 1.0
 * @description rsa证书相关属性
 * @date 2021/04/27
 */
@Component
@Data
@ConfigurationProperties(prefix = "keypair")
public class RSAProperty {

    private String resource;

    private String alias;

    private String password;
}
