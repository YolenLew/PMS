package com.kkb.project.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.portal.domain.ParticipantSignup;
import com.kkb.project.portal.domain.UserSkill;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Author River
 * @Date 2021/4/19 0:08
 * @Description 用户技能 Service
 * @Version 1.0
 **/
public interface UserSkillService extends IService<UserSkill> {
    /**
     * 根据id查找用户技能
     *
     * @param id id
     * @return 返回用户技能集合
     */
    List<UserSkill> findUserSkillById(Long id);

    /**
     * 根据 用户Id 集合查询用户技能
     * @param userIds 用户 Id 集合
     * @return 返回用户技能Map, key为userId，value 为user的技能列表
     */
    Map<Long, List<UserSkill>> findUserSkillsByIds(Collection<Long> userIds);
}
