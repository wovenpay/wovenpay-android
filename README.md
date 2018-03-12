# wovenpay-android

Woven Payments Android SDK is an abstraction on top of Woven Payments REST api to help you intergrate Woven effortlessly to your android application. Process paymentsin your android application.

If you intend to implement payments only, such as in an e-commerce application you only need to use the [payments](#payments) resource.   

## Table of Contents
- [wovenpay-android](#wovenpay-android)
- [Table of Contents](#table-of-contents)
- [Installation](#installation)
- [Create a new Instance of wovenpay](#create-a-new-instance-of-wovenpay)
- [Get token](#get-token)
- [Add token](#add-token)
- [Add request timeout](#add-request-timeout)
- [Change API version](#change-api-version)
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
- [Customer](#customer)
    - [Create a new customer](#create-a-new-customer)
    - [Edit a customer](#edit-a-customer)
    - [Delete a customer](#delete-a-customer)
    - [Retrieve all customers](#retrieve-all-customers)
    - [Retrieve Specific customer](#retrieve-specific-customer)
- [Todo list](#todo)

## Installation

1. __Gradle__ 
To download the SDK, add it as a dependency to the application level `build.gradle`


```
dependencies {
  compile 'com.wovenpay.wovenpay:see.latest.version'
}
```

2. __Maven__
```xml
<dependency>
  <groupId>com.wovenpay.wovenpayments</groupId>
  <artifactId>wovenpayments</artifactId>
  <version>see.latest.version</version>
  <type>pom</type>
</dependency>
```

3. __Ivy__
```xml
<dependency org='com.wovenpay.wovenpayments' name='wovenpayments' rev='see.latest.version'>
  <artifact name='wovenpayments' ext='pom' ></artifact>
</dependency>
```
<br/>

__latest version__ :  [![Download](https://api.bintray.com/packages/wovenpay/wovenpay-android/wovenpay-android/images/download.svg)](https://bintray.com/wovenpay/wovenpay-android/wovenpay-android/_latestVersion)

## Create a new Instance of wovenpay

You need to have an instance of woven to interact with the Woven API.

```java
class MainActivity extends AppCompatActivity {
 
    WovenPay wovenPay = new WovenPay(apikey, apisecret, false);

}
```

## Get Token

To obtain an Authorization token. An authorization will be needed when making most of the requests. The obtained token becomes invalid after a while. Another token needs to be obtained when the previous one gets invalidated.

```java
class MainActivity extends AppCompatActivity {
    
    WovenPay wovenPay = new WovenPay(apikey, apisecret, false);
    
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

## Add Token

After you have obtained a token, set it to your instance of woven. 

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
The woven SDK uses a timeout of 30 seconds for Retrofit's readTimeout & connectTimeout. To set your custom timeout number for both read and connect timeout all you need is to set the timeout.

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

## Refresh token
Authorization tokens expire after a while. If you have the previous auth token, it can be exchanged for a valid one.
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
Get details of the currently authorized account
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

Businesses/Apps are client applications that you intend to be accepted as custom client applications.

### Get all businesses

Use this resource to get a list of all the businesses that are allowed for your account 

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

This resource can be used to get details about a specific business

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

If you would like to implement checkout only in your app, you are more suited to use only this resource.

### Make Payments Charge

When you want to make a charge aganist an customer's client amount

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

### Customer

Customers are entities who pay for something you intend to sell on your app. Every transaction is linked to a customer, and a customer can have multiple payment transactions

#### Create a new customer
```java
class MainActivity extends AppCompatActivity {
   WovenPay wovenPay = new WovenPay(apikey, apisecret, false);
   
   public void createCustomer(){
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
        }});
   }
}
```

#### Edit a customer

```java
class MainActivity extends AppCompatActivity {
   WovenPay wovenPay = new WovenPay(apikey, apisecret, false);
   
   public void createCustomer(){
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
}
```

#### Delete a customer

```java
class MainActivity extends AppCompatActivity {
   WovenPay wovenPay = new WovenPay(apikey, apisecret, false);
   
   public void createCustomer(){
        wovenPay.deleteCustomer(testCustomer, new OnDeleteCustomerListener() {
            @Override
            public void onComplete(boolean success, String message) {
                if (success) {
                    tvAuth.setText("Deleted customer ");
                    return;
                }
        
            tvAuth.setText(String.format("delete customer error: %s", message));
        }});
   }
}
```

#### Retrieve all customers

```java
class MainActivity extends AppCompatActivity {
   WovenPay wovenPay = new WovenPay(apikey, apisecret, false);
   
   public void createCustomer(){
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
}
```

#### Retrieve Specific customer

```java
class MainActivity extends AppCompatActivity {
   WovenPay wovenPay = new WovenPay(apikey, apisecret, false);
   
   public void createCustomer(){
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
}
```

### Plan

If your app has sale packages, this is the resource you use to create, edit and remove plans. 

#### Create a new plan

```java
class MainActivity extends AppCompatActivity {
   WovenPay wovenPay = new WovenPay(apikey, apisecret, false);
   
   public void functionName(){
        Plan plan = new Plan();
        plan.setPrice(1200.00);
        plan.setName("Namejgasddkhfj");
        plan.setBusiness(testBusiness);
        wovenPay.createPlan(plan, new OnPlanListener() {
            @Override
            public void onComplete(boolean success, Plan plan, String message) {
                if (success) {
                    planId = plan.getId();
                    Toast.makeText(getApplicationContext(), plan.getId(), Toast.LENGTH_SHORT).show();
                    tvAuth.setText(String.format("Plan created %s", new Gson().toJson(plan)));
                    return;
                }
       
                tvAuth.setText(String.format("Plan creation error %s", message));
             }
        });
   }
}
```

#### Retrieve all plans

```java
class MainActivity extends AppCompatActivity {
   WovenPay wovenPay = new WovenPay(apikey, apisecret, false);
   
   public void functionName(){
        Plan plan = new Plan();
        plan.setPrice(1200.00);
        plan.setName("Namejgasddkhfj");
        plan.setBusiness(testBusiness);
        wovenPay.createPlan(plan, new OnPlanListener() {
            @Override
            public void onComplete(boolean success, Plan plan, String message) {
                if (success) {
                    planId = plan.getId();
                    Toast.makeText(getApplicationContext(), plan.getId(), Toast.LENGTH_SHORT).show();
                    tvAuth.setText(String.format("Plan created %s", new Gson().toJson(plan)));
                    return;
                }
       
                tvAuth.setText(String.format("Plan creation error %s", message));
             }
        });
   }
}
```

#### Retrieve Specific plan

```java
class MainActivity extends AppCompatActivity {
   WovenPay wovenPay = new WovenPay(apikey, apisecret, false);
   
   public void functionName(){
        wovenPay.getPlan(planId, new OnPlanListener() {
            @Override
            public void onComplete(boolean success, Plan plan, String message) {
                if (success) {
                    tvAuth.setText(String.format("Plan %s", new Gson().toJson(plan)));
                    return;
                }
        
                tvAuth.setText(String.format("Get plan error %s", message));
            }
        });
   }
}
```

#### Edit a plan

```java
class MainActivity extends AppCompatActivity {
   WovenPay wovenPay = new WovenPay(apikey, apisecret, false);
   
   public void functionName(){
        Plan plan = new Plan();
        plan.setName("Name name");
        plan.setPrice(1_300.0);
        wovenPay.editPlan(planId, plan, new OnPlanListener() {
            @Override
            public void onComplete(boolean success, Plan plan, String message) {
                if (success) {
                    tvAuth.setText(String.format("Edited plan %s", new Gson().toJson(plan)));
                    return;
                }
        
            tvAuth.setText(String.format("Edited plan error %s", message));
        }
        });
   }
}
```

#### Delete a plan
```java
class MainActivity extends AppCompatActivity {
   WovenPay wovenPay = new WovenPay(apikey, apisecret, false);
   
   public void functionName(){
        wovenPay.deletePlan(planId, new OnDeleteListener() {
            @Override
            public void onComplete(boolean success, String message) {
                if (success) {
                    tvAuth.setText("Deleted plan");
                    return;
                }
        
                tvAuth.setText(String.format("delete customer error: %s", message));
            }
        });
   }
}
```
### Supported versions

We support android SDK 14 and above. You can checkout our [API documentation](https://developer.wovenpay.com/) for more information.

### Todo
- [x] Authenticate
- [x] Set token
- [x] Refresh token
- [x] Verify token
- [x] Set timeout
- [x] Set version
- [x] Account
- [x] Business/Apps
- [x] Customer
- [x] Plan
- [ ] Subscription
- [x] Payments
- [ ] Webhooks
- [x] Publish AAR to jCenter and Maven Central
