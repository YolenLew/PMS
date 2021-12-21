package com.kkb.project.portal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.portal.dao.ParticipantCommentDao;
import com.kkb.project.portal.domain.ParticipantComment;
import com.kkb.project.portal.service.ParticipantCommentService;
import org.springframework.stereotype.Service;

/**
 * @author __SAD_DOG__
 * @date 2021-04-23
 */
@Service
public class ParticipantCommentServiceImpl extends ServiceImpl<ParticipantCommentDao, ParticipantComment> implements ParticipantCommentService {
    /**
     * 插入一条学员评论, 插入失败则抛出统一异常
     *
     * @param comment 评论
     */
    @Override
    public void insertComment(ParticipantComment comment) {
        boolean flag = this.save(comment);
        if (!flag){
            Asserts.fail("插入学员评论失败");
        }
    }
}
