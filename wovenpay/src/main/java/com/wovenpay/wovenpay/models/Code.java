package com.wovenpay.wovenpay.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Code {

@SerializedName("error_code")
@Expose
private String errorCode;
@SerializedName("detail_code")
@Expose
private String detailCode;

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

}