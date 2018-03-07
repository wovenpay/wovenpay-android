package com.wovenpay.wovenpay.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentPayload {

    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("customer")
    @Expose
    private Customer customer;
    @SerializedName("order")
    @Expose
    private Order order;
    @SerializedName("reference")
    @Expose
    private String reference;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

}