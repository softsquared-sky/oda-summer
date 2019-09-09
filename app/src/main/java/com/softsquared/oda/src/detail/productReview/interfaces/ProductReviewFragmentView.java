package com.softsquared.oda.src.detail.productReview.interfaces;

import com.softsquared.oda.src.detail.productReview.ProductReviewItem;

import java.util.ArrayList;

public interface ProductReviewFragmentView {
    void validateSuccess(ArrayList<ProductReviewItem> item);

    void validateFailure(String message);
}
