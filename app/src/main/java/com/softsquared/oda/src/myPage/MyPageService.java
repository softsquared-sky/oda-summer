package com.softsquared.oda.src.myPage;

import com.softsquared.oda.src.main.interfaces.MainActivityView;
import com.softsquared.oda.src.main.interfaces.MainRetrofitInterface;
import com.softsquared.oda.src.main.models.MainResponse;
import com.softsquared.oda.src.myPage.Interfaces.MyPageActivityView;
import com.softsquared.oda.src.myPage.Interfaces.MyPageRetrofitInterface;
import com.softsquared.oda.src.myPage.Models.MyPageResponse;

import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.oda.src.ApplicationClass.MEDIA_TYPE_JSON;
import static com.softsquared.oda.src.ApplicationClass.getRetrofit;

public class MyPageService {
    private final MyPageActivityView mMyPageActivityView;

    MyPageService(final MyPageActivityView myPageActivityView) {
        this.mMyPageActivityView = myPageActivityView;
    }


    void getMyPage() {
        final MyPageRetrofitInterface myPageRetrofitInterface = getRetrofit().create(MyPageRetrofitInterface.class);
        myPageRetrofitInterface.getMyPage().enqueue(new Callback<MyPageResponse>() {
            @Override
            public void onResponse(Call<MyPageResponse> call, Response<MyPageResponse> response) {
                if (response == null) {
                    mMyPageActivityView.validateFailure("바로 주문 실패");
                    return;
                }
                final MyPageResponse mainResponse = response.body();

                if (mainResponse == null) {
                    mMyPageActivityView.validateFailure(null);

                } else if (mainResponse.getCode() == 1100) {
                    //바로주문 등록 성공
                    mMyPageActivityView.validateSuccess(mainResponse.getMessage(),mainResponse.getMyPageListData());

                }
                else {
                    mMyPageActivityView.validateFailure(mainResponse.getMessage());
                }
                return;
            }

            @Override
            public void onFailure(Call<MyPageResponse> call, Throwable t) {
                mMyPageActivityView.validateFailure(null);
            }
        });
    }
}
