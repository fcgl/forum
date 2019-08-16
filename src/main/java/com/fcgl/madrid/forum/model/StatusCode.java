package com.fcgl.madrid.forum.model;

public class StatusCode {
    public static final StatusCode UNKNOWN = new StatusCode(-1);
    public static final StatusCode OK = new StatusCode(1);
    public static final StatusCode PARAM = new StatusCode(2);

    private int code;

    StatusCode(int code) {
        this.code = code;
    }


    public int getCode() {
        return code;
    }

}
