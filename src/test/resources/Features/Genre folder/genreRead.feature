Feature: Read a Genre

  Background:
  
    Given I am on the genre page

  Scenario:
  
    When I click the search bar and enter the genre "Rap"
    And I click the genre search button
    Then The genre will appear in the search results

 