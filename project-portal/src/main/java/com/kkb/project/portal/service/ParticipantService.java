package com.kkb.project.portal.service;

import com.kkb.project.common.api.CommonPage;
import com.kkb.project.portal.domain.ParticipantSignup;
import com.kkb.project.portal.service.base.IBaseService;

import java.util.List;

/**
 * @author __SAD_DOG__
 * 参与者(学生) 报名/审核相关service
 */
public interface ParticipantService extends IBaseService<ParticipantSignup> {
    /**
     * 学生报名
     * @param participantId 报名的学生id
     * @param projectId 报名的项目id
     * @return 报名成功返回true, 否则返回false
     */
    void participantSignUp(long participantId, long projectId);

    /**
     * 分页查询已报名的学生
     * @param projectId 项目id
     * @param pageNum 当前页码
     * @param pageSize 页大小
     * @return 该项目所有报名学生
     */
    CommonPage<ParticipantSignup> findAllSignUp(long projectId, Integer pageNum, Integer pageSize);


    /**
     * 修改participant表中singUp的status为已接受
     * @param projectId 项目ID
     * @param acceptIds 用户ID
     */
    void acceptSingUp(Long projectId,List<Long> acceptIds);
}
