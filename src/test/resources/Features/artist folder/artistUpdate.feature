Feature: Updating an artist 

  Background:
  
    Given I am on the artist page

  Scenario:
  
    When I click update artist
    And Enter the current artist name "The Rolling Stones"
    And Enter the new artist name "Beyonce"
    And Click the update artist button
    And click the confirm update artist button
    Then I have successfully updated an artist

 