package com.kkb.project.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.admin.domain.ProjectWorkStyle;

import java.util.Collection;
import java.util.Map;

/**
 * @author __SAD_DOG__
 * @date 2021-04-29
 * ProjectWorkType 的service
 */
public interface ProjectWorkStyleService extends IService<ProjectWorkStyle> {
    /**
     * 查询所有ProjectWorkStyle类型(使用Map表示, key是id)
     * @return ProjectWorkStyle集合(使用Map表示, key是id)
     */
    Map<Long, ProjectWorkStyle> getAllWorkStyles();

    /**
     * 根据id集合获取ProjectWorkStyle集合(使用Map表示, key是id)
     * @param ids id 集合
     * @return ProjectWorkType集合(使用Map表示, key是id)
     */
    Map<Long, ProjectWorkStyle> getByIds(Collection<Long> ids);
}
