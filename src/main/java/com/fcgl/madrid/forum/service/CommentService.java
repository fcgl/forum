package com.fcgl.madrid.forum.service;

import com.fcgl.madrid.forum.dataModel.Comment;
import com.fcgl.madrid.forum.dataModel.Post;
import com.fcgl.madrid.forum.model.request.CommentRequest;
import com.fcgl.madrid.forum.model.request.GetPostCommentRequest;
import com.fcgl.madrid.forum.model.response.GetPostCommentResponse;
import com.fcgl.madrid.forum.model.response.InternalStatus;
import com.fcgl.madrid.forum.model.response.Response;
import com.fcgl.madrid.forum.model.response.StatusCode;
import com.fcgl.madrid.forum.repository.CommentRepository;
import com.fcgl.madrid.forum.repository.PostRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CommentService implements ICommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private Log logger;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.logger = LogFactory.getLog(CommentService.class);
    }

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @CircuitBreaker(name = "backendA", fallbackMethod = "fallback")
    public ResponseEntity<Response<Comment>> postComment(CommentRequest commentRequest) {
        Post post = postRepository.findById(commentRequest.getPostId()).get();
        Comment comment = new Comment(
                commentRequest.getMessage(),
                post,
                commentRequest.getUserId(),
                commentRequest.getFirstname()
        );
        commentRepository.save(comment);
        Response<Comment> response = new Response<>(InternalStatus.OK, comment);
        return new ResponseEntity<Response<Comment>>(response, HttpStatus.OK);
    }

    @CircuitBreaker(name = "backendA", fallbackMethod = "fallback")
    public ResponseEntity<GetPostCommentResponse> getPostComments(GetPostCommentRequest request) {
        Pageable pageConfig = PageRequest.of(request.getPage(), request.getSize());
        List<Comment> comments = commentRepository.findAllByPostId(request.getPostId(), pageConfig);
        GetPostCommentResponse response = new GetPostCommentResponse(InternalStatus.OK, comments);
        return new ResponseEntity<GetPostCommentResponse>(response, HttpStatus.OK);
    }

    /**
     * Handles Exceptions dealing with parameters
     * @param e TransactionSystemException
     * @return
     */
    private ResponseEntity<InternalStatus> handleParamException(TransactionSystemException e) {
        return getInternalStatusResponseEntity(e);
    }

    static ResponseEntity<InternalStatus> getInternalStatusResponseEntity(TransactionSystemException e) {
        Throwable cause = e.getRootCause();
        if (cause instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
            List<String> messages = new ArrayList<String>();
            for (ConstraintViolation v : constraintViolations) {
                StringBuilder builder = new StringBuilder("");
                builder.append(v.getPropertyPath().toString());
                builder.append(" ");
                builder.append(v.getMessage());
                messages.add(builder.toString());
            }
            // do something here
            InternalStatus internalStatus = new InternalStatus(StatusCode.PARAM, HttpStatus.BAD_REQUEST, messages);
            return new ResponseEntity<InternalStatus>(internalStatus, HttpStatus.BAD_REQUEST);
        }
        InternalStatus internalStatus = new InternalStatus(StatusCode.UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        return new ResponseEntity<InternalStatus>(internalStatus, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     *
     * @param ex Exception
     * @return ResponseEntity<InternalStatus>
     */
    private ResponseEntity<Response<Comment>> fallback(CommentRequest commentRequest, Exception ex) {
        String message = "Fallback: " + ex.getMessage();
        InternalStatus internalStatus = new InternalStatus(StatusCode.UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR, message);
        Response<Comment> response = new Response<Comment>(internalStatus, null);
        return new ResponseEntity<Response<Comment>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     *
     * @param ex Exception
     * @return ResponseEntity<InternalStatus>
     */
    private ResponseEntity<GetPostCommentResponse> fallback(GetPostCommentRequest request, Exception ex) {
        String message = "Fallback: " + ex.getMessage();
        InternalStatus internalStatus = new InternalStatus(StatusCode.UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR, message);
        GetPostCommentResponse response = new GetPostCommentResponse(internalStatus, null);
        return new ResponseEntity<GetPostCommentResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
