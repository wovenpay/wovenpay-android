package com.wovenpay.wovenpayments.interfaces;

/**
 * Created by thatmarc_ on 07-Mar-18.
 */

public interface OnPaymentListener {

    void onComplete(boolean success, String transactionId, String message);
}
