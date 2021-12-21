package com.kkb.project.demo.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.demo.domain.WorkType;

import java.util.List;

/**
 * @ClassName DemoService
 * @Author LZH
 * @Date 2021/4/26
 * @Description TODO 演示Service接口，不需要了请删除
 * @Version 1.0
 **/
public interface DemoService extends IService<WorkType> {

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
     * @param pageNum 页数
     * @param pageSize 每页数量
     * @return 作品类型集合
     */
    Page<WorkType> pageFindWork(Integer pageNum, Integer pageSize);

    /**
     * 根据ID 删除作品类型
     *
     * @param id 作品类型id
     */
    void deleteWorkTypeById(Long id);

    /**
     * 根据ID 修改作品类型
     *
     * @param workType 作品类型对象
     */
    void updateWorkTypeById(WorkType workType);

}
