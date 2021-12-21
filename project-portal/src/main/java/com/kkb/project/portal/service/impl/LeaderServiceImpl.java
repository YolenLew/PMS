package com.kkb.project.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.portal.dao.LeaderSignupDao;
import com.kkb.project.portal.domain.LeaderSignup;
import com.kkb.project.portal.service.LeaderService;
import org.springframework.stereotype.Service;

/**
 * @Author ynb
 * @Date 2021/4/20
 * @Description 导师报名接口实现类
 **/
@Service
public class LeaderServiceImpl extends ServiceImpl<LeaderSignupDao,LeaderSignup> implements LeaderService {


    /**
     * 导师报名新增数据
     * @param leaderId 导师ID
     * @param projectId 项目ID
     */
    @Override
    public void leaderSignUp(long leaderId, long projectId) {
        LeaderSignup leaderSignup =  new LeaderSignup();
        leaderSignup.setUpdateUser(leaderId);
        leaderSignup.setLeaderId(leaderId);
        leaderSignup.setProjectId(projectId);
        QueryWrapper<LeaderSignup> wrapper = Wrappers.query();
        wrapper.eq("leader_id",leaderId);
        wrapper.eq("project_id",projectId);
        LeaderSignup leader = baseMapper.selectOne(wrapper);
        //查看是否已经存在数据
        if (leader != null){
            Asserts.fail("导师已报名");
        }

        //将导师报名信息插入到participant_signup表中，获取数据库更新的数量
        boolean flag = this.saveOrUpdate(leaderSignup);
        if(!flag){
            Asserts.fail("导师报名失败");
        }
    }
}