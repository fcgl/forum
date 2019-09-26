package com.fcgl.madrid.forum.controller;

import com.fcgl.madrid.forum.dataModel.Post;
import com.fcgl.madrid.forum.model.request.GetCityPostsRequest;
import com.fcgl.madrid.forum.model.request.PostLikeRequest;
import com.fcgl.madrid.forum.model.response.GetCityPostsResponse;
import com.fcgl.madrid.forum.model.response.GetPostInteractionData;
import com.fcgl.madrid.forum.model.response.GetPostResponse;
import com.fcgl.madrid.forum.model.response.InternalStatus;
import com.fcgl.madrid.forum.model.request.PostRequest;
import com.fcgl.madrid.forum.service.PostService;
import com.fcgl.madrid.healthcheck.model.Health;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.Valid;
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
    public ResponseEntity<InternalStatus> post(@Valid @RequestBody PostRequest postRequest) {
        return postService.post(postRequest);
    }

    @GetMapping(path="/getPost")
    public ResponseEntity<GetPostResponse> getPost(Long postId) {
        return postService.getPost(postId);
    }

    @GetMapping(path="/cityPosts/new")
    public ResponseEntity<GetCityPostsResponse> getCityPostsNew(@Valid GetCityPostsRequest request) {
        return postService.getCityPostsNew(request);
    }

    @GetMapping(path="/cityPosts/featured")
    public ResponseEntity<GetCityPostsResponse> getCityPostsFeatured(@Valid GetCityPostsRequest request) {
        return postService.getCityPostsFeatured(request);
    }

    @GetMapping(path="/cityPosts/top")
    public ResponseEntity<GetCityPostsResponse> getCityPostsTop(@Valid GetCityPostsRequest request) {
        return postService.getCityPostsTop(request);
    }

    @PostMapping(path="/like")
    public ResponseEntity<InternalStatus> postLike(@Valid @RequestBody PostLikeRequest request) {
        return postService.postLike(request);
    }

    @GetMapping(path="/postLikeCount")
    public ResponseEntity<GetPostInteractionData> getPostLikeCount(Long postId) {
        return postService.getPostLikeCount(postId);
    }

    @GetMapping(path="/postCommentCount")
    public ResponseEntity<GetPostInteractionData> getPostCommentCount(Long postId) {
        return postService.getPostCommentCount(postId);
    }

    @GetMapping(path="/postInteractionData")
    public ResponseEntity<GetPostInteractionData> getPostInteractionData(Long postId) {
        return postService.getPostInteractionData(postId);
    }
}
