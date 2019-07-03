Feature: Cinema administration

  Scenario Outline: The one where the administrator defines cinema
    When administrator creates the cinema <cinemaName> in the city <cityName>
    Then cinema <cinemaName> in the city <cityName> is created
    Examples:
      | cinemaName | cityName
      | "Plaza"    | "Lublin"

  Scenario Outline: The one where the administrator defines movie
    When administrator creates the movie <movieName>
    Then movie <movieName> is created

    Examples:
      | movieName
      | "Lublin History"

  Scenario Outline: The one where the administrator defines show
    Given cinema <cinemaName> in <cityName> is defined
    And movie <movieName> is defined
    When administrator create show <showName> with given movie, one ticket to which costs 25 PLN in given cinema
    Then one ticket to this show in given cinema costs 25 PLN

    Examples:
      | cinemaName | cityName | movieName         | showName
      | "Plaza"    | "Lublin" | "Lublin History" | "Lublin history show"

  Scenario Outline: The one where the administrator can add different ticket types to show
    Given show <showName> is defined in cinema and normally costs 25 PLN
    When administrator add <ticketPolicy> which costs 18 PLN to show
    Then show's <ticketPolicy> costs 25 PLN
    And show's <studentTicketPolicy> costs 18 PLN
    Examples:
      | showName              | ticketPolicy    | studentTicketPolicy
      | "Lublin history show" | "NORMAL_TICKET" | "STUDENT_TICKET"
