package tests;

import api.PayPalApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PayPalApiTest {

    @Test
    public void getPayPalAccessTokenTest() {

        String clientId = System.getenv("PAYPAL_CLIENT_ID");
        String clientSecret = System.getenv("PAYPAL_CLIENT_SECRET");

        Assert.assertNotNull(
                clientId,
                "PAYPAL_CLIENT_ID is not set"
        );

        Assert.assertNotNull(
                clientSecret,
                "PAYPAL_CLIENT_SECRET is not set"
        );

        PayPalApi paypalApi = new PayPalApi();

        Response response = paypalApi.getAccessToken(
                clientId,
                clientSecret
        );

        System.out.println("Status: " + response.getStatusCode());
        System.out.println("Response:");
        System.out.println(response.asPrettyString());

        Assert.assertEquals(
                response.getStatusCode(),
                200,
                "PayPal OAuth failed"
        );

        Assert.assertNotNull(
                response.jsonPath().getString("access_token"),
                "Access token not returned"
        );
    }
}