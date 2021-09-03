Feature: Creating a genre

  Background:
  
    Given I am on the genre page

  Scenario:
  
    When I click add genre
    And Enter the genre "Dance music"
    And the genre description "catchy songs with an easy pop based structure"
    And click the confirm button
    Then I refresh the page and have successfully created a genre