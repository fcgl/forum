package com.fcgl.madrid.forum.controller;

import com.fcgl.madrid.forum.dataModel.Comment;
import com.fcgl.madrid.forum.model.request.CommentRequest;
import com.fcgl.madrid.forum.model.request.GetPostCommentRequest;
import com.fcgl.madrid.forum.model.response.GetPostCommentResponse;
import com.fcgl.madrid.forum.model.response.InternalStatus;
import com.fcgl.madrid.forum.model.response.Response;
import com.fcgl.madrid.forum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/forum/comment/v1")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(path = "/postComment")
    public ResponseEntity<Response<Comment>> postComment(@Valid @RequestBody CommentRequest commentRequest) {
        return commentService.postComment(commentRequest);
    }

    @GetMapping(path = "/getComment")
    public ResponseEntity<GetPostCommentResponse> getPostComments(@Valid GetPostCommentRequest request) {
        return commentService.getPostComments(request);
    }
}
