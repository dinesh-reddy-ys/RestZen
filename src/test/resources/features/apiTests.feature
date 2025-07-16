Feature: API Testing with Rest Assured
#
#   Scenario: Verify the Health Check Endpoint
#     Given The base URI is "https://restful-booker.herokuapp.com"
#     When I send a GET request to "/ping"
#     Then The response status code should be 201