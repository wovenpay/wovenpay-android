package com.wovenpay.wovenpayments.interfaces;

/**
 * Created by thatmarc_ on 06-Mar-18.
 */

import com.wovenpay.wovenpayments.models.AccountResponse;
import com.wovenpay.wovenpayments.models.AuthenticateModel;
import com.wovenpay.wovenpayments.models.CreateCustomerPayload;
import com.wovenpay.wovenpayments.models.Customer;
import com.wovenpay.wovenpayments.models.EditBusinessPayload;
import com.wovenpay.wovenpayments.models.Business;
import com.wovenpay.wovenpayments.models.GetCustomersResponse;
import com.wovenpay.wovenpayments.models.ListTransactionsResponse;
import com.wovenpay.wovenpayments.models.PaymentChargeResponse;
import com.wovenpay.wovenpayments.models.PaymentPayload;
import com.wovenpay.wovenpayments.models.TokenResponse;
import com.wovenpay.wovenpayments.models.TransactionStatusResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @GET("/me/")
    Call<AccountResponse> accountDetails(@Header("Authorization") String token);

    @GET("/business/")
    Call<List<Business>> allBusinesses(@Header("Authorization") String token);

    @GET("/business/{businessId}/")
    Call<Business> getBusiness(@Header("Authorization") String token, @Path("businessId") String businessId);

    @Headers("Content-Type:application/json")
    @PUT("/business/{businessId}/")
    Call<Business> editBusiness(@Header("Authorization") String token, @Path("businessId") String businessId, @Body EditBusinessPayload payload);

    @Headers("Content-Type:application/json")
    @POST("/customers/")
    Call<Customer> createCustomer(@Header("Authorization") String token, @Body Customer customerPayload);

    @Headers("Content-Type:application/json")
    @PUT("/customers/{customerId}/")
    Call<Customer> editCustomer(@Header("Authorization") String token, @Path("customerId") String customerId, @Body Customer customer);

    @DELETE("/customers/{customerId}/")
    Call<ResponseBody> deleteCustomer(@Header("Authorization") String token, @Path("customerId") String customerId);

    @GET("/customers/{customerId}/")
    Call<Customer> getCustomer(@Header("Authorization") String token, @Path("customerId") String customerId);

    @GET("/customers/")
    Call<GetCustomersResponse> getCustomers(@Header("Authorization") String token);
}
