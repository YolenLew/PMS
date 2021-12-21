package com.kkb.project.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.portal.domain.Client;
import com.kkb.project.portal.domain.Project;
import com.kkb.project.portal.domain.dto.ProjectAdminDto;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author __SAD_DOG__
 * 项目大厅项目相关Servicec
 */
public interface ProjectPortalService extends IService<Project> {

    /**
     * 根据id集合获取项目列表
     * @param ids id集合
     * @return 项目列表
     */
    List<Project> getByIds(Collection<Long> ids);

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
     */
    void incrementSignupParticipantNum(long projectId);

    /**
     * 给 project 表的某一行的 signup_leader_num 字段值加一
     * @param projectId 需要update的project的id
     */
    void incrementSignupLeaderNum(long projectId);

    /**
     * 通过id集合查询出对应状态项目
     * @param pageNum 页码
     * @param pageSize 每显示页数量
     * @param idList 项目id集合
     * @param status 项目状态
     * @return 符合条件的项目集合
     */
    List<Project> findProjectByStatusAndIdList(Integer pageNum, Integer pageSize, List<Long> idList, Integer status);

    /**
     * 新建项目
     * @param projectAdminDto 新增项目的参数
     */
    void   addNewProject(ProjectAdminDto projectAdminDto);

    /**
     * 根据项目ID删除项目
     * @param projectId 项目ID
     */
    void deleteProjectById(long projectId);
}
