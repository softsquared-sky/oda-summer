package com.softsquared.oda.src.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softsquared.odaproject.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrderListViewAdapter extends BaseAdapter {

    private ArrayList<OrderListViewItem> mOrderListViewItems;
    LayoutInflater mInflater;
    // ListViewAdapter의 생성자
    public OrderListViewAdapter(ArrayList<OrderListViewItem> items, Context context) {
        mOrderListViewItems =items;
        mInflater=LayoutInflater.from(context);
    }
    //뷰홀더
    public class ViewHolder {
        TextView tvProductTitle, tvProductPrice,tvProductCount;
        ImageView ivProductThumnail;
    }
    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return mOrderListViewItems.size();
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        final OrderListViewAdapter.ViewHolder holder;

        OrderListViewItem orderListViewItem = mOrderListViewItems.get(position);
        if (convertView == null) {
            holder = new OrderListViewAdapter.ViewHolder();
            convertView = mInflater.inflate(R.layout.order_listview_item, parent, false);
            holder.tvProductCount = convertView.findViewById(R.id.tv_order_product_count);
            holder.ivProductThumnail = convertView.findViewById(R.id.iv_order_product_thumnail);
            holder.tvProductPrice = convertView.findViewById(R.id.tv_order_product_price);
            holder.tvProductTitle = convertView.findViewById(R.id.tv_order_product_title);
            convertView.setTag(holder);
        }
        else {
            holder = (OrderListViewAdapter.ViewHolder) convertView.getTag();
        }

        // 아이템 내 각 위젯에 데이터 반영 데이터 파싱
        if(orderListViewItem.getProductImage()!=null){

                    Glide.with(context)
                    .load(orderListViewItem.getProductImage())
                    .placeholder(R.mipmap.ic_launcher)
                    .centerCrop()
                    .into( holder.ivProductThumnail);
        }

        holder.tvProductTitle.setText(orderListViewItem.getProductTitle());
        DecimalFormat myFormatter = new DecimalFormat("###,###");
        String formattedStringPrice = myFormatter.format(orderListViewItem.getProductPrice());
        holder.tvProductPrice.setText(formattedStringPrice+ '원' +'/');
        holder.tvProductCount.setText(orderListViewItem.getProductCount()+"개");

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return mOrderListViewItems.get(position);
    }
}
