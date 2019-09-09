package com.softsquared.oda.src.shoppingCart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.order.OrderActivity;
import com.softsquared.oda.src.shoppingCart.interfaces.ShoppingCartActivityView;
import com.softsquared.odaproject.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingCartActivity extends BaseActivity implements ShoppingCartActivityView,View.OnClickListener {

    private Button mBtnShoppingCartAllCheck,mBtnOrderRequest;
    public ExpandableListView mExpandableListView; // ExpandableListView 변수 선언
    public ShoppingCartExpandableListViewAdapter mShoppingCartExpandableLvAdapter; // 위 ExpandableListView를 받을 CustomAdapter(2번 class에 해당)를 선언
    public ArrayList<String> mParentList; // ExpandableListView의 Parent 항목이 될 List 변수 선언
    public ArrayList<ShoppingCartListData> mQuickProduct; // ExpandableListView의 Child 항목이 될 List를 각각 선언
    public ArrayList<ShoppingCartListData> mShoppingCartProduct;
    public HashMap<String, ArrayList<ShoppingCartListData>> mChildList; // 위 ParentList와 ChildList를 연결할 HashMap 변수 선언
    public TextView mTvEmptyTitle,mTvTotalPrice;
    public ImageView mIvEmptyImage;
    public CheckBox mChkProduct;
    private int mPrice;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        mBtnShoppingCartAllCheck=findViewById(R.id.btn_shopping_cart_select_all_item);
        mTvEmptyTitle =findViewById(R.id.tv_shopping_cart_empty_title);
        mIvEmptyImage =findViewById(R.id.tv_shopping_cart_empty_image);
        mExpandableListView = findViewById(R.id.expand_lv_shopping_cart);
        mChkProduct=findViewById(R.id.cb_shopping_cart_check);
        mBtnOrderRequest=findViewById(R.id.btn_shopping_cart_order);
        mTvTotalPrice=findViewById(R.id.tv_shopping_cart_total_estimated_price);
        // ExpandableListView의 ParentList에 해당할 항목을 입력
        mParentList = new ArrayList<>();
        mParentList.add("바로주문 상품");
        mParentList.add("장바구니 상품");

        // 앞서 ParentList에 연결할 ChildList 항목을 선언 및 입력

        mQuickProduct = new ArrayList<>();
        mShoppingCartProduct = new ArrayList<>();
        mChildList = new HashMap<>();

        //장바구니 목록 불러오기
        getShoppingList();

        // 앞서 정의해 놓은 ExpandableListView와 그 CustomAdapter를 선언 및 연결한 후
        // ExpandableListView에 대한 OnClickListener 등을 선언

        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                mShoppingCartExpandableLvAdapter.notifyDataSetChanged();
            }
        });
        mExpandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                mShoppingCartExpandableLvAdapter.notifyDataSetChanged();
            }
        });
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                return false;
            }
        });


    }

    private void getShoppingList() {
        showProgressDialog();
        final ShoppingCartService shoppingCartService = new ShoppingCartService(this);
        shoppingCartService.getShoppingCartList();
    }
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_shopping_cart_arrow_back :
                finish();
                break;
            case R.id.btn_shopping_cart_select_all_item:
                mBtnShoppingCartAllCheck.setSelected(!mBtnShoppingCartAllCheck.isSelected());
                if (mBtnShoppingCartAllCheck.isSelected()) {
                    mBtnShoppingCartAllCheck.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_checkbox, 0, 0, 0);

                    for(int i=0;i<mQuickProduct.size();i++) {
                        mQuickProduct.get(i).setSelected(true);
                    }
                    for(int i=0;i<mShoppingCartProduct.size();i++) {
                        mShoppingCartProduct.get(i).setSelected(true);
                    }
                    mShoppingCartExpandableLvAdapter.notifyDataSetChanged();
                    controller();
                } else {
                    mBtnShoppingCartAllCheck.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_checkbox_off, 0, 0, 0);
                    for(int i=0;i<mShoppingCartProduct.size();i++) {
                        mShoppingCartProduct.get(i).setSelected(false);
                    }
                    for(int i=0;i<mQuickProduct.size();i++) {
                        mQuickProduct.get(i).setSelected(false);
                    }
                    mShoppingCartExpandableLvAdapter.notifyDataSetChanged();
                    controller();
                }
                break;

            case R.id.btn_shopping_cart_order:
                if(mPrice>0&&mBtnOrderRequest.isEnabled()){
                    mShoppingCartExpandableLvAdapter.notifyDataSetChanged();
                    showCustomToast("주문요청 Ok");
                    Intent intent = new Intent(ShoppingCartActivity.this, OrderActivity.class);
                    intent.putExtra("mQuickProduct",mQuickProduct);
                    intent.putExtra("mShoppingCartProduct",mShoppingCartProduct);
                    intent.putExtra("price",mPrice);
                    intent.putExtra("shopFlag",true);
                    startActivity(intent);
                }
                else{
                    showCustomToast("상품을 담아주세요");
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void vaildateSuccess(ArrayList<ShoppingCartListData> item) {

        hideProgressDialog();
        mBtnShoppingCartAllCheck.setEnabled(true);
        mBtnShoppingCartAllCheck.setTextColor(getResources().getColor(R.color.colorNormalGray));
        mBtnShoppingCartAllCheck.setSelected(false);
        mBtnShoppingCartAllCheck.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_checkbox_off, 0, 0, 0);
        mTvEmptyTitle.setVisibility(View.GONE);
        mIvEmptyImage.setVisibility(View.GONE);

        for(int i=0;i<item.size();i++){
            if(item.get(i).getType().equals("장바구니")){
                mShoppingCartProduct.add(item.get(i));
            }
            else if(item.get(i).getType().equals("바로주문")){
                mQuickProduct.add(item.get(i));
            }
            else{

            }
        }

        mChildList.put(mParentList.get(0), mQuickProduct);
        mChildList.put(mParentList.get(1), mShoppingCartProduct);
        mShoppingCartExpandableLvAdapter = new ShoppingCartExpandableListViewAdapter(this, mParentList, mChildList);
        mExpandableListView.setAdapter(mShoppingCartExpandableLvAdapter);
        mShoppingCartExpandableLvAdapter.notifyDataSetChanged();

        mShoppingCartExpandableLvAdapter.setCustomOnCheckedChangeListener(new ShoppingCartExpandableListViewAdapter.customOnCheckedChangeListener() {
            @Override
            public void OnCheckClick(boolean isChecked) {
                controller();
            }
        });

    }

    public void controller(){
        int count =0;
        for(int i=0;i<mShoppingCartProduct.size();i++) {
            if(mShoppingCartProduct.get(i).isSelected()){
                count++;
            }
        }
        for(int i=0;i<mQuickProduct.size();i++) {
            if(mQuickProduct.get(i).isSelected()) {
                count++;
            }
        }


        if(count!=0){
            //체크가 될때
            mPrice=0;

            mBtnOrderRequest.setEnabled(true);
            mBtnOrderRequest.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

            for(int i=0;i<mQuickProduct.size();i++) {
                if(mQuickProduct.get(i).isSelected()) {
                    mPrice += mQuickProduct.get(i).getProductPrice()*mQuickProduct.get(i).getProductAmount();
                }

            }
            for(int i=0;i<mShoppingCartProduct.size();i++) {
                if(mShoppingCartProduct.get(i).isSelected()){
                    mPrice += mShoppingCartProduct.get(i).getProductPrice()*mShoppingCartProduct.get(i).getProductAmount();
                }

            }

            DecimalFormat myFormatter = new DecimalFormat("###,###");
            String formattedStringPrice = myFormatter.format(mPrice);
            mTvTotalPrice.setText(formattedStringPrice+"원");
        } else{
            //체크가 하나도 되지 않았을경우
            mBtnOrderRequest.setEnabled(false);
            mBtnOrderRequest.setBackgroundColor(getResources().getColor(R.color.colorPaleGray));
            mTvTotalPrice.setText("0원");

        }

        //전체선택알고리즘추가 하나라도 체크가 되지 않을경우 전체선택에서 제거
        if(count!=(mQuickProduct.size()+mShoppingCartProduct.size())){
            mBtnShoppingCartAllCheck.setSelected(false);
            mBtnShoppingCartAllCheck.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_checkbox_off, 0, 0, 0);
        }


    }
    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
        LinearLayout linearLayout = findViewById(R.id.shopping_cart_inside_linear_layout);
        linearLayout.setVisibility(View.GONE);
        mTvEmptyTitle.setVisibility(View.VISIBLE);
        mIvEmptyImage.setVisibility(View.VISIBLE);
    }
}
