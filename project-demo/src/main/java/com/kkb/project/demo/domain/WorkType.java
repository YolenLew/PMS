package com.kkb.project.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName WorkType
 * @Author LZH
 * @Date 2021/4/26
 * @Description TODO 演示实体类，不需要了请删除
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "作品类型表实体类")
public class WorkType implements Serializable {

    @ApiModelProperty(value = "作品类型ID ", required = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "作品类型 ", required = true)
    @NotEmpty(message = "作品类型不能为空")
    private String type;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间", hidden = true)
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "乐观锁", hidden = true)
    private Integer revision;

    private static final long serialVersionUID = 1L;
}
