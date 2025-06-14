Feature: Booking API Security

  Background:
    Given the base URL is "https://restful-booker.herokuapp.com"
    And the endpoint is "/booking"

  Scenario: Verify CORS headers
    When I send a GET request with CORS headers
    Then the response should include appropriate CORS headers

  Scenario: Verify request without protocol
    When I send a GET request using HTTP instead of HTTPS
    Then the request should be redirected to HTTPS