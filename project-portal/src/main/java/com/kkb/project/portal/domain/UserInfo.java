package com.kkb.project.portal.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户个人信息封装实体
 * @author Yolen
 * @date 2021/4/29
 */
@Data
@ApiModel(value="UserInfo", description="用户个人信息封装实体")
public class UserInfo {

    @ApiModelProperty(value = "用户标识")
    private Long loginId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "微信号码")
    private String wxCard;

    @ApiModelProperty(value = "用户手机号")
    private String phone;

    @ApiModelProperty(value = "头像路径")
    private String imageHeadUrl;

    @ApiModelProperty(value = "擅长技能")
    private List<String> skillList;

    @ApiModelProperty(value = "项目经验")
    private List<String> experienceList;

    @ApiModelProperty(value = "用户类型 0 为导师，1为学生")
    private Integer type;
}
