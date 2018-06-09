package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT17.R176;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CourseOutline;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CreateQuestionCMS;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.NewQuestionDataEntry;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by Dharaneesh TGowda on 24-Oct-14.AddScoreForEachQuestion
 */
public class AddScoreForEachQuestion extends Driver {

    @Test(priority = 1, enabled = true)
    public void doDiagnosticAssessment_AllQuestionTypes(){
        String actual = null;
        String expected = null;
        try {
            /*Row No  - 2 : "1. Login as a CMS user  2. Select a course and select any chapter
            3. Click on Create Practice 4. Select Adaptive Component Diagnostic from dropdown
            5. Select True/False type question"*/
            //Expected 1 - "“Score” field should be displayed  and following order should appear in question screen:
            // - Solution, - Hint, - Score, - Same but different question type parameter"
            ////Driver.startDriver();
            CreateQuestionCMS createQuestionCMS = PageFactory.initElements(driver,CreateQuestionCMS.class);
            new DirectLogin().CMSLogin();//Login as a Super Admin
            new SelectCourse().selectcourse();//Select 'Biology' Course
            new SelectCourse().selectChapterByIndex(0);// Select First Chapter
            new Click().clickbyxpath("//img[@title = 'Create Practice']");// Click on 'Create Practice' icon
            createQuestionCMS.getLink_CreateRegularAssessment().click();//Click on link 'Create Regular Assessment'
            new Click().clickByid("qtn-type-true-false-img");//Click on 'True False Question' Image
            actual = new TextFetch().textfetchbyxpath("//div[@id='score']/h4");//Fetch the text 'Score'
            expected ="Score";
            Assert.assertEquals(expected,actual,"Score field is not displayed");
            List<String> commonParametersList = new TextFetch().textfetchbylistxpathwithoutindex("//div[@id ='commonparameters']//div[starts-with(@class,'parameter')]");//Get All the Common Parameters List in the list
            if(!(commonParametersList.get(2).equals("Solution\n"+ "Enter Solution Text...")&&commonParametersList.get(3).equals("Hint\n" + "Enter Hint Text...")&&commonParametersList.get(4).equals("Score")&&commonParametersList.get(5).contains("Same But Different Question Type Parameters:"))){
                Assert.fail("Standard order is not appeared in the question screen:");
            }


            //Expected 2 - Default value of Score should be 1
            actual = new TextFetch().textFetchByXPathWithAttribute("//input[@id='questionScore']","value");
            expected = "1";
            Assert.assertEquals(actual,expected,"Default value of Score is not 1");
        }catch(Exception e){
            Assert.fail("Exception in testcase 'doDiagnosticAssessment_AllQuestionTypes' in class 'AddScoreForEachQuestion'",e);
        }
    }



    @Test(priority = 2, enabled = true)
    public void doPracticeAssessment_AllQuestionTypes(){
        String actual = null;
        String expected = null;
        try {
           /* Row No  - 22 : "1. Login as a CMS user  2. Select a course and select any chapter
            3. Click on Create Practice  4. Select Adaptive Component Practice from dropdown
            5. Select True/False type question"*/
            //Expected 1 - "“Score” field should be displayed  and following order should appear in question screen:
            // - Solution, - Hint, - Score, - Same but different question type parameter"

            //Driver.startDriver();
            CreateQuestionCMS createQuestionCMS = PageFactory.initElements(driver,CreateQuestionCMS.class);
            new DirectLogin().CMSLogin();//Login as a Super Admin
            new SelectCourse().selectcourse();//Select 'Biology' Course
            new SelectCourse().selectChapterByIndex(0);// Select First Chapter
            Thread.sleep(2000);
            new Click().clickbyxpath("//img[@title = 'Create Practice']");// Click on 'Create Practice' icon
            createQuestionCMS.getLink_CreateRegularAssessment().click();//Click on link 'Create Regular Assessment'
            new Click().clickbylinkText("Adaptive Component Diagnostic");//Select 'Adaptive Component Diagnostic'
            new Click().clickbylinkText("Adaptive Component Practice");//Select 'Adaptive Component Practice'
            new Click().clickByid("qtn-type-true-false-img");//Click on 'True False Question' Image
            actual = new TextFetch().textfetchbyxpath("//div[@id='score']/h4");//Fetch the text 'Score'
            expected ="Score";
            Assert.assertEquals(expected,actual,"Score field is not displayed");
            List<String> commonParametersList = new TextFetch().textfetchbylistxpathwithoutindex("//div[@id ='commonparameters']//div[starts-with(@class,'parameter')]");//Get All the Common Parameters List in the list
            if(!(commonParametersList.get(2).equals("Solution\n"+ "Enter Solution Text...")&&commonParametersList.get(3).equals("Hint\n" + "Enter Hint Text...")&&commonParametersList.get(4).equals("Score")&&commonParametersList.get(5).contains("Same But Different Question Type Parameters:"))){
                Assert.fail("Standard order is not appeared in the question screen:");
            }

            //Expected 2 - Default value of Score should be 1
            actual = new TextFetch().textFetchByXPathWithAttribute("//input[@id='questionScore']","value");
            expected = "1";
            Assert.assertEquals(actual,expected,"Default value of Score is not 1");
        }catch(Exception e){
            Assert.fail("Exception in testcase 'doDiagnosticAssessment_AllQuestionTypes' in class 'AddScoreForEachQuestion'",e);
        }
    }


