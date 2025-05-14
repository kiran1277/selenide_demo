# src/test/resources/features/login.feature

Feature: User Login
  As a user
  I want to log in to the application
  So that I can access my account

  Background:
    Given I am on the login page

  Scenario: Successful login with valid credentials
    When I enter username "testuser"
    And I enter password "testpassword"
    And I click the login button
    Then I should be redirected to the home page
    And I should see "Welcome John Smith" on the home page
  @database
  Scenario: Failed login with invalid credentials
    When I enter username "invaliduser"
    And I enter password "wrongpassword"
    And I click the login button
    Then I should see an error message "Invalid username or password"
    And I should remain on the login page

  @smoke
  Scenario: Login with empty username
    When I enter username ""
    And I enter password "somepassword"
    And I click the login button
    Then I should see an error message "Please enter a username and password."

  @regression
  Scenario: Login with empty password
    When I enter username "anotheruser"
    And I enter password ""
    And I click the login button
    Then I should see an error message "Please enter a username and password."

