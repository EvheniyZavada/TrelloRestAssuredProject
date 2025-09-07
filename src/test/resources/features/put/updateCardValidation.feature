Feature: Update card validation
  Check update cards with validation params

  Scenario Outline: Check update card with invalid id
    Given request with authorization
    And the request has path params:
      | name | value      |
      | id   | <id_value> |
    And the request has headers:
      | Content-Type | application/json |
    And the request has body params:
      | desc | new desc1 |
    When the 'PUT' request is sent to 'UPDATE_A_CARD' endpoint
    Then the response status code is 400
    And the response body is equal to 'invalid id'

    Examples:
      | id_value                  |
      |                           |
      | 123                       |
      | 68345fadb7caea7fdd9104e33 |
      | 68345fadb7caea7fdd9104e   |

  Scenario Outline: Check update card with invalid auth
    Given request without authorization
    And the request has path params:
      | name | value                    |
      | id   | 68345fadb7caea7fdd9104e3 |
    And the request has query params:
      | key   | <key>   |
      | token | <token> |
    And the request has headers:
      | Content-Type | application/json |
    And the request has body params:
      | name | new name |
    When the 'PUT' request is sent to 'UPDATE_A_CARD' endpoint
    Then the response status code is 401
    And the response body is equal to '<error_message>'

    Examples:
    | key              | token              | error_message                    |
    | empty_value      | empty_value        | {\"message\":\"missing scopes\"} |
    | current_user_key | empty_value        | {\"message\":\"missing scopes\"} |
    | empty_value      | current_user_token | invalid key                      |





