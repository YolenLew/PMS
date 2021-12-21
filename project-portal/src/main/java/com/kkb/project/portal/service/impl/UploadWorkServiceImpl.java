package com.kkb.project.portal.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.portal.dao.UserWorkMapper;
import com.kkb.project.portal.dao.WorkDescriptionMapper;
import com.kkb.project.portal.dao.WorkImageMapper;
import com.kkb.project.portal.domain.vo.ListWorkImageVo;
import com.kkb.project.portal.domain.UserWork;
import com.kkb.project.portal.domain.WorkDescription;
import com.kkb.project.portal.domain.WorkImage;
import com.kkb.project.portal.service.UploadWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author River
 * @Date 2021/4/19 23:04
 * @Description 作品上传实现类
 * @Version 1.0
 **/
@Service
public class UploadWorkServiceImpl extends ServiceImpl<WorkImageMapper, WorkImage> implements UploadWorkService {


    @Autowired
    private UserWorkMapper userWorkMapper;
    @Autowired
    private WorkDescriptionMapper workDescriptionMapper;


    /**
     * 作品上传
     *
     * @param userWork        作品集对象
     * @param workDescription 项目描述对象
     * @param typeId          作品类型ID
     * @param userId          用户ID
     * @param listWorkImageVo   图片集合对象
     */
    @Override
    public void uploadWork(Long userId, UserWork userWork, ListWorkImageVo listWorkImageVo, WorkDescription workDescription, Long typeId) {
        if (ObjectUtil.isNull(userId)) {
            Asserts.fail("请登录");
        }
        if (ObjectUtil.isNull(typeId)) {
            Asserts.fail("请选择作品类型");
        }
        userWork.setTypeId(typeId);
        userWork.setUserId(userId);
        // 新增排序字段需要设置为每次递增
        Integer count = userWorkMapper.selectCount(new QueryWrapper<UserWork>().eq("is_deleted", 0));
        userWork.setSequence(count + 1);
        int insertUserWork = userWorkMapper.insert(userWork);
        // 获取新增的作品集的id
        UserWork selectUserWorkById = userWorkMapper.selectById(userWork.getId());
        if (insertUserWork == 0 && ObjectUtil.isNull(selectUserWorkById)) {
            Asserts.fail("请输入项目标题");
        }
        Long userWorkId = selectUserWorkById.getId();
        workDescription.setUserWorkId(userWorkId);
        workDescription.setUserId(userId);
        int insertWorkDescription = workDescriptionMapper.insert(workDescription);
        if (insertWorkDescription == 0) {
            Asserts.fail("请输入作品描述");
        }
        List<WorkImage> workImages = listWorkImageVo.getWorkImages();
        for (WorkImage workImage : workImages) {
            workImage.setUserWorkId(userWorkId);
            workImage.setUserId(userId);
        }
        boolean saveBatch = this.saveBatch(workImages);
        if (!saveBatch) {
            Asserts.fail("作品上传失败");
        }
    }
}
