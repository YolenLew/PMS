package com.kkb.project.portal.service.impl;

import com.kkb.project.common.exception.Asserts;
import com.kkb.project.portal.domain.ProjectWorkStyle;
import com.kkb.project.portal.domain.ProjectWorkType;
import com.kkb.project.portal.domain.vo.ProjectWorkTypeAndStyleVo;
import com.kkb.project.portal.service.ProjectWorkStyleService;
import com.kkb.project.portal.service.ProjectWorkTypeAndStyleFacadeService;
import com.kkb.project.portal.service.ProjectWorkTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author __SAD_DOG__
 * @date 2021-04-29
 * 获取工作类型和方式
 */
@Service
public class ProjectWorkTypeAndStyleFacadeServiceImpl implements ProjectWorkTypeAndStyleFacadeService {

    @Autowired
    private ProjectWorkStyleService projectWorkStyleService;

    @Autowired
    private ProjectWorkTypeService projectWorkTypeService;

    /**
     * 获取所有的项目工作类型和工作方式
     *
     * @return 所有的项目工作方式和工作类型
     */
    @Override
    public ProjectWorkTypeAndStyleVo getAll() {
        List<ProjectWorkStyle> projectWorkStyles = projectWorkStyleService.list();
        List<ProjectWorkType> projectWorkTypes = projectWorkTypeService.list();
        return new ProjectWorkTypeAndStyleVo(projectWorkStyles, projectWorkTypes);
    }
}
