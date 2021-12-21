package com.kkb.project.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.common.api.CommonPage;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.portal.dao.ParticipantPartakeDao;
import com.kkb.project.portal.domain.ParticipantPartake;
import com.kkb.project.portal.domain.ParticipantSignup;
import com.kkb.project.portal.domain.constant.ParticipantSignUpStatus;
import com.kkb.project.portal.service.ParticipantPartakeService;
import com.kkb.project.portal.service.util.PageUtil;
import org.springframework.stereotype.Service;

import com.kkb.project.portal.domain.constant.DeletedStatus;
import com.kkb.project.portal.domain.constant.ProjectStatus;

import java.util.*;

/**
 * @Author: 单名一个川（瞿正杰 ）
 * @Description:每次当你产生想要努力的念头时，都是未来的你对你发出的求救！
 * @Date Created in 2021-04-20 23:57
 * @Modified By:
 */
@Service
public class ParticipantPartakeServiceImpl extends ServiceImpl<ParticipantPartakeDao,ParticipantPartake> implements ParticipantPartakeService{

    /**
     * 分页查询已参与学员信息
     * @param projectId 项目id
     * @return 返回ParticipantPartake的list数据
     */
    @Override
    public CommonPage<ParticipantPartake> findAllPartake(long projectId, Integer pageNum, Integer pageSize) {
        IPage<ParticipantPartake> page = new Page<>(pageNum,pageSize);
        QueryWrapper<ParticipantPartake> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_Id", projectId).eq("status", ParticipantSignUpStatus.ACCEPTED.value());
        IPage<ParticipantPartake> iPage = this.page(page, queryWrapper);
        List<ParticipantPartake> list = iPage.getRecords();
        if (list.size() <= 0){
            Asserts.fail("未查询到已参与学生信息");
        }
        return PageUtil.wrapToCommonPage(list,page);
    }

    /**
     * 添加选择报名学员
     * @param projectId  项目ID
     * @param participantIds  报名学生IDs
     * @param leaderId      导师ID
     * @return 添加结果
     */
    @Override
    public void participantsAccept(Long projectId, Collection<Long> participantIds, Long leaderId) {
        List<ParticipantPartake> list = new LinkedList<>();
        participantIds.forEach(it -> {
            ParticipantPartake p = new ParticipantPartake(0, new Date(),leaderId,new Date(),leaderId,null,it,projectId, ProjectStatus.PUBLISHED.value(), DeletedStatus.NOT_DELETED.value());
            list.add(p);
        });
        boolean result = this.saveBatch(list);
        if (!result) {
            Asserts.fail("添加报名学员失败");
        }
    }

}
