package com.fcgl.madrid.forum.repository;

import com.fcgl.madrid.forum.dataModel.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
