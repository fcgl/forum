package com.fcgl.madrid.forum.controller;

import com.fcgl.madrid.forum.model.CommentRequest;
import com.fcgl.madrid.forum.model.InternalStatus;
import com.fcgl.madrid.forum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forum/comment/v1")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping(path = "/postComment")
    public ResponseEntity<InternalStatus> postComment(CommentRequest commentRequest) {
        return commentService.postComment(commentRequest);
    }

}
