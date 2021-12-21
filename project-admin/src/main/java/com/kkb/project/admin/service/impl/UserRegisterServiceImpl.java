package com.kkb.project.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.admin.dao.UserRegisterMapper;
import com.kkb.project.admin.domain.*;
import com.kkb.project.admin.service.*;
import com.kkb.project.common.exception.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户注册服务实现类
 *
 * @author Yolen
 * @since 2021-04-29
 */
@Service
public class UserRegisterServiceImpl extends ServiceImpl<UserRegisterMapper, UserRegister> implements UserRegisterService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserSkillService userSkillService;
    @Autowired
    private UserExperienceService userExperienceService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 完善身份资料
     *
     * @param userInfo 身份资料
     */
    @Override
    public void addIdentity(UserInfo userInfo) {
        // TODO 应从令牌中获取用户id
        Long loginId = userInfo.getLoginId();
        User userByLoginId = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getLoginId, loginId));
        // 完善user_register表
        UserRegister register = this.getById(loginId);
        register.setId(loginId);
        register.setPhone(userInfo.getPhone());
        register.setType(userInfo.getType());
        boolean updateRegister = this.updateById(register);
        if (!updateRegister) {
            Asserts.fail("用户身份信息提交失败");
        }
        // 完善user表
        boolean updateUser = updateUser(loginId, userInfo);
        if (!updateUser) {
            Asserts.fail("用户信息更新失败");
        }
        // 完善user_skill技能表
        List<String> skillList = userInfo.getSkillList();
        if (CollectionUtil.isNotEmpty(skillList)) {
            boolean userSkillResult = addUserSkill(userByLoginId.getId(), skillList);
            if (!userSkillResult) {
                Asserts.fail("用户技能信息创建失败");
            }
        }
        // 完善user_role表
        boolean roleResult = addUserRole(loginId, userInfo.getType());
        if (!roleResult) {
            Asserts.fail("用户角色信息创建失败");
        }
    }

    /**
     * 修改个人资料
     *
     * @param userInfo 个人资料
     */
    @Override
    public void updateUserInfo(UserInfo userInfo) {
        // TODO 应从令牌中获取注册用户id
        Long loginId = userInfo.getLoginId();
        User userByLoginId = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getLoginId, loginId));
        Long userId = userByLoginId.getId();
        // 更新user_register信息
        if (StrUtil.isNotBlank(userInfo.getPhone())) {
            UserRegister register = new UserRegister();
            register.setId(loginId);
            register.setPhone(userInfo.getPhone());
            boolean updateRegister = this.updateById(register);
            if (!updateRegister) {
                Asserts.fail("用户信息更新失败");
            }
        }
        // 更新user信息
        boolean updateUser = updateUser(loginId, userInfo);
        if (!updateUser) {
            Asserts.fail("用户信息更新失败");
        }
        // 更新user_skill技能表
        List<String> skillList = userInfo.getSkillList();
        if (CollectionUtil.isNotEmpty(skillList)) {
            // 1.删除旧有的技能信息
            userSkillService.remove(Wrappers.<UserSkill>lambdaQuery().eq(UserSkill::getUserId, userId));
            // 2.重新添加技能信息
            boolean userSkillResult = addUserSkill(userId, skillList);
            if (!userSkillResult) {
                Asserts.fail("用户技能信息更新失败");
            }
        }
        // 更新user_experience项目经验
        List<String> experienceList = userInfo.getExperienceList();
        if (CollectionUtil.isEmpty(experienceList)) {
            return;
        }
        // 1.删除旧有的项目信息
        userExperienceService.remove(Wrappers.<UserExperience>lambdaQuery().eq(UserExperience::getUserId, userId));
        // 2.重新添加项目信息
        boolean addExperienceResult = addUserExperience(userId, experienceList);
        if (!addExperienceResult) {
            Asserts.fail("用户项目经验信息更新失败");
        }
    }

    /**
     * 创建用户角色
     *
     * @param loginId 注册用户id
     * @param type    用户类型：0：导师，1：学生
     * @return 创建结果
     */
    private boolean addUserRole(Long loginId, Integer type) {
        Role studentRole = roleService.getOne(Wrappers.<Role>lambdaQuery().eq(Role::getRoleName, "student"));
        Role teacherRole = roleService.getOne(Wrappers.<Role>lambdaQuery().eq(Role::getRoleName, "teacher"));
        UserRole userRole = new UserRole();
        userRole.setUserId(loginId);
        userRole.setRoleId(studentRole.getId());
        if (type == 0) {
            userRole.setRoleId(teacherRole.getId());
        }
        return userRoleService.save(userRole);
    }

    /**
     * 创建/更新 user表信息
     *
     * @param loginId  注册用户id
     * @param userInfo 用户信息
     * @return 更新结果
     */
    private boolean updateUser(Long loginId, UserInfo userInfo) {
        User userByLoginId = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getLoginId, loginId));
        // 封装user实体
        User user = BeanUtil.copyProperties(userInfo, User.class);
        user.setId(userByLoginId.getId());
        user.setLoginId(loginId);
        user.setRevision(userByLoginId.getRevision());
        if (userInfo.getType() == 1) {
            user.setDailySalary(userInfo.getDailySalary());
            user.setWorkYear(userInfo.getWorkYear());
        }
        return userService.updateById(user);
    }

    /**
     * 添加user_skill技能信息
     *
     * @param loginId   user表的用户主键
     * @param skillList 技能列表
     */
    private boolean addUserSkill(Long loginId, List<String> skillList) {
        List<UserSkill> userSkillList = new ArrayList<>();
        skillList.forEach(skill -> {
            UserSkill userSkill = new UserSkill();
            userSkill.setUserId(loginId);
            userSkill.setSkillDesc(skill);
            userSkillList.add(userSkill);
        });
        return userSkillService.saveBatch(userSkillList);
    }

    /**
     * 添加user_experience项目经验信息
     *
     * @param loginId        user表的用户主键
     * @param experienceList 项目列表
     */
    private boolean addUserExperience(Long loginId, List<String> experienceList) {
        List<UserExperience> userExperienceList = new ArrayList<>();
        experienceList.forEach(experience -> {
            UserExperience userExperience = new UserExperience();
            userExperience.setUserId(loginId);
            userExperience.setProjectExpDesc(experience);
        });
        return userExperienceService.saveBatch(userExperienceList);
    }
}
