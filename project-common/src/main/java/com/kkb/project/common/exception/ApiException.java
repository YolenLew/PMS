package com.kkb.project.common.exception;

import com.kkb.project.common.api.IErrorCode;

/**
 * @Description: 自定义API异常 ， 效验失败抛出改异常
 * @author: peng.ni
 * @date: 2021/04/07
 */
public class ApiException extends RuntimeException {
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}

