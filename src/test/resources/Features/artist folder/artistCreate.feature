Feature: Creating an artist 

  Background:
  
    Given I am on the artist page

  Scenario:
  
    When I click add artist
    And Enter the name "Ariana Grande"
    And click the confirm add button
    Then I refresh the page and have successfully created an artist

 