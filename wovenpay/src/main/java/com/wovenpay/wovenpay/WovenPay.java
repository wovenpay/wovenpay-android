package com.wovenpay.wovenpay;

import com.wovenpay.wovenpay.interfaces.Network;
import com.wovenpay.wovenpay.interfaces.WovenResponse;
import com.wovenpay.wovenpay.models.AuthenticateModel;
import com.wovenpay.wovenpay.models.TokenResponse;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by thatmarc_ on 06-Mar-18.
 */

public class WovenPay {
    private final String SANDBOX_URL = "http://sandbox.wovenpay.com";
    private final String LIVE_URL = "https://api.wovenpay.com";

    private Retrofit retrofit;
    private Network network;

    private String url;
    private String token;
    private int timeout;
    private int version;

    private String apiKey;
    private String apiSecret;
    private boolean live = false;

    public WovenPay(String apiKey, String apiSecret, boolean live) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.live = live;


        this.retrofit = new Retrofit.Builder()
                .baseUrl(live ? this.LIVE_URL : this.SANDBOX_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.network = retrofit.create(Network.class);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Authenticate woven api
     *
     * @param email         Email
     * @param password      Password
     * @param wovenResponse For callback
     */
    public void getAuthorizationToken(String email, String password, WovenResponse wovenResponse) throws IOException {
        AuthenticateModel authData = new AuthenticateModel(email, password);
        Response<TokenResponse> response = network.authorize(authData).execute();
        wovenResponse.onResponse(response.toString());
    }
}
