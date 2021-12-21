package com.kkb.project.admin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.admin.dao.SuccessCaseMapper;
import com.kkb.project.admin.domain.SuccessCase;
import com.kkb.project.admin.domain.vo.ProjectVo;
import com.kkb.project.admin.domain.vo.SuccessCaseVo;
import com.kkb.project.admin.service.ProjectVoFacadeService;
import com.kkb.project.admin.service.SuccessCaseService;
import com.kkb.project.admin.service.util.PageUtil;
import com.kkb.project.admin.service.util.VoWrapper;
import com.kkb.project.common.api.CommonPage;
import com.kkb.project.common.exception.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName SuccessCaseServiceImpl
 * @Author River
 * @Date 2021/4/29 15:56
 * @Description 成功案例 Service 实现类
 * @Version 1.0
 **/
@Service
public class SuccessCaseServiceImpl extends ServiceImpl<SuccessCaseMapper, SuccessCase> implements SuccessCaseService {


    @Autowired
    private ProjectVoFacadeService projectResFacadeService;

    final String PROJECT_ID = "project_id";
    final String PRAISE = "praise";

    /**
     * 查找Success case表的中指定分页数查找所有的项目id
     *
     * @param pageNum 当前页
     * @param pageSize 每页显示数量
     * @return 返回success case集合对象
     */
    @Override
    public List<SuccessCase> findProjectId(Integer pageNum, Integer pageSize) {
        Page<SuccessCase> pageParam = new Page<>(pageNum, pageSize);
        Page<SuccessCase> page = this.page(pageParam, new QueryWrapper<SuccessCase>().select(PROJECT_ID).orderByAsc(PRAISE));
        if (page.getTotal() == 0) {
            Asserts.fail("没有项目");
        }
        return page.getRecords();
    }


    /**
     * 分页查找成功案例表里面的所有对象
     *
     * @param pageNum  当前页
     * @param pageSize 每页显示数量
     * @return 返回分页对象
     */
    @Override
    public CommonPage<SuccessCaseVo> findSuccessCase(Integer pageNum, Integer pageSize) {
        List<SuccessCase> projectIdList = findProjectId(pageNum, pageSize);
        List<Long> ids = projectIdList.stream().map(SuccessCase::getProjectId).collect(Collectors.toList());
        List<SuccessCase> successCases = listByIds(ids);
        Map<Long, ProjectVo> projectIdMap = projectResFacadeService.getProjectIdMapByProjectIds(ids);
        System.out.println(projectIdMap+ "++++++++++++++++++++++++++projectIdMap++projectIdMap+++projectIdMap++++++++++++++++++++");
        List<SuccessCaseVo> successCaseVos = VoWrapper.toListSuccessCaseVo(successCases, projectIdMap);
        if (ObjectUtil.isNull(successCaseVos)){
            Asserts.fail("项目不存在");
        }
        Page<SuccessCase> pageParam = new Page<>(pageNum, ids.size());
        return PageUtil.wrapToCommonPage(successCaseVos, pageParam);
    }
}
