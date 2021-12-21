package com.kkb.project.admin.domain;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义分组条件校验：在 类型为导师 的时候，再添加TeacherTypeGroup校验标志
 *
 * @author  Yolen
 * @date  2021/5/1
 */
public class TeacherSequenceProvider implements DefaultGroupSequenceProvider<UserInfo> {

    @Override
    public List<Class<?>> getValidationGroups(UserInfo userInfo) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        defaultGroupSequence.add(UserInfo.class);
        if (userInfo != null && (userInfo.getType() == 0 )) {
            defaultGroupSequence.add(TeacherTypeGroup.class);
        }
        return defaultGroupSequence;
    }
}
