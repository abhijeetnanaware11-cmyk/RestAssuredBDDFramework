package com.qa.rest.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected RequestSpecification reqSpec;

    @BeforeClass
    public void setup() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYmhpamVldC5uYW5hd2FyZSIsInJvbGVzIjoiUk9MRV9BRlhfU3VwZXJfQWRtaW4iLCJpYXQiOjE3NzA4MDc3ODksImV4cCI6MTc3MDg5NDE4OX0.a3LQsh6BgAJ0ozqGbcFd7M8sqcVKPAChDyTIGjKHUXY";
        reqSpec = new RequestSpecBuilder()
                .setBaseUri("https://app-uat.pehlayakshar.org/login")
                .setAuth(io.restassured.RestAssured.oauth2(token))
                .setContentType("application/json")
                .build();
    }
}
 

/*   common configuration (base URI, token, content type) for all API tests, so you don't repeat it in every test class

Common base URI

Auth token

Content type
→ all automatically applied.


*/