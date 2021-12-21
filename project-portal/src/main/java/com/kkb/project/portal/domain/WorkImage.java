package com.kkb.project.portal.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * @Author River
 * @Date 2021/4/19 0:08
 * @Description 作品图片表 的实体类
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "作品图片表实体类")
public class WorkImage implements Serializable {

    @ApiModelProperty(value = "图片ID", hidden = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "作品图片")
    private String imgUrl;

    @ApiModelProperty(value = "作品图片描述")
    private String imgDesc;

    @ApiModelProperty(value = "作品集ID", hidden = true)
    private Long userWorkId;

    @ApiModelProperty(value = "用户ID", hidden = true)
    private Long userId;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createdTime;

    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date updatedTime;

    @ApiModelProperty(value = "乐观锁", hidden = true)
    private Integer revision;

    private static final long serialVersionUID = 1L;

}