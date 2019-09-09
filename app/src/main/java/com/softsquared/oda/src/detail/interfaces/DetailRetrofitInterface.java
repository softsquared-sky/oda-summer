package com.softsquared.oda.src.detail.interfaces;

import com.softsquared.oda.src.detail.models.DetailResponse;
import com.softsquared.oda.src.main.models.MainResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DetailRetrofitInterface {


    @POST("/basket")
    Call<DetailResponse> postBasket(@Body RequestBody params);

}
