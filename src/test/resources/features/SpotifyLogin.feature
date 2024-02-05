Feature: Spotify Web Application Negative And Positive Login Testing

  Background:
    Given I am on the Spotify Login Page

  Scenario: Login with empty credentials
    When I leave both credentials empty and click the Log In button
    Then I should see error messages for both fields

  Scenario: Login with incorrect credentials
    When I type incorrect credentials and click the Log In button
    Then I should see an error message for incorrect credentials

  Scenario: Login with correct credentials
    When I enter valid credentials ValidUsername and ValidPassword and click the Log In button
    Then My Profile Name should be displayed
