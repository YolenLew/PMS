package com.kkb.project.admin.domain.vo;

import com.kkb.project.admin.domain.Project;
import com.kkb.project.admin.domain.SuccessCase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName SuccessCaseVo
 * @Author River
 * @Date 2021/4/29 12:18
 * @Description 成功案例包装类
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "成功案例")
public class SuccessCaseVo {

    @ApiModelProperty(value = "成功案例对象")
    private SuccessCase successCase;

    @ApiModelProperty(value = "项目对象")
    private ProjectVo projectRes;

}
