package com.kkb.project.portal.service;

import com.kkb.project.portal.domain.LeaderSignup;
import com.kkb.project.portal.service.base.IBaseService;


/**
 * @Author ynb
 * @Date 2021/4/20
 * @Description 导师报名接口
 **/
public interface LeaderService extends IBaseService<LeaderSignup> {
    /**
     * 导师报名，新增数据
     * @param leaderId 导师ID
     * @param projectId 项目ID
     */
    public void leaderSignUp(long leaderId, long projectId);
}
