package com.softsquared.oda.src.login;

import com.softsquared.oda.src.login.interfaces.LoginActivityView;
import com.softsquared.oda.src.login.interfaces.LoginRetrofitInterface;
import com.softsquared.oda.src.login.models.LoginResponse;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.oda.src.ApplicationClass.MEDIA_TYPE_JSON;
import static com.softsquared.oda.src.ApplicationClass.getRetrofit;

public class LoginService {

    private final LoginActivityView mLoginActivityView;

    LoginService(final LoginActivityView loginActivityView) {
        this.mLoginActivityView = loginActivityView;
    }

    void postLoginAccess(JSONObject params) {

        final LoginRetrofitInterface LoginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        LoginRetrofitInterface.postLoginAccess(RequestBody.create(params.toString(), MEDIA_TYPE_JSON)).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response == null) {
                    mLoginActivityView.validateFailure("response값이 null");
                    return;
                }
                final LoginResponse loginResponse = response.body();
                if (loginResponse == null) {
                    mLoginActivityView.validateFailure(null);
                    return;
                } else if (loginResponse.getCode() == 1200) {
                    //로그인 성공 code 1200
                    mLoginActivityView.validateSuccess(loginResponse.getMessage(), loginResponse.getResult().getJwt());
                } else {
                    mLoginActivityView.validateFailure(loginResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mLoginActivityView.validateFailure(null);
            }
        });
    }
}
