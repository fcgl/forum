package com.fcgl.madrid.forum.model.request;

public class PostRequest {

    private String title;
    private String description;
    private Integer cityId;
    private Long userId;

    /**
     * Request Parameters for post
     * @param title
     * @param description
     * @param cityId
     * @param userId
     */
    public PostRequest(String title, String description, Integer cityId, Long userId) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
