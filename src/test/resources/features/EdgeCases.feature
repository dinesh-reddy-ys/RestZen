#Feature: Booking API Edge Cases
#
#  Background:
#    Given the base URL is "https://restful-booker.herokuapp.com"
#    And the endpoint is "/booking"
#
#  Scenario: Handle invalid date format in filter
#    When I send a GET request with checkin date "invalid-date"
#    Then the response status code should be 400
#    And the response should contain an appropriate error message
#
#  Scenario: Handle non-existent name in filter
#    When I send a GET request with firstname "NonExistentName123456"
#    Then the response status code should be 200
#    And the response should be an empty array
#
#  Scenario: Verify response with multiple filters
#    When I send a GET request with the following filters:
#      | firstname | John  |
#      | lastname  | Smith |
#    Then the response status code should be 200
#    And all returned bookings should match all filter criteria