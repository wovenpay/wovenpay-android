package com.wovenpay.wovenpayments.interfaces;

import com.wovenpay.wovenpayments.models.Business;

/**
 * Created by thatmarc_ on 07-Mar-18.
 */

public interface OnBusinessListener {

    void onComplete(boolean success, Business business, String message);
}
