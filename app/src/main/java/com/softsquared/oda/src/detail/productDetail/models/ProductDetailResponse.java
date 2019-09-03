package com.softsquared.oda.src.detail.productDetail.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.oda.src.login.models.LoginResponse;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailResponse {

    @SerializedName("result")
    Result result;

    public Result getResult() {
        return result;
    }

    public class Result{

        public ArrayList<ImageResult> getImageResults() {
            return imageResults;
        }

        public ArrayList<DetailContent> getDetailContents() {
            return detailContents;
        }

        @SerializedName("imageResult")
        public ArrayList<ImageResult> imageResults;

        @SerializedName("detailContent")
        public ArrayList<DetailContent> detailContents;

        public class ImageResult {

            @SerializedName("imageUrl")
            private String imageUrl;

            @SerializedName("turn")
            private int turn;
        }


        public class DetailContent{

            @SerializedName("origin")
            private String origin;

            @SerializedName("qpp")
            private String qpp;

            @SerializedName("storeMethod")
            private String storeMethod;

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


        @SerializedName("imageTurn")
        private String jwt;
        @SerializedName("image")
        public String getJwt() { return jwt; }
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
