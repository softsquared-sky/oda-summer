package com.softsquared.oda.src.login.interfaces;

import com.softsquared.oda.src.login.models.LoginResponse;
import com.softsquared.oda.src.main.models.DefaultResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LoginRetrofitInterface {

    //    @GET("/test")
//    @GET("/jwt")
//    Call<DefaultResponse> getTest();
//
//    @GET("/test/{number}")
//    Call<DefaultResponse> getTestPathAndQuery(
//            @Path("number") int number,
//            @Query("content") final String content
//    );

    @POST("/user/token")
    Call<LoginResponse> loginAccess(@Body RequestBody params);
}
