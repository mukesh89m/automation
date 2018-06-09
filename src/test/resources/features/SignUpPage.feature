@complete
Feature: Sign Up Page Validation
  ML 1.1: As a user, I should be able to successfully sign up into the micro learning application

  Scenario: I should see all the fields of sign up page
    Given I am on sign up page
    Then  I should see the logo on the header page in Sign Up page
    And   I should see 'Login' button in the header
    And   I should see 'Sign Up' label in sign up page
    And   I should see name text box with placeholder text as 'Full Name'
    And   I should see 'Full Name' label  in sign up page
    And   I should see email text box with placeholder text as 'Email'
    And   I should see 'Email' label in sign up page
    And   I should see password text box with placeholder text as 'Password'
    And   I should see 'Password' label in sign up page
    And   I should see re-type password text box with placeholder text as 'Re-type your Password'
    And   I should see 'Re-type Password' label in sign up page
    And   I should see Sign-up with your Google Account label
    And   I should see Sign-up with Google link
    And   I should see the Footer label as Already have an account? Login link


  Scenario Outline: I should be able to sign up into the application
    Given I am on ML home page
    When  I click on SignUp button in ML home page
    Then  I should see sign up page
    When  I enter name as "<Name>" in sign up page
    When  I enter email as "<Email>" in sign up page
    When  I enter password as "<Password>" in sign up page
    When  I enter re-type password as "<Re-type Password>" in sign up page
    When  I click on SignUp button in sign up page
    Then  I should see Profile thumbnail

    Examples:
      | Name   | Email                | Password  | Re-type Password |
      | Mukesh | mukesh1234@gmail.com | mukesh123 | mukesh123        |
      | Navin  | navin1234@gmail.com  | navin123  | navin123         |

  Scenario: I am trying to sign Up into the application with already registered email account
    Given I am on sign up page
    When  I enter existing email "ml@snapwiz.com" in the sign up page
    Then  I should see the validation for email field as "Email already exists. Please login to your account"
    When  I click on Login link in Validation popup text in the sign up page
    Then  I should see the Login page
    And   I enters existing email "ml@snapwiz.com" and password "snapwiz" in login page
    And   I click on the Login button in login page
    Then  I should see Profile thumbnail

  Scenario: Sign Up Validation without entering anything
    Given I am on sign up page
    When  I click on SignUp button in sign up page
    Then  I should see the Validation text for Name field as "Please provide your full name"
    And   I should see the Validation text for Email field as "Please provide a valid Email id"
    And   I should see the Validation text for Password field as "Please provide a valid password"


  Scenario Outline: Validation for Empty name, email, password and retype password field
    Given I am on sign up page
    When  I enter "<Name>" in username field in the sign up page
    When  I enter "<Email>"  in email field in the sign up page
    When  I enter "<Password>" in password field in the sign up page
    When  I enter "<Re-type Password>" in re-type password field in the sign up page
    When  I click on SignUp button in sign up page
    Then  I should see the Validation  as "<Validation Message>"


    Examples:
      | Name  | Email             | Password | Re-type Password | Validation Message              |
      |       | mladmin@gmail.com | navin123 | navin123         | Please provide your full name   |
      | Navin |                   | navin123 | navin123         | Please provide a valid Email id |
      | Navin | mladmin@gmail.com |          | navin123         | Please provide a valid password |
      | Navin | mladmin@gmail.com | navin123 |                  | Please retype the password      |

  Scenario Outline: Validation for invalid name, email, password and retype password field
    Given I am on sign up page
    When  I enter "<Name>" in username field in the sign up page
    When  I enter "<Email>"  in email field in the sign up page
    When  I enter "<Password>" in password field in the sign up page
    When  I enter "<Re-type Password>" in re-type password field in the sign up page
