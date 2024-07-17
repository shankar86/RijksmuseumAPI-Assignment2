#Author: Shankaragouda Patil
@smoke
Feature: Rijksmuseum Collection Details APIs Test

  Scenario: TestCase-1 Verify HTTP 200 Response for Collection Image API
    Given CollectionAPI resource url
      | RESOURCE URL | api/nl/collection/SK-C-5/tiles?key=0fiuZFh4 |
    And Request headers
      | Accept | application/json |
    When I make collection api call
    Then I verify response status code is 200
    And Verify response must have value 'levels'
    And Verify responseBody must have key 'name' in JsonArray 'levels'
    And Verify responseBody must have key 'width' in JsonArray 'levels'
    And Verify responseBody must have key 'height' in JsonArray 'levels'

  Scenario: TestCase-2 Verify HTTP 401 Response for Collection Image API for Rijksmuseum when api-key in invalid
    Given CollectionAPI resource url
      | RESOURCE URL | /api/nl/collection/SK-C-5/tiles?key=0fiuZFh411 |
    And Request headers
      | Accept | application/json |
    When I make collection api call
    Then I verify response status code is 401
    And Verify response must have value 'Invalid key'
