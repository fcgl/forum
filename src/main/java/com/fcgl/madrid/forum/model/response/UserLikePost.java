package com.fcgl.madrid.forum.model.response;

import com.fcgl.madrid.forum.dataModel.IBasicPost;

public class UserLikePost {
    private IBasicPost post;
    private Boolean liked;

    public UserLikePost() {

    }

    public UserLikePost(IBasicPost post, Boolean liked) {
        this.post = post;
        this.liked = liked;
    }

    public IBasicPost getPost() {
        return post;
    }

    public void setPost(IBasicPost post) {
        this.post = post;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }
}
