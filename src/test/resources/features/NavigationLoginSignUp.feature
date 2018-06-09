
Feature: I should be able to Navigate to Sign Up page from Different places

  Scenario: I should be able go to Sign Up page from Default explore page
    Given  I am on ML home page
    When   I click on Sign Up button on Default Explore page
    Then   I should see sign up page

  Scenario: I should be able go to Sign Up page from Login page using Sign up Button at header
    Given  I am on Login page
    When   I click on Sign Up button on Login page
    Then   I should see sign up page

  Scenario: I should be able go to Sign Up page from Login page using Sign up link text at footer
    Given  I am on Login page
    When   I click on Sign Up link text on login page footer
    Then   I should see sign up page

  Scenario: I should be able go to Login page from Default explore page
    Given  I am on ML home page
    When   I click on Login button on Default Explore page
    Then   I should see the Login page

  Scenario: I should be able go to Login page from Sign Up page with Login Button at Header
    Given I am on sign up page
    When  I click on Login button on Sign Up page in the header
    Then  I should see the Login page

  Scenario: I should be able go to Login page from Sign Up page with Login Link text at footer
    Given I am on sign up page
    When  I click on Login link text on Sign Up page in the footer
    Then  I should see the Login page
