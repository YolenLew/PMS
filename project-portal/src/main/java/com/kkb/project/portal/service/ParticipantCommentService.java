package com.kkb.project.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kkb.project.portal.domain.ParticipantComment;

/**
 * @author __SAD_DOG__
 * @date 2021-04-23
 */
public interface ParticipantCommentService extends IService<ParticipantComment> {
    /**
     * 插入一条学员评论, 插入失败则抛出统一异常
     * @param comment 评论
     */
    void insertComment(ParticipantComment comment);
}
