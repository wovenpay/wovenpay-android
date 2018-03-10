# wovenpay-android

Note that we support android SDK 14 and above. You can checkout our [API documentation]() for more information.

Woven Payments Android SDK is an abstraction on top of Woven Payments REST api to help you intergrate Woven effortlessly to your android application

## Table of Contents
- [wovenpay-android](#wovenpay-android)
- [Table of Contents](#table-of-contents)
- [Installation](#installation)
- [Create a new Instance of wovenpay](#create-a-new-instance-of-wovenpay)
- [Add token](#add-token)
- [Add request timeout](#add-request-timeout)
- [Change API version](#change-api-version)
- [Get token](#get-token)
- [Refresh token](#refresh-token)
- [Verify token](#verify-token)
- [Account](#account)
- [Business/Apps](#businessesapps)
    - [Get all businesses](#get-all-businesses)
    - [Get a specific business](#get-specific-business)
    - [Edit a business](#edit-a-business)
- [Payments](#payments)
    - [Make payments charge](#make-payments-charge)
    - [Get list of Payment transactions](#get-list-of-payment-transactions)
    - [Transaction status](#transaction-status)
- [Todo list](#todo)

## Installation

To download the SDK, add it as a dependency to the application level `build.gradle`


```
dependencies {
  compile 'com.wovenpay.wovenpay:see.latest.version'
}
```
__latest version__ :  [![Download](https://api.bintray.com/packages/wovenpay/wovenpay-android/wovenpay-android/images/download.svg)](https://bintray.com/wovenpay/wovenpay-android/wovenpay-android/_latestVersion)

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

## Businesses/Apps

### Get all businesses
```java
class MainActivity extends AppCompatActivity {
    
    WovenPay wovenPay = new WovenPay(apikey, apisecret, false);
    
    public void getAllBusinesses(){
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
}
```

### Get specific business
```java
class MainActivity extends AppCompatActivity {
    
    WovenPay wovenPay = new WovenPay(apikey, apisecret, false);
    
    public void getBusiness(){
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
}
```

### Edit a business
```java
class MainActivity extends AppCompatActivity {
    
    WovenPay wovenPay = new WovenPay(apikey, apisecret, false);
    
    public void editBusiness(){
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
- [x] Business/Apps
- [ ] Customer
- [ ] Plan
- [ ] Subscription
- [x] Payments
- [ ] Webhooks
- [x] Publish AAR to jCenter and Maven Central