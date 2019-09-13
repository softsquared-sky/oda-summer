package com.softsquared.oda.src.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.softsquared.oda.src.detail.DetailActivity;
import com.softsquared.oda.src.myPage.MyPageActivity;
import com.softsquared.oda.src.search.SearchActivity;
import com.softsquared.oda.src.shoppingCart.ShoppingCartActivity;
import com.softsquared.odaproject.R;
import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.main.interfaces.MainActivityView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.softsquared.oda.src.main.MainRecyclerViewAdapter.TYPE_FOOTER;

public class MainActivity extends BaseActivity implements MainActivityView, View.OnClickListener {


    private ArrayList<MainRecyclerViewItem> mMainRvitem = new ArrayList<>();
    private HorizontalScrollView mHorizonScrollView;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private MainRecyclerViewAdapter mMainRecyclerViewAdapter;
    private Button mBtnAllCheck;
    private FloatingActionButton mFabQuickOrder;
    private TextView mTvTotalProductAmount;

    private boolean mAllCheckFlag=false;
    private int mTotalProductAmount;
    private final long FINISH_INTERVAL_TIME = 2000;
    private long   backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTotalProductAmount=0;
        //findViewById
        mRecyclerView = findViewById(R.id.rv_main_order_list);//리사이클러뷰
        mHorizonScrollView = findViewById(R.id.sv_main_image);//가로스크롤
        mBtnAllCheck = findViewById(R.id.btn_main_select_all_item);//전체선택버튼
        mFabQuickOrder = findViewById(R.id.fab_quick_order);//fab
        mTvTotalProductAmount=findViewById(R.id.tv_main_total_product_amount);
        //listener

        mBtnAllCheck.setOnClickListener(this);

        // 리사이클러뷰에 GridLayoutManager 객체 지정.
        mGridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mMainRecyclerViewAdapter = new MainRecyclerViewAdapter(mMainRvitem, this);
        // 리사이클러뷰에 MainRecyclerViewAdapter 객체 지정.
        mRecyclerView.setAdapter(mMainRecyclerViewAdapter);
        mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (TYPE_FOOTER == mMainRecyclerViewAdapter.getItemViewType(position))
                    return 3;
                else return 1;
            }

        });

        mRecyclerView.addItemDecoration(new MovieItemDecoration(this));

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == 1) {
                    mFabQuickOrder.hide();
                } else {
                    mFabQuickOrder.show();
                }

                System.out.println("onScroll의 호출횟수");
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }


        });
        mMainRecyclerViewAdapter.setOnCheckedChangeListener(new MainRecyclerViewAdapter.OnCheckedChangeListener() {
            @Override
            public void OnCheckClick() {
                controller();
            }
        });

        mMainRecyclerViewAdapter.setOnItemClickListener(new MainRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int pos) {
                //액티비티에서 리사이클러뷰의 아이템을 접근할 수 있다.
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("productId",mMainRvitem.get(pos).getProductId());
                intent.putExtra("productImage", (mMainRvitem.get(pos).getImageResults()).get(0).getImageUrl());
                intent.putExtra("productTitle", mMainRvitem.get(pos).getOdaTitle());
                intent.putExtra("productPrice", mMainRvitem.get(pos).getOdaPrice());
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        getMainProduct();
    }

    public void controller(){
        int count =0;
        for(int i=0;i<mMainRvitem.size();i++) {
            if(mMainRvitem.get(i).isSelected()){
                count++;
            }

        }

        mTvTotalProductAmount.setText(count+"");

        //전체선택알고리즘추가
        if(count!=mMainRvitem.size()){
            mBtnAllCheck.setSelected(false);
            mBtnAllCheck.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_checkbox_off, 0, 0, 0);
        }
    }
    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            super.onBackPressed();
        }
        else
        {
            backPressedTime = tempTime;
            showCustomToast(getString(R.string.back_press_text));
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        for(int i=0;i<mMainRvitem.size();i++) {
            mMainRvitem.get(i).setSelected(false);
        }
        mMainRecyclerViewAdapter.notifyDataSetChanged();

        controller();
    }

    private void getMainProduct() {
        showProgressDialog();

        final MainService mainService = new MainService(this);
        mMainRvitem.clear();
        for (int i = 1; i <= 21; i++)
            mainService.getProductInfo(i);
    }

    @Override
    public void validateSuccess(MainRecyclerViewItem items) {
        hideProgressDialog();
        mMainRvitem.add(items);
        mMainRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    @Override
    public void validateQuickOrderSuccess(String text) {
        hideProgressDialog();
        showCustomToast(text);
        startActivity(new Intent(MainActivity.this, ShoppingCartActivity.class));//장바구니로 이동하기
    }

    @Override
    public void validateQuickOrderFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    private void postQuickOrder(JSONObject params) {

        final MainService mainService = new MainService(this);
            mainService.postQuickOrder(params);
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

                    for(int i=0;i<mMainRvitem.size();i++) {
                        mMainRvitem.get(i).setSelected(true);
                    }
                    mMainRecyclerViewAdapter.notifyDataSetChanged();
                } else {

                    for(int i=0;i<mMainRvitem.size();i++) {
                        mMainRvitem.get(i).setSelected(false);
                    }

                    mMainRecyclerViewAdapter.notifyDataSetChanged();
                    mBtnAllCheck.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_checkbox_off, 0, 0, 0);
                }
                controller();
                break;
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
            case R.id.fab_quick_order:
                mMainRecyclerViewAdapter.notifyDataSetChanged();
                ArrayList<MainRecyclerViewItem> data = mMainRecyclerViewAdapter.getMainItem();
                ArrayList<Integer> productList = new ArrayList<>();
                for(int i=0;i<data.size();i++){
                    if(data.get(i).isSelected()){
                        productList.add(data.get(i).getProductId());//선택된 getProductId를 순차적으로 productList 주문 상품에 넣는다.
                    }
                }

                if (productList.size()==0){
                    showCustomToast("상품을 담아주세요");
                    break;
                }
                JSONObject params = new JSONObject();
                try {
                    JSONArray jArray = new JSONArray();//배열이 필요할때
                    for (int i = 0; i < productList.size(); i++)//배열
                    {
                        JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
                        sObject.put("pNum", productList.get(i));
                        jArray.put(sObject);
                    }
                    params.put("pNumList", jArray);//배열을 넣음
                    showProgressDialog();
                    postQuickOrder(params);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }


}