package com.softsquared.oda.src.search.recent;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
//   private RecentListViewAdapter mRecentLvAdapter;
//    ListView mLvRecent;
    private ArrayList<RecentListViewItem> mRecentItemList = new ArrayList<>();
    private RecentListViewAdapter mRecentLvAdapter;
    private ListView mLvRecent;
//    private SharedPreferences mSharedPref;
    private Gson gson;

    public RecentListFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recent, container, false);
        // Inflate the layout for this fragment

//        mSharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        gson = new Gson();
        String json = sSharedPreferences.getString("recentList", "");
        Type type = new TypeToken<ArrayList<RecentListViewItem>>() {
        }.getType();
        if (gson.fromJson(json, type) != null) {
            mRecentItemList = gson.fromJson(json, type);
        } else {
        }

//        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.fragment_recent,container,false);
        mLvRecent = (ListView) view.findViewById(R.id.lv_search_recent);
        mRecentLvAdapter = new RecentListViewAdapter(mRecentItemList,getActivity());
        mLvRecent.setAdapter(mRecentLvAdapter);

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
        String json = gson.toJson(mRecentItemList);
        editor.putString("recentList", json);
        editor.commit();
    }

    public void addItem(String text) {

        if (mRecentItemList.contains(text)){
            mRecentItemList.remove(mRecentItemList.indexOf(text));
            mRecentItemList.add(0,new RecentListViewItem(text));
        }
        else{
            mRecentItemList.add(0,new RecentListViewItem(text));
        }
    }
    }
