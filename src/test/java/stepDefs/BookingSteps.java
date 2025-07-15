package stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

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
        response =  given()      // Setup request spec
                .baseUri(baseUrl)  // Set base URI
                .when()            // When request is sent
                .get(endpoint);    // Perform GET on endpoint

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


}
