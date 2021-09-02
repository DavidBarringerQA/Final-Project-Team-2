Feature: create a user

  Background:
  
    Given I am on the sign-up page

  Scenario:
  
    When I add a username "newuser"
    And Enter a password "password"
    And Enter confirmed password "password"
    And Click create user
    Then i have successfully created a new user
