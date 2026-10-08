package tests;

import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.PaymentApi;

public class PaymentApiTest {

    @Test
    public void successfulPaymentTest() {

        PaymentApi paymentApi =
                new PaymentApi();

        Response response =
                paymentApi.processPayment(
                        "ORD1001",
                        29.99);

        Assert.assertEquals(
                response.statusCode(),
                200);

        Assert.assertEquals(
                response.jsonPath()
                        .getString("status"),
                "SUCCESS");
    }
    
    @Test
    public void paymentResponseShouldContainStatus() {

        PaymentApi paymentApi =
                new PaymentApi();

        Response response =
                paymentApi.processPayment(
                        "ORD1002",
                        100);

        Assert.assertNotNull(
                response.jsonPath()
                        .getString("status"));
    }
}