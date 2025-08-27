Feature: Get boards
  I want to get access to all my boards

  Scenario: Check Get Boards
    Given request with authorization
    And the request has query params:
      | fields | id,name |
    And the request has path params:
      | name | value                |
      | id   | zavada1997@gmail.com |
    When the 'GET' request is sent to '/members/{id}/boards' endpoint
    Then the response status code is 200


  Scenario: Check Get Board
    Given request with authorization
    And the request has query params:
      | fields | id,name |
    And the request has path params:
      | name | value                    |
      | id   | 68499967310c9b0128bb22c1 |
    When the 'GET' request is sent to '/boards/{id}' endpoint
    Then the response status code is 200
    And the response time is less than 3000
    And body value has the following values by paths:
      | path | expected_value |
      | name | доска          |
    And the jsonSchema is 'get_board.json' valid