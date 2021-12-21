package com.kkb.project.portal.domain.constant;

/**
 * @author __SAD_DOG__
 * @Date 2021-04-16
 * 软删除enum
 */
public enum DeletedStatus implements Valuable {
    /**
     * 0: 未删除
     */
    NOT_DELETED(0),
    /**
     * 1: 已删除
     */
    IS_DELETED(1);

    private final int value;
    private static final DeletedStatus[] ENUM_VALUES = values();


    DeletedStatus(int v) {
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
    public static DeletedStatus ofValue(int v) {
        return ENUM_VALUES[v];
    }
}
