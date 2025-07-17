package services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.Booking;

import static io.restassured.RestAssured.given;

public class BookingService {
    // Base URI for the RESTful Booker API
    private static final String BASE_URI = "https://restful-booker.herokuapp.com";
    private static final String USERNAME = "admin"; // Username for authentication
    private static final String PASSWORD = "password123"; // Password for authentication

    /**
     * Creates an authentication token by sending a POST request to the /auth endpoint.
     * @return The authentication token as a String.
     */
    public static String createToken() {
        Response response = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body("{ \"username\": \"" + USERNAME + "\", \"password\": \"" + PASSWORD + "\"}")
                .when()
                .post("/auth");

        // Extract and return the token from the response
        return response.jsonPath().getString("token");
    }

    /**
     * Creates a new booking by sending a POST request to the /booking endpoint.
     * @param booking The booking object containing the details of the booking.
     * @return The response object containing the details of the created booking.
     */
    public Response createBooking(Booking booking) {
        return given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(booking) // Serialize the booking object to JSON
                .when()
                .post("/booking")
                .then()
                .extract()
                .response(); // Extract the response for further processing
    }

    /**
     * Retrieves the details of a specific booking by its ID.
     * @param bookingId The ID of the booking to retrieve.
     * @return The Booking object containing the details of the booking.
     */
    public Booking getBooking(int bookingId) {
        // Generate an authentication token
        String token = createToken();

        return given()
                .baseUri(BASE_URI)
                .accept(ContentType.JSON) // Specify the expected response format
                .cookie("token", token) // Include the authentication token in the request
                .pathParam("bookingId", bookingId) // Set the booking ID as a path parameter
                .when()
                .get("/booking/{bookingId}") // Send a GET request to retrieve the booking
                .then()
                .statusCode(200) // Ensure the response status code is 200 (OK)
                .extract()
                .as(Booking.class); // Deserialize the response into a Booking object
    }
}
