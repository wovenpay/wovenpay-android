package com.wovenpay.wovenpay.interfaces;

import com.wovenpay.wovenpay.models.Business;

import java.util.List;

/**
 * Created by thatmarc_ on 07-Mar-18.
 */

public interface OnBusinessListListener {

    void onComplete(boolean success, List<Business> businesses, String message);
}
