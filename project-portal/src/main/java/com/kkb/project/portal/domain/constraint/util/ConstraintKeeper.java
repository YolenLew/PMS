package com.kkb.project.portal.domain.constraint.util;

import cn.hutool.core.util.ObjectUtil;
import com.kkb.project.portal.domain.Client;
import com.kkb.project.portal.domain.Project;

/**
 * @author __SAD_DOG__
 * @date 2021-04-27
 * 用于在业务层检查数据库中的约束关系
 * 声明为enum 防止实例化对象
 */
public enum ConstraintKeeper {
    ;
    public static boolean fkProjectToClientById(Project p, Client c) {
        return ObjectUtil.isAllNotEmpty(p, c) && p.getClientId().equals(c.getId());
    }
}
