package com.wovenpay.wovenpayments.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {

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
@SerializedName("first_name")
@Expose
private Object firstName;
@SerializedName("last_name")
@Expose
private Object lastName;
@SerializedName("national_id")
@Expose
private Object nationalId;
@SerializedName("phone")
@Expose
private Object phone;
@SerializedName("email")
@Expose
private String email;
@SerializedName("address")
@Expose
private Object address;
@SerializedName("city")
@Expose
private Object city;
@SerializedName("country")
@Expose
private Object country;
@SerializedName("postal_code")
@Expose
private Object postalCode;

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

public Object getFirstName() {
return firstName;
}

public void setFirstName(Object firstName) {
this.firstName = firstName;
}

public Object getLastName() {
return lastName;
}

public void setLastName(Object lastName) {
this.lastName = lastName;
}

public Object getNationalId() {
return nationalId;
}

public void setNationalId(Object nationalId) {
this.nationalId = nationalId;
}

public Object getPhone() {
return phone;
}

public void setPhone(Object phone) {
this.phone = phone;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public Object getAddress() {
return address;
}

public void setAddress(Object address) {
this.address = address;
}

public Object getCity() {
return city;
}

public void setCity(Object city) {
this.city = city;
}

public Object getCountry() {
return country;
}

public void setCountry(Object country) {
this.country = country;
}

public Object getPostalCode() {
return postalCode;
}

public void setPostalCode(Object postalCode) {
this.postalCode = postalCode;
}

}