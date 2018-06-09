@complete
Feature: Add rich text slide to lesson
  As a user (author), I should be able to add rich text slide to the micro lesson

  Scenario: Adding rich text slide in lesson
    Given I am on loggedIn page
    Given Create lesson with details in the below table
      | Lesson Name | Instructional Level | Tags    | Upload Image | Filename |
      | lesson      | Intermediate Level  | subject | false        | img.png  |
    When I click on the slide type "Text"
    Then I should see "Add Rich Text" as header label
    And  I should see Froala Editor
    Then I should see Add To Slide button is disabled
    When I enter the text "testing" in Froala Editor
    When I click on Add To Slide button
    Then I should see added "Rich Text" slide

  Scenario: Clicking on back arrow icon on Add Rich Text page
    Given I am on loggedIn page
    When  I am on Add Rich Text page
    When  I click on Back arrow icon
    Then  I should see Add new slide icon
#
  Scenario: Clicking on Cancel button on Add Rich Text page
    Given I am on loggedIn page
    When  I am on Add Rich Text page
    When  I click on Cancel button
    Then  I should see Add new slide icon

  Scenario: Previewing Rich Text slide in full screen
    Given I am on loggedIn page
    When  I am on Add Rich Text page
    When  I enter the text "testing" in Froala Editor
    When  I click on Add To Slide button
    Then  I should see the Text slide preview
    When  I click on the Expand icon
    Then  I should see Exit full screen icon
    When  I click on the Exit full screen icon
    Then  I should see Full screen icon
#
  Scenario: Editing Rich Text slide
    Given I am on loggedIn page
    When  I am on Add Rich Text page
    When  I enter the text "testing" in Froala Editor
    When  I click on Add To Slide button
    When  I click on Edit Slide button
    Then  I should see "Edit Rich Text" as header label
    When  I click on Update Slide Button
    Then  I should see added "Rich Text" slide
#
  Scenario: Deleting Rich Text slide
    Given I am on loggedIn page
    When  I am on Add Rich Text page
    When  I enter the text "testing" in Froala Editor
    When  I click on Add To Slide button
    When  I click on the Delete Slide Button
    And   I click on the "No,Cancel" in the delete popup
    Then  I should see added "Rich Text" slide
    When  I click on the Delete Slide Button
    And   I click on the "Yes,Delete" in the delete popup
    Then  I should not see added "Rich Text" slide
#
  Scenario Outline: Formatting rich text using different format
    Given I am on loggedIn page
    And   I am on Add Rich Text page
    When  I click on "<Format>" button of Froala editor
    And   I enter the text "<Text>" in Froala Editor
    Then  I should see the text "<Text>" is formatted as "<Format>"
    Examples:
      | Text    | Format |
      | Testing | Bold   |
      | Testing | Italic |




































