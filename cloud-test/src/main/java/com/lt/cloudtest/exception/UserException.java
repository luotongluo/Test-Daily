package com.lt.cloudtest.exception;

/**
 * Created by tong.luo on 2019/12/22 0:48
 */
public class UserException extends RuntimeException {
    int code;
    String msg;

    public UserException() {
    }

    public UserException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public UserException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
