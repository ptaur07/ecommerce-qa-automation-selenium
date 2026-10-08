package api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PayPalApi {

    private final String baseUrl =
            "https://api-m.sandbox.paypal.com";

    // =========================================================
    // GET ACCESS TOKEN
    // =========================================================

    public Response getAccessToken(
            String clientId,
            String clientSecret) {

        return given()
                .auth()
                .preemptive()
                .basic(clientId, clientSecret)
                .header("Accept", "application/json")
                .contentType(
                        "application/x-www-form-urlencoded"
                )
                .formParam(
                        "grant_type",
                        "client_credentials"
                )
                .when()
                .post(
                        baseUrl
                                + "/v1/oauth2/token"
                );
    }

    // =========================================================
    // CREATE PAYPAL ORDER
    // =========================================================

    public Response createOrder(
            String accessToken,
            String orderId,
            String amount) {

        String requestBody =
                "{"
                        + "\"intent\":\"CAPTURE\","
                        + "\"purchase_units\":["
                        + "{"
                        + "\"reference_id\":\""
                        + orderId
                        + "\","
                        + "\"amount\":{"
                        + "\"currency_code\":\"USD\","
                        + "\"value\":\""
                        + amount
                        + "\"}"
                        + "}"
                        + "]"
                        + "}";

        return given()
                .header(
                        "Authorization",
                        "Bearer " + accessToken
                )
                .header(
                        "Content-Type",
                        "application/json"
                )
                .header(
                        "Accept",
                        "application/json"
                )
                .body(requestBody)
                .when()
                .post(
                        baseUrl
                                + "/v2/checkout/orders"
                );
    }

    // =========================================================
    // CAPTURE PAYPAL ORDER
    // =========================================================

    public Response captureOrder(
            String accessToken,
            String paypalOrderId) {

        return given()
                .header(
                        "Authorization",
                        "Bearer " + accessToken
                )
                .header(
                        "Content-Type",
                        "application/json"
                )
                .header(
                        "Accept",
                        "application/json"
                )
                .when()
                .post(
                        baseUrl
                                + "/v2/checkout/orders/"
                                + paypalOrderId
                                + "/capture"
                );
    }
}