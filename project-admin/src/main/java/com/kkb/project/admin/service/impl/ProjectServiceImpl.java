package com.kkb.project.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.admin.domain.constant.DeletedStatus;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.admin.dao.ClientDao;
import com.kkb.project.admin.dao.ProjectDao;
import com.kkb.project.admin.domain.Client;
import com.kkb.project.admin.domain.Project;
import com.kkb.project.admin.domain.dto.ProjectAdminDto;
import com.kkb.project.admin.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author __SAD_DOG__
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectDao, Project> implements ProjectService {

    @Deprecated
    private ClientDao clientDao;

    @Autowired
    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
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
    public boolean incrementSignupParticipantNum(long projectId) {
        UpdateWrapper<Project> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", projectId);
        updateWrapper.setSql("signup_participant_num = signup_participant_num + 1");
        return update(updateWrapper);
    }

    /**
     * 给 project 表的某一行的 signup_leader_num 字段值加一
     * @param projectId 需要update的project的id
     * @return 是否修改成功
     */
    @Override
    public boolean incrementSignupLeaderNum(long projectId) {
        UpdateWrapper<Project> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", projectId);
        updateWrapper.setSql("signup_leader_num = signup_leader_num + 1");
        return update(updateWrapper);
    }

    /**
     * 新建项目
     * @author  ynb
     * @param projectAdminDto 新增项目的参数
     */
    @Override
    public void addNewProject(ProjectAdminDto projectAdminDto) {
        QueryWrapper<Client> query = Wrappers.query();
        query.eq("name",projectAdminDto.getClientName());

        Client oldClient = clientDao.selectOne(query);
        Client newClient = new Client(projectAdminDto.getCreateUser(),projectAdminDto.getClientName(),"");
        if (oldClient == null){
            clientDao.insert(newClient);
        }
        Project project = new Project(
                projectAdminDto.getCreateUser(),
                projectAdminDto.getTitle(),
                oldClient == null ? newClient.getId() : oldClient.getId(),
                projectAdminDto.getSignupDeadline(),
                projectAdminDto.getDemandDescription(),
                projectAdminDto.getWorkStyle(),
                projectAdminDto.getPredictedDuration(),
                projectAdminDto.getStartDatetime(),
                projectAdminDto.getType(),
                projectAdminDto.getClientName()
        );
        project.setUpdateUser(projectAdminDto.getCreateUser());
        project.setTags(projectAdminDto.getTags());
        boolean flag = this.saveOrUpdate(project);
        if (!flag){
            Asserts.fail("新建项目失败");
        }
    }

    /**
     * 修改项目
     * @author 张棋
     * @param projectAdminDto 修改项目的参数
     */
    @Override
    public void updateProject(ProjectAdminDto projectAdminDto,long projectId) {
        Project project = baseMapper.selectById(projectId);
        project.setWorkStyleId(projectAdminDto.getWorkStyle());
        project.setDemandDescription(projectAdminDto.getDemandDescription());
        project.setSignupDeadline(projectAdminDto.getSignupDeadline());
        project.setTitle(projectAdminDto.getTitle());
        project.setTags(projectAdminDto.getTags());
        project.setTypeId(projectAdminDto.getType());

        boolean flag = this.saveOrUpdate(project);
        if (!flag){
            Asserts.fail("修改项目失败");
        }
    }

    /**
     * 根据项目ID删除项目
     * @param projectId 项目ID
     */
    @Override
    public void deleteProjectById(long projectId) {
        boolean update = update(new UpdateWrapper<Project>().set("is_deleted", DeletedStatus.IS_DELETED.value()).eq("id", projectId));
        if (!update) {
            Asserts.fail("删除项目失败");
        }
    }
}
