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
 * @Description 导师报名实现类
 **/
@Data
@TableName("leader_signup" )
@ApiModel(value = "LeaderSignup对象",description = "导师报名表")
public class LeaderSignup  implements HasProjectId, Serializable {
	private static final long serialVersionUID =  984418688856468927L;

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
	@ApiModelProperty(value = "更新人")
	@TableField(value = "update_user" )
	private Long updateUser;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	@TableField(value = "updated_time" )
	private Date updatedTime = new Date();

	/**
	 * 导师报名ID
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
	private Long projectId;

	/**
	 * 导师报名状态 utiny int业务层解释
	 */
	@ApiModelProperty(value = "导师报名状态")
	@TableField(value = "status" )
	private Integer status = 0;

	/**
	 * 是否已经删除, 默认未删除
	 */
	@ApiModelProperty(value = "是否删除")
	@TableField(value = "is_deleted" )
	private Integer isDeleted = DeletedStatus.NOT_DELETED.ordinal();

	/**
	 * LeaderSignup全参构造函数
	 * @param revision
	 * @param createTime
	 * @param updateUser
	 * @param updatedTime
	 * @param id
	 * @param leaderId
	 * @param projectId
	 * @param status
	 * @param isDeleted
	 */
	public LeaderSignup(Long revision, Date createTime, Long updateUser, Date updatedTime, Long id, Long leaderId, Long projectId, Integer status, Integer isDeleted) {
		this.revision = revision;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updatedTime = updatedTime;
		this.id = id;
		this.leaderId = leaderId;
		this.projectId = projectId;
		this.status = status;
		this.isDeleted = isDeleted;
	}

	/**
	 * LeaderSignup无参构造
	 */
	public LeaderSignup() {
	}
}
