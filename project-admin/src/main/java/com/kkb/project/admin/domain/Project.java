package com.kkb.project.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.kkb.project.admin.domain.constant.DeletedStatus;
import com.kkb.project.admin.domain.constant.ProjectStatus;
import com.kkb.project.admin.domain.constraint.HasProjectId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author __SAD_DOG__
 * @date 2021-04-16
 * Project entity 类
 */
@Data
@ApiModel(value = "Project对象",description = "项目表")
public class Project implements HasProjectId {
    /**
     * 乐观锁
     */
    @ApiModelProperty(value = "乐观锁")
    private Integer revision;
    @ApiModelProperty(value = "创建人")
    private String createUser;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新人")
    private String updateUser;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "项目ID")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 项目名
     */
    @ApiModelProperty(value = "项目名")
    private String title;
    /**
     * 项目报名导师人数
     */
    @ApiModelProperty(value = "导师报名人数")
    private Integer signupLeaderNum;
    /**
     * 项目报名学生人数
     */
    @ApiModelProperty(value = "学生报名人数")
    private Integer signupParticipantNum;
    /**
     * 发布项目的委托方Id
     */
    @ApiModelProperty(value = "委托方ID")
    private Long clientId;
    /**
     * 项目发布时间
     */
    @ApiModelProperty(value = "项目发布时间")
    private Date publishDatetime;
    /**
     * 项目报名截至日期
     */
    @ApiModelProperty(value = "报名截止时间")
    private Date signupDeadline;
    /**
     * 项目需求描述
     */
    @ApiModelProperty(value = "项目需求描述")
    private String demandDescription;
    /**
     * 项目工作方式Id
     */
    @ApiModelProperty(value = "工作方式")
    private Long workStyleId;
    /**
     * 项目预计持续时间(天)
     */
    @ApiModelProperty(value = "预计工期")
    private Integer predictedDuration;
    /**
     * 项目开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startDatetime;
    /**
     * 项目状态 报名中 进行中 结束了 取消了
     */
    @ApiModelProperty(value = "项目状态")
    private Integer status;
    @ApiModelProperty(value = "是否删除")
    private Integer isDeleted;
    /**
     * 项目类型Id
     */
    @ApiModelProperty(value = "项目类型")
    private Long typeId;
    /**
     * 项目标签
     */
    @ApiModelProperty(value = "项目标签")
    private String tags;

    /**
     * 委托方名
     */
    @ApiModelProperty(value = "委托方名")
    @TableField(exist = false)
    private String clientName;

    /**
     * 已经参加该项目的学员数
     */
    @ApiModelProperty("参加学员数")
    private Integer partakeParticipantNum;

    @Override
    public Long getProjectId() {
        return this.id;
    }

    /**
     * 空参构造器
     */
    public Project() {}

    /**
     * Project 最小需求构造函数
     * @param createUser 创建人, 应该是管理员姓名
     * @param title 项目名
     * @param clientId 项目委托方的Id
     * @param signupDeadline 项目报名截止日期
     * @param demandDescription 项目需求描述
     * @param workStyleId 项目工作方式
     * @param predictedDuration 项目预计时长
     * @param startDatetime 项目开始时间
     * @param type 项目类型
     */
    public Project(
            String createUser, String title, long clientId,
            Date signupDeadline, String demandDescription,
            Long workStyleId, int predictedDuration, Date startDatetime, Long type, String clientName
    ) {
        this.createUser = createUser;
        this.title = title;
        this.clientId = clientId;
        this.signupDeadline = signupDeadline;
        this.demandDescription = demandDescription;
        this.workStyleId = workStyleId;
        this.predictedDuration = predictedDuration;
        this.startDatetime = startDatetime;
        this.typeId = type;
        this.clientName = clientName;
        // 默认值
        revision = 0;
        signupLeaderNum = 0;
        signupParticipantNum = 0;
        publishDatetime = new Date();
        status = ProjectStatus.PUBLISHED.value();
        isDeleted = DeletedStatus.NOT_DELETED.value();
    }
}

