package com.lt.alidemo.exception;

/**
 * @Author: LT
 * @Date: 2020/2/27 14:42
 * @Description:
 * @Version 1.0
 */
public class UseException extends RuntimeException {
    private int code;
    private String msg;

    public UseException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
