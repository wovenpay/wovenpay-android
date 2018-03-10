package com.wovenpay.wovenpayments.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Code {

    @SerializedName("error_code")
    @Expose
    private String errorCode;
    @SerializedName("detail_code")
    @Expose
    private String detailCode;
    @SerializedName("docs")
    @Expose
    private String docs;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

    public String getDocs() {
        return docs;
    }

    public void setDocs(String docs) {
        this.docs = docs;
    }

}