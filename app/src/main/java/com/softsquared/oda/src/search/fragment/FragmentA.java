package com.softsquared.oda.src.search.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.softsquared.odaproject.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentA extends Fragment {
//    ArrayList<RecentListViewItem> mRecentItemArrayList = new ArrayList<>();
//   private RecentListViewAdapter mLvRecentAdapter;
//    ListView mLvRecentList;
    public FragmentA() {
        // Required empty public constructor
    }
    public static FragmentA newInstance(){
        Bundle args = new Bundle();
        FragmentA fragment = new FragmentA();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//
//        mLvRecentList = (ListView)(getActivity().findViewById(R.id.list_view_recent));
//        mLvRecentAdapter = new RecentListViewAdapter(mRecentItemArrayList);//어댑터와 데이터 리스트 연결
//        mLvRecentList.setAdapter(mLvRecentAdapter); ;//리스트 뷰와 어댑터 연결

        return inflater.inflate(R.layout.fragment_a, container, false);



    }

}

