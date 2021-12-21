package com.kkb.project.portal.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * @Author River
 * @Date 2021/4/19 13:09
 * @Description 作品集表 的实体类
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "作品集表实体类")
public class UserWork implements Serializable {

    @ApiModelProperty(value = "作品集表ID主键", hidden = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "作品项目标题", required = true)
    @NotEmpty(message = "请输入作品项目标题")
    private String title;

    @ApiModelProperty(value = "作品发布时间", hidden = true)
    private Date pushDate;

    @ApiModelProperty(value = "作品排序", hidden = true)
    private Integer sequence;

    @ApiModelProperty(value = "作品集类型ID", required = true)
    private Long typeId;

    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

    @ApiModelProperty(value = "软删除标记 0代表未删除 1代表已删除 ")
    private Byte isDeleted;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createdTime;

    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date updatedTime;

    @ApiModelProperty(value = "乐观锁", hidden = true)
    private Integer revision;

    @ApiModelProperty(value = "作品描述对象", hidden = true)
    @TableField(exist = false)
    private WorkDescription workDescription;

    @ApiModelProperty(value = "作品图片列表", hidden = true)
    @TableField(exist = false)
    private List<WorkImage> workImages;

    @ApiModelProperty(value = "作品点赞对象", hidden = true)
    @TableField(exist = false)
    private WorkPraise workPraise;

    private static final long serialVersionUID = 1L;


}