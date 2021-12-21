package com.kkb.project.admin.service;

import com.kkb.project.common.api.CommonPage;
import com.kkb.project.admin.domain.Project;
import com.kkb.project.admin.domain.dto.QueryProjectDto;
import com.kkb.project.admin.domain.vo.ProjectVo;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author __SAD_DOG__
 * @date 2021-04-29
 * 构建游客浏览项目列表 ProjectVo 的service
 */
public interface ProjectVoFacadeService {

    /**
     * 通过project id 获取对应的 ProjectVo对象
     * @param projectId 项目Id
     * @return ProjectVo对象
     */
    ProjectVo getByProjectId(Long projectId);

    /**
     * 通过project id 集合 获取对应的 ProjectVo对象列表
     * @param ids 项目Id 集合
     * @return ProjectVo对象列表
     */
    List<ProjectVo> getByProjectIds(Collection<Long> ids);

    /**
     * 通过project id 集合 获取对应的 ProjectVo对象列Map; key 是project id; value是ProjectVo
     * @param ids 项目Id 集合
     * @return ProjectVo对象列表
     */
    Map<Long, ProjectVo> getProjectIdMapByProjectIds(Collection<Long> ids);

    /**
     * 通过 query参数查询满足条件的 ProjectVo页面
     * @param queryProjectDto 查询项目的查询参数
     * @return 返回项目的分页集合
     */
    CommonPage<ProjectVo> queryProjectList(QueryProjectDto queryProjectDto);

    /**
     * 通过Project集合查询ProjectVo集合
     * @param projects project集合
     * @return 相关的 ProjectVo集合
     */
    List<ProjectVo> getProjectVosByProjects(Collection<Project> projects);
}
