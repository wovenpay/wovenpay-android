package com.wovenpay.wovenpay.interfaces;

import com.wovenpay.wovenpay.models.Business;

/**
 * Created by thatmarc_ on 07-Mar-18.
 */

public interface OnBusinessListener {

    void onComplete(boolean success, Business business, String message);
}
