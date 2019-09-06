package com.softsquared.oda.src.main;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;

import com.softsquared.oda.src.detail.DetailActivity;
import com.softsquared.oda.src.myPage.MyPageActivity;
import com.softsquared.oda.src.search.SearchActivity;
import com.softsquared.oda.src.shoppingCart.ShoppingCartActivity;
import com.softsquared.odaproject.R;
import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.main.interfaces.MainActivityView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.softsquared.oda.src.main.MainRecyclerViewAdapter.TYPE_FOOTER;

public class MainActivity extends BaseActivity implements MainActivityView, View.OnClickListener {


    ArrayList<MainRecyclerViewItem> mMainRvitem = new ArrayList<>();
    HorizontalScrollView mHorizonScrollView;
    RecyclerView mRecyclerView;
    GridLayoutManager mGridLayoutManager;
    MainRecyclerViewAdapter mMainRecyclerViewAdapterdapter;
    Button mBtnAllCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findViewById
        mRecyclerView = findViewById(R.id.rv_main_order_list);
        mHorizonScrollView = findViewById(R.id.sv_main_image);
        mBtnAllCheck = findViewById(R.id.btn_main_select_all_item);

        //listener

        mBtnAllCheck.setOnClickListener(this);


        // 리사이클러뷰에 GridLayoutManager 객체 지정.
        mGridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mMainRecyclerViewAdapterdapter = new MainRecyclerViewAdapter(mMainRvitem, this);
        // 리사이클러뷰에 MainRecyclerViewAdapter 객체 지정.
        mRecyclerView.setAdapter(mMainRecyclerViewAdapterdapter);
        mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (TYPE_FOOTER == mMainRecyclerViewAdapterdapter.getItemViewType(position))
                    return 3;
                else return 1;
            }

        });

        mRecyclerView.addItemDecoration(new MovieItemDecoration(this));

        mMainRecyclerViewAdapterdapter.setOnItemClickListener(new MainRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int pos) {
                //액티비티에서 리사이클러뷰의 아이템을 접근할 수 있다.
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("productId",pos+1);
                intent.putExtra("productImage",(mMainRvitem.get(pos).getImageResults()).get(0).getImageUrl());
                intent.putExtra("productTitle",mMainRvitem.get(pos).getOdaTitle());
                intent.putExtra("productPrice",mMainRvitem.get(pos).getOdaPrice());
                startActivity(intent);
            }
        });
        getMainProduct();
    }

    private void getMainProduct() {
        showProgressDialog();

        final MainService mainService = new MainService(this);
        for(int i=1;i<=21;i++)
        mainService.getProductInfo(i);
    }

    @Override
    public void validateSuccess(MainRecyclerViewItem items) {
        hideProgressDialog();
        mMainRvitem.add(items);
        mMainRecyclerViewAdapterdapter.notifyDataSetChanged();
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_main_shopping_cart:
                startActivity(new Intent(MainActivity.this, ShoppingCartActivity.class));
                break;
            case R.id.iv_main_search:
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
//                tryGetTest();
                break;
            case R.id.btn_main_select_all_item:
                mBtnAllCheck.setSelected(!mBtnAllCheck.isSelected());
                if (mBtnAllCheck.isSelected()) {
                    mBtnAllCheck.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_checkbox, 0, 0, 0);
                    mMainRecyclerViewAdapterdapter.allCheck();
                } else {
                    mBtnAllCheck.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_checkbox_off, 0, 0, 0);
                }
            case R.id.iv_main_bottom_bar_home:
                showCustomToast(getString(R.string.current_page));
                break;
            case R.id.iv_main_bottom_bar_category:
                showCustomToast(getString(R.string.no_development));
                break;
            case R.id.iv_main_bottom_bar_tip:
                showCustomToast(getString(R.string.no_development));
                break;
            case R.id.iv_main_bottom_bar_my_page:
                Intent intent = new Intent(MainActivity.this, MyPageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            default:
                break;
        }
    }


}