Feature: Reading a track

  Background:
  
    Given I am on the tracks page

 

  Scenario:

    When I click the search bar and enter the track "clash"
    And I click the search track button
    Then The track will appear in the search results
  