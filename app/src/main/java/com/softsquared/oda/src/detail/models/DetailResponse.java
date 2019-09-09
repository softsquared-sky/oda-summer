package com.softsquared.oda.src.detail.models;

import com.google.gson.annotations.SerializedName;

public class DetailResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("pName")
    private String productTitle;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getProductTitle() {
        return productTitle;
    }
}
