package com.wovenpay.wovenpayments.interfaces;

import com.wovenpay.wovenpayments.models.Customer;

import java.util.List;

/**
 * Created by thatmarc_ on 10-Mar-18.
 */

public interface OnCustomersListener {
    void onComplete(boolean success, List<Customer> customerResponseList, String message);
}