    @Test(priority = 3, enabled = true)
    public void doStaticPracticeAssessment_AllQuestionTypes(){


        String actual = null;
        String expected = null;
        String assessmentName = null;
        try {
            //Driver.startDriver();

            CourseOutline courseOutline = PageFactory.initElements(driver,CourseOutline.class);
            CreateQuestionCMS createQuestionCMS = PageFactory.initElements(driver,CreateQuestionCMS.class);
            NewQuestionDataEntry newQuestionDataEntry = PageFactory.initElements(driver,NewQuestionDataEntry.class);
           /* Row No  - 42 : "1. Login as a CMS user 2. Select a course and select any chapter
            3. Click on Create Practice  4. Select Static Practice from dropdown and do not click on checkbox for Reserved for assignment
            5. Select True/False type question"*/
            //Expected 1 - "“Score” field should be displayed  and following order should appear in question screen:
            // - Solution, - Hint, - Score, - Same but different question type parameter"
            new DirectLogin().CMSLogin();//Login as a Super Admin
            new SelectCourse().selectcourse();//Select 'Biology' Course
            new SelectCourse().selectChapterByIndex(0);// Select First Chapter
            Thread.sleep(2000);
            courseOutline.get_CreatePracticeLink().click(); //Click on Create Practice Link
            createQuestionCMS.getLink_CreateRegularAssessment().click();//Click on link 'Create Regular Assessment'
            createQuestionCMS.expand_AssignmentTypeDropDown().click(); //Expand Assignment Type Dropdown
            createQuestionCMS.select_staticPracticeAssignmentType().click(); // //Select 'Static Practice'
            createQuestionCMS.getButton_trueFalseQuestion().click();//Click on 'True False Question' button

            actual = newQuestionDataEntry.getLabel_ScoreText().getText();//Fetch the text 'Score'
            expected ="Score";
            Assert.assertEquals(expected,actual,"Score field is not displayed");

            List<String> commonParametersList = new TextFetch().textfetchbylistxpathwithoutindex(NewQuestionDataEntry.commonParams);//Get All the Common Parameters List in the list
            if(!(commonParametersList.get(2).equals("Solution\n"+ "Enter Solution Text...")&&commonParametersList.get(3).equals("Hint\n" + "Enter Hint Text...")&&commonParametersList.get(4).equals("Score")&&commonParametersList.get(5).contains("Same But Different Question Type Parameters:"))){
                Assert.fail("Standard order is not appeared in the question screen:");
            }

            //Expected 2 - Default value of Score should be 1
            actual = newQuestionDataEntry.get_QuestionScore().getAttribute("value");
            expected = "1";
            Assert.assertEquals(actual,expected,"Default value of Score is not 1");

            //Row No 44 - "5. Fill all the details and set the Score as 0 or negative   6. Click on Save button"
            //Expected - Following robo-notification should appear “You should provide a positive score greater than zero. Please choose any other value.”
            assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(44));
            String questionSet = ReadTestData.readDataByTagName("", "questionset", Integer.toString(44));
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(44));

