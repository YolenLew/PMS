package com.kkb.project.portal.config;

import com.kkb.project.portal.domain.Minio;
import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author lemon
 * @Date 2021/4/17
 * @Description minio配置
 * @Version 1.0
 **/

@Configuration
public class MinioConfig {

    @Autowired
    private Minio minio;

    @Bean
    public MinioClient minioClient() throws InvalidPortException, InvalidEndpointException {
       return new MinioClient(minio.getEndpoint(), minio.getAccessKey(), minio.getSecretKey());
    }
}

