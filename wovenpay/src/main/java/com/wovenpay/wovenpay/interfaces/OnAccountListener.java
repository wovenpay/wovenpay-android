package com.wovenpay.wovenpay.interfaces;

import com.wovenpay.wovenpay.models.AccountResponse;

/**
 * Created by thatmarc_ on 07-Mar-18.
 */

public interface OnAccountListener {

    void onComplete(boolean success, AccountResponse accountResponse, String message);
}
