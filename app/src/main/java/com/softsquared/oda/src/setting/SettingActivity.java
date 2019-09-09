package com.softsquared.oda.src.setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.login.LoginActivity;
import com.softsquared.odaproject.R;

import static com.softsquared.oda.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softsquared.oda.src.ApplicationClass.sSharedPreferences;

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
            case R.id.tv_setting_logout:
                SharedPreferences.Editor editor = sSharedPreferences.edit();
                editor.putBoolean("auto", false);
                //jwt 삭제
                editor.putString(X_ACCESS_TOKEN,"");
                editor.apply(); // ediot.commit() -> editor.apply(); 변경
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                showCustomToast("로그아웃 성공");
                break;
        }
    }

}
