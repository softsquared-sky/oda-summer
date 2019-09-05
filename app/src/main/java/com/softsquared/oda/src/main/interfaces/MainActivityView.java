package com.softsquared.oda.src.main.interfaces;


import com.softsquared.oda.src.main.MainRecyclerViewItem;

public interface MainActivityView {

    void validateSuccess(MainRecyclerViewItem items);

    void validateFailure(String message);
}
