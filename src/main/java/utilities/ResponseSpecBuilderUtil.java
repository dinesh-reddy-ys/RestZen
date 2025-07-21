package utilities;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseSpecBuilderUtil {
    /**
     * Builds a basic response spec that checks for 200 status code JSON content type
     */
    public static ResponseSpecification getDefaultSpec(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType("application/json")
                .expectResponseTime(lessThan(3000L))   // 3 sec max response    time
                .build();
    }

    /**
     * Builds a response spec with custom status  code and resposne time
     */
    public static ResponseSpecification getCustomSpec(int expectedStatusCode, long maxresponseTimeMs){
        return new ResponseSpecBuilder()
                .expectStatusCode(expectedStatusCode)
                .expectContentType("application/json")
                .expectResponseTime(lessThan(maxresponseTimeMs))
                .build();
    }

    /**
     * Builds a response spec with validation for a specific field in the response body
     */
    public static ResponseSpecification getSpecWithBodyCheck(String key, Object expectedValue){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType("application/json")
                .expectBody(key,equalTo(expectedValue))
                .build();
    }
}
