package com.softsquared.oda.src.search;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.softsquared.oda.src.search.recent.RecentListFragment;
import com.softsquared.oda.src.search.popular.FragmentB;

public class SearchViewpagerAdapter extends FragmentPagerAdapter {
    RecentListFragment recentListFragment;
    FragmentB fragmentB;

    SearchViewpagerAdapter(FragmentManager fr) {
        super(fr);
        recentListFragment = new RecentListFragment();
        fragmentB = new FragmentB();
    } //꼭 있어야함


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return recentListFragment;
            case 1:
                return fragmentB;
        }
        return null;
    }

    private static int PAGE_NUMBER = 2; //생성할 프래그먼트 수, 나는 총 4개의 프래그먼트를 만들어줌!

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

