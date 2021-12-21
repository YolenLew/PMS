package com.kkb.project.portal.service;

import com.kkb.project.common.api.CommonPage;
import com.kkb.project.portal.domain.constant.ProjectStatus;
import com.kkb.project.portal.domain.constant.RoleType;

/**
 * @author __SAD_DOG__
 * @date 2021-04-27
 * 用于项目管理-主要流程页面业务的Service
 * 调用多个serviceImpl实现连表
 */
public interface ProjectManageFacade {
    /**
     * 查询项目管理-主要流程中的项目信息列表
     * @param pageNum 当前页码
     * @param pageSize 页面大小
     * @param userId 用户Id
     * @param roleType 用户类型
     * @param projectStatus 项目状态
     * @return 返回项目列表Page页面
     */
    CommonPage<?> getProjectManageVoList(Long pageNum, Long pageSize,Long userId, RoleType roleType, ProjectStatus projectStatus);
}
