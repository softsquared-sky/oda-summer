package com.softsquared.oda.src.myPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.main.MainActivity;
import com.softsquared.oda.src.setting.SettingActivity;
import com.softsquared.odaproject.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MyPageActivity extends BaseActivity {

    private Spinner mMyPageSpn;
    private ArrayAdapter mSpnArrayAdapter;
    private ExpandableListView mMyPageExpandableListView; // ExpandableListView 변수 선언
    private MyPageExpandableListViewAdapter mMyPageExpandableLvAdapter; // 위 ExpandableListView를 받을 CustomAdapter(2번 class에 해당)를 선언
    private ArrayList<String> mOrderNumber; // ExpandableListView의 Parent 항목이 될 List 변수 선언
    private ArrayList<MyPageListData> mProductList1; // ExpandableListView의 Child 항목이 될 List를 각각 선언
    private ArrayList<MyPageListData> mProductList2;
    private HashMap<String, ArrayList<MyPageListData>> mChildList; // 위 ParentList와 ChildList를 연결할 HashMap 변수 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        mMyPageSpn = findViewById(R.id.spn_my_page_sort);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("최신순");
        arrayList.add("오래된순");
        mSpnArrayAdapter = new ArrayAdapter<>(getApplicationContext(),R.layout.custom_spinner_item,arrayList);
        mMyPageSpn.setAdapter(mSpnArrayAdapter);


        // ExpandableListView의 ParentList에 해당할 항목을 입력
        mOrderNumber = new ArrayList<>();
        mOrderNumber.add("19/09/06 주문번호 19090600001");
        mOrderNumber.add("19/08/30 주문번호 19083000001");


        // 앞서 ParentList에 연결할 ChildList 항목을 선언 및 입력
        //dummy
        MyPageListData apple = new MyPageListData(null,
                "계란",
                        4900,
                3,
                "9/8 \n도착예정",true);

        MyPageListData pair = new MyPageListData("http://thumbnail11.coupangcdn.com/thumbnails/remote/492x492ex/" +
        "image/vendor_inventory/72b7/ae0ecd6fd31cb90c76d0c4f9e07653ba518e55b81e5f522465821c91e693.jpg",
                "사과",
                4100,
                3,
                "9/9 \n도착예정",true);

        MyPageListData persimmon = new MyPageListData(null,
                "맛있는 순대",
                6000,
                3,
                "9/10 \n도착예정",true);

        mProductList1 = new ArrayList<>();
        mProductList1.add(apple);
        mProductList1.add(pair);
        mProductList1.add(persimmon);
        mProductList1.add(persimmon);
        mProductList1.add(persimmon);
        mProductList1.add(persimmon);

        MyPageListData onion = new MyPageListData(null,
                "내가 만든 떡볶이",
                3000,
                5,
                "9/1 도착",false);

        MyPageListData cabbage = new MyPageListData(null,
                "토종닭 5kg",
                9900,
                2,
                "9/1 도착",false);
        MyPageListData potato = new MyPageListData(null,
                "맛있는 계란",
                5000,
                6,
                "9/1 도착",false);
        MyPageListData sweetPotato = new MyPageListData(null,
                "삶은 계란",
                6000,
                1,
                "9/1 도착",false);
        MyPageListData pumpkin = new MyPageListData(null,
                "맥반석 계란",
                2600,
                1,
                "9/1 도착",false);
        mProductList2 = new ArrayList<>();
        mProductList2.add(onion);
        mProductList2.add(cabbage);
        mProductList2.add(potato);
        mProductList2.add(sweetPotato);
        mProductList2.add(pumpkin);
        mChildList = new HashMap<>();
        mChildList.put(mOrderNumber.get(0), mProductList1);
        mChildList.put(mOrderNumber.get(1), mProductList2);

        // 앞서 정의해 놓은 ExpandableListView와 그 CustomAdapter를 선언 및 연결한 후
        // ExpandableListView에 대한 OnClickListener 등을 선언
        mMyPageExpandableListView = findViewById(R.id.expand_lv_my_page);
        mMyPageExpandableLvAdapter = new MyPageExpandableListViewAdapter(this, mOrderNumber, mChildList);
        mMyPageExpandableListView.setAdapter(mMyPageExpandableLvAdapter);
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
                showCustomToast("gP: "+groupPosition +"cP : "+childPosition);
                return false;
            }
        });

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.tv_my_page_coupon_icon :
                showCustomToast(getString(R.string.no_development));
                break;
            case R.id.tv_my_page_order_statistics_icon :
                showCustomToast(getString(R.string.no_development));
                break;
            case R.id.tv_my_page_payment_icon :
                showCustomToast(getString(R.string.no_development));
                break;
            case R.id.iv_my_page_setting:
                startActivity(new Intent(MyPageActivity.this, SettingActivity.class));
                break;
            case R.id.iv_my_page_bottom_bar_home:
                Intent intent = new Intent(MyPageActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
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
}
