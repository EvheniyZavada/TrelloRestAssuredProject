Feature: Get boards validation
  Check get boards with validation params

  Scenario Outline: Check get board with invalid id
    Given request with authorization
    And the request has path params:
      | name | value      |
      | id   | <id_value> |
    When the 'GET' request is sent to '/boards/{id}' endpoint
    Then the response status code is <status_code>
    And the response body is equal to '<error_message>'

    Examples:
      | id_value                  | status_code | error_message                         |
      | 68499967310c9b0128bb22c7  | 404         | The requested resource was not found. |
      | 68499967310c9b0128bb22c   | 400         | invalid id                            |
      | 68499967310c9b0128bb22c17 | 400         | invalid id                            |
      |                           | 400         | invalid id                            |
