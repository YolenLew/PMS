package com.kkb.project.portal.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.portal.dao.UserSkillMapper;
import com.kkb.project.portal.domain.ParticipantSignup;
import com.kkb.project.portal.domain.UserSkill;
import com.kkb.project.portal.service.UserSkillService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName UserSkillServiceImpl
 * @Author Tao
 * @Date 2021/4/20 21:00
 * @Description 用户技能表Service实现类
 * @Version 1.0
 **/
@Service
public class UserSkillServiceImpl extends ServiceImpl<UserSkillMapper, UserSkill> implements UserSkillService {
    /**
     * 根据id查找用户技能
     *
     * @param id 用户id
     * @return 返回用户技能集合
     */
    @Override
    public List<UserSkill> findUserSkillById(Long id) {
        List<UserSkill> userSkills = this.list(new QueryWrapper<UserSkill>().eq("user_id", id));
        if (ObjectUtil.isNull(userSkills)) {
            Asserts.fail("没有此用户擅长技能");
        }
        return userSkills;
    }

    /**
     * 根据 用户Id 集合查询用户技能
     *
     * @param userIds 用户 Id 集合
     * @return 返回用户技能Map, key为userId，value 为user的技能列表
     */
    @Override
    public Map<Long, List<UserSkill>> findUserSkillsByIds(Collection<Long> userIds) {
        QueryWrapper<UserSkill> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",userIds);
        List<UserSkill> userSkills = this.list(queryWrapper);
        Map<Long, List<UserSkill>> res = new HashMap<>(userSkills.size());
        userSkills.forEach(it -> {
            if (!res.containsKey(it.getUserId())){
                res.put(it.getUserId(),new LinkedList<UserSkill>());
            }
            res.get(it.getUserId()).add(it);
        });
        return res;
    }
}
