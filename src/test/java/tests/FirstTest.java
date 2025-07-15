package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Booking;
import model.BookingDates;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import services.BookingService;

import static io.restassured.RestAssured.given;

public class FirstTest {

    @Test
    public void test1() {
        SoftAssert softAssert = new SoftAssert();

        Response response =
        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .when()
                .get("/ping");
        String contentType = response.getContentType();
        System.out.println("Content Type: " + contentType);
        // Print the response
        System.out.println(response.asString());

        // Soft assertion for the response status code
        softAssert.assertEquals(response.getStatusCode(), 201, "Unexpected status code!");

        // Add more soft assertions here if needed
        // Example: softAssert.assertTrue(response.body().asString().contains("text"), "Expected response body does not contain the text!");

        // Collate and report all soft assertion failures
        softAssert.assertAll();


    }
    
    @Test(dataProvider = "bookingData", dataProviderClass = utilities.DataProviders.class)
    public void createBooking(String firstname, String lastname, String totalpriceStr, String depositpaidStr, String checkin, String checkout, String additionalneeds) {
       
    	int totalprice = (int)Double.parseDouble(totalpriceStr);
    	boolean depositpaid = Boolean.parseBoolean(depositpaidStr);
    	// Create a Booking object with necessary details
        BookingDates bookingDates = new BookingDates(checkin, checkout);
        // Create a Booking object with all required fields
        Booking booking = new Booking(firstname, lastname, totalprice, depositpaid, bookingDates,additionalneeds);
        // get auth token
        String token = new BookingService().createToken();
        System.out.println(token);
        // serialize and send POSt request
        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                ;
    }

    @Test
    public void getBooking1(){
        Response response = given()
        .baseUri("https://restful-booker.herokuapp.com")
                .when()
                .get("/booking/5155");
        System.out.println(response.asString());

    }

  @Test
    public void getBooking(){
      Booking booking = new Booking();
      booking.setFirstname("Johnnn");
      booking.setLastname("mark ");
      booking.setTotalprice(100);
      booking.setDepositpaid(true);

      // Create a BookingDates object
      BookingDates bookingDates = new BookingDates();
      bookingDates.setCheckin("2021-05-01");
      bookingDates.setCheckout("2021-05-03");

      booking.setBookingdates(bookingDates);
      BookingService bookingService = new BookingService();
     // Create booking
      Response response = bookingService.createBooking(booking);
     // get booking id
      int bookingId = response.jsonPath().getInt("bookingid");
  System.out.println(bookingId);


      Booking bookingDetails = bookingService.getBooking(bookingId);
  System.out.println(bookingDetails.getFirstname());

    }
}