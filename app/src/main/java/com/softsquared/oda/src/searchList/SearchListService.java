package com.softsquared.oda.src.searchList;

import com.softsquared.oda.src.searchList.interfaces.SearchListActivityView;
import com.softsquared.oda.src.searchList.interfaces.SearchListRetrofitInterface;
import com.softsquared.oda.src.searchList.models.SearchListResponse;
import com.softsquared.oda.src.signUp.interfaces.SignUpActivityView;
import com.softsquared.oda.src.signUp.interfaces.SignUpRetrofitInterface;
import com.softsquared.oda.src.signUp.models.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.oda.src.ApplicationClass.getRetrofit;

public class SearchListService {

    private final SearchListActivityView mSearchListActivityView;

    SearchListService(final SearchListActivityView searchListActivityView) {
        this.mSearchListActivityView = searchListActivityView;
    }

    void getSearchResult(String pName) {

        final SearchListRetrofitInterface searchListRetrofitInterface = getRetrofit().create(SearchListRetrofitInterface.class);
        searchListRetrofitInterface.getSearchList(pName).enqueue(new Callback<SearchListResponse>() {
            @Override
            public void onResponse(Call<SearchListResponse> call, Response<SearchListResponse> response) {
                if (response == null) {
                    mSearchListActivityView.validateFailure("중복확인실패");
                    return;
                }
                final SearchListResponse searchListResponse = response.body();

                if (searchListResponse == null) {
                    mSearchListActivityView.validateFailure(null);
                    return;
                } else if (searchListResponse.getCode() == 300) {
                    //중복된 ID가 없을시 반환 코드
                    mSearchListActivityView.vaildateSuccess(searchListResponse.getMessage(),searchListResponse.getSearchListItems());
                } else {
                    mSearchListActivityView.validateFailure(searchListResponse.getMessage());
                }

            }

            //다른 네트워크 다른 함수
            //enum써라

            @Override
            public void onFailure(Call<SearchListResponse> call, Throwable t) {
                mSearchListActivityView.validateFailure(null);
            }
        });


    }
}
