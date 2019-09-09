package com.softsquared.oda.src.search.popular;

import com.google.gson.annotations.SerializedName;

public class PopularListViewItem {
    @SerializedName("word")
    private String title;
    private int rank;

    public PopularListViewItem(int rank,String title) {
        this.title = title;
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public int getRank() {
        return rank;
    }
}
