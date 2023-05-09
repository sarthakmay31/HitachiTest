@Interview
Feature: Automate crucial user workflows for https://global.hitachi-solutions.com/ site.

  @TC1
  Scenario: Verify user can successfully navigate to the site
    Given user launch the url in browser
    Then verify user is landed on hitachi solutions homepage

  @TC2
  Scenario Outline: Verify user can successfully search for keywords
    Given user launch the url in browser
    Then verify user is landed on hitachi solutions homepage
    And clicks on the search icon
    And provide a keyword as <keyword> to be searched
    When user clicks on search button
    Then verify search results are displayed

    Examples: 
      | keyword    |
      | automation |

  @TC3
  Scenario Outline: Verify user can successfully open search result
    Given user launch the url in browser
    Then verify user is landed on hitachi solutions homepage
    And clicks on the search icon
    And provide a keyword as <keyword> to be searched
    When user clicks on search button
    Then verify search results are displayed
    And verify user can successfully open returned search results

    Examples: 
      | keyword    |
      | automation |

  @TC4 @NegativeScenario
  Scenario: Verify user cannot navigate to the site with .in extension domain
    Given user launch the url with in extension domain

  @TC5 @NegativeScenario
  Scenario Outline: Verify no search results are found for invalid keywords
    Given user launch the url in browser
    Then verify user is landed on hitachi solutions homepage
    And clicks on the search icon
    And provide a keyword as <keyword> to be searched
    When user clicks on search button
    Then verify no search result message is displayed

    Examples: 
      | keyword                          |
      | 03920103019390129302103129039103 |

  @API-Search
  Scenario Outline: Verify search results through RESTAPI
    Given user provide a keyword as <keyword> to be searched with URI as <URI>
    Then verify stausCode as <statusCode>

    Examples: 
      | keyword    | statusCode | URI                                  |
      | automation |        200 | https://global.hitachi-solutions.com |
      | auto       |        200 | https://global.hitachi-solutions.com |

  @API-Search @NegativeSearch
  Scenario Outline: Verify search results through RESTAPI with invalid endpoint
    Given user provide a keyword as <keyword> to be searched with URI as <URI>
    Then verify stausCode as <statusCode>

    Examples: 
      | keyword    | statusCode | URI                                                  |
      | automation |        404 | https://global.hitachi-solutions.com/&post_type=page |
