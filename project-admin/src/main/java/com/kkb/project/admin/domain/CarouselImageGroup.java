package com.kkb.project.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author 李梓豪
 * @Description imageGroup表示不同的组。 0：默认,1：首页,2：名人堂,3：项目大厅
 * @Date 2021年04月22日  18:04:59
 * @Date Modify in 2021年04月22日  18:04:59
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "轮播图表实体类")
public class CarouselImageGroup implements Serializable {

    @ApiModelProperty(value = "图片ID ", required = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "轮播图URI")
    private String imageUri;

    @ApiModelProperty(value = "轮播图片分组 ", required = true)
    @Max(value = 3, message = "不能大于3")
    @Min(value = 1, message = "不能小于1")
    private Integer imageGroup;

    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date createdTime;

    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date updatedTime;

    @ApiModelProperty(value = "是否已经删除 ", required = true)
    private Byte isDeleted;

    @ApiModelProperty(value = "乐观锁",hidden = true)
    private Integer revision;

    private static final long serialVersionUID = 1L;

}
