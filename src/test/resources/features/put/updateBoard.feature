Feature: Update boards
  I want to update one of my boards

  Scenario: Check Update Board
    #update board
    Given request with authorization
    And the request has headers:
      | Content-Type | application/json |
    And the request has path params:
      | name | value                    |
      | id   | 68345f6639f73183f84a5dfb |
    And the request has body params:
      | name | Updated_Board |
    When the 'PUT' request is sent to '/boards/{id}' endpoint
    Then the response status code is 200
    And body value has the following values by paths:
      | path | expected_value |
      | name | Updated_Board  |
    #check board name updated
    Given request with authorization
    And the request has query params:
      | fields | id,name |
    And the request has path params:
      | name | value                |
      | id   | zavada1997@gmail.com |
    When the 'GET' request is sent to '/members/{id}/boards' endpoint
    Then the response status code is 200
    And body value has one the following values by paths:
      | path | expected_value |
      | name | Updated_Board  |
