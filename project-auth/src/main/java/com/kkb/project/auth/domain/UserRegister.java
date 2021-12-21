package com.kkb.project.auth.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户注册信息实体
 *
 * @author  Yolen
 * @date  2021/4/29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UserRegister对象", description="用户注册信息表")
public class UserRegister implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "用户手机号")
    private String phone;

    @ApiModelProperty(value = "用户类型 0 为导师，1为学生")
    private Integer type;

    @ApiModelProperty(value = "软删除标记")
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "乐观锁")
    @Version
    private Integer revision;
}
