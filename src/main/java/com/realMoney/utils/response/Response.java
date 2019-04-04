package com.realMoney.utils.response;


public class Response<T> {
    private Integer result;
    private T data;
    private String message;
    private boolean flag;

    public Response() {
    }

    public Response(Integer result, T data, String message) {
        this.result = result;
        this.data = data;
        this.message = message;
    }


    public Response(Integer result, T data) {
        this.result = result;
        this.data = data;
        if (ResponseCode.SUCCESS.getCode() == result) {
            this.message = ResponseCode.SUCCESS.getDescription();
        }
    }

    public Response(Integer result, boolean flag, T data) {
        this.result = result;
        this.flag = flag;
        this.data = data;
        if (ResponseCode.SUCCESS.getCode() == result) {
            this.message = ResponseCode.SUCCESS.getDescription();
        }
    }

    public Response(Integer result, String message) {
        this.result = result;
        this.message = message;
    }

    public Response(Integer result) {
        this.result = result;
    }

    public Integer getResult() {
        return this.result;
    }

    public void setResult(Integer status) {
        this.result = result;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Response{");
        sb.append("result=").append(this.result);
        sb.append(", data=").append(this.data);
        sb.append(", message='").append(this.message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
