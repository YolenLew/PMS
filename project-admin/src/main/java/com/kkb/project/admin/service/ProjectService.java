package com.kkb.project.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.admin.domain.Client;
import com.kkb.project.admin.domain.Project;
import com.kkb.project.admin.domain.Project;
import com.kkb.project.admin.domain.dto.ProjectAdminDto;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author __SAD_DOG__
 * 项目大厅项目相关Servicec
 */
public interface ProjectService extends IService<Project> {
    /**
     * 根据分页信息返回Project的列表
     * @param pageNum 本页的记录数
     * @param offset 查询的offset
     * @param projectStatus 查询status为projectStatus的项目, < 0 表示不需要该约束条件
     * @param type 查询类型为type的项目, < 0 表示不需要该约束条件
     * @return 返回查询到的Project对象的列表
     */
    List<Project> getPortalProjectList(int pageNum, int offset, int projectStatus, int type);

    /**
     * 给 project 表的某一行的 signup_participant_num 字段值加一
     * @param projectId 需要update的project的id
     * @return 是否修改成功
     */
    boolean incrementSignupParticipantNum(long projectId);

    /**
     * 给 project 表的某一行的 signup_leader_num 字段值加一
     * @param projectId 需要update的project的id
     * @return 是否修改成功
     */
    boolean incrementSignupLeaderNum(long projectId);

    /**
     * 新建项目
     * @author  ynb
     * @param projectAdminDto 新增项目的参数
     */
    void   addNewProject(ProjectAdminDto projectAdminDto);

    /**
     * 修改项目
     * @author 张棋
     * @param projectAdminDto 修改项目的参数
     * @param projectId 要修改的项目的id
     */
    void updateProject(ProjectAdminDto projectAdminDto,long projectId);

    /**
     * 根据项目ID删除项目
     * @param projectId 项目ID
     */
    void deleteProjectById(long projectId);
}
