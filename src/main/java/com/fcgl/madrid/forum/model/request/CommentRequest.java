package com.fcgl.madrid.forum.model.request;

import com.fcgl.madrid.forum.dataModel.Post;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CommentRequest {
    @NotEmpty
    private String message;
    @NotNull
    private Long userId;
    @NotNull
    private Post post;
    @NotEmpty
    private String firstName;

    /**
     * Request Parameters for post
     * @param message
     * @param userId
     * @param post
     * @param firstName
     */
    public CommentRequest(String message, Long userId, Post post, String firstName){
        this.message = message;
        this.userId = userId;
        this.post = post;
        this.firstName = firstName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public String getFirstname() {
        return firstName;
    }

    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }
}
