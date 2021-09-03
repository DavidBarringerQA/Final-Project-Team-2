Feature: Deleting a playlist

  Background:
  
    Given I am on the playlist page

  Scenario:
  Then I click delete playlist
  And Enter the name of the playlist i want to delete "Workout playlist"
  And Click delete playlist
  And Click confirm delete playlist
  Then I have successfully deleted a playlist