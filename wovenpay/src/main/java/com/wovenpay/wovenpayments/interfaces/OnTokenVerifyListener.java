package com.wovenpay.wovenpayments.interfaces;

/**
 * Created by thatmarc_ on 07-Mar-18.
 */

public interface OnTokenVerifyListener {
    void onVerify(boolean success, String token, String message);
}
