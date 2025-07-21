package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderUtil {

    /**
     * Creates a new RequestSpecification with authentication.
     * This method retrieves a fresh authentication token using the TokenManager class
     * and builds a RequestSpecification with the token included in the header.
     *
     * @return A RequestSpecification object with authentication and default settings.
     */
    public static RequestSpecification getFreshRequestSpecWithAuth() {
        // Retrieve a fresh token using TokenManager
        String token = TokenManager.getFreshToken();
        System.out.println("Token from spec builder " + token);

        // Build and return the RequestSpecification with the token and default settings
        return new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com") // Base URI of the API
                .addHeader("Cookie", "token=" + token) // Add the token to the header
                .setContentType("application/json") // Set the content type to JSON
                .build();
    }
}
