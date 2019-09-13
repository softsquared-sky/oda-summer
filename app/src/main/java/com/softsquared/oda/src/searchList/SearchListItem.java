package com.softsquared.oda.src.searchList;

import com.google.gson.annotations.SerializedName;

public class SearchListItem {
    @SerializedName("pNum")
    private int productId;

    @SerializedName("pName")
    private String productTitle;

    @SerializedName("odaPrice")
    private int productPrice;

    @SerializedName("imageUrl")
    private String productThumnail;

    public SearchListItem(int productId, String productTitle, int productPrice, String productThumnail) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productThumnail = productThumnail;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public String getProductThumnail() {
        return productThumnail;
    }
}
