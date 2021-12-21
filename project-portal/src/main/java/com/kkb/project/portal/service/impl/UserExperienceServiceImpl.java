package com.kkb.project.portal.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.portal.dao.UserExperienceMapper;
import com.kkb.project.portal.domain.UserExperience;
import com.kkb.project.portal.service.UserExperienceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserExperienceServiceImpl
 * @Author Tao
 * @Date 2021/4/20 21:00
 * @Description 用户经验 Service实现类
 * @Version 1.0
 **/
@Service
public class UserExperienceServiceImpl extends ServiceImpl<UserExperienceMapper, UserExperience> implements UserExperienceService {
    /**
     * 根据id查找用户经验集
     *
     * @param id 传入用户id
     * @return 返回用户经验集合
     */
    @Override
    public List<UserExperience> findUserExperienceById(Long id) {
        List<UserExperience> userExperiences = this.list(new QueryWrapper<UserExperience>().eq("user_id", id));
        if (ObjectUtil.isNull(userExperiences)) {
            Asserts.fail("没有此用户项目经验");
        }
        return userExperiences;
    }
}
