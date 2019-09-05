package com.softsquared.oda.src.detail.productReview.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.odaproject.R;

import java.util.ArrayList;

public class ProductReviewResponse {
    @SerializedName("result")
    ArrayList<Result> result;

    //ArrayList<ProductReviewItem>
    public ArrayList<Result> getResults() {
        return result;
    }

    public class Result{

        @SerializedName("id")
        private String id;
        @SerializedName("review")
        private String review;
        @SerializedName("reviewDate")
        private String reviewDate;
        @SerializedName("reviewImage")
        private String reviewImage;

    }
    //이부분 ProductReviewItem으로 바꾸고


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
