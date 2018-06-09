package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.Epic46;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Generic.QuestionCreationGeneric;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.preview.PreviewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dharaneesh T Gowda on 16-01-2015.
 */
public class PassageBased extends Driver {
    String actual = null;
    String expected = null;
    boolean isDisplayed = false;


    @Test(priority = 1,enabled = true)
    public void checkDefaultLandingPageBehaviour(){
        try{
            int index = 10;
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));
            String instructions = ReadTestData.readDataByTagName("", "instructions", Integer.toString(index));
            String headerTitle = ReadTestData.readDataByTagName("", "headerTitle", Integer.toString(index));
            String passageText = ReadTestData.readDataByTagName("", "passageText", Integer.toString(index));
            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            PassageBasedQuestionCreation passageBasedQuestionCreation = PageFactory.initElements(driver, PassageBasedQuestionCreation.class);

            String appendChar = "P";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            Thread.sleep(1000);
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_passageType().click(); //Click on Icon 'Passage Based' to create the sentence response type question
            passageBasedQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);//Type an assessment name
            passageBasedQuestionCreation.getTextField_EnterInstructions().sendKeys(instructions);// Type instructions
            passageBasedQuestionCreation.getTextArea1_EnterTabContent().sendKeys(passageText + "1");// Type Passage Text
            questionCreationGeneric.getButton_Save().click();//Click on Save Button
            Thread.sleep(5000);


            //Row No - 11 : Expected - Instructor should be able to add instructions for the passages by clicking on the "Instructions" text box
            actual = passageBasedQuestionCreation.getTextField_EnterInstructions().getText();
            expected = instructions;
            Assert.assertEquals(actual,expected,"Instructor is not able to add the instructions in the instructions text box");



            //Expected - 2. The passage title for the first passage should optional
            actual = passageBasedQuestionCreation.getNotificationText().getText();
            expected = "Saved.";
            Assert.assertEquals(actual,expected,"The passage title for the first passage should optional");



            //Row No - 14 : Adding a passage text
            //Expected -  Instructors should be able to add passage text by clicking on the passage text area
            actual = passageBasedQuestionCreation.getTextArea1_EnterTabContent().getText();
            expected = passageText + "1";
            Assert.assertEquals(actual,expected,"Instructor is not able to add passage text by clicking on the passage text area");
            /*passageBasedQuestionCreation.getTab_EnterHeaderElementsList().get(0).click();// Click on Tab 'Enter Header'
            passageBasedQuestionCreation.getTab_EnterHeaderElementsList().get(0).sendKeys(headerTitle+"1");//Type passage name*/
            driver.findElement(By.xpath("//div[@data-tab = 'tab-0']//div")).click();// Click on Tab 'Enter Header'
            driver.findElement(By.xpath("//div[@data-tab = 'tab-0']//div")).sendKeys(headerTitle+"1");//Type passage name);
            questionCreationGeneric.getButton_Save().click();//Click on Save Button
            actual = passageBasedQuestionCreation.getNotificationText().getText();
            expected = "Saved.";
            Assert.assertEquals(actual,expected,"The passage title for the first passage should optional");




            //Row No - 15 : Adding more passages
            //Expected - 1.Instructor should be able to add more passages by clicking on the "+" on the tab area
            //passageBasedQuestionCreation.getIcon_PlusNewTab().click();
            for(int a=1;a<5;a++){
                passageBasedQuestionCreation.getIcon_PlusNewTab().click();
                Thread.sleep(2000);
                actual = driver.findElement(By.xpath("//div[@data-tab = 'tab-"+a+"']//div")).getAttribute("placeholder");
                expected = "Passage Title";
                Assert.assertEquals(actual,expected,"Instructor is not able to create the new tabs");
                List<WebElement> all = driver.findElements(By.cssSelector("div[class='editable-question-content f43 editable question-edit-passage-text redactor_editor']"));
                actual = all.get(a).getText();
                expected = "Enter Passage";
                Assert.assertEquals(actual,expected,"Instructor is not able to add the content in the passage");

                driver.findElement(By.xpath("//div[@data-tab = 'tab-"+a+"']//div")).click();// Click on Tab 'Enter Header'
                driver.findElement(By.xpath("//div[@data-tab = 'tab-"+a+"']//div")).sendKeys(headerTitle + (a + 1));//Type passage name
                all = driver.findElements(By.cssSelector("div[class='editable-question-content f43 editable question-edit-passage-text redactor_editor']"));
                all.get(a).sendKeys(passageText +(a+1));
            }
            questionCreationGeneric.getButton_Save().click();//Click on Save Button
        }catch(Exception e){
            Assert.fail("Exception in testcase 'checkDefaultLandingPageBehaviour' in the class 'PassageBased'",e);
        }
    }





    @Test(priority = 2,enabled = true)
    public void checkAddingMorePassages(){
        try{
            int index = 15;

            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));
            String instructions = ReadTestData.readDataByTagName("", "instructions", Integer.toString(index));
            String headerTitle = ReadTestData.readDataByTagName("", "headerTitle", Integer.toString(index));
            String passageText = ReadTestData.readDataByTagName("", "passageText", Integer.toString(index));

            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            PassageBasedQuestionCreation passageBasedQuestionCreation = PageFactory.initElements(driver, PassageBasedQuestionCreation.class);

            String appendChar = "H";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            Thread.sleep(1000);
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_passageType().click(); //Click on Icon 'Passage Based' to create the sentence response type question
            passageBasedQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);//Type an assessment name
            passageBasedQuestionCreation.getTextField_EnterInstructions().sendKeys(instructions);// Type instructions
            passageBasedQuestionCreation.getTextArea1_EnterTabContent().sendKeys(passageText + "1");// Type Passage Text
            driver.findElement(By.xpath("//div[@data-tab = 'tab-0']//div")).click();// Click on Tab 'Enter Header'
            driver.findElement(By.xpath("//div[@data-tab = 'tab-0']//div")).sendKeys(headerTitle+"1");//Type passage name);
            questionCreationGeneric.getButton_Save().click();//Click on Save Button
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[text() = 'Saved.']")));
            //Row No - 15  : Adding more passages
            //Expected - 1.Instructor should be able to add more passages by clicking on the "+" on the tab area
            /*Row N0 - 16:  "2. A new passage text area should be created specific to this Tab
            3. Instructor should be able to enter the passage text into this passage text area"*/
            //Row No - 18 : Expected - 5. Instructor should be able to add the passage title by double clicking on the Tab box
            for(int a=1;a<5;a++){
                passageBasedQuestionCreation.getIcon_PlusNewTab().click();
                Thread.sleep(2000);
                driver.findElement(By.xpath("//div[@data-tab = 'tab-"+a+"']//div")).click();// Click on Tab 'Enter Header'
                driver.findElement(By.xpath("//div[@data-tab = 'tab-" + a + "']//div")).sendKeys(headerTitle + (a + 1));//Type passage name
                List<WebElement> all = driver.findElements(By.cssSelector("div[class='editable-question-content f43 editable question-edit-passage-text redactor_editor']"));
                all.get(a).sendKeys(passageText +(a+1));
            }
            questionCreationGeneric.getButton_Save().click();//Click on Save Button
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[text() = 'Saved.']")));

            actual = passageBasedQuestionCreation.getNotificationText().getText();
            expected = "Saved.";
            Assert.assertEquals(actual,expected,"Instructor is not able to add more passages by clicking on the \"+\" on the tab area ");



            /*Expected - 16 : "2. A new passage text area should be created specific to this Tab
            3. Instructor should be able to enter the passage text into this passage text area"*/
            for(int a=0;a<4;a++){
                driver.findElement(By.xpath("//div[@data-tab = 'tab-" + a + "']//div")).click();// Click on Tab 'Enter Header'
                List<WebElement> all = driver.findElements(By.cssSelector("div[class='editable-question-content f43 editable question-edit-passage-text redactor_editor']"));
                actual = all.get(a).getText();
                expected = passageText +(a+1);
                Assert.assertEquals(actual,expected,"Instructor is not able to enter the passage text into this passage text area");
            }


            //Row No - 17 : 4. If there are more than 1 tab, then adding the passage title is mandatory
            List<WebElement> removeTabElementsList = driver.findElements(By.className("remove-tab"));
            for(int a=0;a<4;a++){
                removeTabElementsList.get(a).click();
            }
            Thread.sleep(5000);
            for(int a=0;a<3;a++){
                passageBasedQuestionCreation.getIcon_PlusNewTab().click();
                //Thread.sleep(2000);
                driver.findElement(By.xpath("//div[@data-tab = 'tab-"+(a+1)+"']//div")).click();// Click on Tab 'Enter Header'
                //Thread.sleep(2000);
                driver.findElement(By.xpath("//div[@data-tab = 'tab-" + (a+1)+ "']//div")).sendKeys(headerTitle + (a + 1));//Type passage name
                //Thread.sleep(2000);
                List<WebElement> all  = driver.findElements(By.cssSelector("div[class='editable-question-content f43 editable question-edit-passage-text redactor_editor']"));
                all.get(a + 1).sendKeys(passageText + (a + 1));
            }
            passageBasedQuestionCreation.getIcon_PlusNewTab().click();
            questionCreationGeneric.getButton_Save().click();//Click on Save Button
            actual = passageBasedQuestionCreation.getNotificationText().getText();
            expected = "Tab title and it's content should not be empty.";
            Assert.assertEquals(actual,expected,"If there are more than 1 tab, then adding the passage title is mandatory");
        }catch(Exception e){
            Assert.fail("Exception in testcase 'checkDefaultLandingPageBehaviour' in the class 'PassageBased'",e);
        }
    }


    @Test(priority = 3,enabled = true)
    public void deletingPassage(){
        try{
            int index = 19;

            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));
            String instructions = ReadTestData.readDataByTagName("", "instructions", Integer.toString(index));
            String headerTitle = ReadTestData.readDataByTagName("", "headerTitle", Integer.toString(index));
            String passageText = ReadTestData.readDataByTagName("", "passageText", Integer.toString(index));
            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            PassageBasedQuestionCreation passageBasedQuestionCreation = PageFactory.initElements(driver, PassageBasedQuestionCreation.class);

            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            Thread.sleep(1000);
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_passageType().click(); //Click on Icon 'Passage Based' to create the sentence response type question
            passageBasedQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);//Type an assessment name
            passageBasedQuestionCreation.getTextField_EnterInstructions().sendKeys(instructions);// Type instructions
            passageBasedQuestionCreation.getTextArea1_EnterTabContent().sendKeys(passageText + "1");// Type Passage Text
            passageBasedQuestionCreation.getTab_EnterHeaderElementsList().get(0).click();// Click on Tab 'Enter Header'
            passageBasedQuestionCreation.getTab_EnterHeaderElementsList().get(0).sendKeys(headerTitle+"1");//Type passage name
            questionCreationGeneric.getButton_Save().click();//Click on Save Button
            Thread.sleep(5000);
            for(int a=0;a<4;a++){
                passageBasedQuestionCreation.getIcon_PlusNewTab().click();
                actual = passageBasedQuestionCreation.getTab_EnterHeaderElementsList().get(a+1).getAttribute("placeholder");
                expected = "Passage Title";
                Assert.assertEquals(actual,expected,"Instructor is not able to create the new tabs");
                List<WebElement> all = driver.findElements(By.cssSelector("div[class='editable-question-content f43 editable question-edit-passage-text redactor_editor']"));
                actual = all.get(a+1).getText();
                expected = "Enter Passage";
                Assert.assertEquals(actual,expected,"Instructor is not able to add the content in the passage");
                passageBasedQuestionCreation.getTab_EnterHeaderElementsList().get(a+1).click();// Click on Tab 'Enter Header'
                passageBasedQuestionCreation.getTab_EnterHeaderElementsList().get(a+1).sendKeys(headerTitle+(a+2));//Type passage name
                all = driver.findElements(By.cssSelector("div[class='editable-question-content f43 editable question-edit-passage-text redactor_editor']"));
                all.get(a+1).sendKeys(passageText +(a+2));


            }
            questionCreationGeneric.getButton_Save().click();//Click on Save Button*/

            //Row No - Deleting the passage
            /*Expected : "1. Every passage tab should display 'X' icon, should turn red on hover
            2. Clicking on 'X' should delete the Tab"*/
            List<WebElement> removeTabElementsList = driver.findElements(By.className("remove-tab"));
            for(int a=0;a<4;a++){
                removeTabElementsList.get(a).click();
            }



            //Row No - 20 : 3. Instructor should not be able to delete the last remaining tab
            if(passageBasedQuestionCreation.getIcon_removeTab().isDisplayed()){
                Assert.fail("Instructor should not be able to delete the last remaining tab");

            }
        }catch(Exception e){
            Assert.fail("Exception in testcase 'deletingPassage' in the class 'PassageBased'",e);
        }
    }









    @Test(priority =4, enabled = true)
    public void checkTabWidthAndTabManagement(){
        try{
            int index = 22;
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));
            String instructions = ReadTestData.readDataByTagName("", "instructions", Integer.toString(index));
            String headerTitle = ReadTestData.readDataByTagName("", "headerTitle", Integer.toString(index));
            String passageText = ReadTestData.readDataByTagName("", "passageText", Integer.toString(index));

            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            PassageBasedQuestionCreation passageBasedQuestionCreation = PageFactory.initElements(driver, PassageBasedQuestionCreation.class);
            TrueFalseQuestionCreation trueFalseQuestionCreation = PageFactory.initElements(driver, TrueFalseQuestionCreation.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);


            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            Thread.sleep(1000);
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_passageType().click(); //Click on Icon 'Passage Based' to create the sentence response type question
            passageBasedQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);//Type an assessment name
            passageBasedQuestionCreation.getTextField_EnterInstructions().sendKeys(instructions);// Type instructions
            passageBasedQuestionCreation.getTextArea1_EnterTabContent().sendKeys(passageText + "1");// Type Passage Text
            questionCreationGeneric.getButton_Save().click();//Click on Save Button
            Thread.sleep(5000);

            passageBasedQuestionCreation.getTab_EnterHeaderElementsList().get(0).click();// Click on Tab 'Enter Header'
            passageBasedQuestionCreation.getTab_EnterHeaderElementsList().get(0).sendKeys(passageText+passageText+passageText+"1");//Type passage name
            questionCreationGeneric.getButton_Save().click();//Click on Save Button


            //Expected - 2. The current active tab must be highlighted
            /*"3. Instructor should be able to create up to 5 tabs
            4. After 5 tabs the ' + ' button should not be displayed"*/

            for(int a=0;a<4;a++){
                passageBasedQuestionCreation.getIcon_PlusNewTab().click();
                actual = passageBasedQuestionCreation.getTab_EnterHeaderElementsList().get(a+1).getAttribute("placeholder");
                expected = "Passage Title";
                Assert.assertEquals(actual,expected,"Instructor is not able to create the new tabs");
                List<WebElement> all = driver.findElements(By.cssSelector("div[class='editable-question-content f43 editable question-edit-passage-text redactor_editor']"));
                actual = all.get(a+1).getText();
                expected = "Enter Passage";
                Assert.assertEquals(actual,expected,"Instructor is not able to add the content in the passage");
                passageBasedQuestionCreation.getTab_EnterHeaderElementsList().get(a+1).click();// Click on Tab 'Enter Header'
                passageBasedQuestionCreation.getTab_EnterHeaderElementsList().get(a+1).sendKeys(headerTitle+(a+2));//Type passage name
                all = driver.findElements(By.cssSelector("div[class='editable-question-content f43 editable question-edit-passage-text redactor_editor']"));
                all.get(a+1).sendKeys(passageText +(a+2));


            }

            if(passageBasedQuestionCreation.getIcon_PlusNewTab().isDisplayed()){
                Assert.fail("After 5 tabs the ' + ' button should not be displayed");
            }



            //Row No - 22 : Tab width and Tab management
            //Expected  - 1. All tabs should have 90px width, If the text goes out of the tab box area then it must have a grey out effect
            questionCreationGeneric.getButton_Save().click();//Click on Save Button
            Thread.sleep(3000);
            passageBasedQuestionCreation.getButton_AddNewQuestionForThisPassage().click();//Click on button 'Add a New Question for this passage'
            questionTypesPage.getIcon_TrueFalseType().click();//Click on Icon 'True False' Question Type
            trueFalseQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText);//Type question text
            trueFalseQuestionCreation.getButton_True().click();//Click on 'True' button
            questionCreationGeneric.getButton_Save().click();//Click on 'Save button'
            Thread.sleep(1000);
            trueFalseQuestionCreation.getButton_Preview().click();//Click on 'Preview' button
            Thread.sleep(3000);
            List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(browserTabs .get(1));
            actual = previewPage.getTab_FirstHeader1Title().getText();
            System.out.println("Actual : " + actual);
            expected = passageText+passageText+passageText+"1";
            Assert.assertEquals(actual,expected," There is no grey out effect, even if the text goes out of the tab box area");

        }catch(Exception e){
            Assert.fail("Exception in the method 'checkTabWidthAndTabManagement' in the class 'PassageBased'",e);
        }

    }





    @Test(priority =5, enabled = true)
    public void checkInstructorPreview(){
        try{
            int index = 32;
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));
            String instructions = ReadTestData.readDataByTagName("", "instructions", Integer.toString(index));
            String headerTitle = ReadTestData.readDataByTagName("", "headerTitle", Integer.toString(index));
            String passageText = ReadTestData.readDataByTagName("", "passageText", Integer.toString(index));

            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            PassageBasedQuestionCreation passageBasedQuestionCreation = PageFactory.initElements(driver, PassageBasedQuestionCreation.class);
            TrueFalseQuestionCreation trueFalseQuestionCreation = PageFactory.initElements(driver, TrueFalseQuestionCreation.class);

            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            createPassageBasedQuestion(index);
            Thread.sleep(3000);
            trueFalseQuestionCreation.getButton_Preview().click();
            Thread.sleep(3000);
            List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(browserTabs .get(1));
            Thread.sleep(3000);

            //Expected - 1 . Instructor should be able to see multiple tabs in the passage
            List<WebElement> tabList = driver.findElements(By.xpath("//div[contains(@data-tab,'tab-')]"));
            System.out.println("Tab Size : " +tabList.size());
            for(int a=0;a<5;a++){
                actual = tabList.get(a).getText();
                System.out.println("Actual : " + actual);
                expected = headerTitle+(a+1);
                Assert.assertEquals(actual,expected," Instructor is not able to see multiple tabs in the passage 1");
                if(!(tabList.get(a).isDisplayed())){
                    Assert.fail("Instructor is not able to see multiple tabs in the passage 2");
                }
            }


            //Expected - 2.He should be able to navigate between passages by clicking on their respective tabs
            //Expected - 3. On clicking on a Tab, the appropriate passage should be displayed and the tab should be highlighted
            System.out.println("Tab Size : " +tabList.size());
            for(int a=1;a<5;a++){
                tabList = driver.findElements(By.xpath("//div[contains(@data-tab,'tab-')]"));
                tabList.get(a).click();
                actual = driver.findElement(By.className("as-tooltip-link-block")).getText();
                expected = headerTitle+(a+1);
                Assert.assertEquals(actual,expected,"After clicking the multiple tabs, proper pop up is not happening");
                List<WebElement> all = driver.findElements(By.cssSelector("div[class='editable-question-content f43 editable question-edit-passage-text redactor_editor']"));
                actual = all.get(a).getText();
                expected = passageText+(a+1);
                Assert.assertEquals(actual,expected,"On clicking on a Tab, the appropriate passage should be displayed");
            }

            //Expected -4. The questions should be displayed on the right side
            actual = assessmentReview.getLabel_QuestionText().getText().trim();
            expected = questionText;
            Assert.assertEquals(actual,expected,"The questions associated with the passage is not getting displayed on the right side");



            questionCreationGeneric.getSubmitButton().click(); //Click on 'Submit' button to submit the question
            actual = sentenceResponceQuestionCreation.getLabel_Points_1Point0().getText();
            expected  = "Points: 1.0";
            Assert.assertEquals(actual, expected, "Points Value is not 1.0 in the Preview Page (One question should be worth one point)");

        }catch(Exception e){
            Assert.fail("Exception in the method 'checkInstructorPreview' in the class 'PassageBased'",e);
        }
    }




    @Test(priority =6, enabled = true)
    public void checkInstructorEditingQuestion(){
        try{
            int index = 27;

            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));
            String instructions = ReadTestData.readDataByTagName("", "instructions", Integer.toString(index));
            String headerTitle = ReadTestData.readDataByTagName("", "headerTitle", Integer.toString(index));
            String passageText = ReadTestData.readDataByTagName("", "passageText", Integer.toString(index));

            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            PassageBasedQuestionCreation passageBasedQuestionCreation = PageFactory.initElements(driver, PassageBasedQuestionCreation.class);
            TrueFalseQuestionCreation trueFalseQuestionCreation = PageFactory.initElements(driver, TrueFalseQuestionCreation.class);

            String appendChar = "AB";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            Thread.sleep(1000);
            createPassageBasedQuestion(index);
            Thread.sleep(2000);
            pf_assignments.getButton_review().click();//Click on 'Review' button
            pf_assignments.getRightArrow().click();//Click on 'Right hand Arrow' symbol
            assessmentReview.getButton_Edit().click();//Click on 'Edit' button
            Thread.sleep(2000);
            //Expected - 2. All the passage content and the question should be populated
            actual = trueFalseQuestionCreation.getAssessmentTextBoxField().getAttribute("value");
            expected = assessmentName;
            Assert.assertEquals(actual,expected,"Instructor is not able to edit the Assessment name in the question");

            actual = questionEditPage.getTextBox_QuestionEditField().getText().trim();
            expected = questionText;
            Assert.assertEquals(actual,expected,"Instructor is not able to edit the question's text in the question");

            actual = trueFalseQuestionCreation.getButton_TrueClicked().getText();
            expected = "A";
            Assert.assertEquals(actual,expected,"Instructor is not able to edit the other answer choicde in the question");

            //Expected - 1. Instructor should be able to edit the question after clicking on the Edit link
            //Expected - 3. Instructor should be able to make changes to any of the fields
            //Expected - 4. Clicking on save button should save the passage and the changes must be reflected
            questionEditPage.getTextBox_QuestionEditField().clear();//Clear the question
            questionEditPage.getTextBox_QuestionEditField().sendKeys("new " + questionText);//Type the new question
            trueFalseQuestionCreation.getAssessmentTextBoxField().clear();
            trueFalseQuestionCreation.getAssessmentTextBoxField().sendKeys("new " + assessmentName);
            trueFalseQuestionCreation.getButton_False().click();//Click on 'False' button
            questionEditPage.getButton_Save().click();//Click on 'Save' button

            Assert.assertEquals(questionEditPage.getTextBox_QuestionEditField().getText(),"new "+questionText,"Instructor is not able to edit the question");
            actual = trueFalseQuestionCreation.getAssessmentTextBoxField().getAttribute("value");
            expected = "new " +assessmentName;
            Assert.assertEquals(actual,expected,"Instructor is not able to edit the Assessment name in the question");

            actual = questionEditPage.getTextBox_QuestionEditField().getText();
            expected = "new " +questionText;
            Assert.assertEquals(actual,expected,"Instructor is not able to edit the question's text in the question");

            actual = trueFalseQuestionCreation.getButton_FalseClicked().getText();
            expected = "B";
            Assert.assertEquals(actual,expected,"Instructor is not able to edit the other answer choicde in the question");

        }catch(Exception e){
            Assert.fail("Exception in the method 'checkInstructorEditingQuestion' in the class 'PassageBased'",e);
        }
    }




    @Test(priority =7, enabled = true)
    public void checkInstructorQuestionExpandedView(){
        try{
            int index = 31;
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));
            String instructions = ReadTestData.readDataByTagName("", "instructions", Integer.toString(index));
            String headerTitle = ReadTestData.readDataByTagName("", "headerTitle", Integer.toString(index));
            String passageText = ReadTestData.readDataByTagName("", "passageText", Integer.toString(index));
            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            PassageBasedQuestionCreation passageBasedQuestionCreation = PageFactory.initElements(driver, PassageBasedQuestionCreation.class);
            TrueFalseQuestionCreation trueFalseQuestionCreation = PageFactory.initElements(driver, TrueFalseQuestionCreation.class);

            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            Thread.sleep(1000);
            createPassageBasedQuestion(index);
            pf_assignments.getButton_review().click();//Click on 'Review' button
            pf_assignments.getRightArrow().click();//Click on 'Right hand Arrow' symbol

            /*Expected - 1. The passages should be displayed on the left side, with tabs for each passage
            2. The questions associated with the passage should be displayed on the right side*/
            List<WebElement> tabList = driver.findElements(By.xpath("//div[contains(@data-tab,'tab-')]"));
            for(int a=0;a<5;a++){
                actual = tabList.get(a).getText();
                System.out.println("Actual : " + actual);
                expected = headerTitle+(a+1);
                Assert.assertEquals(actual,expected," Instructor is not able to see multiple tabs in the passage 1");
                if(!(tabList.get(a).isDisplayed())){
                    Assert.fail("Instructor is not able to see multiple tabs in the passage 2");
                }
            }


            //Expected - 2.He should be able to navigate between passages by clicking on their respective tabs
            //Expected - 3. On clicking on a Tab, the appropriate passage should be displayed and the tab should be highlighted
            for(int a=1;a<5;a++){
                tabList = driver.findElements(By.xpath("//div[contains(@data-tab,'tab-')]"));
                tabList.get(a).click();
                actual = driver.findElement(By.className("as-tooltip-link-block")).getText();
                expected = headerTitle+(a+1);
                Assert.assertEquals(actual,expected,"After clicking the multiple tabs, proper pop up is not happening");
                List<WebElement> all = driver.findElements(By.cssSelector("div[class='editable-question-content f43 editable question-edit-passage-text redactor_editor']"));
                actual = all.get(a).getText();
                expected = passageText+(a+1);
                Assert.assertEquals(actual,expected,"On clicking on a Tab, the appropriate passage should be displayed");
            }

            //Expected - 2. The questions associated with the passage should be displayed on the right side*/
            actual = assessmentReview.getLabel_QuestionText().getText().trim();
            expected = questionText;
            Assert.assertEquals(actual,expected,"The questions associated with the passage is not getting displayed on the right side");

        }catch(Exception e){
            Assert.fail("Exception in the method 'checkInstructorQuestionExpandedView' in the class 'PassageBased'",e);
        }
    }



    @Test(priority =8, enabled = true)
    public void checkStudentAttemptView(){
        try{
            int index = 32;
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));
            String instructions = ReadTestData.readDataByTagName("", "instructions", Integer.toString(index));
            String headerTitle = ReadTestData.readDataByTagName("", "headerTitle", Integer.toString(index));
            String passageText = ReadTestData.readDataByTagName("", "passageText", Integer.toString(index));
            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            PassageBasedQuestionCreation passageBasedQuestionCreation = PageFactory.initElements(driver, PassageBasedQuestionCreation.class);
            TrueFalseQuestionCreation trueFalseQuestionCreation = PageFactory.initElements(driver, TrueFalseQuestionCreation.class);
            Performance performance = PageFactory.initElements(driver, Performance.class);

            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            createPassageBasedQuestion(index);
            Thread.sleep(3000);
            passageBasedQuestionCreation.getButton_review().click();//Click on Review button
            questionsListPage.getButton_saveForLater().click();//Click on Save for Later button
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar, index);//Log in as a student
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            new Assignment().openAssignment(index);
            Thread.sleep(5000);


            //Expected - 1 . Instructor should be able to see multiple tabs in the passage
            List<WebElement> tabList = driver.findElements(By.xpath("//div[contains(@data-tab,'tab-')]"));
            System.out.println("Tab Size : " +tabList.size());
            for(int a=0;a<5;a++){
                actual = tabList.get(a).getText();
                System.out.println("Actual : " + actual);
                expected = headerTitle+(a+1);
                Assert.assertEquals(actual,expected," Instructor is not able to see multiple tabs in the passage 1");
                if(!(tabList.get(a).isDisplayed())){
                    Assert.fail("Instructor is not able to see multiple tabs in the passage 2");
                }
            }

            //Expected - 2.He should be able to navigate between passages by clicking on their respective tabs
            //Expected - 3. On clicking on a Tab, the appropriate passage should be displayed and the tab should be highlighted
            System.out.println("Tab Size : " +tabList.size());
            for(int a=1;a<5;a++){
                tabList = driver.findElements(By.xpath("//div[contains(@data-tab,'tab-')]"));
                tabList.get(a).click();
                actual = driver.findElement(By.className("as-tooltip-link-block")).getText();
                expected = headerTitle+(a+1);
                Assert.assertEquals(actual,expected,"After clicking the multiple tabs, proper pop up is not happening");
                List<WebElement> all = driver.findElements(By.cssSelector("div[class='editable-question-content f43 editable question-edit-passage-text redactor_editor']"));
                actual = all.get(a).getText();
                expected = passageText+(a+1);
                Assert.assertEquals(actual,expected,"On clicking on a Tab, the appropriate passage should be displayed");
            }

            //Expected -4. The questions should be displayed on the right side
            actual = assessmentReview.getLabel_QuestionText().getText().trim();
            expected = questionText;
            Assert.assertEquals(actual,expected,"The questions associated with the passage is not getting displayed on the right side");




            //Expected - 4. The questions should be displayed on the right side, he should be able to attempt these questions
            trueFalseQuestionCreation.getButton_True_StudentSide().click();
            trueFalseQuestionCreation.getButton_submit().click(); //Click on 'Submit' button to submit the question
            Thread.sleep(2000);
            performance.getButton_Continue().click();
            Thread.sleep(2000);
            actual = sentenceResponceQuestionCreation.getLabel_Points_1Point0().getText();
            expected  = "Points: 1";
            Assert.assertEquals(actual, expected, "'Points: 1' is not displayed");

        }catch(Exception e){
            Assert.fail("Exception in the method 'checkStudentAttemptView' in the class 'PassageBased'",e);
        }
    }


    @Test(priority =9, enabled = true)
    public void checkInstructorGradeBookView(){
        try{
            int index = 34;
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));
            String instructions = ReadTestData.readDataByTagName("", "instructions", Integer.toString(index));
            String headerTitle = ReadTestData.readDataByTagName("", "headerTitle", Integer.toString(index));
            String passageText = ReadTestData.readDataByTagName("", "passageText", Integer.toString(index));

            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            PassageBasedQuestionCreation passageBasedQuestionCreation = PageFactory.initElements(driver, PassageBasedQuestionCreation.class);
            TrueFalseQuestionCreation trueFalseQuestionCreation = PageFactory.initElements(driver, TrueFalseQuestionCreation.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver, AssessmentResponses.class);

            //Row No - 34 : Instructor gradebook view
            /*Expected : "1. Instructor should be able to see multiple tabs in the passage
            2.He should be able to navigate between passages by clicking on their respective tabs
            3. On clicking on a Tab, the appropriate passage should be displayed and the tab should be highlighted"*/


            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            createPassageBasedQuestion(index);
            Thread.sleep(3000);
            passageBasedQuestionCreation.getButton_review().click();//Click on Review button
            questionsListPage.getButton_saveForLater().click();//Click on Save for Later button
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar, index);//Log in as a student
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            new Assignment().openAssignment(index);
            Thread.sleep(5000);
            trueFalseQuestionCreation.getButton_True_StudentSide().click();//Click on true button
            trueFalseQuestionCreation.getButton_submit().click();
            Thread.sleep(1000);
            new Navigator().logout();//log out
            Thread.sleep(3000);
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            Thread.sleep(5000);
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getView_grades().click();//Click on 'View Responses'
            Thread.sleep(2000);
            assessmentResponses.getButton_ReleaseGradeForAll().click();//Click on button 'Release Grade for All'
            Thread.sleep(2000);
            assessmentResponses.getGradeBookContentColumn().click();
            Thread.sleep(2000);
            //Expected - 1 . Instructor should be able to see multiple tabs in the passage
            List<WebElement> tabList = driver.findElements(By.xpath("//div[contains(@data-tab,'tab-')]"));
            System.out.println("Tab Size : " +tabList.size());
            for(int a=0;a<5;a++){
                actual = tabList.get(a).getText();
                System.out.println("Actual : " + actual);
                expected = headerTitle+(a+1);
                Assert.assertEquals(actual,expected," Instructor is not able to see multiple tabs in the passage 1");
                if(!(tabList.get(a).isDisplayed())){
                    Assert.fail("Instructor is not able to see multiple tabs in the passage 2");
                }
            }

            //Expected - 2.He should be able to navigate between passages by clicking on their respective tabs
            //Expected - 3. On clicking on a Tab, the appropriate passage should be displayed and the tab should be highlighted
            System.out.println("Tab Size : " +tabList.size());
            for(int a=1;a<5;a++){
                tabList = driver.findElements(By.xpath("//div[contains(@data-tab,'tab-')]"));
                tabList.get(a).click();
                actual = driver.findElement(By.className("as-tooltip-link-block")).getText();
                expected = headerTitle+(a+1);
                Assert.assertEquals(actual,expected,"After clicking the multiple tabs, proper pop up is not happening");
                List<WebElement> all = driver.findElements(By.cssSelector("div[class='editable-question-content f43 editable question-edit-passage-text redactor_editor']"));
                actual = all.get(a).getText();
                expected = passageText+(a+1);
                Assert.assertEquals(actual,expected,"On clicking on a Tab, the appropriate passage should be displayed");
            }

            //Expected -4. The questions should be displayed on the right side
            actual = assessmentReview.getLabel_QuestionText().getText().trim();
            expected = questionText;
            Assert.assertEquals(actual,expected,"The questions associated with the passage is not getting displayed on the right side");


            //Expected - 5. The questions should display the student's response (if any)
            actual = pf_assQuestion.getValue_QuestionPerformanceScore().getAttribute("value");
            expected = "1";
            Assert.assertEquals(actual,expected," On selecting the correct answer student is not getting Full points(1.0)");


        }catch(Exception e){
            Assert.fail("Exception in the method 'checkInstructorGradeBookView' in the class 'PassageBased'",e);
        }
    }



    @Test(priority =10, enabled = true)
    public void checkStudentReportView(){
        try{
            int index = 37;
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));
            String instructions = ReadTestData.readDataByTagName("", "instructions", Integer.toString(index));
            String headerTitle = ReadTestData.readDataByTagName("", "headerTitle", Integer.toString(index));
            String passageText = ReadTestData.readDataByTagName("", "passageText", Integer.toString(index));

            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            PassageBasedQuestionCreation passageBasedQuestionCreation = PageFactory.initElements(driver, PassageBasedQuestionCreation.class);
            TrueFalseQuestionCreation trueFalseQuestionCreation = PageFactory.initElements(driver, TrueFalseQuestionCreation.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver, AssessmentResponses.class);
            Performance performance = PageFactory.initElements(driver, Performance.class);

            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            createPassageBasedQuestion(index);
            Thread.sleep(3000);
            passageBasedQuestionCreation.getButton_review().click();//Click on Review button
            questionsListPage.getButton_saveForLater().click();//Click on Save for later button
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out
            new Login().loginAsStudent(appendChar, index);//Log in as a student
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            new Assignment().openAssignment(index);
            Thread.sleep(5000);
            trueFalseQuestionCreation.getButton_True_StudentSide().click();//Click on true button
            trueFalseQuestionCreation.getButton_submit().click();
            Thread.sleep(2000);
            //driver.findElement(By.cssSelector("g[class ='highcharts-series highcharts-tracker']")).click();
            assessments.getButton_continue().click();//Click on Continue button
            Thread.sleep(2000);

            //Expected - 1 . Instructor should be able to see multiple tabs in the passage
            List<WebElement> tabList = driver.findElements(By.xpath("//div[contains(@data-tab,'tab-')]"));
            System.out.println("Tab Size : " +tabList.size());
            for(int a=0;a<5;a++){
                actual = tabList.get(a).getText();
                System.out.println("Actual : " + actual);
                expected = headerTitle+(a+1);
                Assert.assertEquals(actual,expected," Instructor is not able to see multiple tabs in the passage 1");
                if(!(tabList.get(a).isDisplayed())){
                    Assert.fail("Instructor is not able to see multiple tabs in the passage 2");
                }
            }

            //Expected - 2.He should be able to navigate between passages by clicking on their respective tabs
            //Expected - 3. On clicking on a Tab, the appropriate passage should be displayed and the tab should be highlighted
            System.out.println("Tab Size : " +tabList.size());
            for(int a=1;a<5;a++){
                tabList = driver.findElements(By.xpath("//div[contains(@data-tab,'tab-')]"));
                tabList.get(a).click();
                actual = driver.findElement(By.className("as-tooltip-link-block")).getText();
                expected = headerTitle+(a+1);
                Assert.assertEquals(actual,expected,"After clicking the multiple tabs, proper pop up is not happening");
                List<WebElement> all = driver.findElements(By.cssSelector("div[class='editable-question-content f43 editable question-edit-passage-text redactor_editor']"));
                actual = all.get(a).getText();
                expected = passageText+(a+1);
                Assert.assertEquals(actual,expected,"On clicking on a Tab, the appropriate passage should be displayed");
            }

            //Expected -4. The questions should be displayed on the right side
            actual = assessmentReview.getLabel_QuestionText().getText().trim();
            expected = questionText;
            Assert.assertEquals(actual,expected,"The questions associated with the passage is not getting displayed on the right side");


            //Expected - 5. The questions should display the student's response (if any)
            actual = pf_assQuestion.getValue_QuestionPerformanceScore().getAttribute("value");
            expected = "1";
            Assert.assertEquals(actual,expected," On selecting the correct answer student is not getting Full points(1.0)");


        }catch(Exception e){
            Assert.fail("Exception in the method 'checkStudentReportView' in the class 'PassageBased'",e);
        }
    }


    @Test(priority =11, enabled = true)
    public void checkPrintView(){
        try{
            int index = 39;
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));
            String instructions = ReadTestData.readDataByTagName("", "instructions", Integer.toString(index));
            String headerTitle = ReadTestData.readDataByTagName("", "headerTitle", Integer.toString(index));
            String passageText = ReadTestData.readDataByTagName("", "passageText", Integer.toString(index));
            String printText = ReadTestData.readDataByTagName("", "printText", Integer.toString(index));

            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            PassageBasedQuestionCreation passageBasedQuestionCreation = PageFactory.initElements(driver, PassageBasedQuestionCreation.class);
            TrueFalseQuestionCreation trueFalseQuestionCreation = PageFactory.initElements(driver, TrueFalseQuestionCreation.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver, AssessmentResponses.class);

            //Row No - 34 : Instructor gradebook view
            /*Expected : "1. Instructor should be able to see multiple tabs in the passage
            2.He should be able to navigate between passages by clicking on their respective tabs
            3. On clicking on a Tab, the appropriate passage should be displayed and the tab should be highlighted"*/


            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            createPassageBasedQuestion(index);
            Thread.sleep(3000);
            passageBasedQuestionCreation.getButton_review().click();//Click on Review button
            questionsListPage.getButton_saveForLater().click();//Click on Save or later button
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            pf_assignments.getButton_Print().click();//Click on 'Print' button
            Thread.sleep(3000);
            List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(browserTabs .get(1));

            //Expected - 1. Instruction text should be printed first
            actual = driver.findElement(By.id("question-preview-passage-question-direction")).getText();//Instruction Text
            expected = instructions;
            Assert.assertEquals(actual,expected,"Instruction text is not printed first\n");


            //Expected - 2. All passages should be printed one after the other, a horizontal line should be displayed as a separator for passages
            actual = driver.findElement(By.className("question-edit-passage-tabbed")).getText().trim();
            System.out.println("pass Text : " + actual);
            expected = printText.trim();
            Assert.assertEquals(actual,expected,"All passages is not printed one after the other");
            actual = driver.findElement(By.id("question-edit")).getText().trim();
            expected = questionText;
            Assert.assertEquals(actual,expected,"All questions are not printed in full width");

        }catch(Exception e){
            Assert.fail("Exception in the method 'checkPrintView' in the class 'PassageBased'",e);
        }
    }

    public void createPassageBasedQuestion(int index){

        try {

            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));
            String instructions = ReadTestData.readDataByTagName("", "instructions", Integer.toString(index));
            String headerTitle = ReadTestData.readDataByTagName("", "headerTitle", Integer.toString(index));
            String passageText = ReadTestData.readDataByTagName("", "passageText", Integer.toString(index));

            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            MatchTheFollowingPreviewPage matchTheFollowingPreviewPage = PageFactory.initElements(driver, MatchTheFollowingPreviewPage.class);
            NumberLineQuestionCreation numberLineQuestionCreation = PageFactory.initElements(driver, NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            SentenceResponceQuestionCreation sentenceResponceQuestionCreation = PageFactory.initElements(driver, SentenceResponceQuestionCreation.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionCreationGeneric questionCreationGeneric = PageFactory.initElements(driver, QuestionCreationGeneric.class);
            PassageBasedQuestionCreation passageBasedQuestionCreation = PageFactory.initElements(driver, PassageBasedQuestionCreation.class);
            TrueFalseQuestionCreation trueFalseQuestionCreation = PageFactory.initElements(driver, TrueFalseQuestionCreation.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver, AssessmentResponses.class);

            new Navigator().navigateTo("assignment"); //Navigate to 'Assessment' Page
            assessments.getButton_newAssessment().click(); //Click on the Button '+New Assignment'
            createAssessment.getButton_create().click(); //Click on Icon 'Create New Assessment'
            tloListPage.getButton_create().click(); //Click on the button 'Create' for the TLO '1.OA.A.1'
            questionTypesPage.getIcon_passageType().click(); //Click on Icon 'Passage Based' to create the sentence response type question
            passageBasedQuestionCreation.getInputTextField_SampleAssessmentName().sendKeys(assessmentName);//Type an assessment name
            passageBasedQuestionCreation.getTextField_EnterInstructions().sendKeys(instructions);// Type instructions

            passageBasedQuestionCreation.getTextArea1_EnterTabContent().sendKeys(passageText + "1");// Type Passage Text
            questionCreationGeneric.getButton_Save().click();//Click on Save Button
            Thread.sleep(5000);

            passageBasedQuestionCreation.getTab_EnterHeaderElementsList().get(0).click();// Click on Tab 'Enter Header'
            passageBasedQuestionCreation.getTab_EnterHeaderElementsList().get(0).sendKeys(headerTitle+"1");//Type passage name
            questionCreationGeneric.getButton_Save().click();//Click on Save Button


            //Expected - 2. The current active tab must be highlighted
            /*"3. Instructor should be able to create up to 5 tabs
            4. After 5 tabs the ' + ' button should not be displayed"*/

            for (int a = 0; a < 4; a++) {
                passageBasedQuestionCreation.getIcon_PlusNewTab().click();
                actual = passageBasedQuestionCreation.getTab_EnterHeaderElementsList().get(a + 1).getAttribute("placeholder");
                expected = "Passage Title";
                Assert.assertEquals(actual, expected, "Instructor is not able to create the new tabs");
                List<WebElement> all = driver.findElements(By.cssSelector("div[class='editable-question-content f43 editable question-edit-passage-text redactor_editor']"));
                actual = all.get(a + 1).getText();
                expected = "Enter Passage";
                Assert.assertEquals(actual, expected, "Instructor is not able to add the content in the passage");
                passageBasedQuestionCreation.getTab_EnterHeaderElementsList().get(a + 1).click();// Click on Tab 'Enter Header'
                passageBasedQuestionCreation.getTab_EnterHeaderElementsList().get(a + 1).sendKeys(headerTitle + (a + 2));//Type passage name
                all = driver.findElements(By.cssSelector("div[class='editable-question-content f43 editable question-edit-passage-text redactor_editor']"));
                all.get(a + 1).sendKeys(passageText + (a + 2));


            }


            //Row No - 22 : Tab width and Tab management
            //Expected  - 1. All tabs should have 90px width, If the text goes out of the tab box area then it must have a grey out effect
            questionCreationGeneric.getButton_Save().click();//Click on Save Button
            Thread.sleep(3000);
            passageBasedQuestionCreation.getButton_AddNewQuestionForThisPassage().click();//Click on button 'Add a New Question for this passage'
            questionTypesPage.getIcon_TrueFalseType().click();//Click on Icon 'True False' Question Type
            Thread.sleep(2000);
            trueFalseQuestionCreation.getTextField_EnterQuestionText().sendKeys(questionText);//Type question text
            Thread.sleep(2000);
            trueFalseQuestionCreation.getButton_True().click();//Click on 'True' button
            questionCreationGeneric.getButton_Save().click();//Click on 'Save button'
            Thread.sleep(1000);
        }catch(Exception e){
            Assert.fail("Exception in the method 'createPassageBasedQuestion' in the class 'PassageBased'",e);
        }
    }
}
