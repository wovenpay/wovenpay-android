
package com.wovenpay.wovenpayments.models;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Webhook {

    @SerializedName("created")
    private String mCreated;
    @SerializedName("event")
    private String mEvent;
    @SerializedName("id")
    private Long mId;
    @SerializedName("key")
    private String mKey;
    @SerializedName("target")
    private String mTarget;
    @SerializedName("updated")
    private String mUpdated;
    @SerializedName("user")
    private String mUser;

    public String getCreated() {
        return mCreated;
    }

    public void setCreated(String created) {
        mCreated = created;
    }

    public String getEvent() {
        return mEvent;
    }

    public void setEvent(String event) {
        mEvent = event;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getTarget() {
        return mTarget;
    }

    public void setTarget(String target) {
        mTarget = target;
    }

    public String getUpdated() {
        return mUpdated;
    }

    public void setUpdated(String updated) {
        mUpdated = updated;
    }

    public String getUser() {
        return mUser;
    }

    public void setUser(String user) {
        mUser = user;
    }

}
