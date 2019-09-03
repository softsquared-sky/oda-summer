package com.softsquared.oda.src.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.search.SearchActivity;
import com.softsquared.odaproject.R;

public class DetailActivity extends BaseActivity {

    ViewPager mDetailViewPager;
    TabLayout mDetailTabLayout;
    DetailViewPagerAdapter mDetailViewPagerAdapter;

    ImageView mIvDetailProductImage,mIvDetailArrowBack,mIvDetailSearch,mIvDetailShoppingCart;
    TextView mTvdetailProductTitle,mTvdetailProductPrice,mTvProductCount;
    int mProductCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mIvDetailArrowBack = findViewById(R.id.iv_detali_arrow_back);
        mIvDetailSearch = findViewById(R.id.iv_detali_search);
        mIvDetailShoppingCart = findViewById(R.id.iv_detali_shopping_cart);
        mIvDetailProductImage = findViewById(R.id.iv_detail_product_image);
        mTvdetailProductTitle = findViewById(R.id.tv_detail_product_title);
        mTvdetailProductPrice = findViewById(R.id.tv_detail_product_title);
        mTvProductCount = findViewById(R.id.tv_detail_product_count);


        mDetailViewPager =findViewById(R.id.detail_viewpager);
        mDetailTabLayout =findViewById(R.id.detail_tablayout);

        mDetailViewPagerAdapter = new DetailViewPagerAdapter(getSupportFragmentManager()); //Viewpager와 Fragment를 연결
        mDetailTabLayout.setupWithViewPager(mDetailViewPager); //Viewpager와 TabLayout을 연결해주는 코드!
        mDetailViewPager.setAdapter(mDetailViewPagerAdapter); //Viewpager에 선택된 fragment를 띄워준다.

        mProductCount=1;
        mTvProductCount.setText(mProductCount+"");

    }

    public void onClick(View view){

        switch (view.getId()) {
            case R.id.iv_detali_arrow_back:
                finish();
                break;
            case R.id.iv_detali_search:
                startActivity(new Intent(DetailActivity.this, SearchActivity.class));
                break;
            case R.id.iv_detali_shopping_cart:
                showCustomToast(getString(R.string.no_access));
                break;
            case R.id.btn_detail_decrement:
                if(mProductCount>0){
                    mProductCount--;
                    mTvProductCount.setText(mProductCount+"");
                }
                break;
            case R.id.btn_detail_increment:
                mProductCount++;
                mTvProductCount.setText(mProductCount+"");
                break;
            case R.id.btn_detail_add_shopping_cart:
                //장바구니 담기 API
                showCustomToast("장바구니담기Ok");
                //액티비티 전환X
                break;
            case R.id.btn_detail_order:
                //장바구니 담기 API
                //액티비티 전환 Ok -> 장바구니로
                showCustomToast("장바구니 OK & 장바구니 페이지 이동");
                break;
            default:
                break;
        }

    }
    public void refresh() {
        mDetailViewPagerAdapter.notifyDataSetChanged();
    }


}

//                    ((RecentListFragment)mViewpagerAdapter.getItem(0)).addItem(result); 이런식으로 값 접근 가능

