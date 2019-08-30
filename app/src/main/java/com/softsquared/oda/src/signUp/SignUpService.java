package com.softsquared.oda.src.signUp;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.softsquared.oda.src.signUp.interfaces.SignUpActivityView;
import com.softsquared.oda.src.signUp.interfaces.SignUpRetrofitInterface;
import com.softsquared.oda.src.signUp.models.SignUpResponse;

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

    void getSignUp(String id,String pw,int type,String address) throws JSONException {
        JSONObject params = new JSONObject();
        params.put("id",id);
        params.put("pw",pw);
        params.put("type",type);
        params.put("address",address);

        final SignUpRetrofitInterface signUpRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);
        signUpRetrofitInterface.signUpCall(RequestBody.create(params.toString(),MEDIA_TYPE_JSON)).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                final SignUpResponse signUpResponse = response.body();

                System.out.println(
                      "Code :"+  signUpResponse.getCode()
                      + "Id : "+  signUpResponse.getId()
                       +"isSuccess :"+ signUpResponse.getIsSuccess()
                      + "Message : "+  signUpResponse.getMessage()
                );
                if (signUpResponse == null) {
                    System.out.println("첫번째 if문");
                    mSignUpActivityView.validateFailure(null);
                }
                else if(signUpResponse.getCode()==200){
                    //성공시 반환 코드
                    System.out.println("2번째 if문");

                    mSignUpActivityView.validateSuccess(signUpResponse.getMessage());
                    return;
                }
                else{

                    System.out.println("3번째 if문");
                    mSignUpActivityView.validateFailure(signUpResponse.getMessage());
                }

            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

                System.out.println(t.getCause());
                System.out.println("아예 잘못된 값이 왔습니다");
                mSignUpActivityView.validateFailure(null);
            }
        });
    }
}
