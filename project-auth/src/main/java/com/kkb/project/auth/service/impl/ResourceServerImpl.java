package com.kkb.project.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.auth.dao.ResourceMapper;
import com.kkb.project.auth.domain.Resource;
import com.kkb.project.auth.service.ResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lemon
 * @version 1.0
 * @description 资源接口实现类
 * @date 2021/04/27
 */
@Service
public class ResourceServerImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    /**
     * 获取所有的资源列表
     * @return
     */
    @Override
    public List<Resource> getResourceList() {
        return this.list();
    }
}
