package com.softsquared.oda.src.detail.productDetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.softsquared.odaproject.R;


/**
 * A simple {@link Fragment} subclass.
 */

public class FragmentProductDetail extends Fragment {


    public FragmentProductDetail() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_product_detail, container, false);
        // Inflate the layout for this fragment



        return view;
    }
}
