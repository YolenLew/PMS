package com.kkb.project.portal.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author River
 * @Date 2021/4/19 13:09
 * @Description 用户技能表 的实体类
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UserSkill对象",description = "用户技能表")
public class UserSkill implements Serializable {
    @ApiModelProperty(value = "技能ID",hidden = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "技能描述",required = true)
    @NotNull(message = "请输入技能")
    private String skillDesc;

    @ApiModelProperty(value = "用户ID",hidden = true)
    private Long userId;

    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date createdTime;

    @ApiModelProperty(value = "更新时间",hidden = true)
    private Date updatedTime;

    @ApiModelProperty(value = "乐观锁",hidden = true)
    private Integer revision;

    private static final long serialVersionUID = 1L;

}
