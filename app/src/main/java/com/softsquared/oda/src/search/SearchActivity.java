package com.softsquared.oda.src.search;

import android.os.Bundle;


import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.softsquared.oda.src.BaseActivity;
import com.softsquared.odaproject.R;

public class SearchActivity extends BaseActivity {

    ViewPager mViewPager;
    SearchViewpagerAdapter mViewpagerAdapter;
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mViewPager=findViewById(R.id.viewpager);
        mTabLayout=findViewById(R.id.tablayout);


        mViewpagerAdapter = new SearchViewpagerAdapter(getSupportFragmentManager()); //Viewpager와 Fragment를 연결
        mTabLayout.setupWithViewPager(mViewPager); //Viewpager와 TabLayout을 연결해주는 코드!
        mViewPager.setAdapter(mViewpagerAdapter); //Viewpager에 선택된 fragment를 띄워준다.


    }

}
