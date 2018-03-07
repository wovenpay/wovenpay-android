package com.wovenpay.wovenpay.interfaces;

import com.wovenpay.wovenpay.models.Transation;

import java.util.List;

/**
 * Created by thatmarc_ on 07-Mar-18.
 */

public interface OnTransactionsListener {

    void onComplete(boolean success, List<Transation> transactionList, String message);
}
