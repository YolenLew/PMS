package com.kkb.project.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.admin.domain.InternalRecommendationProperty;

import java.util.List;

/**
 * 内推信息属性服务类
 *
 * @author Yolen
 * @since 2021-04-21
 */
public interface InternalRecommendationPropertyService extends IService<InternalRecommendationProperty> {

    /**
     * 获取指定type的职位属性信息
     *
     * @param type 属性类型
     * @return 属性集合
     */
    List<InternalRecommendationProperty> findByType(Integer type);

    /**
     * 查询所有职位属性信息
     *
     * @return 属性集合
     */
    List<InternalRecommendationProperty> listAll();

    /**
     * 获取指定id的职位属性信息
     *
     * @param id 属性id
     * @return 职位属性信息
     */
    InternalRecommendationProperty findById(Long id);

    /**
     * 添加职位属性
     *
     * @param internalRecommendationProperty 职位属性实体
     */
    void addInternalRecommendationProperty(InternalRecommendationProperty internalRecommendationProperty);

    /**
     * 修改职位属性
     *
     * @param internalRecommendationProperty 职位属性实体
     */
    void updateInternalRecommendationProperty(InternalRecommendationProperty internalRecommendationProperty);

    /**
     * 删除职位属性
     *
     * @param id 职位属性表id
     */
    void deleteInternalRecommendationProperty(Long id);

}
