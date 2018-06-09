Feature: Add video slide to lesson
  As a user (author), I should be able to add video slide to the micro lesson
  Scenario Outline: Adding video slide in lesson
    Given I am on loggedIn page
    Given Create lesson with details in the below table
      | Lesson Name   | Instructional Level   | Tags  | Upload Image  |  Filename     |
      | lesson        | Intermediate Level    | subject | true        |   img.png     |
    When I click on the slide type "Video"
    Then I should see "Add Video" as header label
    When I search video by text "<Search Text>"
    Then I should see Video Player in Add Video page
    When I click on Add To Slide Button
    Then I should see added video slide

  Examples:
  |Search Text|
  |Software testing|

  Scenario: Verification of video search result
    Given I am on loggedIn page
    When  I am on Add video page
    When  I search video by text "Software testing"
    Then  I should see Video Player in Add Video page
    When  I click on the Load More Results
    Then  I should see next set of 20 videos

  Scenario: Clicking on back arrow icon on Add video page
    Given I am on loggedIn page
    When  I am on Add video page
    When  I click on Back arrow icon
    Then  I should see Add new slide icon

  Scenario: Clicking on Cancel button on Add video page
    Given I am on loggedIn page
    When  I am on Add video page
    When  I click on Cancel button
    Then  I should see Add new slide icon

  Scenario: Preview video slide in full screen
    Given I am on loggedIn page
    When  I am on Add video page
    When  I search video by text "Software testing"
    Then  I should see Video Player in Add Video page
    When  I click on Add To Slide Button
    When  I click on the Expand icon
    Then  I should see Exit full screen icon
    When  I click on the Exit full screen icon
    Then  I should see Full screen icon

  Scenario: Editing video slide
    Given I am on loggedIn page
    When  I am on Add video page
    When  I search video by text "Software testing"
    When  I click on Add To Slide Button
    When  I click on the Edit Slide
    Then  I should see "Replace Video" as header label
    When  I search video by text "selenium tutorial"
    Then  I should see Video Player in Replace Video page
    When  I click on Update Slide Button
    Then  I should see updated replace video slide

  Scenario: Deleting video slide
    Given I am on loggedIn page
    When  I am on Add video page
    When  I search video by text "Dhoni"
    When  I click on Add To Slide Button
    When  I click on the Delete Slide Button
    And   I click on the "No,Cancel" in the delete popup
    Then  I should see added video slide
    When  I click on the Delete Slide Button
    And   I click on the "Yes,Delete" in the delete popup
    Then  I should not see added video slide


#  Scenario Outline: Adding video slide in lesson from Vimeo video client
#    Given I am on loggedIn page
#    When  I am on Add video page
#    When  I click on the Vimeo video client dropdown
#    Then  I should see Vimeo logo in the dropdown
#    When  I searches video by text <Search Text>
#    Then  I should see Video Player in Add Video page
#    When  I click on Add To Slide Button
#    Then  I should see added video slide
#
#  Examples:
#  |Search Text|
#  |Software testing|
#  |Functional Testing|
#
#  Scenario: editing Vimeo video slide
#    Given I am on loggedIn page
#    When  I am on Add video page
#    When  I click on the Vimeo video client dropdown
#    When  I searches video by text "Selenium"
#    When  I click on Add To Slide Button
#    When  I click on the Edit Slide
#    Then  I should see "Replace Video" as header label
#    And   I should see Vimeo logo in the dropdown
#    When  I searches video by text <search text>
#    Then  I should see Video Player in Replace Video page
#    When  I click on Update Slide Button
#    Then  I should see added video slide
