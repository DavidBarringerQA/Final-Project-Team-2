Feature: Reading a playlist

  Background:
  
    Given I am on the playlist page

  Scenario:

    When I click the search bar and enter the playlist "Summertime Playlist"
    And I click the search playlist button
    Then The playlist will appear in the search results
  