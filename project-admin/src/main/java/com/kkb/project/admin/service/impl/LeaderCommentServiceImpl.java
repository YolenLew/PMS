package com.kkb.project.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kkb.project.admin.dao.LeaderCommentDao;
import com.kkb.project.admin.domain.LeaderComment;
import com.kkb.project.admin.service.LeaderCommentService;
import com.kkb.project.common.exception.Asserts;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张观林
 * @Date 2021/4/29 13:48
 */
@Service
public class LeaderCommentServiceImpl extends ServiceImpl<LeaderCommentDao, LeaderComment> implements LeaderCommentService {
    /**
     * 查询好评率
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
        if (commentRate == 0){
            Asserts.fail("目前没有人评价该导师");
        }
        return commentRate;
    }
}
