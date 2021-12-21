package com.kkb.project.portal.service;

import com.kkb.project.common.api.CommonPage;
import com.kkb.project.portal.domain.vo.UserVo;


/**
 * @author __SAD_DOG__
 * @date 2021-05-01
 */
public interface ParticipantAcceptFacadeService {
    /**
     * 分页查询所有已报名的学员
     * @param projectId 项目id
     * @param pageNum 当前页
     * @param pageSize 页面大小
     * @return 返回CommonPage<UserVo>的数据包含报名表学员信息，User头像信息，User技能信息
     */
    CommonPage<UserVo> findAllSignUp(Long projectId, Integer pageNum, Integer pageSize);

    /**
     * 分页查询所有已参与的学员
     * @param projectId 项目id
     * @param pageNum 当前页
     * @param pageSize 页面大小
     * @return 返回CommonPage<UserVo>的数据包含参与表学员信息，User头像信息，User技能信息
     */
    CommonPage<UserVo> findAllPartake(Long projectId, Integer pageNum, Integer pageSize);
}
