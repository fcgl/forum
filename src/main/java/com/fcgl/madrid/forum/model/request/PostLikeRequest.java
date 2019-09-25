package com.fcgl.madrid.forum.model.request;

import javax.validation.constraints.NotNull;

public class PostLikeRequest {

    @NotNull
    private Long userId;
    @NotNull
    private Long postId;

    public PostLikeRequest(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "PostLikeRequest{" +
                "userId=" + userId +
                ", postId=" + postId +
                '}';
    }
}
