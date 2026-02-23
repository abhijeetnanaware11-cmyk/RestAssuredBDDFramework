package com.qa.rest.tests1;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Post_Request {

    private String token;
    
    private Map<String, Object> userMap;

    @BeforeClass
    public void setup() {

        // ================= BASE CONFIG =================
        RestAssured.baseURI = "https://app-uat.pehlayakshar.org";
        RestAssured.basePath = "/PAF";

        // ================= LOGIN PAYLOAD =================
        Map<String, String> loginMap = new HashMap<>();
        loginMap.put("username", "abhijeet.nanaware");
        loginMap.put("password", "Abhi@12$");

        System.out.println("=========== LOGIN REQUEST ===========");

        Response loginResponse =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .body(loginMap)
                        .log().all()
                .when()
                        .post("/authenticate");

        System.out.println("=========== LOGIN RESPONSE ===========");
        loginResponse.then().log().all();

        // Validate login success
        loginResponse.then().statusCode(200);

        // ================= EXTRACT TOKEN =================
        token = loginResponse.jsonPath().getString("payload.token");

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token not found in login response!");
        }

        // ================= EXTRACT SESSION =================
        

        System.out.println("TOKEN: " + token);
        

        // ================= USER CREATION PAYLOAD =================
        userMap = new HashMap<>();

        userMap.put("name", getRandomName());
        userMap.put("contactNumber", getRandomMobile());
        userMap.put("email", getRandomEmail());
        userMap.put("dateOfBirth", "2020-02-03");
        userMap.put("grade", 1);
        userMap.put("area", 8);
        userMap.put("citizenship", "Indian");
        userMap.put("status", true);
        userMap.put("roleId", 62);
        userMap.put("stateId", 55);
        userMap.put("districtId", 496);
        userMap.put("schoolId", 12);
        userMap.put("isAdmin", true);
        userMap.put("loggedInUserName", "abhijeet.nanaware");
    }

   
    @Test
    
    public void testCreateUser() throws JsonProcessingException {

        String userJson = new ObjectMapper().writeValueAsString(userMap);

        Response createResponse =
                given()
                        .header("Authorization", "Bearer " + token)
                        .multiPart("user", userJson, "application/json")
                        // If profilePic is required:
                        // .multiPart("profilePic", new File("src/test/resources/test.jpg"))
                        .log().all()
                .when()
                        .post("/users");

        createResponse.then().log().all();
        createResponse.then().statusCode(200);
    }



    // ================= RANDOM DATA METHODS =================

    private String getRandomEmail() {
        return "user" + System.currentTimeMillis() + "@gmail.com";
    }

    private String getRandomMobile() {
        Random r = new Random();
        long number = 9000000000L + (long) (r.nextDouble() * 1000000000L);
        return String.valueOf(number);
    }

    private String getRandomName() {
        return "TestUser" + System.currentTimeMillis();
    }
}
