package com.kkb.project.portal.controller;

import com.kkb.project.common.api.CommonPage;
import com.kkb.project.common.api.CommonResult;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.portal.domain.*;
import com.kkb.project.portal.domain.constant.IsAnonymous;
import com.kkb.project.portal.domain.constant.RoleType;
import com.kkb.project.portal.domain.dto.QueryProjectDto;
import com.kkb.project.portal.domain.vo.*;
import com.kkb.project.portal.service.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author liusheng
 * @file ProjectController.java
 * @email 747539062@qq.com
 * @Date 2021/4/20 18:08
 * @ModifiedBy qzj
 **/
@RestController
@RequestMapping("/project-portal")
@Api(value = "projectController",tags = "项目大厅API")
public class ProjectController {

    /**
     * 导师类型
     */
    private static final Integer LEADER_TYPE = RoleType.LEADER.value();

    /**
     * 学生类型
     */
    private static final Integer PARTICIPANT_TYPE = RoleType.PARTICIPANT.value();

    @Autowired
    private ProjectPortalService portalService;

    @Autowired
    private LeaderService leaderService;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private ParticipantPartakeService participantPartakeService;

    @Autowired
    private LeaderCommentService leaderCommentService;

    @Autowired
    private ParticipantCommentService participantCommentService;

    @Autowired
    private ProjectVoFacadeService projectVoFacadeService;

    @Autowired
    private ParticipantAcceptFacadeService participantAcceptFacadeService;

    @Autowired
    private ProjectWorkTypeService projectWorkTypeService;

    @Autowired
    private ProjectWorkStyleService projectWorkStyleService;

