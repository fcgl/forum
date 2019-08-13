package com.fcgl.madrid.forum.dataModel;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import java.time.Instant;
import java.util.List;
import java.util.ArrayList;


@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private long createdDate;
    private long updatedDate;
    private int likes;
    private int dislikes;
    private int cityId;//TODO: Think about location better than this
    private int userId;

    @OneToMany(mappedBy = "post")
    @JsonManagedReference
    private List<Comment> comment;

    public Post() {
    }

    public Post(String title, String description, int cityId, int userId) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.cityId = cityId;
        this.likes = 0;
        this.dislikes = 0;
        this.createdDate = Instant.now().toEpochMilli();
        this.updatedDate = this.createdDate;
        this.comment = new ArrayList<Comment>();
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getCityId() {
        return cityId;
    }

    public int getUserId() {
        return userId;
    }

    public List<Comment> getComment() {
        return comment;
    }
}