            newQuestionDataEntry.textField_assessmentName().sendKeys(assessmentName); //Type Assessment Name
            newQuestionDataEntry.textField_questionSetName().sendKeys(questionSet); //Type Question Set Name
            newQuestionDataEntry.textField_questionText().sendKeys(questionText); //Type Question Text

            newQuestionDataEntry.button_trueFalseAnswerLabel().click();// Click on 'True False' Image Icon
            newQuestionDataEntry.textField_SolutionText().sendKeys("Text Solution"); // Type 'Text Solution'

            new TextSend().textsendbyid("Text Hint", "content-hint");// Type 'Hint'
            driver.findElement(By.id("questionScore")).clear();// Clears 'Score' Field
            new TextSend().textsendbyid("0","questionScore");// Set the Score Field by '0'

            new Click().clickByid("saveQuestionDetails1");// Save the Question
            Thread.sleep(1000);
            actual = new TextFetch().textfetchbyxpath("//div[@id='cms-notification-dialog']//span");// Fetch the text fom 'Notification Dialog'
            expected = "You should provide a positive score greater than zero. Please choose any value between 0 and 999.";
            Assert.assertEquals(actual,expected,"Robo-notification has not appeared with the message “You should provide a positive score greater than zero. Please choose any value between 0 and 999.”");

            //Row No - 45 : "7. Set the score value greater than 0 and less than 100  8. Click on Save button"
            //Expected 1-    Author should be able to save the score successfully
            driver.findElement(By.id("questionScore")).clear();// Clears the 'score'
            new TextSend().textsendbyid("50","questionScore");// Type the 'Score'
            new Click().clickByid("saveQuestionDetails1");// Save the Question
            Thread.sleep(1000);
            actual = new TextFetch().textFetchByXPathWithAttribute("//input[@id='questionScore']","value");// Fetch the text from 'Score' Text Field
            expected  = "50";
            System.out.println("Actual : " + actual);
            System.out.println("expected : " + expected);
            Assert.assertEquals(actual,expected,"Author is not able to save the score successfully");

            //Expected 2 - If Score value is set with decimal digits, then the number of significant digits supported should be one.




            //Row No - 47 : 9. Edit the question
            //Expected - Score field should be displayed and author should be able to update the score successfully
            questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(47));
            driver.findElement(By.id("question-raw-content")).clear();// Clear the Text Area for 'Question Text Area'
            new TextSend().textsendbyid(questionText,"question-raw-content");// Type the Question for 'Question Text Area'
            actual = new TextFetch().textfetchbyxpath("//div[@id='score']/h4");//Fetch the text 'Score'
            expected ="Score";
            Assert.assertEquals(expected,actual,"Score field is not displayed");
            driver.findElement(By.id("questionScore")).clear(); //Clear the Score
            String score = ReadTestData.readDataByTagName("", "score", Integer.toString(49));
            new TextSend().textsendbyid(score,"questionScore");//Type the Score
            new Click().clickbylinkText("Draft"); //click on Draft option
            new Click().clickbylinkText("Publish");
            new Click().clickByid("saveQuestionDetails1");// Click on Save Button
            Thread.sleep(1000);
            actual = new TextFetch().textFetchByXPathWithAttribute("//input[@id='questionScore']","value");// Fetch the text from 'Score' Text Field
            expected  = score;
            System.out.println("Actual : " + actual);
            System.out.println("expected : " + expected);
            Assert.assertEquals(actual,expected,"Author is not able to save the score successfully");

            //Row No - 49 : 10. Create Multiple Selection type question in the same assessment
            // Expected - Score field should be displayed and author should be able to update the score successfully
            new Assignment().addQuestions(49,"multipleselection","");
            validateScoreField();

            //Row No - 50 : 11. Create Text Entry type question in the same assessment
            //Expected - Score field should be displayed and author should be able to update the score successfully
            new Assignment().addQuestions(49,"textentry","");
            validateScoreField();

            //Row No - 51 : 12. CreateText Dropdown type question in the same assessment
            //Expected -  Score field should be displayed and author should be able to update the score successfully
            new Assignment().addQuestions(49,"textdropdown","");
            validateScoreField();


            //Row No - 53 : 14. Create Numeric Entry w/ Units type question in the same assessment
            //Expected - Score field should be displayed and author should be able to update the score successfully
            new Assignment().addQuestions(49,"numericentrywithunits","");
            validateScoreField();


            //Row No - 54 : 15. Create Maple Numeric type question in the same assessment
            //Expected - Score field should be displayed and author should be able to update the score successfully
            new Assignment().addQuestions(49,"advancednumeric","");
            validateScoreField();


            //Row No - 55 : 16. Create Maple Symbolic Notation type question in the same assessment
            //Expected - Score field should be displayed and author should be able to update the score successfully
            new Assignment().addQuestions(49,"expressionevaluator","");
            validateScoreField();


            //Row No - 58 : 19. Create Essay Type Question type question in the same assessment
            //Expected - Score field should be displayed and author should be able to update the score successfully
            new Assignment().addQuestions(49,"essay","");
            validateScoreField();



            //Row No -60 : 21. Create Writeboard type question in the same assessment
            //Expected : Score field should be displayed and author should be able to update the score successfully

            new Assignment().addQuestions(49,"writeboard","");
            validateScoreField();


            //Row No - 61 : "22. Login as a student 23. Go to eTextbook and start the static practice for which the author has set the score to 3"
            //Expected - In Performance tab, "Points Available:" should be displayed with correct Score
            assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(49));
            score = ReadTestData.readDataByTagName("", "score", Integer.toString(49));
            new LoginUsingLTI().ltiLogin("61");// Login as a 'Student'
            new Navigator().NavigateTo("e-Textbook");
            //new Click().clickbyxpath("//a[normalize-space(@title) = '"+assessmentName+"']");// Click on assessment name which was created by Author
            Thread.sleep(10000);
            WebElement we=driver.findElement(By.linkText(assessmentName));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);
            Thread.sleep(2000);

