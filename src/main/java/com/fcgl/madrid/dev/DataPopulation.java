package com.fcgl.madrid.dev;

import com.fcgl.madrid.forum.dataModel.Comment;
import com.fcgl.madrid.forum.dataModel.Post;
import com.fcgl.madrid.forum.dataModel.UserLike;
import com.fcgl.madrid.forum.repository.CommentRepository;
import com.fcgl.madrid.forum.repository.PostRepository;
import com.fcgl.madrid.forum.repository.UserLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import org.springframework.transaction.annotation.Transactional;

@Service
/**
 * Will only be used in Dev environment.
 */
public class DataPopulation {

    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private UserLikeRepository userLikeRepository;

    @Autowired
    public DataPopulation(PostRepository postRepository, CommentRepository commentRepository, UserLikeRepository userLikeRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userLikeRepository = userLikeRepository;
    }

    @Transactional
    @PostConstruct
    public void init() {
        Post post1 = new Post("Title 1", "This is test number one", 1, new Long(1), "Jean Paul");
        Post post2 = new Post("Title 2", "This is test number two", 1, new Long(2), "Miles");
        Post post3 = new Post("Title 3", "This is test number three", 1, new Long(3), "Ben");
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
        Comment comment1 = new Comment("Testing Comment #1 for post 1", post1, new Long(1), "Jean Paul");
        Comment comment2 = new Comment("Testing Comment #2 for post 1", post1, new Long(2), "Miles");
        Comment comment3 = new Comment("Testing Comment #3 for post 1", post1, new Long(5), "Ben");
        Comment comment4 = new Comment("Testing Comment #4 for post 1", post1, new Long(3), "Nat");
        Comment comment5 = new Comment("Testing Comment #5 for post 1", post1, new Long(4), "Peter");
        commentRepository.save(comment1);
        commentRepository.save(comment2);
        commentRepository.save(comment3);
        commentRepository.save(comment4);
        commentRepository.save(comment5);
        UserLike userLike1 = new UserLike(1L, post1);
        UserLike userLike2 = new UserLike(2L, post1);
        UserLike userLike3 = new UserLike(3L, post1);
        UserLike userLike4 = new UserLike(4L, post1);
        userLikeRepository.save(userLike1);
        userLikeRepository.save(userLike2);
        userLikeRepository.save(userLike3);
        userLikeRepository.save(userLike4);

    }
}
