#Created By Navin

@Complete

Feature: I should be able to view my authored lesson in the “Authoring” page
  ML 1.3 As a user (author), I should be able to view my authored lesson in the “Authoring” page

  Scenario: I should see all the elements on the Authoring page
    Given I am on loggedIn page
    And   I am on Authoring page
    And   I should see the menu icon in the header of Authoring page
    And   I should see the 'Authoring' label in the header of Authoring page
    And   I should see the search box with the search icon in the header of Authoring page
    And   I should see Profile thumbnail
    And   I should see the 'My Library' Icon with Label in the main navigator
    And   I should see the 'Explore' Icon with Label in the main navigator
    And   I should see the 'Authoring' Icon with Label in the main navigator
    And   I should see "Published" status is selected
    And   I should see the Create Lesson card on the Authoring page
    When  I Mouse hover on the lesson name on the card
    Then  I should see the full lesson name as tool tip
    When  I am on the "Draft" Tab on Authoring page
    Then  I should see "draft" status is selected
    When  I Mouse hover on the lesson name on the card
    Then  I should see the full lesson name


  Scenario Outline: I should see the Options for the lesson card according to their status
    Given I am on loggedIn page
    And   I am on Authoring page
    And   I am on the "<status>" Tab on Authoring page
    Then  I should see the lessons authored by me only
    And   I should see the details of lesson card for "<status>" Tab in Authoring page
    And  I should see "<option1>" and "<option2>" buttons for the "<status>"

    Examples:
      | status    | option1 | option2 |
      | Published | Copy    | Preview |
      | Draft     | Edit    | Preview |

  Scenario: I should see the correct count of lessons for Draft and Published status
    Given I am on loggedIn page
    And   I am on Authoring page
    When  I am on the "Published" Tab on Authoring page
    Then  I should see the lesson count at "Published" status is equals to count at All filter label
    Then  I should see the lesson count at "Published" status is equals to addition of counts at Beginner Intermediate and Expert label
    Then  I should see the lesson card count is equals to the count at "Published" status
    When  I am on the "Draft" Tab on Authoring page
    Then  I should see the lesson count at "Draft" status is equals to count at All filter label
    Then  I should see the lesson count at "Draft" status is equals to addition of counts at Beginner Intermediate and Expert label
    Then  I should see the lesson card count is equals to the count at "Draft" status


  Scenario: I should be able to delete draft authored lessons in “Authoring” page
  ML 1.3.1 As a user (author), I should be able to delete draft authored lessons in “Authoring” page

    Given I am on loggedIn page
    When  Create lesson with details in the below table
      | Lesson Name                  | Instructional Level | Tags    | Upload Image | Filename |
      | Del History of Ancient India | Beginner Level      | History | true         | img.png  |
    And   I click on the slide type "Video"
    And   I search video by text "physics"
    Then  I should see Video Player in Add Video page
    When  I click on Add To Slide Button
    Then  I should see added video slide
    When  I click on Exit button on Edit Lesson Details page
    Then  I should see the lesson saved in the Draft Tab
    When  I click on the three vertical dots of the lesson card
    Then  I should see the Delete option
    When  I click on the Delete option for the lesson card
    Then  I should see the Delete pop up with Delete icon followed by the 'Delete' as the header
    And   I should see the close mark in the Header Delete popup
    And   I should see the "Are you sure you want to delete this lesson?" "This cannot be undone." as pop up message
    And   I should see the 'No, Cancel' button on the Delete popup
    And   I should see the 'Yes, Delete' button on the Delete popup
    When  I click on 'No, Cancel'
    Then  I should see the lesson saved in the Draft Tab
    When  I click on the three vertical dots of the lesson card
    Then  I should see the Delete option
    When  I click on the Delete option for the lesson card
    And   I click on the close mark of the Delete popup
    Then  I should see the lesson saved in the Draft Tab
    When  I click on the three vertical dots of the lesson card
    Then  I should see the Delete option
    When  I click on the Delete option for the lesson card
    And   I click on the 'Yes, Delete' button on the Delete popup
    Then  I should not see deleted lesson in Draft Tab


  Scenario: I should be able to copy one authored lesson to another and save it in “Authoring” page
  #ML 1.3.2 As a user (author), I should be able to copy one authored lesson to another in “Authoring” page
    Given I am on loggedIn page
    And   I am on Authoring page
    And   I am on the "Published" Tab on Authoring page
    When  I click on "Beginner" filter
    And   I mouse hover on lesson card thumbnail on the Authoring page and Click on Copy
    Then  I should see the Lesson name as 'Copy of <Original Lesson name>' on Lesson Details Popup
    And   I should see other lesson details as previous with its "Beginner" instructional level
    And   I click on Save button on Lesson Details page
    Then  I should see the Lesson Edit details page
    And   I should see the header name as updated lesson name
    And   I should see all the slide of the lesson
    When  I click on Exit button on Edit Lesson Details page
    And   I am on the "Draft" Tab on Authoring page
    And   I click on "Beginner" filter
    When  I click on copied lesson
    Then  I should see as header label on Preview page
    And   I should see the "Publish" button on slide preview
    And   I should see the First slide view on the Preview page
    And   I should see Top section of the slide with Label 'Level : "Beginner" '


  Scenario: I should be able to copy one authored lesson to another and publish it in “Authoring” page
  #ML 1.3.2 As a user (author), I should be able to copy one authored lesson to another in “Authoring” page
    Given I am on loggedIn page
    And   I am on Authoring page
    Then  I am on the "Published" Tab on Authoring page
    When  I click on "Beginner" filter
    And   I mouse hover on lesson card thumbnail on the Authoring page and Click on Copy
    Then  I should see the Lesson name as 'Copy of <Original Lesson name>' on Lesson Details Popup
    And   I should see other lesson details as previous with its "Beginner" instructional level
    And   I click on Save button on Lesson Details page
    Then  I should see the Lesson Edit details page
    And   I should see the header name as updated lesson name
    And   I should see all the slide of the lesson
    When  I click on Publish button on Lesson Edit Details page
    And   I should see "Published" status is selected
    And   I click on "Beginner" filter
    Then  I click on copied lesson
    Then  I should see as header label on Preview page
    And   I should see the "Published" button on slide preview
    And   I should see the First slide view on the Preview page
    And   I should see Top section of the slide with Label 'Level : "Beginner" '
    And   I should see the tags added to the lesson


  Scenario: I should See the Lesson Edit page with all it's Elements
  ML 1.3.3 As a user (author), I should be able to edit my authored lesson in “Authoring” page
    Given I am on loggedIn page
    And   I am on Authoring page
    And   I am on the "Draft" Tab on Authoring page
    Then  I should see the lessons authored by me only
    And   I mouse hover on lesson card thumbnail on the Authoring page and Click on Edit
    Then  I should see the header label as 'Lesson Name'
    And   I should see the Edit Details button with pen icon
    And   I should see the 'Exit' button in the header in edit lesson page
    And   I should see the 'Publish' button in the header in edit lesson page
    And   I should see the 'Preview' button in the header in edit lesson page


  Scenario: I should be able to edit my authored lesson in Draft Tab and exit it on “Authoring” page
    Given I am on loggedIn page
    When  Create lesson with details in the below table
      | Lesson Name      | Instructional Level | Tags    | Upload Image | Filename |
      | History of India | Beginner Level      | History | true         | img.png  |
    And   I click on Exit button on Edit Lesson Details page
    And   I am on the "Draft" Tab on Authoring page
    And   I mouse hover on lesson card thumbnail on the Authoring page and Click on Edit
    Then  I should see the Lesson Edit details page
    And   I should be able to add new slide "Video" to the lesson
    And   I should be able to modify the "Video" slide details
    When  I click on Exit button on Edit Lesson Details page
    And   I am on the "Draft" Tab on Authoring page
    And   I mouse hover on lesson card thumbnail on the Authoring page and Click on preview
    Then  I should see the details as modified in the Lesson Preview page


  Scenario: I should be able to edit my authored lesson in Draft Tab and publish it on “Authoring” page
    Given I am on loggedIn page
    When  Create lesson with details in the below table
      | Lesson Name      | Instructional Level | Tags    | Upload Image | Filename |
      | History of India | Beginner Level      | History | true         | img.png  |
    And   I click on Exit button on Edit Lesson Details page
    And   I am on the "Draft" Tab on Authoring page
    And   I mouse hover on lesson card thumbnail on the Authoring page and Click on Edit
    Then  I should see the Lesson Edit details page
    And   I should be able to add new slide "Video" to the lesson
    And   I should be able to modify the "Video" slide details
    When  I click on Publish button on Lesson Edit Details page
    And   I am on the "Published" Tab on Authoring page
    And   I mouse hover on lesson card thumbnail on the Authoring page and Click on preview
    Then  I should see the details as modified in the Lesson Preview page


  Scenario: I should be able to delete my authored lesson in Draft Tab
    Given I am on loggedIn page
    When  Create lesson with details in the below table
      | Lesson Name      | Instructional Level | Tags    | Upload Image | Filename |
      | History of India | Beginner Level      | History | true         | img.png  |
    And   I click on Exit button on Edit Lesson Details page
    And   I am on the "Draft" Tab on Authoring page
    And   I mouse hover on lesson card thumbnail on the Authoring page and Click on Edit
    Then  I should see the Lesson Edit details page
    And   I should be able to add new slide "Video" to the lesson
    And   I should be able to delete slide from the lesson

  Scenario: I should See the Lesson Preview page with all it's Elements for Published lesson
  #ML 1.3.4 As a user (author), I should be able to preview my authored lesson in Published Tab on “Authoring” page
    Given I am on loggedIn page
    And   I am on Authoring page
    When  Create lesson with details in the below table
      | Lesson Name              | Instructional Level | Tags    | Upload Image | Filename | Description        |
      | History of Ancient India | Beginner Level      | History | true         | img.png  | Lesson Description |

    When  I click on the slide type "Video"
    Then  I should see "Add Video" as header label
    When  I search video by text "Selenium"
    Then  I should see Video Player in Add Video page
    When  I click on Add To Slide Button
    Then  I should see added video slide
    And   I click on Publish button on Lesson Edit Details page
    And   I am on the "published" Tab on Authoring page
    Then  I should see the lessons authored by me only
    And   I am on "beginner" Filter
    When  I mouse hover on lesson card thumbnail on the Authoring page and Click on preview
    Then  I should see as header label on Preview page
    And   I should see the First slide view on the Preview page
    And   I should see Top section of the slide with Label 'Level : "beginner" '
    And   I should see Top section of the slide with Label 'Free'
    And   I should see Top section of the slide with Label "published" button
    And   I should see the Full screen button on the slide
    And   I should see the label 'Slide x/y' on the slide
    And   I should see the Preview button with forward icon on the slide view
    And   I should see the Label 'published' - <Date on Which it is published in DD Mon Year>
    And   I should see the label 'Author - <Author name>'
    And   I should see the tags added to the lesson
    And   I should see the Description added to the Lesson
    When  I click on the Back arrow in the header
    Then  I should see the Authoring Page


  Scenario: I should See the Lesson Preview page with all it's Elements for Draft lesson
    Given I am on loggedIn page
    When  Create lesson with details in the below table
      | Lesson Name           | Instructional Level | Tags    | Upload Image | Filename | Description        |
      | History of Pyramid123 | Beginner Level      | History | true         | img.png  | Lesson Description |
    When I click on the slide type "video"
    Then I should see "Add Video" as header label
    When I search video by text "Selenium"
    Then I should see Video Player in Add Video page
    When I click on Add To Slide Button
    Then I should see added video slide
    When I click on Exit button on Edit Lesson Details page
    And  I am on the "draft" Tab on Authoring page
    Then I should see the lessons authored by me only
    And  I am on "beginner" Filter
    And  I should see the lesson saved in the Draft Tab
    And  I Mouse hover on the lesson name on the card
    Then I should see the full lesson name
    And  I mouse hover on lesson card thumbnail on the Authoring page and Click on preview
    Then I should see as header label on Preview page
    And  I mouse hover on the Lesson name in the header
    Then I should see the full lesson name as tool tip
    And  I should see the First slide view on the Preview page
    And  I should see Top section of the slide with Label 'Level : "beginner" '
    And  I should see Top section of the slide with Label 'Free'
    And  I should see Top section of the slide with Label "publish" button
    And  I should see the Full screen button on the slide
    And  I should see the label 'Slide x/y' on the slide
    And  I should see the Preview button with forward icon on the slide view
    And  I should see the label 'Author - <Author name>'
    And  I should see the tags added to the lesson
    And  I should see the Description added to the Lesson
    When I click on the Back arrow in the header
    Then I should see the Authoring Page


  Scenario Outline: I should see the resultant lesson as per the search text and Reset the Search result on Explore page.
  ML 1.3.5 As a user (author), I should be able to search for lessons in “Explore” page
    Given I am on loggedIn page
    When  I click on the "<Filter Name>" in the home page
    Then  I should see the "<Filter Name>" filter is selected on ML home page
    When  I search lesson by text "<Search Text>"
    Then  I should see Cross icon at end of search box to clear the search on ML home page
    And   I should see Search Result Indication Text as Search results for "<Search Text>" on ML home page
    And   I should see All filter count is equals to addition of other three filters count
    Then  I should see the "<Filter Name>" filter is selected on ML home page
    And   I should see Tick mark before the "<Filter Name>" Label in home page
    And   I should see Lesson count in the "<Filter Name>" filter label and should match the lesson cards count on page
    And   I should not see tick for other than "<Filter Name>" filter label on ML home page
    And   I should see  Lessons with the search text "<Search Text>"
    When  I click on Cross mark on default explore Search box
    Then  I should not see Search Result Indication Text with Search Text "<Search Text>"
    And   I should see All filter selected by default on ML home page
    And   I should see All filter count is equals to addition of other three filters count
    And   I should see Tick mark before the "All" Label in home page
    And   I should not see tick for other than "All" filter label on ML home page

    Examples:
      | Filter Name  | Search Text                  |
      | Beginner     | Beginner1234 Blesson123      |
      | Beginner     | BeginnerT1234 btag1234       |
      | Beginner     | ml                           |
      | Beginner     | BeginnerDesc123 bdesc123     |
      | Intermediate | Intermediate123 ILesson123   |
      | Intermediate | IntermediateTag123 Itag123   |
      | Intermediate | ml1                          |
      | Intermediate | IntermediateDesc123 Idesc123 |
      | Expert       | ExpertLesson123 ELesson123   |
      | Expert       | ExpertTag123 Etag123         |
      | Expert       | ml3                          |
      | Expert       | ExpertDesc123 Edesc123       |


  Scenario Outline: I should see the resultant lesson as per the search text and Reset the Search result on Authoring page.
  ML 1.3.5 As a user (author), I should be able to search for lessons in “Authoring” page

    Given I am on Login page
    When  I enter "<Email>" in email field on login page
    And   I enter "snapwiz" in password field on login page
    When  I click on Login button on login page
    And   I am on Authoring page
    When  I click on the "<Filter Name>" in the home page
    Then  I should see the "<Filter Name>" filter is selected on ML home page
    When  I search lesson by text "<Search Text>"
    Then  I should see Cross icon at end of search box to clear the search on ML home page
    And   I should see Search Result Indication Text as Search results for "<Search Text>" on ML home page
    And   I should see All filter count is equals to addition of other three filters count
    Then  I should see the "<Filter Name>" filter is selected on ML home page
    And   I should see Tick mark before the "<Filter Name>" Label in home page
    And   I should see Lesson count in the "<Filter Name>" filter label and should match the lesson cards count on page
    And   I should not see tick for other than "<Filter Name>" filter label on ML home page
    And   I should see  Lessons with the search text "<Search Text>"
    When  I click on Cross mark on default explore Search box
    Then  I should not see Search Result Indication Text with Search Text "<Search Text>"
    And   I should see All filter selected by default on ML home page
    And   I should see All filter count is equals to addition of other three filters count
    And   I should see Tick mark before the "All" Label in home page
    And   I should not see tick for other than "All" filter label on ML home page


    Examples:
      | Filter Name  | Search Text                  | Email           |
      | Beginner     | Beginner1234 Blesson123      | ml@snapwiz.com  |
      | Beginner     | BeginnerT1234 btag1234       | ml@snapwiz.com  |
      | Beginner     | ml                           | ml@snapwiz.com  |
      | Beginner     | BeginnerDesc123 bdesc123     | ml@snapwiz.com  |
      | Intermediate | Intermediate123 ILesson123   | ml1@snapwiz.com |
      | Intermediate | IntermediateTag123 Itag123   | ml1@snapwiz.com |
      | Intermediate | ml1                          | ml1@snapwiz.com |
      | Intermediate | IntermediateDesc123 Idesc123 | ml1@snapwiz.com |
      | Expert       | ExpertLesson123 ELesson123   | ml3@snapwiz.com |
      | Expert       | ExpertTag123 Etag123         | ml3@snapwiz.com |
      | Expert       | ml3                          | ml3@snapwiz.com |
      | Expert       | ExpertDesc123 Edesc123       | ml3@snapwiz.com |