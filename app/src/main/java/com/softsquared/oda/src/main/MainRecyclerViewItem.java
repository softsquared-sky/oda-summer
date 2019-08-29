package com.softsquared.oda.src.main;

import android.widget.CheckBox;
import android.widget.ImageView;

public class MainRecyclerViewItem {
    private boolean selected;
    private String mainImage;
    private String odaTitle;
    private int odaPrice;

    public MainRecyclerViewItem(boolean selected, String mainImage, String odaTitle, int odaPrice) {
        this.selected = selected;
        this.mainImage = mainImage;
        this.odaTitle = odaTitle;
        this.odaPrice = odaPrice;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
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
