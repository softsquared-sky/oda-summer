package com.softsquared.oda.src.search;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;


import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.search.recent.RecentListFragment;
import com.softsquared.odaproject.R;

public class SearchActivity extends BaseActivity {

    ViewPager mViewPager;
    SearchViewPagerAdapter mViewPagerAdapter;
    TabLayout mTabLayout;
    EditText mEtSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mViewPager=findViewById(R.id.search_viewpager);
        mTabLayout=findViewById(R.id.search_tablayout);

        mViewPagerAdapter = new SearchViewPagerAdapter(getSupportFragmentManager()); //Viewpager와 Fragment를 연결
        mTabLayout.setupWithViewPager(mViewPager); //Viewpager와 TabLayout을 연결해주는 코드!
        mViewPager.setAdapter(mViewPagerAdapter); //Viewpager에 선택된 fragment를 띄워준다.

        mEtSearch = (EditText)findViewById(R.id.et_search_keyword);
        mEtSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //Enter key Action
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    //Enter키눌렀을떄 처리

                    String result = mEtSearch.getText().toString();
                    ((RecentListFragment) mViewPagerAdapter.getItem(0)).addItem(result);
                    refresh();
                    hideKeyboard();
                    return true;
                }
                return false;
            }
        });


    }

    public void searchOnClick(View view){
        switch(view.getId()){
            case R.id.iv_search_arrow_back :
                finish();
                break;
            case  R.id.iv_search_shopping_cart :
                showCustomToast(getString(R.string.no_access));
                break;
        }
    }

    public  void hideKeyboard(){
        mEtSearch.clearFocus();
        InputMethodManager immhide = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
    public void refresh() {
        mViewPagerAdapter.notifyDataSetChanged();
    }
}
