package com.model;

public class Response {
    String msg;
    int code;
    Boolean isSuc = true;

    public Boolean getSuc() {
        return isSuc;
    }

    public void setSuc(Boolean suc) {
        isSuc = suc;
    }

    Object result;
    public Object getResult() {
        return result;
    }
    public void setResult(Object result) {
        this.result = result;
    }

    public Response() {
    }
    public Response(String msg, int code, Boolean isSuc){
        this.msg = msg;
        this.code = code;
        this.isSuc = isSuc;

    }

    public Response(String msg, int code, Boolean isSuc,Object result) {
        this.msg = msg;
        this.code = code;
        this.isSuc = isSuc;
        this.result=result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }





}
