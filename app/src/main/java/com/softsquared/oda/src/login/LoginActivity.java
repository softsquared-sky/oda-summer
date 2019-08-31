package com.softsquared.oda.src.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;

import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.login.interfaces.LoginActivityView;
import com.softsquared.oda.src.main.MainActivity;
import com.softsquared.oda.src.search.SearchActivity;
import com.softsquared.oda.src.signUp.SignUpActivity;
import com.softsquared.odaproject.R;

import org.json.JSONException;

import static com.softsquared.oda.src.ApplicationClass.sSharedPreferences;
import static java.security.AccessController.getContext;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginActivityView {

    EditText mEtId,mEtPassword;
    Button mBtnAutoLogin;
    TextView mTvFindPassword;
    Button mBtnLogin,mBtnJoin;
    String mLoginId,mLoginPassword;

    private boolean mAutoLoginCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //findViewById

        mEtId = (EditText)findViewById(R.id.et_login_id);
        mEtPassword =  (EditText)findViewById(R.id.et_login_password);
        mBtnAutoLogin =  (Button) findViewById(R.id.auto_login);
        mTvFindPassword =  (TextView)findViewById(R.id.find_password);
        mBtnLogin =  (Button)findViewById(R.id.btn_login);
        mBtnJoin =  (Button)findViewById(R.id.btn_join);

        mBtnAutoLogin.setOnClickListener(this);
        mTvFindPassword.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mBtnJoin.setOnClickListener(this);

        //저장되어있는 아이디와 비밀번호를 가져온다.
        //처음에는 null 값이 들어온다.
        mLoginId = sSharedPreferences.getString("inputId",null);
        mLoginPassword = sSharedPreferences.getString("inputPwd",null);
        mAutoLoginCheck = sSharedPreferences.getBoolean("auto",false);

        mEtId.setText(mLoginId);
        mEtPassword.setText(mLoginPassword);
        mBtnAutoLogin.setSelected(mAutoLoginCheck);

        //자동 로그인 기능 활성화
        if(mBtnAutoLogin.isSelected()){
            tryGetToken();
        }
//
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    private void tryGetToken()  {
        showProgressDialog();

        //edittext의 아이디 비번 담아오기
        mLoginId=mEtId.getText().toString();
        mLoginPassword=mEtPassword.getText().toString();

        final LoginService loginService = new LoginService(this);
        try {
            //로그인 서비스 API
            loginService.getLoginAccess(mLoginId,mLoginPassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void validateSuccess(String text) {
        //로그인 체크 후 자동로그인 체크가 되어있을시에 상태들을 저장해야한다.
        //로그인 성공하지도 않았는데 자동로그인 상태를 저장하는건 말이 되지 않는다.
        //그건 아이디 기억하기에 해당하는 기능이다
        if(mBtnAutoLogin.isSelected()){
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            editor.putString("inputId", mLoginId);
            editor.putString("inputPwd", mLoginPassword);
            editor.putBoolean("auto",mBtnAutoLogin.isSelected());
            editor.commit();
        }
        //로그인 성공시 로그인 페이지 닫고 main 페이지 이동
        hideProgressDialog();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
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
                if(mBtnAutoLogin.isSelected()){
                    mBtnAutoLogin.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_checkbox,0,0,0);
                }
                else{
                    mBtnAutoLogin.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_checkbox_off,0,0,0);
                }
                Toast.makeText(this, "auto_login", Toast.LENGTH_SHORT).show();

                break;
            case R.id.find_password:
                Toast.makeText(this, "find_password", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_login:
                tryGetToken();
                break;
            case R.id.btn_join:
                Toast.makeText(this, "btn_join", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                break;
            default:
                break;
        }
    }


}
