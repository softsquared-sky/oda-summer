package com.softsquared.oda.src.main.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.oda.src.main.MainRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

public class MainResponse {

    @SerializedName("result")
    public MainRecyclerViewItem mainRecyclerViewItems;

    public MainRecyclerViewItem getMainRecyclerViewItems() {
        return mainRecyclerViewItems;
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

    public boolean getIsSuccess() {
        return isSuccess;
    }
}




