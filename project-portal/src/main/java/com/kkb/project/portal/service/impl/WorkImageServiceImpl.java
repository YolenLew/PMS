package com.kkb.project.portal.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.portal.dao.WorkImageMapper;
import com.kkb.project.portal.domain.WorkImage;
import com.kkb.project.portal.service.WorkImageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author River
 * @Date 2021/4/19 22:34
 * @Description 作品图片实现类
 * @Version 1.0
 **/
@Service
public class WorkImageServiceImpl extends ServiceImpl<WorkImageMapper, WorkImage> implements WorkImageService {
    /**
     * 通过ID 查看作品详情
     * @Author Ljh
     * @Date 2021/4/19 22:00
     * @param id 作品集ID
     * @return 返回作品集图片集合
     */
    @Override
    public List<WorkImage> findUserWorkImageById(Long id) {
        QueryWrapper<WorkImage> wrapper = new QueryWrapper<WorkImage>()
                .select("id", "img_url", "img_desc", "user_work_id")
                .eq("user_work_id", id);
        List<WorkImage> list = this.list(wrapper);
        if (ObjectUtil.isNull(list)) {
            Asserts.fail("查询图片集合失败");
        }
        return list;
    }

    /**
     * 通过图片id和用户id查找作品图片
     *
     * @param id     作品集Id
     * @param userId 用户Id
     * @return 图片集合
     */
    @Override
    public List<WorkImage> findImagesByWorksId(Long id, Long userId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_work_id", id);
        map.put("user_id", userId);
        List<WorkImage> userImages = this.listByMap(map);
        if (ObjectUtil.isNull(userImages)) {
            Asserts.fail("该用户放入没有作品图片");
        }
        return userImages;
    }

    /**
     * 通过用户id查询用户的所有作品图片,以作品id分组
     *
     * @param userId 用户Id
     * @return 以作品id分组的图片列表
     */
    @Override
    public Map<Long, List<WorkImage>> listImagesByUserId(Long userId) {
        List<WorkImage> workImages = this.list(new QueryWrapper<WorkImage>().eq("user_id", userId));
        Map<Long, List<WorkImage>> workImageMap= new HashMap<>();
        for (WorkImage workImage : workImages){
            boolean contains = workImageMap.containsKey(workImage.getUserWorkId());
            List<WorkImage> workImages1;
            if (contains){
                workImages1 = workImageMap.get(workImage.getUserWorkId());
                workImages1.add(workImage);
            } else {
                workImages1 = new ArrayList<>();
                workImages1.add(workImage);
                workImageMap.put(workImage.getUserId(),workImages1);
            }
        }
        return workImageMap;
    }
}
