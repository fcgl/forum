package com.fcgl.madrid.forum.model.response;

import com.fcgl.madrid.forum.dataModel.IBasicPost;
import com.fcgl.madrid.forum.dataModel.Post;

import java.time.Instant;
import java.util.List;

public class GetCityPostsResponse {
    private InternalStatus status;
    private List<UserLikePost> posts;
    private long timestamp;

    public GetCityPostsResponse(InternalStatus status, List<UserLikePost> posts) {
        this.status = status;
        this.posts = posts;
        this.timestamp = Instant.now().toEpochMilli();
    }

    public InternalStatus getStatus() {
        return status;
    }

    public void setStatus(InternalStatus status) {
        this.status = status;
    }

    public List<UserLikePost> getPosts() {
        return posts;
    }

    public void setPosts(List<UserLikePost> posts) {
        this.posts = posts;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
