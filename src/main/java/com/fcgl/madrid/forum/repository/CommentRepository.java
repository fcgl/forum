package com.fcgl.madrid.forum.repository;

import com.fcgl.madrid.forum.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
