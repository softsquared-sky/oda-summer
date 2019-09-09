package com.softsquared.oda.src.search.popular;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.softsquared.oda.src.BaseActivity;
import com.softsquared.oda.src.search.popular.interfaces.PopularFragmentView;
import com.softsquared.oda.src.search.recent.RecentListViewAdapter;
import com.softsquared.oda.src.shoppingCart.ShoppingCartService;
import com.softsquared.odaproject.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment implements PopularFragmentView {

    private ArrayList<PopularListViewItem> mPopularListViewItem = new ArrayList<>();
    private PopularListViewAdapter mPopularListViewAdapter;
    private ListView mLvPopular;
    public PopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popular, container, false);

        mLvPopular = view.findViewById(R.id.lv_search_popular);
        mPopularListViewAdapter = new PopularListViewAdapter(mPopularListViewItem,getActivity());
        mLvPopular.setAdapter(mPopularListViewAdapter);

        getPopularWord();
        return view;



    }


    private void getPopularWord() {

        final PopularFragmentService popularFragmentService = new PopularFragmentService(this);
        ( (BaseActivity)getActivity()).showProgressDialog();
        popularFragmentService.getPopularList();
    }

    @Override
    public void vaildateSuccess(ArrayList<PopularListViewItem> items) {
        ( (BaseActivity)getActivity()).hideProgressDialog();
        for(int i=0;i<items.size();i++){
            mPopularListViewItem.add(new PopularListViewItem(i+1,items.get(i).getTitle().toString()));
        }
        mPopularListViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void validateFailure(String message) {
        ( (BaseActivity)getActivity()).hideProgressDialog();
        ( (BaseActivity)getActivity()).showCustomToast(message);
    }

}

