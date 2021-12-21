package com.kkb.project.portal.domain.constant;

/**
 * @author __SAD_DOG__
 * @Date 2021-04-16
 * 委托方状态enum
 */
public enum ClientStatus implements Valuable {
    /**
     * 未通过认证
     */
    NOT_CERTIFICATE(0),
    /**
     * 通过认证
     */
    CERTIFICATE(1) ;
    private final int value;
    private static final ClientStatus[] ENUM_VALUES = values();


    ClientStatus(int v) {
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
    public static ClientStatus ofValue(int v) {
        return ENUM_VALUES[v];
    }
}
