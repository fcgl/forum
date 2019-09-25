package com.fcgl.madrid.forum.model.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class GetCityPostsRequest {

    @NotNull
    private Integer cityId;
    @NotNull
    private Integer page;
    @NotNull
    private Integer size;

    private Long userId;


    public GetCityPostsRequest(Integer cityId, Integer page, Integer size, Long userId) {
        this.cityId = cityId;
        this.page = page;
        this.size = size;
        this.userId = userId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
