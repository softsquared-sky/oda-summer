package com.softsquared.oda.src.myPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softsquared.oda.src.BaseActivity;
import com.softsquared.odaproject.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;


public class MyPageExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private ArrayList<String> mParentList; //제목이니까 String으로 받아도 된다.
    private ArrayList<MyPageListData> mChildList;
    private MyPageChildListViewHolder mMyPageChildListViewHolder;
    private HashMap<String, ArrayList<MyPageListData>> mChildHashMap;
    // CustomExpandableListViewAdapter 생성자
    public MyPageExpandableListViewAdapter(Context context, ArrayList<String> parentList, HashMap<String, ArrayList<MyPageListData>> childHashMap){
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
            convertView = groupInfla.inflate(R.layout.mypage_parent_list, parent, false);
        }

        // ParentList의 Layout 연결 후, 해당 layout 내 TextView를 연결
        TextView parentText = convertView.findViewById(R.id.tv_my_page_parent_title);
        parentText.setText(getGroup(groupPosition));

        return convertView;
    }

    /* 여기서부터 ChildListView에 대한 method */
    @Override
    public MyPageListData getChild(int groupPosition, int childPosition) {
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

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // ChildList의 View. 위 ParentList의 View를 얻을 때와 비슷하게 Layout 연결 후, layout 내 TextView, ImageView를 연결
        final int gP=groupPosition;
        final int cP=childPosition;
        final MyPageListData childData = getChild(gP, cP);
        if(convertView == null){
            LayoutInflater childInfla = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = childInfla.inflate(R.layout.mypage_child_list, null);

            mMyPageChildListViewHolder = new MyPageChildListViewHolder();
            mMyPageChildListViewHolder.ivProductThumnail = convertView.findViewById(R.id.iv_my_page_product_thumnail);
            mMyPageChildListViewHolder.tvProdcutTitle = convertView.findViewById(R.id.tv_my_page_product_title);
            mMyPageChildListViewHolder.tvProductPrice = convertView.findViewById(R.id.tv_my_page_product_price);
            mMyPageChildListViewHolder.tvProductCount = convertView.findViewById(R.id.tv_my_page_product_count);
            mMyPageChildListViewHolder.tvDeliveryDay = convertView.findViewById(R.id.tv_my_page_delivery_day);
            mMyPageChildListViewHolder.btnDeliveryStatus=convertView.findViewById(R.id.btn_my_page_delivery_tracking);

            convertView.setTag(mMyPageChildListViewHolder);
        } else{
            mMyPageChildListViewHolder = (MyPageChildListViewHolder)convertView.getTag();
        }

        //on BInd 부분

        if (childData!=null){
            if (mMyPageChildListViewHolder.ivProductThumnail==null){
                mMyPageChildListViewHolder.ivProductThumnail.setImageResource(R.drawable.tmp_thumnail_img);
            }
            else {
                Glide.with(mContext)
                        .load(childData.getOrderLists().get(cP).getProductThumnail())
                        .placeholder(R.mipmap.ic_launcher)
                        .centerCrop()
                        .into(mMyPageChildListViewHolder.ivProductThumnail);

            }
            mMyPageChildListViewHolder.tvProdcutTitle.setText(childData.getOrderLists().get(childPosition).getProductTitle());

            //원
            DecimalFormat myFormatter = new DecimalFormat("###,###");
            String formattedStringPrice = myFormatter.format(childData.getOrderLists().get(childPosition).getProductPrice());
            mMyPageChildListViewHolder.tvProductPrice.setText(formattedStringPrice+"원 /");
            //개수
            mMyPageChildListViewHolder.tvProductCount.setText(childData.getOrderLists().get(childPosition).getProductAmount()+"개");

            if (childData.getDeliveryStatus().equals("배송중")){
                //true
                mMyPageChildListViewHolder.btnDeliveryStatus.setBackground(mContext.getDrawable(R.drawable.btn_rectacgle_shape_gray_radius_3dp));
                mMyPageChildListViewHolder.btnDeliveryStatus.setText("배송추적");
                mMyPageChildListViewHolder.btnDeliveryStatus.setTextColor(mContext.getResources().getColor(R.color.colorGray));
                mMyPageChildListViewHolder.btnDeliveryStatus.setEnabled(true);

                mMyPageChildListViewHolder.tvDeliveryDay.setText(childData.getPayDate().substring(4)+"\n도착예정");
            } else{
                mMyPageChildListViewHolder.btnDeliveryStatus.setBackground(mContext.getDrawable(R.drawable.btn_rectangle_shape_primary_radius_3dp));
                mMyPageChildListViewHolder.btnDeliveryStatus.setText("배송완료");
                mMyPageChildListViewHolder.btnDeliveryStatus.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
                mMyPageChildListViewHolder.btnDeliveryStatus.setEnabled(false);
                mMyPageChildListViewHolder.tvDeliveryDay.setText(childData.getPayDate().substring(4)+" 도착");

            }

            mMyPageChildListViewHolder.btnDeliveryStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((BaseActivity)mContext).showCustomToast("배송추적OK");
                }
            });

        }

        return convertView;

    }

    @Override
    public boolean hasStableIds() { return true; } // stable ID인지 boolean 값으로 반환

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) { return true; } // 선택여부를 boolean 값으로 반환


    public class MyPageChildListViewHolder {
        TextView tvProdcutTitle,tvProductPrice,tvProductCount,tvDeliveryDay;
        ImageView ivProductThumnail;
        Button btnDeliveryStatus;
    }
}
