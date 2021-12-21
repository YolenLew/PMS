package com.kkb.project.portal.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kkb.project.portal.domain.constant.ParticipantSignUpStatus;
import com.kkb.project.portal.domain.constraint.HasProjectId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author __SAD_DOG__
 */
@Data
@TableName("participant_signup")
@ApiModel(value = "ParticipantSignup对象",description = "参与者参与表")
public class ParticipantSignup implements HasProjectId {
    /**
     * / 乐观锁
     */
    @ApiModelProperty(value = "乐观锁")
    private Integer revision;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "创建人")
    private Long updateUser;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "主键ID")
    @TableId(type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "参与者ID")
    private Long participantId;
    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    /**
     * 参与者报名状态  默认已经报名
     */
    @ApiModelProperty(value = "报名状态")
    private Integer status = ParticipantSignUpStatus.SIGNED_UP.value();

    /**
     * 0表示未删除 1表示已删除 默认未删除
     */
    @ApiModelProperty(value = "是否删除")
    private Integer isDeleted = com.kkb.project.portal.domain.constant.DeletedStatus.NOT_DELETED.value();

    /**
     * 无参构造函数
     */
    public ParticipantSignup() {
    }

    /**
     * 全参构造函数
     */
    public ParticipantSignup(Integer revision, Date createTime, Long updateUser, Date updateTime, Long id, Long participantId, Long projectId, Integer status, Integer isDeleted) {
        this.revision = revision;
        this.createTime = createTime;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.id = id;
        this.participantId = participantId;
        this.projectId = projectId;
        this.status = status;
        this.isDeleted = isDeleted;
    }
}