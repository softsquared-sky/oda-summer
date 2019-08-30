package com.softsquared.oda.src.signUp.models;

import com.google.gson.annotations.SerializedName;

public class SignUpResponse {
    @SerializedName("id")
    private int id;
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;


    public int getId() { return id; }

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
