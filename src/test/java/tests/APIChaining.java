package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import model.AuthRequest;
import model.AuthResponse;
import model.Booking;
import model.BookingDates;
import model.BookingResponse;
import services.BookingService;
import static io.restassured.RestAssured.given;

public class APIChaining {
	
	@Test(priority = 1,dataProvider ="bookingData",dataProviderClass = utilities.DataProviders.class,retryAnalyzer = utilities.RetryAnalyzer.class)
	public void createBooking(String firstname, String lastname, String totalpriceStr, String depositpaidStr,String checkin, String checkout, String additionalneeds) {
		
		// Step 1: Create a booking using the provided data
		
		// Convert string inputs to approriate types
		int totalprice = (int) Double.parseDouble(totalpriceStr);
		boolean depositpaid = Boolean.parseBoolean(depositpaidStr);
		// Create a booking object with necessary details
		BookingDates bookingdates = new BookingDates(checkin, checkout);
		
		Booking booking = new Booking(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds);
		// Create a BookingService instance to handle API requests
		BookingResponse bookingResponse = given()
				.baseUri("https://restful-booker.herokuapp.com")
				.contentType("application/json")
				.pathParam("endpoint","booking")
				.body(booking)
				.when()
				.post("/{endpoint}")
				.then()
				.statusCode(200)
				.extract()
				.as(BookingResponse.class);
		
		// Print the booking ID from the response
		System.out.println("booking ID:"+bookingResponse.getBookingid());
		
		// Step 2: Authenticate to get a token
		
		AuthRequest authRequest = new AuthRequest("admin","password123");
		AuthResponse authResponse = given()
				.baseUri("https://restful-booker.herokuapp.com")
				.contentType("application/json")
				.body(authRequest)
				.when()
				.post("/auth")
				.then()
				.statusCode(200)
				.extract()
				.as(AuthResponse.class);
		
		// Print the authentication token
		System.out.println("Authentication Token: " + authResponse.getToken());
		
		// Step 3: Update the booking with the token
		Booking updatedBooking = new Booking("John", "De", 150, true, bookingdates, "Breakfast");
		Response updateResponse = given()
				.baseUri("https://restful-booker.herokuapp.com")
				.contentType("application/json")
				.header("Cookie", "token=" + authResponse.getToken())
				.body(updatedBooking)
				.when()
				.put("/booking/" + bookingResponse.getBookingid())
				.then()
				.statusCode(200)
				.extract()
				.response();
		
		// Print the response of the update operation
		System.out.println("Update Response: " + updateResponse.asString());

	}

}
