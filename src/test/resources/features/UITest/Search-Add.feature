@UITest
Feature: Mythical Mysfits Eshop UI  search feature

  @Search
  Scenario Outline: Search Product
    Given User launched eshop login page in "Web"
    When User logged in eshop using the valid emailid "<EmailID>" and the valid password "<Password>"
    And User searches for the "<Product>"
    Then User should be able to view the listed product "<Product>"

    Examples:
      | EmailID           | Password  | Product                       |
      | testuser@shop.com | Testing$1 | Apple iPhone 6s Plus          |
      | testuser@shop.com | Testing$1 | Apple iPhone XR               |
      | testuser@shop.com | Testing$1 | Fitbit Versa                  |
      | testuser@shop.com | Testing$1 | Samsung Galaxy Note9          |
      | testuser@shop.com | Testing$1 | Apple Watch Series 4 Aluminum |

  @cart
  Scenario Outline: Add product to the Cart
    Given User launched eshop login page in "Web"
    When User logged in eshop using the valid emailid "<EmailID>" and the valid password "<Password>"
    And User searches for the "<Product>"
    Then User should be able to add the  "<Product>" to the cart

    Examples:
      | EmailID           | Password  | Product              |
      | testuser@shop.com | Testing$1 | Apple iPhone 6s Plus |