package com.kkb.project.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.portal.domain.LeaderGuidance;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Lai Xiangdong
 * @Description TODO
 * @createTime 2021-04-24 21:36:02
 * @updateBy __SAD_DOG__
 * @updateTime 2021-04-21
 */
public interface LeaderGuidanceService extends IService<LeaderGuidance> {
    /**
     * 通过项目id查询导师信息
     * @param projectId
     * @return
     */
    List<LeaderGuidance> findLeaderGuidanceByProjectId(Long projectId);

    /**
     * 通过项目id集合查询对应的导师指导条目列表
     * @author __SAD_DOG__
     * @param projectIds 项目id集合
     * @return 导师指导条目列表
     */
    Map<Long, List<LeaderGuidance>> findLeaderGuidanceByProjectIds(Collection<Long> projectIds);

    /**
     * 通过项目id集合查询对应的导师指导条目, 每个项目id只查询一条指导条目
     * 目前没有指定顺序的业务要求
     * @author __SAD_DOG__
     * @param projectIds 项目id集合
     * @return 导师指导条目列表
     */
    Map<Long, LeaderGuidance> findOneOfLeaderGuidanceByProjectIds(Collection<Long> projectIds);

    /**
     * 通过项目id集合查询导师指导记录
     * @param projectIds 项目Id 集合
     * @return 导师指导记录列表
     */
    List<LeaderGuidance> getByProjectIds(Collection<Long> projectIds);
}
