package com.kkb.project.auth.controller;

import cn.hutool.core.util.ObjectUtil;
import com.kkb.project.auth.domain.Oauth2TokenDTO;
import com.kkb.project.auth.domain.UserRegister;
import com.kkb.project.auth.service.UserRegisterService;
import com.kkb.project.common.api.CommonResult;
import com.kkb.project.common.constant.AuthConstant;
import com.kkb.project.common.exception.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lemon
 * @version 1.0
 * @description demo for login，可删除
 * @date 2021/04/27
 */
@RestController
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRegisterService userRegisterService;

    @GetMapping("/login")
    public CommonResult<Oauth2TokenDTO> login(String username, String password){
        UserRegister userRegister = userRegisterService.loadUserByUsername(username);
        if (ObjectUtil.isNull(userRegister)){
            Asserts.fail("用户名或密码不正确");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean matches = bCryptPasswordEncoder.matches(password, userRegister.getPassword());
        if (!matches){
            Asserts.fail("用户名或密码不正确");
        }

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.put("client_id", Collections.singletonList("kkb"));
        parameters.put("client_secret",Collections.singletonList("secret"));
        parameters.put("grant_type",Collections.singletonList("password"));
        parameters.put("username",Collections.singletonList(username));
        parameters.put("password",Collections.singletonList(password));

        // 封装请求头
        /*String client_info = "kkb:secret";
        client_info = "Basic " + Base64.getEncoder().encodeToString(client_info.getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", client_info);*/
        // 请求参数
        /*MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.put("username", Collections.singletonList(username));
        parameters.put("password", Collections.singletonList(password));
        parameters.put("grant_type", Collections.singletonList("password"));
        parameters.put("scope", Collections.singletonList("all"));
        HttpEntity entity = new HttpEntity(parameters, headers);*/
        HttpEntity entity = new HttpEntity(parameters, null);
        ParameterizedTypeReference<CommonResult<Oauth2TokenDTO>> typeRef = new ParameterizedTypeReference<CommonResult<Oauth2TokenDTO>>(){};
        ResponseEntity<CommonResult<Oauth2TokenDTO>> exchange = restTemplate.exchange("http://localhost:8088/oauth/token", HttpMethod.POST, entity, typeRef);
        CommonResult<Oauth2TokenDTO> commonResult = exchange.getBody();
        Oauth2TokenDTO data = commonResult.getData();
        return CommonResult.success(data, "登录成功");
    }
}
