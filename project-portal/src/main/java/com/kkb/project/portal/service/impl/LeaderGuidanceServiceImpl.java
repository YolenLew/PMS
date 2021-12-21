package com.kkb.project.portal.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.portal.dao.LeaderGuidanceDao;
import com.kkb.project.portal.domain.Client;
import com.kkb.project.portal.domain.LeaderGuidance;
import com.kkb.project.portal.service.LeaderGuidanceService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Lai Xiangdong
 * @createTime 2021-04-24 21:39:12
 */
@Service
public class LeaderGuidanceServiceImpl extends ServiceImpl<LeaderGuidanceDao, LeaderGuidance> implements LeaderGuidanceService {
    /**
     * 通过项目id查询导师信息
     *
     * @param projectId 项目Id
     * @return 对应的导师指导条目
     */
    @Override
    public List<LeaderGuidance> findLeaderGuidanceByProjectId(Long projectId) {
        QueryWrapper<LeaderGuidance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        List<LeaderGuidance> list = this.list(queryWrapper);
        if (list.size() == 0) {
            Asserts.fail("id 为" + projectId + " 的项目没有导师指导");
        }
        return list;
    }

    @Override
    public Map<Long, List<LeaderGuidance>> findLeaderGuidanceByProjectIds(Collection<Long> projectIds) {
        List<LeaderGuidance> list = getByProjectIds(projectIds);
        Map<Long, List<LeaderGuidance>> res = new HashMap<>(projectIds.size());
        list.forEach(it -> {
            if (!res.containsKey(it.getProjectId())) {
                res.put(it.getProjectId(), new LinkedList<>());
            }
            res.get(it.getProjectId()).add(it);
        });
        return res;
    }

    @Override
    public Map<Long, LeaderGuidance> findOneOfLeaderGuidanceByProjectIds(Collection<Long> projectIds) {
        getByProjectIds(projectIds);
        List<LeaderGuidance> list = getByProjectIds(projectIds);
        Map<Long, LeaderGuidance> res = new HashMap<>(projectIds.size());
        list.forEach(it -> {
            if (!res.containsKey(it.getProjectId())) {
                res.put(it.getProjectId(), it);
            }
        });
        return res;
    }

    /**
     * 通过项目id集合查询导师指导记录
     * @param projectIds 项目Id 集合
     * @return 导师指导记录列表
     */
    @Override
    public List<LeaderGuidance> getByProjectIds(Collection<Long> projectIds) {
        if (!(projectIds instanceof Set)) {
            projectIds = CollectionUtil.newHashSet(projectIds);
        }
        QueryWrapper<LeaderGuidance> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("project_id", projectIds);
        List<LeaderGuidance> list = this.list(queryWrapper);
        if (projectIds.size() > list.size()) {
            // 有 id 没有查询到
            StringBuilder failIds = new StringBuilder().append(" (");
            List<Long> selectedIds = list.stream().map(LeaderGuidance::getProjectId).collect(Collectors.toList());
            projectIds.forEach(id -> {
                if (!selectedIds.contains(id)) {
                    failIds.append(id).append(", ");
                }
            });
            failIds.append(") ");
            Asserts.fail("未查询到项目id 为: " + failIds.toString() + "的指导老师");
        }
        return list;
    }
}
