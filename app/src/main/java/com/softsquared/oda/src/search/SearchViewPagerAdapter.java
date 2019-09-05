package com.softsquared.oda.src.search;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.softsquared.oda.src.search.recent.RecentListFragment;
import com.softsquared.oda.src.search.popular.PopularFragment;

public class SearchViewPagerAdapter extends FragmentPagerAdapter {
    RecentListFragment recentListFragment;
    PopularFragment popularFragment;

    SearchViewPagerAdapter(FragmentManager fr) {
        super(fr);
        recentListFragment = new RecentListFragment();
        popularFragment = new PopularFragment();
    } //꼭 있어야함


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return recentListFragment;
            case 1:
                return popularFragment;
        }
        return null;
    }

    private static int PAGE_NUMBER = 2; //생성할 프래그먼트 수

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
                return "최근검색어";
            case 1:
                return "인기검색어";
            default:
                return null;
        }
    }

}

