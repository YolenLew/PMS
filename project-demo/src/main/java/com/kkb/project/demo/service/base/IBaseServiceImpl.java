package com.kkb.project.demo.service.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 基础 IBaseService 实现类-暂时不要用
 * @author: LZH
 * @date: 2021/04/26
 */
public class IBaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<BaseMapper<T>, T> implements IBaseService<T> {


}
