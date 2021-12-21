package com.kkb.project.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.admin.dao.CarouselImageGroupMapper;
import com.kkb.project.admin.domain.CarouselImageGroup;
import com.kkb.project.admin.service.CarouselService;
import com.kkb.project.common.exception.Asserts;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @Author 李梓豪
 * @Description 轮播图 service 实现类
 * @Date 2021年04月22日  19:04:14
 * @Param * @param null
 * @return
 * @Date Modify in 2021年04月22日  19:04:14
 * @Modify Content:  把lzx的service代码修改成规范的代码
 **/
@Service
public class CarouselServiceImpl extends ServiceImpl<CarouselImageGroupMapper, CarouselImageGroup> implements CarouselService {


    /**
     * 新建一个轮播栏位上传
     *
     * @param cig 轮播图对象
     */
    @Override
    public void addCarousel(CarouselImageGroup cig) {
        boolean saveOrUpdate = this.saveOrUpdate(cig);
        if (!saveOrUpdate) {
            Asserts.fail("新增失败");
        }
    }

    /**
     * 轮播图分组上传
     *
     * @param imageUri 图片位置
     * @param id       栏位id
     */
    @Override
    public void uploadImage(String imageUri, Long id) {
        CarouselImageGroup cig = new CarouselImageGroup();
        cig.setId(id);
        cig.setImageUri(imageUri);
        // TODO: 2021/4/22 0022 待测试是否跟新增栏位有冲突
        boolean saveOrUpdate = this.saveOrUpdate(cig);
        if (!saveOrUpdate) {
            Asserts.fail("图片上传失败");
        }
    }

    /**
     * 根据分组，查询该组图片信息
     *
     * @param imageGroup 图片分组
     * @return 返回该组轮播图图片列表
     */
    @Override
    public List<CarouselImageGroup> findImageByGroupId(Integer imageGroup) {
        QueryWrapper<CarouselImageGroup> wrapper = new QueryWrapper<CarouselImageGroup>()
                .eq("image_group", imageGroup)
                .eq("is_deleted", 0);
        List<CarouselImageGroup> imageList = this.list(wrapper);
        if (imageList.size() <= 0) {
            Asserts.fail("找不到图片");
        }
        return imageList;
    }

    /**
     * 根据图片id，删除图片
     *
     * @param id 图片id
     */
    @Override
    public void deleteById(Long id) {
        CarouselImageGroup cig = new CarouselImageGroup();
        cig.setId(id);
        cig.setIsDeleted((byte) 1);
        boolean updateById = this.updateById(cig);
        if (!updateById) {
            Asserts.fail("删除失败,请重试");
        }
    }
}
