package com.kkb.project.portal.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.portal.dao.WorkTypeMapper;
import com.kkb.project.portal.domain.WorkType;

import com.kkb.project.portal.service.WorkTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author River
 * @Date 2021/4/16 0:08
 * @Description 作品类型接口实现类
 * @Version 1.0
 **/
@Service
public class WorkTypeServiceImpl extends ServiceImpl<WorkTypeMapper, WorkType> implements WorkTypeService {

    /**
     * 根据 ID 查询作品类型
     *
     * @return 返回作品对象
     */
    @Override
    public WorkType findWorkTypeById(Long id) {
        WorkType result = this.getById(id);
        if (ObjectUtil.isNull(result)) {
            Asserts.fail("没有此作品类型");
        }
        return result;
    }

    /**
     * 新增一个作品类型
     *
     * @param workType 新增对象
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
     * @return 作品集合
     */
    @Override
    public List<WorkType> findAllWork() {
        List<WorkType> list = this.list();
        return list;
    }

    /**
     * 查询所有作品类型
     *
     * @return 作品类型集合
     */
    @Override
    public Page<WorkType> pageFindWork(Integer pageNum, Integer pageSize) {
        Page<WorkType> page = this.page(new Page<>(pageNum, pageSize));
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
            Asserts.fail("删除失败");
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
            Asserts.fail("修改失败");
        }
    }
}
