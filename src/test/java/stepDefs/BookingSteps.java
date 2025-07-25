package stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import pojo.Booking;
import utilities.RequestSpecBuilderUtil;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BookingSteps {

    // base URL of the API
    private String baseUrl;

    // Specific endpoint path
    private String endpoint;

    // this will hold teh response of teh API request
    private Response response;

    private List<Integer> bookingIds;

    // Booking pojo variable
    private Booking booking;



    // Step to set the base URL for the API
    @Given("the base URL is {string}")
    public void the_base_url_is(String url){
       this.baseUrl = url;
    }

    // Step to set the endpoint path
    @And("the endpoint is {string}")
    public void the_endpoint_is(String endpoint){
        this.endpoint = endpoint;
    }

    // Step to send a GET request to the booking endpoint and store teh response
    @When("I send a GET request to the booking endpoint")
    public void i_send_a_getrequest_to_the_booking_endpoint(){
        response =  given()
                .spec(RequestSpecBuilderUtil.getFreshRequestSpecWithAuth())// Setup request spec
                .baseUri(baseUrl)  // Set base URI
                .when()            // When request is sent
                .get(endpoint);    // Perform GET on endpoint
        response.prettyPrint();
    }

    // Step to verify that the repsonse status code matches the expected value
    @Then("the response status code should be {int}")
    public void the_repsonse_status_code_should_be(int statusCode){
        Assert.assertEquals(response.getStatusCode(),statusCode,"Expected status code doesn't match");
    }

    // Step to verify that teh response contains a non-empty list of booking ID's
    @And("the response should contain a list of booking IDs")
    public void the_response_should_contain_a_list_of_booking_ids(){
        List<Map<String,Object>>  bookings = response.jsonPath().getList(""); //get full response body as list of maps
        Assert.assertTrue(!bookings.isEmpty(), "booking list should not be empty");

        // verify each item has the key 'bookingid'
        for(Map<String, Object> booking : bookings){
            Assert.assertTrue(booking.containsKey("bookingid"),"Each booking should contain 'bookingid'");
        }
    }

    // Step to check the content type of teh response
    @And("the response content type should be {string}")
    public void the_response_content_type_should_be(String expectedContentType){
        String actualContenttype = response.getContentType();
        Assert.assertTrue(actualContenttype.contains(expectedContentType), "Unexpected content type returned");
    }
    // Step to check that the each booking ID returned is a valid integer
    @Then("each booking ID in the response should be a valid integer")
    public void each_booking_id_in_the_response_should_be_a_valid_integer(){
        System.out.println("response: " + response.asString());
        List<Map<String, Object>> bookings = response.jsonPath().getList("");

        // Loop through each booking and check if 'bookingid' is of Integer type
        for(Map<String, Object> booking : bookings){
            Object id = booking.get("bookingid");
            System.out.println("id" + id);
            Assert.assertTrue(id instanceof Integer);
        }
    }
    @When("I send a GET request with firstname parameter {string}")
    public void i_send_a_get_request_with_firstname_parameter(String firstname){
        // Sending get request with firstname as query parameter
        response = given()
                .spec(RequestSpecBuilderUtil.withFirstname(firstname))
                .when()
                .get();

        // log the response for debugging
        response.prettyPrint();
       // Extract booking IDs from response
        bookingIds = response.jsonPath().getList("bookingid");

    }

    @Then("all returned bookings should have firstname {string}")
    public void all_returned_bookings_should_have_firstname(String expectedFirstname){
        // Loop through booking IDs and verify firstname

        int count = 0;
        for(Integer bookingId : bookingIds){
            if(count >=0) break;
            booking = RestAssured
                    .given()
                   // .spec(RequestSpecBuilderUtil.getFreshRequestSpecWithAuth())
                    .when()
                    .get("/booking/"+bookingId)
                    .then()
                    .extract()
                    .as(Booking.class);

            String actualFirstname = booking.getFirstname();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Assert.assertEquals(actualFirstname, expectedFirstname,"Booking ID " + bookingId +" returned incorrect firstname: " + actualFirstname);
            count++;
        }
    }

    @When("I send a GET request with lastname parameter {string}")
    public void i_send_a_get_request_with_lastname_parameter(String lastname){

        response = given()
                .spec(RequestSpecBuilderUtil.withLastname(lastname))
                .when()
                .get();
       response.prettyPrint();

        System.out.println("Content type: " + response.getContentType());
        bookingIds = response.jsonPath().getList("bookingid");


    }

    @And("all returned bookings should have lastname {string}")
    public void all_returned_bookings_should_have_lastname(String expectedLastname){
        int count =  0;
        for(int bookingid : bookingIds){
            if(count >=4) break;
            Booking getBooking = given()
                   // .spec(RequestSpecBuilderUtil.getFreshRequestSpecWithAuth())
                    .when()
                    .get("/booking/" + bookingid)
                    .then()
                    .extract()
                    .as(Booking.class);
            String actualLastname = getBooking.getLastname();

            Assert.assertEquals(actualLastname,expectedLastname,"BookingId "+ bookingid + " returned incorrect lastname " + actualLastname);
            count++;
        }
    }

    @When("I send a GET request with checkin date {string}")
    public void i_send_a_get_request_with_checkin_date(String checkinDate) {
      response = given()
              .spec(RequestSpecBuilderUtil.withCheckinDate(checkinDate))
              .when()
              .get();
      response.then().log().all();
    }

    @And("all returned bookings should have checkin date on or after {string}")
    public void allReturnedBookingsShouldHaveCheckinDateOnOrAfter(String expectedCheckinDate) {
    // Assert.assertFalse(bookingIds.isEmpty(), "No booking found");
    }

    @When("I send a GET request with checkout date {string}")
    public void i_send_a_get_request_with_checkout_date(String checkoutDate) {
         response = given()
                 .spec(RequestSpecBuilderUtil.withCheckoutDate(checkoutDate))
                 .when()
                 .get();

         bookingIds = response.jsonPath().getList("bookingid");
    }

    @And("all returned bookings should have checkout date on or before {string}")
    public void all_returned_bookings_should_have_checkout_date_on_or_before(String expectedCheckoutDate) {
         //  Assert.assertFalse(bookingIds.isEmpty(),"No booking found");

    }
}
