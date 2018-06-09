
Feature: Check the layout of element on home page
  Scenario: Create home page dump
    When  I am on ML home page
    When  I create the dump for "Home page" using spec file "specs/homepage.spec"

  Scenario: Validate layout of element on home page
    When  I am on ML home page
    Then  I validate the layout of "Home page" with spec file "specs/homepage.spec"
