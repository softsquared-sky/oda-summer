package com.softsquared.oda.src.detail.productDetail;

import android.content.res.Resources;

import com.softsquared.oda.src.detail.productDetail.interfaces.ProductDetailFragmentView;
import com.softsquared.oda.src.detail.productDetail.interfaces.ProductDetailRetrofitInterface;
import com.softsquared.oda.src.detail.productDetail.models.ProductDetailResponse;
import com.softsquared.odaproject.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.oda.src.ApplicationClass.getRetrofit;

public class ProductDetailService {

    private final ProductDetailFragmentView mProductDetailFragmentView;

    ProductDetailService(final ProductDetailFragmentView productDetailFragmentView) {
        this.mProductDetailFragmentView = productDetailFragmentView;
    }

    void getProductDetailInfo(int productId) {
        final ProductDetailRetrofitInterface productDetailRetrofitInterface = getRetrofit().create(ProductDetailRetrofitInterface.class);
        productDetailRetrofitInterface.getProductDetail(productId).enqueue(new Callback<ProductDetailResponse>() {
            @Override
            public void onResponse(Call<ProductDetailResponse> call, Response<ProductDetailResponse> response) {
                if (response == null) {
                    mProductDetailFragmentView.validateFailure("제품의 상세정보가 없습니다.");
                    return;
                }
                final ProductDetailResponse productDetailResponse = response.body();

                if (productDetailResponse == null) {
                    mProductDetailFragmentView.validateFailure(null);

                } else if (productDetailResponse.getCode() == 500) {
                    //중복된 ID가 없을시 반환 코드
                    mProductDetailFragmentView.validateSuccess(productDetailResponse.getMessage(),productDetailResponse.getResult());
                } else if(productDetailResponse.getCode() == 550){
                    mProductDetailFragmentView.validateFailure(Resources.getSystem().getString(R.string.db_error));
                }
            }

            @Override
            public void onFailure(Call<ProductDetailResponse> call, Throwable t) {
                mProductDetailFragmentView.validateFailure(null);
            }
        });
    }
}
