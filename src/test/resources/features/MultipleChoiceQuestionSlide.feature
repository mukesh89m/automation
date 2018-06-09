Feature:  Add Multiple choice Question slide to lesson
  1.2.4 As a user (author), I should be able to add Multiple choice Question slide to the micro lesson

  Scenario: Add Multiple Choice Question slide in lesson
    Given  I am on loggedIn page
    Given Create lesson with details in the below table
      | Lesson Name    | Instructional Level | Tags     | Upload Image | Filename |
      | Question slide | Expert Level         | Question | true         | img.png  |
    When I click on the Question Slide type for creating slide
    Then I should see "Question Type" as header label
    When I click on the "Multiple Choice" Question slide
    Then I should see "Add Question" as header label
    When I click on Add To Slide Button for verifying warning message
    Then I should see warning message "Answer choices should not be empty" for Multiple choice Question
    When I enter the required details in the Question slide for Multiple choice question type
    And  I click on Add To Slide Button
    Then I should see added Question slide

  Scenario: Clicking on back arrow icon on Add Question page
    Given I am on loggedIn page
    When  I am on Multiple choice Question page
    When  I click on Back arrow icon
    Then  I should see "Question Type" as header label

  Scenario: Clicking on Cancel button on Add Question page
    Given I am on loggedIn page
    When  I am on Multiple choice Question page
    When  I click on Cancel button
    Then  I should see "Question Type" as header label

  Scenario: preview question slide in full screen
    Given I am on loggedIn page
    When  I am on Multiple choice Question page
    When  I enter the required details in the Question slide for Multiple choice question type
    When  I click on Add To Slide Button
    When  I click on the Expand icon
    Then  I should see Exit full screen icon
    When  I click on the Exit full screen icon
    Then  I should see Full screen icon

  Scenario: editing question slide
    Given I am on loggedIn page
    When  I am on Multiple choice Question page
    When  I enter the required details in the Question slide for Multiple choice question type
    When  I click on Add To Slide Button
    When  I click on the Edit Slide
    Then  I should see "Edit Question" as header label
    When  I update the required details in the Question slide for Multiple choice question type
    When  I click on Update Slide Button
    Then  I should see updated Question slide

  Scenario: Deleting Question slide
    Given I am on loggedIn page
    When  I am on Multiple choice Question page
    When  I enter the required details in the Question slide for Multiple choice question type
    When  I click on Add To Slide Button
    When  I click on the Delete Slide Button
    And   I click on the "No,Cancel" in the delete popup
    Then  I should see added Question slide
    When  I click on the Delete Slide Button

    And   I click on the "Yes,Delete" in the delete popup
    Then  I should not see the deleted Question slide

  Scenario Outline: Add question text using text editor
    Given I am on loggedIn page
    When  I am on Multiple choice Question page
    When  I click on Question text area of Multiple choice question type
    Then  I should see Text Editor popup
    When  I click on "<TextEditor Option>" of text editor
    And   I enter the required details in the Question slide for Multiple choice question type
    Then  I should see the Multiple choice question text font style according "<tag>"

  Examples:
    |TextEditor Option|tag|
    |Bold             | b |
    |Italic           | i |
  Scenario Outline: Add valid points to the Question
    Given I am on loggedIn page
    When  I am on Multiple choice Question page
    When  I enter the required details in the Question slide for Multiple choice question type
    When  I enter the "<Point>" in the question slide
    When  I click on save point
    Then  I should see the given point "<Point>"
    When  I click on Add To Slide Button
    When  I click on Preview Button
    And   I click on Submit Button of question slide
    Then  I should see the "<Point>"

  Examples:
    |Point|
    |0.1  |
    |  1  |
    |99.99|

  Scenario: Add invalid points to the question
    Given I am on loggedIn page
    When  I am on Multiple choice Question page
    When  I enter the required details in the Question slide for Multiple choice question type
    When  I enter the "0" in the question slide
    And   I click on save point
    Then  I should see red border around point edit box

  Scenario: Add Hint in Question Slide
    Given I am on loggedIn page
    When  I am on Multiple choice Question page
    When  I enter the required details in the Question slide for Multiple choice question type
    When  I enter Hint text in the hint text box
    When  I click on Add To Slide Button
    When  I click on Preview Button
    And   I click on Hint Button
    Then  I should see the Hint text

  Scenario: Add Solution in Question Slide
    Given I am on loggedIn page
    When  I am on Multiple choice Question page
    When  I enter the required details in the Question slide for Multiple choice question type
    When  I enter Solution text in the solution text box
    When  I click on Add To Slide Button
    When  I click on Preview Button
    And   I click on Submit Button of question slide
    Then  I should see the Solution text











#  Scenario: Add Tags

