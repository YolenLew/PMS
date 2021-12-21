package com.kkb.project.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.portal.domain.UserExperience;

import java.util.List;

/**
 * @Author Tao
 * @Date 2021/4/20 21:00
 * @Description 用户经验 Service
 * @Version 1.0
 */
public interface UserExperienceService extends IService<UserExperience> {
    /**
     * 根据id查找用户经验集
     * @param id 传入用户id
     * @return 返回用户经验集合
     */
    List<UserExperience> findUserExperienceById(Long id);

}
