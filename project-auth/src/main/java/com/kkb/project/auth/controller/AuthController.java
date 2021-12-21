package com.kkb.project.auth.controller;

import com.kkb.project.auth.domain.Oauth2TokenDTO;
import com.kkb.project.common.api.CommonResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

/**
 * @author lemon
 * @version 1.0
 * @description 自定义token获取接口
 * @date 2021/04/27
 */
@RestController
@Api(value = "AuthController", tags = "认证中心颁发token")
@RequestMapping("/oauth")
public class AuthController {

    /**
     * Jwt令牌前缀
     */
    private static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 获取token端点
     */
    @Autowired
    private TokenEndpoint tokenEndpoint;

    @PostMapping("/token")
    public CommonResult<Oauth2TokenDTO> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        ResponseEntity<OAuth2AccessToken> oAuth2AccessTokenResponseEntity = tokenEndpoint.postAccessToken(principal, parameters);
        OAuth2AccessToken oAuth2AccessToken = oAuth2AccessTokenResponseEntity.getBody();
        // 封装token信息
        Oauth2TokenDTO oauth2TokenDto = Oauth2TokenDTO.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead(TOKEN_PREFIX).build();
        return CommonResult.success(oauth2TokenDto);
    }
}
