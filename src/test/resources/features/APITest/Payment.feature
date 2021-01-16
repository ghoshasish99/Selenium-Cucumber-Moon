@APITest
Feature: Mythical Mysfits E-Shop Payment
  As a user, I want to validate the details

  Scenario: POST Payment Service Positive
    Given  An API endpoint for Payment
    And User set request body
    And User send POST HTTP request
    Then User receive HTTP response code "200"
    And Response body contains all the expected fields


  Scenario: POST Payment Service Negative
    Given An API endpoint for Payment
    And User set invalid request body
    And User send POST HTTP request
    Then User receive HTTP response code "400"


