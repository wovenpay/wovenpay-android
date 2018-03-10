package com.wovenpay.wovenpayments;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import com.wovenpay.wovenpayments.interfaces.WovenService;
import com.wovenpay.wovenpayments.models.AccountResponse;
import com.wovenpay.wovenpayments.models.AuthenticateModel;
import com.wovenpay.wovenpayments.models.Business;
import com.wovenpay.wovenpayments.models.CreateCustomerPayload;
import com.wovenpay.wovenpayments.models.Customer;
import com.wovenpay.wovenpayments.models.EditBusinessPayload;
import com.wovenpay.wovenpayments.models.GetCustomersResponse;
import com.wovenpay.wovenpayments.models.ListTransactionsResponse;
import com.wovenpay.wovenpayments.models.Order;
import com.wovenpay.wovenpayments.models.PaymentChargeResponse;
import com.wovenpay.wovenpayments.models.PaymentPayload;
import com.wovenpay.wovenpayments.models.TokenResponse;
import com.wovenpay.wovenpayments.models.TransactionStatusResponse;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
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

    public WovenPay(String apiKey, String apiSecret, boolean live) {
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
    public void getAuthorizationToken(String email, String password, final AuthComplete authComplete) {
        AuthenticateModel authData = new AuthenticateModel(email, password);
        wovenService.authorize(authData).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()) {
                    authComplete.onComplete(true, response.body().getToken(),
                            response.message());
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
    public void refreshAuthorizationToken(final OnTokenRefreshListener onTokenRefreshListener) {
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        wovenService.refreshToken(tokenResponse).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()) {
                    onTokenRefreshListener.onRefresh(true, response.body().getToken(),
                            null);
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
     *
     * @param onTokenVerifyListener
     */
    public void verifyAuthorizationToken(final OnTokenVerifyListener onTokenVerifyListener) {
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        wovenService.verifyToken(tokenResponse).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()) {
                    onTokenVerifyListener.onVerify(true, response.body().getToken(),
                            null);
                    return;
                }

                onTokenVerifyListener.onVerify(false, null, response.message());
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {

            }
        });
    }

    /**
     * Charge payment aganist a user
     *
     * @param amount            Amount to charge
     * @param customerEmail     Customer email
     * @param method            Method of payment, Only accepts 'mobile.mpesa'
     * @param mobile            Mobile number to charge. If you have used 'mobile.mpesa' as method you have to
     *                          provide a valid mpesa number
     * @param orderDescription  Order description
     * @param reference         Reference
     * @param onPaymentListener Callback method for when a charge completes
     */
    public void chargePayment(double amount, String customerEmail, String method, String mobile,
                              String orderDescription, String reference,
                              final OnPaymentListener onPaymentListener) {
        PaymentPayload paymentPayload = new PaymentPayload();
        paymentPayload.setAmount(amount);
        Customer customer = new Customer();
        customer.setEmail(customerEmail);
        paymentPayload.setCustomer(customer);
        paymentPayload.setMethod(method);
        paymentPayload.setMobile(mobile);
        paymentPayload.setReference(reference);
        Order order = new Order();
        order.setDescription(orderDescription);
        paymentPayload.setOrder(order);

        wovenService.chargePayment(getXpayHeader(), paymentPayload).enqueue(new Callback<PaymentChargeResponse>() {
            @Override
            public void onResponse(Call<PaymentChargeResponse> call, Response<PaymentChargeResponse> response) {
                if (response.isSuccessful() && response.body().getStatus().equals("pending")) {
                    onPaymentListener.onComplete(true, response.body().getTransactionId(), null);
                    return;
                }

                if (response.isSuccessful() && response.body().getStatus().equals("failed")) {
                    onPaymentListener.onComplete(false, response.body().getTransactionId(), response.body().getMetadata().toString());
                }
            }

            @Override
            public void onFailure(Call<PaymentChargeResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    /**
     * List all transactions made to a business/app
     *
     * @param onTransactionsListener Callback for when the async job is done
     */
    public void transactions(final OnTransactionsListener onTransactionsListener) {
        wovenService.listTransactions(getXpayHeader()).enqueue(new Callback<ListTransactionsResponse>() {
            @Override
            public void onResponse(Call<ListTransactionsResponse> call, Response<ListTransactionsResponse> response) {
                if (response.isSuccessful()) {
                    onTransactionsListener.onComplete(true, response.body().getTransactions(), null);
                    return;
                }

                onTransactionsListener.onComplete(false, null, response.message());
            }

            @Override
            public void onFailure(Call<ListTransactionsResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /**
     * Check status of a transaction
     *
     * @param transactionId    Transaction id of the transaction you want to check status for
     * @param onStatusListener Callback for when transaction status check is done
     */
    public void status(String transactionId, final OnStatusListener onStatusListener) {
        wovenService.status(getXpayHeader(), transactionId).enqueue(
                new Callback<TransactionStatusResponse>() {
                    @Override
                    public void onResponse(Call<TransactionStatusResponse> call, Response<TransactionStatusResponse> response) {
                        if (response.isSuccessful() && response.code() == 200) {
                            onStatusListener.onComplete(true, response.body().getStatus(), response.body().getPaymentId(), null);
                            return;
                        }

                        onStatusListener.onComplete(false, null, null, response.body().getErrors().getMessage());
                    }

                    @Override
                    public void onFailure(Call<TransactionStatusResponse> call, Throwable t) {
                        t.printStackTrace();
                        onStatusListener.onComplete(false, null, null, t.getLocalizedMessage());
                    }
                }
        );
    }

    /**
     * Get account details
     *
     * @param onAccountListener Callback
     */
    public void accountDetails(final OnAccountListener onAccountListener) {
        wovenService.accountDetails(getAuthToken()).enqueue(
                new Callback<AccountResponse>() {
                    @Override
                    public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                        if (response.isSuccessful() && response.code() == 200) {
                            onAccountListener.onComplete(true, response.body(), null);
                            return;
                        }

                        if (response.code() == 401) {
                            onAccountListener.onComplete(false, null, response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<AccountResponse> call, Throwable t) {
                        t.printStackTrace();
                        onAccountListener.onComplete(false, null, t.getMessage());
                    }
                }
        );
    }

    /**
     * Get all businesses
     *
     * @param onBusinessListListener Callback
     */
    public void getAllBusinesses(final OnBusinessListListener onBusinessListListener) {
        wovenService.allBusinesses(getAuthToken()).enqueue(new Callback<List<Business>>() {
            @Override
            public void onResponse(Call<List<Business>> call, Response<List<Business>> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    onBusinessListListener.onComplete(true, response.body(), null);
                    return;
                }

                onBusinessListListener.onComplete(false, null, response.message());
            }

            @Override
            public void onFailure(Call<List<Business>> call, Throwable t) {
                t.printStackTrace();
                onBusinessListListener.onComplete(false, null, t.getLocalizedMessage());
            }
        });
    }

    public void getBusiness(String businessId, final OnBusinessListener onBusinessListener) {
        wovenService.getBusiness(getAuthToken(), businessId).enqueue(new Callback<Business>() {
            @Override
            public void onResponse(Call<Business> call, Response<Business> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    onBusinessListener.onComplete(true, response.body(), null);
                    return;
                }

                onBusinessListener.onComplete(false, null, response.message());
            }

            @Override
            public void onFailure(Call<Business> call, Throwable t) {
                t.printStackTrace();
                onBusinessListener.onComplete(false, null, t.getLocalizedMessage());
            }
        });
    }


    public void editBusiness(String businessId, String name, String email, int phoneNumber, String country, final OnBusinessListener onBusinessListener) {
        EditBusinessPayload business = new EditBusinessPayload();
        business.setName(name);
        business.setEmail(email);
        business.setPhoneNumber(phoneNumber);
        business.setCountry(country);

        wovenService.editBusiness(getAuthToken(), businessId, business).enqueue(new Callback<Business>() {
            @Override
            public void onResponse(Call<Business> call, Response<Business> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    onBusinessListener.onComplete(true, response.body(), null);
                    return;
                }

                onBusinessListener.onComplete(false, null, response.message());
            }

            @Override
            public void onFailure(Call<Business> call, Throwable t) {
                t.printStackTrace();
                onBusinessListener.onComplete(false, null, t.getLocalizedMessage());
            }
        });

    }

    public void createCustomer(Customer customer, final OnCustomerListener onCustomerListener) {
        wovenService.createCustomer(getAuthToken(), customer).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (response.isSuccessful() && response.code() == 201) {
                    onCustomerListener.onComplete(true, response.body(), null);
                    return;
                }

                if (response.code() == 400) {
                    onCustomerListener.onComplete(false, null, "Customer with given details already exists.");
                    return;
                }

                onCustomerListener.onComplete(false, null, response.message());
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                t.printStackTrace();
                onCustomerListener.onComplete(false, null, t.getLocalizedMessage());
            }
        });
    }

    public void editCustomer(String customerId, Customer customer, final OnCustomerListener onCustomerListener) {
        wovenService.editCustomer(getAuthToken(), customerId, customer).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    onCustomerListener.onComplete(true, response.body(), null);
                    return;
                }

                onCustomerListener.onComplete(false, null, response.message());
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                t.printStackTrace();
                onCustomerListener.onComplete(false, null, t.getLocalizedMessage());
            }
        });
    }

    public void deleteCustomer(String customerId, final OnDeleteCustomerListener onDeleteCustomerListener) {
        wovenService.deleteCustomer(getAuthToken(), customerId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.code() == 204) {
                    onDeleteCustomerListener.onComplete(true, "Customer deleted");
                    return;
                }

                if (response.code() == 404) {
                    onDeleteCustomerListener.onComplete(false, "Customer not found");
                    return;
                }

                onDeleteCustomerListener.onComplete(false, response.message());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                onDeleteCustomerListener.onComplete(false, t.getLocalizedMessage());
            }
        });
    }

    public void getCustomer(String customerId, final OnCustomerListener onCustomerListener) {
        wovenService.getCustomer(getAuthToken(), customerId).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    onCustomerListener.onComplete(true, response.body(), null);
                    return;
                }

                if (response.code() == 404) {
                    onCustomerListener.onComplete(false, null, "No Customer matches the given query.");
                    return;
                }

                onCustomerListener.onComplete(false, null, response.message());
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                t.printStackTrace();
                onCustomerListener.onComplete(false, null, t.getLocalizedMessage());
            }
        });
    }

    public void getCustomers(final OnCustomersListener onCustomersListener) {
        wovenService.getCustomers(getAuthToken()).enqueue(new Callback<GetCustomersResponse>() {
            @Override
            public void onResponse(Call<GetCustomersResponse> call, Response<GetCustomersResponse> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    onCustomersListener.onComplete(true, response.body().getResults(), null);
                    return;
                }

                onCustomersListener.onComplete(false, null, response.message());
            }

            @Override
            public void onFailure(Call<GetCustomersResponse> call, Throwable t) {
                t.printStackTrace();
                onCustomersListener.onComplete(false, null, t.getLocalizedMessage());
            }
        });
    }

    private String getXpayHeader() {
        return String.format("%s:%s", this.apiKey, this.apiSecret);
    }

    private String getAuthToken() {
        return String.format("Token %s", this.token);
    }
}
