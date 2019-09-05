package com.softsquared.oda.src.login.interfaces;

import com.softsquared.oda.src.login.models.LoginResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginRetrofitInterface {

    //    @GET("/test")
//    @GET("/jwt")
//    Call<MainResponse> getTest();
//
//    @GET("/test/{number}")
//    Call<MainResponse> getTestPathAndQuery(
//            @Path("number") int number,
//            @Query("content") final String content
//    );

    @POST("/user/token")
    Call<LoginResponse> postLoginAccess(@Body RequestBody params);
}
