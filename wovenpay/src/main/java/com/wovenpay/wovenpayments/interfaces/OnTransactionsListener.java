package com.wovenpay.wovenpayments.interfaces;

import com.wovenpay.wovenpayments.models.Transaction;

import java.util.List;

/**
 * Created by thatmarc_ on 07-Mar-18.
 */

public interface OnTransactionsListener {

    void onComplete(boolean success, List<Transaction> transactionList, String message);
}
