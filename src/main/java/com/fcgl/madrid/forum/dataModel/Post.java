package com.fcgl.madrid.forum.dataModel;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.Instant;
import java.util.List;
import java.util.ArrayList;


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
    private Integer likes;
    private Integer dislikes;
    @NotNull
    private Integer cityId;//TODO: Think about location better than this
    @NotNull
    private Integer userId;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Comment> comment;
    private Integer commentSize;

    public Post() {
    }

    public Post(String title, String description, Integer cityId, Integer userId) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.cityId = cityId;
        this.likes = new Integer(0);
        this.dislikes = new Integer(0);
        this.createdDate = Instant.now().toEpochMilli();
        this.updatedDate = this.createdDate;
        this.comment = new ArrayList<Comment>();
        this.commentSize = new Integer(0);//Optimized so that POST reads are faster
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

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public Integer getCityId() {
        return cityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public Integer getCommentSize() {
        return commentSize;
    }

    public void setCommentSize(Integer commentSize) {
        this.commentSize = commentSize;
    }
}
