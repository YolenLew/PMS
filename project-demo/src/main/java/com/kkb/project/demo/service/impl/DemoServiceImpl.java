package com.kkb.project.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.demo.dao.DemoDao;
import com.kkb.project.demo.domain.WorkType;
import com.kkb.project.demo.service.DemoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName DemoServiceImpl
 * @Author LZH
 * @Date 2021/4/26
 * @Description TODO 演示实现类，不需要了请删除
 * @Version 1.0
 **/
@Service
public class DemoServiceImpl extends ServiceImpl<DemoDao, WorkType> implements DemoService {

    /**
     * 根据 ID 查询作品
     *
     * @param id id 作品类型id
     * @return 作品类型对象
     */
    @Override
    public WorkType findWorkTypeById(Long id) {
        WorkType workType = this.getById(id);
        if (workType == null) {
            Asserts.fail("作品不存在");
        }
        return workType;
    }

    /**
     * 添加作品类型
     *
     * @param workType 作品类型对象
     */
    @Override
    public void insertWorkType(WorkType workType) {
        boolean saveOrUpdate = this.saveOrUpdate(workType);
        if (!saveOrUpdate) {
            Asserts.fail("新增失败");
        }
    }

    /**
     * 查询所有作品类型
     *
     * @return 作品类型集合
     */
    @Override
    public List<WorkType> findAllWork() {
        List<WorkType> list = this.list();
        if (list.size() == 0) {
            Asserts.fail("没有作品类型");
        }
        return list;
    }

    /**
     * 分页查询所有作品类型
     *
     * @param pageNum  页数
     * @param pageSize 每页数量
     * @return 作品类型集合
     */
    @Override
    public Page<WorkType> pageFindWork(Integer pageNum, Integer pageSize) {
        Page<WorkType> page = this.page(new Page<>(pageNum, pageSize));
        if (page == null) {
            Asserts.fail("查询失败,请重试");
        }
        return page;
    }

    /**
     * 根据ID 删除作品类型
     *
     * @param id 作品类型id
     */
    @Override
    public void deleteWorkTypeById(Long id) {
        boolean removeById = this.removeById(id);
        if (!removeById) {
            Asserts.fail("删除失败，请重试");
        }
    }

    /**
     * 根据ID 修改作品类型
     *
     * @param workType 作品类型对象
     */
    @Override
    public void updateWorkTypeById(WorkType workType) {
        boolean updateById = this.updateById(workType);
        if (!updateById) {
            Asserts.fail("修改失败，请重试");
        }
    }


}
