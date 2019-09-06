package com.softsquared.oda.src.myPage;

public class MyPageListData {

    private String productThumnail;
    private String productTitle;
    private int productPrice;
    private int productAmount;
    private String deliveryDay;
    private boolean deliveryStatus;

    public MyPageListData(String productThumnail, String productTitle, int productPrice, int productAmount, String deliveryDay, boolean deliveryStatus) {
        this.productThumnail = productThumnail;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productAmount = productAmount;
        this.deliveryDay = deliveryDay;
        this.deliveryStatus = deliveryStatus;
    }


    public String getProductThumnail() {
        return productThumnail;
    }

    public void setProductThumnail(String productThumnail) {
        this.productThumnail = productThumnail;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
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

    public String getDeliveryDay() {
        return deliveryDay;
    }

    public void setDeliveryDay(String deliveryDay) {
        this.deliveryDay = deliveryDay;
    }

    public boolean getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(boolean deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
