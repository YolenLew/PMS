package com.kkb.project.admin.domain.constant;

/**
 * @author Lai Xiangdong
 * @Description 角色类型
 * @createTime 2021-04-24 20:24:06
 * @updateBy __SAD_DOG__
 * @updateAt 2021-04-26
 */
public enum RoleType implements Valuable {
    /**
     * 导师
     */
    LEADER(0),
    /**
     * 学生
     */
    PARTICIPANT(1);
    private final int value;
    private static final RoleType[] ENUM_VALUES = values();

    /**
     * 通过int构造一个enum对象
     * @param i enum对象对应的int值
     */
    RoleType(int i) {
        this.value = i;
    }

    /**
     * enum 对象调用value()方法返回与该enum对象对应的数据库中(jdbcType=unsigned tinyint)的值
     *
     * @return 与该enum对象对应的数据库中(jdbcType = unsigned tinyint)的值
     */
    @Override
    public int value() {
        return value;
    }

    public static RoleType ofValue(int v) {
        return ENUM_VALUES[v];
    }
}
