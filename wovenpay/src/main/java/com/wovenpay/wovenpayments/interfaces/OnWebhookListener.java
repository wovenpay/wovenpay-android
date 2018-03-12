package com.wovenpay.wovenpayments.interfaces;

import com.wovenpay.wovenpayments.models.Webhook;

/**
 * Created by thatmarc_ on 13-Mar-18.
 */

public interface OnWebhookListener {
    void onComplete(boolean success, Webhook webhook, String message);
}
