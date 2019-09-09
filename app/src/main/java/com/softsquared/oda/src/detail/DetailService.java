package com.softsquared.oda.src.detail;

import com.softsquared.oda.src.detail.interfaces.DetailActivityView;
import com.softsquared.oda.src.detail.interfaces.DetailRetrofitInterface;
import com.softsquared.oda.src.detail.models.DetailResponse;

import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.oda.src.ApplicationClass.MEDIA_TYPE_JSON;
import static com.softsquared.oda.src.ApplicationClass.getRetrofit;

public class DetailService {

    private final DetailActivityView mDetailActivityView;

    DetailService(final DetailActivityView detailActivityView) {
        this.mDetailActivityView = detailActivityView;
    }


    void postBasket(JSONObject params) {
        final DetailRetrofitInterface detailRetrofitInterface = getRetrofit().create(DetailRetrofitInterface.class);
        detailRetrofitInterface.postBasket(RequestBody.create(params.toString(), MEDIA_TYPE_JSON)).enqueue(new Callback<DetailResponse>() {
            @Override
            public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {
                if (response == null) {
                    mDetailActivityView.validateFailure("장바구니에 담을 수 없습니다.");
                    return;
                }
                final DetailResponse detailResponse = response.body();

                if (detailResponse == null) {
                    mDetailActivityView.validateFailure(null);

                } else if (detailResponse.getCode() == 800) {
                    //장바구니 등록 성공
                    mDetailActivityView.validateSuccess(detailResponse.getMessage());
                }
                else {
                    mDetailActivityView.validateFailure(detailResponse.getMessage());
                }
                return;
            }

            @Override
            public void onFailure(Call<DetailResponse> call, Throwable t) {
                mDetailActivityView.validateFailure(null);
            }
        });
    }
}
