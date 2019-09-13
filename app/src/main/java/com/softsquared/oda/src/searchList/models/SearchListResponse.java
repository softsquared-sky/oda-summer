package com.softsquared.oda.src.searchList.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.oda.src.searchList.SearchListItem;

import java.util.ArrayList;

public class SearchListResponse {

    @SerializedName("result")
    ArrayList<SearchListItem> searchListItems;

    @SerializedName("code")
    private int code;

    public ArrayList<SearchListItem> getSearchListItems() {
        return searchListItems;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

}
