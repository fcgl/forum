package com.fcgl.madrid.forum.repository;

import com.fcgl.madrid.forum.dataModel.Comment;
import com.fcgl.madrid.forum.dataModel.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostId(Long postId, Pageable pageable);

    Integer countByPostId(Long postId);
}
