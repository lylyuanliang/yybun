package com.yybun.common.vo;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 返回Vo
 */
public class ResponseVo {

    private String rtnCode;
    private String message;
    private Object data;

    ResponseVo() {}
    ResponseVo(String rtnCode, String message, Object data) {
        this.rtnCode = rtnCode;
        this.message = message;
        this.data = data;
    }

    public String getRtnCode() {
        return rtnCode;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static String createResponseStr(String rtnCode, String message, Object data) {
        ResponseVo rs = new ResponseVo(rtnCode, message, data);
        return rs.toString();
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
