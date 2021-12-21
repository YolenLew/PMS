package com.kkb.project.admin.domain.vo;

import lombok.Data;

/**
 * @author 张观林
 * @Date 2021/4/29 14:41
 * 对优秀导师数目、服务企业总数目、好评率封装
 */
@Data
public class SomeNumVo {
    private Integer leaderNum;
    private Double commentRate;
    private Integer clientNum;

    public SomeNumVo(Integer leaderNum, Double commentRate, Integer clientNum) {
        this.leaderNum = leaderNum;
        this.commentRate = commentRate;
        this.clientNum = clientNum;
    }
}
