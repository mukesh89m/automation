package com.snapwiz.selenium.tests.staf.learningspaces.testcases.regression.assignment;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.CustomAssert;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by priyanka on 07-01-2016.
 */
public class CustomAssignment extends Driver {

    @Test(priority = 1,enabled = true)
    public void customAssignmentPage() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Description", "This test case validates find question tab functionality in custom assignment page ", "info");

            String name = ReadTestData.readDataByTagName("", "name", "69");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "69");
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "69");
            String randomndescption = new RandomString().randomstring(2);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);

            new Assignment().create(69);//create assignment
            new Assignment().addQuestionsWithCustomizedQuestionText(69, "qtn-type-true-false-img", assessmentname, 3);
            ReportUtil.log("Create Assessment", "Create 3 true false question in one assessment", "pass");

            new LoginUsingLTI().ltiLogin("69");    //ReportUtil.login as instructor
            new Navigator().NavigateTo("Question Banks");
            ReportUtil.log("Instructor ReportUtil.login", "Instructor successfully ReportUtil.logged in to the application and navigate to question bank page", "pass");
            List<WebElement> a = myQuestionBank.customizeThisLink;
            for (WebElement b : a) {
                if (b.isDisplayed()) {
                    b.click();//Click on "Customize This" link, present below any assessment
                }
            }
            Thread.sleep(2000);
            String tab = newAssignment.activeTab.getText();
            if (!tab.equals("New Assignment"))
                Assert.fail("Click on \"Customize This\" link, Instructor does not navigate to New Assignment tab.");
            ReportUtil.log("Verify New Assignment tab", "Click on \"Customize This\" link, Instructor navigated to New Assignment tab.", "pass");
            String saveButton = newAssignment.saveForLater_Button.getText();
            if (!saveButton.contains("SAVE FOR LATER"))
                Assert.fail("\"SAVE FOR LATER\" button is absent in New Assignment tab.");
            ReportUtil.log("Verify SAVE FOR LATER button", "\"SAVE FOR LATER\" button is displaying in New Assignment tab.", "pass");
            String assignButton = newAssignment.assignNowButton.getText();
            if (!assignButton.contains("ASSIGN NOW"))
                Assert.fail("\"ASSIGN NOW\" button is absent in New Assignment tab.");
            ReportUtil.log("Verify ASSIGN NOW button", "\"ASSIGN NOW\" button is displaying in New Assignment tab.", "pass");
            newAssignment.assessmentNameTextBox.click();//click on Name field
            newAssignment.assessmentName_TextBox.sendKeys(name);
            Actions act = new Actions(driver);
            act.moveToElement(newAssignment.descriptionField);//mouse hover on description
            Thread.sleep(3000);
            newAssignment.descriptionField.click();//click on description
            newAssignment.descriptionTextArea.sendKeys(randomndescption);//edit description
            newAssignment.saveForLater_Button.click();//click on Save For Later
            String notice = newAssignment.notification_message.getText();
            if (!notice.equals("You have not added any questions for this custom assessment. Please add questions before saving the assessment"))
                Assert.fail("No notification appear on clicking SAVE FOR LATER button without selecting question from New Assignment tab.");
            ReportUtil.log("Verify notification message", "You have not added any questions for this custom assessment notification message is displayed", "pass");
            Thread.sleep(5000);
            boolean searchIcon = newAssignment.searchIcon.isDisplayed();
            boolean browseIcon = newAssignment.browse_link.isDisplayed();
            boolean separator = newAssignment.searchBrowseSeparator.isDisplayed();
            Assert.assertEquals(true, searchIcon, "search icon not shown on create custom assignment page");
            ReportUtil.log("Verify search icon", "search icon is displayed custom assignment page", "pass");
            Assert.assertEquals(true, browseIcon, "browse icon not shown on create custom assignment page");
            ReportUtil.log("Verify browse icon", "browse icon is displayed custom assignment page", "pass");
            Assert.assertEquals(true, separator, "separator icon not shown on create custom assignment page");
            ReportUtil.log("Verify separator icon", "separator icon is displayed custom assignment page", "pass");
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            boolean filterOption = newAssignment.filter.isDisplayed();
            Assert.assertEquals(true, filterOption, "filter option not shown after search the question");
            ReportUtil.log("Verify filter option", "Filter option is displayed  after search the question", "pass");
            String expandText = newAssignment.expandAllQuestionLink.getText();
            Assert.assertEquals(true, expandText.contains("Expand all questions"), "By default expand/collapse option not be in close state and not show the label “Expand All Questions”");
            ReportUtil.log("Verify Expand all questions label", "Expand all questions label is displayed", "pass");
            newAssignment.expandAll_Icon.get(0).click();//click on expand all question
            String collapseText = newAssignment.expandAllQuestionLink.getText();
            Assert.assertEquals(true, collapseText.contains("Collapse all questions"), "After click on expand all questions text not be changed to 'Collapse all questions'");
            ReportUtil.log("Verify Collapse all questions label", "After click on expand all questions text it changed to 'Collapse all questions", "pass");
            newAssignment.collapseAllIcon.click();//click on collapse all question icon
            String expandText1 = newAssignment.expandAllQuestionLink.getText();
            Assert.assertEquals(true, expandText1.contains("Expand all questions"), "After click on collapse all questions label not changed to  “Expand All Questions”");
            ReportUtil.log("Verify Expand all questions label", "After click on collapse all questions label it changed to “Expand All Questions”", "pass");
            new AssignLesson().selectQuestionForCustomAssignment("69");
            Thread.sleep(3000);
            newAssignment.saveForLater_Button.click();//click on Save For Later
            String notice1 = newAssignment.notification_message.getText();
            if (!notice1.equals("Saved Custom Assessment Successfully."))
                Assert.fail("No notification appear on clicking SAVE FOR LATER button after selecting a question from New Assignment tab.");
            ReportUtil.log("Verify notification message", "After click on SAVE FOR LATER Saved Custom Assessment Successfully message is displayed", "pass");
        } catch (Exception e) {
            Assert.fail("Exception in test case customAssignmentPage in class CustomAssignment.", e);
        }
    }
    @Test(priority = 2,enabled = true)
        public void myQuestionBankPage() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Description","This test case validates all the link presents under custom assignment in my question bank page","info");

            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String name = ReadTestData.readDataByTagName("", "name", "69");
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);

            new LoginUsingLTI().ltiLogin("69");    //ReportUtil.login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='resource-title']")));
            CustomAssert.assertEquals(myQuestionBank.getAssessment().getText(), name,"Verify created custom assignment","created custom assignment is added under my question bank", "created custom assignment is not added under my question bank");
            CustomAssert.assertEquals(myQuestionBank.getPreviewButton().isDisplayed(), true,"Verify preview link","preview link is available","preview link is not available");
            CustomAssert.assertEquals(myQuestionBank.shareThis.getAttribute("title"), "Share This","Verify Share This link","Share This link is available","Share This link is not available");
            CustomAssert.assertEquals(myQuestionBank.getAssignThis().isDisplayed(), true,"Verify Assign This link","Assign This link is available","Assign This link is not available");
            CustomAssert.assertEquals(myQuestionBank.getDeleteButtonOfOriginal().getAttribute("title"), "Delete This","Verify Delete This link","Delete This link is not available","Delete This link is not available");
            CustomAssert.assertEquals(myQuestionBank.tryItIcon.get(0).isDisplayed(),true,"Verify Try it link","Try it link is not available","Try it link is not available");
            new AssignLesson().Assigncustomeassignemnt(69);//assign assignment
            Thread.sleep(2000);
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            CustomAssert.assertEquals(myQuestionBank.getPreviewButton().isDisplayed(), true,"Verify preview link","preview link is available","preview link is not available");
            CustomAssert.assertEquals(myQuestionBank.shareThis.getAttribute("title"), "Share This","Verify Share This link","Share This link is available","Share This link is not available");
            CustomAssert.assertEquals(myQuestionBank.getAssignThis().isDisplayed(), true,"Verify Assign This link","Assign This link is available","Assign This link is not available");
            CustomAssert.assertEquals(questionBank.getCustomizeThis().isDisplayed(), true,"Verify Customize This link ","Customize This link is available","Customize This link is not available");
            CustomAssert.assertEquals(myQuestionBank.tryItIcon.get(0).isDisplayed(),true,"Verify Try it link","Try it link is not available","Try it link is not available");
            ReportUtil.log("Assign custom assignment to class", "Custom assignment assigned to class section", "pass");

        } catch (Exception e) {
            Assert.fail("Exception in test case customAssignmentPage in class CustomAssignment.", e);
        }

    }

    @Test(priority = 3, enabled = true)
    public void shareThisLink() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Description","This test case validates share this link functionality under custom assignment in my question bank page","info");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            new LoginUsingLTI().ltiLogin("69");    //ReportUtil.login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.shareThis.click();//click on share this
            myQuestionBank.shareButton.click();//click on share button
            Assert.assertEquals(myQuestionBank.errorMessage.getText(),"Please add at-least one instructor to share this assessment.","Error message is not displaying");
            CustomAssert.assertEquals(myQuestionBank.shareWithPopUp.isDisplayed(),true,"Verify share with popup","Share With Pop-up is appearing below the \"Share this\" link","Share With Pop-up is not appear below the \"Share this\" link");
            CustomAssert.assertEquals(myQuestionBank.helpIcon.isDisplayed(),true,"Verify question mark icon label","question mark label icon beside the \"Share with\" label in the pop-up is available","question mark label icon beside the \"Share with\" label in the pop-up is not available");
            CustomAssert.assertEquals(myQuestionBank.shareWithTextBox.isDisplayed(),true,"Verify text box","A Text box is available to add Instructor name for sharing the custom assessment.","A Text box is not available to add Instructor name for sharing the custom assessment.");
            CustomAssert.assertEquals(myQuestionBank.shareButton.isDisplayed(),true,"Verify share button","share button is available","share button is not available");
            CustomAssert.assertEquals(myQuestionBank.cancelPopUp.isDisplayed(),true,"Verify cancel button","cancel button is available","cancel button is not available");
            myQuestionBank.cancelPopUp.click();//click on cancel
            boolean elementFound = false;
            try {
                driver.findElement(By.id("ir-ls-assign-diaReportUtil.log"));
                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            CustomAssert.assertEquals(elementFound, false,"Verify share popup","share with popup is closed","share with popup is not closed");
            myQuestionBank.shareThis.click();//click on share this
            myQuestionBank.helpIcon.click();//click on help icon
            CustomAssert.assertEquals(myQuestionBank.helpMessage.getText(),"Use this option to share custom assessments with other instructors. Once you share, you will not be able to delete this assessment.","Verify help message","help mesage is available","help mesage is not available");

        } catch (Exception e) {
            Assert.fail("Exception in test case shareThisLink of class CustomAssignment ", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void shareACustomAssignment() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Description","Instructor can share a custom assignment","info");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "32");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "32");
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(321));
            String shareName1 = ReadTestData.readDataByTagName("", "shareName", Integer.toString(322));
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);

            new LoginUsingLTI().ltiLogin("321");//ReportUtil.login as instructor
            new LoginUsingLTI().ltiLogin("322");//ReportUtil.login as instructor

            new LoginUsingLTI().ltiLogin("32");//ReportUtil.login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("32");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            new Assignment().shareCustomAssignment(shareName);
            myQuestionBank.yesOnShareMessage.click();//click on yes
            Thread.sleep(1000);
            new Assignment().shareCustomAssignment(shareName1);
            myQuestionBank.shareThis.click();//click on share this
            CustomAssert.assertEquals(myQuestionBank.sharedName.get(0).getText(),"family1, givenname1","Verify shared name","Already shared name is available","Already shared name is not available");
            boolean elementFound = false;
            try {
                driver.findElement(By.xpath("(//span[@title='Delete This'])[1]"));
                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            CustomAssert.assertEquals(elementFound, false,"Verify delete this link","Delete this link is not displaying after share the assignment","Delete this link is displaying after share the assignment");

            new LoginUsingLTI().ltiLogin("322");//ReportUtil.login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestion bank
            boolean elementFound1 = false;
            try {
                driver.findElement(By.xpath("//span[@title='Edit This']"));
                elementFound1 = true;
            } catch (Exception e) {
                //empty catch block
            }
            CustomAssert.assertEquals(elementFound1, false,"Verify edit this","Edit this field is not available","Edit this field is available");
            CustomAssert.assertEquals(questionBank.getCustomizeThis().isDisplayed(),true,"Verify customize this","Customize This link is available","Customize This link is not available");

        } catch (Exception e) {
            Assert.fail("Exception in test case shareACustomAssignment of class CustomAssignment ", e);
        }
    }

    @Test(priority = 5,enabled = true)
         public void assignNowPopUpCustomAssignmentPage(){
            WebDriver driver=Driver.getWebDriver();
            try {
                ReportUtil.log("Description","This test case validates assign now pop up in customassignment page","info");
                MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
                NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
                String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "69");

                new LoginUsingLTI().ltiLogin("69");    //ReportUtil.login as instructor
                new Navigator().NavigateTo("Question Banks");
                Thread.sleep(2000);
                List<WebElement> a1 = myQuestionBank.customizeThisLink;
                for (WebElement b : a1) {
                    if (b.isDisplayed()) {
                        b.click();
                    }
                }
                Thread.sleep(2000);
                new AssignLesson().selectQuestionForCustomAssignment("69");//select two question
                newAssignment.assessmentNameTextBox.click();//click on Name field
                newAssignment.assessmentName_TextBox.sendKeys(customassignmentname);//enter name of assignment
                driver.findElement(By.cssSelector("body")).click();//click out side
                newAssignment.assignNowButton.click();//click on ASSIGN NOW button
                int assignThisPopUp = newAssignment.assignPopUpCustomPage.size();
                if (assignThisPopUp != 1)
                    Assert.fail("\"Assign This\" pop-up does not appear on clicking ASSIGN NOW button from custom assignment page.");
                driver.findElement(By.cssSelector("body")).click();//click out side
                new AssignLesson().assignCustomAssignment(69);
                new Navigator().NavigateTo("Question Banks");
                myQuestionBank.getMyQuestionBankTitle().click();//go to My Question Bank
                String assignmentName1 = myQuestionBank.getAssessment().getText();
                if (!assignmentName1.equals(customassignmentname))
                    Assert.fail("Custom Assignment is not present in My library");
                ReportUtil.log("Verify saved custom assignment", "Custom assignment is successfully created", "pass");

            } catch (Exception e) {
                Assert.fail("Exception in test case assignNowPopUpCustomAssignmentPage in class CustomAssignment.", e);
            }
        }


    @Test(priority = 6,enabled = true)
    public void browseOptionFunctionality()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            ReportUtil.log("Description","This test case validates browse functionality of custom assignment page ","info");

            MyQuestionBank myQuestionBank = PageFactory.initElements(driver,MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver,NewAssignment.class);

            new LoginUsingLTI().ltiLogin("134");//ReportUtil.login as instructor
            new Navigator().NavigateTo("Question Banks");//Navigate to Question Banks
            new UIElement().waitAndFindElement(By.id("customAssignment"));
            myQuestionBank.customAssignmentButton_list.get(1).click();//click on create custom assignment
            int numberofquestion=driver.findElements(By.className("ls-ins-question-wrapper")).size();//check how many question appper in the search
            newAssignment.browse_link.click();//click on browse option
            boolean browsedropdown=newAssignment.browsePopUp.isDisplayed();
            if(browsedropdown==false)
            {
                Assert.fail("browse option not expand");
                ReportUtil.log("Verify browse option","Browse option is expanded","pass");
            }
            newAssignment.browse_link.click();//click on view browse option
            boolean browsedropdown1=new BooleanValue().presenceOfElement(134,newAssignment.browse_PopUp);
            if(browsedropdown1==false)
            {
                Assert.fail("browse option not collapse after 2nd time click on view browse option");
                ReportUtil.log("Verify browse option","Browse option is collapsed after 2nd time click on view browse option","pass");
            }
            newAssignment.browse_link.click();//click on view browse option
            if(!newAssignment.leftFilter.get(0).getText().contains("All Chapters"))
            {
                Assert.fail("all chapter not present");
                ReportUtil.log("Verify all chapter drop down","All chapter drop down is present","pass");
            }
            System.out.println(newAssignment.leftFilter.get(1).getText());
            if(!newAssignment.leftFilter.get(1).getText().contains("All Sections"))
            {
                Assert.fail("All Sections not present");
                ReportUtil.log("Verify All Sections drop down","All Sections drop down is present","pass");
            }
            if(!newAssignment.rightFilter.get(0).getText().contains("All Objectives"))
            {
                Assert.fail("All Objectives not present");
                ReportUtil.log("Verify All Objectives drop down","All Objectives drop down is present","pass");
            }
            if(!newAssignment.rightFilter.get(1).getText().contains("All Difficulty"))
            {
                Assert.fail("All Difficulty not present");
                ReportUtil.log("Verify All Difficulty drop down","All Difficulty drop down is present","pass");
            }
            if(!newAssignment.leftFilter.get(2).getText().contains("All Question types"))
            {
                Assert.fail("All Question types not present");
                ReportUtil.log("Verify All Question types drop down","All Question types drop down is present","pass");
            }
            if(!newAssignment.showQuestionFromEtextbook.getText(). contains("Show questions from e-Textbook"))
            {
                Assert.fail("Show questions from e-Textbook not present");
                ReportUtil.log("Verify Show questions from e-Textbook check box","All Question types drop down is present","pass");
            }
            boolean instructoronlychecked=newAssignment.checkboxForInstructorAssignment.isDisplayed();
            if(instructoronlychecked==false)
            {
                Assert.fail("by deafult instructor only chech box checked");
            }
            newAssignment.allChapters.click();//Click on all chapter
            newAssignment.subChapter.click();//click on chapter1
            newAssignment.allObjectives.click();//click on all objectives
            newAssignment.allSections.click();//click on all sections
            newAssignment.subSection.click();//click on sub section 1.1
            newAssignment.allDifficultyLevels.click();//click on difficulty level
            newAssignment.difficulty_Level.click();//click on difficulty level
            newAssignment.allQuestionTypes.click();//click on all question types
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            String filtercount=newAssignment.filterCount.getText();//fetch count of filter
            String filtercount1=newAssignment.filterCount.getText();
            if(!filtercount.equals(filtercount1))
            {
                Assert.fail("Filter count is not equal again click on browse option");
            }
            int numberofquestion1=driver.findElements(By.className("ls-ins-question-wrapper")).size();
            if(numberofquestion1>numberofquestion)
            {
                Assert.fail("after click on instructor only filter number of question not decrease");
            }

            newAssignment.browse_link.click(); //click on the browse link
            String position= newAssignment.advancedFilter_link.getCssValue("float");
            Assert.assertEquals(position,"left","'+Advanced filters' link is not displayed to the left of GO button.");
            ReportUtil.log("Verify advanced filter link","+Advanced filters' link is displayed to the left of GO button","pass");
            newAssignment.advancedFilter_link.click(); //click on the advanced filter link
            new UIElement().waitAndFindElement(By.cssSelector("span[for='.ls-ins-customize-checkbox-hint']"));
            Thread.sleep(2000);
            Assert.assertEquals(newAssignment.advancedFilter_list.get(0).getText().trim(), "Show only questions with hints", "'Not Showing only questions with Hints' check-box.");
            ReportUtil.log("Verify Show only questions with hints checkbox","Show only questions with hints checkbox is displayed","pass");
            Assert.assertEquals(newAssignment.advancedFilter_list.get(1).getText().trim(),"Show only questions with solutions" ,"Not Showing questions with Solution' checkbox.");
            ReportUtil.log("Verify Show only questions with solutions checkbox","Show only questions with solutions checkbox is displayed","pass");
            Assert.assertEquals(newAssignment.advancedFilter_list.get(2).getText().trim(),"Show only algorithmic questions" ,"'Not Showing only algorithmic questions' check-box.");
            ReportUtil.log("Verify Show only algorithmic questions checkbox","Show only algorithmic questions checkbox is displayed","pass");

            boolean checked=false;
            List<WebElement> advancedFilterCheckbox = newAssignment.advancedFilter_checkbox;
            for(WebElement checkbox:advancedFilterCheckbox)
            {
                if(checkbox.isSelected()==true){
                    checked=true;
                }
            }
            if(checked==true){
                Assert.fail("All  three checkboxes is not unchecked by default.");
                ReportUtil.log("Verify the three checkbox","All three checkboxes is unchecked by default","pass");
            }

            Assert.assertEquals(newAssignment.advancedFilter_link.getText().trim(),"- Advanced Filters"," \"+Advanced filters\" link is not to changed \"-Advanced filters\"");
            ReportUtil.log("Verify -Advanced Filters link"," \"+Advanced filters\" link is changed to \"-Advanced filters\"","pass");
            for(int i=0;i<=3;i++){
                try {
                    newAssignment.advancedFilter_checkbox.get(0).click(); // click on the first checkbox
                    break;
                } catch (Exception e) {
                }

            }
            newAssignment.advancedFilter_link.click();//click on the -advanced filter link
            Thread.sleep(3000);
            newAssignment.advancedFilter_link.click();//click on the -advanced filter link
            if(!newAssignment.advancedFilter_checkbox.get(0).getAttribute("class").equals("ls-ins-customize-checkbox-hint checked")){
                Assert.fail("Not showing same results un till the instructor navigates from the current assignment page. (Chech box should be checked for 'Show only questions with hints')");
                ReportUtil.log("Verify the search result","showing same results un till the instructor navigates from the current assignment page. (Chech box should be checked for 'Show only questions with hints')","pass");
            }
            newAssignment.allQuestionType_dropdown.click();//click on the all question type

            int allQuestionTypeSize=newAssignment.allQuestionType_Filter.size();
            Assert.assertEquals(allQuestionTypeSize,18,"Not displaying 'All Question types' option followed by all the question types as supported by the product in (ascending order of question type name).");
            ReportUtil.log("Verify presence of all question types"," Displaying All Question types' option followed by all the question types as supported by the product in (ascending order of question type name).","pass");
            List<WebElement> checkBoxType=newAssignment.allQuestionType_checkbox;
            for(WebElement questionType:checkBoxType) {
                if(!questionType.getAttribute("type").equals("checkbox"))
                    Assert.fail("Not displaying check box infront of 'All the Question types' and for all the question types supported by the product.");
                ReportUtil.log("Verify presence of checkbox before all question types","Displaying check box infront of 'All the Question types' and for all the question types supported by the product.","pass");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in test case browseOptionFunctionality in method CustomAssignment ",e);
        }
    }


    @Test(priority=7,enabled=true)
    public void expandFunctionalityOfSearchedResult()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Description", "This test case validates expand functionality of search results in currrent assignment page", "info");
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchtext = ReadTestData.readDataByTagName("tocdata", "searchtext", "1");

            new LoginUsingLTI().ltiLogin("134");// ReportUtil.login as instructor
            new Navigator().NavigateTo("Question Banks");// Navigate to Question Banks
            myQuestionBank.customAssignmentButton_list.get(1).click();//click on create custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchtext);
            new AssignLesson().selectQuestionForCustomAssignment("134");
            Thread.sleep(2000);
            newAssignment.expand_Question.get(0).click();//click on link to expand the search question
            String expandText = newAssignment.expandAllQuestionLink.getText();
            Assert.assertEquals(true, expandText.contains("Expand all questions"), "By default expand/collapse option not be in close state and not show the label “Expand All Questions”");
            ReportUtil.log("Verify Expand all questions label", "Expand all questions label is displayed", "pass");
            newAssignment.expandAll_Icon.get(0).click();//click on expand all question
            String collapseText = newAssignment.expandAllQuestionLink.getText();
            Assert.assertEquals(true, collapseText.contains("Collapse all questions"), "After click on expand all questions text not be changed to 'Collapse all questions'");
            ReportUtil.log("Verify Collapse all questions label", "After click on expand all questions text it changed to 'Collapse all questions", "pass");
            newAssignment.collapseAllIcon.click();//click on collapse all question icon
            String expandText1 = newAssignment.expandAllQuestionLink.getText();
            Assert.assertEquals(true, expandText1.contains("Expand all questions"), "After click on collapse all questions label not changed to  “Expand All Questions”");
            ReportUtil.log("Verify Expand all questions label", "After click on collapse all questions label it changed to “Expand All Questions”", "pass");
            newAssignment.expandQuestion.click();//click on + icon
            String expandedarea = newAssignment.questionWrapperContent.get(0).getText();
            if (!expandedarea.contains("Question Set: ")) {
                Assert.fail("assessment name not shown");
            }
            if (!expandedarea.contains("Ch")) {
                Assert.fail("chapter name not shown");
            }
            String question = newAssignment.cmsQuestionPreview.getText();
            if (question == null) {
                Assert.fail("question is not shown");
            }
            newAssignment.expand_Question.get(1).click();//click on other question expand
            newAssignment.expandQuestion.click();
            newAssignment.expand_Question.get(0).click();//click on expanded question  icon
        }
            catch (Exception e) {
            Assert.fail("Exception in test case expandFunctionalityOfSearchedResult in method CustomAssignment ",e);
        }
    }
    @Test(priority=8,enabled=true)
    public void selectedQuestionsTabCustomAssignmentPage() {
        try {
            WebDriver driver=Driver.getWebDriver();
            ReportUtil.log("Description","This test case validates selected Questions Tab in CustomAssignment Page","info");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String searchtext = ReadTestData.readDataByTagName("tocdata", "searchtext", "1");

            new LoginUsingLTI().ltiLogin("134");// ReportUtil.login as instructor
            new Navigator().NavigateTo("Question Banks");// Navigate to Question Banks
            myQuestionBank.customAssignmentButton_list.get(1).click();//click on create custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchtext);
            new AssignLesson().selectQuestionForCustomAssignment("134");
            Thread.sleep(2000);
            newAssignment.tabTittle.get(4).click();
            Thread.sleep(2000);
            int questiononyourquestiontab=newAssignment.scoreBox.size();
            if(questiononyourquestiontab<2)
            {
                Assert.fail("cheked question not added in Selected question tab");
            }
            int expandecount=newAssignment.expand_Question.size();
            System.out.println(expandecount);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", newAssignment.expand_Question.get(expandecount - 2));
            String expandedareasearchquestion=newAssignment.questionWrapperContentSelectTab.get(0).getText();
            if(!expandedareasearchquestion.contains("Question Set: "))
            {
                Assert.fail("assessment name not shown");
            }
            if(!expandedareasearchquestion.contains("Ch"))
            {
                Assert.fail("chapter name not shown");
            }
            if(!expandedareasearchquestion.contains("Q"))
            {
                Assert.fail("Question is not shown");
            }
            newAssignment.manualGradingHelpIcon.click();//click on manual grading help icon
            String manualGradingHelpTextActual = newAssignment.manualGradingHelpMessage.getText();
            CustomAssert.assertEquals(true, manualGradingHelpTextActual.contains("Use this checkbox to grade the assignment manually."),"Verify help text","help text shown for manual grading", "help text not shown for manual grading");
            CustomAssert.assertEquals(newAssignment.pointCheckBox.get(0).isDisplayed(),true,"Verify point check box","points check box is not available","points check box is not available");
            newAssignment.pointCheckBox.clear();//clear the check box
            newAssignment.pointCheckBox.get(0).sendKeys("2");
            String questionType=newAssignment.questionType.getText();
            CustomAssert.assertEquals("Question Type:True/False","Question Type:"+questionType+"","Verify question type","question type is available","question type is not available");
            Thread.sleep(2000);
            CustomAssert.assertEquals(newAssignment.expandAllIcon.isDisplayed(),true,"Verify Expand icon","Expand icon is available at the top end of the question card","Expand icon is not available at the top end of the question card");
            newAssignment.expandAllIcon.click();//click on expand all icon
            CustomAssert.assertEquals(newAssignment.collapseAllIcon.isDisplayed(),true,"Verify Collapse icon","Collapse icon is available at the top end of the question card","Collapse icon is not available at the top end of the question card");
            newAssignment.collapseAllIcon.click();//click on collapse icon
            boolean elementFound = false;
            try{
                driver.findElement(By.linkText("Difficulty Level"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            CustomAssert.assertEquals(elementFound,false,"Verify selected question","selected question is collapsed","selected question is not collapsed");
            String beforeDrag=newAssignment.secondQuestionBar.get(0).getText();
            Actions act =new Actions(driver);
            WebElement dragAble=newAssignment.questionBar.get(2);
            WebElement dropAble=newAssignment.questionBar.get(0);
            act.clickAndHold(dragAble).build().perform();
            act.moveToElement(dropAble).release().build().perform();
            String afterDrag=newAssignment.secondQuestionBar.get(0).getText();
            if(beforeDrag.equals(afterDrag))
                Assert.fail("The Question is not displaying at the dropped position for the Assessment");
            CustomAssert.assertEquals(newAssignment.questionLabel.getText(),"Q1:","Verify question number","The question number is starting with 1","The question number is not starting with 1");

        }
        catch (Exception e) {
            Assert.fail("Exception in test case selectedQuestionsTabCustomAssignmentPage in method CustomAssignment ",e);
        }
    }

    @Test(priority = 9, enabled = true)
    public void updateAssignmentPage() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Description","This test case validates update assignment page","info");
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "231");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "231");
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);

            new LoginUsingLTI().ltiLogin("231");//ReportUtil.login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("231");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(231);
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            String assignment = currentAssignments.getCreatedAssignmentTitle().getAttribute("title");
            CustomAssert.assertEquals(assignment,"New Name","Verify Custom assignment tab","New tab is opened in current assignment page","\"New tab is not opened in current assignment page");
            String assignmentTitle = currentAssignments.getCreatedAssignmentName().getText();
            CustomAssert.assertEquals(assignmentTitle,"New Name","Verify assignment name","Assignment name is displaying","Assignment name is not displaying");
            String cancel = currentAssignments.getCancelButton().getText();
            CustomAssert.assertEquals(cancel,"Cancel","Verify cancel button","Cancel button is displayed","Cancel button is not displayed");
            String reAssign = currentAssignments.getReassign_button().getText();
            CustomAssert.assertEquals(reAssign,"RE-ASSIGN","Verify reassign button","RE-ASSIGN is displayed","RE-ASSIGN is not displayed");
            int question = currentAssignments.getQuestion().size();
            if ( question == 0){
                Assert.fail("All the questions are not displaying in card view");
            }
            String quesionNo=currentAssignments.getQuestionNo().getText();
            CustomAssert.assertEquals(quesionNo,"Q1:","Verify question no.","question no is displaying","question no is not displaying");
            String questionType=currentAssignments.getQuestionType().getText();
            Thread.sleep(3000);
            CustomAssert.assertEquals(questionType.trim(),"Question Type:","Verify question type","Question type is displayed in the 2nd line","Question type is not displayed in the 2nd line");
            String delete = currentAssignments.delete_Icon.get(0).getAttribute("title");
            System.out.println(delete);
            CustomAssert.assertEquals(delete,"Delete","Verify delete icon","The Delete icon is displayed","The Delete icon is not displayed");
            currentAssignments.getExpendQuestion().click();//click on expand icon
            String difficultyLevel = currentAssignments.getDifficultyLevel().getText();
            CustomAssert.assertEquals(difficultyLevel,"Difficulty Level:","Verify difficulty level","Question is expanded after clicking on three dots","Question is not expanded after clicking on three dots");
            boolean expandIcon=currentAssignments.getExpandIcon().isDisplayed();
            Assert.assertEquals(expandIcon,true,"The symbol is not converted to collapse icon");
            currentAssignments.getExpandIcon().click();//click on expand icon
            boolean difficulty=currentAssignments.getDifficultyLevel().isDisplayed();
            Assert.assertEquals(difficulty,false,"The question is not collapsed");
            currentAssignments.getDeleteIcon().click();//click on delete
            currentAssignments.getYesOnNotificationMessage().click();//click on yes
            Thread.sleep(2000);
            currentAssignments.getReassign_button().click();//click on reassign
            currentAssignments.getAssign().click();//click on assign
            currentAssignments.yesLinkAfterAssign.click();//click on yes

            Thread.sleep(1000);
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            boolean elementFound = false;
            try{
                driver.findElement(By.linkText("Delete This"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            CustomAssert.assertEquals(elementFound,false,"Verify delete option","Delete option is available","Delete option is not available");
            Thread.sleep(1000);
            myQuestionBank.getPreviewButton().click();//click on preview of “Assessment Name - V1”
            int questionCount = myQuestionBank.getQuestionCount().size();
            if(questionCount > 1){
                Assert.fail("List of question preview except the deleted question is not displaying");
            }
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            Thread.sleep(1000);
            new AssignLesson().Assigncustomeassignemnt(231);//assign assignment
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            myQuestionBank.getPreviewButtonOfOriginal().click();//click on preview of original custom assignment

            int questionCount1 = myQuestionBank.getQuestionCount().size();
            if(questionCount1 > 2){
                Assert.fail("Deleted question is not displaying");
            }

            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            Thread.sleep(2000);
            currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on un-assign of version assignment
            currentAssignments.getYesOnUnAssignPopUp().click();//click on yes of unassign popup
            Thread.sleep(2000);
            currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on un-assign of version assignment
            currentAssignments.getYesOnUnAssignPopUp().click();//click on yes of unassign popup


        } catch (Exception e) {
            Assert.fail("Exception in test case updateAssignmentPage in class CustomAssignment", e);
        }
    }

    @Test(priority = 10, enabled = true)
    public void anyStudentHasAccessedTheAssignmentBeforeDelete() {
        WebDriver driver=Driver.getWebDriver();
        try {

            ReportUtil.log("Description","This test case validates Student can access assignment before delete","info");
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "51");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "51");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);

            new LoginUsingLTI().ltiLogin("51");//create instructor
            new LoginUsingLTI().ltiLogin("55");//create student
            new LoginUsingLTI().ltiLogin("51");//ReportUtil.login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("51");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(6000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(51);//assign assignment
            Thread.sleep(2000);
            currentAssignments.getViewGrade_link().click();//click on view student responses
            String points=assignmentResponsesPage.getTotalPoints().getText();
            CustomAssert.assertEquals(points, "Total Points: 4", "Verify Total points","Total points is displaying","Total points is not displaying");
            currentAssignments.getCurrentAssignmentTitle().click();//click on current assignment
            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            currentAssignments.getDeleteIcon().click();//click on delete
            currentAssignments.getYesOnNotificationMessage().click();//click on yes
            Thread.sleep(2000);
            currentAssignments.getReassign_button().click();//click on reassign
            currentAssignments.getAssign().click();//click on assign
            currentAssignments.yesLinkAfterAssign.click();//click on yes
            Thread.sleep(1000);
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click();//click on view student responses
            boolean elementFound1 = false;
            try{
                driver.findElement(By.xpath("(//div[contains(@class,'idb-gradebook-content-coloumn idb-gradebook-question-coloumn')])[4]"));
                elementFound1 = true;
            }
            catch (Exception e){
                //empty catch block
            }
            CustomAssert.assertEquals(elementFound1,false,"Verify deleted question column","Deleted question column is available","Deleted question column is not available");
            String points1=assignmentResponsesPage.getTotalPoints().getText();
            CustomAssert.assertEquals(points1,"Total Points: 3","Verify total points","Total points is updated after delete a question","Total points is not updated after delete a question");
            Thread.sleep(2000);
            assignmentResponsesPage.getPreviewOfAssignment().click();
            int questionCount = myQuestionBank.getQuestionCount().size();
            if(questionCount > 3){
                Assert.fail("List of question preview except the deleted question is not displaying");
            }
            currentAssignments.getCurrentAssignmentTitle().click();//click on current assignment
            currentAssignments.getPreviewOfAssignmentOnCurrentAssignment().click();//click on preview of assignment
            int questionCount1 = myQuestionBank.getQuestionCount().size();
            if(questionCount1 > 3){
                Assert.fail("List of question preview except the deleted question is not displaying");
            }
            new LoginUsingLTI().ltiLogin("55");//ReportUtil.login as student
            new Assignment().submitAssignmentAsStudent(51); //submit assignment
            new LoginUsingLTI().ltiLogin("51");//ReportUtil.login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.getViewGrade_link().click();//click on view student responses
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            currentAssignments.getNextArrow().click();
            currentAssignments.getNextArrow().click();
            currentAssignments.getCurrentAssignmentTitle().click();//click on current assignment
            currentAssignments.getViewGrade_link().click();//click on view student responses
            boolean elementFound2 = false;
            try{
                driver.findElement(By.xpath("(//div[contains(@class,'idb-gradebook-content-coloumn idb-gradebook-question-coloumn')])[4]"));
                elementFound2 = true;
            } catch (Exception e){
                //empty catch block
            }
            CustomAssert.assertEquals(elementFound2,false,"Verify view response for deleted question","View response for deleted question is displaying","View response for deleted question is not displaying");
            new Assignment().provideGradeToStudentForMultipleQuestions(51);
            new Assignment().releaseGrades(51,"Release Grade for All");
            String grade=assignmentResponsesPage.getGrade().getText();
            CustomAssert.assertEquals(grade,"2.4","Verify total grade","Total grade is displaying after grade released","Total grade is not displaying after grade released");


        } catch (Exception e) {
            Assert.fail("Exception in test case anyStudentHasAccessedTheAssignmentBeforeDelete in class CustomAssignment", e);
        }
    }
    @Test(priority = 11, enabled = true)
    public void  questionIsDeletedAfterSomeStudentsHaveAttempted() {
        WebDriver driver=Driver.getWebDriver();
        try {

            ReportUtil.log("Description","Create custom assignment,assign assignment,student submit assignment,instructor delete question and check status of deleted question in view response page","info");

            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "65");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(65));

            new Assignment().create(65);
            new Assignment().addQuestions(65, "writeboard", "");
            new Assignment().addQuestions(65, "essay", "");
            ReportUtil.log("Create assessment","Created assessment with 3 questions truefalse,writeboard,essay type question","pass");

            new LoginUsingLTI().ltiLogin("65_1");//create student
            new LoginUsingLTI().ltiLogin("65_2");//create student
            new LoginUsingLTI().ltiLogin("65_3");//create student
            new LoginUsingLTI().ltiLogin("65_4");//create student

            new LoginUsingLTI().ltiLogin("65");//ReportUtil.log in as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            questionBank.getQuestionBankTitle().click();//click on question bank
            myQuestionBank.allResourse_textarea.clear();
            myQuestionBank.allResourse_textarea.sendKeys("\"" + assignmentname + "\"");
            myQuestionBank.search_Button.click();//click on search
            Thread.sleep(2000);

            List<WebElement> a=myQuestionBank.customizeThisLink;
            for(WebElement b:a){
                if(b.isDisplayed()){
                    b.click();
                }
            }
            //click on customize this link
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("65");//select three question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            ReportUtil.log("Create custom assignment","Created custom assignment with 3 questions","pass");

            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(65);//assign assignment
            ReportUtil.log("Assign custom assignment","Assigned custom assignment to class","pass");

            new LoginUsingLTI().ltiLogin("65_2");//ReportUtil.login as student2
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            Thread.sleep(12000);
            ReportUtil.log("Student 2 in progress","Student 2 opened and left assignment","pass");

            new LoginUsingLTI().ltiLogin("65_3");//ReportUtil.login as student3
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().essay(false,"incorrect",65);
            new Assignment().nextButtonInQuestionClick();
            new AttemptQuestion().writeBoard(false,"incorrect",65);
            Thread.sleep(3000);
            new Assignment().nextButtonInQuestionClick();
            ReportUtil.log("Student 3 in progress","Student 2 started and left assignment","pass");


            new LoginUsingLTI().ltiLogin("65_4");//ReportUtil.login as student4
            new Assignment().submitAssignmentAsStudent(65); //submit assignment
            ReportUtil.log("Student 4 submitted","Student 4 submitted assignment","pass");

            new LoginUsingLTI().ltiLogin("65");//ReportUtil.log in as instructor
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            currentAssignments.getDeleteIconTwo().click();//click on delete
            currentAssignments.getYesOnNotificationMessage().click();//click on yes
            Thread.sleep(2000);
            currentAssignments.getReassign_button().click();//click on reassign
            currentAssignments.getExtendDueDate().click();//click on extend due date
            driver.findElement(By.id("extended-due-time")).click();//enter time
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals("12:15 AM")) {
                    time.click();
                    break;
                }
            }
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)","");
            Thread.sleep(2000);
            currentAssignments.getUpdate().click();//click on update
            currentAssignments.updateNotificationYesLink.click();//click on yes
            Thread.sleep(3000);
            ReportUtil.log("Instructor update assignment","Instructor deleted 1 question and reassigned assignment with extend due date","pass");

            currentAssignments.getViewGrade_link().click();//click on view response
            String totalPoint=assignmentResponsesPage.getTotalPoints().getText();
            System.out.println("totalPoint:"+totalPoint);
            Assert.assertEquals(totalPoint,"Total Points: 3","total point is not including the deleted question");
            ReportUtil.log("Verify total point","total point is including the deleted question","pass");
            String deletedQuestion=assignmentResponsesPage.getDeletedQuestion().getText();
            Assert.assertEquals(deletedQuestion,"Q3","Deleted Question column is not displaying");
            ReportUtil.log("Verify deleted question column","Deleted Question column is displaying","pass");
            assignmentResponsesPage.getPreviewOfAssignment().click();
            int questionCount = myQuestionBank.getQuestionCount().size();
            if(questionCount > 2){
                Assert.fail("List of question preview except the deleted question is not displaying");
                ReportUtil.log("Verify deleted question","List of question preview except the deleted question is displaying","pass");
            }
            currentAssignments.getCurrentAssignmentTitle().click();//click on current assignment
            currentAssignments.getPreviewOfAssignmentOnCurrentAssignment().click();//click on preview of assignment
            int questionCount1 = myQuestionBank.getQuestionCount().size();
            if(questionCount1 > 2){
                Assert.fail("List of question preview except the deleted question is not displaying");
                ReportUtil.log("Verify deleted question","List of question preview except the deleted question is displaying","pass");
            }
            new LoginUsingLTI().ltiLogin("65_1");//ReportUtil.login as student1
            new Assignment().submitAssignmentAsStudent(65); //submit assignment
            ReportUtil.log("student post discussion","Student 1 submitted the assignment","pass");

            new LoginUsingLTI().ltiLogin("65_2");//ReportUtil.login as student2
            new Assignment().submitAssignmentAsStudent(65); //submit assignment
            ReportUtil.log("student post discussion","Student 2 submitted the assignment","pass");

            new LoginUsingLTI().ltiLogin("65_3");//ReportUtil.login as student3
            new Assignment().submitAssignmentAsStudent(65); //submit assignment
            ReportUtil.log("student post discussion","Student 3 submitted the assignment","pass");

            new LoginUsingLTI().ltiLogin("65");//ReportUtil.login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            String status=currentAssignments.status_reviewInProgress.getText();
            Assert.assertEquals(status,"Needs Grading","status is not showing review in progress");
            ReportUtil.log("Verify status","Needs Grading status is showing review in progress","pass");
            Thread.sleep(2000);
            String submitBoxCount=currentAssignments.getSubmittedBoxCount().getText();
            Assert.assertEquals(submitBoxCount,"4","submit box is not displaying the total student count");
            ReportUtil.log("verify submit box count","submit box is displaying the total student count","pass");
            currentAssignments.getViewGrade_link().click();//click on view student responses
            String deletedQuestion1=assignmentResponsesPage.getDeletedQuestion().getText();
            Assert.assertEquals(deletedQuestion1,"Q3","Deleted Question column is not displaying");
            ReportUtil.log("Verify deleted question column","Deleted Question column is displaying","pass");
            String crossIcon=assignmentResponsesPage.getCrossIconOnDeletedQuestion().getAttribute("title");
            Assert.assertEquals(crossIcon,"This question has been deleted by the instructor.","deleted message is not displaying");
            ReportUtil.log("Verify cross icon notification message","This question has been deleted by the instructor.deleted message is displaying","pass");
            assignmentResponsesPage.getCrossIcon().click();
            String crossIcon1=assignmentResponsesPage.getCrossIcon().getAttribute("title");
            Assert.assertEquals(crossIcon1,"Question not delivered","deleted message is not displaying");
            ReportUtil.log("Verify notification message","Question not delivered deleted message is not displaying","pass");
            int questionSize=assignmentResponsesPage.getQuestionLabels().size();
            if(questionSize>3){
                Assert.fail("deleted question column is not available");
            }
            assignmentResponsesPage.getViewResponsesforDeletedquestion().click();
            String message=assignments.getNotificationMessage().getText();
            Assert.assertEquals(message,"This question is deleted and is no longer part of this assignment.","notification message for deleted question is not displaying");
            ReportUtil.log("Verify notification message","This question is deleted and is no longer part of this assignment notification message for deleted question is displaying","pass");
            assignmentResponsesPage.getFeedbackBox().sendKeys("This is a feedback");
            assignmentResponsesPage.getSaveButton().click();//click on save
            currentAssignments.getCurrentAssignmentTitle().click();//click on current assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new Assignment().provideGradeToStudentForMultipleQuestions(65);
            new Assignment().releaseGrades(65,"Release Grade for All");
            String gradedBoxCount=assignmentResponsesPage.getGradeBoxCount().getText();
            Assert.assertEquals(gradedBoxCount,"4","gradebox count not displying total student count");
            ReportUtil.log("verify graded Box Count","gradebox count is displying total student count","pass");

        } catch (Exception e) {
            Assert.fail("Exception in test case questionIsDeletedAfterSomeStudentsHaveAttempted in class CustomAssignment", e);
        }
    }


    @Test(priority = 12, enabled = true)
    public void  courseStreamEntryForDiscussionAddedToDeletedQuestion() {
        WebDriver driver=Driver.getWebDriver();
        try {

            ReportUtil.log("Description","Create custom assignment,assign assignment,student post discussion,instructor delete question where discussion is added","info");

            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "99");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "99");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);

            new LoginUsingLTI().ltiLogin("99_1");//create student1
            new LoginUsingLTI().ltiLogin("99_2");//create student2

            new LoginUsingLTI().ltiLogin("99");//ReportUtil.log in as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("99");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            ReportUtil.log("Create custom assignment","Created custom assignment with 3 questions","pass");

            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(99);//assign assignment
            ReportUtil.log("Assign custom assignment","Assigned custom assignment to class","pass");

            new LoginUsingLTI().ltiLogin("99_1");//ReportUtil.login as student1
            new Navigator().NavigateTo("Assignments");
            new Assignment().submitAssignmentAsStudent(99); //submit assignment
            Thread.sleep(2000);
            ReportUtil.log("Submit assignment","Student 1 submitted the assignment","pass");
            new ClickOnquestionCard().clickonquestioncard(0);
            assignments.getDiscussionTab().click();
            assignments.getNewButton().click();
            assignments.getEditBox().sendKeys("aassdfdghh asdfgjj");
            assignments.getSubmit().click();
            ReportUtil.log("student post discussion","Student 1 submitted the assignment","pass");

            new LoginUsingLTI().ltiLogin("99");//ReportUtil.log in as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            currentAssignments.getThreeDots().click();//click on three dots
            currentAssignments.getExpendQuestion().click();//click on expand icon
            String text=currentAssignments.getQuestionLabel().getText();
            currentAssignments.getDeleteIcon().click();//click on delete
            currentAssignments.getYesOnNotificationMessage().click();//click on yes
            Thread.sleep(2000);
            currentAssignments.getReassign_button().click();//click on reassign
            currentAssignments.getExtendDueDate().click();
            driver.findElement(By.id("extended-due-time")).click();
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals("12:15 AM")) {
                    time.click();
                    break;
                }
            }
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)","");
            Thread.sleep(2000);
            currentAssignments.getUpdate().click();
            currentAssignments.updateNotificationYesLink.click();//click on yes
            Thread.sleep(2000);
            ReportUtil.log("Instructor update assignment","Instructor deleted 1 question and reassigned assignment with extend due date","pass");

            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.getJumpOut().click();//click on jumpout
            new UIElement().waitAndFindElement(By.xpath("//div[@class='al-diag-test-chapter-label']"));
            String question=courseStreamPage.getQuestion().getText();
            if (!text.contains(question)){
                Assert.fail("Instructor should not get navigated to deleted question");
                ReportUtil.log("Verify deleted question","Instructor navigated to deleted question","pass");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case courseStreamEntryForDiscussionAddedToDeletedQuestion in class CustomAssignment", e);
        }
    }

    @Test(priority = 13, enabled = true)
    public void  assignmentWithPolicyHavingGradeReleaseOption1() {

        try {
            WebDriver driver=Driver.getWebDriver();
            ReportUtil.log("Description","Assign custom assignment to class with policy 1,delete 1 question,reassign assignment with extend due date","info");

            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "101");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "101");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "101");
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);

            new LoginUsingLTI().ltiLogin("101_1");//create student1
            new LoginUsingLTI().ltiLogin("101_2");//create student2

            new LoginUsingLTI().ltiLogin("101");//ReportUtil.log in as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("101");//select three question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            ReportUtil.log("Create custom assignment","Created custom assignment with 3 questions","pass");

            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//till save policy
            new Navigator().NavigateTo("My Question Bank");//navigate to Assignments
            new AssignLesson().Assigncustomeassignemnt(101);//assign assignment
            ReportUtil.log("Assign custom assignment","Assigned custom assignment to class with policy 1","pass");

            new LoginUsingLTI().ltiLogin("101_1");//ReportUtil.login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//open lesson assignment
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false, "incorrect", 101);
            new Assignment().nextButtonInQuestionClick();
            new AttemptQuestion().trueFalse(false, "incorrect", 101);
            new Assignment().nextButtonInQuestionClick();
            ReportUtil.log("Student 1 in progress","Student 2 attempt 2questions and left assignment","pass");

            new LoginUsingLTI().ltiLogin("101");//ReportUtil.log in as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            currentAssignments.getDeleteIconTwo().click();//click on delete
            currentAssignments.getYesOnNotificationMessage().click();//click on yes
            Thread.sleep(5000);
            currentAssignments.getReassign_button().click();//click on reassign
            currentAssignments.getExtendDueDate().click();
            driver.findElement(By.id("extended-due-time")).click();
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals("12:15 AM")) {
                    time.click();
                    break;
                }
            }
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)","");
            Thread.sleep(2000);
            currentAssignments.getUpdate().click();
            currentAssignments.updateNotificationYesLink.click();//click on yes
            Thread.sleep(2000);
            ReportUtil.log("Instructor update assignment","Instructor deleted 1 question and reassigned assignment with extend due date","pass");

            new LoginUsingLTI().ltiLogin("101_1");//ReportUtil.login as student1
            new Assignment().submitAssignmentAsStudent(101); //submit assignment
            ReportUtil.log("Submit assignment","Student 1 submitted the assignment","pass");
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            Thread.sleep(2000);
            int question=assignments.getWidth().size();
            if(question > 3){
                Assert.fail("scoreover assignment page is not included deleted question");
                ReportUtil.log("Verify deleted question","Deleted question is included","pass");

            }

            new LoginUsingLTI().ltiLogin("101_2");//ReportUtil.login as student2
            new Assignment().submitAssignmentAsStudent(101); //submit assignment
            ReportUtil.log("Submit assignment","Student 2 submitted the assignment","pass");
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            Thread.sleep(2000);
            int question1=assignments.getWidth().size();
            if(question1 > 2){
                Assert.fail("scoreover assignment page is not included deleted question");
                ReportUtil.log("Verify deleted question","Deleted question is included","pass");

            }

            new LoginUsingLTI().ltiLogin("101");//ReportUtil.log in as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.getViewGrade_link().click();//click on view student responses
            Thread.sleep(2000);
            String assigmentStatus=assignmentResponsesPage.review_Status.get(1).getText();
            Assert.assertEquals(assigmentStatus,"Graded","Assignment status not displaying graded");
            ReportUtil.log("Verify status","Instructor  status is displayed graded","pass");

        } catch (Exception e) {
            Assert.fail("Exception in test case assignmentWithPolicyHavingGradeReleaseOption1 in class CustomAssignment", e);
        }
    }


    @Test(priority = 14, enabled = true)
    public void  assignmentWithPolicyHavingGradeReleaseOption3() {
        WebDriver driver=Driver.getWebDriver();
        try {

            ReportUtil.log("Description","Assign custom assignment to class with policy 3,delete 1 question,reassign assignment with extend due date","info");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "109");
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "109");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(109));

            new LoginUsingLTI().ltiLogin("109_1");//create student1
            new LoginUsingLTI().ltiLogin("109_2");//create student2
            new LoginUsingLTI().ltiLogin("109_3");//create student3

            new LoginUsingLTI().ltiLogin("109");//ReportUtil.log in as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            questionBank.getQuestionBankTitle().click();//click on question bank
            myQuestionBank.allResourse_textarea.clear();
            myQuestionBank.allResourse_textarea.sendKeys("\"" + assignmentname + "\"");
            myQuestionBank.search_Button.click();//click on search
            Thread.sleep(2000);

            List<WebElement> a=myQuestionBank.customizeThisLink;
            for(WebElement b:a){
                if(b.isDisplayed()){
                    b.click();
                }
            }
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("109");//select three question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            ReportUtil.log("Create custom assignment","Created custom assignment with 3 questions","pass");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "");//till save policy*//**//**//**//**//**//**//**//*
            new Navigator().NavigateTo("My Question Bank");//navigate to Assignments
            new AssignLesson().Assigncustomeassignemnt(109);//assign assignment
            ReportUtil.log("Assign custom assignment","Assigned custom assignment to class with policy 3","pass");

            new LoginUsingLTI().ltiLogin("109_1");//ReportUtil.login as student1
            new Assignment().submitAssignmentAsStudent(109); //submit assignment
            ReportUtil.log("Submit assignment","Student 1 submitted the assignment","pass");

            new LoginUsingLTI().ltiLogin("109_2");//ReportUtil.login as student2
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//open lesson assignment
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().essay(false, "incorrect", 109);
            assignments.getSubmitAssignment().click();
            new AttemptQuestion().writeBoard(false,"incorrect",109);
            assignments.getSubmitAssignment().click();
            ReportUtil.log("Student 2 in progress","Student 2 attempt 2questions and left assignment","pass");

            new LoginUsingLTI().ltiLogin("109");//ReportUtil.log in as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            currentAssignments.getDeleteIconTwo().click();//click on delete
            currentAssignments.getYesOnNotificationMessage().click();//click on yes
            Thread.sleep(2000);
            currentAssignments.getReassign_button().click();//click on reassign
            currentAssignments.getExtendDueDate().click();//click on extend due date
            driver.findElement(By.id("extended-due-time")).click();//enter time
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals("12:15 AM")) {
                    time.click();
                    break;
                }
            }
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)","");
            Thread.sleep(2000);
            currentAssignments.getUpdate().click();
            currentAssignments.updateNotificationYesLink.click();//click on yes
            Thread.sleep(2000);
            ReportUtil.log("Instructor update assignment","Instructor deleted 1 question and reassigned assignment with extend due date","pass");

            new LoginUsingLTI().ltiLogin("109_2");//ReportUtil.login as student2
            new Assignment().submitAssignmentAsStudent(109); //submit assignment
            ReportUtil.log("Submit assignment","Student 2 submitted the assignment","pass");

            new LoginUsingLTI().ltiLogin("109_3");//ReportUtil.login as student3
            new Assignment().submitAssignmentAsStudent(109); //submit assignment
            ReportUtil.log("Submit assignment","Student 3 submitted the assignment","pass");

            new LoginUsingLTI().ltiLogin("109");//ReportUtil.log in as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            Thread.sleep(2000);
            new Assignment().provideGradeToStudentForMultipleQuestions(109);
            driver.navigate().refresh();
            currentAssignments.getViewGrade_link().click();//click on view response
            ReportUtil.log("Verify status","Instructor provided grade ","pass");

            new LoginUsingLTI().ltiLogin("109_1");//ReportUtil.login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            String score6=assignments.getScore().getText();
            Assert.assertEquals(score6,"Score (2.4/6)","Score for assignment over assignment page is not included deleted question");
            ReportUtil.log("Verify student 1 score","Score for assignment over assignment page is included deleted question","pass");

            new LoginUsingLTI().ltiLogin("109_2");//ReportUtil.login as student2
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            String score4=assignments.getScore().getText();
            Assert.assertEquals(score4,"Score (2.4/6)","Score for assignment over assignment page is not included deleted question");
            ReportUtil.log("Verify student 2 score","Score for assignment over assignment page is included deleted question","pass");

            new LoginUsingLTI().ltiLogin("109_3");//ReportUtil.login as student3
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            String score5=assignments.getScore().getText();
            Assert.assertEquals(score5,"Score (2.6/4)","Score for assignment over assignment page is not excluded deleted question");
            ReportUtil.log("Verify student 3 score","Score for assignment over assignment page is excluded deleted question","pass");

        } catch (Exception e) {
            Assert.fail("Exception in test case assignmentWithPolicyHavingGradeReleaseOption3 in class CustomAssignment", e);
        }
    }


    @Test(priority = 15, enabled = true)
    public void  assignmentWithPolicyHavingGradeReleaseOption4() {
        WebDriver driver=Driver.getWebDriver();
        try {

            ReportUtil.log("Description","Assign custom assignment to class with policy 4,delete 1 question,reassign assignment with extend due date","info");

            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "113");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "113");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "113");
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);

            new LoginUsingLTI().ltiLogin("113_1");//create student1
            new LoginUsingLTI().ltiLogin("113_2");//create student2
            new LoginUsingLTI().ltiLogin("113_3");//create student3

            new LoginUsingLTI().ltiLogin("113");//ReportUtil.log in as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("113");//select three question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            ReportUtil.log("Create custom assignment","Created custom assignment with 3 questions","pass");
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description21", "2", null, false, "1", "", "", "", "", "");//policy 4
            new Navigator().NavigateTo("My Question Bank");//navigate to Assignments
            new AssignLesson().Assigncustomeassignemnt(113);//assign assignment
            ReportUtil.log("Assign custom assignment","Assigned custom assignment to class with policy 4","pass");

            new LoginUsingLTI().ltiLogin("113_1");//ReportUtil.login as student1
            new Assignment().submitAssignmentAsStudent(113); //submit assignment
            ReportUtil.log("Submit assignment","Student 1 submitted the assignment","pass");

            new LoginUsingLTI().ltiLogin("113_2");//ReportUtil.login as student2
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//open lesson assignment
            new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false,"incorrect",113);
            assignments.getSubmitAssignment().click();
            new AttemptQuestion().trueFalse(false,"incorrect",113);
            assignments.getSubmitAssignment().click();
            ReportUtil.log("Student 2 in progress","Student 2 attempt 2questions and left assignment","pass");

            new LoginUsingLTI().ltiLogin("113");//ReportUtil.log in as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            currentAssignments.getDeleteIcon().click();//click on delete
            currentAssignments.getYesOnNotificationMessage().click();//click on yes
            Thread.sleep(2000);
            currentAssignments.getReassign_button().click();//click on reassign
            currentAssignments.getExtendDueDate().click();//click on extend due date
            driver.findElement(By.id("extended-due-time")).click();//enter time
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals("12:15 AM")) {
                    time.click();
                    break;
                }
            }
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)","");
            Thread.sleep(2000);
            currentAssignments.getUpdate().click();
            currentAssignments.updateNotificationYesLink.click();//click on yes
            Thread.sleep(2000);
            ReportUtil.log("Instructor update assignment","Instructor deleted 1 question and reassigned assignment with extend due date","pass");
            new LoginUsingLTI().ltiLogin("113_2");//ReportUtil.login as student2
            new Assignment().submitAssignmentAsStudent(113); //submit assignment
            ReportUtil.log("Submit assignment","Student 2 submitted the assignment","pass");

            new LoginUsingLTI().ltiLogin("113_3");//ReportUtil.login as student3
            new Assignment().submitAssignmentAsStudent(113); //submit assignment
            ReportUtil.log("Submit assignment","Student 3 submitted the assignment","pass");

            new LoginUsingLTI().ltiLogin("113");//ReportUtil.log in as instructor
            new Assignment().releaseGrades(113,"Release Grade for All");
            String status1=assignmentResponsesPage.getReviewStatus().getText();
            Assert.assertEquals(status1,"Graded","Graded status is not displaying after released grade for all");
            ReportUtil.log("Verify status","Instructor released grade and assignment status is displayed graded","pass");

            new LoginUsingLTI().ltiLogin("113_2");//ReportUtil.login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            String score6=assignments.getScore().getText();
            Assert.assertEquals(score6,"Score (4/6)","Score for assignment over assignment page is not included deleted question");
            ReportUtil.log("Verify student 1 score","Score for assignment over assignment page is included deleted question","pass");

            new LoginUsingLTI().ltiLogin("113_2");//ReportUtil.login as student2
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            String score4=assignments.getScore().getText();
            Assert.assertEquals(score4,"Score (4/6)","Score for assignment over assignment page is not included deleted question");
            ReportUtil.log("Verify student 2 score","Score for assignment over assignment page is included deleted question","pass");

            new LoginUsingLTI().ltiLogin("113_3");//ReportUtil.login as student3
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            String score5=assignments.getScore().getText();
            Assert.assertEquals(score5,"Score (4/4)","Score for assignment over assignment page is not excluded deleted question");
            ReportUtil.log("Verify student 3 score","Score for assignment over assignment page is  excluded deleted question","pass");


        } catch (Exception e) {
            Assert.fail("Exception in test case assignmentWithPolicyHavingGradeReleaseOption4 in class CustomAssignment", e);
        }
    }

    @Test(priority = 16, enabled = true)
    public void customAssignmentCreatedFromCustomizeThis() {
        WebDriver driver=Driver.getWebDriver();
        try {

            ReportUtil.log("Description","This test case validates edit this link for custom assignment in my question bank page","info");

            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "21");

            new LoginUsingLTI().ltiLogin("21");//ReportUtil.login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            Thread.sleep(1000);
            questionBank.getQuestionBankTitle().click();//click on question bank
            Thread.sleep(2000);
            List<WebElement> a=myQuestionBank.customizeThisLink;
            Thread.sleep(1000);
            for(WebElement b:a){
                if(b.isDisplayed()){
                    b.click();
                }
            }
            new AssignLesson().selectQuestionForCustomAssignment("21");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for late
            Thread.sleep(8000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='resource-title']")));
            Assert.assertEquals(myQuestionBank.editThis.getText(),"Edit This","Edit This link is not available");
            ReportUtil.log("Verify edit this link","Edit this link is displyed for custom assignment in my question bank page","pass");
            Thread.sleep(3000);
            myQuestionBank.closeTab.click();
            List<WebElement> a1=driver.findElements(By.xpath("//span[@title='Edit This']"));
            Thread.sleep(1000);
            for(WebElement b1:a1){
                if(b1.isDisplayed()){
                    b1.click();
                }
            }
            Assert.assertEquals(newAssignment.tabTittle.get(2).getText(), "New_Name", "A New tab is not opened next to Question Bank tab to edit the Custom Assessment ");
            ReportUtil.log("Verify custom assignment tab","A New tab is opened next to Question Bank tab to edit the Custom Assessment","pass");
            Assert.assertEquals(myQuestionBank.getAssignmentNameField().getText(), "New_Name", "The Name of the assessment is not displaying in the Assessment name field");//click on name field
            ReportUtil.log("Verify assignment name","The Name of the assessment is displaying in the Assessment name field","pass");

        } catch (Exception e) {
            Assert.fail("Exception in test case customAssignmentCreatedFromCustomizeThis of class CustomAssignment ", e);
        }
    }


    @Test(priority = 17, enabled = true)
    public void instructorAbleToSearchAndAddMoreQuestions() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Description","This test case validates instructor can search and add more questions","info");

            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "66");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "65");

            new LoginUsingLTI().ltiLogin("66");//ReportUtil.login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("66");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.editThis.click();//click on edit this
            String selectedQuestionCount=newAssignment.tabTittle.get(4).getText().substring(20,21);
            int questionCount=Integer.parseInt(selectedQuestionCount);
            newAssignment.tabTittle.get(3).click();//click on find question tab
            CustomAssert.assertEquals(newAssignment.noQuestionFound.isDisplayed(),true,"Verify question","questions are not displaying by default","questions are displaying by default");
            newAssignment.searchButton.click();//click on search button
            CustomAssert.assertEquals(newAssignment.searchTextArea.isDisplayed(), true,"Verify search text box","search text box is selected", "search text box is not selected");
            newAssignment.browseButton.click();//click on browse button
            newAssignment.searchButton.click();//click on search button
            newAssignment.searchTextArea.sendKeys("animals");
            newAssignment.searchButton.click();//click on search button
            boolean elementFound = false;
            try{
                driver.findElement(By.linkText("No Results found"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            CustomAssert.assertEquals(elementFound,false,"Verify search result","The questions relative to the search value is displaying below","The questions relative to the search value is not displaying below");
            Thread.sleep(1000);
            new AssignLesson().selectQuestionForCustomAssignment("65");//select one question
            Thread.sleep(1000);
            CustomAssert.assertEquals(newAssignment.checkCheckedBox.isDisplayed(),true,"Verify checkbox","checkbox is checked","checkbox is not checked");
            String searchText=newAssignment.searchText.get(0).getText();
            String selectedQuestionCount1=newAssignment.tabTittle.get(4).getText().substring(20,21);
            int questionCount1=Integer.parseInt(selectedQuestionCount1);
            if(questionCount > questionCount1 )
                Assert.fail("the question count of selected Question is not increased");
            newAssignment.tabTittle.get(4).click();//click on selected question sub tab
            CustomAssert.assertEquals(newAssignment.selectedQuestion.isEnabled(),true,"verify selected question","selected question tab is  selected","selected question tab is not selected");
            String secondQuestionBar=newAssignment.secondQuestionBar.get(1).getText().trim();
            Thread.sleep(2000);
            if(!searchText.contains(secondQuestionBar))
                Assert.fail("Newly added questions is not displayed at the end of the previously saved question list");
            CustomAssert.assertEquals(newAssignment.pointCheckBox.get(1).getAttribute("value"),"1","Verify points","Points available for that question is 1 by default","Points available for that question is not 1 by default");
            newAssignment.close_Icon.click();//click on close
            newAssignment.yesOnPopUp.click();//click on yes
            myQuestionBank.editThis.click();//click on edit this
            CustomAssert.assertEquals(newAssignment.tabTittle.get(2).getText(), "65 New Name_IT1818","Verify new tab","A New tab is opened next to Question Bank tab to edit the Custom Assessment ", "A New tab is not opened next to Question Bank tab to edit the Custom Assessment ");
            if(newAssignment.questionBar.size() < 1)
                Assert.fail("Newly added questions is displaying with the saved question list");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSearchAndAddMoreQuestions of class CustomAssignment ", e);
        }
    }

    @Test(priority = 18, enabled = true)
    public void instructorDeleteQuestionsFromAlreadySavedCustomAssessment() {
        WebDriver driver=Driver.getWebDriver();

        try {

            ReportUtil.log("Description","This test case validates instructor can delete question from already saved custom assignment","info");

            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "102");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "102");

            new LoginUsingLTI().ltiLogin("102");//ReportUtil.login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("102");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.editThis.click();//click on edit this
            newAssignment.delete_Icon.click();//click on delete on 1st question
            if(newAssignment.questionBar.size() < 1)
                Assert.fail("question is not deleted from the assessment");
            myQuestionBank.getSaveForLater().click();//click on save for later
            Assert.assertEquals(newAssignment.notification_message.getText(), "Updated Custom Assessment Successfully.", "Saved Custom Assignment Successfully message is not displaying at the top");
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='resource-title']")));
            CustomAssert.assertEquals(myQuestionBank.getAssessment().getText(),"New Name","Verify created custom assignment","created custom assignment is added under my question bank","created custom assignment is not added under my question bank");
            myQuestionBank.getPreviewButton().click();//click on preview
            Thread.sleep(2000);
            Assert.assertEquals(newAssignment.tabTittle.get(2).getText(), "New Name", "A New tab is not opened next to Question Bank");
            if(newAssignment.question_Card.size() < 1)
                Assert.fail(" deleted question is displaying");
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.editThis.click();//click on edit this
            newAssignment.delete_Icon.click();//click on delete on 1st question
            myQuestionBank.getSaveForLater().click();//click on save for later
            CustomAssert.assertEquals(newAssignment.notification_message.getText(), "You have not added any questions for this custom assessment. Please add questions before saving the assessment","Verify notification message","notification message is displaying", "notification message is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorDeleteQuestionsFromAlreadySavedCustomAssessment of class CustomAssignment ", e);
        }
    }

    @Test(priority = 19, enabled = true)
    public void editThisScenario() {
        WebDriver driver=Driver.getWebDriver();
        try {

            ReportUtil.log("Description","This test case validates edit this scenario at destination instructor side","info");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "41");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "41");
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(411));
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);

            new LoginUsingLTI().ltiLogin("411");//ReportUtil.login as  destination instructor
            new LoginUsingLTI().ltiLogin("41");//ReportUtil.login as  source instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("41");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            new Assignment().shareCustomAssignment(shareName);
            myQuestionBank.yesOnShareMessage.click();//click on yes
            new LoginUsingLTI().ltiLogin("41");//ReportUtil.login as  source instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            boolean elementFound = false;
            try {
                driver.findElement(By.xpath("//span[@title='Edit This']"));

                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            CustomAssert.assertEquals(elementFound,true,"Verify edit this","Edit this field is available","Edit this field is not available");
            myQuestionBank.editThis.click();//click on edit this
            newAssignment.delete_Icon.click();//click on delete on 1st question
            returnCurrentDateAndTime();
            myQuestionBank.getSaveForLater().click();//click on save for later
            String myDate=returnCurrentDateAndTime();
            System.out.println("myDate:"+myDate);

            new LoginUsingLTI().ltiLogin("411");//ReportUtil.login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestion bank
            String updatedBy=myQuestionBank.updatedBy.get(0).getText();
            String family=myQuestionBank.updatedBy.get(1).getText();
            String time=myQuestionBank.updatedBy.get(2).getText().trim();
            CustomAssert.assertEquals(updatedBy+family,"Updated byfamily, givenname","Verify updated by","upadatedby and family name is available","upadatedby and family name is not available");
            Assert.assertEquals(time,myDate);
            CustomAssert.assertEquals(questionBank.getCustomizeThis().isDisplayed(), true,"Verify customize this","Customize This link is available", "Customize This link is not available");
            myQuestionBank.getPreviewButton().click();//click on preview
            Thread.sleep(4000);
            System.out.println(newAssignment.question_Card.size());
            if(newAssignment.question_Card.size() != 1)
                Assert.fail(" deleted question is displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case editThisScenario of class CustomAssignment ", e);
        }
    }

    @Test(priority = 20, enabled = true)
    public void destinationInstructorAccessAndUseAssessments() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Description","This test case validates destination Instructor Access And Use Assessments","info");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "46");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "46");
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(461));
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String customassignmentname1 = ReadTestData.readDataByTagName("", "customassignmentname", "47");

            new LoginUsingLTI().ltiLogin("461");//ReportUtil.login as  destination instructor
            new LoginUsingLTI().ltiLogin("46");//ReportUtil.login as  source instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("46");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            new Assignment().shareCustomAssignment(shareName);
            myQuestionBank.yesOnShareMessage.click();//click on yes
            Thread.sleep(1000);
            boolean elementFound = false;
            try {
                driver.findElement(By.xpath("//span[@title='Edit This']"));
                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            CustomAssert.assertEquals(elementFound,true,"Verify edit this","Edit this field is available","Edit this field is not available");
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.editThis.click();//click on edit this
            newAssignment.delete_Icon.click();//click on delete on 1st question
            myQuestionBank.getSaveForLater().click();//click on save for later
            new LoginUsingLTI().ltiLogin("461");//ReportUtil.login as destination instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestion bank
            CustomAssert.assertEquals(myQuestionBank.getAssessment().getText(), "New Name","Verify custom assignment","created custom assignment is added under my question bank","created custom assignment is not added under my question bank");
            CustomAssert.assertEquals(myQuestionBank.getPreviewButton().isDisplayed(), true,"Verify preview","preview link is available", "preview link is not available");
            CustomAssert.assertEquals(myQuestionBank.getAssignThis().isDisplayed(), true,"Verify assign this","Assign This link is available", "Assign This link is not available");
            CustomAssert.assertEquals(myQuestionBank.getDeleteButtonOfOriginal().getAttribute("title"), "Delete This","Verify delete this","Delete This link is available","Delete This link is not available");
            CustomAssert.assertEquals(myQuestionBank.tryItIcon.get(0).isDisplayed(), true,"Verify try it","Try it link is available", " Try it link is not available");
            CustomAssert.assertEquals(questionBank.getCustomizeThis().isDisplayed(), true,"Verify customize this","Customize This link is available", "Customize This link is not available");
            myQuestionBank.getPreviewButton().click();//click on preview
            Thread.sleep(2000);
            CustomAssert.assertEquals(newAssignment.tabTittle.get(2).getText(), "New Name","Verify Custom assignment","A New tab is opened next to Question Bank" ,"A New tab is not opened next to Question Bank");
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            questionBank.getCustomizeThis().click();//click on customize this link
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("47");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname1);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            myQuestionBank.getDeleteButtonOfOriginal().click();
            myQuestionBank.yesLink.click();//click on yes
            new AssignLesson().Assigncustomeassignemnt(47);//assign assignment
            new LoginUsingLTI().ltiLogin("46");//ReportUtil.login as  source instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            boolean elementFound1 = false;
            try {
                driver.findElement(By.xpath("//span[@title='Edit This']"));
                elementFound1 = true;
            } catch (Exception e) {
                //empty catch block
            }
            CustomAssert.assertEquals(elementFound1, false,"Verify edit this","Edit this field is not available","Edit this field is available");
            CustomAssert.assertEquals(questionBank.getCustomizeThis().isDisplayed(), true, "Verify customize this","Customize This link is available","Customize This link is not available");


        } catch (Exception e) {
            Assert.fail("Exception in test case destinationInstructorAccessAndUseAssessments of class CustomAssignment ", e);
        }
    }

    @Test(priority = 21, enabled = true)
    public void deleteThisFunctionality() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Description","This test case validates delete this functionality","info");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "58");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "58");
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(581));

            new LoginUsingLTI().ltiLogin("581");//ReportUtil.login as  destination instructor
            new LoginUsingLTI().ltiLogin("58");//ReportUtil.login as  source inststructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("58");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            new Assignment().shareCustomAssignment(shareName);
            myQuestionBank.yesOnShareMessage.click();//click on yes
            Thread.sleep(1000);
            new LoginUsingLTI().ltiLogin("581");//ReportUtil.login as  destination instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            Assert.assertEquals(myQuestionBank.getDeleteButtonOfOriginal().getAttribute("title"), "Delete This", "Delete This link is not available");
            myQuestionBank.getDeleteButtonOfOriginal().click();//click on delete
            Assert.assertEquals(myQuestionBank.notification.getText(), "Are you sure you want to delete this assessment?   Yes      No", "Delete This link is not available");
            myQuestionBank.noLink.click();//click on no link
            CustomAssert.assertEquals(myQuestionBank.getAssessment().getText(),"New Name","Verify created custom assignment","created custom assignment is not deleted","created custom assignment is not deleted");
            myQuestionBank.getDeleteButtonOfOriginal().click();//click on delete
            myQuestionBank.yesLink.click();//click on yes
            boolean elementFound = false;
            try {
                driver.findElement(By.xpath("//div[@class='resource-title']"));
                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            CustomAssert.assertEquals(elementFound,true,"Verify assignment","Assignment is deleted","Assignment is not deleted");

        } catch (Exception e) {
            Assert.fail("Exception in test case deleteThisFunctionality of class CustomAssignment ", e);
        }
    }



    @Test(priority = 22, enabled = true)
    public void sharedByLabelShouldBeChangedToUpdatedBy() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Description","This test case validates sharedBy Label Should Be Changed To UpdatedBy","info");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "63");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "63");
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(631));
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);

            new LoginUsingLTI().ltiLogin("631");//ReportUtil.login as  destination instructor
            new LoginUsingLTI().ltiLogin("63");//ReportUtil.login as  source instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("63");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question
            Thread.sleep(2000);
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            new Assignment().shareCustomAssignment(shareName);
            myQuestionBank.yesOnShareMessage.click();//click on yes
            Thread.sleep(1000);
            myQuestionBank.closeTab.click();
            myQuestionBank.editThis.click();//click on edit this
            newAssignment.delete_Icon.click();//click on delete on 1st question
            myQuestionBank.getSaveForLater().click();//click on save for
            new LoginUsingLTI().ltiLogin("631");//ReportUtil.login as  destination instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            CustomAssert.assertEquals(myQuestionBank.updatedBy.get(0).getText(),"Updated by","Verify updated by label","Shared by label is changed to updated by label","Shared by label is not changed to updated by label");
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-ReportUtil.logo")));
            CustomAssert.assertEquals(questionBank.wileyLogo.isDisplayed(), true,"Verify new pop up","A new window pop is opened to attempt it","A new window pop is not opened to attempt it");

        } catch (Exception e) {
            Assert.fail("Exception in test case sharedByLabelShouldBeChangedToUpdatedBy of class CustomAssignment ", e);
        }
    }

    @Test(priority = 23, enabled = true)
    public void shareThisImpactsForInstructorsWithMultipleClassesAndSections() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Description","This test case validates share This Impacts For Instructors With Multiple Class Sections","info");

            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "69");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "69");
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(72));
            String shareName1 = ReadTestData.readDataByTagName("", "shareName", Integer.toString(73));

            new LoginUsingLTI().ltiLogin("72");//ReportUtil.login as  source instructor2
            new LoginUsingLTI().ltiLogin("73");//ReportUtil.login as  source instructor3
            new LoginUsingLTI().ltiLogin("69");//ReportUtil.login as  source instructor1 for section A
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("63");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            new Assignment().shareCustomAssignment(shareName);
            myQuestionBank.yesOnShareMessage.click();//click on yes
            Thread.sleep(1000);
            new Assignment().shareCustomAssignment(shareName1);
            Thread.sleep(1000);
            new LoginUsingLTI().ltiLogin("70");//ReportUtil.login as  source instructor1 for section B
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            boolean elementFound = false;
            try {
                driver.findElement(By.xpath("(//span[@title='Delete This'])[1]"));
                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            CustomAssert.assertEquals(elementFound, false,"Verify Delete this ","Delete this link is not displaying after share the assignment","Delete this link is displaying after share the assignment");
            myQuestionBank.shareThis.click();//click on share this
            CustomAssert.assertEquals(myQuestionBank.sharedName.get(0).getText(), "family2, givenname2","Verify already saved student name","Already shared name is available","Already shared name is not available");
            CustomAssert.assertEquals(myQuestionBank.sharedName.get(1).getText(), "family3, givenname3","Verify already saved student name","Already shared name is available", "Already shared name is not available");

            new LoginUsingLTI().ltiLogin("71");//ReportUtil.login as  source instructor1 for section c
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            boolean elementFound1 = false;
            try {
                driver.findElement(By.xpath("(//span[@title='Delete This'])[1]"));
                elementFound1 = true;
            } catch (Exception e) {
                //empty catch block
            }
            CustomAssert.assertEquals(elementFound1, false,"Verify delete this","Delete this link is not displaying after share the assignment","Delete this link is displaying after share the assignment");
            new LoginUsingLTI().ltiLogin("69");//ReportUtil.login as  source instructor1 for section A
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            new AssignLesson().Assigncustomeassignemnt(69);//assign assignment
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            CustomAssert.assertEquals(questionBank.getCustomizeThis().isDisplayed(),true,"Verify customize this","Customize This link is available","Customize This link is not available");

            new LoginUsingLTI().ltiLogin("72");//ReportUtil.login as  source instructor2 for section B
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            boolean elementFound2 = false;
            try {
                driver.findElement(By.xpath("(//span[@title='Delete This'])[1]"));
                elementFound2 = true;
            } catch (Exception e) {
                //empty catch block
            }
            CustomAssert.assertEquals(elementFound2,true,"Verify delete this","Delete this link is displaying after share the assignment","Delete this link is  not displaying after share the assignment");

        } catch (Exception e) {
            Assert.fail("Exception in test case shareThisImpactsForInstructorsWithMultipleClassesAndSections of class CustomAssignment ", e);
        }
    }


    @Test(priority = 24, enabled = true)
    public void tryItPage() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Description","This test case validates functionality of try it page","info");
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "150");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "150");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);

            new LoginUsingLTI().ltiLogin("150");//ReportUtil.login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("150");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            CustomAssert.assertEquals(questionBank.tryItIcon.get(0).isDisplayed(), true,"Verify try it link","Try it icon is available","Try it icon is not available");
            String parentWindow = driver.getWindowHandle();
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-ReportUtil.logo")));
            CustomAssert.assertEquals(questionBank.wileyLogo.isDisplayed(),true,"Verify wiley ReportUtil.logo","Wiley Plus ReportUtil.logo is displaying at left side header","Wiley Plus ReportUtil.logo is not displaying at left side header");
            CustomAssert.assertEquals(questionBank.courseName.isDisplayed(), true,"Verify course name","Course name is not displaying at middle part of header","Course name is not displaying at middle part of header");
            String str=currentAssignments.assignmentNamePreviewPage.getText();
            if(!str.contains("New Name"))
                Assert.fail("Assignment name with short name in the next line below the header is not displaying");
            CustomAssert.assertEquals(currentAssignments.summaryDropDown.isDisplayed(),true,"Verify summary drop down","Summary drop-down on the right corner of assignment name line is present","Summary drop-down on the right corner of assignment name line is not present");
            CustomAssert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(),"Q1:","Verify 1st question","first question of the assignment is displaying by default","first question of the assignment is not displaying by default");
            CustomAssert.assertEquals(currentAssignments.hint_Button.getText(),"Hint","Verify hint button","Hint button is displaying in buttom footer","Hint button is not displaying in buttom footer");
            CustomAssert.assertEquals(currentAssignments.solution_Button.getText(),"Solution","verfy solution button","solution button is displaying in buttom footer","solution button is not displaying in buttom footer");
            CustomAssert.assertEquals(currentAssignments.checkAnswer_Button.getText(),"Check Answer","Verify check answer","check answer button is displaying in buttom footer","check answer button is not displaying in buttom footer");
            CustomAssert.assertEquals(currentAssignments.reportContentError_Link.isDisplayed(),true,"Verify Report Content Error icon","Report Content Error' icon at the right side bottom corner at footer is present.","Report Content Error' icon at the right side bottom corner at footer is not present.");
            CustomAssert.assertEquals(currentAssignments.performanceTab.isDisplayed(),true,"Verify performance tab at the right side","performance tab at the right side of the question part is displaying","performance tab at the right side of the question part is not displaying");
            CustomAssert.assertEquals(currentAssignments.performanceTabImage.isDisplayed(),true,"Verify performance tab at the right side ","performance tab at the right side  of the question part with minimize (-) option is not displaying","performance tab at the right side  of the question part with minimize (-) option is not displaying");
            CustomAssert.assertEquals(currentAssignments.userThumbNail.isDisplayed(),true,"Verify default thumbnail at the right side of header","default thumbnail at the right side of header is displaying","default thumbnail at the right side of header is not displaying");
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            String assignmentName = currentAssignments.assignmentNamePreviewPage.getText();
            driver.switchTo().window(parentWindow);
            currentAssignments.tryIt_Link.get(0).click();//click on try it link
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            String assignmentName1 = currentAssignments.assignmentNamePreviewPage.getText();
            if (!assignmentName.equals(assignmentName1)) {
                Assert.fail("same window is not getting maximized");
            }
            currentAssignments.checkAnswer_Button.click();//click on check answer
            CustomAssert.assertEquals(currentAssignments.footerText.getText(),"Choose the answer","Verify choose the answer notification message","Choose the answer is displaying","Choose the answer is not displaying");
            currentAssignments.answerChoice.get(0).click();//click on option 'A'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(2000);
            CustomAssert.assertEquals(currentAssignments.footerText.getText(),"You got it right.","Verify right notifiaction message","You got it right message is displaying","You got it right message is not displaying");
            currentAssignments.answerChoice.get(0).click();//click on option 'B'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            CustomAssert.assertEquals(currentAssignments.footerText.getText(),"You got it wrong.","Verify wrong notifiaction message","You got it wrong message is displaying","You got it wrong message is not displaying");
            CustomAssert.assertEquals(currentAssignments.next_Button.isDisplayed(),true,"Verify nextbutton","Next button is available","Next button is not available");
            int found= driver.findElements(By.xpath("//div[text()='Previous']")).size();
            if(found!=0) {
                Assert.fail("previous button is displayed");
            }
            currentAssignments.next_Button.click();//click on next
            Thread.sleep(3000);
            CustomAssert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(),"Q2:","Verify question number","It is navigated to the respective question","It is not navigated to the respective question");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q2");
            Thread.sleep(1000);
            currentAssignments.previous_Button.click();//click on previous button
            Thread.sleep(2000);
            CustomAssert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(),"Q1:","Verify question number","It is navigated to the respective question","It is not navigated to the respective question");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q2");
            CustomAssert.assertEquals(currentAssignments.finishButton.isDisplayed(),true,"Verify finish button","Finish button is available","Finish button is not available");
            currentAssignments.finishButton.click();//click on finish button
            driver.switchTo().window(parentWindow);
            String url=driver.getCurrentUrl();
            if(url.contains("tryItAssessmentOrAssignment"))
            {
                Assert.fail("Browser popup is not closed after click on finish button");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case tryItPage in class CustomAssignment", e);
        }
    }

    @Test(priority = 25, enabled = true)
    public void tryItLinkForDifferentQuestionBasedCustomAssignment() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Description","This test case validates try it functionality of 5 custom assignment","info");

            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "175");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "175");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);

            new LoginUsingLTI().ltiLogin("175");//ReportUtil.login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            for (int i = 0; i <= 4; i++) {
                myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
                Thread.sleep(2000);
                new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
                new AssignLesson().selectQuestionForCustomAssignment("175");//select two question
                myQuestionBank.getAssignmentNameField().click();//click on name field
                myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
                myQuestionBank.getSaveForLater().click();//click on save for later
                Thread.sleep(5000);
                myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
                myQuestionBank.closeTab.click();//close custom assignment
                Thread.sleep(4000);
            }
            String parentWindow = driver.getWindowHandle();
            questionBank.tryItIcon.get(0).click();//click on 1st try-it
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            CustomAssert.assertEquals(currentAssignments.assignmentNamePreviewPage.isDisplayed(),true,"Verify 1st browser pop up","1st browser pop up is opened","1st browser pop up is not opened");
            driver.switchTo().window(parentWindow);
            questionBank.tryItIcon.get(1).click();//click on 1st try-it
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            CustomAssert.assertEquals(currentAssignments.assignmentNamePreviewPage.isDisplayed(),true,"Verify 2nd browser pop up","2nd browser pop up is opened","2nd browser pop up is not opened");
            driver.switchTo().window(parentWindow);
            questionBank.tryItIcon.get(2).click();//click on 1st try-it
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            CustomAssert.assertEquals(currentAssignments.assignmentNamePreviewPage.isDisplayed(),true,"Verify 3rd browser pop up","3rd browser pop up is opened","3rd browser pop up is not opened");
            driver.switchTo().window(parentWindow);
            questionBank.tryItIcon.get(3).click();//click on 1st try-it
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            CustomAssert.assertEquals(currentAssignments.assignmentNamePreviewPage.isDisplayed(),true,"Verify 4th browser pop up","4th browser pop up is opened","4th browser pop up is not opened");
            driver.switchTo().window(parentWindow);
            questionBank.tryItIcon.get(4).click();//click on 1st try-it
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            CustomAssert.assertEquals(currentAssignments.assignmentNamePreviewPage.isDisplayed(),true,"Verify 5th browser pop up","5th browser pop up is opened","5th browser pop up is not opened");

        } catch (Exception e) {
            Assert.fail("Exception in test case tryItLinkForDifferentQuestionBasedCustomAssignment in class CustomAssignment", e);

        }
    }


    @Test(priority = 26, enabled = true)
    public void checkAnswerForManualGradedQuestion() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Description","This test case validates check answer for manual grading question","info");
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "167");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "167");

            new Assignment().create(167); //create assignment
            new Assignment().addQuestions(167, "writeboard", "");
            new Assignment().addQuestions(167, "essay", "");
            new Assignment().addQuestions(167, "audio", "");
            new LoginUsingLTI().ltiLogin("167");//ReportUtil.log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("167");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            Thread.sleep(3000);
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }

            new WebDriverWait(driver,240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            currentAssignments.next_Button.click();//click on next button
            Thread.sleep(2000);
            int found= driver.findElements(By.xpath("//div[text()='Check Answer']")).size();
            if(found!=0) {
                Assert.fail("Check Answer option is displayed");
            }
            CustomAssert.assertEquals(currentAssignments.previous_Button.isDisplayed(),true,"Verify previous button","Previous button is available","Previous button is not available");
            currentAssignments.nextButton.click();//click on next button
            Thread.sleep(2000);
            int found1= driver.findElements(By.xpath("//div[text()='Check Answer']")).size();if(found1!=0) {
                Assert.fail("Check Answer option is displayed");

            }
            currentAssignments.nextButton.click();//click on next button
            Thread.sleep(2000);
            int found2= driver.findElements(By.xpath("//div[text()='Check Answer']")).size();
            if(found2!=0) {
                Assert.fail("Check Answer option is displayed");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToCheckAnswerForManualGradedQuestion in class CustomAssignment", e);
        }
    }

    @Test(priority = 27,enabled = true)
    public void checkAnswerPartiallyCorrect() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Description","This Test case validates partially corrrect notification message","info");

            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "184");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "184");

            new LoginUsingLTI().ltiLogin("184");//ReportUtil.log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("Multiple Selection");
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("184");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            Thread.sleep(3000);
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-ReportUtil.logo")));
            for(int i=0;i<=currentAssignments.answerOption_MultipleChoice.size()-1;i++)
            {
                currentAssignments.answerOption_MultipleChoice.get(i).click();
                currentAssignments.checkAnswer_Button.click();//click on check answer
                new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("footer-notification-text")));
                if(currentAssignments.footerText.getText().equals("You got it partially correct."))
                {
                    break;
                }
            }
            CustomAssert.assertEquals(currentAssignments.footerText.getText(), "You got it partially correct.", "Verify partially correct notification messsage","You got it partially correct is displaying","You got it partially correct is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case checkAnswerPartiallyCorrect in class CustomAssignment", e);
        }
    }

    @Test(priority = 28,enabled = true)
    public void instructorShouldBeAbleToViewQuestion() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Description","This test case validates easy,medium.difficulty label in try it page","info");
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "200");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "200");

            new LoginUsingLTI().ltiLogin("200");//ReportUtil.log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.allDifficultyLevelArrow.click();
            Thread.sleep(2000);
            myQuestionBank.easy_Link.click();
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("200");//select two question
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.allDifficultyLevelArrow.click();
            myQuestionBank.medium_Link.click();
            Thread.sleep(5000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", myQuestionBank.getGo_Button());//click on go
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("201");//select two question
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.allDifficultyLevelArrow.click();
            myQuestionBank.hard_Link.click();
            Thread.sleep(5000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", myQuestionBank.getGo_Button());//click on go
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("202");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            Thread.sleep(3000);
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-ReportUtil.logo")));
            CustomAssert.assertEquals(currentAssignments.totalPoints.isDisplayed(),true,"Verify points","Total points in performance tab at the righ side panel is displaying","Total points in performance tab at the righ side panel not displaying");
            CustomAssert.assertEquals(currentAssignments.performanceInQuestion.isDisplayed(),true,"Verify performance","Perfomance in last 10 question is displaying","Perfomance in last 10 question not displaying");
            CustomAssert.assertEquals(currentAssignments.performanceGraph.isDisplayed(),true,"Verify graph","blank graph below points available option as of CMS preview is displaying","blank graph below points available option as of CMS preview is not displaying");
            CustomAssert.assertEquals(currentAssignments.difficultyBar.isDisplayed(),true,"Verify difficulty bar","difficulty bar is available","difficulty bar is not available");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q2");//select question 2
            Thread.sleep(3000);
            String str=driver.findElement(By.className("cms-question-preview-sidebar-title-sectn")).getText();
            CustomAssert.assertEquals(str,"Points Available : 5","Verify points","5 points for question no 2 is available","5 points for question no 2 is not available");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q1");//select question 2
            Thread.sleep(3000);
            String low=currentAssignments.difficultyLevelBar.getAttribute("style");
            if(!low.contains("10%"))
                Assert.fail("Question with difficulty level low is not displaying");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q2");//select question 2
            Thread.sleep(3000);
            String medium=currentAssignments.difficultyLevelBar.getAttribute("style");
            if(!medium.contains("20%"))
                Assert.fail("Question with difficulty level medium is not displaying");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q3");//select question 2
            Thread.sleep(3000);
            String high=currentAssignments.difficultyLevelBar.getAttribute("style");
            if(!high.contains("30%"))
                Assert.fail("Question with difficulty level high is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToViewQuestion in class CustomAssignment", e);
        }
    }

    @Test(priority = 29,enabled = true)
    public void manualGradeQuestionCheckBox() {
        WebDriver driver=Driver.getWebDriver();
        try {
            ReportUtil.log("Description","This test case validates manual grade check box in selected question tab","info");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "300");
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);

            new LoginUsingLTI().ltiLogin("300");//ReportUtil.log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("300");//select two question
            newAssignment.tabTittle.get(4).click();
            CustomAssert.assertEquals(newAssignment.manualGradeDisableCheckBox.isDisplayed(),true,"Verify manual checked check box","Manual grade check box is disabled","Manual grade check box is not disabled");

        } catch (Exception e) {
            Assert.fail("Exception in test case manualGradeQuestionCheckBox in class CustomAssignment", e);
        }
    }

    public void selectQuestion(String questionNo)
    {
        WebDriver driver=Driver.getWebDriver();
        driver.findElement(By.xpath("//div[text()='"+questionNo+"']")).click();

    }

    public String returnCurrentDateAndTime() {
        String time=null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy, hh:mm a");
            Calendar now = Calendar.getInstance();
            //add minutes to current date using Calendar.add method
            now.add(Calendar.SECOND, -1);
            String calenderFormat=formatter.format(now.getTime());
            System.out.println("calenderFormat:" + calenderFormat);
            time=calenderFormat;
          /*  //get current date time with Date()
            Date date = new Date();
            System.out.println(formatter.format(date));
            time=formatter.format(date);
*/
            //get current date time with Calendar()
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }


}
