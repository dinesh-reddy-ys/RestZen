package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.RequestSpecBuilderUtil;

import static io.restassured.RestAssured.given;

/**
 * This class demonstrates the usage of Path Parameters in REST Assured
 * It makes a GET request to JSONPlaceholder API to fetch a specific post
 */
public class PathParam {
    
    /**
     * Test method to demonstrate path parameter usage
     * Sends a GET request to fetch post with ID 1
     * Endpoint: https://jsonplaceholder.typicode.com/posts/{id}
     */
    @Test
    public void pathParam() {
        // Set the base URI for all requests in this class
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Make GET request with path parameter
        // {id} in the URL will be replaced with the value specified in pathParam
        Response response = given()
                .pathParam("id", 1)  // Sets the path parameter 'id' to value 1
                .when()
                .get("/posts/{id}"); // {id} will be replaced with the value from pathParam

        // Output the response status code (expected: 200 OK)
        System.out.println("Status Code: " + response.getStatusCode());
        
        // Output the response body in a formatted JSON structure
        System.out.println("Response Body:");
        System.out.println(response.asPrettyString());
    }
    @Test
    public static void queryParam() {
        // Set the base URI for all requests in this class
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Make GET request with query parameters
        // This will automatically construct the URL with query string:
        // /todos?userId=1&completed=false
        Response response = given()
                .queryParam("userId", 1)      // Filter todos by user ID
                .queryParam("completed", false)// Filter only incomplete todos
                .when()
                .get("/todos");               // Endpoint for todos

        // Output the response status code (expected: 200 OK)
        System.out.println("Status Code: " + response.getStatusCode());

        // Output the response body in a formatted JSON structure
        // Will show filtered todos matching the query parameters
        System.out.println("Response Body:");
        System.out.println(response.asPrettyString());
    }

    @Test
    public void getLast(){

     RestAssured.useRelaxedHTTPSValidation();
      Response  response =  given()
              .baseUri("https://restful-booker.herokuapp.com")
              .queryParam("lastname","Smith")
                      .when()
                              .get("/booking");


      response.prettyPrint();
    }
}