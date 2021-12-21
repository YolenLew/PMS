package com.kkb.project.portal.service;

import com.kkb.project.common.api.CommonPage;
import com.kkb.project.portal.domain.ParticipantPartake;
import com.kkb.project.portal.service.base.IBaseService;

import java.util.Collection;
import java.util.List;

/**
 * @Author: 单名一个川（瞿正杰 ）
 * @Description:每次当你产生想要努力的念头时，都是未来的你对你发出的求救！
 * @Date Created in 2021-04-20 23:53
 * @Modified By:
 */
public interface ParticipantPartakeService extends IBaseService<ParticipantPartake> {
    /**
     * 分页查询已报名学员信息
     * @param projectId 项目id
     * @param pageNum 当前页码
     * @param pageSize 页大小
     * @return 返回ParticipantPartake的list数据
     */
    CommonPage<ParticipantPartake> findAllPartake(long projectId, Integer pageNum, Integer pageSize);

    /**
     * 添加选择报名学员
     * @param projectId  项目ID
     * @param participantIds  报名学生IDs
     * @param leaderId      导师ID
     */
    void participantsAccept(Long projectId, Collection<Long> participantIds, Long leaderId);
}
