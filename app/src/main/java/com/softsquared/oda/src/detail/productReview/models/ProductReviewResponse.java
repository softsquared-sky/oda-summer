package com.softsquared.oda.src.detail.productReview.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.oda.src.detail.productReview.ProductReviewItem;
import com.softsquared.odaproject.R;

import java.util.ArrayList;

public class ProductReviewResponse {
    @SerializedName("result")
   private ArrayList<ProductReviewItem> results;

    //ArrayList<ProductReviewItem>
    public ArrayList<ProductReviewItem> getResults() {
        return results;
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
