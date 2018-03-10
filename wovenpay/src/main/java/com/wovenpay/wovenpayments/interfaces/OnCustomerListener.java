package com.wovenpay.wovenpayments.interfaces;

import com.wovenpay.wovenpayments.models.Customer;

/**
 * Created by thatmarc_ on 10-Mar-18.
 */

public interface OnCustomerListener {
    void onComplete(boolean success, Customer customerResponse, String message);
}
