package api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PaymentApi {

    private String baseUrl = "https://api-m.sandbox.paypal.com";

    //private String accessToken = "EP6MYCGOUGh9GijE5EWoofuec7AKQSKuj2kf9kt_rDYE9rcnM_p4OPcHgzLDtyssNb8jllBQwd0039KQ";
    private String accessToken =
            System.getenv("PAYPAL_ACCESS_TOKEN");
    public Response processPayment(
            String orderId,
            double amount) {

        String body =
                "{"
                + "\"orderId\":\"" + orderId + "\","
                + "\"amount\":" + amount + ","
                + "\"currency\":\"USD\""
                + "}";
        
        Response response =
                given()
                        .redirects()
                        .follow(false)
                        .contentType("application/json")
                        .body(body)
                        .when()
                        .post(baseUrl + "/payments");

        System.out.println("Status: " + response.getStatusCode());
        System.out.println("Location: " + response.getHeader("Location"));
        
        
        return given()
                .redirects()
                .follow(true)
                .contentType("application/json")
                .body(body)
                .when()
                .post(baseUrl + "/payments");

    
    }
}