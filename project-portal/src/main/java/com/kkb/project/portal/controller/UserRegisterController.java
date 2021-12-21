package com.kkb.project.portal.controller;

import com.kkb.project.common.api.CommonResult;
import com.kkb.project.portal.domain.Oauth2TokenDTO;
import com.kkb.project.portal.domain.UserInfo;
import com.kkb.project.portal.service.UserRegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 用户注册管理控制层
 *
 * @author Yolen
 * @date 2021/4/29
 */
@RestController
@Api(value = "UserRegisterController", tags = "用户注册管理")
@Validated
@RequestMapping("/user")
public class UserRegisterController {

    @Autowired
    private UserRegisterService userRegisterService;

    @ApiOperation("用户注册")
    @PostMapping(value = "/register")
    public CommonResult register(@NotEmpty(message = "手机号不能为空") @RequestParam String phone,
                                 @NotEmpty(message = "密码不能为空") @RequestParam String password) {
        userRegisterService.register(phone, password);
        return CommonResult.success(null, "注册成功");
    }

    @ApiOperation("密码登录")
    @PostMapping(value = "/login")
    public CommonResult<Oauth2TokenDTO> login(@NotEmpty(message = "手机号不能为空") @RequestParam String phone,
                              @NotEmpty(message = "密码不能为空") @RequestParam String password) {
        Oauth2TokenDTO oauth2TokenDTO = userRegisterService.login(phone, password);
        return CommonResult.success(oauth2TokenDTO, "登录成功");
    }

    @ApiOperation("获取验证码")
    @PostMapping(value = "/code")
    public CommonResult<String> getAuthCode(@NotEmpty(message = "手机号不能为空") @RequestParam String phone) {
        String authCode = userRegisterService.getCode(phone);
        return CommonResult.success(authCode, "获取验证码成功");
    }

    @ApiOperation("验证码登录")
    @PostMapping(value = "/login/code")
    public CommonResult<Oauth2TokenDTO> loginByCode(@NotNull @RequestParam String phone, @NotNull @RequestParam String authCode) {
        Oauth2TokenDTO oauth2TokenDTO = userRegisterService.loginByCode(phone, authCode);
        return CommonResult.success(oauth2TokenDTO, "登录成功");
    }

    @ApiOperation("获取当前用户信息")
    @RequestMapping(value = "/info/{loginId}", method = RequestMethod.GET)
    public CommonResult info(@PathVariable(name = "loginId")Long loginId) {
        // TODO 后续优化：从令牌中获取id
        UserInfo userInfo = userRegisterService.getUser(loginId);
        return CommonResult.success(userInfo);
    }
}
