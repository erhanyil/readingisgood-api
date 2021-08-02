package com.example.readingisgood.dto.response;

public class ResponseDto {

    private String msg;
    private Object data;
    private Integer code;
    private boolean success;

    public ResponseDto() {
        //Do nothing
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", code=" + code +
                '}';
    }
}