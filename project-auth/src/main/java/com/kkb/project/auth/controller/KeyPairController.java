package com.kkb.project.auth.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @author lemon
 * @version 1.0
 * @description 自定义公钥获取接口
 * @date 2021/04/27
 */
@RestController
public class KeyPairController {

    @Autowired
    private KeyPair keyPair;

    @GetMapping("/rsa/publicKey")
    public Map<String, Object> getKey(){
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 封装公钥信息
        RSAKey rsaKey = new RSAKey.Builder(publicKey).build();
        return new JWKSet(rsaKey).toJSONObject();
    }
}
