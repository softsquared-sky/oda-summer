package com.softsquared.oda.src.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.order.OrderActivity;
import com.softsquared.oda.src.search.SearchActivity;
import com.softsquared.oda.src.shoppingCart.ShoppingCartActivity;
import com.softsquared.odaproject.R;

public class DetailActivity extends BaseActivity {

    ViewPager mDetailViewPager;
    TabLayout mDetailTabLayout;
    DetailViewPagerAdapter mDetailViewPagerAdapter;
    int mProductId,mProductPrice;
    String mProductTitle,mProductImage;

    ImageView mIvDetailProductImage,mIvDetailArrowBack,mIvDetailSearch,mIvDetailShoppingCart;
    TextView mTvdetailProductTitle,mTvdetailProductPrice, mTvProductAmount;
    int mProductAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent secondIntent = getIntent();
        mProductId=secondIntent.getIntExtra("productId",0);
        mProductPrice=secondIntent.getIntExtra("productPrice",0);
        mProductImage=secondIntent.getStringExtra("productImage");
        mProductTitle=secondIntent.getStringExtra("productTitle");

        System.out.println(mProductId+" "+mProductPrice);
        if(mProductId==0||mProductPrice==0){
            finish();
            showCustomToast(getString(R.string.detail_error));
        }

        mIvDetailArrowBack = findViewById(R.id.iv_detali_arrow_back);
        mIvDetailSearch = findViewById(R.id.iv_detali_search);
        mIvDetailShoppingCart = findViewById(R.id.iv_detali_shopping_cart);
        mIvDetailProductImage = findViewById(R.id.iv_detail_product_top_image);
        mTvdetailProductTitle = findViewById(R.id.tv_detail_product_title);
        mTvdetailProductPrice = findViewById(R.id.tv_detail_product_title);
        mTvProductAmount = findViewById(R.id.tv_detail_product_count);



        mDetailViewPager =findViewById(R.id.detail_viewpager);
        mDetailTabLayout =findViewById(R.id.detail_tablayout);

        mDetailViewPagerAdapter = new DetailViewPagerAdapter(getSupportFragmentManager(),mProductId,mProductImage,mProductTitle,mProductPrice); //Viewpager와 Fragment를 연결
        mDetailTabLayout.setupWithViewPager(mDetailViewPager); //Viewpager와 TabLayout을 연결해주는 코드!
        mDetailViewPager.setAdapter(mDetailViewPagerAdapter); //Viewpager에 선택된 fragment를 띄워준다.
        mProductAmount =1;
        mTvProductAmount.setText(mProductAmount +"");


    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
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
                startActivity(new Intent(DetailActivity.this, ShoppingCartActivity.class));
                break;
            case R.id.btn_detail_decrement:
                if(mProductAmount >0){
                    mProductAmount--;
                    mTvProductAmount.setText(mProductAmount +"");
                }
                break;
            case R.id.btn_detail_increment:
                mProductAmount++;
                mTvProductAmount.setText(mProductAmount +"");
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
                startActivity(new Intent(DetailActivity.this, OrderActivity.class));
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

