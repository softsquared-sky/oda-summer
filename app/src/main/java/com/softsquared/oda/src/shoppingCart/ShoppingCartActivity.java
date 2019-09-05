package com.softsquared.oda.src.shoppingCart;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.softsquared.oda.src.BaseActivity;
import com.softsquared.odaproject.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingCartActivity extends BaseActivity {

    private Button mBtnShoppingCartAllCheck;
    public ExpandableListView mExpandableListView; // ExpandableListView 변수 선언
    public ShoppingCartExpandableListViewAdapter mShoppingCartExpandableLvAdapter; // 위 ExpandableListView를 받을 CustomAdapter(2번 class에 해당)를 선언
    public ArrayList<String> mParentList; // ExpandableListView의 Parent 항목이 될 List 변수 선언
    public ArrayList<ShoppingCartListData> mQuickProduct; // ExpandableListView의 Child 항목이 될 List를 각각 선언
    public ArrayList<ShoppingCartListData> mShoppingCartProduct;
    public HashMap<String, ArrayList<ShoppingCartListData>> mChildList; // 위 ParentList와 ChildList를 연결할 HashMap 변수 선언

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        mBtnShoppingCartAllCheck=findViewById(R.id.btn_shopping_cart_select_all_item);

        // ExpandableListView의 ParentList에 해당할 항목을 입력
        mParentList = new ArrayList<>();
        mParentList.add("바로주문 상품");
        mParentList.add("장바구니 상품");


        // 앞서 ParentList에 연결할 ChildList 항목을 선언 및 입력
        ShoppingCartListData apple = new ShoppingCartListData(false,
                "http://thumbnail11.coupangcdn.com/thumbnails/remote/492x492ex/" +
                        "image/vendor_inventory/72b7/ae0ecd6fd31cb90c76d0c4f9e07653ba518e55b81e5f522465821c91e693.jpg",
                "무항생제 구운계란 국산 HACCP 구운란 대란 30구 1판",
                4900,1);
        ShoppingCartListData pair = new ShoppingCartListData(false,
                "http://thumbnail11.coupangcdn.com/thumbnails/remote/492x492ex/" +
                        "image/vendor_inventory/72b7/ae0ecd6fd31cb90c76d0c4f9e07653ba518e55b81e5f522465821c91e693.jpg",
                "무항생제 구운계란 국산 HACCP 구운란 대란 30구 1판",
                5900,3);
        ShoppingCartListData persimmon = new ShoppingCartListData(false,
                "http://thumbnail11.coupangcdn.com/thumbnails/remote/492x492ex/" +
                        "image/vendor_inventory/72b7/ae0ecd6fd31cb90c76d0c4f9e07653ba518e55b81e5f522465821c91e693.jpg",
                "무항생제 구운계란 국산 HACCP 구운란 대란 30구 1판",
                4900,1);
        mQuickProduct = new ArrayList<ShoppingCartListData>();
        mQuickProduct.add(apple);
        mQuickProduct.add(pair);
        mQuickProduct.add(persimmon);

        ShoppingCartListData onion = new ShoppingCartListData(false,
                "http://thumbnail11.coupangcdn.com/thumbnails/remote/492x492ex/" +
                        "image/vendor_inventory/72b7/ae0ecd6fd31cb90c76d0c4f9e07653ba518e55b81e5f522465821c91e693.jpg",
                "무항생제 구운계란 국산 HACCP 구운란 대란 30구 1판",
                4900,1);
                // );
        ShoppingCartListData cabbage = new ShoppingCartListData(false,
                "http://thumbnail11.coupangcdn.com/thumbnails/remote/492x492ex/" +
                        "image/vendor_inventory/72b7/ae0ecd6fd31cb90c76d0c4f9e07653ba518e55b81e5f522465821c91e693.jpg",
                "무항생제 구운계란 국산 HACCP 구운란 대란 30구 1판",
                4900,1);
        ShoppingCartListData potato = new ShoppingCartListData(false,
                "http://thumbnail11.coupangcdn.com/thumbnails/remote/492x492ex/" +
                        "image/vendor_inventory/72b7/ae0ecd6fd31cb90c76d0c4f9e07653ba518e55b81e5f522465821c91e693.jpg",
                "무항생제 구운계란 국산 HACCP 구운란 대란 30구 1판",
                4900,1);
        ShoppingCartListData sweetPotato = new ShoppingCartListData(false,
                "http://thumbnail11.coupangcdn.com/thumbnails/remote/492x492ex/" +
                        "image/vendor_inventory/72b7/ae0ecd6fd31cb90c76d0c4f9e07653ba518e55b81e5f522465821c91e693.jpg",
                "무항생제 구운계란 국산 HACCP 구운란 대란 30구 1판",
                4900,1);
        ShoppingCartListData pumpkin = new ShoppingCartListData(false,
                "http://thumbnail11.coupangcdn.com/thumbnails/remote/492x492ex/" +
                        "image/vendor_inventory/72b7/ae0ecd6fd31cb90c76d0c4f9e07653ba518e55b81e5f522465821c91e693.jpg",
                "무항생제 구운계란 국산 HACCP 구운란 대란 30구 1판",
                4900,1);
        mShoppingCartProduct = new ArrayList<>();
        mShoppingCartProduct.add(onion);
        mShoppingCartProduct.add(cabbage);
        mShoppingCartProduct.add(potato);
        mShoppingCartProduct.add(sweetPotato);
        mShoppingCartProduct.add(pumpkin);
        mChildList = new HashMap<>();
        mChildList.put(mParentList.get(0), mQuickProduct);
        mChildList.put(mParentList.get(1), mShoppingCartProduct);

        // 앞서 정의해 놓은 ExpandableListView와 그 CustomAdapter를 선언 및 연결한 후
        // ExpandableListView에 대한 OnClickListener 등을 선언
        mExpandableListView = findViewById(R.id.expand_lv_shopping_cart);
        mShoppingCartExpandableLvAdapter = new ShoppingCartExpandableListViewAdapter(this, mParentList, mChildList);
        mExpandableListView.setAdapter(mShoppingCartExpandableLvAdapter);
        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });
        mExpandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                showCustomToast("gP: "+groupPosition +"cP : "+childPosition);
                return false;
            }
        });


    }

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_shopping_cart_arrow_back :
                finish();
                break;
            case R.id.btn_shopping_cart_select_all_item:
                mBtnShoppingCartAllCheck.setSelected(!mBtnShoppingCartAllCheck.isSelected());
                if (mBtnShoppingCartAllCheck.isSelected()) {
                    mBtnShoppingCartAllCheck.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_checkbox, 0, 0, 0);
//                    mMainRecyclerViewAdapterdapter.allCheck();
                } else {
                    mBtnShoppingCartAllCheck.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_checkbox_off, 0, 0, 0);
                }
                break;
            default:
                break;
        }
    }
}
