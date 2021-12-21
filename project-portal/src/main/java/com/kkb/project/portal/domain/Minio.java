package com.kkb.project.portal.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author lemon
 * @Date 2021/4/17
 * @Description minio配置读取
 * @Version 1.0
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class Minio {

    private String endpoint;

    private String accessKey;

    private String secretKey;

    private String bucketName;
}
