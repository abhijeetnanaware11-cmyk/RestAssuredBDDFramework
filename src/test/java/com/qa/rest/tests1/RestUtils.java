package com.qa.rest.tests1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import java.util.Random;

public class RestUtils {

    private static String baseUrl = "https://app-uat.pehlayakshar.org/PAF";
    private static String token;

    // 🔥 Call this before using any API
    public static void authenticate() {

        RestAssured.baseURI = baseUrl;

        String authPayload = "{\n" +
                "  \"username\": \"abhijeet.nanaware\",\n" +
                "  \"password\": \"Abhi@12$\"\n" +
                "}";

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(authPayload)
                .post("/authenticate");

        token = response.jsonPath().getString("token");
    }

    public static Response post(String endpoint, Object body) {

        RestAssured.baseURI = baseUrl;

        return RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(body)
                .post(endpoint);
    }

    // 🔥 Random Email
    public static String randomEmail() {
        return "user" + System.currentTimeMillis() + "@gmail.com";
    }

    // 🔥 Random Mobile
    public static String randomMobile() {
        Random r = new Random();
        long number = 9000000000L + (long)(r.nextDouble() * 1000000000L);
        return String.valueOf(number);
    }
}
