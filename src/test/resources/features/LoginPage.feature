@Complete

Feature: I should be able to successfully login to the micro learning application
  #ML 1.1: As a user, I should be able to successfully login to the micro learning application

  Scenario: I should see all the fields of Login page
    Given I am on Login page
    Then  I should see the logo on the header page in login page
    And   I should see 'Sign Up' button in the header
    And   I should see 'Login to your account' label in sign up page
    And   I should see email text box with placeholder text as 'Email' in Login page
    And   I should see 'Email' label in login page
    And   I should see password text box with placeholder text as 'Password' in Login page
    And   I should see 'Password' label in Login page
    And   I should see remember me check box in login page
    And   I should see 'Remember me' label in Login page
    And   I should see 'Login with your Google Account' label
    And   I should see 'Login with Google' link
    And   I should see the Footer label 'Don't have an account?'
    And   I should see 'Sign Up' link in Login page footer

  Scenario: I should be able to Login from Login page
    Given I am on Login page
    When  I enter valid "ml@snapwiz.com" emil in email field
    And   I enter valid "snapwiz" password in password field
    When  I click on Login button on login page
    And   I should see Profile thumbnail


  Scenario Outline: I should be able to  see Login Page Validations for email and password field
    Given I am on Login page
    When  I enter "<Email>" in email field on login page
    And   I enter "<Password>" in password field on login page
    When  I click on Login button on login page
    Then  I should see the Validation  as "<Validation Message>" for the "<Field>"

    Examples:
      | Email                    | Password | Validation Message              | Field                |
      |                          | navin123 | Required                        | Empty Email          |
      | mladmin@gmail.com        |          | Required                        | Empty Password       |
      | navin1234gmail.com       | navin123 | Please provide a valid Email id | Wrong Email          |
      | navin1234@gmail.         | navin123 | Please provide a valid Email id | Wrong Email          |
      | @gmail.com               | navin123 | Please provide a valid Email id | Wrong Email          |
      | navin1234@gmailcom       | navin123 | Please provide a valid Email id | Wrong Email          |
      | navin1@234@gmail.com     | navin123 | Please provide a valid Email id | Wrong Email          |
      | navin1234@.com           | navin123 | Please provide a valid Email id | Wrong Email          |
      | *avin1234@gmail.com      | navin123 | Please provide a valid Email id | Wrong Email          |
      | mladmin@gmail.com        | 1kasd    | Invalid Email or Password\n×    | Wrong Password       |
      | mladmin2323321@gmail.com | navin123 | Invalid Email or Password\n×    | Email Not Registered |


#  Scenario: I should be able to Login with google Account But not logged in the browser and denies the permission
#    Given I am on Login page
#    When  I click on Login with Google link in the Login page
#    And   I enter email "snapwiz.qatest@gmail.com" in email
#    And   I click on Next button
#    When  I enter the password "SnapwiZ123" in gmail page
#    And   I click on Sign In button
#    Then  I should see  page micro learning would like to: page
#    When  I click on the Deny button
#    Then  I should see sign up page
#
#  Scenario: I should be able to Login with google Account But not logged in the browser but allows
#    Given I am on Login page
#    When  I click on Login with Google link in the Login page
#    And   I enter email "snapwiz.qatest@gmail.com" in email
#    And   I click on Next button
#    When  I enter the password "SnapwiZ123" in gmail page
#    And   I click on Sign In button
#    Then  I should see  page micro learning would like to: page
#    When  I click on the Allow button
#    Then  I should see Profile thumbnail
#
#
#  Scenario: I should be able to Login with google Account and he logged in gmail account in the browser but denies
#    Given I am on Login page
#    When  I click on Login with Google link in the Login page
#    Then  I should see  page micro learning would like to: page
#    When  I click on the Deny button
#    Then  I should see sign up page
#
#  Scenario: I should be able to Login with google Account and he loggedin gmail account in the browser but allows
#    Given I am on Login page
#    When  I click on Login with Google link in the Login page
#    Then  I should see  page micro learning would like to: page
#    When  I click on the Allow button
#    Then  I should see Profile thumbnail
