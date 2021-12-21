package com.kkb.project.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.admin.domain.ProjectWorkType;

import java.util.Collection;
import java.util.Map;

/**
 * @author __SAD_DOG__
 * @date 2021-04-29
 * ProjectWorkType 的service
 */
public interface ProjectWorkTypeService extends IService<ProjectWorkType> {
    /**
     * 获取ProjectWorkType集合(使用Map表示, key是id)
     * @return ProjectWorkType集合(使用Map表示, key是id)
     */
    Map<Long, ProjectWorkType> getAllWorkTypes();

    /**
     * 根据id集合获取ProjectWorkType集合(使用Map表示, key是id)
     * @param ids id 集合
     * @return ProjectWorkType集合(使用Map表示, key是id)
     */
    Map<Long, ProjectWorkType> getByIds(Collection<Long> ids);
}
