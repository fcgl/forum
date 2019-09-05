package com.fcgl.madrid.forum.model.response;

import com.fcgl.madrid.forum.dataModel.IBasicPost;
import com.fcgl.madrid.forum.dataModel.Post;
import java.util.List;

public class GetCityPostsResponse {
    private InternalStatus status;
    private List<IBasicPost> posts;

    public GetCityPostsResponse(InternalStatus status, List<IBasicPost> posts) {
        this.status = status;
        this.posts = posts;
    }

    public InternalStatus getStatus() {
        return status;
    }

    public void setStatus(InternalStatus status) {
        this.status = status;
    }

    public List<IBasicPost> getPosts() {
        return posts;
    }

    public void setPosts(List<IBasicPost> posts) {
        this.posts = posts;
    }
}
