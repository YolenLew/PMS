package com.kkb.project.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kkb.project.admin.domain.User;
import com.kkb.project.admin.domain.vo.SomeNumVo;
import com.kkb.project.admin.domain.vo.SuccessCaseVo;
import com.kkb.project.admin.service.ClientService;
import com.kkb.project.admin.service.LeaderCommentService;
import com.kkb.project.admin.service.SuccessCaseService;
import com.kkb.project.admin.service.UserService;
import com.kkb.project.common.api.CommonPage;
import com.kkb.project.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName HomeController
 * @Author River
 * @Date 2021/4/28 23:40
 * @Description 后台 首页
 * @Version 1.0
 **/
@Api(value = "HallFameController", tags = "首页")
@RestController
@RequestMapping("/admin/home")
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private LeaderCommentService leaderCommentService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private SuccessCaseService successCaseService;

    @ApiOperation(value = "根据用户类型查询优秀导师或者学员", notes = "导师为0，学生为1，默认只查询优秀导师或者学生前3个，指定pageSize后可查询全部学生或导师")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型ID", required = true, dataType = "Byte"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", defaultValue = "1", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", defaultValue = "3", dataType = "int")
    })
    @PostMapping("/{type}")
    public CommonResult<IPage<User>> findTypeFame(@PathVariable Byte type,
                                                  @RequestParam(defaultValue = "1") int pageNum,
                                                  @RequestParam(defaultValue = "3") int pageSize) {
        Page<User> pageParam = new Page<>(pageNum, pageSize);
        IPage<User> result = userService.findUserByType(type, pageParam);
        return CommonResult.success(result);
    }

    @ApiOperation(value = "查询优秀导师、好评率、累计服务企业", notes = "根据用户类型查找")
    @ApiImplicitParam(name = "type", value = "用户类型", required = true, dataType = "Byte")
    @PostMapping("/someNum/{type}")
    public CommonResult<SomeNumVo> findSomeNumByType(@PathVariable Byte type) {
        Integer leaderNum = userService.findLeaderNumById(type);
        Double commentRate = leaderCommentService.findCommentRate();
        Integer clientNum = clientService.findClientNum();
        SomeNumVo numVo = new SomeNumVo(leaderNum, commentRate, clientNum);
        return CommonResult.success(numVo, "查询优秀导师、好评率、累计服务企业成功");
    }

    @ApiOperation(value = "默认查询成功案例前三名按点整数量来查", notes = "根据提供的每页显示数量可选择查全部，默认显示前三名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页数", defaultValue = "1", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", defaultValue = "3", dataType = "int")
    })
    @PostMapping("/top/rank")
    public CommonResult<CommonPage<SuccessCaseVo>> findTopThree(@RequestParam(defaultValue = "1") Integer pageNum,
                                                                @RequestParam(defaultValue = "3") Integer pageSize) {
        CommonPage<SuccessCaseVo> successCase = successCaseService.findSuccessCase(pageNum, pageSize);
        return CommonResult.success(successCase, "查找成功");
    }
}
