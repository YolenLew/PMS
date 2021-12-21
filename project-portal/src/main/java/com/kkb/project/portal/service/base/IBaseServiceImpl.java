package com.kkb.project.portal.service.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 基础 IBaseService 实现类-暂时不要用
 * @author: peng.ni
 * @date: 2021/04/18
 */
public class IBaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<BaseMapper<T>, T> implements IBaseService<T> {


}
