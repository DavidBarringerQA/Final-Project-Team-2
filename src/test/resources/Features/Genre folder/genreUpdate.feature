Feature: Updating a Genre 

  Background:
  
    Given I am on the genre page

  Scenario:
  
    When I click update genre
    And Enter current genre "Rock"
    And Enter the updated genre "Classical"
    And Enter the updated genre description "music that has been composed by musicians who are trained in the art of writing music"
    And click the update button
    And click the confirm update genre
    Then I have successfully updated a genre

 