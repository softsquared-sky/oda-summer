package com.softsquared.oda.src.detail;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.softsquared.oda.src.detail.productDetail.FragmentProductDetail;
import com.softsquared.oda.src.detail.productReview.FragmentProductReview;

public class DetailViewPagerAdapter extends FragmentPagerAdapter {
    FragmentProductDetail mFmtProductdetail;
    FragmentProductReview mFmtProductReview;

    DetailViewPagerAdapter(FragmentManager fr) {
        super(fr);
        mFmtProductdetail = new FragmentProductDetail();
        mFmtProductReview = new FragmentProductReview();
    } //꼭 있어야함


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mFmtProductdetail;
            case 1:
                return mFmtProductReview;
        }
        return null;
    }

    private static int PAGE_NUMBER = 2; //생성할 프래그먼트

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }

    //탭 레이아웃의 제목을 지정해준다
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "상품상세";
            case 1:
                return "상품후기";
            default:
                return null;
        }
    }
}
