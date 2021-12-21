package com.kkb.project.portal.domain.vo;

import com.kkb.project.portal.domain.WorkImage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @ClassName ListWorkImageVo
 * @Author River
 * @Date 2021/4/21 10:41
 * @Description 作品集图片集合 包装类
 * @Version 1.0
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户图片表集合对象")
public class ListWorkImageVo {

    @ApiModelProperty(value = "作品图片集合对象")
    @NotEmpty(message = "请上传作品图片")
    private List<WorkImage> workImages;

}
