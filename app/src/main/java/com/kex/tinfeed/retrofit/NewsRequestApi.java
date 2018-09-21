package com.kex.tinfeed.retrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.kex.tinfeed.retrofit.response.BaseResponse;

public interface NewsRequestApi {
    @GET("top-headlines")
    Observable<com.kex.tinfeed.retrofit.response.BaseResponse> getNewsByCountry(@Query("country") String country);
}
