package com.softsquared.oda.src.detail.productDetail.interfaces;

import com.softsquared.oda.src.detail.productDetail.models.ProductDetailResponse;

public interface ProductDetailFragmentView {

    void validateSuccess(String text, ProductDetailResponse.DetailResult detailResult);

    void validateFailure(String message);

}
