package com.kkb.project.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kkb.project.admin.service.*;
import com.kkb.project.common.api.CommonPage;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.admin.domain.Client;
import com.kkb.project.admin.domain.Project;
import com.kkb.project.admin.domain.ProjectWorkStyle;
import com.kkb.project.admin.domain.ProjectWorkType;
import com.kkb.project.admin.domain.constant.ProjectTimeRange;
import com.kkb.project.admin.domain.dto.QueryProjectDto;
import com.kkb.project.admin.domain.vo.ProjectVo;
import com.kkb.project.admin.service.util.PageUtil;
import com.kkb.project.admin.service.util.VoWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author __SAD_DOG__
 * @date 2021-04-29
 * 构建游客浏览项目列表 ProjectVo 的service
 */
@Service
public class ProjectVoFacadeServiceImpl implements ProjectVoFacadeService {

    @Autowired
    private ProjectService projectPortalService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProjectWorkTypeService projectWorkTypeService;

    @Autowired
    private ProjectWorkStyleService projectWorkStyleService;

    /**
     * 通过project id 获取对应的 ProjectVo对象
     *
     * @param projectId 项目Id
     * @return ProjectVo对象
     */
    @Override
    public ProjectVo getByProjectId(Long projectId) {
        Project project = projectPortalService.getById(projectId);
        if (project == null) {
            Asserts.fail("未查询到id 为: " + projectId + " 的项目");
        }
        Client client = clientService.getById(project.getClientId());
        if (client == null) {
            Asserts.fail("未查询到id 为: " + project.getClientId() + " 的委托方");
        }
        ProjectWorkStyle workStyle = projectWorkStyleService.getById(project.getWorkStyleId());
        if (workStyle == null) {
            Asserts.fail("未查询到id 为: " + project.getWorkStyleId() + " 的工作方式");
        }
        ProjectWorkType workType = projectWorkTypeService.getById(project.getTypeId());
        if (workType == null) {
            Asserts.fail("未查询到id 为: " + project.getTypeId() + " 的工作类型");
        }
        return VoWrapper.toProjectVo(project, client, workType, workStyle);
    }

    /**
     * 通过project id 集合 获取对应的 ProjectVo对象列表
     *
     * @param ids 项目Id 集合
     * @return ProjectVo对象列表
     */
    @Override
    public List<ProjectVo> getByProjectIds(Collection<Long> ids) {
        if (!(ids instanceof Set)) {
            ids = CollectionUtil.newHashSet(ids);
        }
        List<Project> projects = projectPortalService.listByIds(ids);
        if (ids.size() > projects.size()) {
            // 有 id 没有查询到
            StringBuilder failIds = new StringBuilder().append(" ");
            List<Long> selectedIds = projects.stream().map(Project::getId).collect(Collectors.toList());
            ids.forEach(id -> {
                if (!selectedIds.contains(id)) {
                    failIds.append(id).append(", ");
                }
            });
            Asserts.fail("未查询到id 为: " + failIds.toString() + " 的项目");
        }
        return this.getProjectVosByProjects(projects);
    }

    /**
     * 通过project id 集合 获取对应的 ProjectVo对象列Map; key 是project id; value是ProjectVo
     *
     * @param ids 项目Id 集合
     * @return ProjectVo对象列表
     */
    @Override
    public Map<Long, ProjectVo> getProjectIdMapByProjectIds(Collection<Long> ids) {
        List<ProjectVo> projectVoList = this.getByProjectIds(ids);
        Map<Long, ProjectVo> res = new HashMap<>(projectVoList.size());
        projectVoList.forEach(it -> res.put(it.getProject().getId(), it));
        return res;
    }

    /**
     * @param queryProjectDto 查询项目的查询参数
     * @return 返回项目的分页集合
     */
    @Override
    public CommonPage<ProjectVo> queryProjectList(QueryProjectDto queryProjectDto) {
        @NotNull Long current = queryProjectDto.getPageNum();
        @NotNull Long size = queryProjectDto.getPageSize();
        Page<Project> p = new Page<>(current, size);
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        if (queryProjectDto.getProjectWorkTypeId() >= 0) {
            queryWrapper.eq("typeId", queryProjectDto.getProjectWorkTypeId());
        }
        if (queryProjectDto.getProjectStatus() >= 0) {
            queryWrapper.eq("status", queryProjectDto.getProjectStatus());
        }
        if (queryProjectDto.getTimeRangeFilter() >= 0) {
            ProjectTimeRange tr = ProjectTimeRange.ofValue(queryProjectDto.getTimeRangeFilter());
            Date date = null;
            switch (tr) {
                case WEEK:
                    date = DateUtil.lastWeek();
                    break;
                case MONTH:
                    date = DateUtil.lastMonth();
                    break;
                default:
                    Asserts.fail("服务器内部错误");
            }
            queryWrapper.gt("publish_datetime", date);
        }

        Page<Project> resPage = projectPortalService.page(p, queryWrapper);
        List<ProjectVo> projectVos = this.getProjectVosByProjects(resPage.getRecords());
        return PageUtil.wrapToCommonPage(projectVos, resPage);
    }

    /**
     * 通过Project集合查询ProjectVo集合
     *
     * @param projects project集合
     * @return 相关的 ProjectVo集合
     */
    @Override
    public List<ProjectVo> getProjectVosByProjects(Collection<Project> projects) {
        List<Long> clientIds = new ArrayList<>(projects.size());
        List<Long> workTypeIds = new ArrayList<>(projects.size());
        List<Long> workStyleIds = new ArrayList<>(projects.size());
        projects.forEach(it -> {
            clientIds.add(it.getClientId());
            workTypeIds.add(it.getTypeId());
            workStyleIds.add(it.getWorkStyleId());
        });
        Map<Long, Client> clients = clientService.getByClientIds(clientIds);
        Map<Long, ProjectWorkType> workTypes = projectWorkTypeService.getByIds(workTypeIds);
        Map<Long, ProjectWorkStyle> workStyles = projectWorkStyleService.getByIds(workStyleIds);
        return VoWrapper.toProjectVos(projects, clients, workTypes, workStyles);
    }
}
