package com.softsquared.oda.src.orderReceipt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.main.MainActivity;
import com.softsquared.oda.src.order.OrderActivity;
import com.softsquared.odaproject.R;

public class OrderReceiptActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_receipt);

        TextView tvTitle, tvPrice, tvAddress, tvDeliveryTime;

        tvTitle = findViewById(R.id.tv_order_receipt_product_title);
        tvPrice = findViewById(R.id.tv_order_receipt_product_price);
        tvAddress = findViewById(R.id.tv_order_receipt_address);
        tvDeliveryTime = findViewById(R.id.tv_order_receipt_delivery_time);

        Intent intent = getIntent();
        tvAddress.setText(intent.getStringExtra("address"));
        tvTitle.setText(intent.getStringExtra("title"));
        tvPrice.setText(intent.getStringExtra("price"));
        tvDeliveryTime.setText(intent.getStringExtra("deliveryTime"));


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OrderReceiptActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_order_receipt_close:
                Intent intent = new Intent(OrderReceiptActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
        }

    }
}
