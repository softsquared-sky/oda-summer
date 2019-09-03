package com.softsquared.oda.src.detail.productDetail.interfaces;

import com.softsquared.oda.src.detail.productDetail.models.ProductDetailResponse;
import com.softsquared.oda.src.login.models.LoginResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProductDetailRetrofitInterface {




    @GET("/productDetail")
    Call<ProductDetailResponse> getProductDetail(@Query("pNum")  int pNum);
}
