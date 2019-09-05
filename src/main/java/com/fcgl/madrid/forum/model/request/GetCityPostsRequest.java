package com.fcgl.madrid.forum.model.request;

public class GetCityPostsRequest {

    private Integer cityId;
    private int page;
    private int size;


    public GetCityPostsRequest(Integer cityId, int page, int size) {
        this.cityId = cityId;
        this.page = page;
        this.size = size;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
