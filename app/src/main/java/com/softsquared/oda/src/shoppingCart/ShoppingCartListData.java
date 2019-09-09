package com.softsquared.oda.src.shoppingCart;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ShoppingCartListData implements Serializable {
    private boolean selected;
    @SerializedName("imageUrl")
    private String productImage;
    @SerializedName("pName")
    private String productTitle;
    @SerializedName("odaPrice")
    private int productPrice;

    public int getProductId() {
        return productId;
    }

    @SerializedName("pNum")
    private int productId;

    @SerializedName("stoke")
    private int stoke;

    public int getStoke() {
        return stoke;
    }


    private int productAmount;


    @SerializedName("type")
    private String type;

    public String getType() {
        return type;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    public ShoppingCartListData(boolean selected, String productImage, String productTitle, int productPrice, int productAmount) {
        this.selected = selected;
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productAmount = productAmount;
    }


    public boolean isSelected() {
        return selected;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public int getProductAmount() {
        return productAmount;
    }
}
