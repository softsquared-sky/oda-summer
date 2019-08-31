package com.softsquared.oda.src.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.odaproject.R;

import java.util.ArrayList;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM = 1;
    public static int TYPE_FOOTER = 2;

    private ArrayList<MainRecyclerViewItem> mData;
    LayoutInflater mInflater;
    Context mContext;




    // 생성자에서 데이터 리스트 객체를 전달받음.
    MainRecyclerViewAdapter(ArrayList<MainRecyclerViewItem> data, Context context) {
        this.mContext = context;
        this.mData = data;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Footer은 리스트의 마지막 사이즈보다 +1 이어야한다.
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        mContext=parent.getContext();
        RecyclerView.ViewHolder holder;
        View view;

        if (viewType == TYPE_HEADER) {
            //헤더인경우
            view = mInflater.inflate(R.layout.main_recyclerview_header, parent, false);
            holder = new HeaderViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            //아이템인
            view = mInflater.inflate(R.layout.main_recyclerview_footer, parent, false);
            holder = new FooterViewHolder(view);
        }
            //이러한 방법으로 넘기는 방법이 있다.
//            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recyclerview_item, parent, false);
//            return new ViewHolder(v);

        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recyclerview_item, parent, false);
            holder = new ItemViewHolder(view);
        }

        return holder;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
        } else {
            // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.onBind(mData.get(position - 1), position);
        }

    }

    //뷰타입 정하기
    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        else if (position == mData.size() + 1)
            return TYPE_FOOTER;
        else
            return TYPE_ITEM;
    }



    // getItemCount() - 전체 데이터 갯수 리턴.
    //헤더와 footer가 추가되었으므로 2를 리턴
    @Override
    public int getItemCount() {
        return mData.size() + 2;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvOdaTitle;
        TextView tvOdaPrice;
        CheckBox cbOdaCheck;

        ItemViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조. (hold strong reference)
            tvOdaTitle = itemView.findViewById(R.id.tv_main_oda_title);
            tvOdaPrice = itemView.findViewById(R.id.tv_main_oda_price);
            cbOdaCheck = itemView.findViewById(R.id.cb_main_oda_check);
        }

        void onBind(final MainRecyclerViewItem data,int position) {

            tvOdaTitle.setText(data.getOdaTitle());
            tvOdaPrice.setText(data.getOdaPrice() + "");

            cbOdaCheck.setOnCheckedChangeListener(null);
            cbOdaCheck.setChecked(data.isSelected());
            cbOdaCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //set your object's last status
                    data.setSelected(isChecked);
                }
            });

        }
    }

    // 헤더 뷰를 저장하는 뷰홀더 클래스.
    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView tvItemCount;
        Button btnAllCheck;

        HeaderViewHolder(View itemView) {
            super(itemView);

            tvItemCount=itemView.findViewById(R.id.tv_main_oda_item_count);
            btnAllCheck=itemView.findViewById(R.id.cb_main_oda_all_check);
            // 뷰 객체에 대한 참조. (hold strong reference)

        }

        void onBind(){

        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        TextView tvScrollUp;

        FooterViewHolder(View footerView) {
            super(footerView);
        }
    }

}


