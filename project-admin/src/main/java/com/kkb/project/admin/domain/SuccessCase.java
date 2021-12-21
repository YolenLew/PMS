package com.kkb.project.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName SuccessCase
 * @Author River
 * @Date 2021/4/29 12:12
 * @Description 成功案例表实体类
 * @Version 1.0
 **/
@Data
@ApiModel(description = "成功案例表实体类")
public class SuccessCase {

    @ApiModelProperty(value = "主键id ")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "项目表id")
    @NotNull
    private Long projectId;

    @ApiModelProperty(value = "成功案例图片")
    @NotNull
    private String url;

    @ApiModelProperty(value = "点赞数量")
    private Byte praise;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createdTime;

    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date updatedTime;

    @ApiModelProperty(value = "乐观锁", hidden = true)
    private Integer revision;
}
