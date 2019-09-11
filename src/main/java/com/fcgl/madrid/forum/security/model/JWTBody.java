package com.fcgl.madrid.forum.security.model;
import java.util.List;

public class JWTBody {

    private Long userId;
    private List<String> authorities;

    public JWTBody(Long userId, List<String> authorities) {
        this.userId = userId;
        this.authorities = authorities;
    }

    public Long getUserId() {
        return userId;
    }

    public List<String> getAuthorities() {
        return this.authorities;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "JWTBody{" +
                "userId=" + userId +
                ", authorities=" + authorities +
                '}';
    }
}
