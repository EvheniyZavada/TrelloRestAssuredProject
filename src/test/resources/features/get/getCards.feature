Feature: Get cards

  Scenario: Check get cards
    #get all lists of the board
    Given request with authorization
    And the request has path params:
      | name | value                    |
      | id   | 684497a8f96fc4515a307c4d |
    When the 'GET' request is sent to 'GET_ALL_LISTS' endpoint
    Then the response status code is 200
    #get all cards from the list
    Given request with authorization
    And the request has path params:
      | name | value                    |
      | id   | 684497a8f96fc4515a307c9f |
    When the 'GET' request is sent to 'GET_ALL_CARDS' endpoint
    Then the response status code is 200

  Scenario: Check get a card
    Given request with authorization
    And the request has path params:
      | name | value                    |
      | id   | 68345fadb7caea7fdd9104e3 |
    When the 'GET' request is sent to 'GET_A_CARD' endpoint
    Then the response status code is 200
    And the response time is less than 3000
    And body value has the following values by paths:
      | path | expected_value |
      | desc | NONE           |
    And the response matches 'get_card.json' schema