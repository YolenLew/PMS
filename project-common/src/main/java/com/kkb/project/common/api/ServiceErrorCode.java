package com.kkb.project.common.api;

/**
 * @Description: Service 层异常
 * @author: peng.ni
 * @date: 2021/04/18
 */
public enum ServiceErrorCode implements IErrorCode {
    ;
    private long code;
    private String message;

    @Override
    public long getCode() {
        return 0;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
