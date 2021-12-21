package com.kkb.project.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kkb.project.admin.domain.User;

import com.kkb.project.admin.service.*;
import com.kkb.project.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;


/**
 * @ClassName UserRankController
 * @Author qz
 * @Date 2021/4/24 22:10
 * @Description 名人堂管理名次页面
 * @Version 2.0
 **/
@Api(value = "HallFameController", tags = "名人堂")
@RestController
@RequestMapping("/admin/hall/fame")
public class HallFameController {

    @Autowired
    private UserService userService;
    @Autowired
    private FameHallService fameHallService;
    @Autowired
    private UserRankService rankService;

    @ApiOperation(value = "根据姓名进行搜索", notes = "根据姓名模糊搜索用户")
    @ApiImplicitParam(name = "name", value = "用户姓名", required = true, dataType = "String")
    @PostMapping("/user/find/like/{name}")
    public CommonResult<List<User>> findLikeUser(@PathVariable String name) {
        List<User> result = userService.findLikeUser(name);
        return CommonResult.success(result, "查找成功");
    }

    @ApiOperation(value = "删除名次", notes = "根据ID来删除名次")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @PostMapping("/remove/rank/{id}")
    public CommonResult removeRank(@PathVariable Long id) {
        userService.removeRankById(id);
        return CommonResult.success(null, "删除名次成功");
    }

    @ApiOperation(value = "关联名次", notes = "根据managerRank关联名次")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "managerRank", value = "设置名次", required = true, dataType = "Byte"),
    })
    @PostMapping("/update")
    public CommonResult<User> updateManagerRank(@NotEmpty(message = "请选择要设置名次的用户")
                                                @Validated Long id,
                                                @NotEmpty(message = "请设置名次")
                                                @Validated Byte managerRank) {
        fameHallService.updateSort(id, managerRank);
        return CommonResult.success(null, "关联名次成功");
    }

    @ApiOperation(value = "查寻名人堂排名", notes = "根据用户类型查找名人堂成员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "用户类型", required = true, dataType = "Byte"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", defaultValue = "1", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", defaultValue = "10", dataType = "int")
    })
    @PostMapping("/find/{type}")
    public CommonResult<IPage<User>> findUserByType(@PathVariable Byte type,
                                                    @RequestParam(defaultValue = "1") Integer pageNum,
                                                    @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<User> pageParam = new Page<>(pageNum, pageSize);
        IPage<User> result = rankService.findUserByType(type, pageParam);
        return CommonResult.success(result, "查寻名人堂排名成功");
    }

}
