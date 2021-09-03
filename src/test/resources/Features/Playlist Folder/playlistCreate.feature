Feature: Creating a playlist

  Background:
  
    Given I am on the playlist page

  Scenario:
  
    When I click add playlist
    And Enter a playlist name ""
    And the playlist description "catchy songs"
    And Click the add button
    And click the confirm playlist button
    Then I refresh the page and have successfully created a playlist