package com.kkb.project.portal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.portal.domain.UserWork;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * @Author River
 * @Date 2021/4/19 0:08
 * @Description 用户作品集 Service
 * @Version 1.0
 **/
public interface UserWorkService extends IService<UserWork> {


    /**
     * 根据id查找用户作品集
     *
     * @param id 传入作品集id
     * @return 返回作品集对象
     * @Date 2021/4/19 22:00
     */
    UserWork findUserWorkById(Long id);

    /**
     * 根据用户id查找用户作品集
     *
     * @param id 传入用户id
     * @return 用户作品集
     */
    List<UserWork> findUserWorkByUserId(Long id);

    /**
     * 添加用户作品集
     *
     * @param userWork 传入作品集参数对象
     */
    void insertUserWork(UserWork userWork);

    /**
     * 通过id查找创建时间
     *
     * @param id id
     * @return 返回时间
     */
    Date findWorkCreateTimeById(Long id);

    /**
     * 通过userId查找作品集并根据sequence排序
     *
     * @param userId 用户Id
     * @return 作品集对象
     */
    List<UserWork> sortUserWorkByUserId(Long userId);

    /**
     * 通过当前登录用户的id和作品类型id分页查找当前登录用户的作品列表
     *
     * @param userId    用户Id
     * @param typeId    作品类型Id
     * @param pageParam 分页参数
     * @return 作品分页列表
     */
    IPage<UserWork> findUserWorkByUserIdAndTypeId(Long userId, Long typeId, Page<UserWork> pageParam);

    /**
     * 通过id删除作品(逻辑删除)
     *
     * @param id 作品 id
     */
    @Transactional(rollbackFor = Exception.class)
    void deleteUserWorkById(Long id);
}
