package com.softsquared.oda.src.shoppingCart;

public class ShoppingCartListData {
    private boolean selected;
    private String productImage;
    private String productTitle;
    private int productPrice;
    private int productAmount;

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
