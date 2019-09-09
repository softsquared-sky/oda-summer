package com.softsquared.oda.src.shoppingCart.interfaces;

import com.softsquared.oda.src.shoppingCart.ShoppingCartListData;

import java.util.ArrayList;

public interface ShoppingCartActivityView {

    void vaildateSuccess(ArrayList<ShoppingCartListData> items);
    void validateFailure(String message);

}
