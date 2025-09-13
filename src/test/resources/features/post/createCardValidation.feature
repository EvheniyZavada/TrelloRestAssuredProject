Feature: Create card validation
  Check create cards with validation params

  Scenario Outline: Check create card with invalid auth
    Given request without authorization
    And the request has headers:
      | Content-Type | application/json |
    And the request has body params:
      | idList | 684497a8f96fc4515a307c9f |
      | name   | Cucumber_name            |
    And the request has query params:
      | key   | <key>   |
      | token | <token> |
    When the 'POST' request is sent to 'CREATE_A_CARD' endpoint
    Then the response status code is 401
    And the response body is equal to '<error_message>'

    Examples:
      | key                  | token                  | error_message                          |
      | empty_value          | empty_value            | {"message":"missing scopes"}           |
      | current_user_key     | empty_value            | {"message":"missing scopes"}           |
      | empty_value          | current_user_token     | invalid key                            |
      | another_acc_user_key | another_acc_user_token | unauthorized card permission requested |

  Scenario Outline: Check create card with invalid idList
    Given request with authorization
    And the request has headers:
      | Content-Type | application/json |
    And the request has body params:
      | idList | <idList> |
      | name   | <name>   |
    When the 'POST' request is sent to 'CREATE_A_CARD' endpoint
    Then the response status code is 400
    And the response body is equal to 'invalid value for idList'

    Examples:
      | idList                    | name     |
      | 684497a8f96fc4515a307c9ff | NEW CARD |
      | 684497a8f96fc4515a307c    | NEW CARD |
      | invalid_value             | NEW CARD |
      |                           | NEW CARD |
