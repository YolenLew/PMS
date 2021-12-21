package com.kkb.project.portal.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.portal.dao.WorkDescriptionMapper;
import com.kkb.project.portal.domain.UserWork;
import com.kkb.project.portal.domain.WorkDescription;
import com.kkb.project.portal.service.WorkDescriptionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName WorkDescriptionServiceImpl
 * @Author River
 * @Date 2021/4/19 22:24
 * @Description 作品描述详情实现类
 * @Version 1.0
 **/
@Service
public class WorkDescriptionServiceImpl extends ServiceImpl<WorkDescriptionMapper, WorkDescription> implements WorkDescriptionService {

    /**
     * 根据id查找用户作品集描述
     * @Author Ljh
     * @Date 2021/4/19 22:00
     * @param id 作品集ID
     * @return 返回作品描述
     */
    @Override
    public WorkDescription findWorkDescriptionById(Long id) {
        WorkDescription workDescription = this.getOne(new QueryWrapper<WorkDescription>()
                .eq("user_work_id", id)
                .select("project_desc", "id"));
        if (ObjectUtil.isNull(workDescription)) {
            Asserts.fail("没有此作品描述");
        }
        return workDescription;
    }

    /**
     * 添加作品描述
     *
     * @param workDescription 传入作品描述对象
     */
    @Override
    public void insertWorkDescription(WorkDescription workDescription) {
        boolean saveOrUpdate = this.saveOrUpdate(workDescription);
        if (!saveOrUpdate) {
            Asserts.fail("新增作品描述失败");
        }
    }

    /**
     * 根据用户id，返回用户作品描述
     *
     * @param userId 传入用户ID
     * @return 返回用户作品描述
     */
    @Override
    public List<WorkDescription> listWorkDescriptionByUserId(Long userId) {
        List<WorkDescription> workDescriptions = this.list(new QueryWrapper<WorkDescription>()
                .eq("user_id", userId));
        return workDescriptions;
    }
}
