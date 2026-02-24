package com.qa.rest.tests1;

import com.qa.rest.base.BaseTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
public class GetRequestTest extends BaseTest {

    @Test
    public void getUserById() {
        given()
        .spec(reqSpec)
        .header("Test-Header", "TestValue") 
    .when()
        .get("/users/3")
    .then()
        .statusCode(200)
     .header("Content-Type","text/html");
    }

   
    
    @Test
    public void getAllUsers() {
        given()
            .spec(reqSpec)
        .when()
            .get("/users/all")
        .then()
            .statusCode(200)
            .header("Content-Type","text/html");
            
            }
   

   
	

	@Test
    public void getAllUserGroups() {
        given()
            .spec(reqSpec)
        .when()
            .get("/userGroups/all")
        .then()
            .statusCode(200)
            .header("Content-Type","text/html");
            
            }
    
    
    
    @Test
    public void createUser() {
        String requestBody = "{\r\n"
        		+ "    \"name\": \"Abhijeet M\",\r\n"
        		+ "    \"contactNumber\": \"9348580000\",\r\n"
        		+ "    \"email\": \"Abhijeet@yopmail.com\",\r\n"
        		+ "    \"dateOfBirth\": \"2006-05-19\",\r\n"
        		+ "    \"grade\": 1,\r\n"
        		+ "    \"area\": 27,\r\n"
        		+ "    \"userType\": \"Registered\",\r\n"
        		+ "    \"citizenship\": \"Indian\",\r\n"
        		+ "    \"status\": true,\r\n"
        		+ "    \"roleId\": 65,\r\n"
        		+ "    \"stateId\": 55,\r\n"
        		+ "    \"districtId\": 510,\r\n"
        		+ "    \"schoolId\": 38,\r\n"
        		+ "    \"isAdmin\": false,\r\n"
        		+ "    \"loggedInUserName\": \"abhijeet.nanaware\"}";

        Response response = given()
            .spec(reqSpec)
            .body(requestBody)
        .when()
            .post("/users");
        response.then().statusCode(200); 
        response.prettyPrint(); 
    }
    
    
    
}
