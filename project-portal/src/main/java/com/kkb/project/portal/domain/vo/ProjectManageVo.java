package com.kkb.project.portal.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Lai Xiangdong
 * @Description 个人中心项目管理页Vo类
 * @createTime 2021-04-24 17:33:34
 */
@Data
@AllArgsConstructor
@ApiModel(value = "ProjectManageVo" ,description = "个人中心项目管理页Vo类")
public class ProjectManageVo {
    /**
     * 项目名
     */
    @ApiModelProperty(value = "项目名")
    private String title;
    /**
     * 发布项目的委托方名称
     */
    @ApiModelProperty(value = "委托方")
    private String clientName;
    /**
     * 项目报名学生人数
     */
    @ApiModelProperty(value = "学生报名人数")
    private Integer signupParticipantNum;
    /**
     * 导师名称
     */
    @ApiModelProperty(value = "导师姓名")
    private String leaderName;
    /**
     * 项目状态 报名中 进行中 结束了 取消了
     */
    @ApiModelProperty(value = "项目状态")
    private Integer status;

    public ProjectManageVo() {

    }
}
