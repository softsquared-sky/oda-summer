package com.softsquared.oda.src.myPage.Interfaces;

import com.softsquared.oda.src.detail.productDetail.models.ProductDetailResponse;
import com.softsquared.oda.src.myPage.Models.MyPageResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyPageRetrofitInterface {



    @GET("/mypage")
    Call<MyPageResponse> getMyPage();
}
