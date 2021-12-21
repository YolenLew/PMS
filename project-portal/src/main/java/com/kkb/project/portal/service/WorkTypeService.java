package com.kkb.project.portal.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.portal.domain.WorkType;
import com.kkb.project.portal.service.base.IBaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author River
 * @Date 2021/4/19 0:08
 * @Description 作品类型 Service
 * @Version 1.0
 **/
public interface WorkTypeService extends IService<WorkType> {


    /**
     * 根据 ID 查询作品
     *
     * @param id id 作品类型id
     * @return 作品类型对象
     */
    WorkType findWorkTypeById(Long id);

    /**
     * 添加作品类型
     *
     * @param workType 作品类型对象
     */
    void insertWorkType(WorkType workType);


    /**
     * 查询所有作品类型
     *
     * @return 作品类型集合
     */
    List<WorkType> findAllWork();

    /**
     * 分页查询所有作品类型
     *
     * @param pageNum  页数
     * @param pageSize 每页数量
     * @return 作品类型集合
     */
    Page<WorkType> pageFindWork(Integer pageNum, Integer pageSize);

    /**
     * 根据ID 删除作品类型
     *
     * @param id 作品类型id
     */
    @Transactional(rollbackFor = Exception.class)
    void deleteWorkTypeById(Long id);

    /**
     * 根据ID 修改作品类型
     *
     * @param workType 作品类型对象
     */
    @Transactional(rollbackFor = Exception.class)
    void updateWorkTypeById(WorkType workType);

}
