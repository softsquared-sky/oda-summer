package com.softsquared.oda.src.detail.productDetail.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.oda.src.login.models.LoginResponse;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailResponse {

    @SerializedName("result")
    DetailResult result;

    public DetailResult getResult() {
        return result;
    }

    public class DetailResult{

        @SerializedName("origin")
        private String origin;

        @SerializedName("qpp")
        private String qpp;

        @SerializedName("storeMethod")
        private String storeMethod;



        @SerializedName("imageResult")
        private ArrayList<DetailImageResult> detailImageResults;


        public ArrayList<DetailImageResult> getDetailImageResults() {
            return detailImageResults;
        }

        public class DetailImageResult {

            @SerializedName("imageUrl")
            private String imageUrl;

            @SerializedName("turn")
            private int turn;

            public String getImageUrl() {
                return imageUrl;
            }

            public int getTurn() {
                return turn;
            }
        }


        public String getOrigin() {
            return origin;
        }

        public String getQpp() {
            return qpp;
        }

        public String getStoreMethod() {
            return storeMethod;
        }




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
