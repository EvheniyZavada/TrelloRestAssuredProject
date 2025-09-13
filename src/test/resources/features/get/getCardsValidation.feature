Feature: Get card validation
  Check get cards with validation params

  Scenario Outline: Check get card with invalid id
    Given request with authorization
    And the request has path params:
      | name | value      |
      | id   | <id_value> |
    When the 'GET' request is sent to 'GET_A_CARD' endpoint
    Then the response status code is <status_code>
    And the response body is equal to '<error_message>'

    Examples:
      | id_value                   | status_code | error_message                         |
      | 68345fadb7caea7fdd9104e9   | 404         | The requested resource was not found. |
      | 68345fadb7caea7fdd9104e    | 400         | invalid id                            |
      | 68499967310c9b0128bb22c171 | 400         | invalid id                            |
      |                            | 400         | invalid id                            |

  Scenario Outline: Check get card with invalid auth
    Given request without authorization
    And the request has path params:
      | name | value                    |
      | id   | 68345fadb7caea7fdd9104e3 |
    And the request has query params:
      | key   | <key>   |
      | token | <token> |
    When the 'GET' request is sent to 'GET_A_CARD' endpoint
    Then the response status code is 401
    And the response body is equal to '<error_message>'

    Examples:
      | key                  | token                  | error_message                          |
      | empty_value          | empty_value            | unauthorized card permission requested |
      | current_user_key     | empty_value            | {"message":"missing scopes"}           |
      | empty_value          | current_user_token     | invalid key                            |
      | another_acc_user_key | another_acc_user_token | unauthorized card permission requested      |