package com.kkb.project.portal.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.common.api.CommonPage;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.portal.dao.ParticipantSignUpDao;
import com.kkb.project.portal.domain.ParticipantSignup;
import com.kkb.project.portal.domain.constant.ParticipantSignUpStatus;

import com.kkb.project.portal.service.ParticipantService;
import com.kkb.project.portal.service.util.PageUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhu_chen_xu
 */
@Service
public class ParticipantServiceImpl extends ServiceImpl<ParticipantSignUpDao,ParticipantSignup> implements ParticipantService{

    @Override
    public void participantSignUp(long participantId, long projectId) {
        ParticipantSignup participantSignup = new ParticipantSignup();
        QueryWrapper<ParticipantSignup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("participant_id",participantId).eq("project_id",projectId);
        ParticipantSignup query = this.getOne(queryWrapper);
        if (query != null){
            Asserts.fail("请勿重复报名");
        }
        //参与人id
        participantSignup.setParticipantId(participantId);
        //项目id
        participantSignup.setProjectId(projectId);
        //更新人id
        participantSignup.setUpdateUser(participantId);
        //乐观锁  暂时用不到 默认插入为零
        participantSignup.setRevision(0);
        //此处修改前代码使用了dao.insert方法
        boolean saveOrUpdate = this.saveOrUpdate(participantSignup);
        if (!saveOrUpdate){
            Asserts.fail("报名失败");
        }
    }

    /**
     * 查询所有已报名的学生信息
     * @param projectId 项目id
     * @return
     */
    @Override
    public CommonPage<ParticipantSignup> findAllSignUp(long projectId, Integer pageNum, Integer pageSize) {
        IPage<ParticipantSignup> page = new Page<>(pageNum,pageSize);
        QueryWrapper<ParticipantSignup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id",projectId).eq("status", ParticipantSignUpStatus.SIGNED_UP.value());
        IPage<ParticipantSignup> iPage = this.page(page, queryWrapper);
        List<ParticipantSignup> list = iPage.getRecords();
        if (list.size() <= 0){
            Asserts.fail("未查询到已报名的学生信息");
        }
        return PageUtil.wrapToCommonPage(list,iPage);
    }

    /**
     * 修改participant表中singUp的status为已接受
     * @param projectId 项目ID
     * @param acceptIds 用户ID
     */
    @Override
    public void acceptSingUp(Long projectId, List<Long> acceptIds) {
        UpdateWrapper<ParticipantSignup> puw = new UpdateWrapper<>();
        puw.eq("project_id",projectId);
        puw.in("participant_id",acceptIds);
        puw.set("status", ParticipantSignUpStatus.ACCEPTED);
        boolean update = this.update(puw);
        if (!update) {
            Asserts.fail("修改用户报名状态失败");
        }
    }
}
