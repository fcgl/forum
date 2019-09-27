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
    private Long postId;
    @NotEmpty
    private String firstName;

    /**
     * Request Parameters for post
     * @param message
     * @param userId
     * @param postId
     * @param firstName
     */
    public CommentRequest(String message, Long userId, Long postId, String firstName){
        this.message = message;
        this.userId = userId;
        this.postId = postId;
        this.firstName = firstName;
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
