package com.kkb.project.common.mpaop;

import com.kkb.project.common.exception.Asserts;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author __SAD_DOG__
 * @date 2021-04-30
 * 对使用Collection作为参数的mybatis-plus IService方法进行空集合校验
 */
@Aspect
@Component
public class MpBatchQueryAspect {

    /**
     * portal切入点
     */
    @Pointcut("execution(public (java.util.List || java.util.Map || com.kkb.project.common.api.CommonPage) com.kkb.project.portal.service.*.*(..,java.util.Collection,..))")
    public void portalBatchQuery(){}

    /**
     * admin切入点
     */
    @Pointcut("execution(public (java.util.List || java.util.Map || com.kkb.project.common.api.CommonPage) com.kkb.project.admin.service.*.*(..,java.util.Collection,..))")
    public void adminBatchQuery(){}

    @Before("portalBatchQuery() || adminBatchQuery()")
    public void beforeMpBatchQuery(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Arrays.stream(args).forEach(it -> {
            if (it instanceof Collection) {
                Collection<?> c = (Collection<?>) it;
                if (c.size() == 0) {
                    Asserts.fail("服务器内部错误: 使用空的集合参数进行批量查询, 可能由数据库记录约束被破坏导致");
                }
            }
        });
    }

}