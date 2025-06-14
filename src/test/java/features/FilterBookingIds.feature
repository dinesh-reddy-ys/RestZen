Feature: Filtering Booking IDs

  Background:
    Given the base URL is "https://restful-booker.herokuapp.com"
    And the endpoint is "/booking"

  Scenario: Filter bookings by firstname
    When I send a GET request with firstname parameter "John"
    Then the response status code should be 200
    And all returned bookings should have firstname "John"

  Scenario: Filter bookings by lastname
    When I send a GET request with lastname parameter "Smith"
    Then the response status code should be 200
    And all returned bookings should have lastname "Smith"

  Scenario: Filter bookings by checkin date
    When I send a GET request with checkin date "2024-01-01"
    Then the response status code should be 200
    And all returned bookings should have checkin date on or after "2024-01-01"

  Scenario: Filter bookings by checkout date
    When I send a GET request with checkout date "2024-12-31"
    Then the response status code should be 200
    And all returned bookings should have checkout date on or before "2024-12-31"