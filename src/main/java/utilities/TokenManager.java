package utilities;

import io.restassured.response.Response;
import services.BookingService;

import static io.restassured.RestAssured.given;

public class TokenManager {



    public static String getFreshToken(){

        return generateToken();
    }

    private static String generateToken(){
        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType("application/json")
                .body("{\"username\":\"admin\",\"password\":\"password123\"}")
                .when()
                .post("/auth");
        return response.jsonPath().getString("token");



    }
}
