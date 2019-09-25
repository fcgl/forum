package com.fcgl.madrid.forum.model.response;

public class Response<T> {
    private T response;
    private InternalStatus status;

    public Response(InternalStatus status, T response) {
        this.status = status;
        this.response = response;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public InternalStatus getStatus() {
        return status;
    }

    public void setStatus(InternalStatus status) {
        this.status = status;
    }
}