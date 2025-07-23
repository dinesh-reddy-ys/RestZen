package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderUtil {
    private static final String BASE_URI = "https://restful-booker.herokuapp.com";

    private static final String BASE_PATH = "/booking";


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

    public static RequestSpecification withCheckinDate(String checkinDate){
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH)
                .addQueryParam("checkin",checkinDate)
                .build();
    }

    public static RequestSpecification withCheckoutDate(String checkoutDate){
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH)
                .addQueryParam("checkout",checkoutDate)
                .build();
    }

    public static RequestSpecification withFirstname(String firstname){
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH)
                .addQueryParam("firstname",firstname)
                .build();
    }

    public static RequestSpecification withLastname(String lastname){
        return new RequestSpecBuilder()
                .setBasePath(BASE_URI)
                .setBasePath(BASE_PATH)
                .addQueryParam("lastname",lastname)
                .build();

    }
    public static RequestSpecification withTotalPrice(String totalPrice){
        return new RequestSpecBuilder()
                .addQueryParam("totalprice",totalPrice)
                .build();
    }

    public static RequestSpecification withDepositePaid(String depositePaid){
        return new RequestSpecBuilder()
                .addQueryParam("depositepaid",depositePaid)
                .build();
    }

    public static RequestSpecification withAdditionalNeeds(String additionalNeeds){
        return new RequestSpecBuilder()
                .addQueryParam("additionalneeds",additionalNeeds)
                .build();
    }


}
