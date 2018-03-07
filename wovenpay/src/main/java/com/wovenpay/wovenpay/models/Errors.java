package com.wovenpay.wovenpay.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Errors {

@SerializedName("message")
@Expose
private String message;
@SerializedName("code")
@Expose
private Code code;

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public Code getCode() {
return code;
}

public void setCode(Code code) {
this.code = code;
}

}