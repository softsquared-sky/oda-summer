package com.softsquared.oda.src.detail.productDetail;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.detail.productDetail.interfaces.ProductDetailFragmentView;
import com.softsquared.oda.src.detail.productDetail.models.ProductDetailResponse;
import com.softsquared.odaproject.R;

import java.text.DecimalFormat;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */

public class ProductDetailFragment extends Fragment implements ProductDetailFragmentView {
    private Activity activity;




    private int mProductId,mProductPrice;
    private String mProductTitle, mProductImage;
    private ImageView mIvProductTopImage,mIvProductBottomImage1,mIvProductBottomImage2; //상단이미지, 하단이미지2개
    private TextView mTvProductPrice,mTvProductTitle;//가격,제목
    private TextView mTvProductQpp,mTvProductStoreMethod,mTvProductOrigin;//수량정보,보관법,원산지

    public ProductDetailFragment(int mProductId, String mProductImage, String mProductTitle, int mProductPrice) {
        this.mProductId = mProductId;
        this.mProductPrice = mProductPrice;
        this.mProductTitle = mProductTitle;
        this.mProductImage = mProductImage;
    }

    public ProductDetailFragment(){

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            activity = (Activity) context;
        }

    }
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_product_detail, container, false);

        mIvProductTopImage = view.findViewById(R.id.iv_detail_product_top_image);
        mIvProductBottomImage1 = view.findViewById(R.id.iv_detail_product_bottom_image1);
        mIvProductBottomImage2 = view.findViewById(R.id.iv_detail_product_bottom_image2);
        mTvProductTitle = view.findViewById(R.id.tv_detail_product_title);
        mTvProductPrice = view.findViewById(R.id.tv_detail_product_price);
        mTvProductQpp = view.findViewById(R.id.tv_detail_product_qpp);
        mTvProductStoreMethod = view.findViewById(R.id.tv_detail_product_store_method);
        mTvProductOrigin = view.findViewById(R.id.tv_detail_product_origin);

        Glide.with(activity)
                .load(mProductImage)
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(mIvProductTopImage);

        mTvProductTitle.setText(mProductTitle);

        DecimalFormat myFormatter = new DecimalFormat("###,###");
        String formattedStringPrice = myFormatter.format(mProductPrice);
        mTvProductPrice.setText(formattedStringPrice+"원");


        //detail이 생긴뒤 네트워크를 통해 불러온다
        getProductDetailInfo();

        return view;
    }

    private void getProductDetailInfo() {

        final ProductDetailService productDetailService = new ProductDetailService(this);
        if(mProductId==0){

        }
        else{
            ((BaseActivity) Objects.requireNonNull(activity)).showProgressDialog();
            productDetailService.getProductDetailInfo(mProductId);
        }

    }
    @Override
    public void validateSuccess(String text, ProductDetailResponse.DetailResult detailResult) {

       mTvProductQpp.setText( detailResult.getQpp());
       mTvProductOrigin.setText( detailResult.getOrigin());
       mTvProductStoreMethod.setText( detailResult.getStoreMethod());

       if(detailResult.getDetailImageResults().size()==1) {
           Glide.with(Objects.requireNonNull(activity))
                   .load((detailResult.getDetailImageResults().get(0)).getImageUrl())
                   .placeholder(R.mipmap.ic_launcher)
                   .centerCrop()
                   .into(mIvProductBottomImage1);
       }
       else if (detailResult.getDetailImageResults().size()==2){
           Glide.with(Objects.requireNonNull(activity))
                   .load((detailResult.getDetailImageResults().get(0)).getImageUrl())
                   .placeholder(R.mipmap.ic_launcher)
                   .into(mIvProductBottomImage1);

        Glide.with(activity)
                .load((detailResult.getDetailImageResults().get(1)).getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .into(mIvProductBottomImage2);
       }
       else{

       }
        ((BaseActivity) Objects.requireNonNull(activity)).hideProgressDialog();
    }

    @Override
    public void validateFailure(String message) {
        ((BaseActivity) Objects.requireNonNull(activity)).hideProgressDialog();
        ((BaseActivity)activity).showCustomToast("상품정보를 불러오지 못했습니다.");
    }
}
