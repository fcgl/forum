package com.fcgl.madrid.forum.model.request;

public class PostLikeRequest {

    private Long userId;
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
}
