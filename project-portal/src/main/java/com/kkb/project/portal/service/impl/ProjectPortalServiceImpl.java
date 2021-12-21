package com.kkb.project.portal.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.portal.dao.ClientDao;
import com.kkb.project.portal.dao.ProjectDao;
import com.kkb.project.portal.domain.Client;
import com.kkb.project.portal.domain.Project;
import com.kkb.project.portal.domain.UserWork;
import com.kkb.project.portal.domain.constant.ProjectStatus;
import com.kkb.project.portal.domain.constraint.HasProjectId;
import com.kkb.project.portal.domain.dto.ProjectAdminDto;
import com.kkb.project.portal.service.ProjectPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kkb.project.common.exception.Asserts;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author __SAD_DOG__
 */
@Service
public class ProjectPortalServiceImpl extends ServiceImpl<ProjectDao, Project> implements ProjectPortalService {

    /**
     * 根据id集合获取项目列表
     *
     * @param ids id集合
     * @return 项目列表
     */
    @Override
    public List<Project> getByIds(Collection<Long> ids) {
        if (!(ids instanceof Set)) {
            ids = CollectionUtil.newHashSet(ids);
        }
        List<Project> projects = this.list(new QueryWrapper<Project>().eq("id", ids));
        if (ids.size() > projects.size()) {
            // 有 id 没有查询到
            StringBuilder failIds = new StringBuilder().append(" (");
            List<Long> selectedIds = projects.stream().map(Project::getId).collect(Collectors.toList());
            ids.forEach(id -> {
                if (!selectedIds.contains(id)) {
                    failIds.append(id).append(", ");
                }
            });
            failIds.append(") ");
            Asserts.fail("未查询到id 为: " + failIds.toString() + "的项目");
        }
        return projects;
    }

    /**
     *
     * @param pageNum 请求的页面的条目数
     * @param offset 从第offset条开始
     * @param projectStatus 如果projectStatus小于0 则表示不加projectStatus查询条件
     * @param type 如果type 小于0 则表示不加 type 查询条件
     * @return 返回Project的List
     */
    @Override
    public List<Project> getPortalProjectList(int pageNum, int offset, int projectStatus, int type) {
        int currentPage = offset / pageNum;
        IPage<Project> iPage = new Page<>(currentPage, pageNum);
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        if(type >= 0) {
            // 需要在查询的时候加入type作为条件
            queryWrapper.eq("type", type);
        }
        if (projectStatus >= 0) {
            queryWrapper.eq("status", projectStatus);
        }
        IPage<Project> resPage = baseMapper.selectPage(iPage, queryWrapper);
        List<Project> result = resPage.getRecords();
        if (result.size() <= 0) {
            Asserts.fail("未查询到项目列表");
        }
        return result;
    }

    /**
     * 给 project 表的某一行的 signup_participant_num 字段值加一
     * @param projectId 需要update的project的id
     * @return 是否修改成功
     */
    @Override
    public void incrementSignupParticipantNum(long projectId) {
        UpdateWrapper<Project> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", projectId);
        updateWrapper.setSql("signup_participant_num = signup_participant_num + 1");
        boolean res = update(updateWrapper);
        if (!res) {
            Asserts.fail("增加项目学员报名人数失败");
        }
    }

    /**
     * 给 project 表的某一行的 signup_leader_num 字段值加一
     * @param projectId 需要update的project的id
     * @return 是否修改成功
     */
    @Override
    public void incrementSignupLeaderNum(long projectId) {
        UpdateWrapper<Project> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", projectId);
        updateWrapper.setSql("signup_leader_num = signup_leader_num + 1");
        boolean res = update(updateWrapper);
        if (!res) {
            Asserts.fail("增加项目报名导师数失败");
        }
    }

    /**
     * 通过id集合查询出对应状态项目
     *
     * @param pageNum
     * @param pageSize
     * @param idList
     * @param status
     * @return
     */
    @Override
    public List<Project> findProjectByStatusAndIdList(Integer pageNum, Integer pageSize, List<Long> idList, Integer status) {
        IPage<Project> iPage = new Page<>(pageNum, pageSize);
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",idList);
        queryWrapper.eq("status", status);
        IPage<Project> resPage = baseMapper.selectPage(iPage, queryWrapper);
        List<Project> result = resPage.getRecords();
        if (result.size() <= 0) {
            Asserts.fail("未查询到项目列表");
        }
        return result;
    }


    /**
     * 新建项目
     *
     * @param projectAdminDto 新增项目的参数
     */
    @Override
    public void addNewProject(ProjectAdminDto projectAdminDto) {
        Project project = new Project();
        project.setWorkStyleId(projectAdminDto.getWorkStyle());
        project.setDemandDescription(projectAdminDto.getDemandDescription());
        project.setSignupDeadline(projectAdminDto.getSignupDeadline());
        project.setTitle(projectAdminDto.getTitle());
        project.setTags(projectAdminDto.getTags());
        project.setTypeId(projectAdminDto.getType());

        boolean flag = this.saveOrUpdate(project);
        if (!flag){
            Asserts.fail("新建项目失败");
        }
    }

    /**
     * 根据项目ID删除项目
     * @param projectId 项目ID
     */
    @Override
    public void deleteProjectById(long projectId) {
        boolean update = update(new UpdateWrapper<Project>().set("is_deleted", 1).eq("id", projectId));
        if (!update) {
            Asserts.fail("删除项目失败");
        }
    }
}
