package com.softsquared.oda.src.searchList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.detail.DetailActivity;
import com.softsquared.oda.src.main.MainActivity;
import com.softsquared.oda.src.searchList.interfaces.SearchListActivityView;
import com.softsquared.oda.src.shoppingCart.ShoppingCartService;
import com.softsquared.odaproject.R;

import java.util.ArrayList;

public class SearchListActivity extends BaseActivity implements SearchListActivityView {

    private ArrayList<SearchListItem> mSearchListItem=new ArrayList<>();
    private ListView mLvSearchList;
    private SearchListAdapter mSearchListAdpater;
    private String productTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        mLvSearchList=findViewById(R.id.lv_search_result);
        mSearchListAdpater = new SearchListAdapter(mSearchListItem,this);
        mLvSearchList.setAdapter(mSearchListAdpater);

        productTitle = getIntent().getStringExtra("pName");



        getShoppingList();

        mLvSearchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent = new Intent(SearchListActivity.this, DetailActivity.class);
                intent.putExtra("productId", mSearchListItem.get(position).getProductId());
                intent.putExtra("productImage", mSearchListItem.get(position).getProductThumnail());
                intent.putExtra("productTitle",  mSearchListItem.get(position).getProductTitle());
                intent.putExtra("productPrice", mSearchListItem.get(position).getProductPrice());
                startActivity(intent);
            }
        });
    }

    private void getShoppingList() {

        final SearchListService searchListService = new SearchListService(this);
        showProgressDialog();


        String pName = productTitle;
        if (pName!=null||pName.length()!=0){
            searchListService.getSearchResult(pName);
        }
        else{
            showCustomToast("공백이 입력되었습니다.");
            return;
        }
    }

    @Override
    public void vaildateSuccess(String message, ArrayList<SearchListItem> items) {
        hideProgressDialog();

        for(int i=0;i<items.size();i++){
            mSearchListItem.add(items.get(i));
        }
        mSearchListAdpater.notifyDataSetChanged();
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }
}
