package com.kkb.project.portal.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.portal.dao.UserWorkMapper;
import com.kkb.project.portal.domain.UserWork;
import com.kkb.project.portal.domain.WorkPraise;
import com.kkb.project.portal.service.UserWorkService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName UserWorkServiceImpl
 * @Author River
 * @Date 2021/4/19 22:00
 * @Description 用户作品集实现类
 * @Version 1.0
 **/
@Service
public class UserWorkServiceImpl extends ServiceImpl<UserWorkMapper, UserWork> implements UserWorkService {

    /**
     * @Author Ljh
     * 根据id查找用户作品集
     * @Date 2021/4/19 22:00
     * @param id 传入作品集id
     * @return 返回作品集对象
     */
    @Override
    public UserWork findUserWorkById(Long id) {
        UserWork userWork = this.getOne(new QueryWrapper<UserWork>()
                .eq("id", id)
                .select("created_time", "id"));
        if (ObjectUtil.isNull(userWork)) {
            Asserts.fail("没有此作品");
        }
        return userWork;
    }

    /**
     * 根据用户id查找用户作品集
     *
     * @param id 传入用户id
     * @return 返回用户作品集
     */
    @Override
    public List<UserWork> findUserWorkByUserId(Long id) {
        List<UserWork> userWorks = this.list(new QueryWrapper<UserWork>().eq("user_id", id));
        if (ObjectUtil.isNull(userWorks)) {
            Asserts.fail("没有此作品集");
        }
        return userWorks;
    }

    /**
     * 添加用户作品集
     *
     * @param userWork 传入作品集参数对象
     */
    @Override
    public void insertUserWork(UserWork userWork) {
        boolean saveOrUpdate = this.saveOrUpdate(userWork);
        if (!saveOrUpdate) {
            Asserts.fail("新增作品失败");
        }
    }

    /**
     * 通过id查找创建时间
     *
     * @param id 参数 id
     * @return 返回时间
     */
    @Override
    public Date findWorkCreateTimeById(Long id) {
        UserWork userWork = findUserWorkById(id);
        Date createdTime = userWork.getCreatedTime();
        if (ObjectUtil.isNull(createdTime)) {
            Asserts.fail("没有这个创建时间");
        }
        return createdTime;
    }

    /**
     * 根据用户id，对用户作品进行排序
     *
     * @param userId 传入用户ID
     * @return 返回用户作品集
     */
    @Override
    public List<UserWork> sortUserWorkByUserId(Long userId) {
        List<UserWork> userWorks = list(new QueryWrapper<UserWork>()
                .eq("user_id", userId)
                .orderBy(true, false, "sequence"));
        if (userWorks.size() <= 0) {
            Asserts.fail("该用户没有作品");
        }
        return userWorks;
    }

    /**
     * 通过当前登录用户的id和作品类型id分页查找当前登录用户的作品列表
     *
     * @param userId    用户Id
     * @param typeId    作品类型Id
     * @param pageParam 分页参数
     * @return 作品分页列表
     */
    @Override
    public IPage<UserWork> findUserWorkByUserIdAndTypeId(Long userId, Long typeId, Page<UserWork> pageParam) {
        Page<UserWork> userWorkPage;
        QueryWrapper<UserWork> userWorkQueryWrapper = new QueryWrapper<UserWork>()
                .eq("user_id", userId)
                .eq("is_deleted", 0)
                .orderByDesc("sequence");
        if (typeId == 0) {
            userWorkPage = this.page(pageParam, userWorkQueryWrapper);
        } else {
            userWorkPage = this.page(pageParam, userWorkQueryWrapper.eq("type_id", typeId));
        }
        return userWorkPage;
    }

    /**
     * 通过id删除作品(逻辑删除)
     *
     * @param id 作品 id
     */
    @Override
    public void deleteUserWorkById(Long id) {
        boolean update = update(new UpdateWrapper<UserWork>().set("is_deleted", 1).eq("id", id));
        if (!update) {
            Asserts.fail("删除作品失败");
        }
    }
}
