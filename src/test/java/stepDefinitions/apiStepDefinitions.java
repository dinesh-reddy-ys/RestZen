package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class apiStepDefinitions {

    private String baseUri;
    private Response response;

    @Given("The base URI is {string}")
    public void theBaseUriIs(String uri){
        baseUri = uri;
    }

    @When("I send a GET request to {string}")
    public void iSendGetRequestTo(String endpoint){
        response = given()
                .baseUri(baseUri)
                .when()
                .get(endpoint);
    }
    @Then("The response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int expectedStatusCode){
        assertEquals(response.statusCode(), expectedStatusCode);
    }
}
