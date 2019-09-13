package com.softsquared.oda.src.searchList;

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

public class SearchListAdapter extends BaseAdapter {

    private ArrayList<SearchListItem> mSearchListItem;
    private LayoutInflater mInflater;

    // ListViewAdapter의 생성자
    public SearchListAdapter(ArrayList<SearchListItem> items, Context context) {
        mSearchListItem =items;
        mInflater=LayoutInflater.from(context);
    }
    //뷰홀더
    public class ViewHolder {
        TextView tvProductTitle,tvProductPrice;
        ImageView ivProductThumnail;
    }
    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return mSearchListItem.size();
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        final SearchListAdapter.ViewHolder holder;

        //역순으로 보여주는 리스트뷰 출력만 역순으로 해준다
        SearchListItem searchListItem = mSearchListItem.get(position);
        if (convertView == null) {
            holder = new SearchListAdapter.ViewHolder();
            convertView = mInflater.inflate(R.layout.search_listview_item, parent, false);
            holder.tvProductTitle = convertView.findViewById(R.id.tv_search_list_product_title);
            holder.tvProductPrice = convertView.findViewById(R.id.tv_search_list_product_price);
            holder.ivProductThumnail = convertView.findViewById(R.id.iv_search_list_product_thumnail);
            convertView.setTag(holder);
        }
        else {
            holder = (SearchListAdapter.ViewHolder) convertView.getTag();
        }


        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득


        DecimalFormat myFormatter = new DecimalFormat("###,###");
        String formattedStringPrice = myFormatter.format(searchListItem.getProductPrice());
        // 아이템 내 각 위젯에 데이터 반영 데이터 파싱
        holder.tvProductTitle.setText(searchListItem.getProductTitle());
        holder.tvProductPrice.setText(formattedStringPrice+"원");
        Glide.with(context)
                .load(searchListItem.getProductThumnail())
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(holder.ivProductThumnail);

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
        return mSearchListItem.get(position);
    }
}
