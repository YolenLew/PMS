package com.kkb.project.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.admin.domain.User;

import java.util.List;


/**
 * @Author MaXia
 * @Date 2021/4/22 18:24
 * @Description 用户表 Service
 * @Version 1.0
 **/
public interface UserService extends IService<User> {
    /**
     * 根据id删除名次
     *
     * @param id 传入id
     */
    void removeRankById(Long id);

    /**
     * 根据name进行模糊查询
     *
     * @param name 查询信息
     * @return 查询结果
     */
    List<User> findLikeUser(String name);

    /**根据type分页返回用户列表
     * @param type 用户类型
     * @param pageParam 翻页对象
     * @return 分页用户列表
     */
    IPage<User> findUserByType(Byte type, Page<User> pageParam);

    /**
     * 根据用户类型和优秀导师项目数标准发现导师数目
     * @param type
     * @return 优秀导师数目
     */
    Integer findLeaderNumById(Byte type);
}
