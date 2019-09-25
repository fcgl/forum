package com.fcgl.madrid.forum.dataModel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.Instant;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Post table
 * params userLikeCount and userCommentCount follow the eventually consistent pattern.
 * Will be updated when a user requests the data and it's been over 1 minute since the last update
 */
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min = 3, max = 40)
    private String title;
    private String description;
    private long createdDate;
    private long updatedDate;
    @NotNull
    private Integer cityId;//TODO: Think about location better than this
    @NotNull
    private Long userId;
    private Integer userLikeCount;
    private Integer userCommentCount;
    private String userFirstName;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Comment> comment;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<UserLike> userLikes;

    @PreUpdate
    public void preUpdate() {
        this.updatedDate = Instant.now().toEpochMilli();
    }

    public Post() {
    }

    public Post(String title, String description, Integer cityId, Long userId, String userFirstName) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.cityId = cityId;
        this.userFirstName = userFirstName;
        this.createdDate = Instant.now().toEpochMilli();
        this.updatedDate = this.createdDate;
        this.comment = new ArrayList<Comment>();
        this.userLikes = new ArrayList<UserLike>();
        this.userLikeCount = 0;
        this.userCommentCount = 0;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(long updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getCityId() {
        return cityId;
    }

    public Long getUserId() {
        return userId;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public List<UserLike> getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(List<UserLike> userLikes) {
        this.userLikes = userLikes;
    }

    public Integer getUserLikeCount() {
        return userLikeCount;
    }

    public void setUserLikeCount(Integer userLikeCount) {
        this.userLikeCount = userLikeCount;
    }

    public Integer getUserCommentCount() {
        return userCommentCount;
    }

    public void setUserCommentCount(Integer userCommentCount) {
        this.userCommentCount = userCommentCount;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }
}
