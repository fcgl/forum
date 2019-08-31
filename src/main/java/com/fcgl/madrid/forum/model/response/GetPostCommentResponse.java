package com.fcgl.madrid.forum.model.response;

import com.fcgl.madrid.forum.dataModel.Comment;
import java.util.List;

public class GetPostCommentResponse {
    private InternalStatus status;
    private List<Comment> comments;

    public GetPostCommentResponse(InternalStatus status, List<Comment> comments) {
        this.status = status;
        this.comments = comments;
    }

    public InternalStatus getStatus() {
        return status;
    }

    public void setStatus(InternalStatus status) {
        this.status = status;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
