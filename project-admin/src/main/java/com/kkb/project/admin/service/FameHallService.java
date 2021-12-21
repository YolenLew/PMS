package com.kkb.project.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.admin.domain.User;

/**
 * @author Alices
 * @date 2021/4/22
 * @Description 用户表 Service
 * @Version 1.0
 */
public interface FameHallService extends IService<User> {

    /**
     * 通过id进行修改名次
     *
     * @param id          传入用户ID
     * @param managerRank 传入修改的名次
     */
    void updateSort(Long id, Byte managerRank);

}