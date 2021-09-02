Feature: Updating a playlist

  Background:
  
    Given I am on the playlist page

  Scenario:
  When I click update playlist
  And Insert the name of the playlist i want to update "Morning Playlist"
  And Enter a new playlist name "Party playlist"
  And Enter a playlist description "songs to celebrate"
  And Click the update playlist button
  And Click the confirm update playlist button
  Then I have updated a playlist