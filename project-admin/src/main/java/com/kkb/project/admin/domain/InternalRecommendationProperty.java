package com.kkb.project.admin.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 内推信息属性实体类
 *
 * @author Yolen
 * @since 2021-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "InternalRecommendationProperty对象", description = "职位属性表")
public class InternalRecommendationProperty implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(message = "主键不能为空", groups = UpdateGroup.class)
    private Long id;

    @ApiModelProperty(value = "数据类型 0: 学历 ; 1: 工作年限;  2: 月薪;  3: 发薪月数")
    @NotNull(message = "类型值不能为空")
    private Integer type;

    @ApiModelProperty(value = "属性值")
    @NotBlank(message = "属性值不能为空")
    private String properties;

    @ApiModelProperty(value = "属性分类名")
    @NotBlank(message = "分类名不能为空")
    private String category;

    @ApiModelProperty(value = "软删除字段", hidden = true)
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty(value = "创建人", hidden = true)
    private String createdBy;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新人", hidden = true)
    private String updatedBy;

    @ApiModelProperty(value = "更新时间", hidden = true)
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "乐观锁", hidden = true)
    @Version
    private Integer revision;
}
