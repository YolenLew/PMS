package com.kkb.project.portal.domain.vo;

import com.kkb.project.portal.domain.UserWork;
import com.kkb.project.portal.domain.WorkDescription;
import com.kkb.project.portal.domain.WorkImage;
import com.kkb.project.portal.domain.WorkPraise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName UserWorkImageVo
 * @Author River
 * @Date 2021/4/25 20:32
 * @Description 查找作品集详情 返回参数，封装为对象 便于Knife4j 显示   包装类
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWorkImageVo {

    private List<WorkImage> workImageList;
    private UserWork userWork;
    private WorkDescription workDescription;
    private WorkPraise workPraise;
}
