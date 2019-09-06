package com.softsquared.oda.src.setting;

import android.os.Bundle;
import android.view.View;

import com.softsquared.oda.src.BaseActivity;
import com.softsquared.odaproject.R;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void onClick(View v){
        switch (v.getId()){

            case R.id.iv_setting_arrow_back :
                finish();
                break;
            case R.id.tv_setting_coupon :
                showCustomToast(getString(R.string.no_development));
                break;
            case R.id.tv_setting_update_user_info :
                showCustomToast(getString(R.string.no_development));
                break;
            case R.id.tv_setting_customer_center :
                showCustomToast(getString(R.string.no_development));
                break;
        }
    }

}
