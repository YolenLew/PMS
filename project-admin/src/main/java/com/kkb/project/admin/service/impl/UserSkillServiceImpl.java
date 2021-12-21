package com.kkb.project.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.admin.dao.UserSkillMapper;
import com.kkb.project.admin.domain.UserSkill;
import com.kkb.project.admin.service.UserSkillService;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserSkillServiceImpl
 * @Author Tao
 * @Date 2021/4/20 21:00
 * @Description 用户技能表Service实现类
 * @Version 1.0
 **/
@Service
public class UserSkillServiceImpl extends ServiceImpl<UserSkillMapper, UserSkill> implements UserSkillService {
}
