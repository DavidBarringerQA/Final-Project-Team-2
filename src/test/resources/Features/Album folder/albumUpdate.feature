Feature: Updating an album

  Background:
  
    Given I am on the album page

  Scenario:
  
    When I click update album
    And Enter current album "Over it"
    And Enter the updated album "Last day of summer"
    And Click the artist dropdown
    And Choose an artist
    And Click the genre dropdown
    And Choose the genre
    And click the update albums button
    And click the confirm update album
    Then I have successfully updated an album

 