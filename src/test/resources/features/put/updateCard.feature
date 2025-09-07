Feature: Update boards
  I want to update one of my cards

  Scenario: Check update card
    #update card
    Given request with authorization
    And the request has path params:
      | name | value                    |
      | id   | 68345fadb7caea7fdd9104e3 |
    And the request has headers:
    | Content-Type | application/json |
    And the request has body params:
    | name | updatedNameOfCard |
    | desc | NONE              |
    When the 'PUT' request is sent to 'UPDATE_A_CARD' endpoint
    Then the response status code is 200
    And body value has the following values by paths:
      | path | expected_value    |
      | name | updatedNameOfCard |
    #check card name updated
    Given request with authorization
    And the request has path params:
      | name | value                    |
      | id   | 68345fadb7caea7fdd9104e3 |
    And the request has query params:
    | fields | id,name |
    When the 'GET' request is sent to 'GET_A_CARD' endpoint
    Then the response status code is 200
    And body value has the following values by paths:
      | path | expected_value    |
      | name | updatedNameOfCard |