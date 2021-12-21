package com.kkb.project.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kkb.project.admin.domain.Client;
import org.springframework.stereotype.Repository;

/**
 * @Author  __SAD_DOG__
 * @Date 2021/4/16 0:08
 * @Description 操作委托方表 Mapper接口
 * @Version 1.0
 **/
@Repository
public interface ClientDao extends BaseMapper<Client> {
}
