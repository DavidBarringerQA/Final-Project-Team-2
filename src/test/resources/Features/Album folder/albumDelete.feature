Feature: Deleting an album

  Background:
  
    Given I am on the album page

  Scenario:
  
    When I click delete album
    And I enter the album "Tattoo You"
    And click the confirm delete album button
    Then I have successfully deleted the album

 