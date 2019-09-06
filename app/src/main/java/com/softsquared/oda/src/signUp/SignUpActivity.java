package com.softsquared.oda.src.signUp;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.login.LoginActivity;
import com.softsquared.oda.src.signUp.interfaces.SignUpActivityView;
import com.softsquared.odaproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

public class SignUpActivity extends BaseActivity implements SignUpActivityView {

    private EditText mEtSignUpId, mEtSignUpPassword, mEtSignUpPasswordCheck, mEtSignUpBusinessNumber, mEtSignUpAddress, mEtSignUpExtraAddress;
    private RadioGroup mBtnRgFood1, mBtnRgFood2;
    private Button mBtnSignUp, mBtnDupCheck;
    private String mId, mPassword, mPasswordCheck, mAddress;
    private int mType;
    private boolean mDupCheck = false;

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

        mEtSignUpId =  findViewById(R.id.et_sign_up_id);
        mEtSignUpPassword =  findViewById(R.id.et_sign_up_password);
        mEtSignUpPasswordCheck =  findViewById(R.id.et_sign_up_password_check);
        mEtSignUpBusinessNumber =  findViewById(R.id.et_sign_up_business_number);
        mEtSignUpAddress =  findViewById(R.id.et_sign_up_address);
        mEtSignUpExtraAddress = findViewById(R.id.et_sign_up_extra_address);
        mBtnSignUp = findViewById(R.id.btn_request_sign_up);
        mBtnDupCheck =  findViewById(R.id.btn_duplicate_check);
        mBtnRgFood1 =  findViewById(R.id.rg_food_1);
        mBtnRgFood2 =findViewById(R.id.rg_food_2);


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
                mType != 0);
        if (mBtnSignUp.isEnabled()) {
            mBtnSignUp.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

            mEtSignUpPassword.setBackground(null);
            mEtSignUpPasswordCheck.setBackground(null);
            mEtSignUpAddress.setBackground(null);
            mEtSignUpExtraAddress.setBackground(null);
            mEtSignUpBusinessNumber.setBackground(null);

            mEtSignUpPassword.setTextColor(getResources().getColor(R.color.colorDark));
            mEtSignUpPasswordCheck.setTextColor(getResources().getColor(R.color.colorDark));
            mEtSignUpAddress.setTextColor(getResources().getColor(R.color.colorDark));
            mEtSignUpExtraAddress.setTextColor(getResources().getColor(R.color.colorDark));
            mEtSignUpBusinessNumber.setTextColor(getResources().getColor(R.color.colorDark));
        } else {
            mBtnSignUp.setBackgroundColor(getResources().getColor(R.color.colorPaleGray));
        }
    }

    private void tryLoginAccess(String id, String pw, int type, String address) {

        final SignUpService signUpService = new SignUpService(this);
        try {
            JSONObject params = new JSONObject();
            params.put("id", id);
            params.put("pw", pw);
            params.put("type", type);
            params.put("address", address);
            showProgressDialog();
            signUpService.postSignUp(params);
        } catch (JSONException e) {
            showCustomToast("회원가입 실패");
        }
    }

    private void tryDuplicateCheck(String id) {

        final SignUpService signUpService = new SignUpService(this);
        showProgressDialog();
        signUpService.getDuplicationCheck(id);
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
                        mType = 1;
                        break;
                    case R.id.btn_radio_food_2:
                        mType = 2;
                        break;
                    case R.id.btn_radio_food_3:
                        mType = 3;
                        break;
                    case R.id.btn_radio_food_4:
                        mType = 4;
                        break;
                    case R.id.btn_radio_food_5:
                        mType = 5;
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
                        mType = 6;
                        break;
                    case R.id.btn_radio_food_7:
                        mType = 7;
                        break;
                    case R.id.btn_radio_food_8:
                        mType = 8;
                        break;
                }
                checkSignUpButtonState();
            }
        }
    };


    @Override
    public void vaildateSignUpSuccess(String text, String id) {
        hideProgressDialog();
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        intent.putExtra("newId", id);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        showCustomToast(text);
    }

    @Override
    public void validateSignUpFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    @Override
    public void validateDupSuccess(String text) {
        hideProgressDialog();
        showCustomToast(text);
        mDupCheck = true;
        mBtnDupCheck.setBackground(getDrawable(R.drawable.btn_rectangle_shape_primary_radius_3dp));
        mBtnDupCheck.setText("사용가능");
        mBtnDupCheck.setTextColor(getResources().getColor(R.color.colorWhite));
        mBtnDupCheck.setEnabled(false);
        mEtSignUpId.setBackground(null);
        mEtSignUpId.setTextColor(getResources().getColor(R.color.colorDark));
        mEtSignUpId.setEnabled(false);
    }

    public void validateDupFailure(String message) {

        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);

    }

    //response가 null 일때
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);

    }


    public void onClick(View view){

        switch (view.getId()){
            case R.id.iv_join_arrow_back :
                finish();
                break;

            case R.id.btn_business_number_check:
                showCustomToast(getString(R.string.no_access));
                break;

            case R.id.btn_find_address:
                showCustomToast(getString(R.string.no_access));
                break;
        }

    }
    public void btn_join(View view) {

        mId = mEtSignUpId.getText().toString();
        mPassword = mEtSignUpPassword.getText().toString();
        mPasswordCheck = mEtSignUpPasswordCheck.getText().toString();
        mAddress = mEtSignUpAddress.getText().toString();
        if (mDupCheck) {


            if (mPasswordCheck.equals(mPassword)) {
                String regex = "^[0-9a-z]{5,15}$";
                String id = mEtSignUpPasswordCheck.getText().toString();
                if (Pattern.matches(regex, id)) {
                    tryLoginAccess(mId, mPassword, mType, mAddress);
                }
                else{
                    showCustomToast("pw는 5자 이상 15자 이하의 숫자와 소문자 조합으로 만들어주세요");
                }
            }
            else {
                Toast.makeText(this, "동일한 암호를 임력하세요.", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "아이디 중복 확인이 필요합니다.", Toast.LENGTH_SHORT).show();
        }
    }

    public void btn_duplicate_check(View view) {
        String regex = "^[a-z0-9]{4,10}$";
        String id = mEtSignUpId.getText().toString();

        if (Pattern.matches(regex, id)) {
            tryDuplicateCheck(id);
        } else {
            Toast.makeText(this, "id는 4자 이상 10자 이하 영소문자/숫자 허용으로 만들어주세요", Toast.LENGTH_SHORT).show();
        }

    }


}
