package com.fcgl.madrid.forum.model.response;

import java.util.List;
import java.util.ArrayList;

public class InternalStatus {
    public static final InternalStatus OK = new InternalStatus(StatusCode.OK, 200, "ok");
    public static final InternalStatus MISSING_PARAM = new InternalStatus(StatusCode.PARAM, 400, "Missing Required Param");
    public static final InternalStatus NOT_FOUND = new InternalStatus(StatusCode.NOT_FOUND, 404, "Data Not Found");

    private int code;
    private int httpCode;
    private List<String> messages;

    public InternalStatus() {

    }

    public InternalStatus(StatusCode statusCode, int httpCode, String message) {
        this.code = statusCode.getCode();
        this.httpCode = httpCode;
        this.messages = new ArrayList<String>();
        this.messages.add(message);
    }

    public InternalStatus(StatusCode statusCode, int httpCode, List<String> messages) {
        this.code = statusCode.getCode();
        this.httpCode = httpCode;
        this.messages = messages;
    }


    public int getCode() {
        return this.code;
    }

    public int getHttpCode() {
        return this.httpCode;
    }

    public List<String> getMessages() {
        return this.messages;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        InternalStatus that = (InternalStatus) object;

        if (code != that.code) return false;
        if (httpCode != that.httpCode) return false;
        return messages.equals(that.messages);
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + code;
        result = 31 * result + httpCode;
        result = 31 * result + messages.hashCode();
        return result;
    }
}
