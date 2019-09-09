package com.softsquared.oda.src.myPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.main.MainActivity;
import com.softsquared.oda.src.myPage.Interfaces.MyPageActivityView;
import com.softsquared.oda.src.setting.SettingActivity;
import com.softsquared.odaproject.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MyPageActivity extends BaseActivity implements MyPageActivityView {

    private Spinner mMyPageSpn;
    private ArrayAdapter mSpnArrayAdapter;
    private ExpandableListView mMyPageExpandableListView; // ExpandableListView 변수 선언
    private MyPageExpandableListViewAdapter mMyPageExpandableLvAdapter; // 위 ExpandableListView를 받을 CustomAdapter(2번 class에 해당)를 선언

    ArrayList<String> mOrderNumber =new ArrayList<>(); // ExpandableListView의 Parent 항목이 될 List 변수 선언
    ArrayList<MyPageListData> mProductList =new ArrayList<>(); // ExpandableListView의 Child 항목이 될 List를 각각 선언
    HashMap<String, ArrayList<MyPageListData>> mChildList =new HashMap<>(); // 위 ParentList와 ChildList를 연결할 HashMap 변수 선언

    //    private ArrayList<MyPageListData> mProductList2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        mMyPageSpn = findViewById(R.id.spn_my_page_sort);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("최신순");
        arrayList.add("오래된순");
        mSpnArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.custom_spinner_item, arrayList);
        mMyPageSpn.setAdapter(mSpnArrayAdapter);
        mMyPageExpandableListView = findViewById(R.id.expand_lv_my_page);

        mOrderNumber=new ArrayList<>();
        mProductList=new ArrayList<>();
        mChildList=new HashMap<>();

        getMyPage();

        mMyPageExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });
        mMyPageExpandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });
        mMyPageExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                showCustomToast("gP: " + groupPosition + "cP : " + childPosition);
                return false;
            }
        });
    }

    private void getMyPage() {

        final MyPageService myPageService = new MyPageService(this);
        showProgressDialog();
        myPageService.getMyPage();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_my_page_coupon_icon:
                showCustomToast(getString(R.string.no_development));
                break;
            case R.id.tv_my_page_order_statistics_icon:
                showCustomToast(getString(R.string.no_development));
                break;
            case R.id.tv_my_page_payment_icon:
                showCustomToast(getString(R.string.no_development));
                break;
            case R.id.iv_my_page_setting:
                startActivity(new Intent(MyPageActivity.this, SettingActivity.class));
                break;
            case R.id.iv_my_page_bottom_bar_home:
                Intent intent = new Intent(MyPageActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
            case R.id.iv_my_page_bottom_bar_category:
                showCustomToast(getString(R.string.no_development));
                break;
            case R.id.iv_my_page_bottom_bar_tip:
                showCustomToast(getString(R.string.no_development));
                break;
            case R.id.iv_my_page_bottom_bar_my_page:
                showCustomToast(getString(R.string.current_page));
                break;
        }
    }

    @Override
    public void validateSuccess(String message, ArrayList<MyPageListData> items) {

        hideProgressDialog();
        for (int i = 0; i < items.size(); i++) {
            MyPageListData temp = items.get(i);

            mOrderNumber.add("주문번호" + temp.getPayDate() + temp.getPayDegree());
            mProductList.clear();
            for (int j = 0; j < temp.getOrderLists().size(); j++) {
                mProductList.add(items.get(j));
            }
            mChildList.put(mOrderNumber.get(i), mProductList);
        }
        // 앞서 정의해 놓은 ExpandableListView와 그 CustomAdapter를 선언 및 연결한 후
        // ExpandableListView에 대한 OnClickListener 등을 선언


        mMyPageExpandableLvAdapter = new MyPageExpandableListViewAdapter(this, mOrderNumber, mChildList);
        mMyPageExpandableListView.setAdapter(mMyPageExpandableLvAdapter);
        mMyPageExpandableLvAdapter.notifyDataSetChanged();
    }

    @Override
    public void validateFailure(String message) {

        hideProgressDialog();
        showCustomToast(message);
    }
}
