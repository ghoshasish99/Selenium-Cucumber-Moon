@UIRWDTest
Feature: Mythical Mysfits Eshop UI payment feature

 Scenario Outline: E-Shop Application - Create New Account
     Given User launched eshop login page in "<Device>"
     When User create account with "<FirstName>", "<LastName>", "<EmailID>" and "<Password>"
     Then User account should get created

     Examples:
       | Device    | FirstName | LastName | EmailID           | Password  |
       | iPad      | test      | user     | testuser@shop.com | Testing$1 |

  @Payment
  Scenario Outline: Order a product
    Given User launched eshop login page in "<Device>"
    When User logged in eshop using the valid emailid "<EmailID>" and the valid password "<Password>"
    And User searches for the "<Product>"
    And User adds the  "<Product>" to the cart
    And User Address details with "<Title>","<FirstName>", "<LastName>", "<Line1>","<Line2>","<City>","<State>", "<Zipcode>"
    And User Payment details with "<CardNumber>", "<CardName>", "<Year>","<Month>","<SecurityCode>"
    Then User should get the Confirmation of Order

    Examples:
      | Device    | EmailID           | Password  | Product              | Title | FirstName | LastName | Line1        | Line2         | City   | State       | Zipcode | CardNumber       | CardName | Year | Month | SecurityCode |
      | iPad      | testuser@shop.com | Testing$1 | Apple iPhone 6s Plus | Mr    | Mohan     | Raj      | Queen Garden | Victoria Road | London | Lanarkshire | G50UH   | 1111111111111111 | Mohan    | 2025 | 12    | 232          |
      | Galaxy S5 | testuser@shop.com | Testing$1 | Apple iPhone 6s Plus | Mr    | Mohan     | Raj      | Queen Garden | Victoria Road | London | Lanarkshire | G50UH   | 1111111111111111 | Mohan    | 2025 | 12    | 232          |
      | Nexus 7   | testuser@shop.com | Testing$1 | Apple iPhone 6s Plus | Mr    | Mohan     | Raj      | Queen Garden | Victoria Road | London | Lanarkshire | G50UH   | 1111111111111111 | Mohan    | 2025 | 12    | 232          |
      | iPad Pro  | testuser@shop.com | Testing$1 | Apple iPhone 6s Plus | Mr    | Mohan     | Raj      | Queen Garden | Victoria Road | London | Lanarkshire | G50UH   | 1111111111111111 | Mohan    | 2025 | 12    | 232          |
