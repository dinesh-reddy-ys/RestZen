Feature: Booking API Performance
#
#  Background:
#    Given the base URL is "https://restful-booker.herokuapp.com"
#    And the endpoint is "/booking"
#
#  Scenario: Response time for basic request
#    When I send a GET request to the booking endpoint
#    Then the response time should be less than 2000 milliseconds
#
#  Scenario: Handle multiple concurrent requests
#    When I send 10 concurrent GET requests to the booking endpoint
#    Then all responses should have status code 200
#    And the average response time should be less than 3000 milliseconds