# Created by vladyslav at 14/05/19
Feature: Tickets reservation

  Background:
    Given now is "2019-05-01 15:00"

  Scenario: The one whe the customer reserves a single seat
    Given cinema "Plaza" in "Lublin" is defined
    And movie "Smoleńsk" is defined
    And show "Smoleńsk" is played in cinema "Plaza" in "Lublin"
    And the ticked cost is 25 PLN
    When I select cinema "Plaza" in "Lublin"
    And I select show at "2019-05-01 20:00"
    And I select 1 tickets
    Then the cost of reservation is 25 PLN
    When I select seat 10 in row 5
    And I make a reservation
    Then the selected seat is reserved for me
    And I get an email with reservation confirmation

  Scenario: The one whe the customer reserves 2 seats
    Given cinema "Plaza" in "Lublin" is defined
    And movie "Smoleńsk" is defined
    And show "Smoleńsk" is played in cinema "Plaza" in "Lublin"
    And the ticked cost is 25 PLN
    When I select cinema "Plaza" in "Lublin"
    And I select show at "2019-05-01 20:00"
    And I select 2 tickets
    Then the cost of reservation is 50 PLN
    When I select seat 10 in row 5
    When I select seat 11 in row 5
    And I make a reservation
    Then the selected seat is reserved for me
    And I get an email with reservation confirmation

  Scenario: The one when Customer sees occupied seats
    Given cinema "Plaza" in "Lublin" is defined
    And movie "Smoleńsk" is defined
    And show "Smoleńsk" is played in cinema "Plaza" in "Lublin"
    And the ticked cost is 25 PLN
    And seat 10 in row 5 is reserved for "Smoleńsk"
    When I select cinema "Plaza" in "Lublin"
    And I select movie "Smoleńsk"
    And I select show at "2019-05-01 20:00"
    Then I see that seat 10 in row 5 is already reserved