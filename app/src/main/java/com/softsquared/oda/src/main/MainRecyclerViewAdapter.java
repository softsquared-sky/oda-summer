package com.softsquared.oda.src.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softsquared.odaproject.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public static final int TYPE_ITEM = 1;
    public static int TYPE_FOOTER = 2;

    private ArrayList<MainRecyclerViewItem> mMainRecyclerViewItems;
    private RecyclerView mRecyclerView;
    LayoutInflater mInflater;
    Context mContext;




    // 생성자에서 데이터 리스트 객체를 전달받음.
    MainRecyclerViewAdapter(ArrayList<MainRecyclerViewItem> data, Context context) {
        this.mContext = context;
        this.mMainRecyclerViewItems = data;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Footer은 리스트의 마지막 사이즈보다 +1 이어야한다.
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        mContext=parent.getContext();
        mRecyclerView= (RecyclerView)parent;
        RecyclerView.ViewHolder holder;
        View view;

            if (viewType == TYPE_FOOTER) {
            //아이템인
            view = mInflater.inflate(R.layout.main_recyclerview_footer, parent, false);
            holder = new FooterViewHolder(view);
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recyclerview_item, parent, false);
            holder = new ItemViewHolder(view);
        }

        return holder;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

//        if (holder instanceof HeaderViewHolder) {
//            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
//        } else
            if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            footerViewHolder.onBind();
        } else {
            // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.onBind(mMainRecyclerViewItems.get(position));
        }

    }

    //뷰타입 정하기
    @Override
    public int getItemViewType(int position) {

        if (position == mMainRecyclerViewItems.size())
            return TYPE_FOOTER;
        else
            return TYPE_ITEM;
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    //헤더와 footer가 추가되었으므로 2를 리턴
    @Override
    public int getItemCount() {
        return mMainRecyclerViewItems.size() + 1;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView tvOdaThumnail;
        TextView tvOdaTitle;
        TextView tvOdaPrice;
        CheckBox cbOdaCheck;

        ItemViewHolder(final View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조. (hold strong reference)
            tvOdaThumnail = itemView.findViewById(R.id.iv_main_oda_thumnail);
            tvOdaTitle = itemView.findViewById(R.id.tv_main_oda_title);
            tvOdaPrice = itemView.findViewById(R.id.tv_main_oda_price);
            cbOdaCheck = itemView.findViewById(R.id.cb_main_oda_check);

            //item click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO : process click event.
                    int pos = getAdapterPosition();
                    if(pos!=RecyclerView.NO_POSITION){

                        // notifyDataSetChanged()에 의해 리사이클러뷰가 아이템뷰를 갱신하는 과정에서,
                        // 뷰홀더가 참조하는 아이템이 어댑터에서 삭제되면
                        // getAdapterPosition() 메서드는 NO_POSITION을 리턴하므로 반드시 체크해야한다.

                        if(mListener!=null){
                            mListener.OnItemClick(v,pos);
                        }

                    }
                }
            });
        }

        void onBind(final MainRecyclerViewItem data) {

            Glide.with(mContext)
                    .load(data.getImageResults().get(0).getImageUrl())
                    .placeholder(R.mipmap.ic_launcher)
                    .centerCrop()
                    .into(tvOdaThumnail);
            tvOdaTitle.setText(data.getOdaTitle());

            DecimalFormat myFormatter = new DecimalFormat("###,###");
            String formattedStringPrice = myFormatter.format(data.getOdaPrice());
            tvOdaPrice.setText(formattedStringPrice+"원");

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


    //custom으로 액티비티에서 클릭 리스너를 제어하기 위한 커스텀 리스너


    public interface OnItemClickListener{
        void OnItemClick(View v,int pos);
    }
    private OnItemClickListener mListener=null;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener =listener;
    }


    // 헤더 뷰를 저장하는 뷰홀더 클래스.
//    public class HeaderViewHolder extends RecyclerView.ViewHolder {
//
//        TextView tvItemCount;
//        Button btnAllCheck;
//
//        HeaderViewHolder(View itemView) {
//            super(itemView);
//
//            tvItemCount=itemView.findViewById(R.id.tv_main_oda_item_count);
//            btnAllCheck=itemView.findViewById(R.id.cb_main_oda_all_check);
//            // 뷰 객체에 대한 참조. (hold strong reference)
//
//        }
//
//        void onBind(){
//
//        }
//    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        TextView tvScrollUp;
        FooterViewHolder(View footerView) {
            super(footerView);
            tvScrollUp =footerView.findViewById(R.id.tv_main_footer_scroll_up);
        }

        void onBind(){
            tvScrollUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mRecyclerView.smoothScrollToPosition(0);
                }
            });
        }
    }

    public void allCheck(){

        //미구현

    }

}


