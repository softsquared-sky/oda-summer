package com.softsquared.oda.src.main;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.softsquared.odaproject.R;
import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.main.interfaces.MainActivityView;

import java.security.cert.Certificate;
import java.util.ArrayList;

public class MainActivity extends BaseActivity implements MainActivityView {


    ArrayList<MainRecyclerViewItem> mMainRvitem =new ArrayList<>();

    HorizontalScrollView mHorizonScrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHorizonScrollView = findViewById(R.id.sv_main_image);

        for (int i=0;i<20;i++){
            mMainRvitem.add(new MainRecyclerViewItem(false,"","오다타이틀",10000));
        }
        // 리사이클러뷰에 GridLayoutManager 객체 지정.
        RecyclerView recyclerView = findViewById(R.id.rv_main_order_list) ;
        recyclerView.setLayoutManager(new GridLayoutManager(this,3)) ;

        // 리사이클러뷰에 MainRecyclerViewAdapter 객체 지정.
        MainRecyclerViewAdapter adapter = new MainRecyclerViewAdapter(mMainRvitem,this) ;
        recyclerView.setAdapter(adapter) ;

    }

    private void tryGetTest() {
        showProgressDialog();

        final MainService mainService = new MainService(this);
        mainService.getTest();
    }

    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    public void customOnClick(View view) {
        switch (view.getId()) {
//            case R.id.main_btn_hello_world:
//                tryGetTest();
//                break;
            default:
                break;
        }
    }
}