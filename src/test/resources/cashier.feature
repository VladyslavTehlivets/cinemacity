Feature: Cashier functions

  Scenario Outline: The one where cashier accept reservation as payed by person
    Given show <showName> defined to be at <dateTime>
    And 5-th seat at 5-th row on show <showName> is reserved by person with first name <firstName> and last name <lastName>
    When this person pay to cashier
    Then the cashier mark 5-th seat at 5-th row as <reservationStatus>

    Examples:
      | showName              | dateTime           | firstName   | lastName    | reservationStatus
      | "Lublin history show" | "2019-05-01 20:00" | "Vladyslav" | "Tehlivets" | "RESERVED"

  Scenario Outline: The one where cashier reject reservation because of not payed by person in 30 min before the show
    Given show <showName> at <dateTime>
    And now is 30 min before given show
    And 5-th seat at 5-th row on show <showName> is reserved by person with first name <firstName> and last name <lastName>
    When person is not pay to cashier
    Then the cashier mark 5-th seat at 5-th row as <reservationStatus>

    Examples:
      | showName              | dateTime           | firstName   | lastName    | reservationStatus
      | "Lublin history show" | "2019-05-01 20:00" | "Vladyslav" | "Tehlivets" | "RESERVED"

  Scenario Outline: The one where cashier marks tickets as payed when person pay near the cashier window
    Given show <showName>
    And 6-th seat at 5-th row on show <showName> is <notReservedStatus> yet
    When person pay for cashier and order this place
    Then the cashier mark 6-th seat at 5-th row as <reservationStatus>

    Examples:
      | showName              | notReservedStatus | reservationStatus
      | "Lublin history show" | "NOT_RESERVED"    | "RESERVED"
