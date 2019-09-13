package com.softsquared.oda.src.searchList.interfaces;

import com.softsquared.oda.src.searchList.SearchListItem;

import java.util.ArrayList;

public interface SearchListActivityView {

    void vaildateSuccess(String message,ArrayList<SearchListItem> items);
    void validateFailure(String message);
}
