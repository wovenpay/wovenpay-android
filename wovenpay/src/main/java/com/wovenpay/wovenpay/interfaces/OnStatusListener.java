package com.wovenpay.wovenpay.interfaces;

/**
 * Created by thatmarc_ on 07-Mar-18.
 */

public interface OnStatusListener {

    void onComplete(boolean success, String status, String paymentId, String error);
}
