Feature: Create card

  @deleteCard
  Scenario: Check create card
    Given request with authorization
    And the request has headers:
      | Content-Type | application/json |
    And the request has body params:
      | idList | 684497a8f96fc4515a307c9f |
      | name   | Cucumber_card_name       |
    When the 'POST' request is sent to 'CREATE_A_CARD' endpoint
    Then the response status code is 200
    And body value has the following values by paths:
      | path | expected_value     |
      | name | Cucumber_card_name |
    And the card id from the response is remembered
    # проверка созданой карты
    Given request with authorization
    And the request has path params:
      | name | value                    |
      | id   | 68c4674968316aa4ffb791a5 |
    When the 'GET' request is sent to 'GET_A_CARD' endpoint
    Then the response status code is 200
    And body value has the following values by paths:
      | path | expected_value     |
      | name | Cucumber_card_name |
