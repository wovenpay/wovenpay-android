package com.wovenpay.wovenpay.interfaces;

/**
 * Created by thatmarc_ on 06-Mar-18.
 */

import com.wovenpay.wovenpay.models.AuthenticateModel;
import com.wovenpay.wovenpay.models.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WovenService {

        @Headers("Content-Type:application/json")
        @POST("/authorize/")
        Call<TokenResponse> authorize(@Body AuthenticateModel body);
}
