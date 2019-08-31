package com.softsquared.oda.src.signUp;

import android.content.Intent;
import android.content.res.Resources;
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

import java.util.regex.Pattern;

public class SignUpActivity extends BaseActivity implements SignUpActivityView {

    private EditText mEtSignUpId, mEtSignUpPassword, mEtSignUpPasswordCheck, mEtSignUpBusinessNumber, mEtSignUpAddress, mEtSignUpExtraAddress;
    private RadioGroup mBtnRgFood1, mBtnRgFood2;
    private Button mBtnSignUp,mBtnDupCheck;
    private String mId, mPassword,mPasswordCheck, mAddress;
    private int mType;
    private boolean mDupCheck=false;

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
        mBtnDupCheck = (Button)findViewById(R.id.btn_duplicate_check);

        mBtnRgFood1 = (RadioGroup) findViewById(R.id.rg_food_1);
        mBtnRgFood2 = (RadioGroup) findViewById(R.id.rg_food_2);


        mBtnRgFood1.setOnCheckedChangeListener(listener1);
        mBtnRgFood2.setOnCheckedChangeListener(listener2);


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
        if(mBtnSignUp.isEnabled()){
            mBtnSignUp.setBackgroundResource(R.drawable.login_button_selector);
            mBtnSignUp.setTextColor(getResources().getColor(R.color.login_text_selector));
        }
        else{
            mBtnSignUp.setTextColor(getResources().getColor(R.color.colorWhite));
            mBtnSignUp.setBackgroundResource(R.color.nomalColor);
        }
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

    private void tryDuplicateCheck(String id){
        showProgressDialog();

        final SignUpService signUpService = new SignUpService(this);
        try {
            signUpService.getDuplicationCheck(id);
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
    public void validateSuccess(String text,int code) {
        hideProgressDialog();

        if(code==200){
            finish();

        }
        else if (code ==100){
            //중복된 아이디가 존재할때
//            mDupCheck=false;

        }
        else if(code ==150){
            // 중복된 아이디가 존재하지 않을때
//            mDupCheck=true;
        }
        else{
        }
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }



    public void btn_join(View view) {

        mId = mEtSignUpId.getText().toString();
        mPassword = mEtSignUpPassword.getText().toString();
        mPasswordCheck = mEtSignUpPasswordCheck.getText().toString();
        mAddress = mEtSignUpAddress.getText().toString();
        if(mDupCheck){

            if (mPasswordCheck.equals(mPassword)) {
                tryLoginAccess(mId,mPassword,mType,mAddress);
            }
            else
            {
                Toast.makeText(this, "동일한 암호를 임력하세요.", Toast.LENGTH_SHORT).show();
                mEtSignUpPasswordCheck.setFocusable(true);
            }

        }
        else{
            Toast.makeText(this, "아이디 중복 확인이 필요합니다.", Toast.LENGTH_SHORT).show();
        }
    }

    public void btn_duplicate_check(View view){
        String regex = "^[a-z0-9]{4,10}$";
        String id = mEtSignUpId.getText().toString();

        if(Pattern.matches(regex,id)){
            Toast.makeText(this, "중복확인 API OK", Toast.LENGTH_SHORT).show();
            mDupCheck=true;
        }else{
            mDupCheck=false;
            Toast.makeText(this, "id는 4자 이상 10자 이하 영소문자/숫자 허용으로 만들어주세요", Toast.LENGTH_SHORT).show();
        }

    }



}
