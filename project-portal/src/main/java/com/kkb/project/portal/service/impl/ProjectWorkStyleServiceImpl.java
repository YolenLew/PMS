package com.kkb.project.portal.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.portal.dao.ProjectWorkStyleDao;
import com.kkb.project.portal.domain.ProjectWorkStyle;
import com.kkb.project.portal.service.ProjectWorkStyleService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author __SAD_DOG__
 * @date 2021-04-29
 * ProjectWorkStyle 的service
 */
@Service
public class ProjectWorkStyleServiceImpl extends ServiceImpl<ProjectWorkStyleDao, ProjectWorkStyle> implements ProjectWorkStyleService {


    /**
     * 查询所有ProjectWorkStyle集合(使用Map表示, key是id)
     *
     * @return ProjectWorkStyle集合(使用Map表示, key是id)
     */
    @Override
    public Map<Long, ProjectWorkStyle> getAllWorkStyles() {
        List<ProjectWorkStyle> workStyles = this.list();
        Map<Long, ProjectWorkStyle> res = new HashMap<>(workStyles.size());
        workStyles.forEach(it -> res.put(it.getId(), it));
        return res;
    }

    /**
     * 根据id集合获取ProjectWorkStyle集合(使用Map表示, key是id)
     *
     * @param ids id 集合
     * @return ProjectWorkType集合(使用Map表示, key是id)
     */
    @Override
    public Map<Long, ProjectWorkStyle> getByIds(Collection<Long> ids) {
        if (!(ids instanceof Set)) {
            ids = CollectionUtil.newHashSet(ids);
        }
        List<ProjectWorkStyle> list = this.listByIds(ids);
        if (ids.size() > list.size()) {
            // 有 id 没有查询到
            StringBuilder failIds = new StringBuilder().append(" (");
            List<Long> selectedIds = list.stream().map(ProjectWorkStyle::getId).collect(Collectors.toList());
            ids.forEach(id -> {
                if (!selectedIds.contains(id)) {
                    failIds.append(id).append(", ");
                }
            });
            failIds.append(") ");
            Asserts.fail("未查询到id 为: " + failIds.toString() + "项目工作方式");
        }
        Map<Long, ProjectWorkStyle> res = new HashMap<>(list.size());
        list.forEach(it -> res.put(it.getId(), it));
        return res;
    }
}
