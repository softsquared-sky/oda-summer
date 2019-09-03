package com.softsquared.oda.src.detail.productReview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.softsquared.oda.src.detail.DetailActivity;
import com.softsquared.odaproject.R;

import java.util.ArrayList;

public class FragmentProductReview extends Fragment {

    private ArrayList<ProductReviewItem> mProductReviewItemList = new ArrayList<>();
    private ProductReviewAdapter mLvProductReviewAdapter;
    private ListView mLvProductReview;
    private Button mBtnReviewEnrollment;
    //    private SharedPreferences mSharedPref;

    public FragmentProductReview() {

        for(int i=0;i<5;i++) {
            mProductReviewItemList.add( new ProductReviewItem(null, i + "번째 리뷰 제목입니다~", "2019-09-03", "jhw**** 님",
                    "dummy dummy 너무 yummy yummy 하네요 좋습니다"));
        }
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

        ((DetailActivity) getActivity()).refresh();

        mBtnReviewEnrollment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((DetailActivity) getActivity()).showCustomToast(getString(R.string.no_access));
            }
        });
        return view;
    }


}
