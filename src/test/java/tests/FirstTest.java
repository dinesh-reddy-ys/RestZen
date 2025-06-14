package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Booking;
import model.BookingDates;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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

        // Print the response
        System.out.println(response.asString());

        // Soft assertion for the response status code
        softAssert.assertEquals(response.getStatusCode(), 201, "Unexpected status code!");

        // Add more soft assertions here if needed
        // Example: softAssert.assertTrue(response.body().asString().contains("text"), "Expected response body does not contain the text!");

        // Collate and report all soft assertion failures
        softAssert.assertAll();


    }
    @Test
    public void createBooking(){
        Booking booking = new Booking();
        booking.setFirstname("John");
        booking.setLastname("mark ");
        booking.setTotalprice(100);
        booking.setDepositpaid(true);

        // Create a BookingDates object
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2021-05-01");
        bookingDates.setCheckout("2021-05-03");

        booking.setBookingdates(bookingDates);

        // serialize and send POSt request
        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking")
                .then()
                .statusCode(200);
    }
}