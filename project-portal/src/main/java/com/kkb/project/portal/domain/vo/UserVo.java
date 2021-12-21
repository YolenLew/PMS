package com.kkb.project.portal.domain.vo;

import com.kkb.project.portal.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName UserVo
 * @Author River
 * @Date 2021/4/25 20:24
 * @Description 查看名人堂用户信息返回参数，封装为对象 便于Knife4j 显示  包装类
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private User user;
    private List<UserWork> userWorkList;
    private List<UserExperience> userExperienceList;
    private List<UserSkill> userSkillList;
    private List<WorkImage> workImageList;

}
