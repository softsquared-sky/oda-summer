package com.softsquared.oda.src.detail;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.softsquared.oda.src.detail.productDetail.ProductDetailFragment;
import com.softsquared.oda.src.detail.productReview.ProductReviewFragment;

import java.util.ArrayList;

public class DetailViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;

    DetailViewPagerAdapter(FragmentManager fr,int productId,String productImage,String productTitle,int productPrice) {
        super(fr);
        this.fragments = new ArrayList<>();
        fragments.add(new ProductDetailFragment(productId,productImage,productTitle,productPrice));
        fragments.add(new ProductReviewFragment(productId));
    } //꼭 있어야함

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
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
