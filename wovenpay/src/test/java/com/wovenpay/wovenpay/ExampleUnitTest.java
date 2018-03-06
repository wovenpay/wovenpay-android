package com.wovenpay.wovenpay;

import android.util.Log;

import com.wovenpay.wovenpay.interfaces.WovenResponse;

import org.junit.Test;

import java.io.IOException;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    final String apikey = "ak_7zeqY5qGVhPLTMKKyb3vwF";
    final String apisecret = "sk_q6segivqhA3xxNPn2KkWbP";
    final String email = "test@test.com";
    final String password = "test12345";
    final String wrongPassword = "test123451";
    final String testToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiYWNjX2JrVVJrNGI2MnE2WGJXaE5DZWphUjMiLCJ1c2VybmFtZSI6InRlc3RAdGVzdC5jb20iLCJleHAiOjE1MTk3MDE2ODgsImVtYWlsIjoidGVzdEB0ZXN0LmNvbSIsIm9yaWdfaWF0IjoxNTE5NjU4NDg4fQ.p7V85SFoZ3WPF08c5MdkVPgQllB6DGZ6pS8w_OSmTdU";

    final String testBusiness = "bus_B6cDFj4uTz4AuFAUFzPFgm";
    final String testCustomer = "cus_BBmafrJiRgTJaVh5aenwLm";
    final String testPlan = "plan_jpcms2jwkvHbinJHvHygkZ";

    WovenPay wovenPay = new WovenPay(apikey, apisecret, false);

    @Test
    public void authWovenPay() {
        try {
            wovenPay.getAuthorizationToken(email, password, new WovenResponse() {
                @Override
                public void onResponse(String response) {
                    System.out.println(response);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}