@Complete

Feature: I should be able to gain access Micro learning application
  #ML 1.1.2 As a user, I should be able to gain access to the application after successful sign up

  Scenario Outline: I should be able to gain access to the application after successful sign up
    Given I am on ML home page
    When  I click on SignUp button in ML home page
    Then  I should see sign up page
    When  I enter name as "<Name>" in sign up page
    When  I enter email as "<Email>" in sign up page
    When  I enter password as "<Password>" in sign up page
    When  I enter re-type password as "<Re-type Password>" in sign up page
    When  I click on SignUp button in sign up page
    Then  I should see ML Logo in Header
    And   I should see Explore All Lesson Page
    And   I should see Profile thumbnail
    And   I should see Username "<name>"
    And   I should see the 'My Library' Icon with Label in the main navigator
    And   I should see the 'Explore' Icon with Label in the main navigator
    And   I should see the 'Authoring' Icon with Label in the main navigator
    When  I click on user name
    And   I click on the Logout button
    Then  I should see the Home Page

    Examples:
      | Name   | Email                | Password | Re-type Password |
      | Navin1 | navin12345@gmail.com | navin123 | navin123         |


  Scenario: I should be able to gain access to the application after successful login with gmail account Login Page
    Given I am on Login page
    Then  I click on Login with Google link in the Login page
    When  I click on the Allow button
    Then  I should see ML Logo in Header
    And   I should see Explore All Lesson Page
    And   I should see Profile thumbnail
    And   I should see Username "<name>"
    And   I should see the 'My Library' Icon with Label in the main navigator
    And   I should see the 'Explore' Icon with Label in the main navigator
    And   I should see the 'Authoring' Icon with Label in the main navigator
    When  I click on user name
    And   I click on the Logout button
    Then  I should see the Home Page

  Scenario: I should be able to gain access to the application after successful login with gmail account Login Page
    Given I am on loggedIn page
    When  I click on profile thumnail
    And   I click on the Logout button
    Then  I should see the Home Page