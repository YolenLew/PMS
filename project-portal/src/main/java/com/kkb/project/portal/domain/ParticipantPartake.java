package com.kkb.project.portal.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kkb.project.portal.domain.constant.ProjectStatus;
import com.kkb.project.portal.domain.constraint.HasProjectId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author __SAD_DOG__
 */
@Data
@TableName("participant_partake")
@ApiModel(value = "ParticipantPartake对象", description = "参与者报名表")
public class ParticipantPartake implements HasProjectId {
    /**
     * / 乐观锁
     */
    @ApiModelProperty(value = "乐观锁")
    private Integer revision;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "创建人")
    private Long createUser;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "更新人")
    private Long updateUser;
    @ApiModelProperty(value = "主键ID")
    @TableId(type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "参与者ID")
    private Long participantId;
    @ApiModelProperty(value = "项目ID")
    private Long projectId;
    /**
     * 项目状态，默认是已发布
     */
    @ApiModelProperty(value = "报名状态")
    private Integer status = ProjectStatus.PUBLISHED.value();

    /**
     * 0表示未删除 1表示已删除 默认未删除
     */
    @ApiModelProperty(value = "是否删除")
    private Integer isDeleted = com.kkb.project.portal.domain.constant.DeletedStatus.NOT_DELETED.value();

    /**
     * 全参构造
     *
     * @param revision
     * @param createTime
     * @param createUser
     * @param updateTime
     * @param updateUser
     * @param id
     * @param participantId
     * @param projectId
     * @param status
     * @param isDeleted
     */
    public ParticipantPartake(Integer revision, Date createTime, Long createUser, Date updateTime, Long updateUser, Long id, Long participantId, Long projectId, Integer status, Integer isDeleted) {
        this.revision = revision;
        this.createTime = createTime;
        this.createUser = createUser;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.id = id;
        this.participantId = participantId;
        this.projectId = projectId;
        this.status = status;
        this.isDeleted = isDeleted;
    }

    /**
     * 无参构造
     */
    public ParticipantPartake() {
    }
}
