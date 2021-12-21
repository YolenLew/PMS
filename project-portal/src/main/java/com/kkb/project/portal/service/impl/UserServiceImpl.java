package com.kkb.project.portal.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.portal.dao.UserMapper;
import com.kkb.project.portal.domain.*;
import com.kkb.project.portal.service.UserService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Xu yu hao
 * @Date 2021/4/20 0:08
 * @Description 用户类型接口实现类
 * @Version 1.0
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 根据用户类型和优秀导师项目数标准发现导师数目
     *
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
        if (count == 0) {
            Asserts.fail("暂无优秀导师");
        }
        return count;
    }


    /**
     * 根据id集合查询用户列表
     *
     * @param ids id集合
     * @return 用户列表
     */
    @Override
    public List<User> getByIds(Collection<Long> ids) {
        if (!(ids instanceof Set)) {
            ids = CollectionUtil.newHashSet(ids);
        }
        List<User> users = this.listByIds(ids);
        if (ids.size() > users.size()) {
            // 有 id 没有查询到
            StringBuilder failIds = new StringBuilder().append(" (");
            List<Long> selectedIds = users.stream().map(User::getId).collect(Collectors.toList());
            ids.forEach(id -> {
                if (!selectedIds.contains(id)) {
                    failIds.append(id).append(", ");
                }
            });
            failIds.append(") ");
            Asserts.fail("未查询到id 为: " + failIds.toString() + "的用户");
        }
        return users;
    }

    /**
     * 根据id查找用户信息
     *
     * @param id 传入id
     * @return 返回user对象
     */
    @Override
    public User findUserInfoById(Long id) {
        User user = this.getById(id);
        if (ObjectUtil.isNull(user)) {
            Asserts.fail("用户不存在");
        }
        return user;
    }


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
     * 根据 id 集合查询User集合
     *
     * @param ids id集合
     * @return User集合, 使用Map来表示, Key 是 User.Id, Value是User
     * @author __SAD_DOG__
     */
    @Override
    public Map<Long, User> findUsersByIds(Collection<Long> ids) {
        List<User> res = this.getByIds(ids);
        Map<Long, User> resMap = new HashMap<>(ids.size());
        res.forEach(it -> resMap.put(it.getId(), it));
        return resMap;
    }
}
