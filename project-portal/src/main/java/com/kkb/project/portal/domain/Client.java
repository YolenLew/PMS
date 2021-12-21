package com.kkb.project.portal.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.kkb.project.portal.domain.constant.ClientStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author __SAD_DOG__
 * @date 2021-04-16
 * Client entity 类
 */
@Data
@ApiModel(value = "Client对象",description = "委托方表")
public class Client {
    /**
     *  乐观锁
     */
    @ApiModelProperty(value = "乐观锁")
    private Integer revision;
    @ApiModelProperty(value = "创建人")
    private String createUser;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新人")
    private String updateUser;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "委托方ID")
    @TableId(type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "委托方名称")
    private String name;
    @ApiModelProperty(value = "委托方头像uri")
    private String headImageUri;
    /**
     * 委托方状态, 是否通过认证
     */
    @ApiModelProperty(value = "委托方状态")
    private Integer status;
    @ApiModelProperty(value = "是否删除")
    private Integer isDeleted;


    /**
     * Client最小需求构造函数
     * revision默认设置为0
     * createTime默认为当前时间
     * updateUser默认和createUser相同
     * updateTime默认是创建时间
     * 默认是未认证状态
     * @param createUser 创建人, 应该是后台管理员姓名或用户名
     * @param name 委托方名, 不可与数据库中其他委托方重名
     * @param headImageUri 委托方头像的uri地址
     */
    public Client(String createUser, String name, String headImageUri) {
        this.createUser = createUser;
        this.name = name;
        this.headImageUri = headImageUri;

        // 默认值
        revision = 0;
        createTime = new Date();
        // 创建的时候create user 和 update user 相同
        updateUser = createUser;
        updateTime = this.createTime;
        // 默认未认证
        status = ClientStatus.NOT_CERTIFICATE.value();
        // 默认未删除
        isDeleted = com.kkb.project.portal.domain.constant.DeletedStatus.NOT_DELETED.value();
    }
}

