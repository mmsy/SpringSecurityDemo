package com.nfky.datacenter.common.base;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 请求返回数据
 *
 * Created by lyr on 2017/6/8.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResponseData<T> {
    // 请求成功默认代码
    public final static int SUCCESS_CODE = 200;
    // 请求失败默认代码
    public final static int ERROR_CODE = -1;
    // 请求成功默认消息
    public final static String SUCCESS_MSG_DEFAULT = "请求成功";
    // 请求失败默认消息
    public final static String ERROR_MSG_DEFAULT = "请求失败";
    // 返回代码
    private int code;
    // 返回消息
    private String msg;
    // 返回数据
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
