package com.softsquared.oda.src.order.interfaces;

import com.softsquared.oda.src.order.models.OrderResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OrderRetrofitInterface {

    @POST("/payment")
    Call<OrderResponse> postPayment(@Body RequestBody params);
}
