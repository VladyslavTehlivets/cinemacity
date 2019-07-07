Feature: Tickets reservation

  Scenario Outline: The one whe the customer reserves a single seat
    Given cinema <cinemaName> in <cityName> is defined
    And movie <movieName> is defined
    And show <showName> in the cinema has <ticketCost> PLN ticket cost and played at <dateTime>
    When I select a cinema <cinemaName> in <cityName>
    And I select show <showName> at <dateTime>
    And I select seat <seatColumn> in row <seatRow>
    Then the cost of reservation is <expectedReservationCost> PLN
    When I make a reservation
    Then the selected seat is reserved for me
    And I get an email with reservation confirmation

    Examples:
      | dateTime           | showName   | movieName  | cinemaName | cityName | seatColumn | seatRow | ticketCost | expectedReservationCost |
      | "2019-05-01 20:00" | "Smolensk" | "Smolensk" | "Plaza"    | "Lublin" | 10         | 5       | 25         | 25                      |

  Scenario Outline: The one whe the customer reserves 2 seats
    Given cinema <cinemaName> in <cityName> is defined
    And movie <movieName> is defined
    And show <showName> in the cinema has <ticketCost> PLN ticket cost and played at <dateTime>
    When I select a cinema <cinemaName> in <cityName>
    And I select show <showName> at <dateTime>
    And I select seats <seatColumns> in row <seatRow>
    Then the cost of reservation is <expectedReservationCost> PLN
    And I make a reservation
    Then the selected seat is reserved for me
    And I get an email with reservation confirmation

    Examples:
      | dateTime           | showName   | movieName  | cinemaName | cityName | seatColumns | seatRow | ticketCost | expectedReservationCost |
      | "2019-05-01 20:00" | "Smolensk" | "Smolensk" | "Plaza"    | "Lublin" | "10,11"     | 5       | 25         | 50                      |

  Scenario Outline: The one when Customer sees occupied seats
    Given cinema <cinemaName> in <cityName> is defined
    And movie <movieName> is defined
    And show <showName> in the cinema has <ticketCost> PLN ticket cost and played at <dateTime>
    And seat <seatColumn> in row <seatRow> is reserved for <userName>
    When I select a cinema <cinemaName> in <cityName>
    And I select show <showName> at <dateTime>
    Then I see that seat <seatColumn> in row <seatRow> is not available for reservation

    Examples:
      | dateTime           | showName   | movieName  | cinemaName | cityName | userName    | seatColumn | seatRow | ticketCost |
      | "2019-05-01 20:00" | "Smolensk" | "Smolensk" | "Plaza"    | "Lublin" | "Vladyslav" | 10         | 5       | 25         |