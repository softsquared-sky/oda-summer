package com.softsquared.oda.src.search.popular.interfaces;

import com.softsquared.oda.src.search.popular.models.PopularResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PopularRetrofitInterface {

    @GET("/popularWord")
    Call<PopularResponse> getPopularList();
}
