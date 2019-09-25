package com.fcgl.madrid.forum.model.request;

public class PostRequest {

    private String title;
    private String description;
    private Integer cityId;
    private Long userId;
    private String userFirstName;

    /**
     * Request Parameters for post
     * @param title
     * @param description
     * @param cityId
     * @param userId
     */
    public PostRequest(String title, String description, Integer cityId, Long userId, String userFirstName) {
        this.cityId = cityId;
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.userFirstName = userFirstName;
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

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }
}
