package com.softsquared.oda.src.searchList.interfaces;

import com.softsquared.oda.src.search.popular.models.PopularResponse;
import com.softsquared.oda.src.searchList.models.SearchListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchListRetrofitInterface {

    @GET("/product")
    Call<SearchListResponse> getSearchList(@Query("pName") String pName);

}
