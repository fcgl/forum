package com.fcgl.madrid.forum.model;

public class PostRequest {

    private String title;
    private String description;
    private Integer cityId;
    private Integer userId;

    /**
     * Request Parameters for post
     * @param title
     * @param description
     * @param cityId
     * @param userId
     */
    public PostRequest(String title, String description, Integer cityId, Integer userId) {
        this.cityId = cityId;
        this.title = title;
        this.description = description;
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
