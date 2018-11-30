package com.example.bean;

public class UserBean {
    private String msg;
    private String code;
    private String i="0";
    public boolean isSuccess(){
        return code.equals(i);
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
