package com.fcgl.madrid.forum.model.response;

import com.fcgl.madrid.forum.dataModel.Comment;

import java.time.Instant;
import java.util.List;

public class GetPostCommentResponse {
    private InternalStatus status;
    private List<Comment> comments;
    private Long timestamp;


    public GetPostCommentResponse(InternalStatus status, List<Comment> comments) {
        this.status = status;
        this.comments = comments;
        this.timestamp = Instant.now().toEpochMilli();
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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
