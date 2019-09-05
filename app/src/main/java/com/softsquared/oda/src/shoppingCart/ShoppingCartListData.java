package com.softsquared.oda.src.shoppingCart;

public class ShoppingCartListData {
    private boolean selected;
    private String productImage;
    private String productTitle;
    private int productPrice;

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    private int productCount;

    public ShoppingCartListData(boolean selected, String productImage, String productTitle, int productPrice, int productCount) {
        this.selected = selected;
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.productCount = productCount;
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

    public int getProductCount() {
        return productCount;
    }
}
