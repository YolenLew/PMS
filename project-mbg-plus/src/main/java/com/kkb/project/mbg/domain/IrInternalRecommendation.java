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
 * 内推信息表
 * </p>
 *
 * @author ${author}
 * @since 2021-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="IrInternalRecommendation对象", description="内推信息表")
public class IrInternalRecommendation implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "公司名")
    private String companyName;

    @ApiModelProperty(value = "职位")
    private String companyPosition;

    @ApiModelProperty(value = "工作年限")
    private String workingYears;

    @ApiModelProperty(value = "学历 0：专科及以下 1：本科 2：硕士 3：博士 ")
    private String education;

    @ApiModelProperty(value = "工资")
    private String salary;

    @ApiModelProperty(value = "月数")
    private String numberOfMonth;

    @ApiModelProperty(value = "公司规模")
    private String companyScale;

    @ApiModelProperty(value = "是否上市 0代表未上市 1代表已上市")
    private Integer isListedCompany;

    @ApiModelProperty(value = "公司logo")
    private String companyLogo;

    @ApiModelProperty(value = "公司地址")
    private String companyAddress;

    @ApiModelProperty(value = "发布时间")
    private LocalDateTime publishTime;

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
