package com.wovenpay.wovenpay.models;

/**
 * Created by thatmarc_ on 06-Mar-18.
 */
public class AuthenticateModel {
    private String email;
    private String password;

    public AuthenticateModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
