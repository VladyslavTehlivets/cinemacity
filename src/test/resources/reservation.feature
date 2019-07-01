Feature: Tickets reservation

  Background:
    Given now is "2019-05-01 15:00"

  Scenario: The one whe the customer reserves a single seat
    Given cinema "Plaza" in "Lublin" is defined
    And movie "Smoleńsk" is defined
    And show "Smoleńsk" in the cinema has 25 PLN ticket cost
    When I select cinema "Plaza" in "Lublin"
    And I select show at "2019-05-01 20:00"
    And I select seat 10 in row 5
    Then the cost of reservation is 25 PLN
    When I make a reservation
    Then the selected seat is reserved for me
    And I get an email with reservation confirmation

  Scenario: The one whe the customer reserves 2 seats
    Given cinema "Plaza" in "Lublin" is defined
    And movie "Smoleńsk" is defined
    And show "Smoleńsk" in the cinema has 25 PLN ticket cost
    When I select cinema "Plaza" in "Lublin"
    And I select show at "2019-05-01 20:00"
    And I select seat 10 in row 5
    And I select seat 11 in row 5
    Then the cost of reservation is 50 PLN
    And I make a reservation
    Then the selected seat is reserved for me
    And I get an email with reservation confirmation

  Scenario: The one when Customer sees occupied seats
    Given cinema "Plaza" in "Lublin" is defined
    And movie "Smoleńsk" is defined
    And show "Smoleńsk" in the cinema has 25 PLN ticket cost
    And seat 10 in row 5 is reserved for "Smoleńsk"
    When I select cinema "Plaza" in "Lublin"
    And I select movie "Smoleńsk"
    And I select show at "2019-05-01 20:00"
    Then I see that seat 10 in row 5 is already reserved