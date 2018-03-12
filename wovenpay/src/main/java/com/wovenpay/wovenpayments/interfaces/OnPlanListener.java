package com.wovenpay.wovenpayments.interfaces;

import com.wovenpay.wovenpayments.models.Plan;

/**
 * Created by thatmarc_ on 12-Mar-18.
 */

public interface OnPlanListener {
    void onComplete(boolean success, Plan plan, String message);
}
