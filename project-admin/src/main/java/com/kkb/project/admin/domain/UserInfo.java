package com.kkb.project.admin.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * 用户个人信息封装实体
 * @author Yolen
 * @date 2021/4/29
 */
@Data
@ApiModel(value="UserInfo", description="用户个人信息封装实体")
@GroupSequenceProvider(value = TeacherSequenceProvider.class)
public class UserInfo {

    @ApiModelProperty(value = "用户标识")
    @NotNull(message = "用户标识id不能为空")
    private Long loginId;

    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "姓名不能为空", groups = AddGroup.class)
    private String name;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "微信号码")
    @NotBlank(message = "微信号不能为空", groups = AddGroup.class)
    private String wxCard;

    @ApiModelProperty(value = "用户手机号")
    @NotBlank(message = "联系电话不能为空", groups = AddGroup.class)
    private String phone;

    @ApiModelProperty(value = "头像路径")
    private String imageHeadUrl;

    @ApiModelProperty(value = "擅长技能")
    private List<String> skillList;

    @ApiModelProperty(value = "项目经验")
    private List<String> experienceList;

    @ApiModelProperty(value = "用户类型 0 为导师，1为学生")
    @NotNull(message = "用户类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "日薪")
    @NotNull(message = "日薪不能为空", groups = TeacherTypeGroup.class)
    private BigDecimal dailySalary;

    @ApiModelProperty(value = "工作年限")
    private String workYear;
}
