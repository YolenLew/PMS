package com.kkb.project.admin.service;

import com.kkb.project.admin.domain.vo.ProjectWorkTypeAndStyleVo;

/**
 * @author __SAD_DOG__
 * @date 2021-04-29
 * 获取工作类型和方式
 */
public interface ProjectWorkTypeAndStyleFacadeService {
    /**
     * 获取所有的项目工作类型和工作方式
     * @return 所有的项目工作类型和方式
     */
    ProjectWorkTypeAndStyleVo getAll();
}
