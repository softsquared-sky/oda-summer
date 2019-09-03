package com.softsquared.oda.src.signUp;

import android.provider.Settings;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.softsquared.oda.src.signUp.interfaces.SignUpActivityView;
import com.softsquared.oda.src.signUp.interfaces.SignUpRetrofitInterface;
import com.softsquared.oda.src.signUp.models.SignUpResponse;
import com.softsquared.odaproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.oda.src.ApplicationClass.MEDIA_TYPE_JSON;
import static com.softsquared.oda.src.ApplicationClass.getRetrofit;

class SignUpService {
    private final SignUpActivityView mSignUpActivityView;

    SignUpService(final SignUpActivityView signUpActivityView) {
        this.mSignUpActivityView = signUpActivityView;
    }

    void getDuplicationCheck(String id) {

        final SignUpRetrofitInterface signUpRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);
        signUpRetrofitInterface.idDupCheck(id).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (response == null) {
                    mSignUpActivityView.validateFailure("중복확인실패");
                    return;
                }
                final SignUpResponse signUpResponse = response.body();

                if (signUpResponse == null) {
                    mSignUpActivityView.validateFailure(null);
                    return;
                } else if (signUpResponse.getCode() == 150) {
                    //중복된 ID가 없을시 반환 코드
                    mSignUpActivityView.validateDupSuccess(signUpResponse.getMessage());
                } else {
                    mSignUpActivityView.validateDupFailure(signUpResponse.getMessage());
                }

            }

            //다른 네트워크 다른 함수
            //enum써라

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                mSignUpActivityView.validateFailure(null);
            }
        });


    }

    void postSignUp(JSONObject params) {

        final SignUpRetrofitInterface signUpRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);
        signUpRetrofitInterface.signUpCall(RequestBody.create(params.toString(), MEDIA_TYPE_JSON)).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (response == null) {
                    mSignUpActivityView.validateFailure(response.message());
                    return;
                }
                final SignUpResponse signUpResponse = response.body();
                if (signUpResponse == null) {
                    mSignUpActivityView.validateSignUpFailure(null);
                    return;
                } else if (signUpResponse.getCode() == 200) {
                    //성공시 반환 코드
                    mSignUpActivityView.vaildateSignUpSuccess(signUpResponse.getMessage(), signUpResponse.getId());
                } else {
                    mSignUpActivityView.validateSignUpFailure(signUpResponse.getMessage());
                }
                return;

            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                mSignUpActivityView.validateSignUpFailure(null);
            }
        });
    }
}
