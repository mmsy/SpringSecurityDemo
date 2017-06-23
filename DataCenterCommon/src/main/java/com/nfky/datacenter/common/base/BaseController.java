package com.nfky.datacenter.common.base;

/**
 * 基础控制器
 *
 * Created by lyr on 2017/6/8.
 */
public class BaseController {

    /**
     * 返回请求成功信息
     *
     * @param data 返回数据
     * @param <T>
     * @return
     */
    public <T> ResponseData success(T data) {
        ResponseData<T> response = new ResponseData<>();
        response.setCode(ResponseData.SUCCESS_CODE);
        response.setMsg(ResponseData.SUCCESS_MSG_DEFAULT);
        response.setData(data);

        return response;
    }

    /**
     * 返回请求成功信息
     *
     * @param msg 返回消息
     * @param data 返回数据
     * @param <T>
     * @return
     */
    public <T> ResponseData success(String msg, T data) {
        ResponseData<T> response = new ResponseData<>();
        response.setCode(ResponseData.SUCCESS_CODE);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    /**
     * 返回请求成功信息
     *
     * @param msg 返回消息
     * @return
     */
    public ResponseData success(String msg) {
        ResponseData response = new ResponseData();
        response.setCode(ResponseData.SUCCESS_CODE);
        response.setMsg(msg);
        return response;
    }

    /**
     * 返回请求错误信息
     *
     * @param msg 返回消息
     * @return
     */
    public ResponseData error(String msg) {
        ResponseData response = new ResponseData();
        response.setCode(ResponseData.SUCCESS_CODE);
        response.setMsg(msg);

        return response;
    }

    /**
     * 返回请求错误信息
     *
     * @return
     */
    public ResponseData error() {
        ResponseData response = new ResponseData();
        response.setCode(ResponseData.ERROR_CODE);
        response.setMsg(ResponseData.ERROR_MSG_DEFAULT);
        return response;
    }
}
