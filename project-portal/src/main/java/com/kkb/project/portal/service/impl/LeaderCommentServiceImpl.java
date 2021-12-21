package com.kkb.project.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.common.exception.Asserts;
import com.kkb.project.portal.dao.LeaderCommentDao;
import com.kkb.project.portal.domain.LeaderComment;
import com.kkb.project.portal.service.LeaderCommentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author __SAD_DOG__
 * @date 2021-04-23
 */
@Service
public class LeaderCommentServiceImpl extends ServiceImpl<LeaderCommentDao, LeaderComment> implements LeaderCommentService {
    /**
     * 插入一条导师评论, 若插入失败则抛出统一异常
     *
     * @param comment 评论
     */
    @Override
    public void insertComment(LeaderComment comment) {
        boolean flag = this.save(comment);
        if (!flag) {
            Asserts.fail("插入评论失败");
        }
    }

    /**
     * 查询好评率
     *
     * @return 好评率
     */
    @Override
    public Double findCommentRate() {
        List<LeaderComment> leaderComments = this.list(new QueryWrapper<LeaderComment>().select("avg_score"));
        Double sum = 0.0;
        Integer count = this.count();
        for (LeaderComment leaderComment : leaderComments) {
            Double avgScore = leaderComment.getAvgScore();
            sum = sum + avgScore;
        }
        Double commentRate = sum / count / 5;
        if (commentRate == 0) {
            Asserts.fail("目前没有人评价该导师");
        }
        return commentRate;
    }
}
