package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Hooks {
    // Global request Specification to be reused in step definitions
    public static RequestSpecification requestSpec;

   // This method will run BEFORE each scenario starts
    @Before
    public void setUp(Scenario scenario) {
        System.out.println("====== Starting Scenario: " + scenario.getName() + "==============");

        // Initialize Request Specification with default headers, content type etc.
        requestSpec = new RequestSpecBuilder()
                .setContentType("application/json")      // Set default content type
                .build();

        // You can also attach auth tokens, default query params, base URI atc. here
        RestAssured.useRelaxedHTTPSValidation();
    }
    // This metyhod will run AFTER each scenario ends
    @After
    public void tearDown(Scenario scenario){
        System.out.println("====================Finished Scenario: " + scenario.getName() + " =================");

        if(scenario.isFailed()){
            System.out.println("Scenario FAILED! Do some failure handling here.");
        }

        // Optional: Cleanup or reset global objects if necessary
        requestSpec = null;
    }

}
