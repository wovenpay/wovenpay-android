package com.wovenpay.wovenpayments;

import com.wovenpay.wovenpayments.interfaces.AuthComplete;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        wovenPay.getAuthorizationToken(email, wrongPassword, new AuthComplete() {
            @Override
            public void onComplete(boolean success, String token, String message) {
                System.out.println("Lo!");
//                assertTrue(success && token != null);
            }
        });
    }
}