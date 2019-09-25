package com.fcgl.madrid.forum.model.request;

import javax.validation.constraints.NotNull;

public class GetPostCommentRequest {

    @NotNull
    private Long postId;
    @NotNull
    private Integer page;
    @NotNull
    private Integer size;

    public GetPostCommentRequest(Long postId, Integer page, Integer size) {
        this.postId = postId;
        this.page = page;
        this.size = size;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
