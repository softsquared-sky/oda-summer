package com.softsquared.oda.src.search.popular;

import com.softsquared.oda.src.search.popular.interfaces.PopularFragmentView;
import com.softsquared.oda.src.search.popular.interfaces.PopularRetrofitInterface;
import com.softsquared.oda.src.search.popular.models.PopularResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.oda.src.ApplicationClass.getRetrofit;

public class PopularFragmentService {

    private final PopularFragmentView mPopularFragmentView;

    PopularFragmentService(final PopularFragmentView popularFragmentView) {
        this.mPopularFragmentView = popularFragmentView;
    }

    public void getPopularList() {
        final PopularRetrofitInterface popularRetrofitInterface = getRetrofit().create(PopularRetrofitInterface.class);
        popularRetrofitInterface.getPopularList().enqueue(new Callback<PopularResponse>() {
            @Override
            public void onResponse(Call<PopularResponse> call, Response<PopularResponse> response) {
                if (response == null) {
                    mPopularFragmentView.validateFailure(null);
                    return;
                }
                final PopularResponse popularResponse = response.body();

                if (popularResponse == null) {
                    mPopularFragmentView.validateFailure(null);
                    return;
                } else if (popularResponse.getCode() == 1600) {
                    //중복된 ID가 없을시 반환 코드
                    mPopularFragmentView.vaildateSuccess(popularResponse.getResult());
                } else {
                    mPopularFragmentView.validateFailure(popularResponse.getMessage());
                }

            }

            @Override
            public void onFailure(Call<PopularResponse> call, Throwable t) {
                mPopularFragmentView.validateFailure(null);
            }
        });

    }
}
