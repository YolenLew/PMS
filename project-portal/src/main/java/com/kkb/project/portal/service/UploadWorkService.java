package com.kkb.project.portal.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.portal.domain.*;
import com.kkb.project.portal.domain.vo.ListWorkImageVo;


/**
 * @Author River
 * @Date 2021/4/19 0:08
 * @Description 作品上传 Service
 * @Version 1.0
 **/
public interface UploadWorkService extends IService<WorkImage> {

    /**
     * 作品上传
     *
     * @param userWork        作品集对象
     * @param workDescription 项目描述对象
     * @param typeId          作品类型ID
     * @param userId          用户ID
     * @param listWorkImageVo   作品图片对象集合
     */
    void uploadWork(Long userId, UserWork userWork, ListWorkImageVo listWorkImageVo, WorkDescription workDescription, Long typeId);

}
