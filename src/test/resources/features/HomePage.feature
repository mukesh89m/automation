# Created by Navin

@Complete
Feature: Home Page Validations
  As User I should be able to See all the Fields on Default Explore Page
  Scenario: Verifying the Header and it's fields on home page
    Given I am on ML home page
    Then  I should see ML Logo in Header in ML home page
    Then  I should see the 'Sign Up' button in the header in ML home page
    Then  I should see the 'Login' button in the header in ML home page
    And   I should see the Lesson Filter "All" in the  page body
    And   I should see the Lesson Filter "Beginner" in the  page body
    And   I should see the Lesson Filter "Intermediate" in the  page body
    And   I should see the Lesson Filter "Expert" in the  page body
    And   I should see Lesson cards on page body

  Scenario Outline: Verify the Lessons count at filters label available and respective lesson cards are equal.
    Given I am on ML home page
    Then  I should see All filter selected by default on ML home page
    When  I click on the "<Filter Name>" in the home page
    Then  I should see the "<Filter Name>" filter is selected on ML home page
    And   I should see Tick mark before the "<Filter Name>" Label in home page
    And   I should not see tick for other than "<Filter Name>" filter label on ML home page

  Examples:
    | Filter Name  |
    | All          |
    | Beginner     |
    | Intermediate |
    | Expert       |


  Scenario Outline: I should see the resultant lesson as per the search text and Reset the Search result.
    Given I am on ML home page
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
    | Filter Name  | Search Text                 |
    | Beginner     | Beginner1234 Blesson123     |
    | Beginner     | BeginnerT1234 btag1234      |
    | Beginner     | ml                          |
    | Beginner     | BeginnerDesc123 bdesc123    |
    | Intermediate | Intermediate123 ILesson123  |
    | Intermediate | IntermediateTag123 Itag123  |
    | Intermediate | ml1                         |
    | Intermediate | IntermediateDesc123 Idesc123|
    | Expert       | ExpertLesson123 ELesson123  |
    | Expert       | ExpertTag123 Etag123        |
    | Expert       | ml3                         |
    | Expert       | ExpertDesc123 Edesc123      |