package com.softsquared.oda.src.main;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MainRecyclerViewItem {

    @SerializedName("imageResult")
    private ArrayList<ImageResult> imageResults;
    public class ImageResult {

        @SerializedName("imageUrl")
        private String imageUrl;

        public String getImageUrl() {
            return imageUrl;
        }
    }

    @SerializedName("pNum")
    private int productId;

    @SerializedName("pName")
    private String odaTitle;

    @SerializedName("odaPrice")
    private int odaPrice;


    public int getProductId() {
        return productId;
    }

    private boolean selected;



    public ArrayList<ImageResult> getImageResults() {
        return imageResults;
    }


    public MainRecyclerViewItem(boolean selected) {
        this.selected = selected;
    }

    public MainRecyclerViewItem() {
        this.selected = false;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getOdaTitle() {
        return odaTitle;
    }

    public void setOdaTitle(String odaTitle) {
        this.odaTitle = odaTitle;
    }

    public int getOdaPrice() {
        return odaPrice;
    }

    public void setOdaPrice(int odaPrice) {
        this.odaPrice = odaPrice;
    }
}
