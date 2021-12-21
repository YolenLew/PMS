package com.kkb.project.portal.service;

import com.kkb.project.portal.domain.vo.ProjectWorkTypeAndStyleVo;

import java.util.List;

/**
 * @author __SAD_DOG__
 * @date 2021-04-29
 * 获取工作类型和方式
 */
public interface ProjectWorkTypeAndStyleFacadeService {
    /**
     * 获取所有的项目工作类型和工作方式
     * @return 所有的项目工作类型和工作方式
     */
    ProjectWorkTypeAndStyleVo getAll();
}
