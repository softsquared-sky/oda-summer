package com.softsquared.oda.src.shoppingCart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softsquared.oda.src.BaseActivity;
import com.softsquared.odaproject.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingCartExpandableListViewAdapter extends BaseExpandableListAdapter  {

    private Context mContext;
    private ArrayList<String> mParentList; //제목이니까 String으로 받아도 된다.
    private ArrayList<ShoppingCartListData> mChildList;
    private ChildListViewHolder mChildListViewHolder;
    private HashMap<String, ArrayList<ShoppingCartListData>> mChildHashMap;
    // CustomExpandableListViewAdapter 생성자
    public ShoppingCartExpandableListViewAdapter(Context context, ArrayList<String> parentList, HashMap<String, ArrayList<ShoppingCartListData>> childHashMap){
        this.mContext = context;
        this.mParentList = parentList;
        this.mChildHashMap = childHashMap;
    }



    /* ParentListView에 대한 method */
    @Override
    public String getGroup(int groupPosition) { // ParentList의 position을 받아 해당 TextView에 반영될 String을 반환
        return mParentList.get(groupPosition);
    }

    @Override
    public int getGroupCount() { // ParentList의 원소 개수를 반환
        return mParentList.size();
    }

    @Override
    public long getGroupId(int groupPosition) { // ParentList의 position을 받아 long값으로 반환
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        // ParentList의 View
        if(convertView == null){
            LayoutInflater groupInfla = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // ParentList의 layout 연결. root로 argument 중 parent를 받으며 root로 고정하지는 않음
            convertView = groupInfla.inflate(R.layout.shopping_cart_parent_list, parent, false);
        }

        // ParentList의 Layout 연결 후, 해당 layout 내 TextView를 연결
        TextView parentText = convertView.findViewById(R.id.tv_shopping_cart_parent_title);
        String str = getGroup(groupPosition);
        parentText.setText(str);

        return convertView;
    }

    /* 여기서부터 ChildListView에 대한 method */
    @Override
    public ShoppingCartListData getChild(int groupPosition, int childPosition) {
        // groupPostion과 childPosition을 통해 childList의 원소를 얻어옴
        return this.mChildHashMap.get(this.mParentList.get(groupPosition)).get(childPosition);

    }

    @Override
    public int getChildrenCount(int groupPosition) { // ChildList의 크기를 int 형으로 반환
        return this.mChildHashMap.get(this.mParentList.get(groupPosition)).size();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) { // ChildList의 ID로 long 형 값을 반환
        return childPosition;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // ChildList의 View. 위 ParentList의 View를 얻을 때와 비슷하게 Layout 연결 후, layout 내 TextView, ImageView를 연결
        final int gP=groupPosition;
        final int cP=childPosition;
        final ShoppingCartListData childData = getChild(gP, cP);
        if(convertView == null){
            LayoutInflater childInfla = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = childInfla.inflate(R.layout.shopping_cart_child_list, null);

            mChildListViewHolder = new ChildListViewHolder();
            mChildListViewHolder.cbProductCheck = convertView.findViewById(R.id.cb_shopping_cart_check);
            mChildListViewHolder.ivProductThumnail = convertView.findViewById(R.id.iv_shopping_cart_thumnail);
            mChildListViewHolder.ivProductDelete = convertView.findViewById(R.id.iv_shopping_cart_delete);
            mChildListViewHolder.tvProdcutTitle = convertView.findViewById(R.id.tv_shopping_cart_product_title);
            mChildListViewHolder.tvProductPrice = convertView.findViewById(R.id.tv_shopping_cart_product_price);
            mChildListViewHolder.tvProductAmount = convertView.findViewById(R.id.tv_shopping_cart_product_count);
            mChildListViewHolder.tvProdcutPlus = convertView.findViewById(R.id.tv_shopping_cart_increment);
            mChildListViewHolder.tvProductMinus = convertView.findViewById(R.id.tv_shopping_cart_decrement);

            convertView.setTag(mChildListViewHolder);
        } else{
            mChildListViewHolder = (ChildListViewHolder)convertView.getTag();
        }

        //on BInd 부분


        mChildListViewHolder.ivProductDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //원소 지우기
                mChildHashMap.get(mParentList.get(gP)).remove(cP);
                notifyDataSetChanged();
                ((ShoppingCartActivity)mContext).controller();
            }
        });

        DecimalFormat myFormatter = new DecimalFormat("###,###");
        String formattedStringPrice = myFormatter.format(childData.getProductPrice());
        mChildListViewHolder.tvProductPrice.setText(formattedStringPrice+"원");
        mChildListViewHolder.tvProductAmount.setText(childData.getProductAmount()+"");
        mChildListViewHolder.tvProdcutTitle.setText(childData.getProductTitle());

        mChildListViewHolder.tvProdcutPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                childData.setProductAmount(childData.getProductAmount()+1);
                mChildListViewHolder.tvProductAmount.setText(childData.getProductAmount()+"");
                notifyDataSetChanged();

                if(mListener!=null){
                    mListener.OnCheckClick(true);
                }
            }

        });

        mChildListViewHolder.tvProductMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(childData.getProductAmount()>0){
                childData.setProductAmount(childData.getProductAmount()-1);
                mChildListViewHolder.tvProductAmount.setText(childData.getProductAmount()+"");
                notifyDataSetChanged();
                }
                else{
                    ((BaseActivity)mContext).showCustomToast("올바른 수량을 입력해주세요");
                }

                if(mListener!=null){
                    mListener.OnCheckClick(true);
                }
            }
        });

        Glide.with(mContext)
                .load(childData.getProductImage())
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into( mChildListViewHolder.ivProductThumnail);

        mChildListViewHolder.cbProductCheck.setOnCheckedChangeListener(null);
        mChildListViewHolder.cbProductCheck.setChecked(childData.isSelected());
        mChildListViewHolder.cbProductCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //set your object's last status
                childData.setSelected(isChecked);
                if(mListener!=null){
                    mListener.OnCheckClick(isChecked);
                }
            }
        });

        return convertView;

    }


    public interface customOnCheckedChangeListener {
        void OnCheckClick(boolean isChecked);
    }
    private customOnCheckedChangeListener mListener=null;

    public void setCustomOnCheckedChangeListener(customOnCheckedChangeListener listener){
        this.mListener =listener;
    }

    @Override
    public boolean hasStableIds() { return true; } // stable ID인지 boolean 값으로 반환

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) { return true; } // 선택여부를 boolean 값으로 반환


    public class ChildListViewHolder {
        CheckBox cbProductCheck;
        TextView tvProdcutTitle,tvProductPrice, tvProductAmount,tvProdcutPlus,tvProductMinus;
        ImageView ivProductThumnail,ivProductDelete;
    }
}



