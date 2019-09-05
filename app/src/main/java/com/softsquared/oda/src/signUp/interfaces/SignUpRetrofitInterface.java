package com.softsquared.oda.src.signUp.interfaces;


import com.softsquared.oda.src.signUp.models.SignUpResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SignUpRetrofitInterface {

    //    @GET("/test")

//    @GET("/jwt")
//    Call<MainResponse> getTest();
//
//    @GET("/test/{number}")
//    Call<MainResponse> getTestPathAndQuery(
//            @Path("number") int number,
//            @Query("content") final String content
//    );
//
//    @POST("/test")
//    Call<MainResponse> postTest(@Body RequestBody params);

    @GET("/id")
    Call<SignUpResponse> idDupCheck(
            @Query("id") final String id
    );
    @POST("/user")
    Call<SignUpResponse> signUpCall(@Body RequestBody params);


}
