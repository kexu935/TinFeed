package com.kex.tinfeed.retrofit.response;

import com.kex.tinfeed.retrofit.response.News;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseResponse {
    public String status;
    public int totalResults;
    @SerializedName("articles")
    public List<com.kex.tinfeed.retrofit.response.News> articles;
}
