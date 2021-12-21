package com.kkb.project.admin.domain.constant;

/**
 * @author __SAD_DOG__
 * @date 2021-04-23
 */
public enum IsAnonymous implements Valuable {
    /**
     * 不是匿名评论的
     */
    NOT_ANONYMOUS(0),

    /**
     * 匿名评论
     */
    IS_ANONYMOUS(1);

    private final int value;

    private static final IsAnonymous[] ENUM_VALUES = values();

    IsAnonymous(int v) {
        value = v;
    }

    @Override
    public int value() {
        return value;
    }

    /**
     * 在O(1)的时间内以int类型的value获取对应的enum对象
     *
     * @param v 某个enum对象的value值
     * @return 返回该value值对应的enum对象, 如果没有value 为 v 的enum对象, 则会抛出运行时异常
     */
    public static IsAnonymous ofValue(int v) {
        return ENUM_VALUES[v];
    }
}
