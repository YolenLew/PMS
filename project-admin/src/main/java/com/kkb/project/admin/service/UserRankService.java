package com.kkb.project.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.admin.domain.User;

/**
 * @Author xjy
 * @Date 2021/4/24
 * @Description 名人堂排名service接口
 */
public interface UserRankService extends IService<User> {
    /**
     * 根据type分页返回用户列表
     *
     * @param type      用户类型
     * @param pageParam 翻页对象
     * @return 分页用户列表
     */
    IPage<User> findUserByType(Byte type, Page<User> pageParam);

}
