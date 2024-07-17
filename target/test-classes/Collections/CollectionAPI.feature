#Author: Shankaragouda Patil
@smoke
Feature: Rijksmuseum Collection APIs Test
@test1
  Scenario: TestCase-1 Verify HTTP 200 Response for Collection API for Rijksmuseum with valid API Key
    Given CollectionAPI resource url
      | RESOURCE URL | /api/nl/collection?key=0fiuZFh4&involvedMaker=Rembrandt van Rijn |
    And Request headers
      | Accept | application/json |
    When I make collection api call
    Then I verify response status code is 200
    And Verify response must have value 'elapsedMilliseconds'
    And Verify response must have value 'count'
    And Verify response must have value 'countFacets'
    And Verify response must have value 'artObjects'
    And Verify response must have value 'facets'
    And Verify responseBody must have link 'http://www.rijksmuseum.nl/api/nl/collection/SK-A-4050' and objectNumber 'SK-A-4050'
    And Verify responseBody must have link 'http://www.rijksmuseum.nl/api/nl/collection/SK-A-4691' and objectNumber 'SK-A-4691'

  Scenario: TestCase-2 Verify HTTP 401 Response for Collection API for Rijksmuseum when api-key in invalid
    Given CollectionAPI resource url
      | RESOURCE URL | /api/nl/collection?key=0fiuZFh41&involvedMaker=Rembrandt van Rijn |
    And Request headers
      | Accept | application/json |
    When I make collection api call
    Then I verify response status code is 401
    And Verify response must have value 'Invalid key'


  
