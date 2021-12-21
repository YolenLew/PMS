package com.kkb.project.common.exception;

/**
 * @author __SAD_DOG__
 * @date 2021-04-27
 * 数据库表的外键等约束在业务层保证,
 * 若发现有违背此约束, 或是其他逻辑上应该满足的约束的情况,
 * 则可以抛出此异常
 */
public class ConstraintException extends RuntimeException {
    public ConstraintException(String msg) {
        super(msg);
    }
}
