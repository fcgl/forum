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

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Comment> comment;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<UserLike> userLikes;

    public Post() {
    }

    public Post(String title, String description, Integer cityId, Long userId) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.cityId = cityId;
        this.createdDate = Instant.now().toEpochMilli();
        this.updatedDate = this.createdDate;
        this.comment = new ArrayList<Comment>();
        this.userLikes = new ArrayList<UserLike>();
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
}
