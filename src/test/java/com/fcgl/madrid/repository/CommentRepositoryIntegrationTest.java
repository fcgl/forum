package com.fcgl.madrid.repository;

import com.fcgl.madrid.forum.dataModel.Comment;
import com.fcgl.madrid.forum.dataModel.Post;
import com.fcgl.madrid.forum.repository.CommentRepository;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void findByIdTest() {
        Post post1 = new Post("Looking for unit tests", "Anyone see any units tests in Madrid?", 1, new Long(1), "Jean Paul");
        entityManager.persist(post1);
        entityManager.flush();
        Long postId = post1.getId();
        Comment comment1 = new Comment("I found a unit test", post1, new Long(20), "Max");
        entityManager.persist(comment1);
        entityManager.flush();
        Long commentId = comment1.getId();
        Comment found = commentRepository.findById(commentId).get();
        assertEquals(found.getMessage(), comment1.getMessage());
    }
}
