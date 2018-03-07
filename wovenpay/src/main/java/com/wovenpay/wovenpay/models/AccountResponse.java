package com.wovenpay.wovenpay.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountResponse {

    @SerializedName("errors")
    @Expose
    private Errors errors;
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("metadata")
    @Expose
    private Object metadata;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("username")
    @Expose
    private Object username;
    @SerializedName("firstname")
    @Expose
    private Object firstname;
    @SerializedName("lastname")
    @Expose
    private Object lastname;

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
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

    public Object getMetadata() {
        return metadata;
    }

    public void setMetadata(Object metadata) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getUsername() {
        return username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }

    public Object getFirstname() {
        return firstname;
    }

    public void setFirstname(Object firstname) {
        this.firstname = firstname;
    }

    public Object getLastname() {
        return lastname;
    }

    public void setLastname(Object lastname) {
        this.lastname = lastname;
    }

}