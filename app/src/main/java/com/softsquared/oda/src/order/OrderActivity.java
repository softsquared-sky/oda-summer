package com.softsquared.oda.src.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.orderReceipt.OrderReceiptActivity;
import com.softsquared.odaproject.R;

import java.util.ArrayList;


public class OrderActivity extends BaseActivity {

    private EditText mEtShopName,mEtAddress,mEtExtraAddress,mEtPhone,mEtMemo;
    private RadioGroup mRgDeliveryTime;
    private Button mBtnOrderDefaultInfo;
    private int mDeliveryType;
    private ArrayList<OrderListViewItem> mOrderLvItem = new ArrayList<>();
    private OrderListViewAdapter mOrderLvAdapter;
    private ListView mLvOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        mEtShopName = findViewById(R.id.et_order_shop_name);
        mEtAddress = findViewById(R.id.et_order_address);
        mEtExtraAddress = findViewById(R.id.et_order_extra_address);
        mEtPhone = findViewById(R.id.et_order_phone_number);
        mEtMemo = findViewById(R.id.et_order_extra_memo);
        mBtnOrderDefaultInfo=findViewById(R.id.btn_order_default_info);
        mRgDeliveryTime = findViewById(R.id.rg_delivery_time);
        mLvOrder=findViewById(R.id.lv_order_product);

        mOrderLvAdapter = new OrderListViewAdapter(mOrderLvItem,this);
        mLvOrder.setAdapter(mOrderLvAdapter);

        //dummy
        mOrderLvItem.add(new OrderListViewItem(null,"제목",9000,3));
        mOrderLvItem.add(new OrderListViewItem(null,"제목2",13333,33));
        mOrderLvItem.add(new OrderListViewItem(null,"제목",9000,333));
        mOrderLvItem.add(new OrderListViewItem(null,"제목",9000,3333));

        setListViewSize(mLvOrder);//리스트뷰의 크기를 아이템 크기만큼 확장시켜주는 메소드

        mOrderLvAdapter.notifyDataSetChanged();
        //set listener
        mRgDeliveryTime.setOnCheckedChangeListener(listener1);

    }

    //라디오 그룹 리스너
    //선택된 버튼에 따라서 1 ,2 ,3, 4 를 반환
    //테스트완료
    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                int chkId1 = mRgDeliveryTime.getCheckedRadioButtonId();
                RadioButton rb =  findViewById(chkId1);
                TextView tvDeleveryTime = findViewById(R.id.tv_radio_delivery_title);
                tvDeleveryTime.setTextColor(getResources().getColor(R.color.colorNormalGray));
                switch (rb.getId()) {
                    case R.id.btn_radio_delivery_time_1:
                        mDeliveryType = 1;
                        break;
                    case R.id.btn_radio_delivery_time_2:
                        mDeliveryType = 2;
                        break;
                    case R.id.btn_radio_delivery_time_3:
                        mDeliveryType = 3;

                        break;
                    case R.id.btn_radio_delivery_time_4:
                        mDeliveryType = 4;
                        break;

                }
            }
        }
    };

    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_order_arrow_back :
                finish();
                break;
            case R.id.btn_order_find_address :
                showCustomToast(getString(R.string.no_access));
                break;
            case R.id.btn_order_default_info :
                mBtnOrderDefaultInfo.setSelected(!mBtnOrderDefaultInfo.isSelected());
                if (mBtnOrderDefaultInfo.isSelected()) {
                    mBtnOrderDefaultInfo.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_checkbox, 0, 0, 0);
                } else {
                    mBtnOrderDefaultInfo.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_checkbox_off, 0, 0, 0);
                }
                break;
            case R.id.btn_order_coupon_search :
                showCustomToast(getString(R.string.no_access));
                break;
            case R.id.btn_order_request_order :
                Intent intent = new Intent(OrderActivity.this, OrderReceiptActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;


        }

    }
    //리스트뷰 사이즈 조절 높이에 맞게

    public void setListViewSize(ListView myListView) {
        OrderListViewAdapter myListAdapter = (OrderListViewAdapter) myListView.getAdapter();
        if (myListAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int size = 0; size < myListAdapter.getCount(); size++) {
            View listItem = myListAdapter.getView(size, null, myListView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = myListView.getLayoutParams();
        params.height = totalHeight + (myListView.getDividerHeight() * (myListAdapter.getCount() - 1));
        myListView.setLayoutParams(params);

    }

}
