package com.kkb.project.admin.domain.vo;

import com.kkb.project.admin.domain.ProjectWorkStyle;
import com.kkb.project.admin.domain.ProjectWorkType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author __SAD_DOG__
 * @date 2021-04-29
 * 封装工作方式和工作类型到一起
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectWorkTypeAndStyleVo {
    @ApiModelProperty("工作方式")
    private List<ProjectWorkStyle> projectWorkStyles;

    @ApiModelProperty("工作类型")
    private List<ProjectWorkType> projectWorkTypes;

}