package com.kkb.project.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.admin.dao.FameHallMapper;
import com.kkb.project.admin.domain.User;
import com.kkb.project.admin.service.FameHallService;
import com.kkb.project.common.exception.Asserts;
import org.springframework.stereotype.Service;

/**
 * @author Alices
 * @date 2021/4/22
 * @Description 名人堂关联名次 service 实现类
 */
@Service
public class FameHallServiceImpl extends ServiceImpl<FameHallMapper, User> implements FameHallService {

    /**
     * 通过id进行修改名次
     *
     * @param id          传入用户ID
     * @param managerRank 传入修改的名次
     */
    @Override
    public void updateSort(Long id, Byte managerRank) {
        boolean flag = this.update(new UpdateWrapper<User>().set("manager_rank", managerRank).eq("id", id));
        if (!flag) {
            Asserts.fail("用户不存在");
        }
    }
}
