Feature: Updating a track

  Background:
  
    Given I am on the tracks page

  Scenario:
  When I click update track
  And insert the name of the track i want to update "Butterfly Affect"
  And Enter the new track name "5% Tint"
  And Enter the duration 320
  And Enter the new track lyrics "Up late feelin' real chancy they outside really tryna end me (yeah) Who's that creeping through my window? (my window)"
  And Click the drop down
  And Choose an album 
  And Click update track
  And Click confirm track update
  Then you have updated a track