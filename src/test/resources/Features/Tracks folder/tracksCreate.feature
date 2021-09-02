Feature: Creating a track

  Background:
  
    Given I am on the tracks page

  Scenario:
  
    When I click add track
    And Enter the track name "Location"
    And Enter the track duration 212
    And Enter the track lyrics "If you send me the location then I'll be right there Let me come, come check you, my baby No time, no"
    And Click the drop down button
    And Click the album 
    And Click add track button
    And Click confirm add
    Then I have successfully created a track