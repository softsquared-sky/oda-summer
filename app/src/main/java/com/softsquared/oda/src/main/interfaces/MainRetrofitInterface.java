package com.softsquared.oda.src.main.interfaces;


import com.softsquared.oda.src.main.models.MainResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MainRetrofitInterface {
    //    @GET("/test")
//    @GET("/jwt")
//    Call<MainResponse> getTest();
//
//    @GET("/test/{number}")
//    Call<MainResponse> getTestPathAndQuery(
//            @Path("number") int number,
//            @Query("content") final String content
//    );

    @POST("/directOrder")
    Call<MainResponse> postQuickOrder(@Body RequestBody params);

    @GET("/product/productNum")
    Call<MainResponse> getBasicProductInfo(@Query("pNum") int productId );
}
