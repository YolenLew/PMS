package com.kkb.project.portal.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.common.api.CommonPage;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.portal.dao.InternalRecommendationInfoMapper;
import com.kkb.project.portal.domain.InternalRecommendationInfo;
import com.kkb.project.portal.service.InternalRecommendationInfoService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 内推信息增删改查接口实现类
 *
 * @author lemon
 * @version 1.0
 * @since 2021/04/14
 */
@Service
public class InternalRecommendationInfoServiceImpl extends ServiceImpl<InternalRecommendationInfoMapper, InternalRecommendationInfo> implements InternalRecommendationInfoService {

    /**
     * 获取所有信息 对tag处理 将数组填充到实体
     *
     * @param id 内推信息表主键
     * @return   返回业务实体
     */
    @Override
    public InternalRecommendationInfo getInternalRecommendationInfoById(Long id) {
        InternalRecommendationInfo info = this.getById(id);
        if (ObjectUtil.isNull(info)) {
            Asserts.fail("未找到该内推信息");
        }
        return info;
    }

    /**
     * 分页查询
     * 当表中数据为空(infoList != null && infoList.size() == 0) 直接返回数据
     * 当获取的数据为null时 抛出异常
     *
     * @param pageNum        当前页数
     * @param pageSize       每页显示数量
     * @param companyAddress 公司地址
     * @param publishTime    发布时间
     * @param salary         薪资
     * @return               分页信息
     */
    @Override
    public CommonPage<InternalRecommendationInfo> getInternalRecommendationInfoList(Integer pageNum, Integer pageSize, String companyAddress, Date publishTime, String salary) {
        Page<InternalRecommendationInfo> page = new Page<>(pageNum, pageSize);
        // 构建查询条件
        QueryWrapper<InternalRecommendationInfo> queryWrapper = Wrappers.<InternalRecommendationInfo>query()
                .like(StrUtil.isNotBlank(companyAddress), "company_address", companyAddress)
                .ge(ObjectUtil.isNotNull(publishTime), "publish_time", publishTime)
                .ge(StrUtil.isNotBlank(salary), "salary+0", salary)
                .orderBy(StrUtil.isNotBlank(salary), false, "salary+0")
                // 默认按发布日期降序排列
                .orderByDesc("publish_time");
        // 执行分页条件查询
        Page<InternalRecommendationInfo> pageInfo = this.page(page, queryWrapper);
        if (ObjectUtil.isNull(pageInfo)) {
            Asserts.fail("获取数据失败");
        }
        List<InternalRecommendationInfo> records = pageInfo.getRecords();
        // 组装业务数据
        CommonPage<InternalRecommendationInfo> commonPage = new CommonPage<>();
        commonPage.setPageNum((int) pageInfo.getCurrent());
        commonPage.setPageSize((int) pageInfo.getSize());
        commonPage.setTotal(pageInfo.getTotal());
        commonPage.setTotalPage((int) pageInfo.getPages());
        commonPage.setList(records);
        return commonPage;
    }
}
