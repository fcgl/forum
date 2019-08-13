package com.fcgl.madrid.forum.model;

public class InternalStatus {
    public static final InternalStatus OK = new InternalStatus(1, 200, "ok");
    public static final InternalStatus MISSING_PARAM = new InternalStatus(2, 400, "Missing Required Param");

    private int code;
    private int httpCode;
    private String message;

    public InternalStatus() {

    }

    public InternalStatus(int code, int httpCode, String message) {
        this.code = code;
        this.httpCode = httpCode;
        this.message = message;
    }


    public int getCode() {
        return this.code;
    }

    public int getHttpCode() {
        return this.httpCode;
    }

    public String getMessage() {
        return this.message;
    }
}
