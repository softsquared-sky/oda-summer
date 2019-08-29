package com.softsquared.oda.src.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.main.MainActivity;
import com.softsquared.oda.src.search.SearchActivity;
import com.softsquared.oda.src.signUp.SignUpActivity;
import com.softsquared.odaproject.R;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    EditText mEtId,mEtPassword;
    Button mBtnAutoLogin;
    TextView mTvFindPassword;
    Button mBtnLogin,mBtnJoin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //findViewById
//
//        mEtId = (EditText)findViewById(R.id.et_login_id);
//        mEtPassword =  (EditText)findViewById(R.id.et_login_password);
        mBtnAutoLogin =  (Button) findViewById(R.id.auto_login);
        mTvFindPassword =  (TextView)findViewById(R.id.find_password);
        mBtnLogin =  (Button)findViewById(R.id.btn_login);
        mBtnJoin =  (Button)findViewById(R.id.btn_join);

//        //setOnClickListener
//        mEtId.setOnClickListener(this);
//        mEtPassword.setOnClickListener(this);
        mBtnAutoLogin.setOnClickListener(this);
        mTvFindPassword.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mBtnJoin.setOnClickListener(this);


    }



//    private void tryGetTest() {
//        showProgressDialog();
//
//        final MainService mainService = new MainService(this);
//        mainService.getTest();
//    }

//    @Override
//    public void validateSuccess(String text) {
//        hideProgressDialog();
//    }
//
//    @Override
//    public void validateFailure(@Nullable String message) {
//        hideProgressDialog();
//        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.auto_login:
                Toast.makeText(this, "auto_login", Toast.LENGTH_SHORT).show();

//                tryGetTest();
                break;
            case R.id.find_password:
                Toast.makeText(this, "find_password", Toast.LENGTH_SHORT).show();

//                tryGetTest();
                break;
            case R.id.btn_login:
                Toast.makeText(this, "btn_login", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class)); // 8,26 오후 8시

//                tryGetTest();
                break;
            case R.id.btn_join:
                Toast.makeText(this, "btn_join", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class)); // 8,26 오후 8시

//                tryGetTest();
                break;
            default:
                break;
        }
    }
}
