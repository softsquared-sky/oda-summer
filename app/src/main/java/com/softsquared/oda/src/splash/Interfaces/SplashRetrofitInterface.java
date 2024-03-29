package com.softsquared.oda.src.splash.Interfaces;

import com.softsquared.oda.src.signUp.models.SignUpResponse;
import com.softsquared.oda.src.splash.models.SplashResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SplashRetrofitInterface {


    @POST("/productReview")
    Call<SplashResponse> autoLoginAccess(@Body RequestBody params);

}
