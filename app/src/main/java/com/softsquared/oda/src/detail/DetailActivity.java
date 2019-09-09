package com.softsquared.oda.src.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.detail.interfaces.DetailActivityView;
import com.softsquared.oda.src.order.OrderActivity;
import com.softsquared.oda.src.search.SearchActivity;
import com.softsquared.oda.src.shoppingCart.ShoppingCartActivity;
import com.softsquared.odaproject.R;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends BaseActivity implements DetailActivityView {

    private ViewPager mDetailViewPager;
    private TabLayout mDetailTabLayout;
    private DetailViewPagerAdapter mDetailViewPagerAdapter;
    private int mProductId,mProductPrice;
    private String mProductTitle,mProductImage;

    private ImageView mIvDetailProductImage,mIvDetailArrowBack,mIvDetailSearch,mIvDetailShoppingCart;
    private  TextView mTvdetailProductTitle,mTvdetailProductPrice, mTvProductAmount;
    private int mProductAmount;

    private Button mBtnDetailAddShoppingCart,mBtnDetailOrder;
    private boolean mBtnDetailShoppingCartFlag=false,mBtnDetailOrderFlag=false;
    private RelativeLayout mRlProductAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent secondIntent = getIntent();
        mProductId=secondIntent.getIntExtra("productId",0);
        mProductPrice=secondIntent.getIntExtra("productPrice",0);
        mProductImage=secondIntent.getStringExtra("productImage");
        mProductTitle=secondIntent.getStringExtra("productTitle");

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
        mBtnDetailAddShoppingCart =findViewById(R.id.btn_detail_add_shopping_cart);
        mBtnDetailOrder=findViewById(R.id.btn_detail_order);
        mRlProductAmount=findViewById(R.id.detail_product_count_layout);

        mDetailViewPager =findViewById(R.id.detail_viewpager);
        mDetailTabLayout =findViewById(R.id.detail_tablayout);

        mDetailViewPagerAdapter = new DetailViewPagerAdapter(getSupportFragmentManager(),mProductId,mProductImage,mProductTitle,mProductPrice); //Viewpager와 Fragment를 연결
        mDetailTabLayout.setupWithViewPager(mDetailViewPager); //Viewpager와 TabLayout을 연결해주는 코드!
        mDetailViewPager.setAdapter(mDetailViewPagerAdapter); //Viewpager에 선택된 fragment를 띄워준다.
        mProductAmount =1;
        mTvProductAmount.setText(mProductAmount +"");
        mRlProductAmount.setVisibility(View.GONE);

    }

    private void postBasket() {
        int productId=mProductId;
        if (productId==0)return;
        final DetailService detailService = new DetailService(this);
        try {

            JSONObject params = new JSONObject();
            params.put("pNum", productId);
            showProgressDialog();
            detailService.postBasket(params);
        } catch (JSONException e) {
            showCustomToast("장바구니 등록 실패");
        }
    }

    @Override
    public void onBackPressed() {
        if(mBtnDetailOrderFlag==true||mBtnDetailShoppingCartFlag==true){
            mBtnDetailOrder.setLayoutParams(new TableLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
            mBtnDetailAddShoppingCart.setLayoutParams(new TableLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
            mBtnDetailAddShoppingCart.setBackgroundColor(getResources().getColor(R.color.colorPaleGray));
            mRlProductAmount.setVisibility(View.GONE);
            mBtnDetailShoppingCartFlag=false;
            mBtnDetailOrderFlag=false;
        }
        else {
                super.onBackPressed();
        }

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
                if(mProductAmount >1){
                    mProductAmount--;
                    mTvProductAmount.setText(mProductAmount +"");
                }
                else{
                    showCustomToast("올바른 수량을 입력하세요");
                }
                break;
            case R.id.btn_detail_increment:
                mProductAmount++;
                mTvProductAmount.setText(mProductAmount +"");
                break;
            case R.id.btn_detail_add_shopping_cart:

                if (mBtnDetailShoppingCartFlag==false){
                    mBtnDetailAddShoppingCart.setLayoutParams(new TableLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 0f));
                    mBtnDetailOrder.setLayoutParams(new TableLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
                    mBtnDetailAddShoppingCart.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    mRlProductAmount.setVisibility(View.VISIBLE);
                }else{
                    //장바구니 담기 API
                    //액티비티 전환X
                    postBasket();

                    showCustomToast("장바구니담기Ok");
                }
                mBtnDetailShoppingCartFlag=true;
                break;
            case R.id.btn_detail_order:

                //액티비티 전환 Ok -> 장바구니로
                if (!mBtnDetailOrderFlag){
                    mBtnDetailAddShoppingCart.setLayoutParams(new TableLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
                    mBtnDetailOrder.setLayoutParams(new TableLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 0f));
                    mRlProductAmount.setVisibility(View.VISIBLE);
                }else{
                    //주문요청하기 API

                    Intent intent = new Intent(DetailActivity.this,OrderActivity.class);
                    intent.putExtra("detailFlag",true);
                    intent.putExtra("mProductId",mProductId);
                    intent.putExtra("mProductAmount",mProductAmount);
                    intent.putExtra("mProductPrice",mProductPrice);
                    intent.putExtra("mProductImage",mProductImage);
                    intent.putExtra("mProductTitle",mProductTitle);
                    startActivity(intent);
                }

                mBtnDetailOrderFlag=true;

//                startActivity(new Intent(DetailActivity.this, OrderActivity.class));
                break;
            default:
                break;
        }

    }
    public void refresh() {
        mDetailViewPagerAdapter.notifyDataSetChanged();
    }


    @Override
    public void validateSuccess(String message) {
        hideProgressDialog();
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
    }
}

//                    ((RecentListFragment)mViewpagerAdapter.getItem(0)).addItem(result); 이런식으로 값 접근 가능

