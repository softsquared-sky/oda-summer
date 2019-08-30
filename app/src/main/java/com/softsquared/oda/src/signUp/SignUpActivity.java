package com.softsquared.oda.src.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.login.LoginActivity;
import com.softsquared.oda.src.signUp.interfaces.SignUpActivityView;
import com.softsquared.odaproject.R;

import org.json.JSONException;

public class SignUpActivity extends BaseActivity implements SignUpActivityView {

    private EditText mEtSignUpId, mEtSignUpPassword, mEtSignUpPasswordCheck, mEtSignUpBusinessNumber, mEtSignUpAddress, mEtSignUpExtraAddress;
    private RadioGroup mBtnRgFood1, mBtnRgFood2;
    private Button mBtnSignUp;
    private String mId, mPassword,mPasswordCheck, mAddress;
    private int mType;
    private boolean mTypeCheck=false;

    public SignUpActivity() {
        this.mId = "";
        this.mPassword = "";
        this.mPasswordCheck = "";
        this.mAddress = "";
        this.mType = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mEtSignUpId = (EditText) findViewById(R.id.et_sign_up_id);
        mEtSignUpPassword = (EditText) findViewById(R.id.et_sign_up_password);
        mEtSignUpPasswordCheck = (EditText) findViewById(R.id.et_sign_up_password_check);
        mEtSignUpBusinessNumber = (EditText) findViewById(R.id.et_sign_up_business_number);
        mEtSignUpAddress = (EditText) findViewById(R.id.et_sign_up_address);
        mEtSignUpExtraAddress = (EditText) findViewById(R.id.et_sign_up_extra_address);
        mBtnSignUp =(Button)findViewById(R.id.btn_request_sign_up);

        mBtnRgFood1 = (RadioGroup) findViewById(R.id.rg_food_1);
        mBtnRgFood2 = (RadioGroup) findViewById(R.id.rg_food_2);


        mBtnRgFood1.setOnCheckedChangeListener(listener1);
        mBtnRgFood2.setOnCheckedChangeListener(listener2);


        mBtnSignUp.setEnabled(false);
    }

    protected void onResume() {
        super.onResume();
        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkSignUpButtonState();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkSignUpButtonState();
            }
        };

        mEtSignUpId.addTextChangedListener(tw);
        mEtSignUpPassword.addTextChangedListener(tw);
        mEtSignUpPasswordCheck.addTextChangedListener(tw);
        mEtSignUpBusinessNumber.addTextChangedListener(tw);
        mEtSignUpAddress.addTextChangedListener(tw);
        mEtSignUpExtraAddress.addTextChangedListener(tw);
    }

    private void checkSignUpButtonState() {
        //모든 editText가 활성화 될때
        mBtnSignUp.setEnabled(mEtSignUpId.getText().length() > 0 &&
                mEtSignUpPassword.getText().length() > 0 &&
                mEtSignUpPasswordCheck.getText().length() > 0 &&
                mEtSignUpBusinessNumber.getText().length() > 0 &&
                mEtSignUpAddress.getText().length() > 0 &&
                mEtSignUpExtraAddress.getText().length() > 0 &&
                mType!=0);
    }

    private void tryLoginAccess(String id,String pw,int type,String address) {
        showProgressDialog();

        final SignUpService signUpService = new SignUpService(this);
        try {
            signUpService.getSignUp(id,pw,type,address);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //2개의 라디오 그룹을 리스너를 통해 하나로 묶어주는 방법

    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                mBtnRgFood2.setOnCheckedChangeListener(null); // remove the listener before clearing so we don't throw that stackoverflow exception(like Vladimir Volodin pointed out)
                mBtnRgFood2.clearCheck(); // clear the second RadioGroup!
                mBtnRgFood2.setOnCheckedChangeListener(listener2); //reset the listener
                // Log.e("XXX2", "do the work");

                int chkId1 = mBtnRgFood1.getCheckedRadioButtonId();
                int chkId2 = mBtnRgFood2.getCheckedRadioButtonId();
                int realCheck = chkId1 == -1 ? chkId2 : chkId1;

                RadioButton rb = (RadioButton) findViewById(realCheck);
                switch (rb.getId()) {

                    case R.id.btn_radio_food_1:
                        mType =1;
                        break;
                    case R.id.btn_radio_food_2:
                        mType =2;
                        break;
                    case R.id.btn_radio_food_3:
                        mType=3;
                        break;
                    case R.id.btn_radio_food_4:
                       mType=4;
                        break;
                    case R.id.btn_radio_food_5:
                        mType=5;
                        break;
                }
                checkSignUpButtonState();
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                mBtnRgFood1.setOnCheckedChangeListener(null);
                mBtnRgFood1.clearCheck();
                mBtnRgFood1.setOnCheckedChangeListener(listener1);
                //Log.e("XXX2", "do the work");

                int chkId1 = mBtnRgFood1.getCheckedRadioButtonId();
                int chkId2 = mBtnRgFood2.getCheckedRadioButtonId();
                int realCheck = chkId1 == -1 ? chkId2 : chkId1;

                RadioButton rb = (RadioButton) findViewById(realCheck);
                switch (rb.getId()) {
                    case R.id.btn_radio_food_6:
                        mType=6;
                        break;
                    case R.id.btn_radio_food_7:
                        mType=7;
                        break;
                    case R.id.btn_radio_food_8:
                        mType=8;
                        break;
                }
                checkSignUpButtonState();
            }
        }
    };


    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();

        System.out.println("Success"+text);
        startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
        finish();

        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        System.out.println("Failure"+message);
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }



    public void btn_join(View view) {

        mId = mEtSignUpId.getText().toString();
        mPassword = mEtSignUpPassword.getText().toString();
        mPasswordCheck = mEtSignUpPasswordCheck.getText().toString();
        mAddress = mEtSignUpAddress.getText().toString();
        if(mId.length()!=0 && mPassword.length()!=0 && mPasswordCheck.length()!=0 && mAddress.length()!=0&&mType!=0){

            System.out.println(mPassword+" "+mPasswordCheck+" "+(mPasswordCheck.equals(mPassword)));
            if (mPasswordCheck.equals(mPassword)) {
                System.out.println("btn_join내부 :"+mId+" "+mPassword+" "+mType+" "+mAddress);
                tryLoginAccess(mId,mPassword,mType,mAddress);
            }
            else
            {
                Toast.makeText(this, "동일한 암호를 임력하세요.", Toast.LENGTH_SHORT).show();
                mEtSignUpPasswordCheck.setFocusable(true);
            }
        }
        else{
            Toast.makeText(this, "값을 전부 입력해주세요", Toast.LENGTH_SHORT).show();
        }
    }



}
