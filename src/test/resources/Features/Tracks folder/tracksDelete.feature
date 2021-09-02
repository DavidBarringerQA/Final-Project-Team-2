Feature: Deleting a track

  Background:
  
    Given I am on the tracks page

  Scenario:
   And i click delete track
   And Enter the name of the track i want to delete "Clash"
   And click confirm track delete
   Then i have deleted a track