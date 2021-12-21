package com.kkb.project.admin.domain;

import com.kkb.project.admin.domain.constraint.HasProjectId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author __SAD_DOG__
 * @date 2021-04-23
 */
@Data
@ApiModel(value = "LeaderComment对象",description = "导师评论表")
public class LeaderComment implements HasProjectId {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("leader_id")
    private Long leaderId;

    @ApiModelProperty("project_id")
    private Long projectId;

    @ApiModelProperty("comment")
    private String comment;

    @ApiModelProperty("comment_from")
    private Long commentFrom;

    @ApiModelProperty("is_anonymous")
    private Integer isAnonymous;

    @ApiModelProperty("professional")
    private Integer professional;

    @ApiModelProperty("organizational")
    private Integer organizational;

    @ApiModelProperty("communication")
    private Integer communication;

    @ApiModelProperty("avg_score")
    private Double avgScore;

    @ApiModelProperty("create_time")
    private Date createTime;

    @ApiModelProperty("update_time")
    private Date updateTime;

    @ApiModelProperty("revision")
    private Integer revision;

    public LeaderComment(
            Long leaderId, Long projectId, String comment,
            Long commentFrom, Integer isAnonymous, Integer professional,
            Integer organizational, Integer communication
    ) {
        this.leaderId = leaderId;
        this.projectId = projectId;
        this.comment = comment;
        this.commentFrom = commentFrom;
        this.isAnonymous = isAnonymous;
        this.professional = professional;
        this.organizational = organizational;
        this.communication = communication;

        // 默认值
        avgScore = (professional + organizational + communication) / 3.;
        createTime = new Date();
        updateTime = createTime;
        revision = 0;
    }

    public LeaderComment() {}

}
