package com.fcgl.madrid.forum.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostRequest {

    @NotEmpty
    @Size(min = 3, max = 60)
    private String title;
    private String description;
    @NotNull
    private Integer cityId;
    @NotNull
    private Long userId;
    @NotNull
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
