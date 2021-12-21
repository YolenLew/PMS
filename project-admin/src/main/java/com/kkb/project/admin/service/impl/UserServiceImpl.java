package com.kkb.project.admin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.admin.dao.UserMapper;
import com.kkb.project.admin.domain.User;
import com.kkb.project.admin.service.UserService;
import com.kkb.project.common.exception.Asserts;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


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
                .orderByAsc("manager_rank"));
        if (ObjectUtil.isNull(result)) {
            Asserts.fail("暂无名人堂成员信息");
        }
        return result;
    }
    /**
     * 根据用户类型和优秀导师项目数标准发现导师数目
     * @param type
     * @return 优秀导师数目
     */
    @Override
    public Integer findLeaderNumById(Byte type) {
        Integer goodNum = 5;
        Integer count = this.count(new QueryWrapper<User>()
                .eq("is_deleted", 0)
                .eq("type", 1)
                .ge("project_number", goodNum));
        if (count == 0){
            Asserts.fail("暂无优秀导师");
        }
        return count;
    }

    /**
     * 删除名次
     *
     * @param id 传入id
     */
    @Override
    public void removeRankById(Long id) {
        boolean result = this.update(new UpdateWrapper<User>().set("is_deleted", 1).eq("id", id));
        if (!result) {
            Asserts.fail("用户不存在");
        }
    }

    /**
     * 根据name进行模糊查询
     *
     * @param name 查询信息
     * @return 查询结果
     */
    @Override
    public List<User> findLikeUser(String name) {
        List<User> result = this.list(new QueryWrapper<User>().like("name", name));
        if (ObjectUtil.isEmpty(result)) {
            Asserts.fail("用户不存在");
        }
        return result;
    }
}
