@APITest
Feature: Mythical Mysfits E-Shop Products
  As a user, I want to validate the details

  @Smoke
  Scenario: Get All products
    Given An API endpoint for Products
    When User send GET HTTP request
    Then User receive valid HTTP response code "200"
    And Response body contains all the valid fields for all the products

  Scenario Outline: Get product by name
    Given An API endpoint for Products with "<productName>"
    When User send GET HTTP request
    Then User receive valid HTTP response code "200"
    And Response body contains all the valid fields for all the products

    Examples:
      | productName          |
      | Apple                |
      | Samsung              |
      | Fitbit               |
      | OnePlus              |
      | Apple iPhone 6s Plus |



