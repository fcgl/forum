package com.fcgl.madrid.forum.service;

import com.fcgl.madrid.forum.dataModel.Post;
import com.fcgl.madrid.forum.dataModel.IBasicPost;
import com.fcgl.madrid.forum.dataModel.UserLike;
import com.fcgl.madrid.forum.model.request.GetCityPostsRequest;
import com.fcgl.madrid.forum.model.request.PostLikeRequest;
import com.fcgl.madrid.forum.model.response.*;
import com.fcgl.madrid.forum.model.request.PostRequest;
import com.fcgl.madrid.forum.repository.CommentRepository;
import com.fcgl.madrid.forum.repository.PostRepository;

import com.fcgl.madrid.forum.repository.UserLikeRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionSystemException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Optional;


import java.lang.Exception;
import java.lang.Throwable;
import java.lang.StringBuilder;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import static com.fcgl.madrid.forum.service.CommentService.getInternalStatusResponseEntity;

@Service
public class PostService implements IPostService {

    private PostRepository postRepository;
    private UserLikeRepository userLikeRepository;
    private CommentRepository commentRepository;
    private Log logger;


    @Autowired
    public PostService(PostRepository postRepository, UserLikeRepository userLikeRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userLikeRepository = userLikeRepository;
        this.commentRepository = commentRepository;
        this.logger = LogFactory.getLog(PostService.class);

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
                    postRequest.getUserId(),
                    postRequest.getUserFirstName()
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

    @CircuitBreaker(name = "backendA", fallbackMethod = "fallback")
    public ResponseEntity<GetCityPostsResponse> getCityPosts(GetCityPostsRequest request) {
        Pageable pageConfig = PageRequest.of(request.getPage(), request.getSize());
        List<IBasicPost> posts = postRepository.findAllByCityIdOrderByCreatedDateDesc(request.getCityId(), pageConfig);
        List<UserLikePost> userLikePosts = updatePostAttribute(posts, request.getUserId());
        GetCityPostsResponse response = new GetCityPostsResponse(InternalStatus.OK, userLikePosts);
        return new ResponseEntity<GetCityPostsResponse>(response, HttpStatus.OK);
    }

    private List<UserLikePost> updatePostAttribute(List<IBasicPost> posts, Long userId) {
        long oneMinuteAgo = Instant.now().toEpochMilli() - 60000;
        List<UserLikePost> userLikePosts = new ArrayList<>();
        for (IBasicPost iBasicPost : posts) {
            UserLikePost userLikePost = new UserLikePost(iBasicPost, false);
            if (iBasicPost.getUpdatedDate() <= oneMinuteAgo) {
                long postId = iBasicPost.getId();
                Post post = postRepository.findById(postId).get();
                int likeCount = userLikeRepository.countByPostId(postId);
                int commentCount = commentRepository.countByPostId(postId);
                post.setUserLikeCount(likeCount);
                post.setUserCommentCount(commentCount);
                postRepository.save(post);
            }
            if (userLikeRepository.findUserLikeByUserIdAndPostId(userId, iBasicPost.getId()) != null) {
                userLikePost.setLiked(true);
            }
            userLikePosts.add(userLikePost);
        }
        return userLikePosts;
    }

    @CircuitBreaker(name = "backendA", fallbackMethod = "fallback")
    public ResponseEntity<InternalStatus> postLike(PostLikeRequest request) {
        Optional<Post> postOptional = postRepository.findById(request.getPostId());
        if (!postOptional.isPresent()) {
           return new ResponseEntity<InternalStatus>(InternalStatus.NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        Post post = postOptional.get();
        UserLike userLike = userLikeRepository.findUserLikeByUserIdAndPostId(request.getUserId(), request.getPostId());
        if (userLike == null) {
            UserLike newUserLike = new UserLike(request.getUserId(), post);
            userLikeRepository.save(newUserLike);
        } else {
            userLikeRepository.delete(userLike);
        }
        return new ResponseEntity<InternalStatus>(InternalStatus.OK, HttpStatus.OK);
    }

    @CircuitBreaker(name = "backendA", fallbackMethod = "fallbackTwo")
    public ResponseEntity<GetPostInteractionData> getPostLikeCount(Long postId) {
        Integer likeCount = userLikeRepository.countByPostId(postId);
        GetPostInteractionData interactionData = new GetPostInteractionData(likeCount, null, InternalStatus.OK);
        return new ResponseEntity<GetPostInteractionData>(interactionData, HttpStatus.OK);
    }

    @CircuitBreaker(name = "backendA", fallbackMethod = "fallbackTwo")
    public ResponseEntity<GetPostInteractionData> getPostCommentCount(Long postId) {
        Integer commentCount = commentRepository.countByPostId(postId);
        GetPostInteractionData interactionData = new GetPostInteractionData(null, commentCount, InternalStatus.OK);
        return new ResponseEntity<GetPostInteractionData>(interactionData, HttpStatus.OK);
    }

    @CircuitBreaker(name = "backendA", fallbackMethod = "fallbackTwo")
    public ResponseEntity<GetPostInteractionData> getPostInteractionData(Long postId) {
        Integer commentCount = commentRepository.countByPostId(postId);
        Integer likeCount = userLikeRepository.countByPostId(postId);
        GetPostInteractionData interactionData = new GetPostInteractionData(likeCount, commentCount, InternalStatus.OK);
        return new ResponseEntity<GetPostInteractionData>(interactionData, HttpStatus.OK);
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
        InternalStatus internalStatus = new InternalStatus(StatusCode.UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR, message);
        return new ResponseEntity<InternalStatus>(internalStatus, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     *
     * @param ex Exception
     * @return ResponseEntity<GetPostResponse>
     */
    private ResponseEntity<GetPostResponse> fallback(Long postId, Exception ex) {
        String message = "Fallback: " + ex.getMessage();
        InternalStatus internalStatus = new InternalStatus(StatusCode.UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR, message);
        GetPostResponse postResponse = new GetPostResponse(internalStatus, null);
        return new ResponseEntity<GetPostResponse>(postResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     *
     * @param ex Exception
     * @return ResponseEntity<GetCityPostsResponse>
     */
    private ResponseEntity<GetCityPostsResponse> fallback(GetCityPostsRequest request, Exception ex) {
        String message = "Fallback: " + ex.getMessage();
        InternalStatus internalStatus = new InternalStatus(StatusCode.UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR, message);
        GetCityPostsResponse postResponse = new GetCityPostsResponse(internalStatus, null);
        return new ResponseEntity<GetCityPostsResponse>(postResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     *
     * @param ex Exception
     * @return ResponseEntity<GetCityPostsResponse>
     */
    private ResponseEntity<InternalStatus> fallback(PostLikeRequest request, Exception ex) {
        String message = "Fallback: " + ex.getMessage();
        InternalStatus internalStatus = new InternalStatus(StatusCode.UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR, message);
        return new ResponseEntity<InternalStatus>(internalStatus, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     *
     * @param ex Exception
     * @return ResponseEntity<GetCityPostsResponse>
     */
    private ResponseEntity<GetPostInteractionData> fallbackTwo(Long postId, Exception ex) {
        String message = "Fallback: " + ex.getMessage();
        InternalStatus internalStatus = new InternalStatus(StatusCode.UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR, message);
        GetPostInteractionData postResponse = new GetPostInteractionData(null, null, internalStatus);
        return new ResponseEntity<GetPostInteractionData>(postResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
