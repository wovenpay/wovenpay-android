# wovenpay-android

Note that we support android SDK 14 and above. You can checkout our [API documentation]() for more information.

Woven Payments Android SDK is an abstraction on top of Woven Payments REST api to help you intergrate Woven effortlessly to your android application

## Installation

To download the SDK, add it as a dependency to the application level `build.gradle`

```
dependencies {
  compile 'com.wovenpay.wovenpay:see.latest.version'
}
```

## Create a new Instance of wovenpay

You need to have an instance of woven to interact with the Woven API.

```java
class MainActivity extends AppCompatActivity {
 
    WovenPay wovenPay = new WovenPay(apikey, apisecret, false);

}
```

## Add Token

```java
class MainActivity extends AppCompatActivity {
 
    WovenPay wovenPay = new WovenPay(apikey, apisecret, false);
    
    void setToken(String token){
        wovenPay.setToken(token);
    }
    
    //to get the current token
    String token = wovenPay.getToken();
}
```

## Add Request Timeout

```java

class MainActivity extends AppCompatActivity {
    public void setTimeout(){
        wovenPay.setTimeout(5000);
        //get set timeout
        int timeout = wovenPay.getTimeout();
    }
}
```

## Change API version
```java
class MainActivity extends AppCompatActivity {
    public void setVersion(){
        wovenPay.setVersion(1);
        //get set version
        int version = wovenpay.getVersion();
    }
}
```

## Get Token

```java
class MainActivity extends AppCompatActivity {
    
    WovenPay wovenPay = new WovenPay(apikey, apisecret, false)
    
    public void Authenticate(){
        wovenPay.getAuthorizationToken(email, password, new AuthComplete() {
            @Override
            public void onComplete(boolean success, String token, String message) {
                if (success) {
                    wovenPay.setToken(token);
                        tvAuth.setText(String.format("Token : %s", token));
                        return;
                    }
        
                    tvAuth.setText(String.format("Auth failed error %s ", message));
                }
            });    
    }
}
```

## Refresh token
```java
class MainActivity extends AppCompatActivity {
    
    WovenPay wovenPay = new WovenPay(apikey, apisecret, false);
    
    public void refreshToken(){
        wovenPay.refreshAuthorizationToken(new OnTokenRefreshListener() {
            @Override
            public void onRefresh(boolean success, String token, String message) {
                if(success){
                    wovenPay.setToken(token);
                    tvAuth.setText(String.format("Refresh Token : %s", token));
                    return;
                }
        
                tvAuth.setText(String.format("Auth token refresh error %s ", message));
            }
        });
    }
}
```

## Verify token
```java
class MainActivity extends AppCompatActivity {
    
    WovenPay wovenPay = new WovenPay(apikey, apisecret, false);
    
    public void verifyToken(){
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
}
```

## Account
Get account details
```java
class MainActivity extends AppCompatActivity {
    
    WovenPay wovenPay = new WovenPay(apikey, apisecret, false);
    
    public void getAccountDetails(){
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
}
```

## Payments

### Make Payments Charge
```java
class MainActivity extends AppCompatActivity {
    
    WovenPay wovenPay = new WovenPay(apikey, apisecret, false);
    
    public void makePaymentCharge(){
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
}
```

### Get list of Payment transactions
```java
class MainActivity extends AppCompatActivity {
    
    WovenPay wovenPay = new WovenPay(apikey, apisecret, false);
    
    public void fetchTransactionList(){
         wovenPay.transactions(new OnTransactionsListener() {
            @Override
            public void onComplete(boolean success, List<Transation> transactionList, String message) {
                if (success) {
                    tvAuth.setText(String.format(Locale.getDefault(), "Transactions %d", transactionList.size()));
                    return;
                }
         
                tvAuth.setText(String.format("Error: %s", message));
            }
         });       
    }
}
```

### Transaction Status

Checking for transaction status

```java
class MainActivity extends AppCompatActivity {
    
    WovenPay wovenPay = new WovenPay(apikey, apisecret, false);
    
    public void transactionStatus(){
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
}    
```

### Todo
- [x] Authenticate
- [x] Set token
- [x] Refresh token
- [x] Verify token
- [x] Set timeout
- [x] Set version
- [x] Account
- [ ] Business/Apps
- [ ] Customer
- [ ] Plan
- [ ] Subscription
- [x] Payments
- [ ] Webhooks
- [ ] Publish AAR to jCenter and Maven Central