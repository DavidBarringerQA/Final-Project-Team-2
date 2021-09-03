Feature: Deleting an artist 

  Background:
  
    Given I am on the artist page

  Scenario:
  
    When I click delete artist
    And Enter an artist name "Travis Scott"
    And click the confirm deletion button
    Then I have successfully deleted an artist

 