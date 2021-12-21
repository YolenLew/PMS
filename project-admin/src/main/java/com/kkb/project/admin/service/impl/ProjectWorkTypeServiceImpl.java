package com.kkb.project.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.admin.domain.Client;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.admin.dao.ProjectWorkTypeDao;
import com.kkb.project.admin.domain.ProjectWorkType;
import com.kkb.project.admin.service.ProjectWorkTypeService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author __SAD_DOG__
 * @date 2021-04-29
 * ProjectWorkType 的service
 */
@Service
public class ProjectWorkTypeServiceImpl extends ServiceImpl<ProjectWorkTypeDao, ProjectWorkType> implements ProjectWorkTypeService {

    /**
     * 查询所有ProjectWorkType集合(使用Map表示, key是id)
     *
     * @return ProjectWorkType集合(使用Map表示, key是id)
     */
    @Override
    public Map<Long, ProjectWorkType> getAllWorkTypes() {
        List<ProjectWorkType> workTypes = this.list();
        Map<Long, ProjectWorkType> res = new HashMap<>(workTypes.size());
        workTypes.forEach(it -> res.put(it.getId(), it));
        return res;
    }

    /**
     * 根据id集合获取ProjectWorkType集合(使用Map表示, key是id)
     *
     * @param ids id 集合
     * @return ProjectWorkType集合(使用Map表示, key是id)
     */
    @Override
    public Map<Long, ProjectWorkType> getByIds(Collection<Long> ids) {
        if (!(ids instanceof Set)) {
            ids = CollectionUtil.newHashSet(ids);
        }
        List<ProjectWorkType> list = this.listByIds(ids);
        if (ids.size() > list.size()) {
            // 有 id 没有查询到
            StringBuilder failIds = new StringBuilder().append(" (");
            List<Long> selectedIds = list.stream().map(ProjectWorkType::getId).collect(Collectors.toList());
            ids.forEach(id -> {
                if (!selectedIds.contains(id)) {
                    failIds.append(id).append(", ");
                }
            });
            failIds.append(") ");
            Asserts.fail("未查询到id 为: " + failIds.toString() + "项目类型");
        }
        Map<Long, ProjectWorkType> res = new HashMap<>(list.size());
        list.forEach(it -> res.put(it.getId(), it));
        return res;
    }
}
