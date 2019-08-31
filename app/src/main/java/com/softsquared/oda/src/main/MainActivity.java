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

import static com.softsquared.oda.src.main.MainRecyclerViewAdapter.TYPE_FOOTER;
import static com.softsquared.oda.src.main.MainRecyclerViewAdapter.TYPE_HEADER;

public class MainActivity extends BaseActivity implements MainActivityView {


    ArrayList<MainRecyclerViewItem> mMainRvitem =new ArrayList<>();
    HorizontalScrollView mHorizonScrollView;
    RecyclerView mRecyclerView;
    GridLayoutManager mGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findViewById
        mRecyclerView = findViewById(R.id.rv_main_order_list) ;
        mHorizonScrollView = findViewById(R.id.sv_main_image);

        //dummy
        for (int i=0;i<20;i++){
            mMainRvitem.add(new MainRecyclerViewItem(false,"","오다타이틀",10000));
        }

        // 리사이클러뷰에 GridLayoutManager 객체 지정.
        mGridLayoutManager = new GridLayoutManager(this,3);
        mRecyclerView.setLayoutManager(mGridLayoutManager) ;
        final MainRecyclerViewAdapter adapter = new MainRecyclerViewAdapter(mMainRvitem,this) ;

        // 리사이클러뷰에 MainRecyclerViewAdapter 객체 지정.
        mRecyclerView.setAdapter(adapter) ;

        mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (TYPE_HEADER == adapter.getItemViewType(position)) return 3;
                else if(TYPE_FOOTER==adapter.getItemViewType(position)) return 3;
                else return 1;
            }

        });



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