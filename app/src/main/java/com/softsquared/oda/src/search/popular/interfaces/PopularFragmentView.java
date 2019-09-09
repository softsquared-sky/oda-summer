package com.softsquared.oda.src.search.popular.interfaces;

import com.softsquared.oda.src.search.popular.PopularListViewItem;
import com.softsquared.oda.src.shoppingCart.ShoppingCartListData;

import java.util.ArrayList;

public interface PopularFragmentView {

    void vaildateSuccess(ArrayList<PopularListViewItem> items);
    void validateFailure(String message);
}
