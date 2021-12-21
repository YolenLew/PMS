package com.kkb.project.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.admin.dao.InternalRecommendationInfoMapper;
import com.kkb.project.admin.domain.InternalRecommendationInfo;
import com.kkb.project.admin.service.InternalRecommendationInfoService;
import com.kkb.project.common.exception.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 内推信息增删改查接口实现类
 *
 * @author lemon
 * @version 1.0
 * @since 2021/04/14
 */
@Service
public class InternalRecommendationInfoServiceImpl extends ServiceImpl<InternalRecommendationInfoMapper, InternalRecommendationInfo> implements InternalRecommendationInfoService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 添加内推信息
     *
     * @param internalRecommendationInfo 业务实体类
     */
    @Override
    public void addInternalRecommendationInfo(InternalRecommendationInfo internalRecommendationInfo) {
        boolean saveResult = this.save(internalRecommendationInfo);
        if (!saveResult) {
            Asserts.fail("添加内推信息失败");
        }
    }

    /**
     * 修改内推信息
     *
     * @param internalRecommendationInfo 业务实体类
     */
    @Override
    public void updateInternalRecommendationInfo(InternalRecommendationInfo internalRecommendationInfo) {
        Long id = internalRecommendationInfo.getId();
        if (ObjectUtil.isNull(id)) {
            Asserts.fail("未获取到内推信息唯一标识，更新失败");
        }
        InternalRecommendationInfo infoById = this.getById(id);
        if (ObjectUtil.isNull(infoById)) {
            Asserts.fail(StrUtil.format("内推信息[{}]不存在", id));
        }
        internalRecommendationInfo.setRevision(infoById.getRevision());
        boolean updateResult = this.updateById(internalRecommendationInfo);
        if (!updateResult) {
            Asserts.fail("更新内推信息失败");
        }
    }

    /**
     * 删除内推信息
     *
     * @param id 内推信息表id
     */
    @Override
    public void deleteInternalRecommendationInfo(Long id) {
        boolean deleteResult = this.removeById(id);
        if (!deleteResult) {
            Asserts.fail(StrUtil.format("内推信息[{}]删除失败", id));
        }
    }

    /**
     * 批量删除内推信息
     *
     * @param ids 内推信息表id
     */
    @Override
    public void deleteInternalRecommendationInfo(Long[] ids) {
        boolean deleteResult = this.removeByIds(Arrays.asList(ids));
        if (!deleteResult) {
            Asserts.fail(StrUtil.format("{}内推信息删除失败", Arrays.toString(ids)));
        }
    }

    /**
     * 添加用户的内推介绍浏览记录
     *
     * @param infoId 内推介绍id
     */
    @Override
    public void addHistory(Integer infoId) {
        int count = this.count(Wrappers.<InternalRecommendationInfo>lambdaQuery().eq(InternalRecommendationInfo::getId, infoId));
        if (count < 1) {
            Asserts.fail(StrUtil.format("内推信息[{}]不存在", infoId));
        }
        // TODO 从token中获取用户信息
        String username = "user";
        String infoKey = "HISTORY_INTERNAL_RECOMMENDATION_INFO_" + username;
        // 利用redis实现存储浏览记录
        // 1.去重：如果内推信息已经被浏览，需要先将信息id从列表中移除
        redisTemplate.opsForList().remove(infoKey, 0, infoId);
        // 2.有序新增
        redisTemplate.opsForList().leftPush(infoKey, infoId);
        redisTemplate.expire(infoKey, TimeUnit.DAYS.toSeconds(30), TimeUnit.SECONDS);
        // 3.有限存储：只保留最近一个月内10次的浏览记录
        redisTemplate.opsForList().trim(infoKey, 0, 9);
    }

    /**
     * 查询用户的内推介绍浏览记录
     *
     * @return 内推介绍浏览记录
     */
    @Override
    public List<InternalRecommendationInfo> getHistory() {
        // TODO 从token中获取用户信息
        String username = "user";
        String infoKey = "HISTORY_INTERNAL_RECOMMENDATION_INFO_" + username;
        List<Object> ids = redisTemplate.opsForList().range(infoKey, 0, -1);
        if (CollectionUtil.isEmpty(ids)) {
            Asserts.fail(StrUtil.format("用户【{}】的内推信息浏览记录为空", username));
        }
        List<InternalRecommendationInfo> infoList = new ArrayList<>();
        ids.forEach(id -> {
            InternalRecommendationInfo infoById = this.getById((Integer) id);
            if (infoById != null) {
                infoList.add(infoById);
            }
        });
        return infoList;
    }
}
