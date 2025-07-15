package tests;

import model.Booking;
import model.BookingDates;
import model.BookingResponse;
import org.testng.annotations.Test;
import services.BookingService;
import utilities.RequestSpecBuilderUtil;
import utilities.TokenManager;

import static io.restassured.RestAssured.given;

public class Authentication {
    private static int bookingId;
    @Test(priority = 1,dataProvider = "bookingData",dataProviderClass = utilities.DataProviders.class)
    public void createBooking(String firstName, String lastName, String totalPriceStr, String depositePaidStatusStr, String checkin, String checkout, String additionalNeeds){
       int totalPrice = (int) Double.parseDouble(totalPriceStr);
       boolean depositePaid = Boolean.parseBoolean(depositePaidStatusStr);
       BookingDates bookingDates = new BookingDates(checkin,checkout);


       Booking booking = new Booking(firstName,lastName,totalPrice,depositePaid,bookingDates,additionalNeeds);
       BookingResponse response = given()
               .baseUri("https://restful-booker.herokuapp.com")
               .contentType("application/json")
               .body(booking)
               .when()
               .post("/booking")
               .then()
               .statusCode(200)
               .extract()
               .as(BookingResponse.class);

      bookingId = response.getBookingid();
      System.out.print("Booking id of the user " + bookingId);

    }


    @Test(priority = 2,dataProvider = "bookingData",dataProviderClass = utilities.DataProviders.class,dependsOnMethods = {"createBooking"})
    public void basicAuth(String firstName,String lastName, String totalPriceStr, String depositePaidStr,String checkin, String checkout, String additionalNeeds){
        int totalPrice = (int) Double.parseDouble(totalPriceStr);
        boolean depositePaid = Boolean.parseBoolean(depositePaidStr);
        BookingDates bookingDates = new BookingDates(checkin,checkout);


        Booking booking = new Booking(firstName,lastName,totalPrice,depositePaid,bookingDates,additionalNeeds);
                 given()
                 .spec(RequestSpecBuilderUtil.getFreshRequestSpecWithAuth())
                .body(booking)
                .pathParam("id",bookingId)
                         .log().all()
                 .when()
                .put("/booking/{id}")
                .then()
                 //.log().all()
                .statusCode(200);
    }
}
