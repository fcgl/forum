package com.fcgl.madrid.dev;

import com.fcgl.madrid.forum.dataModel.Comment;
import com.fcgl.madrid.forum.dataModel.Post;
import com.fcgl.madrid.forum.repository.CommentRepository;
import com.fcgl.madrid.forum.repository.PostRepository;
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

    @Autowired
    public DataPopulation(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    @PostConstruct
    public void init() {
        Post post1 = new Post("Title 1", "This is test number one", 1);
        Post post2 = new Post("Title 2", "This is test number two", 1);
        Post post3 = new Post("Title 3", "This is test number three", 1);
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
        Comment comment1 = new Comment("Testing Comment for post 1", post1);
        commentRepository.save(comment1);
    }
}
