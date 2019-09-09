package com.softsquared.oda.src.order;

public class OrderListViewItem {

    private String productImage;
    private int productPrice;
    private int productAmount;
    private String productTitle;

    public OrderListViewItem(int productId, String productImage,String productTitle, int productPrice, int productAmount) {
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.productAmount = productAmount;
        this.productTitle = productTitle;
        this.productId = productId;
    }

    private int productId;

    public int getProductId() {
        return productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }
}
