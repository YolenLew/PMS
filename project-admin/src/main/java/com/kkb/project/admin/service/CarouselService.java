package com.kkb.project.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.admin.domain.CarouselImageGroup;

import java.util.List;

/**
 * @Author lzx
 * @Date 2021/4/18
 * @Description 轮播图 service接口
 */
public interface CarouselService extends IService<CarouselImageGroup> {

    /**
     * 新建一个轮播栏位上传
     *
     * @param cig 轮播图对象
     */
    void addCarousel(CarouselImageGroup cig);


    /**
     * 轮播图分组上传
     *
     * @param imageUri 图片位置
     * @param id       栏位id
     */
    void uploadImage(String imageUri, Long id);

    /**
     * 根据分组，查询该组图片信息
     *
     * @param imageGroup 图片分组
     * @return 返回该组轮播图列表
     */
    List<CarouselImageGroup> findImageByGroupId(Integer imageGroup);


    /**
     * 根据栏位id，删除图片
     *
     * @param id 栏位id
     */
    void deleteById(Long id);
}
