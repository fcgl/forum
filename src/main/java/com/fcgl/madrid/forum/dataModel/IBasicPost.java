package com.fcgl.madrid.forum.dataModel;

/**
 * POST without comments
 * Used in queries
 */
public interface IBasicPost {
    Long getId();
    String getTitle();
    String getDescription();
    long getCreatedDate();
    long getUpdatedDate();
    Integer getLikes();
    Integer getDislikes();
    Integer getCityId();
    Integer getUserId();

}
