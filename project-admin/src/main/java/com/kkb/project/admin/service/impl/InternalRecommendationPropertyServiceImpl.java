package com.kkb.project.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.admin.dao.InternalRecommendationPropertyMapper;
import com.kkb.project.admin.domain.InternalRecommendationProperty;
import com.kkb.project.admin.service.InternalRecommendationPropertyService;
import com.kkb.project.common.exception.Asserts;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 内推信息属性服务实现类
 *
 * @author Yolen
 * @since 2021-04-21
 */
@Service
public class InternalRecommendationPropertyServiceImpl extends ServiceImpl<InternalRecommendationPropertyMapper, InternalRecommendationProperty> implements InternalRecommendationPropertyService {

    /**
     * 获取指定type的内推信息属性
     *
     * @param type 属性类型
     * @return 属性集合
     */
    @Override
    public List<InternalRecommendationProperty> findByType(Integer type) {
        LambdaQueryWrapper<InternalRecommendationProperty> queryWrapper = Wrappers.<InternalRecommendationProperty>lambdaQuery()
                .eq(InternalRecommendationProperty::getType, type);
        List<InternalRecommendationProperty> internalRecommendationPropertyList = this.list(queryWrapper);
        if (CollectionUtil.isEmpty(internalRecommendationPropertyList)) {
            Asserts.fail(StrUtil.format("类型【{}】对应的内推属性信息不存在", type));
        }
        return internalRecommendationPropertyList;
    }

    /**
     * 查询所有内推职位属性信息
     *
     * @return 属性集合
     */
    @Override
    public List<InternalRecommendationProperty> listAll() {
        List<InternalRecommendationProperty> internalRecommendationPropertyList = this.list();
        if (CollectionUtil.isEmpty(internalRecommendationPropertyList)) {
            Asserts.fail("查询的内推属性信息为空");
        }
        return internalRecommendationPropertyList;
    }

    /**
     * 获取指定id的内推信息属性
     *
     * @param id 属性id
     * @return 内推信息属性
     */
    @Override
    public InternalRecommendationProperty findById(Long id) {
        InternalRecommendationProperty internalRecommendationProperty = this.getById(id);
        if (ObjectUtil.isNull(internalRecommendationProperty)) {
            Asserts.fail(StrUtil.format("职位属性信息【{}】不存在", id));
        }
        return internalRecommendationProperty;
    }

    /**
     * 添加职位属性
     *
     * @param internalRecommendationProperty 职位属性类
     */
    @Override
    public void addInternalRecommendationProperty(InternalRecommendationProperty internalRecommendationProperty) {
        boolean saveResult = this.save(internalRecommendationProperty);
        if (!saveResult) {
            Asserts.fail("添加职位属性失败");
        }
    }

    /**
     * 修改职位属性
     *
     * @param internalRecommendationProperty 职位属性类
     */
    @Override
    public void updateInternalRecommendationProperty(InternalRecommendationProperty internalRecommendationProperty) {
        Long id = internalRecommendationProperty.getId();
        if (ObjectUtil.isNull(id)) {
            Asserts.fail("未获取到职位属性唯一标识，更新失败");
        }
        InternalRecommendationProperty propertyById = this.getById(id);
        if (ObjectUtil.isNull(propertyById)) {
            Asserts.fail(StrUtil.format("职位属性【{}】不存在", id));
        }
        internalRecommendationProperty.setRevision(propertyById.getRevision());
        boolean updateResult = this.updateById(internalRecommendationProperty);
        if (!updateResult) {
            Asserts.fail(StrUtil.format("更新职位属性【{}】失败", id));
        }
    }

    /**
     * 删除职位属性
     *
     * @param id 职位属性表id
     */
    @Override
    public void deleteInternalRecommendationProperty(Long id) {
        InternalRecommendationProperty propertyById = this.getById(id);
        if (ObjectUtil.isNull(propertyById)) {
            Asserts.fail(StrUtil.format("职位属性【{}】不存在", id));
        }
        boolean deleteResult = this.removeById(id);
        if (!deleteResult) {
            Asserts.fail(StrUtil.format("职位属性【{}】删除失败", id));
        }
    }
}
