package com.softsquared.oda.src.myPage.Models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.oda.src.myPage.MyPageListData;

import java.util.ArrayList;

public class MyPageResponse {

    @SerializedName("result")
    private ArrayList<MyPageListData> myPageListData;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public ArrayList<MyPageListData> getMyPageListData(){
        return myPageListData;
    }
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
