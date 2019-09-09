package com.softsquared.oda.src.order;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.order.interfaces.OrderActivityView;
import com.softsquared.oda.src.orderReceipt.OrderReceiptActivity;
import com.softsquared.oda.src.shoppingCart.ShoppingCartListData;
import com.softsquared.odaproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class OrderActivity extends BaseActivity implements OrderActivityView {

    private EditText mEtShopName, mEtAddress, mEtExtraAddress, mEtPhone, mEtMemo;
    private RadioGroup mRgDeliveryTime;
    private Button mBtnOrderDefaultInfo;
    private int mDeliveryTime;
    private ArrayList<OrderListViewItem> mOrderLvItem = new ArrayList<>();
    private OrderListViewAdapter mOrderLvAdapter;
    private ListView mLvOrder;
    private TextView mTvEstimatedPrice, mTvTotalEstimatedPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        mDeliveryTime=-1;

        mEtShopName = findViewById(R.id.et_order_shop_name);
        mEtAddress = findViewById(R.id.et_order_address);
        mEtExtraAddress = findViewById(R.id.et_order_extra_address);
        mEtPhone = findViewById(R.id.et_order_phone_number);
        mEtMemo = findViewById(R.id.et_order_extra_memo);
        mBtnOrderDefaultInfo = findViewById(R.id.btn_order_default_info);
        mRgDeliveryTime = findViewById(R.id.rg_delivery_time);
        mLvOrder = findViewById(R.id.lv_order_product);
        mTvEstimatedPrice = findViewById(R.id.tv_order_product_estimated_price);
        mTvTotalEstimatedPrice = findViewById(R.id.tv_order_product_total_estimated_price);
        mOrderLvAdapter = new OrderListViewAdapter(mOrderLvItem, this);
        mLvOrder.setAdapter(mOrderLvAdapter);

        mEtPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        Intent intent = getIntent();

        if (intent.getBooleanExtra("shopFlag", false)) {

            ArrayList<ShoppingCartListData> data = (ArrayList<ShoppingCartListData>) intent.getSerializableExtra("mQuickProduct");
            ArrayList<ShoppingCartListData> data2 = (ArrayList<ShoppingCartListData>) intent.getSerializableExtra("mShoppingCartProduct");
            int price = intent.getIntExtra("price", 0);

            DecimalFormat myFormatter = new DecimalFormat("###,###");
            String formattedStringPrice = myFormatter.format(price);
            mTvEstimatedPrice.setText(formattedStringPrice + "원");
            mTvTotalEstimatedPrice.setText(formattedStringPrice + "원");

            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).isSelected() && (data.get(i).getProductAmount() > 0)) {
                    mOrderLvItem.add(new OrderListViewItem(data.get(i).getProductId(), data.get(i).getProductImage(), data.get(i).getProductTitle(),
                            data.get(i).getProductPrice(), data.get(i).getProductAmount()
                    ));
                }

            }
            for (int i = 0; i < data2.size(); i++) {
                if (data2.get(i).isSelected() && (data2.get(i).getProductAmount() > 0)) {
                    mOrderLvItem.add(new OrderListViewItem(data2.get(i).getProductId(), data2.get(i).getProductImage(), data2.get(i).getProductTitle(),
                            data2.get(i).getProductPrice(), data2.get(i).getProductAmount()));
                }
            }
        } else if (intent.getBooleanExtra("detailFlag", false)) {

            int productId = intent.getIntExtra("mProductId", 0);
            int price = intent.getIntExtra("mProductPrice", 0);
            int amount = intent.getIntExtra("mProductAmount", 0);
            String title = intent.getStringExtra("mProductTitle");
            String image = intent.getStringExtra("mProductImage");

            mOrderLvItem.add(new OrderListViewItem(productId, image, title, price, amount));

            DecimalFormat myFormatter = new DecimalFormat("###,###");
            String formattedStringPrice = myFormatter.format((price * amount));
            mTvEstimatedPrice.setText(formattedStringPrice + "원");
            mTvTotalEstimatedPrice.setText(formattedStringPrice + "원");
        } else {

        }


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
                RadioButton rb = findViewById(chkId1);
                TextView tvDeleveryTime = findViewById(R.id.tv_radio_delivery_title);
                tvDeleveryTime.setTextColor(getResources().getColor(R.color.colorNormalGray));
                switch (rb.getId()) {
                    case R.id.btn_radio_delivery_time_1:
                        mDeliveryTime = 1;
                        break;
                    case R.id.btn_radio_delivery_time_2:
                        mDeliveryTime = 2;
                        break;
                    case R.id.btn_radio_delivery_time_3:
                        mDeliveryTime = 3;

                        break;
                    case R.id.btn_radio_delivery_time_4:
                        mDeliveryTime = 4;
                        break;

                }
            }
        }
    };

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_order_arrow_back:
                finish();
                break;
            case R.id.btn_order_find_address:
                showCustomToast(getString(R.string.no_access));
                break;
            case R.id.btn_order_default_info:
                mBtnOrderDefaultInfo.setSelected(!mBtnOrderDefaultInfo.isSelected());
                if (mBtnOrderDefaultInfo.isSelected()) {
                    mBtnOrderDefaultInfo.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_checkbox, 0, 0, 0);
                } else {
                    mBtnOrderDefaultInfo.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_checkbox_off, 0, 0, 0);
                }
                break;
            case R.id.btn_order_coupon_search:
                showCustomToast(getString(R.string.no_access));
                break;
            case R.id.btn_order_request_order:
                postPayment();
                //서비스연동
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

    public void postPayment() {
        //미구현

        final OrderService orderService = new OrderService(this);


        if (mOrderLvItem.size() == 0) {
            showCustomToast("결제요청이 실패하였습니다. 다시 시도해주세요");
            return;
        }
        String shopName = mEtShopName.getText().toString();
        String address = mEtAddress.getText().toString();
        String extraAddress = mEtExtraAddress.getText().toString();
        String phone = mEtPhone.getText().toString();
        String regex = "(010|011|017|019)-\\d{3,4}-\\d{4}";

        if (shopName.length() == 0 || address.length() == 0 || extraAddress.length() == 0 || phone.length() == 0||mDeliveryTime<1) {

            //값이 하나라도 비어있는경우
            showCustomToast("수령 정보를 입력하세요");

        } else {

            if (Pattern.matches(regex, phone)) {
                //진행
                JSONObject params = new JSONObject();
                try {
                    JSONArray jArray = new JSONArray();//배열이 필요할때
                    for (int i = 0; i < mOrderLvItem.size(); i++)//배열
                    {
                        JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
                        sObject.put("pNum", mOrderLvItem.get(i).getProductId());
                        sObject.put("amount", mOrderLvItem.get(i).getProductAmount());
                        jArray.put(sObject);
                    }
                    params.put("payList", jArray);//배열을 넣음
                    showProgressDialog();
                    orderService.postPayment(params);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                showCustomToast("올바른 전화번호를 입력하세요");
            }

        }

    }

    @Override
    public void vaildateSuccess(String message) {
        hideProgressDialog();
        String address = mEtAddress.getText().toString();
        String title = "";
        if (mOrderLvItem.size() == 1) {
            title = mOrderLvItem.get(0).getProductTitle();
        } else {
            title = mOrderLvItem.get(0).getProductTitle() + " 외 " + (mOrderLvItem.size() - 1) + "개의 상품";
        }

        String price = "예상 오다가 " + mTvTotalEstimatedPrice.getText().toString();

        String deliveryTime = "";
        switch (mDeliveryTime) {
            case 1:
                deliveryTime = "아침(5-7시) 수령";
                break;
            case 2:
                deliveryTime = "오전(7-12시) 수령";
                break;
            case 3:
                deliveryTime = "오후(12-7시) 수령";
                break;
            case 4:
                deliveryTime = "새벽(11-4시) 수령";
                break;
            default:
                deliveryTime = "";
                break;
        }

        Intent intent = new Intent(OrderActivity.this, OrderReceiptActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("address", address);
        intent.putExtra("title", title);
        intent.putExtra("price", price);
        intent.putExtra("deliveryTime", deliveryTime);
        startActivity(intent);
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }
}
