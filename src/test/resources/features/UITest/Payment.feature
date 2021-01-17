@UITest
Feature: Mythical Mysfits Eshop UI  payment feature

  Scenario Outline: Order a product
    Given User launched eshop login page
    When User logged in eshop using the valid emailid "<EmailID>" and the valid password_2 "<Password>"
    And User searches for the "<Product>"
    And User adds "<Product>" product to the cart
    Then User should be able to view and add the listed product "<Product>"
    And User enters Address details with "<Title>","<FirstName>", "<LastName>", "<Line1>","<Line2>","<City>","<State>", "<Zipcode>"
    And User enters Payment details with "<CardNumber>", "<CardName>", "<Year>","<Month>","<SecurityCode>"
    Then User should get the Confirmation of Order

    Examples:
      | EmailID           | Password  | Product              | Title | FirstName  | LastName   | Line1        | Line2         | City      | State       | Zipcode | CardNumber       | CardName  | Year | Month | SecurityCode |
      | Ashish@shop.com   | Testing$1 | Apple iPhone 6s Plus | Mr    | Ashish     | Ghosh      | Queen Garden | Uilenstede    | Amsterdam | Netherlands | 1181    | 1234567890       | Ashish    | 2031 | 12    | 151          |
