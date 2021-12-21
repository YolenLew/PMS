package com.kkb.project.portal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.portal.domain.ParticipantPartake;
import com.kkb.project.portal.domain.ParticipantSignup;
import com.kkb.project.portal.domain.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * @Author River
 * @Date 2021/4/19 0:08
 * @Description 用户表 Service
 * @Version 1.0
 **/
public interface UserService extends IService<User> {

    /**
     * 根据id集合查询用户列表
     * @param ids id集合
     * @return 用户列表
     */
    List<User> getByIds(Collection<Long> ids);

    /**
     * 根据id查找用户信息
     * @param id 传入id
     * @return 返回user对象
     */
    User findUserInfoById(Long id);


    /**根据type分页返回用户列表
     * @param type 用户类型
     * @param pageParam 翻页对象
     * @return 分页用户列表
     */
    IPage<User> findUserByType(Byte type, Page<User> pageParam);

    /**
     * 根据 id 集合查询User集合
     * @author __SAD_DOG__
     * @param ids id集合
     * @return User集合, 使用Map来表示, Key 是 User.Id, Value是User
     */
    Map<Long, User> findUsersByIds(Collection<Long> ids);

    /**
     * 根据用户类型和优秀导师项目数标准发现导师数目
     * @param type
     * @return 优秀导师数目
     */
    Integer findLeaderNumById(Byte type);
}
