package com.kkb.project.portal.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author __SAD_DOG__
 * @date 2021-04-49
 * 查询 projectVo 列表的筛选条件
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryProjectDto {
    /**
     * 项目类型
     */
    @NotNull
    @ApiModelProperty("项目类型id")
    private Long projectWorkTypeId;

    /**
     * 项目状态
     */
    @NotNull
    @ApiModelProperty("项目状态")
    @Pattern(regexp = "[0-4]|(-1)")
    private Integer projectStatus;

    /**
     * 发布时间
     */
    @NotNull
    @ApiModelProperty("发布日期筛选")
    @Pattern(regexp = "[0-2]|(-1)")
    private Integer timeRangeFilter;

    /**
     * 页数
     */
    @NotNull
    @ApiModelProperty("页数")
    private Long pageNum;

    /**
     * 页大小
     */
    @NotNull
    @ApiModelProperty("页大小")
    private Long pageSize;
}
