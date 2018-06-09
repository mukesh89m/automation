#Created By Navin

Feature: ML 1.4 As a user (consumer), I should be able to subscribe(Add to My library) to a particular micro lesson in “Explore” page

  Scenario:I should be able to preview lesson in “Explore” page with all UI Elements without login to application
    Given  I am on ML home page
    When   I mouse hover on lesson card thumbnail on the Authoring page and Click on preview
    Then   I should see as header label on Preview page
    And    I should see Top section of the slide with Label 'Level - <Level : as added by the author >'
    And    I should see the five star rating scale
    And    I should see the rating in number followed by the Rating label within rounded brackets
    And    I should see Top section of the slide with Label 'Free'
    And    I should see Top section of the slide with Label 'Add To My Library' button with plus mark
    And    I should see the Full screen button on the slide
    And    I should see the label 'Slide x/y' on the slide
    And    I should see the Preview button with forward icon on the slide view
    And    I should be able to navigate through all the slides of the lesson

  Scenario:I should be able to preview lesson in “Explore” page with all UI Elements after login to application
    Given  I am on loggedIn page
    When   I mouse hover on lesson card thumbnail on the Authoring page and Click on preview
    Then   I should see as header label on Preview page
    And    I should see Top section of the slide with Label 'Level - <Level : as added by the author >'
    And    I should see the five star rating scale
    And    I should see the rating in number followed by the Rating label within rounded brackets
    And    I should see Top section of the slide with Label 'Free'
    And    I should see Top section of the slide with Label 'Add To My Library' button with plus mark
    And    I should see the Full screen button on the slide
    And    I should see the label 'Slide x/y' on the slide
    And    I should see the Preview button with forward icon on the slide view
    And    I should be able to navigate through all the slides of the lesson
