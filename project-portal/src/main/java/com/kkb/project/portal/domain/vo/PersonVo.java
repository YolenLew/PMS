package com.kkb.project.portal.domain.vo;

import com.kkb.project.portal.domain.WorkDescription;
import com.kkb.project.portal.domain.WorkImage;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName PersonVo
 * @Author River
 * @Date 2021/4/25 20:39
 * @Description 查看名人详情 返回参数，封装为对象 便于Knife4j 显示  包装类
 * @Version 1.0
 **/
@Data
public class PersonVo {

    private List<WorkImage> workImageList;
    private WorkDescription workDescription;
    private Date createTime;
    private Integer praiseNum;

    public PersonVo(List<WorkImage> workImageList, WorkDescription workDescription, Date createTime, Integer praiseNum) {
        this.workImageList = workImageList;
        this.workDescription = workDescription;
        this.createTime = createTime;
        this.praiseNum = praiseNum;
    }
}
