package com.wovenpay.wovenpayments.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditBusinessPayload {

@SerializedName("name")
@Expose
private String name;
@SerializedName("email")
@Expose
private String email;
@SerializedName("phone_number")
@Expose
private Integer phoneNumber;
@SerializedName("country")
@Expose
private String country;

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

public Integer getPhoneNumber() {
return phoneNumber;
}

public void setPhoneNumber(Integer phoneNumber) {
this.phoneNumber = phoneNumber;
}

public String getCountry() {
return country;
}

public void setCountry(String country) {
this.country = country;
}

}