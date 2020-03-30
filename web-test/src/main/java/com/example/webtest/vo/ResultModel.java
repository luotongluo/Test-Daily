package com.example.webtest.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author luotong
 * @description ResultModel
 * 接口返回结果对象
 * @date 2020/3/30 10:37
 */
@ApiModel(description = "返回结果")
public class ResultModel<T> {
    @ApiModelProperty("是否成功: true or false")
    private boolean result;
    @ApiModelProperty("描述性原因")
    private String message;
    @ApiModelProperty("业务数据")
    private T data;

    public ResultModel(boolean result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }
    public static<T> ResultModel<T> success(T data) {
        return new ResultModel<>(true, "SUCCESS", data);
    }


    public static<T> ResultModel<T> success(String message, T data) {
        return new ResultModel<>(true, message, data);
    }


    public static ResultModel failure() {
        return new ResultModel<>(false, "FAILURE", null);
    }


    public static ResultModel failure(String message) {
        return new ResultModel<>(false, message, null);
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
