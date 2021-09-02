Feature: Creating an Album

  Background:
  
    Given I am on the album page

  Scenario:
  
    When I click add album
    And Enter the album name "Favourite Worst Nightmare"
    And click the artist dropdown 
    And choose an artist 
    And click the genre dropdown
    And choose a genre
    And click the create album button
    And click the confirm album button
    Then I refresh the page and have successfully created an album