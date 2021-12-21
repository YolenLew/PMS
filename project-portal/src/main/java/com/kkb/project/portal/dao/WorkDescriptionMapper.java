package com.kkb.project.portal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kkb.project.portal.domain.WorkDescription;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author River
 * @Date 2021/4/16 0:08
 * @Description 作品项目描述表  Mapper接口
 * @Version 1.0
 **/
public interface WorkDescriptionMapper extends BaseMapper<WorkDescription> {

    /**
     * 插入一条记录
     *
     * @param entity 实体对象
     * @return 返回0or1
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    int insert(WorkDescription entity);
}
