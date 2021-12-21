package com.kkb.project.portal.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 内推信息实体类
 *
 * @author lemon
 * @version 1.0
 * @since 2021/04/15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "InternalRecommendationInfo对象", description = "内推信息实体类")
public class InternalRecommendationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "公司名")
    private String companyName;

    @ApiModelProperty(value = "职位")
    private String companyPosition;

    @ApiModelProperty(value = "工作年限")
    private String workingYear;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "工资 ")
    private String salary;

    @ApiModelProperty(value = "公司规模")
    private String companyScale;

    @ApiModelProperty(value = "是否上市 0代表未上市 1代表已上市")
    private Integer isListedCompany;

    @ApiModelProperty(value = "公司logo")
    private String companyLogo;

    @ApiModelProperty(value = "以逗号分隔多个标签")
    private String companyTag;

    @ApiModelProperty(value = "公司地址")
    private String companyAddress;

    @ApiModelProperty(value = "职位描述")
    private String positionDescription;

    @ApiModelProperty(value = "发布时间")
    private Date publishTime;

    @ApiModelProperty(value = "软删除字段 0代表未删除 1代表已删除", hidden = true)
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty(value = "创建人", hidden = true)
    private String createdBy;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createdTime;

    @ApiModelProperty(value = "更新人", hidden = true)
    private String updatedBy;

    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date updatedTime;

    @ApiModelProperty(value = "乐观锁", hidden = true)
    private Integer revision;
}
