package com.kkb.project.portal.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.portal.dao.WorkPraiseMapper;
import com.kkb.project.portal.domain.WorkPraise;
import com.kkb.project.portal.service.WorkPraiseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @Author zhang guan lin
 * @Date 2021/4/20 21:47
 * @Description 点赞实现类
 * @Version 1.0
 **/
@Service
public class WorkPraiseServiceImpl extends ServiceImpl<WorkPraiseMapper, WorkPraise> implements WorkPraiseService {

    /**
     * 通过作品集Id查找点赞类
     * @Author Ljh
     * @Date 2021/4/19 22:00
     * @param id 作品集Id
     * @return 点赞类
     */
    @Override
    public WorkPraise findWorkPraisesById(Long id) {
        WorkPraise praise = this.getOne(new QueryWrapper<WorkPraise>()
                .eq("user_work_id", id)
                .select("praise_number", "id"));
        if (ObjectUtil.isNull(praise)) {
            WorkPraise workPraise = new WorkPraise();
            workPraise.setPraiseNumber(0);
            return workPraise;
        }
        return praise;
    }

    /**
     * 通过作品集Id查找点赞数
     *
     * @param workId 作品集id
     * @return 返回点赞数量
     */
    @Override
    public Integer findWorkNumById(Long workId) {
        WorkPraise praise = findWorkPraisesById(workId);
        Integer number = praise.getPraiseNumber();
        if (number == 0) {
            Asserts.fail("查找失败");
        }
        praise.setPraiseNumber(number);
        return number;
    }

    /**
     * 通过作品集Id更新点赞数
     *
     * @param workId 传入作品集id
     */
    @Override
    public void updateWorkNumById(Long workId) {
        WorkPraise praise = findWorkPraisesById(workId);
        Integer num = praise.getPraiseNumber() + 1;
        praise.setPraiseNumber(num);
        boolean update = this.updateById(praise);
        if (!update) {
            Asserts.fail("更新失败");
        }
    }

    /**
     * 根据用户id查询用户所有作品集点赞信息
     *
     * @param userId 用户id
     * @return 作品图片信息 map (userWorkId -> workImageList)
     */
    @Override
    public Map<Long, WorkPraise> listWorkPraiseByUserId(Long userId) {
        List<WorkPraise> workPraises = this.list(new QueryWrapper<WorkPraise>().eq("user_id", userId));
        Map<Long, WorkPraise> workPraiseMap = workPraises.stream().collect(Collectors.toMap(WorkPraise::getUserWorkId, wp -> wp));
        return workPraiseMap;
    }


}
