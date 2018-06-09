package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.assignmentflow;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentLibrary;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assign;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.MyAssessments;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Mukesh on 11/25/14.
 */
public class TLOactivity extends Driver {
    @Test(priority = 1, enabled = true)
    public void clickOnAddMoreButton() {
        try {
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "62");

            //Tc row no 62
            String appendChar = "a";
            new SignUp().teacher(appendChar, 62); //sign up as a teacher
            new School().createWithOnlyName(appendChar, 62); //create school
            new Classes().createClass(appendChar, 62);//create class

            new Navigator().navigateTo("assignment"); //navigate to assignment
            new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");//click on the Create New Assignment
            driver.findElement(By.id("new-assessment-name")).clear();
            new TextSend().textsendbyid(assessmentname, "new-assessment-name");//Enter assessment name
            new Click().clickByid("create-assessment-with-val");//Click on create button
            new Click().clickbylistcssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']",1);;//Click on 2nd create link
            new Click().clickByid("qtn-true-false-type");//click on true false question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("True False Question");//type the question
            new Click().clickBycssselector("span.true-false-answer-label");//click on Answer Option
            new TextSend().textsendbyid("True False Solution Text", "content-solution");
            new TextSend().textsendbyid("True False Hint Text", "content-hint");
            new Click().clickByid("saveQuestionDetails1");//click on the save
            WebDriverWait wait=new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Saved.']")));
            assignments.getButton_review().click();//click on the review button
            new Click().clickByid("assessments-back-button");//click on the add more
            Thread.sleep(3000);
            new Click().clickByXpath("(//span[@class='lsm-auto-assign-btn btn btn-blue btn-rounded btn-outline'])[1]");//click on the 2nd auto select[1st elo auto select]

            //Expected 1. Maximum of 2 questions are picked up randomly from any ELO
            Thread.sleep(5000);
            int maxNoOfQuestionCount=driver.findElements(By.xpath("//span[@class='lsm-elo-total-questions count-badge'][text()>0]")).size();
            Thread.sleep(3000);
            if(!(maxNoOfQuestionCount>=2))
                Assert.fail("Maximum of 2 questions are not  picked up randomly from any ELO");

            //2.The auto select option beside the TLO gets disabled
            List<WebElement> disabledAutoSelectButtons = driver.findElements(By.xpath("//span[contains(@class,'btn-disabled')]"));
            String disableTLo = disabledAutoSelectButtons.get(0).getText();
            if (!disableTLo.contains("Auto Select"))
                Assert.fail("The auto select option beside the TLO is not disabled");

            String disableELo1 = disabledAutoSelectButtons.get(1).getText();
            if (!disableELo1.contains("Auto Select"))
                Assert.fail("The auto select option beside the ELO1 is not disabled even after creating a question inside");

            String disableELo2 = disabledAutoSelectButtons.get(2).getText();
            if (!disableELo2.contains("Auto Select"))
                Assert.fail("The auto select option beside the TLO is not disabled after clicking on auto select");

            //3. If only 1 question is present among the ELOs, that question is selected.
            new Click().clickbylistcssselector("span[class='lsm-select-btn lsm-select-tlo-questions-btn btn btn-blue btn-outline btn-rounded']",0);//click on the select button
            //Expected 1.All the questions present under that TLO(All ELOs inclusive) are displayed
            if(driver.findElements(By.className("lsm-createAssignment-Question")).size()==0)
                Assert.fail("All the questions present under that TLO(All ELOs inclusive) are not displayed");
        } catch (Exception e) {
            Assert.fail("Exception in test case  clickOnAddMoreButton of class TLOactivity", e);
        }
    }
    @Test(priority = 2, enabled = true)
    public void clickOnSelectBesideTlo() {
        try {
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "66");

            //Tc row no 66
            clickOnCreateNewAssignment("a",66);
            new TextSend().textsendbyid(assessmentname, "new-assessment-name");//Enter assessment name
            new Click().clickByid("create-assessment-with-val");//Click on create button
            new Click().clickbylistcssselector("span[class='lsm-select-btn lsm-select-tlo-questions-btn btn btn-blue btn-outline btn-rounded']",0);//click on the select button
            List<WebElement> clickOnQuestion=driver.findElements(By.className("as-grey-checkbox"));
            for(int i=0;i<2;i++) {
                clickOnQuestion.get(i).click();
            }
            String reviewCounter=new TextFetch().textfetchbyclass("lsm-createAssignment-total-questions");
            Assert.assertEquals(reviewCounter,"2","As teacher keeps selecting questions, the counter above review button should keep not Increasing");
            String reviewButton= assignments.getButton_review().getText();
            if(!reviewButton.contains("Review"))
                Assert.fail("Teacher is not able to see Review Button");
            //Tc row no 69
            new Click().clickByclassname("as-create-asignmentHeader-close");//click on the x icon
            Thread.sleep(5000);

            //TC row no 70
            String xIconMsg=new TextFetch().textfetchbyclass("as-assignment-modal-msg");
            if(!xIconMsg.contains("Changes to the assessment will be lost, do you want to proceed?"))
                Assert.fail("Changes to the assignment will be lost, do you want to proceed is not displayed after clicking on x");
            //Tc row no 71
            String noButton=new TextFetch().textfetchbycssselector("span[class='as-modal-no-btn no-delete']");
            Assert.assertEquals(noButton,"No","No option is not visible");
            String yesOption=new TextFetch().textfetchbycssselector("div[class='as-modal-yes-btn yes-delete add-question-page']");
            if(!yesOption.contains("Yes"))
                Assert.fail("Yes option is not visible");

            //Tc row no 72
            new Click().clickBycssselector("span[class='as-modal-no-btn no-delete']");//click on No option
            String selectQuestion1=new TextFetch().textfetchbyclass("lsm-add-questions-title");
            if(!selectQuestion1.contains("Step 1 of 3Select or Create questions"))
                Assert.fail("User is not directed to main assignment page(Step 1 of 3Select questions)");

            //Tc row no 73
            new Click().clickByclassname("as-create-asignmentHeader-close");//click on the x icon
            new Click().clickBycssselector("div[class='as-modal-yes-btn yes-delete add-question-page']");//click on the yes option
            String assignmentHomeTitle=new TextFetch().textfetchbyclass("pull-left");
            Assert.assertEquals("Assignments",assignmentHomeTitle,"Assignment home title is not present in assignment home page");

        } catch (Exception e) {
            Assert.fail("Exception in test case  clickOnSelectBesideTlo of class TLOactivity", e);
        }
    }
    @Test(priority = 3, enabled = true)
    public void clickReviewAfterSelectingQuestion() {
        try {
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "74");
            MyAssessments myAssessments = PageFactory.initElements(driver,MyAssessments.class);

            //Tc row no 74
            clickOnCreateNewAssignment("a", 74);
            new TextSend().textsendbyid(assessmentname, "new-assessment-name");//Enter assessment name
            new Click().clickByid("create-assessment-with-val");//Click on create button
            new Click().clickbylistcssselector("span[class='lsm-select-btn lsm-select-tlo-questions-btn btn btn-blue btn-outline btn-rounded']",0);//click on the select button
            List<WebElement> clickOnQuestion=driver.findElements(By.className("as-grey-checkbox"));
            for(int i=0;i<2;i++) {
                clickOnQuestion.get(i).click();
            }
            assignments.getButton_review().click();//click on the review
            String saveForLater=new TextFetch().textfetchbyid("assessments-save-later-button");
            Assert.assertEquals(saveForLater,"Save in Drafts","Save for later option is not available");
            String addMore=new TextFetch().textfetchbyid("assessments-back-button");
            if(!addMore.contains("Add more"))
                Assert.fail("Add more button is not available in the page");
            String nextButton=new TextFetch().textfetchbyid("assessments-use-button");
            Assert.assertEquals(nextButton,"Next","Next button is not available in the page");

           //Tc row no 75
            new Click().clickByid("assessments-save-later-button"); //click on the save for later button
            Thread.sleep(3000);
            String assignmentHomeTitle=myAssessments.button_creteNewAssessment.getText();
            Assert.assertEquals("Create New Assessment",assignmentHomeTitle,"Assignment home title is not present in assignment home page");

            new Navigator().navigateTo("myAssessments");//Navigate to my assessments page
            new Click().clickByXpath("//input[@id='draft']/following-sibling::ins");//Click on draft

            String draftCountBeforeIncrement=new TextFetch().textFetchByXpath("//input[@id='draft']/following-sibling::ins");

            String count=draftCountBeforeIncrement.substring(7,draftCountBeforeIncrement.length()-1);
            int integerValueIncrement=Integer.parseInt(count);
            if(!(integerValueIncrement>=1))
                Assert.fail("\"View Draft status\"\" is not incrementing\"");

            //TC row no 76
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");//click on the Create new Assignment
            new TextSend().textsendbyid(assessmentname + "1", "new-assessment-name");//Enter assessment name
            new Click().clickByid("create-assessment-with-val");//Click on create button
            new Click().clickbylistcssselector("span[class='lsm-select-btn lsm-select-tlo-questions-btn btn btn-blue btn-outline btn-rounded']",0);//click on the select button
            List<WebElement> clickOnQuestion1=driver.findElements(By.className("as-grey-checkbox"));
            for(int i=0;i<2;i++) {
                clickOnQuestion1.get(i).click();
            }
            assignments.getButton_review().click();//click on the review
            new Click().clickByid("assessments-use-button");//click on the next button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("assign-button")));
            String assignThis=new TextFetch().textfetchbyid("assign-button");
            Assert.assertEquals(assignThis,"Assign","Assign button is not present");

            //Tc row no 77
            new Click().clickByid("go-back");//click on the back button
            String selectQuestion1=new TextFetch().textFetchByXpath("//span[@class='left-header-msg-section left-crumb']/parent::div");
            System.out.println("Select Question Page:"+selectQuestion1);
            if(!selectQuestion1.contains("Step 2 of 3Select questions"))
                Assert.fail("User is not directed to main assignment page(Step 2 of 3Select questions)");

        } catch (Exception e) {
            Assert.fail("Exception in test case  clickReviewAfterSelectingQuestion of class TLOactivity", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void assignThisAfterClickOnUseThisButton() {
        try {

            AssessmentLibrary assessmentLibrary = PageFactory.initElements(driver,AssessmentLibrary.class);
            Assign assign = PageFactory.initElements(driver,Assign.class);

            //Tc row no 79
            String appendChar = "a";
            int index=79;
            new SignUp().teacher(appendChar, index); //sign up as a teacher
            new School().createWithOnlyName(appendChar, index); //create school
            new Classes().createClass(appendChar, index);//create class
            new Assignment().selectExistingAssignment();
            assessmentLibrary.getList_assessment().get(0).click();//Select the assessment
            assessmentLibrary.getList_createNewVersion().get(0).click();//Click on Customize
            new Click().clickByid("assessments-use-button");//click on Next
            assign.getRadio_button_rightNow().click();//Click on right now radio button

            //1. “Assign to” field should be automatically filled
            String assignTO=new TextFetch().textfetchbyclass("item-text");
            if(assignTO.equals("")||assignTO==null)
                Assert.fail("“Assign to” field is not filled automatically");
            //2. “Short Label” field should be empty for teacher to fill in
            String shortLabel=driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).getAttribute("value");
            if(shortLabel.length()<0 || shortLabel.length()==0)
                Assert.fail("Short label is not empty");
            driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).clear();
            driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).sendKeys("shor");//short label name for due date error

            //4. Points allotted for the assignment should be correct
            //6. Due date should be empty, for teacher to enter

            String dueDate= driver.findElement(By.id("lsm_assignment_due_date")).getAttribute("class");
            if(!dueDate.contains("form-control input-lg lsm-assignment-input hasDatepicker"))
                Assert.fail("Due date is not empty, for teacher to enter");
            //8. On entering previous/past date Error message should be displayed
            new Click().clickByid("lsm_assignment_due_date");//click on Due Date field
            new Click().clickBycssselector("span[class='ui-icon ui-icon-circle-triangle-w']");
            new Click().clickbylinkText("11");//select past due date

            new Click().clickByid("lsm_assignment_due_time");//click on Due Time field
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals("12:00 AM")) {
                    time.click();
                    break;
                }
            }
            new Click().clickByid("assign-button");//click on the assign

            String errorMsg=new TextFetch().textfetchbycssselector("div[class='lsm-assignment-name-tooltip lsm-assign-assessment-error-message-tooltip']");
            Assert.assertEquals("Due date must be a future date.",errorMsg,"Error Message is not appeared");

            //11. If all the fields are filled, on clicking assign, teacher should go back to assignment Page
            new Click().clickByid("lsm_assignment_due_date");//click on Due Date field
            new Click().clickBycssselector("span[class='ui-icon ui-icon-circle-triangle-e']");
            new Click().clickBycssselector("span[class='ui-icon ui-icon-circle-triangle-e']");
            new Click().clickbylinkText("28");//select past due date
            new Click().clickByid("assign-button");//click on the assign
            Thread.sleep(2000);
            String assignedText=new TextFetch().textfetchbycssselector("span[class='right-header-msg-section right-crumb']");
            Assert.assertEquals("Assignment Assigned",assignedText,"'Assignment Assigned' is not displayed");

            //. 12. The new assignment should be visible on the “assignments” page now
            new Navigator().navigateTo("assignment"); //navigate to assignment
            String assignment=new TextFetch().textfetchbycssselector("h4[class='as-title as-label']");
            if(assignment.equals("")||assignment==null)
                Assert.fail("New Assignment is not visible on the Assignment page");

        } catch (Exception e) {
            Assert.fail("Exception in test case  assignThisAfterClickOnUseThisButton of class TLOactivity", e);
        }
    }


    public void clickOnCreateNewAssignment(String char1, int index) {
        String appendChar = char1;
        new SignUp().teacher(appendChar, index); //sign up as a teacher
        new School().createWithOnlyName(appendChar, index); //create school
        new Classes().createClass(appendChar, index);//create class
        new Navigator().navigateTo("assignment"); //navigate to assignment
        new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");//click on the Create New Assignment


    }
}