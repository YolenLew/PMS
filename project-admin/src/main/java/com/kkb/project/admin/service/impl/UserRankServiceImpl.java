package com.kkb.project.admin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.admin.dao.UserMapper;
import com.kkb.project.admin.domain.User;
import com.kkb.project.admin.service.UserRankService;
import com.kkb.project.common.exception.Asserts;
import org.springframework.stereotype.Service;

/**
 * @Author xjy
 * @Description 名人堂排名 service 实现类
 * @Date 2021年04月24日  22:04:55
 **/
@Service
public class UserRankServiceImpl extends ServiceImpl<UserMapper, User> implements UserRankService {
    /**
     * 根据type分页返回用户列表
     *
     * @param type      用户类型
     * @param pageParam 翻页对象
     * @return 分页用户列表
     */
    @Override
    public IPage<User> findUserByType(Byte type, Page<User> pageParam) {
        IPage<User> result = this.page(pageParam, new QueryWrapper<User>().eq("type", type)
                .eq("is_deleted", 0)
                .orderByDesc("project_number"));
        if (result.getTotal() == 0) {
            Asserts.fail("暂无名人堂成员信息");
        }
        return result;
    }
}
