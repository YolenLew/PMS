package com.kkb.project.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.admin.dao.UserExperienceMapper;
import com.kkb.project.admin.domain.UserExperience;
import com.kkb.project.admin.service.UserExperienceService;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserExperienceServiceImpl
 * @Author Tao
 * @Date 2021/4/20 21:00
 * @Description 用户经验 Service实现类
 * @Version 1.0
 **/
@Service
public class UserExperienceServiceImpl extends ServiceImpl<UserExperienceMapper, UserExperience> implements UserExperienceService {
}
