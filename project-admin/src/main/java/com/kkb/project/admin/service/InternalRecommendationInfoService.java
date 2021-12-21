package com.kkb.project.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.admin.domain.InternalRecommendationInfo;

import java.util.List;

/**
 * 内推信息增删改接口
 *
 * @author lemon
 * @version 1.0
 * @since 2021/04/14
 */
public interface InternalRecommendationInfoService extends IService<InternalRecommendationInfo> {

    /**
     * 添加内推信息
     *
     * @param internalRecommendationInfo 业务实体类
     */
    void addInternalRecommendationInfo(InternalRecommendationInfo internalRecommendationInfo);

    /**
     * 修改内推信息
     *
     * @param internalRecommendationInfo 业务实体类
     */
    void updateInternalRecommendationInfo(InternalRecommendationInfo internalRecommendationInfo);

    /**
     * 删除内推信息
     *
     * @param id 内推信息表id
     */
    void deleteInternalRecommendationInfo(Long id);

    /**
     * 批量删除内推信息
     *
     * @param ids 内推信息表id
     */
    void deleteInternalRecommendationInfo(Long[] ids);

    /**
     * 添加用户的内推介绍浏览记录
     *
     * @param infoId 内推介绍id
     */
    void addHistory(Integer infoId);

    /**
     * 查询用户的内推介绍浏览记录
     *
     * @return 内推介绍浏览记录
     */
    List<InternalRecommendationInfo> getHistory();
}