#    When  I click on SignUp button in sign up page
    Then  I should see the Validation for "<Data Povided>" as "<Validation Message>"

    Examples:
      | Name     | Email                | Password | Re-type Password | Validation Message                     | Data Povided            |
      | na~      | mladmin@gmail.com    | navin123 | navin123         | No special characters allowed          | Invalid Name            |
      | @snapwiz | mladmin@gmail.com    | navin123 | navin123         | No special characters allowed          | Invalid Name            |
      | snapwiz# | mladmin@gmail.com    | navin123 | navin123         | No special characters allowed          | Invalid Name            |
      | snapwiz$ | mladmin@gmail.com    | navin123 | navin123         | No special characters allowed          | Invalid Name            |
      | snapwiz% | mladmin@gmail.com    | navin123 | navin123         | No special characters allowed          | Invalid Name            |
      | snapwiz^ | mladmin@gmail.com    | navin123 | navin123         | No special characters allowed          | Invalid Name            |
      | snapwiz* | mladmin@gmail.com    | navin123 | navin123         | No special characters allowed          | Invalid Name            |
      | snapwiz  | navin1234gmail.com   | navin123 | navin123         | Please provide a valid Email id        | Invalid Email           |
      | snapwiz  | navin1234@gmail.     | navin123 | navin123         | Please provide a valid Email id        | Invalid Email           |
      | snapwiz  | @gmail.com           | navin123 | navin123         | Please provide a valid Email id        | Invalid Email           |
      | snapwiz  | navin1234@gmailcom   | navin123 | navin123         | Please provide a valid Email id        | Invalid Email           |
      | snapwiz  | navin1@234@gmail.com | navin123 | navin123         | Please provide a valid Email id        | Invalid Email           |
      | snapwiz  | navin1234@.com       | navin123 | navin123         | Please provide a valid Email id        | Invalid Email           |
      | snapwiz  | *avin1234@gmail.com  | navin123 | navin123         | Please provide a valid Email id        | Invalid Email           |
      | snapwiz  | navin1234@gmail.com  | n43      | n43              | Password must be at least 4 characters | Invalid Password        |
      | snapwiz  | navin1234@gmail.com  | navin123 | navin1234        | Retyped password do not match          | Invalid retype password |


  Scenario: I am able to open Terms link
    Given I am on sign up page
    When  I click on Terms link in the sign up page
    Then  I should see the terms page

  Scenario: I am able to open Privacy Policy link
    Given I am on sign up page
    When  I click on Privacy Policy link in the sign up page
    Then  I should see the Privacy Policy page

#  Scenario: I should be able to Sign Up with google Account But not logged in the browser and denies the permission
#    Given I am on sign up page
#    When  I click on Sign-up with Google link in the sign up page
#    Then  I should see the gmail login page
#    When  I enter email "snapwiz.qatest@gmail.com" in gmail page email field
#    And   I click on Next button
#    When  I enter the password "SnapwiZ123" in gmail page password field
#    And   I click on Sign In button
#    Then  I should see  page micro learning would like to: page
#    When  I click on the Deny button
#    Then  I should see sign up page
# Scenario: I should be able to Sign Up with google Account and he logged in gmail account in the browser but denies
#    Given I am on sign up page
#    When  I click on Sign-up with Google link in the sign up page
#    Then  I should see  page micro learning would like to: page
#    When  I click on the Deny button
#    Then  I should see sign up page
#
#  Scenario: I should be able to Sign Up with google Account But not logged in the browser
#    Given I am on sign up page
#    When  I click on Sign-up with Google link in the sign up page
#    Then  I should see the gmail login page
#    When  I enter email "snapwiz.qatest@gmail.com" in gmail page email field
#    And   I click on Next button
#    When  I enter the password "SnapwiZ123" in gmail page password field
#    And   I click on Sign In button
#    Then  I should see  page micro learning would like to: page
#    When  I click on the Allow button
#    Then  I should see Profile thumbnail
#
#  Scenario: I should be able to Sign Up with google Account and he loggedin gmail account in the browser but allows
#    Given I am on sign up page
#    When  I click on Sign-up with Google link in the sign up page
#    Then  I should see  page micro learning would like to: page
#    When  I click on the Allow button
#    Then  I should see Profile thumbnail