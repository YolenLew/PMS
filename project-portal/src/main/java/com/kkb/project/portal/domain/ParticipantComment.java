package com.kkb.project.portal.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kkb.project.portal.domain.constraint.HasProjectId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author __SAD_DOG__
 * @date 2021-04-23
 */
@Data
@TableName("student_comment")
@ApiModel(value = "StudentComment对象",description = "学生评论表")
public class ParticipantComment implements HasProjectId {

    @ApiModelProperty("id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("student_id")
    private Long studentId;

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

    @ApiModelProperty("operational")
    private Integer operational;

    @ApiModelProperty("understand")
    private Integer understand;

    @ApiModelProperty("avg_score")
    private Double avgScore;

    @ApiModelProperty("create_time")
    private Date createTime;

    @ApiModelProperty("update_time")
    private Date updateTime;

    @ApiModelProperty("revision")
    private Integer revision;

    public ParticipantComment(
            Long studentId, Long projectId, String comment,
            Long commentFrom, Integer isAnonymous, Integer professional,
            Integer operational, Integer understand
    ) {
        this.studentId = studentId;
        this.projectId = projectId;
        this.comment = comment;
        this.commentFrom = commentFrom;
        this.isAnonymous = isAnonymous;
        this.professional = professional;
        this.operational = operational;
        this.understand = understand;

        // 默认值
        avgScore = (professional + operational + understand) / 3.;
        createTime = new Date();
        updateTime = createTime;
        revision = 0;
    }

    public ParticipantComment() {}
}
