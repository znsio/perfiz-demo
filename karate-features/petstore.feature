Feature: Petstore
  Background:
    * url urlBase

  Scenario: Get pet by id
    Given path '/pets/1'
    When method get
    Then status 200