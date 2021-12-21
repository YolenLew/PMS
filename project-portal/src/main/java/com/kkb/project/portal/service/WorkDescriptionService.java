package com.kkb.project.portal.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.portal.domain.WorkDescription;

import java.util.List;

/**
 * @Author River
 * @Date 2021/4/19 0:08
 * @Description 作品描述详情 Service
 * @Version 1.0
 **/
public interface WorkDescriptionService extends IService<WorkDescription> {

    /**
     * 根据id查找用户作品集描述
     * @Author Ljh
     * @Date 2021/4/19 22:00
     * @param id 作品集ID
     * @return 返回作品描述
     */
    WorkDescription findWorkDescriptionById(Long id);

    /**
     * 添加作品描述
     *
     * @param workDescription 传入作品描述对象
     */
    void insertWorkDescription(WorkDescription workDescription);

    /**
     * 通过userId查找用户所有作品集的描述
     *
     * @param userId 用户id
     * @return 用户作品集描述
     */
    List<WorkDescription> listWorkDescriptionByUserId(Long userId);
}
