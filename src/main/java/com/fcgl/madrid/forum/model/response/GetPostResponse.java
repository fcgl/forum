package com.fcgl.madrid.forum.model.response;

import com.fcgl.madrid.forum.dataModel.IBasicPost;

public class GetPostResponse {
    private InternalStatus status;
    private IBasicPost post;

    public GetPostResponse(InternalStatus status, IBasicPost post) {
        this.status = status;
        this.post = post;
    }

    public InternalStatus getStatus() {
        return status;
    }

    public void setStatus(InternalStatus status) {
        this.status = status;
    }

    public IBasicPost getPost() {
        return post;
    }

    public void setPost(IBasicPost post) {
        this.post = post;
    }
}
