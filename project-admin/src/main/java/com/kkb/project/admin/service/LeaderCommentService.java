package com.kkb.project.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.admin.domain.LeaderComment;

/**
 * @author __SAD_DOG__
 * @date 2021-04-23
 * @updateBy 张观林
 */
public interface LeaderCommentService extends IService<LeaderComment> {
    /**
     * 查询好评率
     * @return 好评率
     */
    Double findCommentRate();
}