//            new Click().clickbylinkText(assessmentName);
            if(driver.findElements(By.className("static-assessment-retake")).size()!=0){
                new Click().clickByclassname("static-assessment-retake");// Click on 'Retake' button to retake the assessment by Student
            }
            actual = new TextFetch().textfetchbyxpath("//div[@class='static-assessment-point-content-box']");// Fetch the Value from 'Points Available : ' Label
            expected = "Points Available : "+ score;
            Assert.assertEquals(actual,expected,"\"Points Available\" is not showing the score in which author has updated in CMS 2");
            System.out.println("actual 2: " + actual);
            System.out.println("expected 2 : " + expected);

            // Row No - 63 : 24. Submit the question and go to performance summary page
            //expected - Question bar should also show the Scoring Points as per the updated Score
            new StaticAssignmentSubmit().staticAssesment();// Submit the static assessment by Student




            //Row No - 64 : 25. Go to proficiency report page and click on the question card of static practice assessment
            //Expected - In About tab, "Points" should be displayed with correct Score
//            new Navigator().NavigateTo("Proficiency Report");// navigate to 'Proficiency Report'
            new QuestionCard().clickOnCard("61",0);// Click on first Question in 'Question Card'
            actual = new TextFetch().textfetchbyclass("question-card-difficulty-level");// Fetch the text from 'Points :' Label
            System.out.println("Actual Marks :" + actual);
            score = ReadTestData.readDataByTagName("", "score", Integer.toString(49));
            expected = ("Points : "+score);
            System.out.println("actual :" + actual);
            Assert.assertEquals(actual,expected,"\"Points Available\" is not showing the score which author has updated in CMS");

            //Row - 65 : "26. Login as instructor  27. Go to Assignments->Question Banks page
            //28. Click on Create Custom Assignment button  29. Search the static practice assessment question and go to Selected questions tab"
            //Expected - Under Selected questions tab, "Points Available" should show the score which author has updated in CMS
            new UpdateContentIndex().updatecontentindex("65");
            new LoginUsingLTI().ltiLogin("65"); // Login as Instructor
            new Navigator().NavigateTo("Question Banks");// Navigates to 'Question Banks'
            new Click().clickbyxpath("(//div[@id = 'customAssignment'])[2]");// Click on 'Create Custom Assignment' Button
            new Click().clickByclassname("ls-ins-search-text");//Click on 'Search' Icon
            String searchKey = ReadTestData.readDataByTagName("", "searchKey", Integer.toString(49));
            new TextSend().textSendByXpath("\"" + searchKey + "\"" ,"//input[@placeholder = 'Search question statements...']");//Type a Question
            new Click().clickbyxpath("(//div[@class='ls-ins-left']//i)[2]");//Click on 'Search' Icon
            Thread.sleep(5000);
            List<WebElement> quesionsCheckBoxList = driver.findElements(By.xpath("//div[@class='ls-ins-customize-checkbox-small']//label"));// Store the list of Question Checkbox's elements
            we = null;
            for(int a=0;a<quesionsCheckBoxList.size();a++){
                we= quesionsCheckBoxList.get(a);
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);
                Thread.sleep(1000);
            }
            new UIElement().waitAndFindElement(By.xpath("//div[@class='tab']/span[contains(@title,'Selected Questions')]"));
            new Click().clickbyxpath("//div[@class='tab']/span[contains(@title,'Selected Questions')]");//Click on 'Selected Questions' Tab
            List<WebElement> scoreFieldList = driver.findElements(By.xpath("//div[@id = 'ls-ins-your-question-scoring-data']/input"));// Store the list of Question Score Field's elements
            for(int a=0;a<scoreFieldList.size();a++){
                actual =   new TextFetch().textFetchByWebElementWithAttribute(scoreFieldList.get(a),"value");
                score = ReadTestData.readDataByTagName("", "score", Integer.toString(49));
                expected =score;
                Assert.assertEquals(actual,expected,"\"Points Available\" is not showing the score which author has updated in CMS");
                new TextSend().textSendByWebElement("4",scoreFieldList.get(a));//Type 'Points Available' Field with oter value
            }


            //Row No - 66 : "30. Edit the Score and set the other value 31. Save the assignment and assign it to class
            //32. Login as student  33. Start the assignment"
            //Expected - In About tab, "Points Available" should displayed the updated Score
            ((JavascriptExecutor) driver).executeScript("scroll(250,0);");// Scroll up to 0 from 250
            new Click().clickByid("ls-ins-assignment-name");// Click on ''Click to enter assignment name...'
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(65));
            new TextSend().textsendbyid(assessmentname, "ls-ins-edit-assignment");// type the assignment name in 'Click to enter assignment name...' textfield
            driver.findElement(By.xpath("//span[text() = 'SAVE FOR LATER']")).click();//Click on 'Save for Later' button
            Thread.sleep(1000);
            new Click().clickBycssselector("div[data-id='my-resources']");//Click on 'My Library' Tab
            Thread.sleep(2000);
            new AssignLesson().Assigncustomeassignemnt(65);// Assign the assignment to a 'class'/*
            new LoginUsingLTI().ltiLogin("61");//Login as Student
            Thread.sleep(10000);
            new Assignment().openAssignmentFromCourseStream("61");//Starts Assignment
            //actual =  new TextFetch().textfetchbyxpath("//div[@class = 'static-assessment-point-content-box']");
            actual =  new TextFetch().textfetchbyxpath("//div[@class = 'static-assessment-point-content-box']");
            //actual = new TextFetch().textfetchbyclass("question-card-difficulty-level");// Fetch the text from 'Points :' Label

            score = ReadTestData.readDataByTagName("", "score", Integer.toString(65));
            expected ="Points Available : " + score;
            Assert.assertEquals(actual,expected,"\"Points Available\" is not showing the score which author has updated in CMS");


            // Row No - 67 : "34. Complete the assignment 35. Go to Performance summary page"
            //Expected - Question bar should also show the Scoring Points as per the updated Score
            new Assignment().submitAssignmentAsStudent(65);


            //Row No - 68 : "36. Login as instructor again 37. Go to view response page of assignment
            //38. Click on view response for a question"
            // Expected : Total Score in denominator should be displayed as updated score in view response tab of any question
            new LoginUsingLTI().ltiLogin("65"); // Login as Instructor
            new Navigator().NavigateTo("Summary");// Navigates to 'Summary'
            new Click().clickbyxpath("//span[@title = 'View Student Responses']");// Click on 'View Student Responses'
            System.out.println("Special Score :"+score);
            int updatedScore =Integer.parseInt(score);
            System.out.println("updatedScore:"+updatedScore);
            expected ="Total Points: "+ (updatedScore * scoreFieldList.size());
            actual = new TextFetch().textfetchbycssselector("span[class = 'ls-assignment-grading-title ls-assignment-total-points']");
            Assert.assertEquals(actual,expected,"In View response Page, Total Points are not appearing as updated ");



            //Row No -  69 : "39. Login as student again 40. Go to proficiency report page
            // 41. Click on the Question Card"
            //Expected - In About tab, Points should displayed the updated Score

            new LoginUsingLTI().ltiLogin("61");// Login as a 'Student'
            new Navigator().NavigateTo("Proficiency Report");// Na
            new QuestionCard().clickOnCard("61",0);// Click on first question Card
            actual = new TextFetch().textfetchbyclass("static-assessment-point-content-box");
            //actual = new TextFetch().textfetchbyclass("question-card-difficulty-level");// Fetch the text from 'Points :' Label
            expected = "Points: 3";
            System.out.println("actual :" + actual);
            Assert.assertEquals(actual,expected,"\"Points\" is not showing the score which author has updated in CMS");


        }catch(Exception e){
            Assert.fail("Exception in testcase 'doStaticPracticeAssessment_AllQuestionTypes' in class 'AddScoreForEachQuestion'",e);
        }
    }


    @Test(priority = 4, enabled = true)
    public void doStaticAssessmentWithInstrucorTypeOnly(){
        String actual =null;
        String expected =null;
        try{


            //Row No - 70 : "1. Login as instructor  2. Go to Assignments->Question Banks page
            //3. Click on 'Customize this' for the same static assessment which is created by author
            // 4. Select the question and go to Selected questions tab"
            //Expected - Under Selected questions tab, "Points Available" should show the score which author has updated in CMS
            String updatedScore = ReadTestData.readDataByTagName("", "score", Integer.toString(65));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(70));

            new DirectLogin().CMSLogin();
            new Assignment().create(70);
            new Assignment().addQuestions(70,"textentry","");
            new Assignment().addQuestions(70,"multipleselection","");
            new Assignment().addQuestions(70,"textentry","");
            new Assignment().addQuestions(70,"textdropdown","");
            new Assignment().addQuestions(70,"numericentrywithunits","");
            new Assignment().addQuestions(70,"advancednumeric","");
            new Assignment().addQuestions(70,"essay","");
            new Assignment().addQuestions(70,"expressionevaluator","");
            new LoginUsingLTI().ltiLogin("70_1");
            new LoginUsingLTI().ltiLogin("70");
            new Navigator().NavigateTo("Question Banks");
            new Assignment().searchAssessmentInQuestionBanks(70);
            Thread.sleep(3000);
            new Click().clickbyxpath("//span[@title = 'Customize This']");
            Thread.sleep(2000);
            List<WebElement> quesionsCheckBoxList = driver.findElements(By.xpath("//div[@class='ls-ins-customize-checkbox-small']//label"));
            WebElement we = null;
            for(int a=0;a<quesionsCheckBoxList.size();a++){
                we= quesionsCheckBoxList.get(a);
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);
                Thread.sleep(1000);
            }
            new Click().clickbyxpath("//div[@class='tab']/span[contains(@title,'Selected Questions')]");//Click on 'Selected Questions' Tab
            List<WebElement> scoreFieldList = driver.findElements(By.xpath("//div[@id = 'ls-ins-your-question-scoring-data']/input"));
            for(int a=0;a<scoreFieldList.size();a++){
                actual =   new TextFetch().textFetchByWebElementWithAttribute(scoreFieldList.get(a),"value");
                expected =ReadTestData.readDataByTagName("", "score", Integer.toString(70));
                Assert.assertEquals(actual,expected,"\"Points Available\" is not showing the score which author has updated in CMS");
                new TextSend().textSendByWebElement(updatedScore,scoreFieldList.get(a));//Type 'Points Available' Field with oter value
            }




            //Row No - 71 : "5. Edit the Score and set the other value 6. Save the assignment and assign it to class as gradable assignment
            //7. Login as student  8. Start the assignment"
            ((JavascriptExecutor) driver).executeScript("scroll(250,0);");
            new Click().clickByid("ls-ins-assignment-name");// Click on ''Click to enter assignment name...'
            assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(75));
            System.out.println("assessmentname 70" +assessmentName);
            new TextSend().textsendbyid(assessmentName, "ls-ins-edit-assignment");// type the assignment name in 'Click to enter assignment name...' textfield
            driver.findElement(By.xpath("//span[text() = 'SAVE FOR LATER']")).click();//Click on 'Save for Later' button
            Thread.sleep(1000);
            new Click().clickBycssselector("div[data-id='my-resources']");//Click on 'My Library' Tab
            new AssignLesson().Assigncustomeassignemnt(70);// Assign the assignment to a 'class'*//*
            new LoginUsingLTI().ltiLogin("70_1");//Login as Student
            new Assignment().openAssignmentFromCourseStream("61");//Starts Assignment
            actual =  new TextFetch().textfetchbyxpath("//div[@class = 'static-assessment-point-content-box']");
            expected ="Points Available : " + updatedScore;
            Assert.assertEquals(actual, expected, "\"Points Available\" is not showing the score which author has updated in CMS");


            //Row - 72  : "9. Complete the assignment 10. Go to Performance summary page"
            //Expected - Question bar should also show the Scoring Points as per the updated Score
            new Assignment().submitAssignmentAsStudent(75);




            //Row No - 73 : "11. Login as instructor again  12. Go to view response page of assignment
            //13. Click on view response for a question"
            //Expected - Total Score in denominator should be displayed as updated Score in view response tab of any question
            new LoginUsingLTI().ltiLogin("70");// Login as Instructor
            new Navigator().NavigateTo("Summary");//Click on 'Summary' Page
            new Click().clickbyxpath("//span[@title = 'View Student Responses']");//Click on view response for a question"
            int updatedScoreInt = Integer.parseInt(updatedScore);
            expected ="Total Points: "+ (updatedScoreInt * scoreFieldList.size());
            actual = new TextFetch().textfetchbycssselector("span[class = 'ls-assignment-grading-title ls-assignment-total-points']");
            Assert.assertEquals(actual,expected,"In View response Page, Total Points are not appearing as updated ");


            //Row No - 74 : "14. Login as student again 15. Go to proficiency report page
            //16. Click on the Question Card"
            //Expected - In About tab, Points should displayed the updated Score
            new Click().clickbyxpath("//div[@title = 'Release Grade for All']");
            new LoginUsingLTI().ltiLogin("70_1");//Login as Student
            new Navigator().NavigateTo("Proficiency Report");
            new QuestionCard().clickOnCard("70",0);
            actual = new TextFetch().textfetchbyclass("static-assessment-point-content-box");
            expected = "Points: "+updatedScore;
            System.out.println("actual :" + actual);
            Assert.assertEquals(actual,expected,"\"Points Available\" is not showing the score which author has updated in CMS");


            /*Row n0 - 75 : "17. Login as instructor
            18. Go to Assignments->Question Banks page
            19. Click on 'Customize this' for the same static assessment which is created by author
            20. Select the question and go to Selected questions tab
            21. Edit the Score and set the other value
            22. Save the assignment and assign it to class as non- gradable assignment
            23. Login as student and start the assignment"*/
            //Expected - In About tab, "Points Available" should displayed the updated Score
            new LoginUsingLTI().ltiLogin("75_1");//Login as Student
            new LoginUsingLTI().ltiLogin("75");//Login as Instructor
            new Navigator().NavigateTo("Question Banks");
            new Assignment().searchAssessmentInQuestionBanks(70);
            new Click().clickbyxpath("//span[@title = 'Customize This']");
            quesionsCheckBoxList = driver.findElements(By.xpath("//div[@class='ls-ins-customize-checkbox-small']//label"));
            for(int a=0;a<quesionsCheckBoxList.size();a++){
                we= quesionsCheckBoxList.get(a);
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);
                Thread.sleep(1000);
            }
            new Click().clickbyxpath("//div[@class='tab']/span[contains(@title,'Selected Questions')]");//Click on 'Selected Questions' Tab
            scoreFieldList = driver.findElements(By.xpath("//div[@id = 'ls-ins-your-question-scoring-data']/input"));
            for(int a=0;a<scoreFieldList.size();a++){
                actual =   new TextFetch().textFetchByWebElementWithAttribute(scoreFieldList.get(a),"value");
                expected =ReadTestData.readDataByTagName("", "score", Integer.toString(70));
                Assert.assertEquals(actual,expected,"\"Points Available\" is not showing the score which author has updated in CMS");
                new TextSend().textSendByWebElement(updatedScore,scoreFieldList.get(a));//Type 'Points Available' Field with oter value
            }
            ((JavascriptExecutor) driver).executeScript("scroll(250,0);");
            new Click().clickByid("ls-ins-assignment-name");// Click on ''Click to enter assignment name...'
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(76));
            new TextSend().textsendbyid(assessmentname, "ls-ins-edit-assignment");// type the assignment name in 'Click to enter assignment name...' textfield
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[text() = 'SAVE FOR LATER']")).click();//Click on 'Save for Later' button
            Thread.sleep(2000);
            new Click().clickBycssselector("div[data-id='my-resources']");//Click on 'My Library' Tab
            new AssignLesson().Assigncustomeassignemnt(75);// Assign the assignment to a 'class'*//*
            new LoginUsingLTI().ltiLogin("75_1");//Login as Student
            new Assignment().openAssignmentFromCourseStream("70");//Starts Assignment
            actual =  new TextFetch().textfetchbyxpath("//div[@class = 'static-assessment-point-content-box']");
            expected ="Points Available : "+updatedScore;
            Assert.assertEquals(actual,expected,"\"Points Available\" is not showing the score which author has updated in CMS");
            System.out.println("actual 123 is :" +actual );


            //Row N0 - 76 : "24. Complete the assignment  25. Go to Performance summary page"
            //Expected - Question bar should also show the Scoring Points as per the updated Score
            new Assignment().submitAssignmentAsStudent(76);
            //incomplete



            //Row No - 77 : "26. Go to proficiency report page  27. Click on the Question Card"
            //Expected - In About tab, Points should displayed the updated Score
            new Navigator().NavigateTo("Proficiency Report");
            new QuestionCard().clickOnCard("70",0);
            actual = new TextFetch().textfetchbyclass("static-assessment-point-content-box");
            expected = "Points: " + updatedScore;
            System.out.println("actual :" + actual);
            Assert.assertEquals(actual,expected,"\"Points Available\" is not showing the score which author has updated in CMS");

        }catch(Exception e){
            Assert.fail("Exception testcase 'doStaticAssessmentWithInstrucorTypeOnly' in class 'AddScoreForEachQuestion'",e);
        }
    }

    private void validateScoreField(){
        String actual = null;
        String expected = null;
        String score = null;
        try {
            actual = new TextFetch().textfetchbyxpath("//div[@id='score']/h4");//Fetch the text 'Score'
            expected = "Score";
            Assert.assertEquals(expected, actual, "Score field is not displayed");
            driver.findElement(By.id("questionScore")).clear();
            score  = ReadTestData.readDataByTagName("", "score", Integer.toString(49));
            System.out.println("Score : " + score);
            new TextSend().textsendbyid(score, "questionScore");


            if(driver.findElements(By.linkText("Draft")).size()!=0){
                new Click().clickbylinkText("Draft"); //click on Draft option
                new Click().clickbylinkText("Publish");
                new Click().clickByid("saveQuestionDetails1");
                Thread.sleep(1000);
            }


            actual = new TextFetch().textFetchByXPathWithAttribute("//input[@id='questionScore']", "value");
            expected = score;
            System.out.println("Actual : " + actual);
            System.out.println("expected : " + expected);
            Assert.assertEquals(actual, expected, "Author is not able to save the score successfully");
        }catch(Exception e){
            Assert.fail("Exception in Method 'validateScoreField'in class 'validateScoreField'",e);
        }
    }

    /*@AfterMethod
    public void TearDown()throws Exception
    {
        driver.quit();
    }*/
}
