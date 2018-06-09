Feature: Access a micro lesson added in My Library
  1.5 As a user (consumer), I should be able to access a micro lesson once i have added it in “My Library”

  Scenario: Add micro lesson to My Library
    Given I am on loggedIn page
    When  I add Lesson to My library
    Then  I should see "Added To My Library" button
    When  I navigate to "My Library" page
    Then  I should see the Added Micro Lesson

 Scenario: Add micro lesson to My Library without login
    Given I am on ML home page
    When  I add Lesson to My library
    Then  I should see the 'Login' button in the header in ML home page
    When  I click on Login button on Sign Up page in the header
    When  I enters existing email "pritiranjan@gmail.com" and password "pritiranjan" in login page
    And   I click on the Login button in login page
    Then  I should see "Added To My Library" button
    When  I navigate to "My Library" page
    Then  I should see the Added Micro Lesson

 Scenario: Add micro lesson to My Library without SignUp
    Given I am on ML home page
    When  I add Lesson to My library
    Then  I should see the 'Login' button in the header in ML home page
    When  I enter name as "Demo User" in sign up page
    When  I enter email as "demouser@snapwiz.com" in sign up page
    When  I enter password as "snapwiz" in sign up page
    When  I enter re-type password as "snapwiz" in sign up page
    And   I click on SignUp button in sign up page
    Then  I should see "Added To My Library" button
    When  I navigate to "My Library" page
    Then  I should see the Added Micro Lesson

 Scenario: Removing the micro lesson from My Library
    Given I am on loggedIn page
    When  I add Lesson to My library
    And   I navigate to "My Library" page
    Then  I should see the Added Micro Lesson
    When  I click on the Remove Button on lesson card
    And   I click on the "No,Cancel" in the Remove popup
    Then  I should see the Added Micro Lesson
    When  I click on the Remove Button on lesson card
    And   I click on the "Yes,Remove" in the Remove popup
    Then  I should not see the Removed Lesson

  Scenario: Play the micro Lesson having only video slide from My Library
    Given I am on loggedIn page
    When  Create lesson with details in the below table
      | Lesson Name   | Instructional Level   | Tags  | Upload Image|  Filename     |
      | lesson        | Intermediate Level    | subject | false        |        |
    When  I add all Slide Type in lesson
      |Slide Type|
      |Video     |
    When  I click on Publish button
    When  I navigate to "Explore" page
    When  I add Lesson to My library
    When  I navigate to "My Library" page
    And   I click on the Play Button on lesson card
    When  I click on the play button of video slide
    Then  I should see the Pause button in the video slide
    When  I click on the Expand icon
    Then  I should see Exit full screen icon
    When  I click on the Exit full screen icon
    Then  I should see Full screen icon

 Scenario: Play the micro Lesson having only Rich Text slide from My Library
   Given I am on loggedIn page
   When  Create lesson with details in the below table
     | Lesson Name   | Instructional Level   | Tags  | Upload Image|  Filename     |
     | lesson        | Intermediate Level    | subject | false        |        |
   When  I add all Slide Type in lesson
     |Slide Type|
     |Rich Text |
   When  I click on Publish button
   When  I navigate to "Explore" page
   When  I add Lesson to My library
   When  I navigate to "My Library" page
   And   I click on the Play Button on lesson card
   Then  I should see added Rich Text slide
   When  I click on the Expand icon
   Then  I should see Exit full screen icon
   When  I click on the Exit full screen icon
   Then  I should see Full screen icon

  Scenario: Play the micro Lesson from My Library and navigation from 1 slide to another
    Given I am on loggedIn page
    When  Create lesson with details in the below table
      | Lesson Name   | Instructional Level   | Tags  | Upload Image|  Filename     |
      | lesson        | Intermediate Level    | subject | false        |        |
    When  I add all Slide Type in lesson
      |Slide Type|
      |Video     |
      |Question  |
      |Rich Text |
    When  I click on Publish button
    When  I navigate to "Explore" page
    When  I add Lesson to My library
    When  I navigate to "My Library" page
    And   I click on the Play Button on lesson card
    Then  I should see the label "Slide 1/3" on the slide
    When  I click on the Next slide button
    Then  I should see the label "Slide 2/3" on the slide
    And   I should see added Question slide
    When  I click on Submit Button of question slide
    Then  I should see the Question Attempt score card for question slide
    When  I click on the Next slide button
    Then  I should see added Rich Text slide
    When  I click on the Previous slide button
    Then  I should see the Question Attempt score card for question slide
    When  I click on the Next slide button
    Then  I should see the label "Slide 3/3" on the slide
    And   I should see added Rich Text slide
    And   I should not see the Next slide button

 Scenario: UI Validation
   Given I am on loggedIn page
    When  Create lesson with details in the below table
      | Lesson Name   | Instructional Level   | Tags  | Upload Image|  Filename     |
      | lesson        | Intermediate Level    | subject | false        |        |
    When  I add all Slide Type in lesson
      |Slide Type|
      |Question  |
    When  I click on Publish button
    When  I navigate to "Explore" page
    When  I add Lesson to My library
    When  I navigate to "My Library" page
    And   I click on the Play Button on lesson card
    Then  I should see "Play -" as header label on MyLibrary
    Then  I should see Top section of the slide with Label 'Level : "Intermediate" '
    And   I should see Top section of the slide with Label 'Free'
    And   I should see "Added To My Library" button
    And   I should see the Label 'published' - <Date on Which it is published in DD Mon Year>
    And   I should see the label 'Author - <Author name>'
    And   I should see the tags added to the lesson

 Scenario: Playing the micro Lesson from My Library having only Question slides
    Given I am on loggedIn page
    When  Create lesson with details in the below table
      | Lesson Name   | Instructional Level   | Tags  | Upload Image|  Filename     |
      | lesson        | Intermediate Level    | subject | false        |        |
    When  I create True False Question Type having hint and solution
    When  I create Multiple choice Question Type having hint and solution
    When  I click on Publish button
    When  I navigate to "Explore" page
    When  I add Lesson to My library
    When  I navigate to "My Library" page
    And   I click on the Play Button on lesson card
    Then  I should see added Question slide
    When  I click on the Next slide button
    And   I click on the "No,Cancel" in the Skip Slide popup
    Then  I should see added Question slide
    When  I click on the Next slide button
    And   I click on the "Yes" in the Skip Slide popup
    Then  I should see the label "Slide 2/2" on the slide
    When  I click on the Previous slide button
    When  I enter the Answer in "True False Question" slide
    And   I click on the Next slide button
    Then  I should see the Question Attempt score card for question slide
    And   I should see the Solution text
    When  I click on Hint Button
    Then  I should see the Hint text
    When  I click on the Next slide button
    Then  I should see the label "Slide 2/2" on the slide
    When  I click on Submit Button of question slide
    Then  I should see the Question Attempt score card for question slide