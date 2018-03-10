package com.wovenpay.wovenpayments.interfaces;

import com.wovenpay.wovenpayments.models.CreateCustomerResponse;

/**
 * Created by thatmarc_ on 10-Mar-18.
 */

public interface OnCreateCustomerListener {
    void onComplete(boolean success, CreateCustomerResponse customerResponse, String message);
}
