package com.softsquared.oda.src.detail.productReview;

import android.content.res.Resources;
import android.util.Log;

import com.softsquared.oda.src.detail.productReview.interfaces.ProductReviewFragmentView;
import com.softsquared.oda.src.detail.productReview.interfaces.ProductReviewRetrofitInterface;
import com.softsquared.oda.src.detail.productReview.models.ProductReviewResponse;
import com.softsquared.odaproject.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.oda.src.ApplicationClass.getRetrofit;

public class ProductReviewService {

    private final ProductReviewFragmentView mProductReviewFragmentView;

    ProductReviewService(final ProductReviewFragmentView productReviewFragmentView) {
        this.mProductReviewFragmentView = productReviewFragmentView;
    }

    void getProductReview(int productId) {
        final ProductReviewRetrofitInterface productReviewRetrofitInterface = getRetrofit().create(ProductReviewRetrofitInterface.class);
        productReviewRetrofitInterface.getProductReview(productId).enqueue(new Callback<ProductReviewResponse>() {
            @Override
            public void onResponse(Call<ProductReviewResponse> call, Response<ProductReviewResponse> response) {
                if (response == null) {
                    mProductReviewFragmentView.validateFailure("제품의 상세정보가 없습니다.");
                    return;
                }
                final ProductReviewResponse productReviewResponse = response.body();

                if (productReviewResponse == null) {
                    mProductReviewFragmentView.validateFailure(null);

                } else if (productReviewResponse.getCode() == 600) {
                    //리뷰가 있을때
                    mProductReviewFragmentView.validateSuccess(productReviewResponse.getResults());
                } else if(productReviewResponse.getCode() == 610){
                    mProductReviewFragmentView.validateFailure("상품 후기가 없습니다.");
                }
                else{
                    mProductReviewFragmentView.validateFailure(null);
                }
            }

            @Override
            public void onFailure(Call<ProductReviewResponse> call, Throwable t) {
                mProductReviewFragmentView.validateFailure(null);
            }
        });
    }
}
