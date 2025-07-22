Feature: Get Booking IDs API

  Background: 
    Given the base URL is "https://restful-booker.herokuapp.com"
    And the endpoint is "/booking"

   @Test
  Scenario: Get all booking IDs successfully
    When I send a GET request to the booking endpoint
    Then the response status code should be 200
    And the response should contain a list of booking IDs
    And the response content type should be "application/json"

  Scenario: Verify response structure for booking IDs
    When I send a GET request to the booking endpoint
    Then each booking ID in the response should be a valid integer