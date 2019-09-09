package com.softsquared.oda.src.myPage;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyPageListData {

    @SerializedName("payDate")
    private String payDate;

    @SerializedName("deliveryStatus")
    private String deliveryStatus;

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public String getPayDate() {
        return payDate;
    }

    public int getPayDegree() {
        return payDegree;
    }

    @SerializedName("payDegree")
    private int payDegree;

    public MyPageListData(String payDate, String deliveryStatus, int payDegree, ArrayList<OrderList> orderLists) {
        this.payDate = payDate;
        this.deliveryStatus = deliveryStatus;
        this.payDegree = payDegree;
        this.orderLists = orderLists;
    }

    @SerializedName("orderList")
    private ArrayList<OrderList> orderLists;

    public ArrayList<OrderList> getOrderLists() {
        return orderLists;
    }

    public class OrderList {

        @SerializedName("pNum")
        private int productId;
        @SerializedName("imageUrl")
        private String productThumnail;
        @SerializedName("pName")
        private String productTitle;
        @SerializedName("odaPrice")
        private int productPrice;
        @SerializedName("amount")
        private int productAmount;
        @SerializedName("paySeq")
        private int paySeq;

        public int getProductId() {
            return productId;
        }

        public String getProductThumnail() {
            return productThumnail;
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

        public int getPaySeq(){return paySeq;}


    }

}
