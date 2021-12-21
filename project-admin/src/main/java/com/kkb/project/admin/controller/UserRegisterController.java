package com.kkb.project.admin.controller;

import com.kkb.project.admin.domain.AddGroup;
import com.kkb.project.admin.domain.UserInfo;
import com.kkb.project.admin.service.UserRegisterService;
import com.kkb.project.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户注册管理控制层
 *
 * @author Yolen
 * @date 2021/4/29
 */
@RestController
@Api(value = "UserRegisterController", tags = "用户注册管理")
@Validated
@RequestMapping("/admin/user")
public class UserRegisterController {

    @Autowired
    private UserRegisterService userRegisterService;

    @ApiOperation("完善身份资料")
    @PostMapping("/add/identity")
    public CommonResult addIdentity(@Validated(AddGroup.class) @RequestBody UserInfo userInfo) {
        userRegisterService.addIdentity(userInfo);
        return CommonResult.success(null, "身份信息提交成功");
    }

    @ApiOperation("修改个人资料")
    @PostMapping("/update")
    public CommonResult updateUserInfo(@Validated @RequestBody UserInfo userInfo) {
        userRegisterService.updateUserInfo(userInfo);
        return CommonResult.success(null, "修改成功");
    }
}
