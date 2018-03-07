package com.wovenpay.wovenpay.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Business {

    @SerializedName("errors")
    @Expose
    private String errors;
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("metadata")
    @Expose
    private Object metadata;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("is_live")
    @Expose
    private Boolean isLive;
    @SerializedName("apikey")
    @Expose
    private String apikey;
    @SerializedName("apisecret")
    @Expose
    private String apisecret;
    @SerializedName("pubkey")
    @Expose
    private String pubkey;

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Object getObject() {
        return metadata;
    }

    public void setObject(Object metadata) {
        this.metadata = metadata;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getIsLive() {
        return isLive;
    }

    public void setIsLive(Boolean isLive) {
        this.isLive = isLive;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getApisecret() {
        return apisecret;
    }

    public void setApisecret(String apisecret) {
        this.apisecret = apisecret;
    }

    public String getPubkey() {
        return pubkey;
    }

    public void setPubkey(String pubkey) {
        this.pubkey = pubkey;
    }

}