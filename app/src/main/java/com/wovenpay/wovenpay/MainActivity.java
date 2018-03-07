package com.wovenpay.wovenpay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wovenpay.wovenpay.interfaces.AuthComplete;
import com.wovenpay.wovenpay.interfaces.OnTokenRefreshListener;
import com.wovenpay.wovenpay.interfaces.OnTokenVerifyListener;

public class MainActivity extends AppCompatActivity {

    final String apikey = "ak_7zeqY5qGVhPLTMKKyb3vwF";
    final String apisecret = "sk_q6segivqhA3xxNPn2KkWbP";
    final String email = "test@test.com";
    final String password = "test12345";
    final String wrongPassword = "test123451";
    final String testToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiYWNjX2JrVVJrNGI2MnE2WGJXaE5DZWphUjMiLCJ1c2VybmFtZSI6InRlc3RAdGVzdC5jb20iLCJleHAiOjE1MTk3MDE2ODgsImVtYWlsIjoidGVzdEB0ZXN0LmNvbSIsIm9yaWdfaWF0IjoxNTE5NjU4NDg4fQ.p7V85SFoZ3WPF08c5MdkVPgQllB6DGZ6pS8w_OSmTdU";

    final String testBusiness = "bus_B6cDFj4uTz4AuFAUFzPFgm";
    final String testCustomer = "cus_BBmafrJiRgTJaVh5aenwLm";
    final String testPlan = "plan_jpcms2jwkvHbinJHvHygkZ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvAuth = findViewById(R.id.tvToken);
        Button bAuth = findViewById(R.id.bAuth);
        Button bRefresh = findViewById(R.id.bRefresh);
        Button bVerify = findViewById(R.id.bVerify);

        final WovenPay wovenPay = new WovenPay(apikey, apisecret, false);
        wovenPay.setVersion(1);
        wovenPay.getVersion();
        wovenPay.setTimeout(5000);
        wovenPay.getTimeout();

        bAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wovenPay.getAuthorizationToken(email, password, new AuthComplete() {
                    @Override
                    public void onComplete(boolean success, String token, String message) {
                        if (success) {
                            wovenPay.setToken(token);
                            // get set token
                            wovenPay.getToken();
                            tvAuth.setText(String.format("Token : %s", token));
                            return;
                        }

                        tvAuth.setText(String.format("Auth failed error %s ", message));
                    }
                });
            }
        });

        bRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wovenPay.refreshAuthorizationToken(new OnTokenRefreshListener() {
                    @Override
                    public void onRefresh(boolean success, String token, String message) {
                        if (success) {
                            wovenPay.setToken(token);
                            tvAuth.setText(String.format("Refresh Token : %s", token));
                            return;
                        }

                        tvAuth.setText(String.format("Auth token refresh error %s ", message));
                    }
                });
            }
        });

        bVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wovenPay.verifyAuthorizationToken(new OnTokenVerifyListener() {
                    @Override
                    public void onVerify(boolean success, String token, String message) {
                        if (success) {
                            wovenPay.setToken(token);
                            tvAuth.setText(String.format("Verified Token : %s", token));
                            return;
                        }

                        tvAuth.setText(String.format("Auth token verify error %s ", message));
                    }
                });
            }
        });
    }
}
