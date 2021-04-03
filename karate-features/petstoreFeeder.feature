Feature: Petstore
  Background:
    * def pet_id = karate.get('__gatling.petId', '1')

  Scenario: Get pet by id
    Given url 'http://host.docker.internal:9999/pets/' + pet_id
    When method get
    Then status 200