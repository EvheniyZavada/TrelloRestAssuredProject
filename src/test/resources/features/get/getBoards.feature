Feature: Get boards
  I want to get access to all my boards

  Scenario: Check get boards
    Given request with authorization
    And the request has query params:
    | fields | id,name |
    And the request has path params:
    | name | value |
    | id | zavada1997@gmail.com |
    When the 'GET' request is sent to '/members/{id}/boards' endpoint
    Then the response status code is 200