    @Autowired
    private ProjectWorkTypeAndStyleFacadeService projectWorkTypeAndStyleFacadeService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ApiOperation(value = "查询项目列表", notes = "返回根据条件查询的项目列表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "返回查询成功列表", response = CommonResult.class),
            @ApiResponse(code = 404, message = "参数检验失败", response = CommonResult.class),
            @ApiResponse(code = 401, message = "暂未登录或token已经过期", response = CommonResult.class),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "queryProjectDto", value = "查询公共项目列表参数", required = true, dataTypeClass = QueryProjectDto.class)
    })
    public CommonResult<CommonPage<ProjectVo>> getAllProjects(@NotNull QueryProjectDto queryProjectDto){
        CommonPage<ProjectVo> voList = projectVoFacadeService.queryProjectList(queryProjectDto);
        return CommonResult.success(voList, "查询项目列表成功");
    }

    /**
     * 学生/导师报名项目
     * @param projectId 项目id
     * @param roleType 角色类型
     * @param roleId  角色id
     * @return 报名是否成功
     */
    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    @ApiOperation(value = "角色(导师或学生)报名项目", notes = "返回报名结果")
    @ApiResponses({
            @ApiResponse(code = 200, message = "报名成功", response = CommonResult.class),
            @ApiResponse(code = 404, message = "参数检验失败", response = CommonResult.class),
            @ApiResponse(code = 401, message = "暂未登录或token已经过期", response = CommonResult.class),
            @ApiResponse(code = 500, message = "已报名", response = CommonResult.class),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "projectId", value = "项目id", required = true),
            @ApiImplicitParam(paramType = "query", name = "roleType", value = "角色成员(1导师 ，2学生)", required = true),
            @ApiImplicitParam(paramType = "query", name = "roleId", value = "角色(导师或学生)id", required = true)
    })
    public CommonResult<?> singUp(Long projectId,int roleType , Long roleId) {
        if (LEADER_TYPE == roleType){
            leaderService.leaderSignUp(roleId, projectId);
            portalService.incrementSignupLeaderNum(projectId);
            return CommonResult.success(null,"导师报名成功");
        }
        if(PARTICIPANT_TYPE == roleType){
            participantService.participantSignUp(roleId, projectId);
            portalService.incrementSignupParticipantNum(projectId);
            return CommonResult.success(null,"学生报名成功");
        }
        return CommonResult.failed("报名失败");
    }

    /**
     * 分页查询已选择学员列表
     * @param pageNum 当前页
     * @param pageSize  每页数量
     * @return 查询结果
     */
    @GetMapping(value = "/signup-list")
    @ApiOperation(value = "查询已报名学员列表")
    public CommonResult<CommonPage<UserVo>> getAllParticipantSignUps(@RequestParam(name = "pageNum", defaultValue = "10") Integer pageNum,
                                                                     @RequestParam(name = "pageSize", defaultValue = "0") Integer pageSize,
                                                                     @Validated Long projectId){
        CommonPage<UserVo> allSignUp = participantAcceptFacadeService.findAllSignUp(projectId, pageNum, pageSize);
        return CommonResult.success(allSignUp, "查询报名学员列表成功");
    }

    /**
     * 分页查询已报名学员列表
     * @param pageNum 当前页
     * @param pageSize  每页数量
     * @return 查询结果
     */
    @GetMapping(value = "/partake-list")
    @ApiOperation(value = "查询已选择学员列表")
    public CommonResult<CommonPage<UserVo>> getAllPartakes(@RequestParam(name = "pageNum", defaultValue = "10") Integer pageNum,
                                                           @RequestParam(name = "pageSize", defaultValue = "0") Integer pageSize,
                                                           @Validated Long projectId){
        CommonPage<UserVo> allPartake = participantAcceptFacadeService.findAllPartake(projectId, pageNum, pageSize);
        return CommonResult.success(allPartake, "查询参加学员列表成功");
    }

    /**
     * 导师接受学员的报名
     * @param projectId 项目id
     * @param participantIds 选择学员列表
     * @param leaderId 导师id
     * @return 返回接受结果
     */
    @PostMapping("/accept-signup")
    @ApiOperation(value = "导师接受学员的报名", notes = "返回接受结果")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "projectId", value = "项目id", required = true),
            @ApiImplicitParam(paramType = "query", name = "participantIds", value = "选择学员列表", required = true),
            @ApiImplicitParam(paramType = "query", name = "leaderId", value = "导师id", required = true)
    })
    public CommonResult<?> participantsAccept(Long projectId , List<Long> participantIds, Long leaderId){
        // 向 participant partake 表写入新的数据
        participantPartakeService.participantsAccept(projectId, participantIds, leaderId);
        // 修改 participant signup 表中报名状态
        participantService.acceptSingUp(projectId, participantIds);
        return CommonResult.success(null,"添加报名学员成功");
    }

    @PostMapping("/comment-leader")
    @ApiOperation(value = "学生评论导师", notes = "返回评论是否成功")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "leaderComment", dataTypeClass = LeaderCommentVo.class)
    )
    public CommonResult<?> commentLeader(@NotNull LeaderCommentVo leaderComment) {
        LeaderComment comment = new LeaderComment(
                leaderComment.getLeaderId(),
                leaderComment.getProjectId(),
                leaderComment.getComment(),
                leaderComment.getCommentFrom(),
                leaderComment.getIsAnonymous() ? IsAnonymous.IS_ANONYMOUS.value() : IsAnonymous.NOT_ANONYMOUS.value(),
                leaderComment.getProfessionalAbility(),
                leaderComment.getOrganizationalAbility(),
                leaderComment.getCommunicationAbility()
        );
        leaderCommentService.insertComment(comment);
        return CommonResult.success(null, "评论成功");
    }

    @PostMapping("/comment-participant")
    @ApiOperation(value = "导师评论学生", notes = "返回评论是否成功")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "participantComment", dataTypeClass = ParticipantCommentVo.class)
    )
    public CommonResult<?> commentParticipant(@NotNull ParticipantCommentVo participantComment
    ) {
        ParticipantComment comment = new ParticipantComment(
                participantComment.getParticipantId(),
                participantComment.getProjectId(),
                participantComment.getComment(),
                participantComment.getCommentFrom(),
                participantComment.getIsAnonymous() ? IsAnonymous.IS_ANONYMOUS.value() : IsAnonymous.NOT_ANONYMOUS.value(),
                participantComment.getProfessionalAbility(),
                participantComment.getOperationalAbility(),
                participantComment.getComprehensionAbility()
        );
        participantCommentService.insertComment(comment);
        return CommonResult.success(null, "评论成功");
    }


    @PostMapping("/list-work-style")
    @ApiOperation(value = "查询所有的工作方式")
    public CommonResult<Map<Long, ProjectWorkStyle>> getAllWorkStyles() {
        Map<Long, ProjectWorkStyle> allWorkStyles = projectWorkStyleService.getAllWorkStyles();
        return CommonResult.success(allWorkStyles, "查询项目工作方式成功");
    }

    @PostMapping("/list-work-type")
    @ApiOperation(value = "查询所有项目类型")
    public CommonResult<Map<Long, ProjectWorkType>> getAllWorkTypes() {
        Map<Long, ProjectWorkType> allWorkTypes = projectWorkTypeService.getAllWorkTypes();
        return CommonResult.success(allWorkTypes, "查询项目类型成功");
    }

    @PostMapping("/list-work-style-and-type")
    @ApiOperation(value = "查询所有工作方式和类型")
    public CommonResult<ProjectWorkTypeAndStyleVo> getAllWorkStyleAndTypes() {
        ProjectWorkTypeAndStyleVo allTypesAndStyles = projectWorkTypeAndStyleFacadeService.getAll();
        return CommonResult.success(allTypesAndStyles, "查询项目所有类型和工作方式成功");
    }

}
