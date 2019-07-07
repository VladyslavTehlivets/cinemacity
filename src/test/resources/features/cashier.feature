Feature: Cashier functions

  Scenario Outline: The one where cashier accept reservation as payed by person
    Given show <showName> defined to be at <time>
    And <seatColumn>-th seat at <seatRow>-th row on show <showName> is reserved by person with <userName>
    When this person pay to cashier
    And the cashier accepts reservation for <userName>
    Then reservation for <userName> is accepted

    Examples:
      | showName              | time    | userName    | seatColumn | seatRow
      | "Lublin history show" | "20:00" | "vladyslav" | 5          | 5

  Scenario Outline: The one where cashier reject reservation because of not payed by person in 30 min before the show
    Given show <showName> defined to start in half an hour
    And <seatColumn>-th seat at <seatRow>-th row on show <showName> is reserved by person with <userName>
    When the cashier rejects all not payed reservations
    Then reservation for <userName> is rejected

    Examples:
      | showName              | userName    | seatColumn | seatRow
      | "Lublin history show" | "vladyslav" | 5          | 5

  Scenario Outline: The one where cashier marks tickets as payed when person pay near the cashier window
    Given show <showName> is defined
    And <seatColumn>-th seat at <seatRow>-th row on show <showName> is not reserved yet
    When person pay for cashier and order this place
    And the cashier creates and accepts reservation of <seatRow> in <seatColumn> for <userName>
    Then reservation for <userName> is accepted

    Examples:
      | showName              | seatColumn | seatRow | userName
      | "Lublin history show" | 6          | 5       | "vladyslav"
