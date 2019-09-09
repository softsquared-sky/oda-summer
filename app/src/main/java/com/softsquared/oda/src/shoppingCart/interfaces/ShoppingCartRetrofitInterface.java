package com.softsquared.oda.src.shoppingCart.interfaces;

import com.softsquared.oda.src.shoppingCart.models.ShoppingCartResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ShoppingCartRetrofitInterface {

    @GET("/basket")
    Call<ShoppingCartResponse> getShoppingCart();
}
