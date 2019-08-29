package com.softsquared.oda.src.search.recent;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.softsquared.oda.src.search.SearchActivity;
import com.softsquared.odaproject.R;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.softsquared.oda.src.ApplicationClass.sSharedPreferences;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecentListFragment extends Fragment {
    //    ArrayList<RecentListViewItem> mRecentItemArrayList = new ArrayList<>();
//   private RecentListViewAdapter mLvRecentAdapter;
//    ListView mLvRecentList;
    private ArrayList<RecentListViewItem> mLvRecentItemList= new ArrayList<>();;
    private RecentListViewAdapter mLvRecentAdapter;
    private ListView mLvRecentList;
//    private SharedPreferences mSharedPref;
    private Gson gson;

    public RecentListFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recent_list, container, false);
        // Inflate the layout for this fragment

//        mSharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        gson = new Gson();
        String json = sSharedPreferences.getString("recentList", "");
        Type type = new TypeToken<ArrayList<RecentListViewItem>>() {
        }.getType();
        if (gson.fromJson(json, type) != null) {
            mLvRecentItemList = gson.fromJson(json, type);
        } else {
        }

//        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.fragment_recent_list,container,false);
        mLvRecentList = (ListView) view.findViewById(R.id.lv_recent_list);
        mLvRecentAdapter = new RecentListViewAdapter(mLvRecentItemList,getActivity());
        mLvRecentList.setAdapter(mLvRecentAdapter);
        mLvRecentList.setStackFromBottom(true);

        ((SearchActivity) getActivity()).refresh();

        return view;


    }
//
//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        // get TextView's Text.
//        RecentListViewItem item = (RecentListViewItem) l.getItemAtPosition(position);
//        String text = item.getTitle();
//        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
//
//        // TODO
//    }


    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        String json = gson.toJson(mLvRecentItemList);
        editor.putString("recentList", json);
        editor.commit();
    }

    public void addItem(String text) {
        mLvRecentAdapter.addItem(text);
    }
}