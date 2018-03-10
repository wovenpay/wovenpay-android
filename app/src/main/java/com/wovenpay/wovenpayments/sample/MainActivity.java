package com.wovenpay.wovenpayments.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wovenpay.wovenpayments.WovenPay;
import com.wovenpay.wovenpayments.interfaces.AuthComplete;
import com.wovenpay.wovenpayments.interfaces.OnAccountListener;
import com.wovenpay.wovenpayments.interfaces.OnBusinessListListener;
import com.wovenpay.wovenpayments.interfaces.OnBusinessListener;
import com.wovenpay.wovenpayments.interfaces.OnCustomerListener;
import com.wovenpay.wovenpayments.interfaces.OnCustomersListener;
import com.wovenpay.wovenpayments.interfaces.OnDeleteCustomerListener;
import com.wovenpay.wovenpayments.interfaces.OnPaymentListener;
import com.wovenpay.wovenpayments.interfaces.OnStatusListener;
import com.wovenpay.wovenpayments.interfaces.OnTokenRefreshListener;
import com.wovenpay.wovenpayments.interfaces.OnTokenVerifyListener;
import com.wovenpay.wovenpayments.interfaces.OnTransactionsListener;
import com.wovenpay.wovenpayments.models.AccountResponse;
import com.wovenpay.wovenpayments.models.Business;
import com.wovenpay.wovenpayments.models.Customer;
import com.wovenpay.wovenpayments.models.Transaction;

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
    final String testPlan = "plan_jpcms2jwkvHbinJHvHygkZ";

    String testCustomer = "cus_JBkyB88VbjNhux2PQTivmH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tvAuth = findViewById(R.id.tvToken);
        Button bAuth = findViewById(R.id.bAuth);
        final Button bRefresh = findViewById(R.id.bRefresh);
        Button bVerify = findViewById(R.id.bVerify);
        Button bCharge = findViewById(R.id.bCharge);
        Button bListTransactions = findViewById(R.id.bListTransactions);
        Button bStatus = findViewById(R.id.bStatus);
        Button bAccount = findViewById(R.id.bAccount);
        Button bAllBusinesses = findViewById(R.id.bAllBusinesses);
        Button bGetBusiness = findViewById(R.id.bGetBusiness);
        Button bEditBusiness = findViewById(R.id.bEditBusiness);
        Button bCreateCustomer = findViewById(R.id.bCreateCustomer);
        Button bEditCustomer = findViewById(R.id.bEditCustomer);
        Button bGetCustomer = findViewById(R.id.bGetCustomer);
        Button bGetCustomers = findViewById(R.id.bGetCustomers);
        Button bDeleteCustomer = findViewById(R.id.bDeleteCustomer);

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

        bCreateCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer c = new Customer();
                c.setEmail("hhhhhsdah@gmail.com");
                wovenPay.createCustomer(c, new OnCustomerListener() {
                    @Override
                    public void onComplete(boolean success, Customer customerResponse, String message) {
                        if (success) {
                            testCustomer = customerResponse.getId();
                            tvAuth.setText(String.format("Created customer: %s", new Gson().toJson(customerResponse)));
                            return;
                        }

                        tvAuth.setText(String.format("Created customer error: %s", message));
                    }
                });
            }
        });

        bEditCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer customer = new Customer();
                customer.setEmail("ecustomessr@gmail.com");
                wovenPay.editCustomer(testCustomer, customer, new OnCustomerListener() {
                    @Override
                    public void onComplete(boolean success, Customer customerResponse, String message) {
                        if (success) {
                            tvAuth.setText(String.format("Edited customer: %s", new Gson().toJson(customerResponse)));
                            return;
                        }

                        tvAuth.setText(String.format("Error editing customer: %s", message));
                    }
                });
            }
        });


        bGetCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wovenPay.getCustomer(testCustomer, new OnCustomerListener() {
                    @Override
                    public void onComplete(boolean success, Customer customerResponse, String message) {
                        if (success) {
                            tvAuth.setText(String.format("Got customer : %s", new Gson().toJson(customerResponse)));
                            return;
                        }

                        tvAuth.setText(String.format("Get customer error: %s", message));
                    }
                });
            }
        });

        bGetCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wovenPay.getCustomers(new OnCustomersListener() {
                    @Override
                    public void onComplete(boolean success, List<Customer> customerResponseList, String message) {
                        if (success) {
                            tvAuth.setText(String.format("Got customers : %s", new Gson().toJson(customerResponseList)));
                            return;
                        }

                        tvAuth.setText(String.format("Get customers error: %s", message));
                    }
                });
            }
        });

        bDeleteCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wovenPay.deleteCustomer(testCustomer, new OnDeleteCustomerListener() {
                    @Override
                    public void onComplete(boolean success, String message) {
                        if (success) {
                            tvAuth.setText("Deleted customer ");
                            return;
                        }

                        tvAuth.setText(String.format("delete customer error: %s", message));
                    }
                });
            }
        });
    }
}
