
Feature: Read an album

  Background:
  
    Given I am on the album page

  Scenario:
  
    When I click the search bar and enter the album name "AM"
    And I click the search album button
    Then The album will appear in the search results

 