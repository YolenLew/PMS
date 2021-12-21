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
 * @Date 2021/4/19 0:08
 * @Description 作品项目描述表 的实体类
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "作品项目描述表实体类")
public class WorkDescription implements Serializable {

    @ApiModelProperty(value = "作品项目描述表ID", hidden = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "作品集ID ", hidden = true)
    private Long userWorkId;

    @ApiModelProperty(value = "用户ID ",hidden = true)
    private Long userId;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createdTime;

    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date updatedTime;

    @ApiModelProperty(value = "乐观锁", hidden = true)
    private Integer revision;

    @ApiModelProperty(value = "作品项目描述",required = true)
    @NotNull(message = "请输入项目描述")
    private String projectDesc;

    private static final long serialVersionUID = 1L;


}