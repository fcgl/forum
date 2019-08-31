package com.fcgl.madrid.forum.service;

import com.fcgl.madrid.forum.dataModel.Post;
import com.fcgl.madrid.forum.dataModel.IBasicPost;
import com.fcgl.madrid.forum.model.response.GetPostResponse;
import com.fcgl.madrid.forum.model.response.InternalStatus;
import com.fcgl.madrid.forum.model.request.PostRequest;
import com.fcgl.madrid.forum.model.response.StatusCode;
import com.fcgl.madrid.forum.repository.PostRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionSystemException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import java.lang.Exception;
import java.lang.Throwable;
import java.lang.StringBuilder;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import static com.fcgl.madrid.forum.service.CommentService.getInternalStatusResponseEntity;

@Service
public class PostService implements IPostService {

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @CircuitBreaker(name = "backendA", fallbackMethod = "fallback")
    public ResponseEntity<InternalStatus> post(PostRequest postRequest) {
        try {
            Post post = new Post(
                    postRequest.getTitle(),
                    postRequest.getDescription(),
                    postRequest.getCityId(),
                    postRequest.getUserId()
            );
            postRepository.save(post);
            return new ResponseEntity<InternalStatus>(InternalStatus.OK, HttpStatus.OK);
        }
        catch (TransactionSystemException e) {
            return handleParamException(e);
        }
    }

    @CircuitBreaker(name = "backendA", fallbackMethod = "fallback")
    public ResponseEntity<GetPostResponse> getPost(Long postId) {
        IBasicPost post = postRepository.getPost(postId);
        if (post == null) {
            GetPostResponse postResponse = new GetPostResponse(InternalStatus.NOT_FOUND, null);
            return new ResponseEntity<GetPostResponse>(postResponse, HttpStatus.NOT_FOUND);
        }
        GetPostResponse postResponse = new GetPostResponse(InternalStatus.OK, post);
        return new ResponseEntity<GetPostResponse>(postResponse, HttpStatus.OK);
    }

    /**
     * Handles Exceptions dealing with parameters
     * @param e TransactionSystemException
     * @return
     */
    private ResponseEntity<InternalStatus> handleParamException(TransactionSystemException e) {
        return getInternalStatusResponseEntity(e);
    }

    /**
     *
     * @param ex Exception
     * @return ResponseEntity<InternalStatus>
     */
    private ResponseEntity<InternalStatus> fallback(PostRequest postRequest, Exception ex) {
        String message = "Fallback: " + ex.getMessage();
        InternalStatus internalStatus = new InternalStatus(StatusCode.UNKNOWN, 500, message);
        return new ResponseEntity<InternalStatus>(internalStatus, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     *
     * @param ex Exception
     * @return ResponseEntity<InternalStatus>
     */
    private ResponseEntity<GetPostResponse> fallback(Long postId, Exception ex) {
        String message = "Fallback: " + ex.getMessage();
        InternalStatus internalStatus = new InternalStatus(StatusCode.UNKNOWN, 500, message);
        GetPostResponse postResponse = new GetPostResponse(internalStatus, null);
        return new ResponseEntity<GetPostResponse>(postResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
