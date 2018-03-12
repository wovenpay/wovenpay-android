package com.wovenpay.wovenpayments.interfaces;

import com.wovenpay.wovenpayments.models.Plan;

import java.util.List;

/**
 * Created by thatmarc_ on 12-Mar-18.
 */

public interface OnPlansListener {
    void onComplete(boolean success, List<Plan> planList, String message);
}
