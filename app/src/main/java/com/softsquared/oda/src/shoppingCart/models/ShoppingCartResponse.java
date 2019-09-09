package com.softsquared.oda.src.shoppingCart.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.oda.src.shoppingCart.ShoppingCartListData;

import java.util.ArrayList;

public class ShoppingCartResponse {


    public ArrayList<ShoppingCartListData> getShoppingCartListData() {
        return shoppingCartListData;
    }

    @SerializedName("basketList")
    public  ArrayList<ShoppingCartListData> shoppingCartListData;

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
