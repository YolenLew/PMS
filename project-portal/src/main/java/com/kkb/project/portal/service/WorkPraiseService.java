package com.kkb.project.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.portal.domain.WorkPraise;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Author River
 * @Date 2021/4/19 0:08
 * @Description 作品点赞 Service
 * @Version 1.0
 **/
public interface WorkPraiseService extends IService<WorkPraise> {
    /**
     * 通过作品集Id查找点赞类
     *
     * @param id 作品集Id
     * @return 返回点赞对象
     */
    WorkPraise findWorkPraisesById(Long id);

    /**
     * 通过workId获得点赞数
     *
     * @param workId 传入作品集Id
     * @return 点赞数
     */
    Integer findWorkNumById(Long workId);

    /**
     * 通过workId改变点赞数
     *
     * @param workId 传入作品集Id
     */
    @Transactional(rollbackFor = Exception.class)
    void updateWorkNumById(Long workId);

    /**
     * 根据用户id查询用户所有作品集点赞信息
     *
     * @param userId 用户id
     * @return 点赞信息列表 map (userWorkId -> workPraise)
     */
    Map<Long, WorkPraise> listWorkPraiseByUserId(Long userId);
}
