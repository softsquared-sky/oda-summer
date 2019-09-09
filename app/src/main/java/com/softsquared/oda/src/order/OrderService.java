package com.softsquared.oda.src.order;

import com.softsquared.oda.src.order.interfaces.OrderActivityView;
import com.softsquared.oda.src.order.interfaces.OrderRetrofitInterface;
import com.softsquared.oda.src.order.models.OrderResponse;

import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.oda.src.ApplicationClass.MEDIA_TYPE_JSON;
import static com.softsquared.oda.src.ApplicationClass.getRetrofit;

public class OrderService {

    private final OrderActivityView mOrderActivityView;

    OrderService(final OrderActivityView orderActivityView) {
        this.mOrderActivityView = orderActivityView;
    }

    public void postPayment(JSONObject params) {
        final OrderRetrofitInterface orderRetrofitInterface = getRetrofit().create(OrderRetrofitInterface.class);
        orderRetrofitInterface.postPayment(RequestBody.create(params.toString(), MEDIA_TYPE_JSON)).enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if (response == null) {
                    mOrderActivityView.validateFailure(null);
                    return;
                }
                final OrderResponse orderResponse = response.body();

                if (orderResponse == null) {
                    mOrderActivityView.validateFailure(null);
                    return;
                } else if (orderResponse.getCode() == 1000) {
                    //중복된 ID가 없을시 반환 코드
                    mOrderActivityView.vaildateSuccess(orderResponse.getMessage());
                } else if (orderResponse.getCode() == 1050) {
                    mOrderActivityView.validateFailure(orderResponse.getMessage());

                } else {
                    mOrderActivityView.validateFailure(orderResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                mOrderActivityView.validateFailure("서버연결실패");
            }
        });
    }
}
