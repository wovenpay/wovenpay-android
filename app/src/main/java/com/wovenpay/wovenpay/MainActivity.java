package com.wovenpay.wovenpay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wovenpay.wovenpay.interfaces.AuthComplete;
import com.wovenpay.wovenpay.interfaces.OnAccountListener;
import com.wovenpay.wovenpay.interfaces.OnBusinessListListener;
import com.wovenpay.wovenpay.interfaces.OnBusinessListener;
import com.wovenpay.wovenpay.interfaces.OnPaymentListener;
import com.wovenpay.wovenpay.interfaces.OnStatusListener;
import com.wovenpay.wovenpay.interfaces.OnTokenRefreshListener;
import com.wovenpay.wovenpay.interfaces.OnTokenVerifyListener;
import com.wovenpay.wovenpay.interfaces.OnTransactionsListener;
import com.wovenpay.wovenpay.models.AccountResponse;
import com.wovenpay.wovenpay.models.Business;
import com.wovenpay.wovenpay.models.Transaction;

import java.util.List;
import java.util.Locale;

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
        Button bCharge = findViewById(R.id.bCharge);
        Button bListTransactions = findViewById(R.id.bListTransactions);
        Button bStatus = findViewById(R.id.bStatus);
        Button bAccount = findViewById(R.id.bAccount);
        Button bAllBusinesses = findViewById(R.id.bAllBusinesses);
        Button bGetBusiness = findViewById(R.id.bGetBusiness);
        Button bEditBusiness = findViewById(R.id.bEditBusiness);

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

        bCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wovenPay.chargePayment(
                        10,
                        "test@test.com",
                        "mobile.mpesa",
                        "+254977777777", // replace this with your mpesa phone number :-)
                        "Order description",
                        "Reference",
                        new OnPaymentListener() {
                            @Override
                            public void onComplete(boolean success, String transactionId, String message) {
                                if (success) {
                                    tvAuth.setText(String.format("Success: %s", transactionId));
                                    return;
                                }

                                tvAuth.setText(String.format("Failed: %s\nMessage: %s", transactionId, message));
                            }
                        });
            }
        });

        bListTransactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wovenPay.transactions(new OnTransactionsListener() {
                    @Override
                    public void onComplete(boolean success, List<Transaction> transactionList, String message) {
                        if (success) {
                            tvAuth.setText(String.format(Locale.getDefault(), "Transactions %d", transactionList.size()));
                            return;
                        }

                        tvAuth.setText(String.format("Error: %s", message));
                    }
                });
            }
        });

        bStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wovenPay.status("txn_272uXW8ZfepxMW3kHyEj7f", new OnStatusListener() {
                    @Override
                    public void onComplete(boolean success, String status, String paymentId, String error) {
                        if (success) {
                            tvAuth.setText(String.format("Status: %s\nPayment id: %s", status, paymentId));
                            return;
                        }

                        tvAuth.setText(String.format("Error: %s", error));
                    }
                });
            }
        });

        bAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wovenPay.accountDetails(new OnAccountListener() {
                    @Override
                    public void onComplete(boolean success, AccountResponse accountResponse, String message) {
                        if (success) {
                            tvAuth.setText(String.format("Account: %s", new Gson().toJson(accountResponse)));
                            return;
                        }

                        tvAuth.setText(String.format("Account Details error: %s", message));
                    }
                });
            }
        });

        bAllBusinesses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wovenPay.getAllBusinesses(new OnBusinessListListener() {
                    @Override
                    public void onComplete(boolean success, List<Business> businesses, String message) {
                        if (success) {
                            tvAuth.setText(String.format("Businesses: %s", new Gson().toJson(businesses)));
                            return;
                        }

                        tvAuth.setText(String.format("Get all business error: %s", message));
                    }
                });
            }
        });

        bGetBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wovenPay.getBusiness(testBusiness, new OnBusinessListener() {
                    @Override
                    public void onComplete(boolean success, Business business, String message) {
                        if (success) {
                            tvAuth.setText(String.format("Business: %s", new Gson().toJson(business)));
                            return;
                        }

                        tvAuth.setText(String.format("Get business error: %s", message));
                    }
                });
            }
        });

        bEditBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wovenPay.editBusiness(testBusiness, "business name", "business@email.com", 23456789, "KE", new OnBusinessListener() {
                    @Override
                    public void onComplete(boolean success, Business business, String message) {
                        if (success) {
                            tvAuth.setText(String.format("Edited business: %s", new Gson().toJson(business)));
                            return;
                        }

                        tvAuth.setText(String.format("Edit business error: %s", message));
                    }
                });
            }
        });
    }
}
