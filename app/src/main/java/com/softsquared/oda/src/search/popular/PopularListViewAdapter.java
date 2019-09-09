package com.softsquared.oda.src.search.popular;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.softsquared.odaproject.R;

import java.util.ArrayList;

public class PopularListViewAdapter extends BaseAdapter {

    private ArrayList<PopularListViewItem> mPopularListViewItem;
    LayoutInflater mInflater;
    // ListViewAdapter의 생성자
    public PopularListViewAdapter(ArrayList<PopularListViewItem> items, Context context) {
        mPopularListViewItem =items;
        mInflater=LayoutInflater.from(context);
    }
    //뷰홀더
    public class ViewHolder {
        TextView tvTitle,tvRank;
    }
    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return mPopularListViewItem.size();
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        final PopularListViewAdapter.ViewHolder holder;

        //역순으로 보여주는 리스트뷰 출력만 역순으로 해준다
        PopularListViewItem popularListViewItem = mPopularListViewItem.get(position);
        if (convertView == null) {
            holder = new PopularListViewAdapter.ViewHolder();
            convertView = mInflater.inflate(R.layout.fragment_popular_listview_item, parent, false);
            holder.tvRank = convertView.findViewById(R.id.tv_popular_rank);
            holder.tvTitle = convertView.findViewById(R.id.tv_popular_title);
            convertView.setTag(holder);
        }
        else {
            holder = (PopularListViewAdapter.ViewHolder) convertView.getTag();
        }


        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득

        // 아이템 내 각 위젯에 데이터 반영 데이터 파싱
        holder.tvRank.setText(mPopularListViewItem.get(pos).getRank()+"");
        holder.tvTitle.setText(mPopularListViewItem.get(pos).getTitle());

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
        return mPopularListViewItem.get(position);
    }


}
