package com.softsquared.oda.src.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;

import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.login.interfaces.LoginActivityView;
import com.softsquared.oda.src.main.MainActivity;
import com.softsquared.oda.src.signUp.SignUpActivity;
import com.softsquared.odaproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import static com.softsquared.oda.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softsquared.oda.src.ApplicationClass.sSharedPreferences;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginActivityView {

    EditText mEtId, mEtPassword;
    Button mBtnAutoLogin;
    TextView mTvFindPassword;
    Button mBtnLogin, mBtnJoin;
    String mLoginId, mLoginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //findViewById

        mEtId = findViewById(R.id.et_login_id);
        mEtPassword = findViewById(R.id.et_login_password);
        mBtnAutoLogin = findViewById(R.id.auto_login);
        mTvFindPassword = findViewById(R.id.find_password);
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnJoin = findViewById(R.id.btn_join);

        mBtnAutoLogin.setOnClickListener(this);
        mTvFindPassword.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mBtnJoin.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        //재실행시 인텐트를 받아옴
        mEtId.setText(getIntent().getStringExtra("newId"));
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void tryGetToken() {


        //edittext의 아이디 비번 담아오기
        mLoginId = mEtId.getText().toString();
        mLoginPassword = mEtPassword.getText().toString();

        final LoginService loginService = new LoginService(this);
        //View 에서 모든 exception을 처리후 서비스에 넘긴다.
        try {
            //로그인 서비스 API
            JSONObject params = new JSONObject();
            params.put("id", mLoginId);
            params.put("pw", mLoginPassword);
            showProgressDialog();
            loginService.postLoginAccess(params);
        } catch (JSONException e) {
            showCustomToast("id와 비밀번호를 올바르게 입력해주세요");
        }
    }

    @Override
    public void validateSuccess(String text,String jwt) {
        //로그인 체크 후 자동로그인 체크가 되어있을시에 상태들을 저장해야한다.
        //로그인 성공하지도 않았는데 자동로그인 상태를 저장하는건 말이 되지 않는다.
        //그건 아이디 기억하기에 해당하는 기능이다
        SharedPreferences.Editor editor = sSharedPreferences.edit();

        if (mBtnAutoLogin.isSelected()) {
            editor.putBoolean("auto", true);
        }
        //jwt 저장
        editor.putString(X_ACCESS_TOKEN,jwt);
        editor.apply(); // ediot.commit() -> editor.apply(); 변경

        //로그인 성공시 로그인 페이지 닫고 main 페이지 이동
        hideProgressDialog();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
        showCustomToast(text);
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.auto_login:
                mBtnAutoLogin.setSelected(!mBtnAutoLogin.isSelected());
                if (mBtnAutoLogin.isSelected()) {
                    mBtnAutoLogin.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_checkbox, 0, 0, 0);
                } else {
                    mBtnAutoLogin.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_checkbox_off, 0, 0, 0);
                }

                break;
            case R.id.find_password:
                showCustomToast(getString(R.string.no_access));
                break;
            case R.id.btn_login:
                tryGetToken();
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));

                break;
            case R.id.btn_join:
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                break;
            default:
                break;
        }
    }


}
