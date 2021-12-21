package com.kkb.project.portal.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.kkb.project.portal.domain.constant.DeletedStatus;
import com.kkb.project.portal.domain.constraint.HasProjectId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @Author ynb
 * @Date 2021/4/20
 * @Description 导师指导实体类
 **/
@Data
@TableName( "leader_guidance" )
@ApiModel(value = "LeaderGuidance对象",description = "导师指导表")
public class LeaderGuidance  implements HasProjectId, Serializable {
	private static final long serialVersionUID =  7213801948814726405L;

	/**
	 * 乐观锁
	 */
	@ApiModelProperty(value = "乐观锁")
	@TableField(value = "revision" )
	private Long revision = 0L;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	@TableField(value = "create_time" )
	private Date createTime = new Date();

	/**
	 * 更新人
	 */
	@ApiModelProperty(value = "创建人")
	@TableField(value = "update_user" )
	private Long updateUser;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "创建时间")
	@TableField(value = "update_time" )
	private Date updateTime = new Date();

	/**
	 * 指导ID
	 */
	@ApiModelProperty(value = "主键ID")
	@TableId
	private Long id;

	/**
	 * 导师ID
	 */
	@ApiModelProperty(value = "导师ID")
	@TableField(value = "leader_id" )
	private Long leaderId;

	/**
	 * 项目ID
	 */
	@ApiModelProperty(value = "项目ID")
	@TableField(value = "project_id" )
	private Long projectId ;

	/**
	 * 项目状态
	 */
	@ApiModelProperty(value = "项目状态")
	@TableField(value = "project_status" )
	private Integer projectStatus;

	/**
	 * 是否已经删除
	 */
	@ApiModelProperty(value = "是否删除")
	@TableField(value = "is_deleted" )
	private Integer isDeleted = DeletedStatus.NOT_DELETED.ordinal();

	/**
	 * LeaderGuidance全参构造
	 * @param revision
	 * @param createTime
	 * @param updateUser
	 * @param updateTime
	 * @param id
	 * @param leaderId
	 * @param projectId
	 * @param projectStatus
	 * @param isDeleted
	 */
	public LeaderGuidance(Long revision, Date createTime, Long updateUser, Date updateTime, Long id, Long leaderId, Long projectId, Integer projectStatus, Integer isDeleted) {
		this.revision = revision;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.id = id;
		this.leaderId = leaderId;
		this.projectId = projectId;
		this.projectStatus = projectStatus;
		this.isDeleted = isDeleted;
	}

	/**
	 * LeaderGuidance 无参构造
	 */
	public LeaderGuidance() {
	}
}

