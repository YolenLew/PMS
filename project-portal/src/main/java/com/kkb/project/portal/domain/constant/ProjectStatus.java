package com.kkb.project.portal.domain.constant;

/**
 * @author __SAD_DOG__
 * @Date 2021-04-16
 * 项目状态enum
 */
public enum ProjectStatus implements Valuable {
    /**
     * 已发布
     */
    PUBLISHED(0),
    /**
     * 审核中, REVIEWING本不应该代表项目的状态(而因该是报名状态), 这种不均一性是前端的设计带来的
     */
    REVIEWING(1),
    /**
     * 进行中
     */
    PROCESSING(2),
    /**
     * 已经完成
     */
    COMPLETED(3),
    /**
     * 已取消
     */
    CANCELLED(4);

    private final int value;
    private static final ProjectStatus[] ENUM_VALUES = values();


    ProjectStatus(int v) {
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
    public static ProjectStatus ofValue(int v) throws RuntimeException {
        ProjectStatus res;
        try {
            res = ENUM_VALUES[v];
        } catch (Exception e) {
            throw new RuntimeException("value: " + "v" + " Not represent an Enum of " + ProjectStatus.class.getSimpleName());
        }
        return res;
    }
}