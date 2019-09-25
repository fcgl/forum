package com.fcgl.madrid.forum.dataModel;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.Instant;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String message;
    private long createdDate;
    private long updatedDate;
    private int likes;
    private int dislikes;
    @NotNull
    private Long userId;
    private String firstName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @NotNull
    @JoinColumn(name = "postId")
    private Post post;

    @PreUpdate
    public void preUpdate() {
        this.updatedDate = Instant.now().toEpochMilli();
    }
    public Comment(String message, Post post, Long userId, String firstName) {
        this.userId = userId;
        this.message = message;
        this.post = post;
        this.createdDate = Instant.now().toEpochMilli();
        this.updatedDate = this.createdDate;
        this.likes = 0;
        this.dislikes = 0;
        this.firstName = firstName;
    }

    public Comment() {

    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(long updatedDate) {
        this.updatedDate = updatedDate;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public Post getPost() {
        return post;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
