package com.wovenpay.wovenpay;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wovenpay.wovenpay.interfaces.AuthComplete;
import com.wovenpay.wovenpay.interfaces.OnTokenRefreshListener;
import com.wovenpay.wovenpay.interfaces.OnTokenVerifyListener;
import com.wovenpay.wovenpay.interfaces.WovenService;
import com.wovenpay.wovenpay.models.AuthenticateModel;
import com.wovenpay.wovenpay.models.TokenResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
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
    private WovenService wovenService;

    private String url;
    private String token;
    private int timeout;
    private int version;

    private String apiKey;
    private String apiSecret;
    private boolean live = false;

    WovenPay(String apiKey, String apiSecret, boolean live) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.live = live;

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        this.retrofit = new Retrofit.Builder()
                .baseUrl(live ? this.LIVE_URL : this.SANDBOX_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        this.wovenService = retrofit.create(WovenService.class);
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
     * @param email        Email
     * @param password     Password
     * @param authComplete For callback
     */
    void getAuthorizationToken(String email, String password, final AuthComplete authComplete) {
        AuthenticateModel authData = new AuthenticateModel(email, password);
        wovenService.authorize(authData).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()) {
                    authComplete.onComplete(true, response.body().getToken(), response.message());
                } else {
                    authComplete.onComplete(false, null, response.message());
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /**
     * Refresh auth token, get a new one
     *
     * @param onTokenRefreshListener Callback interface method
     */
    void refreshAuthorizationToken(final OnTokenRefreshListener onTokenRefreshListener) {
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        wovenService.refreshToken(tokenResponse).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()) {
                    onTokenRefreshListener.onRefresh(true, response.body().getToken(), null);
                    return;
                }

                onTokenRefreshListener.onRefresh(false, null, response.message());
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /**
     * Verify token
     * @param onTokenVerifyListener
     */
    void verifyAuthorizationToken(final OnTokenVerifyListener onTokenVerifyListener) {
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        wovenService.verifyToken(tokenResponse).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()) {
                    onTokenVerifyListener.onVerify(true, response.body().getToken(), null);
                    return;
                }

                onTokenVerifyListener.onVerify(false, null, response.message());
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {

            }
        });
    }
}
