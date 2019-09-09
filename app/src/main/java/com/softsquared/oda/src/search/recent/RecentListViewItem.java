package com.softsquared.oda.src.search.recent;



public class RecentListViewItem {
    private String titleStr ;

    public RecentListViewItem(String titleStr) {
        this.titleStr = titleStr;
    }

    public void setTitle(String title) {
        titleStr = title ;
    }

    public String getTitle() {
        return this.titleStr ;
    }
}