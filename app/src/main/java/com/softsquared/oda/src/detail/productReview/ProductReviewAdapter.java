package com.softsquared.oda.src.detail.productReview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.softsquared.odaproject.R;

import java.util.ArrayList;

public class ProductReviewAdapter  extends BaseAdapter {

    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ProductReviewItem> mProductReviewItemList;
    LayoutInflater mInflater;
    // ListViewAdapter의 생성자
    public ProductReviewAdapter(ArrayList<ProductReviewItem> items, Context context) {
        mProductReviewItemList =items;
        mInflater=LayoutInflater.from(context);
    }
    //뷰홀더
    public class ViewHolder {

        ImageView ivReviewThumnail;
        TextView tvReviewTitle,tvReviewContent,tvReviewDate,tvReviewId;
    }
    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return mProductReviewItemList.size();
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final ViewHolder holder;

        ProductReviewItem productReviewItem = mProductReviewItemList.get(position);

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.fragment_detail_review_listview_item, parent, false);
            holder.ivReviewThumnail = convertView.findViewById(R.id.iv_detail_review_thumnail);
            holder.tvReviewTitle = convertView.findViewById(R.id.tv_detail_review_title);
            holder.tvReviewContent = convertView.findViewById(R.id.tv_detail_review_content);
            holder.tvReviewDate = convertView.findViewById(R.id.tv_detail_review_date);
            holder.tvReviewId = convertView.findViewById(R.id.tv_detail_review_id);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }


        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득

        // 아이템 내 각 위젯에 데이터 반영 데이터 파싱
        holder.tvReviewId.setText(mProductReviewItemList.get(pos).getId());
        holder.tvReviewTitle.setText(mProductReviewItemList.get(pos).getReviewTitle());
        holder.tvReviewDate.setText(mProductReviewItemList.get(pos).getReviewDate());
        holder.tvReviewContent.setText(mProductReviewItemList.get(pos).getReviewContent());
        //이미지는 미연결


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
        return mProductReviewItemList.get(position);
    }

}
