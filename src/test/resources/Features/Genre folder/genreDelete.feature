Feature: Deleting a genre 

  Background:
  
    Given I am on the genre page

  Scenario:
  
    When I click delete genre
    And I enter the genre "rave"
    And click the confirm delete button
    Then I have successfully deleted a genre

 