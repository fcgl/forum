package com.fcgl.madrid.forum.service;

import com.fcgl.madrid.forum.dataModel.Comment;
import com.fcgl.madrid.forum.model.request.CommentRequest;
import com.fcgl.madrid.forum.model.response.InternalStatus;
import com.fcgl.madrid.forum.model.response.StatusCode;
import com.fcgl.madrid.forum.repository.CommentRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @CircuitBreaker(name = "backendA", fallbackMethod = "fallback")
    public ResponseEntity<InternalStatus> postComment(CommentRequest commentRequest) {
        try {
            Comment comment = new Comment(
                    commentRequest.getMessage(),
                    commentRequest.getPost(),
                    commentRequest.getUserId()
            );
            commentRepository.save(comment);
            return new ResponseEntity<InternalStatus>(InternalStatus.OK, HttpStatus.OK);
        }
        catch (TransactionSystemException e) {
            return handleParamException(e);
        }
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
            InternalStatus internalStatus = new InternalStatus(StatusCode.PARAM, 400, messages);
            return new ResponseEntity<InternalStatus>(internalStatus, HttpStatus.BAD_REQUEST);
        }
        InternalStatus internalStatus = new InternalStatus(StatusCode.UNKNOWN, 500, e.getMessage());
        return new ResponseEntity<InternalStatus>(internalStatus, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     *
     * @param ex Exception
     * @return ResponseEntity<InternalStatus>
     */
    private ResponseEntity<InternalStatus> fallback(Exception ex) {
        String message = "Fallback: " + ex.getMessage();
        InternalStatus internalStatus = new InternalStatus(StatusCode.UNKNOWN, 500, message);
        return new ResponseEntity<InternalStatus>(internalStatus, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
