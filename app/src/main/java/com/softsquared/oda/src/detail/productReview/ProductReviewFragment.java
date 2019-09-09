package com.softsquared.oda.src.detail.productReview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.detail.DetailActivity;
import com.softsquared.oda.src.detail.productReview.interfaces.ProductReviewFragmentView;
import com.softsquared.odaproject.R;

import java.util.ArrayList;

public class ProductReviewFragment extends Fragment implements ProductReviewFragmentView {

    private ArrayList<ProductReviewItem> mProductReviewItemList = new ArrayList<>();
    private ProductReviewAdapter mLvProductReviewAdapter;
    private ListView mLvProductReview;
    private Button mBtnReviewEnrollment;
    private int mProductId;

    public ProductReviewFragment(int productId) {

        mProductId=productId;
//        for(int i=0;i<10;i++) {
//            mProductReviewItemList.add( new ProductReviewItem(null, i + "번째 리뷰 제목", "2019-09-04", "jhw**** 님",
//                    "dummy dummy 너무 yummy yummy 하네요 좋습니다"));
//        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_product_review, container, false);
        // Inflate the layout for this fragment

        mBtnReviewEnrollment = view.findViewById(R.id.btn_detail_review_enrollment);

//        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.fragment_recent,container,false);
        mLvProductReview = view.findViewById(R.id.lv_detail_review);
        mLvProductReviewAdapter = new ProductReviewAdapter(mProductReviewItemList,getActivity());
        mLvProductReview.setAdapter(mLvProductReviewAdapter);

//        mLvProductReview.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                mLvProductReview.requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });

        ((DetailActivity) getActivity()).refresh();

        mBtnReviewEnrollment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((DetailActivity) getActivity()).showCustomToast(getString(R.string.no_access));
            }
        });

        getProductReview();
        return view;
    }

    private void getProductReview() {
        ( (BaseActivity)getActivity()).showProgressDialog();

        final ProductReviewService productReviewService = new ProductReviewService(this);
        productReviewService.getProductReview(mProductId);
    }


    @Override
    public void validateSuccess(ArrayList<ProductReviewItem> items) {

        //값을 받아온다음에 add해주면 끝!
        ( (BaseActivity)getActivity()).hideProgressDialog();

        for (int i=0;i<items.size();i++){

            mProductReviewItemList.add(items.get(i));
        }
        mLvProductReviewAdapter.notifyDataSetChanged();

    }

    @Override
    public void validateFailure(String message) {
        ( (BaseActivity)getActivity()).hideProgressDialog();
        ( (BaseActivity)getActivity()).showCustomToast(message == null || message.isEmpty() ? "리뷰를 불러올 수 없습니다." : message);
        for(int i=0;i<20;i++) {
            mProductReviewItemList.add( new ProductReviewItem(null, i + "번째 리뷰 제목", "2019-09-04", "jhw**** 님",
                    "dummy dummy 너무 yummy yummy 하네요 좋습니다"));
        }
        mLvProductReviewAdapter.notifyDataSetChanged();
    }
}
