Feature: create a user

  Background:
  
    Given I am on the login page

  Scenario:
  
    When i type a username "newuser"
    And type password "password"
    And click log in
    Then i have signed into Choonz

 