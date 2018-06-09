@complete
Feature:Create lesson
  As a user, I should be able to create lesson


  Scenario Outline: Create lesson with  minimum fields
    Given I am on loggedIn page
    When  I navigate to "Authoring" page
    And   I click on the "Create New" link in authoring page
    Then  Lesson pop-up should be displayed
    When  I enter lesson name "<Lesson Name>" in lesson name textbox
    When  I select instructional level "<Instructional Level>"
    When  I enter tags "<Tags>" in lesson pop up
    When  I click on the Save button in lesson pop up
    Then  I should see Lesson created successfully

    Examples:
      | Lesson Name | Instructional Level | Tags            |
      | lesson      | Beginner Level      | tags1,tag2,tag3 |
      | lesson      | Intermediate Level  | tags4           |


  Scenario: Error validation while creating lesson
    Given I am on loggedIn page
    When  I navigate to "Authoring" page
    And   I click on the "Create New" link in authoring page
    When  I click on the Save button in lesson pop up
    Then  I should see validation message for name as "Please provide a valid lesson name"
    And   I should see validation message for instructional level as "Please select an instructional level"


  Scenario: Closing the lesson creation pop up by clicking on the Cancel button
    Given I am on loggedIn page
    When  I navigate to "Authoring" page
    And   I click on the "Create New" link in authoring page
    When  I click on the Cancel button in lesson pop up
    Then  Lesson creation pop up should be closed

  Scenario: Closing the lesson creation pop up by clicking on the x icon displayed in the header.
    Given I am on loggedIn page
    When  I navigate to "Authoring" page
    And   I click on the "Create New" link in authoring page
    When  I click on the x icon in lesson pop up
    Then  Lesson creation pop up should be closed

  Scenario: Replace image link on successful file upload
    Given I am on loggedIn page
    When  I navigate to "Authoring" page
    And   I click on the "Create New" link in authoring page
    When  I upload image"img.png" in lesson pop up
    Then  I should see "Replace Image" link on pop up


  Scenario Outline: Upload invalid file type and size of file more than 5 MB
    Given I am on loggedIn page
    When  I navigate to "Authoring" page
    And   I click on the "Create New" link in authoring page
    When  I click on the Upload image link in lesson pop up
    And   I upload "<Invalid File>" image
    Then  I should see error message as "<Error Message>" based on the Invalid File in pop up

    Examples:
      | Invalid File   | Error Message                                                    |
      | passphrase.txt | Invalid file type. Supported file types are .png, .jpeg or .gif. |
      | 7mb.jpeg       | The maximum supported file size is 5 MB.                         |


  Scenario: Create lesson with and without image from Authoring page
    Given I am on loggedIn page
    Given Create lesson with details in the below table
      | Lesson Name | Instructional Level | Tags   | Upload Image | Filename |
      | lesson      | Beginner Level      | tags1  | false        |          |
      | lesson      | Intermediate Level  | tags17 | true         | img.png  |


  Scenario: Visibility of previously added tags by any user in the autosuggest
    Given I am on loggedIn page
    Given Create lesson with details in the below table
      | Lesson Name | Instructional Level | Tags                                                                                                         | Upload Image | Filename |
      | lesson      | Beginner Level      | tags1,tags2,tags3,tags4,tags5,tags6,tags7,tags8,tags9,tags10,tag11,tags12,tags13,tags14,tags15,tags16,tags17 | false        |          |
      | lesson      | Beginner Level      | tags1                                                                                                        | false        |          |

    When  I navigate to "Authoring" page
    And  I click on the "Create New" link in authoring page
    When I click on Tag text box of lesson popup
    Then I should see text "Please enter 2 or more characters"
    When I enter tag "tags" in lesson pop up
    Then I should see previously added "tags1" in the autosuggestion
    When I select "tags1" from autosuggestion
    Then I should see "tags1" added in the tag field
    When I enter more than 15 tags
#    Then I should see adding tag is not allowed