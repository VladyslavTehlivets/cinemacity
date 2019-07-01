Feature: Cinema administration

  Scenario: The one where the administrator defines cinema
    When administrator create the cinema "Plaza" in the city "Lublin"
    Then cinema "Plaza" in the city "Lublin" is created

  Scenario: The one where the administrator defines film
    When administrator create the film "Czornobyl"
    Then film "Czornobyl" is created

  Scenario: The one where the administrator defines show
    Given cinema "Plaza" in the city "Lublin" is defined
    And film "Czornobyl" is defined
    When administrator create show "Czornobyl" with given film, one ticket to which costs 25 PLN in given cinema
    Then one ticket to this show in given cinema costs 25 PLN

  Scenario: The one where the administrator can add different ticket types to show
    Given show "Czornobyl" is defined in cinema and normaly costs 25 PLN
    When administrator add "STUDENT_TICKET" wich costs 18 PLN to show
    Then show's "NORMAL_TICKET" costs 25 PLN
    And show's "STUDEN_TICKET" costs 18 PLN
