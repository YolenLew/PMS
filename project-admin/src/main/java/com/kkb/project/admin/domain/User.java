package com.kkb.project.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author River
 * @Date 2021/4/19 0:08
 * @Description 用户表的 实体类
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "用户表实体类")
public class User implements Serializable {

    @ApiModelProperty(value = "用户ID ", hidden = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "姓名", required = true)
    @NotEmpty(message = "请输入姓名")
    private String name;

    @ApiModelProperty(value = "微信号码", required = true)
    @NotEmpty(message = "请输入微信号码")
    private String wxCard;

    @ApiModelProperty(value = "用户类型, 0 游客 默认值 ,1 导师 ,2 学生 ", required = true)
    private Byte type;

    @ApiModelProperty(value = "职业身份", required = true)
    private String title;

    @ApiModelProperty(value = "工作年限", required = true)
    private String workYear;

    @ApiModelProperty(value = "项目数量 ", hidden = true)
    private Integer projectNumber;

    @ApiModelProperty(value = "能力平均分数", hidden = true)
    private BigDecimal avgScore;

    @ApiModelProperty(value = "甲方评价", hidden = true)
    private BigDecimal avgScoreByA;

    @ApiModelProperty(value = "日薪", required = true)
    @NotEmpty(message = "请输入日薪")
    private BigDecimal dailySalary;

    @ApiModelProperty(value = "后台设置排名 ", hidden = true)
    @NotEmpty
    private Byte managerRank;

    @ApiModelProperty(value = "名次软删除 0代表未删除 1代表已删除 ")
    private Byte isDeleted;

    @ApiModelProperty(value = "头像图片地址", hidden = true)
    private String imageHeadUrl;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createdTime;

    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date updatedTime;

    @ApiModelProperty(value = "乐观锁", hidden = true)
    private Integer revision;

    @ApiModelProperty(value = "用户信息唯一标识")
    private Long loginId;

    private static final long serialVersionUID = 1L;

}
