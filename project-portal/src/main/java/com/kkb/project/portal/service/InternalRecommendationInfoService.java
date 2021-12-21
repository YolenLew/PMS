package com.kkb.project.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.common.api.CommonPage;
import com.kkb.project.portal.domain.InternalRecommendationInfo;

import java.util.Date;

/**
 * 内推信息增删改查接口
 *
 * @author lemon
 * @since 2021/04/14
 * @version 1.0
 */
public interface InternalRecommendationInfoService extends IService<InternalRecommendationInfo> {

    /**
     * 通过id查询内推信息
     *
     * @param id 内推信息表id
     * @return   业务实体类
     */
    InternalRecommendationInfo getInternalRecommendationInfoById(Long id);

    /**
     * 分页获取所有的内推信息 pageHelper分页
     *
     * @param page           当前页数
     * @param size           每页显示数量
     * @param companyAddress 公司地址
     * @param publishTime    发布时间
     * @param salary         薪资
     * @return               分页信息
     */
    CommonPage<InternalRecommendationInfo> getInternalRecommendationInfoList(Integer page, Integer size, String companyAddress, Date publishTime, String salary);
}
