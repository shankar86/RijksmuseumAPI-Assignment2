#Author: shankaragouda Patil
@smoke
Feature: Rijksmuseum Collection Details APIs Test

  Scenario: TestCase-1 Verify HTTP 200 Response for Collection Detail API for Rijksmuseum with valid API Key
    Given CollectionAPI resource url
      | RESOURCE URL | /api/nl/collection/SK-C-5?key=0fiuZFh4 |
    And Request headers
      | Accept | application/json |
    When I make collection api call
    Then I verify response status code is 200
    And Verify response must have value 'elapsedMilliseconds'
    And Verify response must have value 'artObject'
    And Verify responseBody must have value 'http://www.rijksmuseum.nl/api/nl/collection' Under 'artObject.links.search'
    And Verify responseBody must have value 'nl-SK-C-5' Under 'artObject.id'
    And Verify responseBody must have value '5216' Under 'artObject.priref'
    And Verify responseBody must have value 'SK-C-5' Under 'artObject.objectNumber'
    And Verify responseBody must have value 'nl' Under 'artObject.language'
    And Verify responseBody must have value 'De Nachtwacht' Under 'artObject.title'
    And Verify responseBody must have value 'null' Under 'artObject.copyrightHolder'

  Scenario: TestCase-2 Verify HTTP 401 Response for Collection Detail API for Rijksmuseum when api-key in invalid
    Given CollectionAPI resource url
      | RESOURCE URL | /api/nl/collection/SK-C-5?key=0fiuZFh411 |
    And Request headers
      | Accept | application/json |
    When I make collection api call
    Then I verify response status code is 401
    And Verify response must have value 'Invalid key'
