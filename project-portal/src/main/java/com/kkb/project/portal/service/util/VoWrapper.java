package com.kkb.project.portal.service.util;

import com.kkb.project.common.exception.ConstraintException;
import com.kkb.project.portal.domain.*;
import com.kkb.project.portal.domain.constraint.util.ConstraintKeeper;
import com.kkb.project.portal.domain.vo.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author __SAD_DOG__
 * @date 2021-04-27
 * 提供util方法, 用于将对象entity对象包装为VO对象,
 * 声明为enum是为了防止实例化该类的对象, 不过也会导致对象无法序列化
 * 但是序列化估计不会是问题
 * 将其声明为继承自RuntimeException是因为Java的收检异常并非一个优雅的设计
 */
public enum VoWrapper {
    // util enum 没有枚举对象
    ;
    public static List<ProjectVo> toProjectVos(
            Collection<Project> projects, Map<Long, Client> clientMap, Map<Long, ProjectWorkType> projectWorkTypes, Map<Long, ProjectWorkStyle> projectWorkStyles
    ) throws ConstraintException {
        return projects.stream().map(
                it -> toProjectVo(it, clientMap.get(it.getClientId()), projectWorkTypes.get(it.getTypeId()), projectWorkStyles.get(it.getWorkStyleId()))
        ).collect(Collectors.toList());
    }

    /**
     * 将Project 和 相关的Client 包装为 ProjectVO
     * @param project Project entity 对象
     * @param client Client entity 对象
     * @return 返回包装的 ProjectVo对象
     * @throws ConstraintException 若两对象没有通过外键联系起来，则会抛出运行时异常, 虽然是RuntimeException, 但是显示抛出以强调
     */
    public static ProjectVo toProjectVo(Project project, Client client, ProjectWorkType projectWorkType, ProjectWorkStyle projectWorkStyle) throws ConstraintException {
        if (!ConstraintKeeper.fkProjectToClientById(project, client)) {
            throw new ConstraintException("包装ProjectVO出错, 传入的Project和Client的外键约束失效");
        }
        return new ProjectVo(project, client, projectWorkType, projectWorkStyle);
    }

    public static List<ProjectManageReviewingVo> toProjectManageReviewingVos(Collection<Project> projects, Map<Long, Client> clientMap) throws ConstraintException {
        return projects.stream().map(it -> toProjectManageReviewingVo(it, clientMap.get(it.getId()))).collect(Collectors.toList());
    }


    /**
     * 将Project 和对应的Client 包装为ProjectManageReviewingVo对象
     * @param p Project对象
     * @param c Client对象
     * @return 相对应的ProjectManageReviewingVo对象
     * @throws ConstraintException 若两对象没有通过外键联系起来，则会抛出运行时异常, 虽然是RuntimeException, 但是显示抛出以强调
     */
    public static ProjectManageReviewingVo toProjectManageReviewingVo(Project p, Client c) throws ConstraintException {
        if (!ConstraintKeeper.fkProjectToClientById(p, c)) {
            throw new ConstraintException("包装ProjectManageReviewingVo出错, 传入的Project和Client的外键约束失效");
        }
        return new ProjectManageReviewingVo(p.getTitle(), p.getTypeId(), p.getClientName());
    }

    /**
     * 将Project 和对应的Client 包装为ProjectManageLeaderVo对象
     * @param p Project对象
     * @param c Client对象
     * @return 相对应的ProjectManageLeaderVo对象
     * @throws ConstraintException 若两对象没有通过外键联系起来，则会抛出运行时异常, 虽然是RuntimeException, 但是显示抛出以强调
     */
    public static ProjectManageLeaderVo toProjectManageLeaderVo(Project p, Client c) throws ConstraintException {
        if (!ConstraintKeeper.fkProjectToClientById(p, c)) {
            throw new ConstraintException("包装ProjectManageLeaderVo出错, 传入的Project和Client的外键约束失效");
        }
        return new ProjectManageLeaderVo(p.getTitle(), p.getTypeId(), c.getName(), p.getPartakeParticipantNum());
    }

    /**
     * 将Project 和对应的Client 包装为ProjectManageLeaderVo对象
     * @param p Project对象
     * @param c Client对象
     * @param u User对象, 是该项目的导师(之一)
     * @return 相对应的ProjectManageLeaderVo对象
     * @throws ConstraintException 若两对象没有通过外键联系起来，则会抛出运行时异常, 虽然是RuntimeException, 但是显示抛出以强调
     */
    public static ProjectManageParticipantVo toProjectManageParticipantVo(Project p, Client c, User u) {
        if (!ConstraintKeeper.fkProjectToClientById(p, c)) {
            throw new ConstraintException("包装ProjectManageParticipantVo出错, 传入的Project和Client的外键约束失效");
        }
        return new ProjectManageParticipantVo(p.getTitle(), p.getTypeId(), c.getName(), u.getName());
    }

    /**
     * 将User 和对应的UserSkill 包装成UserVo对象
     * @param u User对象
     * @param userSkillList UserSkill对象列表
     * @return 相对应的UserVo对象
     */
    public static UserVo toAcceptUserVo(User u, List<UserSkill> userSkillList) throws ConstraintException{
        UserVo userVo = new UserVo();
        userVo.setUser(u);
        userVo.setUserSkillList(userSkillList);
        return userVo;
    }

    /**
     * 将User 和对应的UserSkill 包装成UserVo对象
     * @param users User对象集合
     * @param userSkillListMap UserSkill对象列表集合
     * @return 相对应的UserVo对象
     */
    public static List<UserVo> toAcceptUserVos(Collection<User> users, Map<Long, List<UserSkill>> userSkillListMap){
        List<UserVo> userVoList = new ArrayList<>(users.size());
        users.forEach(it -> userVoList.add(toAcceptUserVo(it, userSkillListMap.get(it.getId()))));
        return userVoList;
    }


    /**
     * 将SuccessCase对象 和 projectVo对象 包装为 SuccessVo 对象
     *
     * @param successCase SuccessCase对象
     * @param projectRes projectVo对象
     * @return 返回SuccessVo 对象
     */
    public static SuccessCaseVo toSuccessCaseVo(SuccessCase successCase, ProjectVo projectRes) {
        SuccessCaseVo successCaseVo = new SuccessCaseVo();
        successCaseVo.setSuccessCase(successCase);
        successCaseVo.setProjectRes(projectRes);
        return successCaseVo;
    }

    /**
     * 将 SuccessCase对象 和 ProjectVo对象封装 为 SuccessCaseVo集合对象
     *
     * @param successCases SuccessCase 对象
     * @param projectVoMap ProjectVo 对象
     * @return 返回 SuccessCaseVo 集合对象
     */
    public static List<SuccessCaseVo> toListSuccessCaseVo(Collection<SuccessCase> successCases,
                                                          Map<Long, ProjectVo> projectVoMap) {
        List<SuccessCaseVo> list = new ArrayList<>();
        successCases.forEach(
                it -> list.add(toSuccessCaseVo(it, projectVoMap.get(it.getProjectId())))
        );
        return list;
    }

}
