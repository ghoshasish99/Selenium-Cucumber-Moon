@APITest
Feature: Mythical Mysfits E-Shop Registration Positive

  Scenario Outline: POST Registration Positive
    Given  An API endpoint for Registration
    And User set request body with "<firstName>","<lastName>","<email>" and "<password>"
    And User send POST HTTP request
    Then User receive response code "200"

    Examples:
      | firstName | lastName | email           | password  |
      | Test      | User     | Test@User.com   | Password1 |
      | Sample    | Test     | Sample@Test.com | Password2 |




