package com.softsquared.oda.src.main;



import com.softsquared.oda.src.main.interfaces.MainActivityView;
import com.softsquared.oda.src.main.interfaces.MainRetrofitInterface;
import com.softsquared.oda.src.main.models.MainResponse;

import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.oda.src.ApplicationClass.MEDIA_TYPE_JSON;
import static com.softsquared.oda.src.ApplicationClass.getRetrofit;


class MainService {
    private final MainActivityView mMainActivityView;

    MainService(final MainActivityView mainActivityView) {
        this.mMainActivityView = mainActivityView;
    }


    void postQuickOrder(JSONObject params) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.postQuickOrder(RequestBody.create(params.toString(), MEDIA_TYPE_JSON)).enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if (response == null) {
                    mMainActivityView.validateQuickOrderFailure("바로 주문 실패");
                    return;
                }
                final MainResponse mainResponse = response.body();

                if (mainResponse == null) {
                    mMainActivityView.validateQuickOrderFailure(null);

                } else if (mainResponse.getCode() == 1300) {
                    //바로주문 등록 성공
                    mMainActivityView.validateQuickOrderSuccess(mainResponse.getMessage());
                }
                else {
                    mMainActivityView.validateQuickOrderFailure(mainResponse.getMessage());
                }
                return;
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                mMainActivityView.validateQuickOrderFailure(null);
            }
        });
    }

    void getProductInfo(int productId) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getBasicProductInfo(productId).enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if (response == null) {
                    mMainActivityView.validateFailure("제품의 상세정보가 없습니다.");
                    return;
                }
                final MainResponse mainResponse = response.body();

                if (mainResponse == null) {
                    mMainActivityView.validateFailure(null);

                } else if (mainResponse.getCode() == 400) {
                    //중복된 ID가 없을시 반환 코드
                    mMainActivityView.validateSuccess(mainResponse.getMainRecyclerViewItems());
                }
                else {
                    mMainActivityView.validateFailure(null);
                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
            }
        });
    }
}
