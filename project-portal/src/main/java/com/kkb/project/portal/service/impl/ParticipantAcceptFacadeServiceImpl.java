package com.kkb.project.portal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kkb.project.common.api.CommonPage;
import com.kkb.project.common.api.CommonResult;
import com.kkb.project.portal.domain.ParticipantPartake;
import com.kkb.project.portal.domain.ParticipantSignup;
import com.kkb.project.portal.domain.User;
import com.kkb.project.portal.domain.UserSkill;
import com.kkb.project.portal.domain.vo.UserVo;
import com.kkb.project.portal.service.*;
import com.kkb.project.portal.service.util.PageUtil;
import com.kkb.project.portal.service.util.VoWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author __SAD_DOG__
 * @date 2021-05-01
 */
@Service
public class ParticipantAcceptFacadeServiceImpl implements ParticipantAcceptFacadeService {

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private ParticipantPartakeService participantPartakeService;

    @Autowired
    private UserSkillService userSkillService;

    @Autowired
    private UserService userService;

    /**
     * 分页查询所有已报名的学员
     * @param projectId 项目id
     * @param pageNum 当前页
     * @param pageSize 页面大小
     * @return 返回CommonPage<UserVo>的数据包含报名表学员信息，User头像信息，User技能信息
     */
    @Override
    public CommonPage<UserVo> findAllSignUp(Long projectId, Integer pageNum, Integer pageSize) {
        //1.根据项目id查询已报名学员信息
        CommonPage<ParticipantSignup> participantsSignUp = participantService.findAllSignUp(projectId,pageNum,pageSize);
        List<ParticipantSignup> participantSignUpList = participantsSignUp.getList();
        //2.此时我们已获得含有全部已报名学员信息的participantSignUpList，我们需要获取其中的学员id
        List<Long> participantIds = participantSignUpList.stream().map(ParticipantSignup::getId).collect(Collectors.toList());
        //3.根据查询到的学员id集合去获取相应的头像信息
        List<User> users = userService.listByIds(participantIds);
        //4.根据查询到的学员id集合去获取相应的学员技能信息
        Map<Long, List<UserSkill>> userSkills = userSkillService.findUserSkillsByIds(participantIds);
        List<UserVo> userVoList = VoWrapper.toAcceptUserVos(users, userSkills);
        Page<Object> page = new Page<>(participantsSignUp.getPageNum(),participantsSignUp.getPageSize());
        return PageUtil.wrapToCommonPage(userVoList,page);
    }

    /**
     * 分页查询所有已参与的学员
     * @param projectId 项目id
     * @param pageNum 当前页
     * @param pageSize 页面大小
     * @return 返回CommonPage<UserVo>的数据包含参与表学员信息，User头像信息，User技能信息
     */
    @Override
    public CommonPage<UserVo> findAllPartake(Long projectId, Integer pageNum, Integer pageSize){
        CommonPage<ParticipantPartake> participantsPartake = participantPartakeService.findAllPartake(projectId,pageNum,pageSize);
        List<ParticipantPartake> participantPartakeList = participantsPartake.getList();
        List<Long> participantIds = participantPartakeList.stream().map(ParticipantPartake::getId).collect(Collectors.toList());
        List<User> users = userService.listByIds(participantIds);
        Map<Long, List<UserSkill>> userSkills = userSkillService.findUserSkillsByIds(participantIds);
        List<UserVo> userVoList = VoWrapper.toAcceptUserVos(users, userSkills);
        Page<Object> page = new Page<>(participantsPartake.getPageNum(),participantsPartake.getPageSize());
        return PageUtil.wrapToCommonPage(userVoList,page);
    }
}
