Feature: Read an artist 

  Background:
  
    Given I am on the artist page

  Scenario:
  
    When I click the search bar and enter name "Summer Walker"
    And I click artist search
    Then The artist will appear in the search results

 