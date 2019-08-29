package com.softsquared.oda.src.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.odaproject.R;

import java.util.ArrayList;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {


    private ArrayList<MainRecyclerViewItem> mData;
    LayoutInflater mInflater;


    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvOdaTitle;
        TextView tvOdaPrice;
        CheckBox cbOdaCheck;

        ViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조. (hold strong reference)
            tvOdaTitle = itemView.findViewById(R.id.tv_main_oda_title);
            tvOdaPrice = itemView.findViewById(R.id.tv_main_oda_price);
            cbOdaCheck = itemView.findViewById(R.id.cb_main_oda_check);
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    MainRecyclerViewAdapter(ArrayList<MainRecyclerViewItem> data,Context context) {
        mData = data;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public MainRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        View view = mInflater.inflate(R.layout.main_recyclerview_item, parent, false);
        MainRecyclerViewAdapter.ViewHolder vh = new MainRecyclerViewAdapter.ViewHolder(view);


        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(MainRecyclerViewAdapter.ViewHolder holder, int position) {

        final MainRecyclerViewItem mainRecyclerViewItem = mData.get(position);

        holder.tvOdaTitle.setText(mainRecyclerViewItem.getOdaTitle());
        holder.tvOdaPrice.setText(mainRecyclerViewItem.getOdaPrice()+"");

        holder.cbOdaCheck.setOnCheckedChangeListener(null);
        holder.cbOdaCheck.setChecked(mainRecyclerViewItem.isSelected());
        holder.cbOdaCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//set your object's last status
                mainRecyclerViewItem.setSelected(isChecked);
            }
        });


    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size();
    }
}

