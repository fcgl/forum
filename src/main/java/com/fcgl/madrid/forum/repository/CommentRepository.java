package com.fcgl.madrid.forum.repository;

import com.fcgl.madrid.forum.dataModel.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostId(Long postId, Pageable pageable);

    Integer countByPost(Long postId);
}
