package com.softsquared.oda.src.order;

public class OrderListViewItem {

    private String productImage;
    private int productPrice;
    private int productCount;
    private String productTitle;

    public OrderListViewItem(String productImage, String productTitle, int productPrice, int productCount) {

        this.productImage = productImage;
        this.productPrice = productPrice;
        this.productCount = productCount;
        this.productTitle = productTitle;
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

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }
}
