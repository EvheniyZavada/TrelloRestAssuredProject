Feature: Get boards validation
  Check get boards with validation params

  Scenario Outline: Check get board with invalid id
    Given request with authorization
    And the request has path params:
      | name | value      |
      | id   | <id_value> |
    When the 'GET' request is sent to 'GET_A_BOARD' endpoint
    Then the response status code is <status_code>
    And the response body is equal to '<error_message>'

    Examples:
      | id_value                  | status_code | error_message                         |
      | 68499967310c9b0128bb22c7  | 404         | The requested resource was not found. |
      | 68499967310c9b0128bb22c   | 400         | invalid id                            |
      | 68499967310c9b0128bb22c17 | 400         | invalid id                            |
      |                           | 400         | invalid id                            |

  Scenario Outline: Check get  board with invalid authorization
    Given request without authorization
    And the request has path params:
      | name | value                    |
      | id   | 68499967310c9b0128bb22c1 |
    And the request has query params:
      | key   | <key>   |
      | token | <token> |
    When the 'GET' request is sent to 'GET_A_BOARD' endpoint
    Then the response status code is 401
    And the response body is equal to '<error_message>'

    Examples:
      | key                  | token                  | error_message                     |
      | empty_value          | empty_value            | unauthorized permission requested |
      | current_user_key     | empty_value            | {"message":"missing scopes"}      |
      | empty_value          | current_user_token     | invalid key                       |
      | another_acc_user_key | another_acc_user_token | unauthorized permission requested |
