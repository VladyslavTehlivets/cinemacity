Feature: Tickets reservation

  Scenario Outline: The one whe the customer reserves a single seat
    Given cinema <cinemaName> in <cityName> is defined
    And movie <movieName> is defined
    And show <showName> in the cinema has 25 PLN ticket cost and played at <dateTime>
    When I select cinema <cinemaName> in <cityName>
    And I select show <showName> at <dateTime>
    And I select seat 10 in row 5
    Then the cost of reservation is 25 PLN
    When I make a reservation
    Then the selected seat is reserved for me
    And I get an email with reservation confirmation

    Examples:
      | dateTime           | showName   | movieName  | cinemaName | cityName
      | "2019-05-01 20:00" | "Smolensk" | "Smolensk" | "Plaza"    | "Lublin"

  Scenario Outline: The one whe the customer reserves 2 seats
    Given cinema <cinemaName> in <cityName> is defined
    And movie <movieName> is defined
    And show <showName> in the cinema has 25 PLN ticket cost and played at <dateTime>
    When I select cinema <cinemaName> in <cityName>
    And I select show <showName> at <dateTime>
    And I select seat 10 in row 5
    And I select seat 11 in row 5
    Then the cost of reservation is 50 PLN
    And I make a reservation
    Then the selected seat is reserved for me
    And I get an email with reservation confirmation

    Examples:
      | dateTime           | showName   | movieName  | cinemaName | cityName
      | "2019-05-01 20:00" | "Smolensk" | "Smolensk" | "Plaza"    | "Lublin"

  Scenario Outline: The one when Customer sees occupied seats
    Given cinema <cinemaName> in <cityName> is defined
    And movie <movieName> is defined
    And show <showName> in the cinema has 25 PLN ticket cost and played at <dateTime>
    And seat 10 in row 5 is reserved for <forName>
    When I select cinema <cinemaName> in <cityName>
    And I select show <showName> at <dateTime>
    Then I see that seat 10 in row 5 is already reserved

    Examples:
      | dateTime           | showName   | movieName  | cinemaName | cityName | forName
      | "2019-05-01 20:00" | "Smolensk" | "Smolensk" | "Plaza"    | "Lublin" | "Vladyslav"