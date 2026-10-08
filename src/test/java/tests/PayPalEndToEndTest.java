package tests;

import api.PayPalApi;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PayPalEndToEndTest {

    @Test
    public void paypalPaymentApiFlowTest() {

        // =====================================================
        // 1. READ ENVIRONMENT VARIABLES
        // =====================================================

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


        // =====================================================
        // 2. CREATE PAYPAL API OBJECT
        // =====================================================

        PayPalApi paypalApi =
                new PayPalApi();


        // =====================================================
        // 3. GET ACCESS TOKEN
        // =====================================================

        Response tokenResponse =
                paypalApi.getAccessToken(
                        clientId,
                        clientSecret
                );

        System.out.println(
                "OAuth Status: "
                        + tokenResponse.getStatusCode()
        );

        Assert.assertEquals(
                tokenResponse.getStatusCode(),
                200,
                "PayPal OAuth failed"
        );

        String accessToken =
                tokenResponse
                        .jsonPath()
                        .getString(
                                "access_token"
                        );

        Assert.assertNotNull(
                accessToken,
                "Access token was not generated"
        );

        System.out.println(
                "PayPal OAuth successful."
        );


        // =====================================================
        // 4. CREATE ORDER
        // =====================================================

        String referenceOrderId =
                "ORD-" + System.currentTimeMillis();

        Response orderResponse =
                paypalApi.createOrder(
                        accessToken,
                        referenceOrderId,
                        "29.99"
                );

        System.out.println(
                "Create Order Status: "
                        + orderResponse.getStatusCode()
        );

        Assert.assertEquals(
                orderResponse.getStatusCode(),
                201,
                "PayPal order creation failed"
        );


        // =====================================================
        // 5. GET ORDER ID
        // =====================================================

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


        // =====================================================
        // 6. VALIDATE ORDER STATUS
        // =====================================================

        String orderStatus =
                orderResponse
                        .jsonPath()
                        .getString("status");

        System.out.println(
                "PayPal Order Status: "
                        + orderStatus
        );

        Assert.assertEquals(
                orderStatus,
                "CREATED",
                "Unexpected PayPal order status"
        );


        // =====================================================
        // 7. GET APPROVAL URL
        // =====================================================

        String approvalUrl =
                orderResponse
                        .jsonPath()
                        .getString(
                                "links.find { it.rel == 'approve' }.href"
                        );

        Assert.assertNotNull(
                approvalUrl,
                "Approval URL was not generated"
        );

        System.out.println(
                "Approval URL generated successfully."
        );


        // =====================================================
        // 8. PAYMENT VALIDATION
        // =====================================================

        System.out.println(
                "PayPal Sandbox order created successfully."
        );

        System.out.println(
                "Reference Order ID: "
                        + referenceOrderId
        );

        System.out.println(
                "PayPal Order ID: "
                        + paypalOrderId
        );

        System.out.println(
                "Amount: USD 29.99"
        );

        System.out.println(
                "Payment API validation completed."
        );
    }
}