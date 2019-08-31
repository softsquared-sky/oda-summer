package com.softsquared.oda.src.login;

import com.softsquared.oda.src.login.interfaces.LoginActivityView;
import com.softsquared.oda.src.login.interfaces.LoginRetrofitInterface;
import com.softsquared.oda.src.login.models.LoginResponse;
import com.softsquared.oda.src.ApplicationClass;
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

    void getLoginAccess(String id,String pw) throws JSONException {
        JSONObject params = new JSONObject();
        params.put("id",id);
        params.put("pw",pw);

        final LoginRetrofitInterface LoginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        LoginRetrofitInterface.loginAccess(RequestBody.create(params.toString(),MEDIA_TYPE_JSON)).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                final LoginResponse loginResponse = response.body();
                if (loginResponse == null) {
                    mLoginActivityView.validateFailure(null);
                }
                else if(loginResponse.getCode()==1200){
                    //로그인 성공 code 1200
                    mLoginActivityView.validateSuccess(loginResponse.getMessage());
//                    ApplicationClass.X_ACCESS_TOKEN=loginResponse.getResult().getJwt();
                }
                else{
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
