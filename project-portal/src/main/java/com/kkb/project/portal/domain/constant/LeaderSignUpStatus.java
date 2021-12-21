package com.kkb.project.portal.domain.constant;

/**
 * @Author ynb
 * @Date 2021/4/20
 * @Description 导师报名状态枚举
 **/
public enum LeaderSignUpStatus implements Valuable {
    /**
     *  已报名
     */
    SIGNED_UP(0),
    /**
     *  已接受报名
     */
    ACCEPTED(1),
    /**
     *  已被拒绝报名
     */
    REFUSED(2);

    private final int value;
    private static final LeaderSignUpStatus[] ENUM_VALUES = values();

    LeaderSignUpStatus(int v) {
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
    public static LeaderSignUpStatus ofValue(int v) {
        return ENUM_VALUES[v];
    }
}
