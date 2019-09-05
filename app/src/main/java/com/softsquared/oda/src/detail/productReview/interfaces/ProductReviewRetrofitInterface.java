package com.softsquared.oda.src.detail.productReview.interfaces;

import com.softsquared.oda.src.detail.productDetail.models.ProductDetailResponse;
import com.softsquared.oda.src.detail.productReview.models.ProductReviewResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductReviewRetrofitInterface {


    @GET("/productReview")
    Call<ProductReviewResponse> getProductReview(@Query("pNum")  int productId);
}
