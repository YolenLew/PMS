package com.kkb.project.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.auth.domain.Resource;

import java.util.List;

/**
 * @author lemon
 * @version 1.0
 * @description ResourceService
 * @date 2021/04/27
 */
public interface ResourceService extends IService<Resource> {

    /**
     * 获取所有的资源信息
     * @return
     */
    List<Resource> getResourceList();
}
