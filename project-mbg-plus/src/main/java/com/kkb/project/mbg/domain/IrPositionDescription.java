package com.kkb.project.mbg.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 职位信息表
 * </p>
 *
 * @author ${author}
 * @since 2021-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="IrPositionDescription对象", description="职位信息表")
public class IrPositionDescription implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "内推表主键")
    private Long recommendId;

    @ApiModelProperty(value = "职业描述")
    private String positionDescription;

    @ApiModelProperty(value = "软删除字段 0代表未删除 1代表已删除")
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty(value = "乐观锁")
    private Integer revision;

    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新人")
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;


}
