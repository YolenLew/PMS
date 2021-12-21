package com.kkb.project.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.portal.domain.LeaderComment;

/**
 * @author __SAD_DOG__
 * @date 2021-04-23
 */
public interface LeaderCommentService extends IService<LeaderComment> {
    /**
     * 插入一条导师评论, 若插入失败则抛出统一异常
     * @param comment 评论
     */
    void insertComment(LeaderComment comment);

    /**
     * 查询好评率
     * @return 好评率
     */
    Double findCommentRate();
}
