package com.fcgl.madrid.forum.repository;

import com.fcgl.madrid.forum.dataModel.Post;
import com.fcgl.madrid.forum.dataModel.UserLike;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserLikeRepository extends JpaRepository<UserLike, Long>  {

    UserLike findUserLikeByUserIdAndPostId(Long userId, Long postId);

    Integer countByPostId(Long postId);
}
