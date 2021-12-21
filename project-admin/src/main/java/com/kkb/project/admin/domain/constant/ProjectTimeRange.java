package com.kkb.project.admin.domain.constant;


/**
 * @author __SAD_DOG__
 * @date 2021-04-29
 * 查询项目时的最近日期选项: 最近一周项目或者最近一个月项目
 */

public enum ProjectTimeRange implements Valuable {

    /**
     * 一周内
     */
    WEEK(0),
    /**
     * 一个月内
     */
    MONTH(1);
    private final int value;
    private static final ProjectTimeRange[] ENUM_VALUES = values();


    ProjectTimeRange(int v) {
        value = v;
    }

    @Override
    public int value() {
        return value;
    }

    /**
     * 在O(1)的时间内以int类型的value获取对应的enum对象
     * @param v 某个enum对象的value值
     * @return 返回该value值对应的enum对象, 如果没有value 为 v 的enum对象, 则会抛出运行时异常
     */
    public static ProjectTimeRange ofValue(int v) {
        return ENUM_VALUES[v];
    }
}
