package com.kkb.project.common.exception;

import com.kkb.project.common.api.IErrorCode;

/**
 * @Description: 断言处理类，用于抛出各种API异常
 * @author: peng.ni
 * @date: 2021/04/07
 */
public class Asserts {
    /**
     *
     * @param message 错误消息
     */
    public static void fail(String message) {
        throw new ApiException(message);
    }

    /**
     *
     * @param errorCode 错误码
     */
    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
