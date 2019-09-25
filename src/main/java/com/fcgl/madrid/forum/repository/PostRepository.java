package com.fcgl.madrid.forum.repository;

import com.fcgl.madrid.forum.dataModel.Post;
import com.fcgl.madrid.forum.dataModel.IBasicPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * Queries the database for Post data without comments
     * @param id Long: The id for the post being queried
     * @return IBasicPost
     */
    @Query("SELECT u FROM Post u WHERE u.id = ?1")
    IBasicPost getPost(Long id);

    List<IBasicPost> findAllByCityIdOrderByCreatedDateDesc(Integer cityId, Pageable pageable);
}
