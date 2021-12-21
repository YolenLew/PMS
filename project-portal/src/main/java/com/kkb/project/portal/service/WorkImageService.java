package com.kkb.project.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.portal.domain.WorkImage;

import java.util.List;
import java.util.Map;

/**
 * @Author River
 * @Date 2021/4/19 0:08
 * @Description 作品图片 Service
 * @Version 1.0
 **/
public interface WorkImageService extends IService<WorkImage> {

    /**
     * 通过ID 查看作品详情
     * @Author Ljh
     * @Date 2021/4/19 22:00
     * @param id 作品集ID
     * @return 返回作品集图片集合
     */
    List<WorkImage> findUserWorkImageById(Long id);


    /**
     * 通过作品集id和用户ID来查找这个用户的作品图片
     *
     * @param id     作品集id
     * @param userId 用户Id
     * @return 返回图片集合对象
     */
    List<WorkImage> findImagesByWorksId(Long id, Long userId);

    /**
     * 通过用户id查询用户的所有作品图片,以作品id分组
     *
     * @param userId 用户Id
     * @return 作品图片信息 map (userWorkId -> workImageList)
     */
    Map<Long, List<WorkImage>> listImagesByUserId(Long userId);
}
