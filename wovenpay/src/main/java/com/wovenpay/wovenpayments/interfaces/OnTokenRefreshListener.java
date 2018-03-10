package com.wovenpay.wovenpayments.interfaces;

/**
 * Created by thatmarc_ on 07-Mar-18.
 */

public interface OnTokenRefreshListener {
    void onRefresh(boolean success, String token, String message);
}
