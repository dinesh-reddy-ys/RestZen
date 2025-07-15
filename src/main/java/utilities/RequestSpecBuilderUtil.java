package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderUtil {

    public static RequestSpecification getFreshRequestSpecWithAuth(){
      String token = TokenManager.getFreshToken();
      System.out.println("Token from spec builder " + token);
       return new RequestSpecBuilder()
               .setBaseUri("https://restful-booker.herokuapp.com")
               .addHeader("Cookie","token=" + token)
               .setContentType("application/json")
               .build();

    }
}
