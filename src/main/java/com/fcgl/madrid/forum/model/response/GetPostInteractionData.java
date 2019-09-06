package com.fcgl.madrid.forum.model.response;

public class GetPostInteractionData {

    private Integer likeCount;
    private Integer commentCount;
    private InternalStatus internalStatus;

    public GetPostInteractionData(Integer likeCount, Integer commentCount, InternalStatus internalStatus) {
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.internalStatus = internalStatus;
    }


    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public InternalStatus getInternalStatus() {
        return internalStatus;
    }

    public void setInternalStatus(InternalStatus internalStatus) {
        this.internalStatus = internalStatus;
    }
}
