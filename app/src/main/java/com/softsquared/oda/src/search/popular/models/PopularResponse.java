package com.softsquared.oda.src.search.popular.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.oda.src.search.popular.PopularListViewItem;

import java.util.ArrayList;

public class PopularResponse {

    @SerializedName("result")
    private ArrayList<PopularListViewItem> result;

    public ArrayList<PopularListViewItem> getResult(){
        return result;
    }


    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
