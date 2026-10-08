package tests;

import api.PayPalApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PayPalCreateOrderTest {

    @Test
    public void createPayPalOrderTest() {

        String clientId =
                System.getenv("PAYPAL_CLIENT_ID");

        String clientSecret =
                System.getenv("PAYPAL_CLIENT_SECRET");

        Assert.assertNotNull(
                clientId,
                "PAYPAL_CLIENT_ID is not set"
        );

        Assert.assertNotNull(
                clientSecret,
                "PAYPAL_CLIENT_SECRET is not set"
        );

        PayPalApi paypalApi =
                new PayPalApi();

        // Step 1: OAuth
        Response tokenResponse =
                paypalApi.getAccessToken(
                        clientId,
                        clientSecret
                );

        Assert.assertEquals(
                tokenResponse.getStatusCode(),
                200,
                "PayPal OAuth failed"
        );

        String accessToken =
                tokenResponse
                        .jsonPath()
                        .getString("access_token");

        Assert.assertNotNull(
                accessToken,
                "Access token was not generated"
        );

        // Step 2: Create Order
        Response orderResponse =
                paypalApi.createOrder(
                        accessToken,
                        "ORD-1001",
                        "29.99"
                );

        System.out.println(
                "Create Order Status: "
                        + orderResponse.getStatusCode()
        );

        System.out.println(
                "PayPal Order Response:"
        );

        System.out.println(
                orderResponse.asPrettyString()
        );

        Assert.assertEquals(
                orderResponse.getStatusCode(),
                201,
                "PayPal order creation failed"
        );

        String paypalOrderId =
                orderResponse
                        .jsonPath()
                        .getString("id");

        Assert.assertNotNull(
                paypalOrderId,
                "PayPal Order ID was not generated"
        );

        System.out.println(
                "PayPal Order ID: "
                        + paypalOrderId
        );
    }
}