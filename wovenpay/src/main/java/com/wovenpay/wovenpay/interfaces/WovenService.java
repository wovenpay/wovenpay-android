package com.wovenpay.wovenpay.interfaces;

/**
 * Created by thatmarc_ on 06-Mar-18.
 */

import com.wovenpay.wovenpay.models.AuthenticateModel;
import com.wovenpay.wovenpay.models.ListTransactionsResponse;
import com.wovenpay.wovenpay.models.PaymentChargeResponse;
import com.wovenpay.wovenpay.models.PaymentPayload;
import com.wovenpay.wovenpay.models.TokenResponse;
import com.wovenpay.wovenpay.models.TransactionStatusResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WovenService {

    @Headers("Content-Type:application/json")
    @POST("/authorize/")
    Call<TokenResponse> authorize(@Body AuthenticateModel body);

    @Headers("Content-Type:application/json")
    @POST("/token/actions/refresh/")
    Call<TokenResponse> refreshToken(@Body TokenResponse body);

    @Headers("Content-Type:application/json")
    @POST("/token/actions/verify/")
    Call<TokenResponse> verifyToken(@Body TokenResponse body);

    @Headers("Content-Type:application/json")
    @POST("/payments/actions/charge")
    Call<PaymentChargeResponse> chargePayment(@Header("XPAY") String xpayHeader, @Body PaymentPayload body);

    @Headers("Content-Type:application/json")
    @GET("/payments/")
    Call<ListTransactionsResponse> listTransactions(@Header("XPAY") String xpayHeader);

    @Headers("Content-Type:application/json")
    @GET("/payments/{transactionId}/status")
    Call<TransactionStatusResponse> status(@Header("XPAY") String xpayHeader, @Path("transactionId") String transactionId);
}
