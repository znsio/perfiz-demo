Feature: Petstore
  Scenario: Get pet by id
    Given url 'http://host.docker.internal:9999/pets/1'
    When method get
    Then status 200