Feature: Delete the board

  @createBoard
  Scenario: Check delete board
    Given request with authorization
    And the request has path params:
      | name | value            |
      | id   | created_board_id |
    When the 'DELETE' request is sent to 'DELETE_A_BOARD' endpoint
    Then the response status code is 200
    #проверка что доски нет
    Given request with authorization
    And the request has path params:
      | name | value                |
      | id   | zavada1997@gmail.com |
    When the 'GET' request is sent to 'GET_ALL_BOARDS' endpoint
    Then the response status code is 200
    And body value has not the following values by paths:
      | id   | created_board_id |
