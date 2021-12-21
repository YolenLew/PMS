package com.kkb.project.portal.domain;

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
 * @Author River
 * @Date 2021/4/19 0:08
 * @Description 作品类型实体类
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
