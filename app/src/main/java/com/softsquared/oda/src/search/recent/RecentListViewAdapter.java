package com.softsquared.oda.src.search.recent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.softsquared.odaproject.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RecentListViewAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<RecentListViewItem> mRecentItemList;
    LayoutInflater mInflater;
    // ListViewAdapter의 생성자
    public RecentListViewAdapter(ArrayList<RecentListViewItem> items,Context context) {
        mRecentItemList =items;
        mInflater=LayoutInflater.from(context);
    }
    //뷰홀더
    public class ViewHolder {
        TextView titleTextView;
        ImageView closeImageView;
    }
    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return mRecentItemList.size();
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        final ViewHolder holder;

        //역순으로 보여주는 리스트뷰 출력만 역순으로 해준다
        RecentListViewItem recentlistViewItem = mRecentItemList.get(position);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.fragment_recent_listview_item, parent, false);
            holder.titleTextView = convertView.findViewById(R.id.tv_recent_search_name);
            holder.closeImageView = convertView.findViewById(R.id.iv_recent_search_close);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }


        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득

        // 아이템 내 각 위젯에 데이터 반영 데이터 파싱
        holder.titleTextView.setText(recentlistViewItem.getTitle());
        holder.closeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecentItemList.remove(pos);
                notifyDataSetChanged();
            }
        });

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
        return mRecentItemList.get(position);
    }


    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String title) {
        RecentListViewItem item = new RecentListViewItem();
        item.setTitle(title);
        mRecentItemList.add(0,item);
        //stackfrombottom 미 적용으로 인해 직접 0번부터 채워 넣는다.
    }
}