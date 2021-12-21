package com.kkb.project.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.common.api.CommonPage;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.portal.domain.User;
import com.kkb.project.portal.domain.Client;
import com.kkb.project.portal.domain.LeaderComment;
import com.kkb.project.portal.domain.LeaderGuidance;
import com.kkb.project.portal.domain.Project;
import com.kkb.project.portal.domain.constant.LeaderSignUpStatus;
import com.kkb.project.portal.domain.constant.ParticipantSignUpStatus;
import com.kkb.project.portal.domain.constant.ProjectStatus;
import com.kkb.project.portal.domain.constant.RoleType;
import com.kkb.project.portal.domain.constraint.HasProjectId;
import com.kkb.project.portal.domain.vo.ProjectManageLeaderVo;
import com.kkb.project.portal.domain.vo.ProjectManageParticipantVo;
import com.kkb.project.portal.domain.vo.ProjectManageReviewingVo;
import com.kkb.project.portal.service.util.VoWrapper;
import com.kkb.project.portal.service.*;
import com.kkb.project.portal.service.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author __SAD_DOG__
 * @date 2021-04-27
 * 用于项目管理-主要流程页面业务的Service
 * 调用多个serviceImpl实现连表
 */
@Service
public class ProjectManageFacadeImpl implements ProjectManageFacade {

    @Autowired
    private LeaderService leaderService;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private LeaderGuidanceService leaderGuidanceService;

    @Autowired
    private ParticipantPartakeService participantPartakeService;

    @Autowired
    private ProjectPortalService projectPortalService;

    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;


    @Override
    @SuppressWarnings("unchecked")
    public CommonPage<?> getProjectManageVoList(Long pageNum, Long pageSize, Long userId, RoleType roleType, ProjectStatus projectStatus) {
        IService<? extends HasProjectId> service = null;
        @SuppressWarnings("rawtypes")
        QueryWrapper queryWrapper = new QueryWrapper();

        QueryWrapper<LeaderComment> wrapper = new QueryWrapper<>();
        switch (projectStatus) {
            // 审核中项目,
            case REVIEWING:
                switch (roleType) {
                    case LEADER:
                        service = leaderService;
                        queryWrapper.eq("leader_id", userId);
                        queryWrapper.eq("status", LeaderSignUpStatus.SIGNED_UP.value());
                        break;
                    case PARTICIPANT:
                        service = participantService;
                        queryWrapper.eq("participant_id", userId);
                        queryWrapper.eq("status", ParticipantSignUpStatus.SIGNED_UP.value());
                        break;
                    default: Asserts.fail("服务器内部错误");
                }
                break;
            case PROCESSING:
            case COMPLETED:
            case CANCELLED:
                switch (roleType) {
                    case LEADER:
                        service = leaderGuidanceService;
                        queryWrapper.eq("leader_id", userId);
                        break;
                    case PARTICIPANT:
                        service = participantPartakeService;
                        queryWrapper.eq("participant_id", userId);
                        break;
                    default: Asserts.fail("服务器内部错误");
                }
                queryWrapper.eq("project_status", projectStatus.value());
                break;
            default: Asserts.fail("服务器内部错误");
        }
        @SuppressWarnings("rawtypes")
        IPage page = new Page(pageNum, pageSize);
        @SuppressWarnings("rawtypes")
        IPage resPage = service.page(page, queryWrapper);
        if (resPage.getTotal() == 0) {
            Asserts.fail("查询项目列表错误");
        }
        List<HasProjectId> records = resPage.getRecords();
        List<Long> projectIds = records.stream().map(HasProjectId::getProjectId).collect(Collectors.toList());

        List<Project> projects = projectPortalService.getByIds(projectIds);
        List<Long> clientIds = projects.stream().map(Project::getClientId).collect(Collectors.toList());
        Map<Long, Client> clients = clientService.getByClientIds(clientIds);
        if (projectStatus == ProjectStatus.REVIEWING) {
            List<ProjectManageReviewingVo> res = new ArrayList<>(projects.size());
            projects.forEach(it -> {
                res.add(VoWrapper.toProjectManageReviewingVo(it, clients.get(it.getClientId())));
            });
            return PageUtil.wrapToCommonPage(res, resPage);
        }
        // projectStatus != REVIEWING 表示不是审核中的项目
        if (roleType == RoleType.LEADER) {
            List<ProjectManageLeaderVo> res = new ArrayList<>(projects.size());
            projects.forEach(it -> {
                res.add(VoWrapper.toProjectManageLeaderVo(it, clients.get(it.getClientId())));
            });
            return PageUtil.wrapToCommonPage(res, resPage);
        }
        // roleType = PARTICIPANT
        Map<Long, LeaderGuidance> leaderGuidanceMap = leaderGuidanceService.findOneOfLeaderGuidanceByProjectIds(projectIds);
        List<Long> leaderIds = leaderGuidanceMap.values().stream().map(LeaderGuidance::getLeaderId).collect(Collectors.toList());
        Map<Long, User> leaders = userService.findUsersByIds(leaderIds);
        List<ProjectManageParticipantVo> res = new ArrayList<>(projects.size());
        projects.forEach(it -> res.add(VoWrapper.toProjectManageParticipantVo(
                it, clients.get(it.getClientId()), leaders.get(leaderGuidanceMap.get(it.getId()).getLeaderId()))
        ));
        return PageUtil.wrapToCommonPage(res, resPage);
    }
}
