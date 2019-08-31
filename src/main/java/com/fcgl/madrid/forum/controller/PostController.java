package com.fcgl.madrid.forum.controller;

import com.fcgl.madrid.forum.dataModel.Post;
import com.fcgl.madrid.forum.model.response.GetPostResponse;
import com.fcgl.madrid.forum.model.response.InternalStatus;
import com.fcgl.madrid.forum.model.request.PostRequest;
import com.fcgl.madrid.forum.service.PostService;
import com.fcgl.madrid.healthcheck.model.Health;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/forum/post/v1")
public class PostController {

    private PostService postService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    /**
     * TODO: REMOVE THIS EVENTUALLY, ONLY USED FOR TESTING, OR SET TO DEV ONLY
     * @return List<Post>
     */
    @RequestMapping("/all")
    public List<Post> getAll() {
        return postService.getAll();
    }

    /**
     * TODO: REMOVE THIS EVENTUALLY, ONLY USED FOR TESTING, OR SET TO DEV ONLY
     * @return
     */
    @RequestMapping("/test")
    public Health checkHealth() {
        return new Health(1, "testing forum");
    }

    @PostMapping(path= "/post")
    public ResponseEntity<InternalStatus> post(PostRequest postRequest) {
        return postService.post(postRequest);
    }

    @GetMapping(path="/getPost")
    public ResponseEntity<GetPostResponse> getPost(Long postId) {
        return postService.getPost(postId);
    }

}
