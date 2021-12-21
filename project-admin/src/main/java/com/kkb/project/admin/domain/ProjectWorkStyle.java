package com.kkb.project.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 项目工作方式
 * 远程、本地
 * @author donkeyfly
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectWorkStyle implements Serializable {
    @ApiModelProperty(value = "工作方式ID ", required = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "工作方式", required = true)
    @NotEmpty(message = "工作方式不能为空")
    private String name;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间", hidden = true)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "乐观锁", hidden = true)
    private Integer revision;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("修改人")
    private String updateUser;

    @ApiModelProperty("软删除")
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}
