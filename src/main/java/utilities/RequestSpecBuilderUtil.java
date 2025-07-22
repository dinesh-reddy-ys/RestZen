package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderUtil {
    private static final String BASE_URI = "https://restful-booker.herokuapp.com";

    private static final String BASE_PATH = "/booking";

    public static RequestSpecification getFreshRequestSpecWithAuth(){
      String token = TokenManager.getFreshToken();
      System.out.println("Token from spec builder " + token);
       return new RequestSpecBuilder()
               //.addHeader("Cookie","token=" + token)
               .addHeader("Authorization","Bearer " + token)
               .setContentType("application/json")
               .build();

    }

    public static RequestSpecification withCheckinDate(String checkinDate){
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH)
                .addQueryParam("checkin",checkinDate)
                .build();
    }

    public static RequestSpecification withCheckoutDate(String checkoutDate){
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH)
                .addQueryParam("checkout",checkoutDate)
                .build();
    }

    public static RequestSpecification withFirstname(String firstname){
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH)
                .addQueryParam("firstname",firstname)
                .build();
    }

    public static RequestSpecification withLastname(String lastname){
        return new RequestSpecBuilder()
                .setBasePath(BASE_URI)
                .setBasePath(BASE_PATH)
                .addQueryParam("lastname",lastname)
                .build();

    }
    public static RequestSpecification withTotalPrice(String totalPrice){
        return new RequestSpecBuilder()
                .addQueryParam("totalprice",totalPrice)
                .build();
    }

    public static RequestSpecification withDepositePaid(String depositePaid){
        return new RequestSpecBuilder()
                .addQueryParam("depositepaid",depositePaid)
                .build();
    }

    public static RequestSpecification withAdditionalNeeds(String additionalNeeds){
        return new RequestSpecBuilder()
                .addQueryParam("additionalneeds",additionalNeeds)
                .build();
    }


}
