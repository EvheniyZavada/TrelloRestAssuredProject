Feature: Create a board
  I want to create a new board

  Scenario: Check create a
    #создание новой доски
    Given request with authorization
    And the request has body params:
      | name | Cucumber_board |
    And the request has headers:
      | Content-Type | application/json |
    When the 'POST' request is sent to 'CREATE_A_BOARD' endpoint
    Then the response status code is 200
    And body value has the following values by paths:
      | path | expected_value |
      | name | Cucumber_board |
    #проверка что доска создалась GET запросом
    Given request with authorization
    And the request has query params:
      | fields | id,name |
    And the request has path params:
      | name | value     |
      | id   | USER_NAME |
    When the 'GET' request is sent to 'GET_ALL_BOARDS' endpoint
    Then the response status code is 200
    And body value has one the following values by paths:
      | name | Cucumber_board |

