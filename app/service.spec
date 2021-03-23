Feature: Contract for the petstore service

  Scenario: Should be able to get a pet by petId
    When GET /pets/(petid:number)
    Then status 200
    And response-body {petid: "(number)"}