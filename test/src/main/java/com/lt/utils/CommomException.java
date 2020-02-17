package com.lt.utils;

import com.sun.istack.internal.NotNull;

/**
 * @Author: LT
 * @Date: 2020/1/5 16:40
 * @Description: 自定义异常类
 * @Version 1.0
 */
public class CommomException extends RuntimeException {
    @NotNull
    private Integer code;
    @NotNull
    private String msg;

    public CommomException() {
    }

    public CommomException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
