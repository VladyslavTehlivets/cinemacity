Feature: Cashier functions

  Scenario: The one where cashier accept reservation as payed by person
    Given now is "2019-05-01 17:00"
    Given show "Chornobyl" at "20:00" today
    Given 5-th seat at 5-th row on show "Czornobyl" is reserved by person with first name "Vladyslav" and second name "Tehlivets"
    When this person pay to cashier
    Then the cashier mark 5-th seat at 5-th row as "payed"

  Scenario: The one where cashier reject reservation because of not payed by person in 30 min before the show
    Given show "Chornobyl" at "20:00" today
    And now is 30 min before given show
    And 5-th seat at 5-th row on show "Czornobyl" is reserved by person with first name "Vladyslav" and second name "Tehlivets"
    When person is not pay to cashier
    Then the cashier mark 5-th seat at 5-th row as "not reserved"

  Scenario: The one where cashier marks tickets as payed when person pay near the cashier window
    Given show "Chornobyl"
    And 6-th seat at 5-th row on show "Chornobyl" is "not reserved" yet
    When person pay for cashier and order this place
    Then the cashier mark 6-th seat at 5-th row as "payed"