package com.kkb.project.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.admin.domain.SuccessCase;
import com.kkb.project.admin.domain.vo.SuccessCaseVo;
import com.kkb.project.common.api.CommonPage;

import java.util.List;

/**
 * @author River
 * @date 2021/4/22
 * @Description 成功案例表 Service
 * @Version 1.0
 */
public interface SuccessCaseService extends IService<SuccessCase> {


    /**
     * 查找成功案例表里面的所有 项目ID
     *
     * @param pageNum 当前页
     * @param pageSize 每页显示数量
     * @return 返回所有的项目id
     */
    List<SuccessCase> findProjectId(Integer pageNum, Integer pageSize);

    /**
     * 分页查找成功案例表里面的所有对象
     *
     * @param pageNum  当前页
     * @param pageSize 每页显示数量
     * @return 返回分页对象
     */
    CommonPage<SuccessCaseVo> findSuccessCase(Integer pageNum, Integer pageSize);


}
