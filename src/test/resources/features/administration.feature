Feature: Cinema administration

  Scenario Outline: The one where the administrator defines cinema
    When administrator creates the cinema <cinemaName> in the city <cityName>
    Then cinema <cinemaName> in the city <cityName> is created
    Examples:
      | cinemaName | cityName |
      | "Plaza"    | "Lublin" |

  Scenario Outline: The one where the administrator defines movie
    When administrator creates the movie <movieName>
    Then movie <movieName> is created

    Examples:
      | movieName        |
      | "Lublin History" |

  Scenario Outline: The one where the administrator defines show
    Given cinema <cinemaName> in the city <cityName> is defined
    And movie with <movieName> is defined
    When administrator create show <showName> with given movie, one ticket to which costs <ticketPrice> PLN in given cinema at <givenTime>
    Then one ticket to this show in given cinema costs <ticketPrice> PLN

    Examples:
      | cinemaName | cityName | movieName        | showName              | givenTime | ticketPrice |
      | "Plaza"    | "Lublin" | "Lublin History" | "Lublin history show" | "20:00"   | 25          |

  Scenario Outline: The one where the administrator can add different ticket types to show
    Given show <showName> is defined in cinema and normally costs <ticketPrice> PLN
    When administrator add <ticketDiscount> which costs <percentFromNormalCost> % to show
    Then show's <ticketDiscount> costs <ticketPrice> PLN
    And show's <studentTicketDiscount> costs <studentTicketPrice> PLN
    Examples:
      | showName              | ticketDiscount  | studentTicketDiscount | ticketPrice | percentFromNormalCost | studentTicketPrice |
      | "Lublin history show" | "NORMAL_TICKET" | "STUDENT_TICKET"      | 25          | 80                    | 20                 |
