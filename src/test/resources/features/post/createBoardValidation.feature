Feature: Create board validation
  Check create boards with validation params

  Scenario Outline: Check create board with invalid id
    Given request with authorization
    And the request has headers:
      | Content-Type | application/json |
    And the request has body params:
      | <body_key> | <body_value> |
    When the 'POST' request is sent to 'CREATE_A_BOARD' endpoint
    Then the response status code is 400
    And the response body is equal to '<error_message>'

    Examples:
      | body_key | body_value | error_message                                        |
      | name     |            | {"message":"invalid value for name","error":"ERROR"} |
      | ''       | qwe        | {"message":"invalid value for name","error":"ERROR"} |
      | ''       | ''         | {"message":"invalid value for name","error":"ERROR"} |

  Scenario Outline: Check create board with invalid auth
    Given request without authorization
    And the request has query params:
      | key   | <key>   |
      | token | <token> |
    And the request has headers:
      | Content-Type | application/json |
    And the request has body params:
      | name | will_not_exist |
    When the 'POST' request is sent to 'CREATE_A_BOARD' endpoint
    Then the response status code is 401
    And the response body is equal to '<error_message>'

    Examples:
      | key                  | token                  | error_message                          |
      | empty_value          | empty_value            | {"message":"missing scopes"}           |
      | current_user_key     | empty_value            | {"message":"missing scopes"}           |
      | empty_value          | current_user_token     | invalid key                            |


