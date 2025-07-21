package utilities;

import io.restassured.response.Response;
import services.BookingService;

import static io.restassured.RestAssured.given;

public class TokenManager {

    /**
     * Retrieves a fresh authentication token.
     * This method is used to get a new token by calling the private method `generateToken`.
     *
     * @return A fresh authentication token as a String.
     */
    public static String getFreshToken() {
        return generateToken();
    }

    /**
     * Generates a new authentication token.
     * This method sends a POST request to the `/auth` endpoint of the API
     * with the required credentials and retrieves the token from the response.
     *
     * @return The generated authentication token as a String.
     */
    private static String generateToken() {
        // Send a POST request to the /auth endpoint with username and password
        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com") // Base URI of the API
                .contentType("application/json") // Content type of the request
                .body("{\"username\":\"admin\",\"password\":\"password123\"}") // Request body with credentials
                .when()
                .post("/auth"); // Endpoint to generate the token

        // Extract and return the token from the response
        return response.jsonPath().getString("token");
    }
}
