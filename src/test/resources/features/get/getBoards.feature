Feature: Get boards
  I want to get access to all my boards

  Background: request with authorization and query params
    Given request with authorization
    And the request has query params:
      | fields | id,name |

  Scenario: Check Get Boards
    And the request has path params:
      | name | value                |
      | id   | zavada1997@gmail.com |
    When the 'GET' request is sent to 'GET_ALL_BOARDS' endpoint
    Then the response status code is 200


  Scenario: Check Get Board
    And the request has path params:
      | name | value                    |
      | id   | 68499967310c9b0128bb22c1 |
    When the 'GET' request is sent to 'GET_A_BOARD' endpoint
    Then the response status code is 200
    And the response time is less than 3000
    And body value has the following values by paths:
      | path | expected_value |
      | name | доска          |
    And the response matches 'get_board.json' schema