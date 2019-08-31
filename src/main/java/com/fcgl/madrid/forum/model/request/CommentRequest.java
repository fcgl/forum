package com.fcgl.madrid.forum.model.request;

import com.fcgl.madrid.forum.dataModel.Post;

public class CommentRequest {
    private String message;
    private Integer userId;
    private Post post;

    /**
     * Request Parameters for post
     * @param message
     * @param userId
     * @param post
     */
    public CommentRequest(String message, Integer userId, Post post){
        this.message = message;
        this.userId = userId;
        this.post = post;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
