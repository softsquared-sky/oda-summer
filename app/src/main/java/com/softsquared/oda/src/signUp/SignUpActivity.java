package com.softsquared.oda.src.signUp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.softsquared.odaproject.R;

public class SignUpActivity extends AppCompatActivity {

    RadioGroup mBtnRgFood1,mBtnRgFood2;
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

                RadioButton rb = (RadioButton)findViewById(realCheck);
                switch (rb.getId()){
                    case R.id.btn_radio_food_1:
                        Toast.makeText(SignUpActivity.this, "1", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btn_radio_food_2:
                        Toast.makeText(SignUpActivity.this, "2", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btn_radio_food_3:
                        Toast.makeText(SignUpActivity.this, "3", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btn_radio_food_4:
                        Toast.makeText(SignUpActivity.this, "4", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btn_radio_food_5:
                        Toast.makeText(SignUpActivity.this, "5", Toast.LENGTH_SHORT).show();
                        break;
                }
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

                RadioButton rb = (RadioButton)findViewById(realCheck);
                switch (rb.getId()){
                    case R.id.btn_radio_food_6:
                        Toast.makeText(SignUpActivity.this, "6", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btn_radio_food_7:
                        Toast.makeText(SignUpActivity.this, "7", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btn_radio_food_8:
                        Toast.makeText(SignUpActivity.this, "8", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mBtnRgFood1 = (RadioGroup)findViewById(R.id.rg_food_1);
        mBtnRgFood2 = (RadioGroup)findViewById(R.id.rg_food_2);

        mBtnRgFood1.setOnCheckedChangeListener(listener1);
        mBtnRgFood2.setOnCheckedChangeListener(listener2);

    }


}
