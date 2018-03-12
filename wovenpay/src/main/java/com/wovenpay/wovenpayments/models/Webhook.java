
package com.wovenpay.wovenpayments.models;

import com.google.gson.annotations.SerializedName;

public class Webhook {

    @SerializedName("event")
    private String Event;
    @SerializedName("key")
    private String Key;
    @SerializedName("target")
    private String Target;

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getTarget() {
        return Target;
    }

    public void setTarget(String target) {
        Target = target;
    }

}
