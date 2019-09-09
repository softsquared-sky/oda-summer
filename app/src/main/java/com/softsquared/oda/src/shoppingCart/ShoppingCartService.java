package com.softsquared.oda.src.shoppingCart;

import com.softsquared.oda.src.shoppingCart.interfaces.ShoppingCartActivityView;
import com.softsquared.oda.src.shoppingCart.interfaces.ShoppingCartRetrofitInterface;
import com.softsquared.oda.src.shoppingCart.models.ShoppingCartResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.oda.src.ApplicationClass.getRetrofit;

public class ShoppingCartService {

    private final ShoppingCartActivityView mShoppingCartActivityView;

    ShoppingCartService(final ShoppingCartActivityView shoppingCartActivityView) {
        this.mShoppingCartActivityView = shoppingCartActivityView;
    }

    public void getShoppingCartList() {
        final ShoppingCartRetrofitInterface shoppingCartRetrofitInterface = getRetrofit().create(ShoppingCartRetrofitInterface.class);
        shoppingCartRetrofitInterface.getShoppingCart().enqueue(new Callback<ShoppingCartResponse>() {
            @Override
            public void onResponse(Call<ShoppingCartResponse> call, Response<ShoppingCartResponse> response) {
                if (response == null) {
                    mShoppingCartActivityView.validateFailure(null);
                    return;
                }
                final ShoppingCartResponse shoppingCartResponse = response.body();

                if (shoppingCartResponse == null) {
                    mShoppingCartActivityView.validateFailure(null);
                    return;
                } else if (shoppingCartResponse.getCode() == 900) {
                    //중복된 ID가 없을시 반환 코드
                    mShoppingCartActivityView.vaildateSuccess(shoppingCartResponse.getShoppingCartListData());
                } else {
                    mShoppingCartActivityView.validateFailure(shoppingCartResponse.getMessage());
                }

            }

            @Override
            public void onFailure(Call<ShoppingCartResponse> call, Throwable t) {
                mShoppingCartActivityView.validateFailure(null);
            }
        });


    }
}
