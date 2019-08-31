package com.fcgl.madrid.forum.model.request;

public class GetPostCommentRequest {

    private Long postId;
    private int page;
    private int size;

    public GetPostCommentRequest(Long postId, int page, int size) {
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
