package com.kkb.project.admin.domain.constant;

/**
 * @author __SAD_DOG__
 * 用于给管理SQL中status的unsigned tinyint的Enum类实现
 */
public interface Valuable {
    /**
     * enum 对象调用value()方法返回与该enum对象对应的数据库中(jdbcType=unsigned tinyint)的值
     * @return 与该enum对象对应的数据库中(jdbcType=unsigned tinyint)的值
     */
    int value();
}
