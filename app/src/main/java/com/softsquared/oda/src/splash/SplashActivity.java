package com.softsquared.oda.src.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.login.LoginActivity;
import com.softsquared.oda.src.main.MainActivity;
import com.softsquared.oda.src.splash.Interfaces.SplashActivityView;
import com.softsquared.odaproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import static com.softsquared.oda.src.ApplicationClass.sSharedPreferences;


public class SplashActivity extends BaseActivity implements SplashActivityView {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        //자동 로그인 기능 활성화

        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 1000); // 1초 후에 hd handler 실행



    }


    private void tryAutoLogin() {

        final SplashService splashService = new SplashService(this);
        JSONObject params = new JSONObject();
        splashService.getAutoLoginCheck(params);
    }

    @Override
    public void validateSuccess(String text) {
        startActivity(new Intent(getApplication(), MainActivity.class)); //로딩이 끝난 후, MainActivity로 이동
        SplashActivity.this.finish(); // 로딩페이지 Activity 제거
    }

    @Override
    public void validateFailure(String message) {

        startActivity(new Intent(getApplication(), LoginActivity.class)); //로딩이 끝난 후, LoginActivity 이동
        SplashActivity.this.finish(); // 로딩페이지 Activity 제거
    }

    private class splashhandler implements Runnable {
        public void run() {
            if (sSharedPreferences.getBoolean("auto", false)) {
                tryAutoLogin();
            } else {
                validateFailure(null);
            }
        }
    }

    @Override
    public void onBackPressed() {
        //초반 플래시 화면에서 넘어갈때 뒤로가기 버튼 못누르게 함
    }

}

