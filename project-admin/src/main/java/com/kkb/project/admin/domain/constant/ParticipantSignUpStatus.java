package com.kkb.project.admin.domain.constant;

/**
 * @author __SAD_DOG__
 * @Date 2021-04-16
 * 学生报名status enum
 */
public enum ParticipantSignUpStatus implements Valuable {
    /**
     * 已报名
     */
    SIGNED_UP(0),
    /**
     * 已接受
     */
    ACCEPTED(1),
    /**
     * 已拒绝
     */
    REFUSED(2);

    private final int value;
    private static final ParticipantSignUpStatus[] ENUM_VALUES = values();

    ParticipantSignUpStatus(int v) {
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
    public static ParticipantSignUpStatus ofValue(int v) {
        return ENUM_VALUES[v];
    }
}
