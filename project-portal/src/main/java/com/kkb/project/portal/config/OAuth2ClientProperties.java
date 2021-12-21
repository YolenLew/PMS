package com.kkb.project.portal.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * OAuth2客户端属性配置
 *
 * @author  Yolen
 * @date  2021/5/2
 */
@Data
@Component
@ConfigurationProperties(prefix = "security.oauth2.client")
public class OAuth2ClientProperties {
    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 客户端密钥
     */
    private String clientSecret;

    /**
     * 客户端密钥
     */
    private String grantType;

    /**
     * oauth2令牌授权端点
     */
    private String accessTokenUri;
}
