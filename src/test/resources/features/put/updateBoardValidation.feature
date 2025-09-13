Feature: Update board validation
  Check update boards with validation params

  Scenario Outline: Check update board with invalid id
    Given request with authorization
    And the request has path params:
      | name | value      |
      | id   | <id_value> |
    And the request has headers:
      | Content-Type | application/json |
    And the request has body params:
      | name | new name |
    When the 'PUT' request is sent to 'UPDATE_A_BOARD' endpoint
    Then the response status code is <status_code>
    And the response body is equal to '<error_message>'

    Examples:
      | id_value                  | status_code | error_message |
      |                           | 400         | invalid id    |
      | id                        | 400         | invalid id    |
      | 123                       | 400         | invalid id    |
      | 68345f6639f73183f84a5dfbb | 400         | invalid id    |

  Scenario Outline: Check update board with invalid authorization
    Given request without authorization
    And the request has path params:
      | name | value                    |
      | id   | 68345f6639f73183f84a5dfb |
    And the request has query params:
      | key   | <key>   |
      | token | <token> |
    And the request has headers:
      | Content-Type | application/json |
    And the request has body params:
      | name | new_name |
    When the 'PUT' request is sent to 'UPDATE_A_BOARD' endpoint
    Then the response status code is 401
    And the response body is equal to '<error_message>'

    Examples:
      | key                  | token                  | error_message                     |
      | empty_value          | empty_value            | {\"message\":\"missing scopes\"}  |
      | current_user_key     | empty_value            | {\"message\":\"missing scopes\"}  |
      | empty_value          | current_user_token     | invalid key                       |
      | another_acc_user_key | another_acc_user_token | unauthorized permission requested |
