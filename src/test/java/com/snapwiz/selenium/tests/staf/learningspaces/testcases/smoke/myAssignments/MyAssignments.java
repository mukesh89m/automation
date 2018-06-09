package com.snapwiz.selenium.tests.staf.learningspaces.testcases.smoke.myAssignments;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Discussion;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Resources.MyResources;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.*;
import com.snapwiz.selenium.tests.staf.learningspaces.testcases.regression.assignment.CurrentAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextSend;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Dharaneesh T Gowda on 10-06-2016.
 */
public class MyAssignments extends Driver {

    @Test(priority = 1,enabled = true)
    public void assigningGradableDWFromLessonPage() {
        try {
            //ROW NO - 15
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate Save for Later feature", "Check all the details of save for later feature", "info");
            String dataIndex = "15";

            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            QuestionPage questionPage = PageFactory.initElements(driver, QuestionPage.class);
            MyAssignment myAssignment = PageFactory.initElements(driver, MyAssignment.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);


            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);


            new LoginUsingLTI().ltiLogin(dataIndex);//login as an instructor
            Thread.sleep(9000);
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(15);

            new Navigator().NavigateTo("eTextbook");
            Thread.sleep(9000);
            new TopicOpen().lessonOpen(9, 2);//open 1st chapter


            //Row No - 16 : 4. Assign the DW as gradable assignment to the class section/Group/Individual
            //Expected - # Instructor should be able to assign DW assignment
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(Integer.parseInt(dataIndex));


            //Row No - 17 : 5. Select Main navigator>>Assignments>>My Assignments option
            //Expected - # DW assignment entry should be displayed in My Assignments page
            new Navigator().NavigateTo("My Assignments");
            if (!myAssignment.dwSection.isDisplayed()) {
                CustomAssert.fail("Validating DW assignment entry", "DW assignment entry is displayed");
            }

            //Row No - 18 : 6. Verify My Assignments page
            //Expected - 1 : # Icon for DW assignment should be displayed in row 1
            if (!myAssignment.dwDescriptionIcon.isDisplayed()) {
                CustomAssert.fail("Validating DW icon", "DW icon is not displayed");
            }


            //Expected - 2 :# Assignment name should be displayed in row1.
            CustomAssert.assertEquals(myAssignment.dwAssignmentName.isDisplayed(), true, "Validating Assignment Name", "Assignment name is displayed in row 1", "Assignment name is not displayed in row 1");


            //Expected - 3 : # DW assignment description should be displayed in row 2


            //Expected - 4 : # Icon for the DW assignment next to description should be as in Current Assignment page
            if (!myAssignment.dwIcon.isDisplayed()) {
                CustomAssert.fail("Validating Icon for the DW assignment next to description", "Icon for the DW assignment next to description is not as in the Current Assignment page");
            }


            //Expected - 5: # "D1" should be displayed before the DW description
            if(!driver.findElement(By.partialLinkText("D1 - ")).isDisplayed()){
                CustomAssert.fail("Validate 'D1' link ","\"D1\" is not displayed before the DW description");
            }


            //Expected : # Only "Assign this" option should be displayed below the assignment name in action category
            if(!myAssignment.assignThis.isDisplayed()){
               CustomAssert.fail("Validate \"Assign this\" option","\"Assign this\" option is not displayed below the assignment name in action category");
            }


            //Row No - 9. Click on DW assignment
            //Expected - # Instructor should be able to click on the assignment name
            driver.findElement(By.partialLinkText("D1 - ")).click();
            String dwText = "What things could possibly go wrong during all of the different stages of the cell cycle and what would be the likely outcomes of those errors?";
            CustomAssert.assertEquals(newAssignment.discussionText.getText().trim(),dwText,"Validate that instructor be able to click assignment name","Instructor is able to click on the assignment name","Instructor is not able to click on the assignment name");



             /*VERIFYING ASSIGNMENT ENTRY ON ASSIGNING SECOND TIME*/

            /*"Row No - 33 - 10. Navigate to My Assignments page from main navigator
            11. Click on 'Assign this' option"*/

            /*Expected - # Instructor should be able to click on "Assign this" option
            # "Assign this" pop up should be displayed*/
            new Navigator().NavigateTo("My Assignments");
            //myAssignment.assignThis.click();
            Thread.sleep(5000);
            List<WebElement> assignThisElementsList = driver.findElements(By.cssSelector("span[class='assign-this']"));
            for(int a=0;a<assignThisElementsList.size();a++){
                if(assignThisElementsList.get(a).isDisplayed()){
                    assignThisElementsList.get(a).click();
                    break;
                }
            }
            Assert.assertTrue(myQuestionBank.shareWithPopUp.isDisplayed(), "Instructor is not able to click on \"Assign this\" option");// Share with popUp


            //Row No - 36 : 13. Fill all the details in the pop up and assign the DW to the class section
            //Expected - # Instructor should be able to assign the DW assignment
            new Assignment().assignThisFormFillOtherDetails(dataIndex);
            Thread.sleep(9000);
            String dwtext = "D1 - What things could possibly go wrong during all of the different stages of the cell cycle and what would be the likely outcomes of those errors?";
            System.out.println("111 : " + driver.findElements(By.cssSelector("span[title = '"+dwtext+"']")).size());
            if(driver.findElements(By.cssSelector("span[title = '"+dwtext+"']")).size()!=2){
                CustomAssert.fail("Validate assigning DW Assignment","Instructor is not able to assign the DW assignment");
            }


            //Row No - 37 : 14. Verify My Assignments page
            //Expected - # Already created entry should be brought into Top in My Assignments page with the existing card details
            //Expected - # Second/Duplicate entry of the assignment should not be created in My Assignments page
            new Navigator().NavigateTo("My Assignments");
            Thread.sleep(9000);
            System.out.println("222 : " + driver.findElements(By.partialLinkText("D1 - ")).size());
            if(driver.findElements(By.partialLinkText("D1 - ")).size()!=2){
                CustomAssert.fail("Validate Already created entry","Already created entry is not brought into Top in My Assignments page with the existing card details");
            }


            /*Verifying assignment entry on assigning second time with different assignment name*/

            //39 - 15. Click on 'Assign this' option for the DW assignment again
            /*# Already created entry should be brought into Top in My Assignments page with the existing card details
            # Second/Duplicate entry of the assignment should not be created in My Assignments page*/
            new LoginUsingLTI().ltiLogin(dataIndex);//login as an instructor
            new Navigator().NavigateTo("My Assignments");
            Thread.sleep(5000);
            assignThisElementsList = driver.findElements(By.cssSelector("span[class='assign-this']"));
            for(int a=0;a<assignThisElementsList.size();a++){
                if(assignThisElementsList.get(a).isDisplayed()){
                    assignThisElementsList.get(a).click();
                    break;
                }
            }
            Thread.sleep(5000);
            assignmentTab.editAssignmentName.click();
            driver.switchTo().activeElement().sendKeys("ABCDEF");
            new Assignment().assignThisFormFillOtherDetails(dataIndex);
            new Navigator().NavigateTo("My Assignments");
            System.out.println("222 : " + driver.findElements(By.partialLinkText("D1 - ")).size());
            if(driver.findElements(By.partialLinkText("D1 - ")).size()!=2){
                CustomAssert.fail("Validate Already created entry","Already created entry is not brought into Top in My Assignments page with the existing card details");
            }
            /*44 - "21. Click on All type filter
            22. Select Learning Activities from the drop down"*/

            /*Expected - # Instructor should be able to select Learning Activities filter from the drop down
            # DW question assignment should not be displayed in  My Assignments page*/
            new Navigator().NavigateTo("My Assignments");
            driver.findElement(By.linkText("All Type")).click();
            driver.findElement(By.linkText("Learning Activities")).click();

            newAssignment.searchAssignableItemsTextField.click();
            Thread.sleep(3000);
            if(!driver.findElement(By.cssSelector("a[title = '"+dwtext+"']")).isDisplayed()){
                CustomAssert.fail("Validate Question assignment","Question assignment is not displayed");
            }

            //Row No - 46
            new Navigator().NavigateTo("My Assignments");
            driver.findElement(By.linkText("All Type")).click();
            driver.findElement(By.linkText("Question Banks")).click();

            newAssignment.searchAssignableItemsTextField.click();
            Thread.sleep(3000);
            if(driver.findElements(By.cssSelector("div[title = '"+dwtext+"']")).size()!=0){
                CustomAssert.fail("Validate DW Question assignment","DW Question assignment is not displayed");
            }


            /*Row No - 49 : "25. Login as student
            26. Navigate to Assignments page
            27. Click on the DW assignment name"*/
            //Expected - # Student should be able to add perspective to the assignment
            dataIndex = "49";
            new LoginUsingLTI().ltiLogin(dataIndex);
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            currentAssignments.getLessonAssignment().click(); //click on DW
            String perspective = new RandomString().randomstring(2);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective); //add perspective to DW assignment
            CustomAssert.assertEquals(currentAssignments.getDwComment().getText().trim(),perspective,"Validate perspective","Student is able to add perspective to the assignment","Student is not able to add perspective to the assignment");

            //Row No - 52 : 29. Add like to the assignment
            //Expected - # Student should be able to add like to the assignment
            driver.findElement(By.partialLinkText("Like")).click();
            if(!driver.findElement(By.partialLinkText("Unlike")).isDisplayed()){
                CustomAssert.fail("Validate like link","Student is not able to add like to the assignment");
            }


            /*Un-assign flow*/

            /*Expected - "53. Login as Instructor
            31. Navigate to current Assignment page
            32. Un-assign the assignment"*/
            //Expected - # Instructor should be able to un-assign the assignment
            //Expected - # Entry of the assignment should be removed from Current Assignment page
            dataIndex = "15";
            new LoginUsingLTI().ltiLogin(dataIndex);//login as an instructor
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getUnAssignButtonOfVersionAssignment());//Un-assign assignment
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getYesOnUnAssignPopUp());
            Thread.sleep(5000);

            if(driver.findElements(By.linkText(dwtext)).size()!=3){
                CustomAssert.fail("Validate un-assign the assignment","Instructor is not able to un-assign the assignment");
            }


            /*Row No - 55 : "34. Select Main navigator>>Assignments>>My Assignments option
            35. Verify the My Assignments page"*/
            //Expected - # Entry of the assignment should not be removed from My Assignments page
            new Navigator().NavigateTo("My Assignments");
            if(driver.findElements(By.linkText(dwtext)).size()!=2){
                CustomAssert.fail("Validate Entry of the assignment","Entry of the assignment is not removed from My Assignments page");
            }

            } catch (Exception e) {
            Assert.fail("Exception in the test method 'assigningGradableDWFromLessonPage' in the class 'DiscussionBasedAssignment'", e);
        }
    }



    @Test(priority = 2,enabled = true)
    public void assigningGradableDWFromMainNavigator() {
        try {
            //ROW NO - 57
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate Save for Later feature", "Check all the details of save for later feature", "info");
            String dataIndex = "57";

            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            QuestionPage questionPage = PageFactory.initElements(driver, QuestionPage.class);
            MyAssignment myAssignment = PageFactory.initElements(driver, MyAssignment.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);


            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String description = ReadTestData.readDataByTagName("", "description", dataIndex);


            /*Assigning gradable DW from main navigator*/

            //Row No - 57 & 58
            new LoginUsingLTI().ltiLogin(dataIndex);//login as an instructor
            new Navigator().NavigateTo("New Assignment");
            currentAssignments.discussionAssignmentButton.click();
            myQuestionBank.getAssignmentNameField().click();
            driver.switchTo().activeElement().sendKeys(description);
            newAssignment.questionContent.click();
            Thread.sleep(5000);
            driver.switchTo().activeElement().sendKeys("ABCD"+description);
            newAssignment.assignNowButton.click();
            Thread.sleep(5000);
            new Assignment().assignThisFormFillOtherDetails(dataIndex);

            new Navigator().NavigateTo("Class Assignments");
            Thread.sleep(5000);
            if(driver.findElements(By.cssSelector("span[title = 'D1 - ABCD"+description+"']")).size()==0){
                CustomAssert.fail("Validate DW assignment","Instructor is not able to assign DW assignment");
            }

            //Row No - 60 : 6. Verify My Assignments page
            //Expected - 1 : # Icon for DW assignment should be displayed in row 1
            System.out.println("5555: " + currentAssignments.imageIcon.getAttribute("src"));
            if (!currentAssignments.imageIcon.getAttribute("src").contains("/webresources/images/al/home-work-icon.png")) {
                CustomAssert.fail("Validating DW icon", "DW icon is not displayed");
            }

            //Expected - 5: # "D1" should be displayed before the DW description
            //Expected - 2 :# Assignment name should be displayed in row1.
            CustomAssert.assertEquals(driver.findElement(By.cssSelector("span[title = 'D1 - ABCD"+description+"']")).isDisplayed(), true, "Validating Assignment Name", "Assignment name is displayed in row 1", "Assignment name is not displayed in row 1");


            //Expected - 3 : # DW assignment description should be displayed in row 2


            //Expected - 4 : # Icon for the DW assignment next to description should be as in Current Assignment page
            if (!questionPage.discussionAssignmentDescriptionIcon.isDisplayed()) {
                CustomAssert.fail("Validating Icon for the DW assignment next to description", "Icon for the DW assignment next to description is not as in the Current Assignment page");
            }



            /*Verifying assignment entry on assigning second time*/

            /*Row No - 75 : "10. Navigate to My Assignments page from main navigator
            11. Click on 'Assign this' option"*/
            //Expected - # Instructor should be able to click on "Assign this" option

            new Navigator().NavigateTo("New Assignment");
            currentAssignments.discussionAssignmentButton.click();
            myQuestionBank.getAssignmentNameField().click();
            driver.switchTo().activeElement().sendKeys(description);
            newAssignment.questionContent.click();
            Thread.sleep(5000);
            driver.switchTo().activeElement().sendKeys("ABCD"+description);
            newAssignment.assignNowButton.click();
            Thread.sleep(5000);
            new Assignment().assignThisFormFillOtherDetails(dataIndex);

            /*Row No - 75 : "10. Navigate to My Assignments page from main navigator
            11. Click on 'Assign this' option"*/
            //Expected - # Instructor should be able to click on "Assign this" option
            //Expected - # "Assign this" pop up should be displayed
            new Navigator().NavigateTo("My Assignments");
            myQuestionBank.getAssignThis().click();
            WebDriverUtil.clickOnElementUsingJavascript(myQuestionBank.getAssignThis());
            if(!myQuestionBank.shareWithPopUp.isDisplayed()){
                CustomAssert.fail("Validating Assign This popup","Assign This popup is not displayed.");
            }

            //Row No - 78 : 21. Fill all the necessary details and click on Assign button.
            //Expected -1:  # # Instructor should be able to assign the DW assignment.
            Thread.sleep(5000);
            new Assignment().assignThisFormFillOtherDetails("" + dataIndex);
            if(!driver.findElement(By.cssSelector("span[title = 'Class Assignments']")).isDisplayed()){
                CustomAssert.fail("Validate Class assignment page","Class assignment page is not opened.");
            }


            //ROw No - 79  ;14. Verify My Assignments page
            //Expected - # Already created entry should be brought into Top in My Assignments page with the existing card details
            System.out.println("12345: " + driver.findElements(By.partialLinkText("D1")).size());
            if(driver.findElements(By.cssSelector("span[title = 'D1 - ABCD"+description+"']")).size()!=3){
                CustomAssert.fail("Validate Already created entry","Already created entry is not brought into Top in My Assignments page with the existing card details");
            }



            /*Verifying assignment entry on assigning second time with different assignment name*/

            new LoginUsingLTI().ltiLogin(dataIndex);//login as an instructor
            new Navigator().NavigateTo("My Assignments");
            Thread.sleep(5000);
            List<WebElement> assignThisElementsList = driver.findElements(By.cssSelector("span[class='assign-this']"));
            for(int a=0;a<assignThisElementsList.size();a++){
                if(assignThisElementsList.get(a).isDisplayed()){
                    assignThisElementsList.get(a).click();
                    break;
                }
            }
            Thread.sleep(5000);
            assignmentTab.editAssignmentName.click();
            driver.switchTo().activeElement().sendKeys("ABCDEFGHIJKL");
            new Assignment().assignThisFormFillOtherDetails(dataIndex);
            if(driver.findElements(By.cssSelector("span[title = 'D1 - ABCD"+description+"']")).size()!=4){
                CustomAssert.fail("Validate Already created entry","Already created entry is not brought into Top in My Assignments page with the existing card details");
            }



            /*Row No - 86 : 44 - "21. Click on All type filter
            22. Select Learning Activities from the drop down"*/

            /*Expected - # Instructor should be able to select Learning Activities filter from the drop down
            # DW question assignment should not be displayed in  My Assignments page*/
            new Navigator().NavigateTo("My Assignments");
            driver.findElement(By.linkText("All Type")).click();
            driver.findElement(By.linkText("Learning Activities")).click();

            newAssignment.searchAssignableItemsTextField.click();
            driver.switchTo().activeElement().sendKeys("D1 - ABCD"+description+"");
            Thread.sleep(3000);
            if(!currentAssignments.assignmentname.isDisplayed()){
                CustomAssert.fail("Validate Question assignment","Question assignment is not displayed");
            }

            //Row No - 46
            new Navigator().NavigateTo("My Assignments");
            driver.findElement(By.linkText("All Type")).click();
            driver.findElement(By.linkText("Question Banks")).click();

            newAssignment.searchAssignableItemsTextField.click();
            Thread.sleep(3000);
            if(driver.findElements(By.xpath("//div[@class='thumbnail-and-description-wrapper']//span[2]")).size()!=0){
                CustomAssert.fail("Validate DW Question assignment","DW Question assignment is not displayed");
            }


            /*Row No - 90 : "25. Login as student
            26. Navigate to Assignments page
            27. Click on the DW assignment name"*/

            String dwtext = "";
            dataIndex = "90";
            new LoginUsingLTI().ltiLogin(dataIndex);
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            currentAssignments.getLessonAssignment().click(); //click on DW
            Thread.sleep(9000);
            String perspective = new RandomString().randomstring(2);
            //driver.findElement(By.partialLinkText("Perspectives")).click();
            lessonPage.your_perspective.click();
            driver.switchTo().activeElement().sendKeys(perspective);
            driver.findElement(By.className("post-perspective")).click();
            Thread.sleep(5000);
            //new DiscussionWidget().addPerspectiveForDWAssignment(perspective); //add perspective to DW assignment
            CustomAssert.assertEquals(currentAssignments.getDwComment().getText().trim(),perspective,"Validate perspective","Student is able to add perspective to the assignment","Student is not able to add perspective to the assignment");

            //Row No - 52 : 29. Add like to the assignment
            //Expected - # Student should be able to add like to the assignment
            driver.findElement(By.partialLinkText("Like")).click();
            if(!driver.findElement(By.partialLinkText("Unlike")).isDisplayed()){
                CustomAssert.fail("Validate like link","Student is not able to add like to the assignment");
            }


            /*Un-assign flow*/

            /*Expected - "53. Login as Instructor
            31. Navigate to current Assignment page
            32. Un-assign the assignment"*/
            //Expected - # Instructor should be able to un-assign the assignment
            //Expected - # Entry of the assignment should be removed from Current Assignment page
            dataIndex = "57";
            //String dwtext = "abcdefgh";
            new LoginUsingLTI().ltiLogin(dataIndex);//login as an instructor
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getUnAssignButtonOfVersionAssignment());//Un-assign assignment
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getYesOnUnAssignPopUp());
            Thread.sleep(5000);

            if(driver.findElements(By.cssSelector("span[title = 'D1 - ABCD"+description+"']")).size()!=3){
                CustomAssert.fail("Validate un-assign the assignment","Instructor is not able to un-assign the assignment");
            }


            /*Row No - 55 : "34. Select Main navigator>>Assignments>>My Assignments option
            35. Verify the My Assignments page"*/
            //Expected - # Entry of the assignment should not be removed from My Assignments page
            new Navigator().NavigateTo("My Assignments");
            if(driver.findElements(By.xpath("//div[@class='thumbnail-and-description-wrapper']//span[2]")).size()!=2){
                CustomAssert.fail("Validate Entry of the assignment","Entry of the assignment is not removed from My Assignments page");
            }

        } catch (Exception e) {
            Assert.fail("Exception in the test method 'assigningGradableDWFromMainNavigator' in the class 'DiscussionBasedAssignment'", e);
        }
    }





    @Test(priority = 3,enabled = true)
    public void assigningNonGradableDWFromLessonPage() {
        try {
            //ROW NO - 98
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate assigning non gradable DW", "Validate assigning non gradable DW from Lesson Page", "info");
            String dataIndex = "98";

            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            QuestionPage questionPage = PageFactory.initElements(driver, QuestionPage.class);
            MyAssignment myAssignment = PageFactory.initElements(driver, MyAssignment.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);


            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            /*Row No - 98 : "1. Login as instructor/Mentor
            2. Navigate to e-textbook from main navigator
            3. Select the chapter and lesson having DW in its lesson content"*/

            new LoginUsingLTI().ltiLogin(dataIndex);//login as an instructor
            Thread.sleep(9000);
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(Integer.parseInt(dataIndex));

            new Navigator().NavigateTo("eTextbook");
            Thread.sleep(9000);
            new TopicOpen().lessonOpen(9, 2);//open 1st chapter


            //Row No - 99 : 4. Assign the DW as non gradable assignment to the class section/Group/Individual
            //Expected - # Instructor should be able to assign DW assignment
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(Integer.parseInt(dataIndex));


            //Row No - 100 : 5. Select Main navigator>>Assignments>>My Assignments option
            //Expected - # DW assignment entry should be displayed in My Assignments page
            new Navigator().NavigateTo("My Assignments");
            if (!myAssignment.dwSection.isDisplayed()) {
                CustomAssert.fail("Validating DW assignment entry", "DW assignment entry is displayed");
            }

            //Row 101 - 18 : 6. Verify My Assignments page
            //Expected - 1 : # Icon for DW assignment should be displayed in row 1
            if (!myAssignment.dwDescriptionIcon.isDisplayed()) {
                CustomAssert.fail("Validating DW icon", "DW icon is not displayed");
            }


            //Expected - 2 :# Assignment name should be displayed in row1.
            CustomAssert.assertEquals(myAssignment.dwAssignmentName.isDisplayed(), true, "Validating Assignment Name", "Assignment name is displayed in row 1", "Assignment name is not displayed in row 1");


            //Expected - 3 : # DW assignment description should be displayed in row 2


            //Expected - 4 : # Icon for the DW assignment next to description should be as in Current Assignment page
            if (!myAssignment.dwIcon.isDisplayed()) {
                CustomAssert.fail("Validating Icon for the DW assignment next to description", "Icon for the DW assignment next to description is not as in the Current Assignment page");
            }


            //Expected - 5: # "D1" should be displayed before the DW description
            if(!driver.findElement(By.partialLinkText("D1 - ")).isDisplayed()){
                CustomAssert.fail("Validate 'D1' link ","\"D1\" is not displayed before the DW description");
            }


            //Expected : # Only "Assign this" option should be displayed below the assignment name in action category
            if(!myAssignment.assignThis.isDisplayed()){
                CustomAssert.fail("Validate \"Assign this\" option","\"Assign this\" option is not displayed below the assignment name in action category");
            }


            //Row No - 9. Click on DW assignment
            //Expected - # Instructor should be able to click on the assignment name
            driver.findElement(By.partialLinkText("D1 - ")).click();
            String dwText = "What things could possibly go wrong during all of the different stages of the cell cycle and what would be the likely outcomes of those errors?";
            CustomAssert.assertEquals(newAssignment.discussionText.getText().trim(),dwText,"Validate that instructor be able to click assignment name","Instructor is able to click on the assignment name","Instructor is not able to click on the assignment name");



             /*VERIFYING ASSIGNMENT ENTRY ON ASSIGNING SECOND TIME*/

            /*"Row No - 116 - 10. Navigate to My Assignments page from main navigator
            11. Click on 'Assign this' option"*/

            /*Expected - # Instructor should be able to click on "Assign this" option
            # "Assign this" pop up should be displayed*/
            new Navigator().NavigateTo("My Assignments");
            Thread.sleep(5000);
            List<WebElement> assignThisElementsList = driver.findElements(By.cssSelector("span[class='assign-this']"));
            for(int a=0;a<assignThisElementsList.size();a++){
                if(assignThisElementsList.get(a).isDisplayed()){
                    assignThisElementsList.get(a).click();
                    break;
                }
            }
            Assert.assertTrue(myQuestionBank.shareWithPopUp.isDisplayed(), "Instructor is not able to click on \"Assign this\" option");// Share with popUp


            //Row No - 119 : 13. Fill all the details in the pop up and assign the DW to the class section
            //Expected - # Instructor should be able to assign the DW assignment
            new Assignment().assignThisFormFillOtherDetails(dataIndex);
            Thread.sleep(9000);
            String dwtext = "D1 - What things could possibly go wrong during all of the different stages of the cell cycle and what would be the likely outcomes of those errors?";
            System.out.println("111 : " + driver.findElements(By.cssSelector("span[title = '"+dwtext+"']")).size());
            if(driver.findElements(By.cssSelector("span[title = '"+dwtext+"']")).size()!=2){
                CustomAssert.fail("Validate assigning DW Assignment","Instructor is not able to assign the DW assignment");
            }


            //Row No - 120 : 14. Verify My Assignments page
            //Expected - # Already created entry should be brought into Top in My Assignments page with the existing card details
            //Expected - # Second/Duplicate entry of the assignment should not be created in My Assignments page
            new Navigator().NavigateTo("My Assignments");
            Thread.sleep(9000);
            System.out.println("222 : " + driver.findElements(By.partialLinkText("D1 - ")).size());
            if(driver.findElements(By.partialLinkText("D1 - ")).size()!=2){
                CustomAssert.fail("Validate Already created entry","Already created entry is not brought into Top in My Assignments page with the existing card details");
            }


            /*Verifying assignment entry on assigning second time with different assignment name*/

            //122 - 15. Click on 'Assign this' option for the DW assignment again
            /*# Already created entry should be brought into Top in My Assignments page with the existing card details
            # Second/Duplicate entry of the assignment should not be created in My Assignments page*/
            new LoginUsingLTI().ltiLogin(dataIndex);//login as an instructor
            new Navigator().NavigateTo("My Assignments");
            Thread.sleep(5000);
            assignThisElementsList = driver.findElements(By.cssSelector("span[class='assign-this']"));
            for(int a=0;a<assignThisElementsList.size();a++){
                if(assignThisElementsList.get(a).isDisplayed()){
                    assignThisElementsList.get(a).click();
                    break;
                }
            }
            Thread.sleep(5000);
            assignmentTab.editAssignmentName.click();
            driver.switchTo().activeElement().sendKeys("ABCDEF");
            new Assignment().assignThisFormFillOtherDetails(dataIndex);
            new Navigator().NavigateTo("My Assignments");
            System.out.println("222 : " + driver.findElements(By.partialLinkText("D1 - ")).size());
            if(driver.findElements(By.partialLinkText("D1 - ")).size()!=2){
                CustomAssert.fail("Validate Already created entry","Already created entry is not brought into Top in My Assignments page with the existing card details");
            }


            /*127 - "21. Click on All type filter
            22. Select Learning Activities from the drop down"*/

            /*Expected - # Instructor should be able to select Learning Activities filter from the drop down
            # DW question assignment should not be displayed in  My Assignments page*/
            new Navigator().NavigateTo("My Assignments");
            driver.findElement(By.linkText("All Type")).click();
            driver.findElement(By.linkText("Learning Activities")).click();

            newAssignment.searchAssignableItemsTextField.click();
            Thread.sleep(3000);
            if(!driver.findElement(By.cssSelector("a[title = '"+dwtext+"']")).isDisplayed()){
                CustomAssert.fail("Validate Question assignment","Question assignment is not displayed");
            }

            //Row No - 129
            new Navigator().NavigateTo("My Assignments");
            driver.findElement(By.linkText("All Type")).click();
            driver.findElement(By.linkText("Question Banks")).click();

            newAssignment.searchAssignableItemsTextField.click();
            Thread.sleep(3000);
            if(driver.findElements(By.cssSelector("div[title = '"+dwtext+"']")).size()!=0){
                CustomAssert.fail("Validate DW Question assignment","DW Question assignment is not displayed");
            }


            /*Row No - 131 : "25. Login as student
            26. Navigate to Assignments page
            27. Click on the DW assignment name"*/
            //Expected - # Student should be able to add perspective to the assignment
            dataIndex = "131";
            new LoginUsingLTI().ltiLogin(dataIndex);
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            currentAssignments.getLessonAssignment().click(); //click on DW
            String perspective = new RandomString().randomstring(2);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective); //add perspective to DW assignment
            CustomAssert.assertEquals(currentAssignments.getDwComment().getText().trim(),perspective,"Validate perspective","Student is able to add perspective to the assignment","Student is not able to add perspective to the assignment");

            //Row No - 134 : 29. Add like to the assignment
            //Expected - # Student should be able to add like to the assignment
            driver.findElement(By.partialLinkText("Like")).click();
            if(!driver.findElement(By.partialLinkText("Unlike")).isDisplayed()){
                CustomAssert.fail("Validate like link","Student is not able to add like to the assignment");
            }


            /*Un-assign flow*/

            /*Expected - "135. Login as Instructor
            31. Navigate to current Assignment page
            32. Un-assign the assignment"*/
            //Expected - # Instructor should be able to un-assign the assignment
            //Expected - # Entry of the assignment should be removed from Current Assignment page
            dataIndex = "98";
            new LoginUsingLTI().ltiLogin(dataIndex);//login as an instructor
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getUnAssignButtonOfVersionAssignment());//Un-assign assignment
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getYesOnUnAssignPopUp());
            Thread.sleep(5000);
            System.out.println("4444 : " + driver.findElements(By.cssSelector("span[title = '"+dwtext+"']")).size());
            if(driver.findElements(By.cssSelector("span[title = '"+dwtext+"']")).size()!=2){
                CustomAssert.fail("Validate un-assign the assignment","Instructor is not able to un-assign the assignment");
            }
            /*Row No - 137 : "34. Select Main navigator>>Assignments>>My Assignments option
            35. Verify the My Assignments page"*/
            //Expected - # Entry of the assignment should not be removed from My Assignments page
            new Navigator().NavigateTo("My Assignments");
            System.out.println("5555 : " + driver.findElements(By.linkText(dwText)));
            if(driver.findElements(By.partialLinkText(dwtext)).size()!=2){
                CustomAssert.fail("Validate Entry of the assignment","Entry of the assignment is not removed from My Assignments page");
            }

        } catch (Exception e) {
            Assert.fail("Exception in the test method 'assigningNonGradableDWFromLessonPage' in the class 'DiscussionBasedAssignment'", e);
        }
    }


    @Test(priority = 4,enabled = true)
    public void assigningNonGradableDWFromMainNavigator() {
        try {
            //ROW NO - 139
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate assigning non gradable DW", "Validate assigning non gradable DW from Main Navigator", "info");
            String dataIndex = "139";

            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            QuestionPage questionPage = PageFactory.initElements(driver, QuestionPage.class);
            MyAssignment myAssignment = PageFactory.initElements(driver, MyAssignment.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String description = ReadTestData.readDataByTagName("", "description", dataIndex);

            /*Assigning gradable DW from main navigator*/

            //Row No - 57 & 58
            new LoginUsingLTI().ltiLogin(dataIndex);//login as an instructor
            new Navigator().NavigateTo("New Assignment");
            currentAssignments.discussionAssignmentButton.click();
            myQuestionBank.getAssignmentNameField().click();
            driver.switchTo().activeElement().sendKeys(description);
            newAssignment.questionContent.click();
            Thread.sleep(5000);
            driver.switchTo().activeElement().sendKeys("ABCD"+description);
            newAssignment.assignNowButton.click();
            Thread.sleep(5000);
            new Assignment().assignThisFormFillOtherDetails(dataIndex);

            new Navigator().NavigateTo("Class Assignments");
            Thread.sleep(5000);
            if(driver.findElements(By.cssSelector("span[title = 'D1 - ABCD"+description+"']")).size()==0){
                CustomAssert.fail("Validate DW assignment","Instructor is not able to assign DW assignment");
            }

            //Row No - 60 : 6. Verify My Assignments page
            //Expected - 1 : # Icon for DW assignment should be displayed in row 1
            System.out.println("5555: " + currentAssignments.imageIcon.getAttribute("src"));
            if (!currentAssignments.imageIcon.getAttribute("src").contains("/webresources/images/al/home-work-icon.png")) {
                CustomAssert.fail("Validating DW icon", "DW icon is not displayed");
            }

            //Expected - 5: # "D1" should be displayed before the DW description
            //Expected - 2 :# Assignment name should be displayed in row1.
            CustomAssert.assertEquals(driver.findElement(By.cssSelector("span[title = 'D1 - ABCD"+description+"']")).isDisplayed(), true, "Validating Assignment Name", "Assignment name is displayed in row 1", "Assignment name is not displayed in row 1");


            //Expected - 3 : # DW assignment description should be displayed in row 2


            //Expected - 4 : # Icon for the DW assignment next to description should be as in Current Assignment page
            if (!questionPage.discussionAssignmentDescriptionIcon.isDisplayed()) {
                CustomAssert.fail("Validating Icon for the DW assignment next to description", "Icon for the DW assignment next to description is not as in the Current Assignment page");
            }

            /*Verifying assignment entry on assigning second time*/

            /*Row No - 75 : "10. Navigate to My Assignments page from main navigator
            11. Click on 'Assign this' option"*/
            //Expected - # Instructor should be able to click on "Assign this" option

            new Navigator().NavigateTo("New Assignment");
            currentAssignments.discussionAssignmentButton.click();
            myQuestionBank.getAssignmentNameField().click();
            driver.switchTo().activeElement().sendKeys(description);
            newAssignment.questionContent.click();
            Thread.sleep(5000);
            driver.switchTo().activeElement().sendKeys("ABCD"+description);
            newAssignment.assignNowButton.click();
            Thread.sleep(5000);
            new Assignment().assignThisFormFillOtherDetails(dataIndex);

            /*Row No - 75 : "10. Navigate to My Assignments page from main navigator
            11. Click on 'Assign this' option"*/
            //Expected - # Instructor should be able to click on "Assign this" option
            //Expected - # "Assign this" pop up should be displayed
            new Navigator().NavigateTo("My Assignments");
            myQuestionBank.getAssignThis().click();
            WebDriverUtil.clickOnElementUsingJavascript(myQuestionBank.getAssignThis());
            if(!myQuestionBank.shareWithPopUp.isDisplayed()){
                CustomAssert.fail("Validating Assign This popup","Assign This popup is not displayed.");
            }

            //Row No - 78 : 21. Fill all the necessary details and click on Assign button.
            //Expected -1:  # # Instructor should be able to assign the DW assignment.
            Thread.sleep(5000);
            new Assignment().assignThisFormFillOtherDetails("" + dataIndex);
            if(!driver.findElement(By.cssSelector("span[title = 'Class Assignments']")).isDisplayed()){
                CustomAssert.fail("Validate Class assignment page","Class assignment page is not opened.");
            }

            //ROw No - 79  ;14. Verify My Assignments page
            //Expected - # Already created entry should be brought into Top in My Assignments page with the existing card details
            System.out.println("12345: " + driver.findElements(By.partialLinkText("D1")).size());
            if(driver.findElements(By.cssSelector("span[title = 'D1 - ABCD"+description+"']")).size()!=3){
                CustomAssert.fail("Validate Already created entry","Already created entry is not brought into Top in My Assignments page with the existing card details");
            }

            /*Verifying assignment entry on assigning second time with different assignment name*/

            new LoginUsingLTI().ltiLogin(dataIndex);//login as an instructor
            new Navigator().NavigateTo("My Assignments");
            Thread.sleep(5000);
            List<WebElement> assignThisElementsList = driver.findElements(By.cssSelector("span[class='assign-this']"));
            for(int a=0;a<assignThisElementsList.size();a++){
                if(assignThisElementsList.get(a).isDisplayed()){
                    assignThisElementsList.get(a).click();
                    break;
                }
            }
            Thread.sleep(5000);
            assignmentTab.editAssignmentName.click();
            driver.switchTo().activeElement().sendKeys("ABCDEFGHIJKL");
            new Assignment().assignThisFormFillOtherDetails(dataIndex);
            if(driver.findElements(By.cssSelector("span[title = 'D1 - ABCD"+description+"']")).size()!=4){
                CustomAssert.fail("Validate Already created entry","Already created entry is not brought into Top in My Assignments page with the existing card details");
            }


            /*Row No - 86 : 44 - "21. Click on All type filter
            22. Select Learning Activities from the drop down"*/

            /*Expected - # Instructor should be able to select Learning Activities filter from the drop down
            # DW question assignment should not be displayed in  My Assignments page*/
            new Navigator().NavigateTo("My Assignments");
            driver.findElement(By.linkText("All Type")).click();
            driver.findElement(By.linkText("Learning Activities")).click();

            newAssignment.searchAssignableItemsTextField.click();
            driver.switchTo().activeElement().sendKeys("D1 - ABCD"+description+"");
            Thread.sleep(3000);
            if(!currentAssignments.assignmentname.isDisplayed()){
                CustomAssert.fail("Validate Question assignment","Question assignment is not displayed");
            }

            //Row No - 46
            new Navigator().NavigateTo("My Assignments");
            driver.findElement(By.linkText("All Type")).click();
            driver.findElement(By.linkText("Question Banks")).click();

            newAssignment.searchAssignableItemsTextField.click();
            Thread.sleep(3000);
            if(driver.findElements(By.xpath("//div[@class='thumbnail-and-description-wrapper']//span[2]")).size()!=0){
                CustomAssert.fail("Validate DW Question assignment","DW Question assignment is not displayed");
            }


            /*Row No - 90 : "25. Login as student
            26. Navigate to Assignments page
            27. Click on the DW assignment name"*/

            String dwtext = "";
            dataIndex = "172";
            new LoginUsingLTI().ltiLogin(dataIndex);
            new Navigator().NavigateTo("Assignments");//navigate to assignments
            currentAssignments.getLessonAssignment().click(); //click on DW
            Thread.sleep(9000);
            String perspective = new RandomString().randomstring(2);
            //driver.findElement(By.partialLinkText("Perspectives")).click();
            lessonPage.your_perspective.click();
            driver.switchTo().activeElement().sendKeys(perspective);
            driver.findElement(By.className("post-perspective")).click();
            Thread.sleep(5000);
            //new DiscussionWidget().addPerspectiveForDWAssignment(perspective); //add perspective to DW assignment
            CustomAssert.assertEquals(currentAssignments.getDwComment().getText().trim(),perspective,"Validate perspective","Student is able to add perspective to the assignment","Student is not able to add perspective to the assignment");

            //Row No - 52 : 29. Add like to the assignment
            //Expected - # Student should be able to add like to the assignment
            driver.findElement(By.partialLinkText("Like")).click();
            if(!driver.findElement(By.partialLinkText("Unlike")).isDisplayed()){
                CustomAssert.fail("Validate like link","Student is not able to add like to the assignment");
            }


            /*Un-assign flow*/

            /*Expected - "53. Login as Instructor
            31. Navigate to current Assignment page
            32. Un-assign the assignment"*/
            //Expected - # Instructor should be able to un-assign the assignment
            //Expected - # Entry of the assignment should be removed from Current Assignment page
            dataIndex = "139";
            //String dwtext = "abcdefgh";
            new LoginUsingLTI().ltiLogin(dataIndex);//login as an instructor
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getUnAssignButtonOfVersionAssignment());//Un-assign assignment
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getYesOnUnAssignPopUp());
            Thread.sleep(5000);

            if(driver.findElements(By.cssSelector("span[title = 'D1 - ABCD"+description+"']")).size()!=3){
                CustomAssert.fail("Validate un-assign the assignment","Instructor is not able to un-assign the assignment");
            }


            /*Row No - 55 : "34. Select Main navigator>>Assignments>>My Assignments option
            35. Verify the My Assignments page"*/
            //Expected - # Entry of the assignment should not be removed from My Assignments page
            new Navigator().NavigateTo("My Assignments");
            if(driver.findElements(By.xpath("//div[@class='thumbnail-and-description-wrapper']//span[2]")).size()!=2){
                CustomAssert.fail("Validate Entry of the assignment","Entry of the assignment is not removed from My Assignments page");
            }

        } catch (Exception e) {
            Assert.fail("Exception in the test method 'assigningNonGradableDWFromMainNavigator' in the class 'DiscussionBasedAssignment'", e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void validateImageTypeResourcesEntryInAssignmentsPage() {
        try {
            //ROW NO - 182

            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("ImageType Resources", "Validating ImageType Resources in Assignments Page", "info");
            Integer dataIndex = 522;
            Integer secondDataIndex = 523;

            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            MyAssignment myAssignment = PageFactory.initElements(driver, MyAssignment.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            MyResources myResources = PageFactory.initElements(driver, MyResources.class);

            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            String resourseName = ReadTestData.readDataByTagName("", "resoursename", "" + dataIndex);

            new ResourseCreate().resourseCreate(secondDataIndex, 0);//create chapterlevel resource
            new ResourseCreate().resourseCreate(dataIndex, 0);//create chapterlevel resource


            /*Row No - 522 : "1.Login as Instructor.
            2.Navigate to eTextbook from main navigator.
            3.Navigate to corresponding lesson.
            4.Navigate to resources tab."*/
            //Expected - # Resources entry should be displayed in resources tab.
            new LoginUsingLTI().ltiLogin("" + dataIndex);
            new ResourseCreate().openResourcesTab("", "1.1: The Science of Biology", "" + dataIndex);
            Thread.sleep(5000);
            if(!driver.findElement(By.cssSelector("a[title = '"+resourseName+"']")).isDisplayed()){
                CustomAssert.fail("Validating Resources entry in resources tab","Resources entry is not displayed in resources tab.");
            }
            //Row No - 523 : 5.Assign resources from resources tab to an class section/Group/Individual
            //Expected - # User should be able to assign resources.
            new Assignment().assignResourceFromResourceTab(dataIndex);
            Thread.sleep(5000);
            if(!driver.findElement(By.cssSelector("a[title = '"+resourseName+"']")).isDisplayed()) {
                CustomAssert.fail("Validating assigned resource in Resource Tab", "User is not able to assign resources");
            }


            //Row No - 524 : 6.Navigate to "My Assignments" page element in sub menu  of the “Assignment” element in the main navigator.
            //Expected - # Assigned resources entry should be displayed in "My Assignments" page.
            new Navigator().NavigateTo("My Assignments");
            if (driver.findElements(By.linkText(resourseName)).size() == 0) {
                CustomAssert.fail("Validating assigned resource in My Assignments Module", "Assigned resources entry is not displayed in \"My Assignments\" page.");
            }

            //Row No - 525 : 7.Verify resources card in "My Assignments" page.
            //Expected - 1:  Learning activity icon should be displayed in first row.
            new Navigator().NavigateTo("My Assignments");
            if (!myAssignment.dwDescriptionIcon.getAttribute("src").contains("/webresources/images/al/home-work-icon.png")) {
                CustomAssert.fail("Validating Learning activity icon", "Learning activity icon is not displayed in first row.");
            }

            //Expected - 2 : Assignment name should be displayed after learning activity icon.
            CustomAssert.assertEquals(myAssignment.dwAssignmentName.getText().trim(), resourseName, "Validating assignment name", "Assignment name is displayed after learning activity icon", "Assignment name is displayed after learning activity icon");

            //Expected - 3 : # Image icon should be displayed in second row.
            CustomAssert.assertEquals(newAssignment.dwImageIcon.isDisplayed(), true, "Validating Image icon", "Image icon is displayed in second row", "Image icon is not displayed in second row");

            //Expected - 4 : # Assign icon and Assign this link should be displayed after Actions label.
            CustomAssert.assertEquals(questionBank.getAssignThisButtton().isDisplayed(), true, "Validating Assign This link", "Assign this link is displayed after Actions label", "Assign this link is not displayed after Actions label");


            //Row No - 530 :  - 8. Click on Resources name link.
            //Expected - # Image resources should be open in new tab.
            driver.findElement(By.linkText(resourseName)).click();
            CustomAssert.assertEquals(currentAssignments.tab_Title.get(2).getText().trim(), resourseName, "Validating image Resource in new tab", "Image resources is opened in new tab", "Image resources is not opened in new tab");


            /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
            /*UN ASSIGN RESOURCES ASSIGNMENT*/

            //Row No - 531 : "9. Navigate to Class Assignment page.
            //Expected - # Assigned resources entry should be displayed in "Class Assignment Page".
            new Navigator().NavigateTo("Class Assignments");
            if (driver.findElements(By.linkText("L1 - " + resourseName + "")).size() == 0) {
                CustomAssert.fail("Validating Assigned resources", "Assigned resources entry is displayed in \"Class Assignment Page\"");
            }

            /*Row No - 532 : "10. Click on  ""Un-assign Assignment"" link.
            11. Click on ""Yes"" from Un-assign popup."*/
            //Expected - # Assigned resources entry should be removed from "Class Assignment" page.
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getUnAssignButtonOfVersionAssignment());//Un-assign assignment
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getYesOnUnAssignPopUp());
            Thread.sleep(5000);
            WebDriverUtil.waitTillVisibilityOfElement(currentAssignments.noAssignmentExit,10);
            CustomAssert.assertEquals(currentAssignments.noAssignmentExit.getText().trim(), "No Assignment exists.", "Validating Un assign functionality", "Assignment is unassigned", "Assignment is not unassigned");


            //Row No - 533 : 12. Navigate to "My Assignments" page.
            //Expected - # Image resources entry should not be removed from "My Assignments" page.
            new Navigator().NavigateTo("My Assignments");
            if(!myAssignment.dwSection.isDisplayed()){
                CustomAssert.fail("Validating Image resources entry in My Assignments module", "Image resources entry is not removed from \"My Assignments\" page.");
            }

            new LoginUsingLTI().ltiLogin("" + dataIndex);//Login a s a student
            new ResourseCreate().openResourcesTab("", "1.1: The Science of Biology", "" + secondDataIndex);
            new Assignment().assignResourceFromResourceTab(secondDataIndex);

            /*Row No - 534 : "13.Assign same image resources again from resources tab.
            14.Navigate to ""My Assignments"" page."*/
            //Expected 1 - # Already created entry should be brought into Top in My Assignments page with the existing card details
            new LoginUsingLTI().ltiLogin("" + dataIndex);//Login as an instructor
            new ResourseCreate().openResourcesTab("", "1.1: The Science of Biology", "" + dataIndex);
            new Assignment().assignResourceFromResourceTab(dataIndex);
            new Navigator().NavigateTo("My Assignments");
            List<WebElement> resourceNameElementsList = driver.findElements(By.className("ls-assessment-item-sectn"));
            if(!resourceNameElementsList.get(0).getText().trim().contains(resourseName)){
                CustomAssert.fail("Validating created resource entry","Already created entry is not brought into Top in My Assignments page with the existing card details");
            }


            //Expected - 2 : # Second/Duplicate entry of the assignment should not be created in My Assignments page
            if(driver.findElements(By.linkText(resourseName)).size()>1){
                CustomAssert.fail("Validating Second/Duplicate entry of the assignment","Second/Duplicate entry of the assignment is not created in My Assignments page");
            }


            /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
            /*ASSIGN RESOURCES FROM ALL RESOURCES PAGE*/

           /*Row No - 536 : "15.3.Assign same image resources from ""All Resources"" page.
            16.Navigate to ""My Assignments"" page."*/
            //Expected -1 : # Already created entry should be brought into Top in My Assignments page with the existing card details
            resourseName = ReadTestData.readDataByTagName("", "resoursename", "" + secondDataIndex);
            new Assignment().assignResourceFromAllResourcesPage(secondDataIndex);
            new Navigator().NavigateTo("My Assignments");
            resourceNameElementsList = driver.findElements(By.className("ls-assessment-item-sectn"));
            if(!resourceNameElementsList.get(0).getText().trim().contains(resourseName)){
                CustomAssert.fail("Validating created resource entry","Already created entry is not brought into Top in My Assignments page with the existing card details");
            }


            //Expected - 2 : # Second/Duplicate entry of the assignment should not be created in My Assignments page
            if(driver.findElements(By.linkText(resourseName)).size()>1){
                CustomAssert.fail("Validating Second/Duplicate entry of the assignment","Second/Duplicate entry of the assignment is not created in My Assignments page");
            }


            //Row No - 538 : 17. Click on "Assign This" link.
            //Expected - #Assign This popup should be displayed.
            myQuestionBank.getAssignThis().click();
            if(!myQuestionBank.shareWithPopUp.isDisplayed()){
                CustomAssert.fail("Validating Assign This popup","Assign This popup is not displayed.");
            }


            /*Row No - 539 : "18. Fill necessary value in assign this popup.
            19. Click on Assign button."*/
            //Expected - # User should be able to assign image resources from "My Assignments" page.
            new Assignment().assignResourceFromMyAssignmentsPage(dataIndex);
            if(driver.findElements(By.cssSelector("ul[class = 'ls-resource-links ls-assign-cart-items-wrapper']")).get(1).getText().trim().equals("L1 - "+resourseName+"")){
                CustomAssert.fail("Validating Assigned resources", "User is not able to assign image resources from \"My Assignments\" page.");
            }

            //Row No - 540 : 20.Navigate to "My Assignments" Page.
            //Expected - # Already created entry should be brought into Top in My Assignments page with the existing card details
            new Navigator().NavigateTo("My Assignments");
            resourseName = ReadTestData.readDataByTagName("", "resoursename", "" + dataIndex);
            resourceNameElementsList = driver.findElements(By.className("ls-assessment-item-sectn"));
            if(!resourceNameElementsList.get(0).getText().trim().contains(resourseName)){
                CustomAssert.fail("Validating created resource entry","Already created entry is not brought into Top in My Assignments page with the existing card details");
            }

            //Row No - 541 : # Second/Duplicate entry of the assignment should not be created in My Assignments page
            if(driver.findElements(By.linkText(resourseName)).size()>1){
                CustomAssert.fail("Validating Second/Duplicate entry of the assignment","Second/Duplicate entry of the assignment is not created in My Assignments page");
            }


            /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

            /*STUDENT CAN ATTEMPT THE ASSIGN RESOURCES*/

            /*Row No - 542 : "21.Login as Student.
            22.Navigate to assignment page."*/
            //Expected - #Assigned resources entry should be displayed in "Assignment" page.
            secondDataIndex = 542;
            new LoginUsingLTI().ltiLogin("" + dataIndex);//Login a s a student
            new ResourseCreate().openResourcesTab("", "1.1: The Science of Biology", "" + dataIndex);
            new Assignment().assignResourceFromResourceTab(dataIndex);

            String comment = ReadTestData.readDataByTagName("", "comment", "" + secondDataIndex);
            new LoginUsingLTI().ltiLogin("" + secondDataIndex);//Login a s a student
            new Navigator().NavigateTo("Assignments");
            if(!assignments.resourceName.isDisplayed()){
                CustomAssert.fail("Validating Assigned resources", "Assigned resources entry is displayed in \"Class Assignment Page\"");
            }

            //Row No - 543 : 23.Submit resources assignment.
            //Expected - #Student should able to attempt resources as assignment.
            assignments.resourceName.click();
            newAssignment.addQuestionTextBox.click();
            driver.switchTo().activeElement().sendKeys(comment);
            myResources.button_post.click();
            CustomAssert.assertEquals(lessonPage.comment.getText().trim(),"family, givenname "+comment+"","validating the resource attempt by student","Student is able to attempt resources as assignment","Student is not able to attempt resources as assignment");



            /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
            /*ASSIGN RESOURCE FROM MY RESOURCES PAGE*/

            dataIndex = 544;
            resourseName = ReadTestData.readDataByTagName("", "resourcesname", "" + dataIndex);
            new LoginUsingLTI().ltiLogin("" + dataIndex);//Login as an instructor
            new UploadResources().uploadResources(""+dataIndex, true, true, true);
            new Assignment().assignResourceFromMyResourcePage(dataIndex);

            //Row No - 544 : 24.Navigate to "My Assignments" page element in sub menu  of the “Assignment” element in the main navigator.
            //Expected - #Assigned resources entry should be displayed in "My Assignments" page.
            new Navigator().NavigateTo("My Assignments");
            new WebDriverUtil().waitTillVisibilityOfElement(driver.findElement(By.linkText(resourseName + LoginUsingLTI.appendChar)),10);
            if (driver.findElements(By.linkText(resourseName + LoginUsingLTI.appendChar)).size() == 0) {
                CustomAssert.fail("Validating assigned resource in My Assignments Module", "Assigned resources entry is not displayed in \"My Assignments\" page.");
            }

            //Row No - 548 : 26. Click on Resources name link.
            //Expected - #Image resources should be open in new tab.
            driver.findElement(By.linkText(resourseName + LoginUsingLTI.appendChar)).click();
            CustomAssert.assertEquals(currentAssignments.tab_Title.get(2).getText().trim(), resourseName+ LoginUsingLTI.appendChar, "Validating image Resource in new tab", "Image resources is opened in new tab", "Image resources is not opened in new tab");

            //Row No - 549 : 27. Click on "Assign This" link.
            //Expected - #Assign This popup should be displayed.
            new Navigator().NavigateTo("My Assignments");
            myQuestionBank.getAssignThis().click();
            if(!myQuestionBank.shareWithPopUp.isDisplayed()){
                CustomAssert.fail("Validating Assign This popup","Assign This popup is not displayed.");
            }

            /*Row No - 550 :  "28. Fill necessary value in assign this popup.
            29. Click on Assign button."*/
            //Expected - # User should be able to assign image resources from "My Assignments" page.
            new Assignment().assignResourceFromMyResourcePage(dataIndex);
            new Navigator().NavigateTo("Class Assignments");
            if (driver.findElements(By.linkText("L1 - " + resourseName + LoginUsingLTI.appendChar)).size() == 0) {
                CustomAssert.fail("Validating Assigned resources", "User is able to assign image resources from \"My Assignments\" page.");
            }

            /*Row No - 551 : "30.Login as Student.
            31.Navigate to assignment page."*/
            //Expected : #Assigned resources entry should be displayed in "Assignment" page.
            secondDataIndex = 551;
            comment = ReadTestData.readDataByTagName("", "comment", "" + secondDataIndex);
            new LoginUsingLTI().ltiLogin("" + secondDataIndex);//Login a s a student
            new Navigator().NavigateTo("Assignments");
            if(!assignments.resourceName.isDisplayed()){
                CustomAssert.fail("Validating Assigned resources", "Assigned resources entry is displayed in \"Class Assignment Page\"");
            }

            //Row No - 555: 33.Submit resources assignment.
            //Expected - #Student should able to attempt resources as assignment.
            assignments.resourceName.click();
            newAssignment.addQuestionTextBox.click();
            driver.switchTo().activeElement().sendKeys(comment);
            myResources.button_post.click();
            if(lessonPage.comment.getText().trim().contains(resourseName + LoginUsingLTI.appendChar)){
                CustomAssert.fail("validating the resource attempt by student","Student is not able to attempt resources as assignment");
            }

        } catch (Exception e) {
            Assert.fail("Exception in the test method 'validateImageTypeResourcesEntryInAssignmentsPage' in the class 'MyAssignments'", e);
        }
    }


    @Test(priority = 6,enabled = true)
    public void verifyPreCreatedQuestionAssignmentInMyAssignmentsPage() {
        try {
            //ROW NO - 219
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate PreCreatedQuestionAssignment", "Verify PreCreated Question Assignment availability in My Assignments Page", "info");
            int dataIndex = 1016;

            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyAssignment myAssignment = PageFactory.initElements(driver, MyAssignment.class);

            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "" + dataIndex);

            //Precondition
            new Assignment().create(dataIndex);
            new LoginUsingLTI().ltiLogin(""+dataIndex);
            new Assignment().assignToStudent(dataIndex);
            new LoginUsingLTI().ltiLogin(""+dataIndex);

            /*Row No - 1016: "1. Login as Instructor.
            2. Navigate to ""My Assignment"" page."*/
            //Expected -1:  # Assignment entry should be displayed in "My Assignment" page.
            new Navigator().NavigateTo("My Assignments");
            if (!myAssignment.dwSection.isDisplayed()) {
                CustomAssert.fail("Validating DW assignment entry", "DW assignment entry is not displayed");
            }

            //Expected - 2 : # Assignment icon should be displayed in first row.
            if (!myAssignment.dwDescriptionIcon.isDisplayed()) {
                CustomAssert.fail("Validating DW icon", "DW icon is not displayed");
            }

            //Expected -3 : # Assignment name should be displayed after assignment icon.
            if(!driver.findElement(By.cssSelector("div[title = '"+assessmentName+"']")).isDisplayed()){
                CustomAssert.fail("Validating Assignment Name","Assignment name is not displayed after assignment icon");
            }

            //Expected - 4 : #In third row "Actions:" label should be displayed.
            //Expected - 5 : #Actions label should be displayed inside of box.
            CustomAssert.assertEquals(myAssignment.actionLabel.getText().trim(),"Actions :","Validating 'Actions: ' label","Actions label is displayed inside of box","Actions label is not displayed inside of box");

           /*Expected - 6 : # After Actions lebel following fileds should be displayed:
            Preview
            Remove from My Assignments
            Assign This
            Customize This
            Try it
            Student view"*/
            CustomAssert.assertEquals(questionBank.previewLink.isDisplayed(),true,"Validate 'Preview' button","Preview button is displayed","Preview button is not displayed");
            CustomAssert.assertEquals(myQuestionBank.removeFromMyQuestionBankButton.isDisplayed(),true,"Validate 'Remove from My Assignments' button","'Remove from My Assignments' button is displayed","'Remove from My Assignments' button is not displayed");
            CustomAssert.assertEquals(myQuestionBank.getAssignThis().isDisplayed(),true,"Validate 'Assign This' button","'Assign This' button is displayed","'Assign This' button is not displayed");
            CustomAssert.assertEquals(myQuestionBank.customizeThis.isDisplayed(),true,"Validate 'Customize This' button","'Customize This' button is displayed","'Customize This' button is not displayed");
            CustomAssert.assertEquals(myQuestionBank.tryIT_link.isDisplayed(),true,"Validate 'Try it' button","'Try it' button is displayed","'Try it' button is not displayed");
            CustomAssert.assertEquals(myQuestionBank.studentView_link.isDisplayed(),true,"Validate 'Student view' button","'Student view' button is displayed","'Student view' button is not displayed");


            /*VERIFY PREVIEW LINK*/
            //Row No - 1022 : 4. Verify "Preview" link.
            //Expected - #  Before preview label preview icon should be displayed.
            CustomAssert.assertEquals(myQuestionBank.getPreviewButton().isDisplayed(),true,"Validate preview icon","Before preview label preview icon is displayed","Before preview label preview icon is not displayed");

            //Row No - 1024 : 5.Click on Preview link.
            //Expected - # Question preview tab should be open after Question bank tab.
            myQuestionBank.getPreviewButton().click();
            if(!driver.findElement(By.cssSelector("span[title = '"+assessmentName+"']")).isDisplayed()){
                CustomAssert.fail("Validating Question preview tab","Validating Question preview tab is not displayed");
            }

            /*Row No - 1025 : "6. Close the Preview tab.
            7.Navigate to My Assignment tab."*/
            //Expected - # User should be able to close preview tab.
            Thread.sleep(5000);
            driver.findElement(By.xpath("//*[starts-with(@id,'close-assessment-')]")).click();//click on close link
            if(!driver.findElement(By.cssSelector("span[title = 'Question Banks']")).isDisplayed()){
                CustomAssert.fail("Validating close preview tab","Validating User is able to close preview tab.");
            }

            /*VERIFY 'REMOVE FROM ASSIGNMENTS' LINK*/

            //Row No - 1026 : 8.Verify 'Remove from My Assignments" link.
            //Expected - 1: # Bookmark icon should be displayed before label.
            new Navigator().NavigateTo("My Assignments");
            if(!myQuestionBank.bookmarkIcon.isDisplayed()){
                CustomAssert.fail("Validating Bookmark icon","Bookmark icon is not displayed");
            }

            //Row No - 1029 : 9.Navigate to Question Bank.
            //Expected - # Bookmark icon for assigned assignment should be marked as selected.
            new Navigator().NavigateTo("Question Banks");
            Thread.sleep(5000);
            if(driver.findElement(By.xpath("//div[@class='ls-assessment-item-sectn']//i[@class='removeThisFromMyResources bookmarked ls-resource-bookmark-icon']")).isDisplayed()){
                CustomAssert.fail("Validating Bookmark icon","Bookmark icon for assigned assignment is not marked as selected.");
            }

            /*Row No - 1030 : "10.Navigate to My Assignment page.
            11.Click on "" 'Remove from My Assignments"" link."*/
            //Expected - # Assignment entry should be remove from My Assignment page.
            new Navigator().NavigateTo("My Assignments");
            myQuestionBank.removeFromMyQuestionBankButton.click();
            List<WebElement> assignThisElementsList = driver.findElements(By.cssSelector("div[class = 'ls-assessment-item-sectn-right ls-my-resource-item-section']"));
            if(assignThisElementsList.size()!=0){
                CustomAssert.fail("Validating assignment entry", "Assignment entry is removed from My Assignment page.");
            }


            //Row No - 1031 : 12. Navigate to "Class Assignments".
            //Expected - # Assignment entry should not be removed from Class assignment.
            new Navigator().NavigateTo("Class Assignments");
            if(!myQuestionBank.assignmentEntry.isDisplayed()){
                CustomAssert.fail("Validating Assignment entry","Assignment entry is removed from Class assignment.");
            }

            dataIndex = 1032;
            myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            questionBank = PageFactory.initElements(driver, QuestionBank.class);

            assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "" + dataIndex);

            /*ASSIGN ASSIGNMENT FROM QUESTION BANK*/
            //Pre Condition - Assign any question assessment as assignment from Question Bank.
            new Assignment().create(dataIndex);
            new LoginUsingLTI().ltiLogin(""+dataIndex);
            new Assignment().assignAssignmentFromQuestionBanks(dataIndex);


            //Row No - 1032 : 13. Navigate to "My Assignment".
            //Expected - # Assignment entry should be displayed in "My Assignment".
            new Navigator().NavigateTo("My Assignments");
            CustomAssert.assertEquals(myQuestionBank.getAssessment().getText().trim(),assessmentName,"Validating Assignment entry","Assignment entry is displayed in \"My Assignment\"","Assignment entry is not displayed in \"My Assignment\"");

            //Row No - 1033 : 14.Navigate to Question Bank.
            //Expected - # Bookmark icon for assigned assignment should be marked as selected.
            new Navigator().NavigateTo("Question Banks");
            if(driver.findElement(By.xpath("//div[@class='ls-assessment-item-sectn']//i[@class='removeThisFromMyResources bookmarked ls-resource-bookmark-icon']")).isDisplayed()){
                CustomAssert.fail("Validating Bookmark icon","Bookmark icon for assigned assignment is not marked as selected.");
            }


            //Row No -1034 : 15. Click on "Remove from My Assignments".
            //Expected - # "Remove from My Assginments" link changed to "Add to My Assignment".
            //Adding assignment to search
            Thread.sleep(5000);
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assessmentName + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            new UIElement().waitAndFindElement(By.cssSelector("span[class='assign-this']"));
            Thread.sleep(5000);
            List<WebElement> assignThisElements = driver.findElements(By.cssSelector("i[class='removeThisFromMyResources bookmarked ls-resource-bookmark-icon']"));
            for (int a=0;a<assignThisElements.size();a++){
                if(assignThisElements.get(a).isDisplayed()){
                    assignThisElements.get(a).click();
                    break;
                }
            }


            //myQuestionBank.bookmarkIcon.click();
            //myQuestionBank.removeFromMyQuestionBankButton.click();
            if(!questionBank.addToMyAssignments.isDisplayed()){
                CustomAssert.fail("Validating Remove from My Assginments link", "\"Remove from My Assginments\" link is not changed to \"Add to My Assignment\".");
            }

            //Row No - 1035 : 16. Navigate to "My Assignments".
            //Expected - # Assignment entry should not be displayed in "My Assignment" page.
            new Navigator().NavigateTo("My Assignments");
            assignThisElementsList = driver.findElements(By.cssSelector("div[class = 'ls-assessment-item-sectn-right ls-my-resource-item-section']"));
            if(assignThisElementsList.size()!=0){
                CustomAssert.fail("Validating assignment entry", "Assignment entry is displayed in \"My Assignment\" page");
            }

            //Row No - 1036 : 17. Navigate to "Class Assignments".
            //Expected - # Assignment entry should not be removed from Class assignment.
            new Navigator().NavigateTo("Class Assignments");
            if(!myQuestionBank.assignmentEntryInClassAssignments.isDisplayed()){
                CustomAssert.fail("Validating Assignment entry","Assignment entry is removed from Class assignment.");
            }


            /*VERIFY ASSIGN THIS LINK*/

            //Pre Condition - Assign any question assessment as assignment from Assignment tab of E-textbook .
            dataIndex = 1037;
            new Assignment().create(dataIndex);
            new LoginUsingLTI().ltiLogin("" + dataIndex);
            new ResourseCreate().openResourcesTab("", "1.1: The Science of Biology", "" + dataIndex);
            new Assignment().assignResourceFromResourceTab(dataIndex);

            //Row No - 1037 : 18. Navigate to "My Assignment".
            //Expected - # Assignment entry should be displayed in "My Assignment".
            new Navigator().NavigateTo("My Assignments");
            if(myQuestionBank.assignmentBlockInMyAssignments.size()==0){
                CustomAssert.fail("Validating Assignment entry","Assignment entry is not displayed in \"My Assignment\".");
            }

            //Row No - 1038 : 19.Verify "Assign This" link.
            //Expected - # "Assign icon" or "Assign this" label should be displayed after " Remove from My Assignments" label in blue color.
            if(!myAssignment.assignThis.isDisplayed()){
                CustomAssert.fail("Validate \"Assign icon\" or \"Assign this\" label","\"Assign icon\" or \"Assign this\" label is not displayed");
            }


            //Row No 1039 : - 20.Click on "Assign This" link.
            //Expected - # Assign popup should be displayed.
            WebDriverUtil.clickOnElementUsingJavascript(myQuestionBank.getAssignThis());
            if(!myQuestionBank.shareWithPopUp.isDisplayed()){
                CustomAssert.fail("Validating Assign This popup","Assign This popup is not displayed.");
            }

            //Row No - 1040 : 21. Fill all the naccessory details and click on Assign button.
            //Expected -1:  # Class assignment page should be opened.
            new Assignment().assignThisFormFillOtherDetails("" + dataIndex);
            if(!driver.findElement(By.cssSelector("span[title = 'Class Assignments']")).isDisplayed()){
                CustomAssert.fail("Validate Class assignment page","Class assignment page is not opened.");
            }


            //Expected - 2 : # New Assignment entry should be added.
            if(!myQuestionBank.assignmentEntry.isDisplayed()){
                CustomAssert.fail("Validate New Assignment entry","New Assignment entry is not added.");
            }


            //Row No - 1042 :22. Navigate to "My Assignments" page.
            //Expected - # Assignment card entry should be displayed in top.
            new Navigator().NavigateTo("My Assignments");
            if(!myAssignment.assignmentEntry.isDisplayed()){
                CustomAssert.fail("Validate Assignment card entry","Assignment card entry is not displayed");
            }


            //Row No -1044 : 23. Verify "Customize This" link.
            //Expected - # Customize icon or Customize This label should be displayed after Assign this link in blue color.
            CustomAssert.assertEquals(myQuestionBank.customizeThis.isDisplayed(),true,"Validate 'Customize This' button","'Customize This' button is displayed","'Customize This' button is not displayed");


            //Row No - 1045 : 24. Click on "Customize This" link.
            //Expected - # Custom assignment tab should be open.
            myQuestionBank.customizeThis.click();
            if(!driver.findElement(By.cssSelector("span[title = 'New Assignment']")).isDisplayed()){
                CustomAssert.fail("Validate Custom assignment tab","Custom assignment tab is not opened");
            }


            /*Row No - 1046: "25. Select some question and click on ""Save For Later' link.
            26. Navigate to ""My Assignment"" page."*/
            //Expected - # New Assignment entry should be added.
            myQuestionBank.checkbox.click();
            myQuestionBank.getAssignmentNameField().click();
            driver.switchTo().activeElement().sendKeys("Customized " + assessmentName);
            WebDriverUtil.mouseHover(By.id("ls-ins-assignment-desc"));//mouse hover on description
            Thread.sleep(3000);
            driver.findElement(By.id("ls-ins-assignment-desc")).click();
            driver.switchTo().activeElement().sendKeys(assessmentName + "description");
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.className("save-for-later-text")));


            new Navigator().NavigateTo("My Assignments");
            CustomAssert.assertEquals(myQuestionBank.getAssessment().getText().trim(),"Customized "+assessmentName,"Validate New Assignment entry","New Assignment entry is added.","New Assignment entry is not added.");


             /*VERIFY TRY IT*/
            //Row No - 1047 : 27.Verify "Try it" link.
            //Expected - # Try icon or 'Try it' label should be displayed  after customize This label.
            CustomAssert.assertEquals(myQuestionBank.tryIT_link.isDisplayed(),true,"Validate 'Try it' button","'Try it' button is displayed","'Try it' button is not displayed");

            //Row No -1048 : 28. Click on "Try it" link.
            //Expected - # Question assignment should be open in new window.
            myQuestionBank.tryIT_link.click();
            for(String window : driver.getWindowHandles()){
                driver.switchTo().window(window);
            }
            Thread.sleep(2000);
            CustomAssert.assertEquals(currentAssignments.assignmentNamePreviewPage.getText().trim(),"Customized "+assessmentName,"Validate Question assignment","Question assignment is opened in new window.","Question assignment is not opened in new window.");
            driver.close();
        } catch (Exception e) {
            Assert.fail("Exception in the test method 'verifyPreCreatedQuestionAssignmentInMyAssignmentsPage' in the class 'MyAssignments'", e);
        }
    }

    @Test(priority = 7,enabled = true)
    public void verifyOrionAdaptivePracticeAssignmentInMyAssignmentsPage() {
        try {
            //ROW NO - 253
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate Orion Adaptive Practice Assignment", "Verify Orion Adaptive Practice Assignment availability in My Assignments Page", "info");
            int dataIndex = 1050;

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyAssignment myAssignment = PageFactory.initElements(driver, MyAssignment.class);

            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "" + dataIndex);


            //Precondition - Assign ORION assessment as a assignment from ORION Adaptive practice tab in eTextbook.
            new LoginUsingLTI().ltiLogin(""+dataIndex);
            new PracticeTest().assignOrionAdaptivePractice(dataIndex, 0);


            /*Row No - 1050: "1. Login as Instructor.
            2. Navigate to ""My Assignment"" page."*/
            //Expected -1:  # Assignment entry should be displayed in "My Assignment" page.
            new Navigator().NavigateTo("My Assignments");
            if (!myAssignment.dwSection.isDisplayed()) {
                CustomAssert.fail("Validating DW assignment entry", "DW assignment entry is not displayed");
            }

            //Row No - 1051 : 3.Verify Assignment entry.
            //Expected - 2 : # Assignment icon should be displayed in first row.
            if (!myAssignment.dwDescriptionIcon.isDisplayed()) {
                CustomAssert.fail("Validating DW icon", "DW icon is not displayed");
            }

            //Expected -3 : # Assignment name should be displayed after assignment icon.
            List<WebElement> assessmentList = driver.findElements(By.cssSelector("div[title = '"+assessmentName+"']"));
            for(int a=0;a<assessmentList.size();a++){
                if(assessmentList.get(a).isDisplayed()){
                    CustomAssert.fail("Validating Assignment Name","Assignment name is not displayed after assignment icon");
                }
            }

            //Expected - 4 : #In third row "Actions:" label should be displayed.
            //Expected - 5 : #Actions label should be displayed inside of box.
            CustomAssert.assertEquals(myAssignment.actionLabel.getText().trim(),"Actions :","Validating 'Actions: ' label","Actions label is displayed inside of box","Actions label is not displayed inside of box");

           /*Expected - 6 : # After Actions lebel following fileds should be displayed:
            Preview
            Remove from My Assignments
            Assign This
            Try it
            Student view"*/
            CustomAssert.assertEquals(questionBank.previewLink.isDisplayed(),true,"Validate 'Preview' button","Preview button is displayed","Preview button is not displayed");
            CustomAssert.assertEquals(myQuestionBank.removeFromMyQuestionBankButton.isDisplayed(),true,"Validate 'Remove from My Assignments' button","'Remove from My Assignments' button is displayed","'Remove from My Assignments' button is not displayed");
            CustomAssert.assertEquals(myQuestionBank.getAssignThis().isDisplayed(),true,"Validate 'Assign This' button","'Assign This' button is displayed","'Assign This' button is not displayed");
            CustomAssert.assertEquals(myQuestionBank.tryIT_link.isDisplayed(),true,"Validate 'Try it' button","'Try it' button is displayed","'Try it' button is not displayed");

            /*VERIFY PREVIEW LINK*/

            //Row No - 1056 : 4. Verify "Preview" link.
            //Expected - #  Before preview label preview icon should be displayed.
            CustomAssert.assertEquals(myQuestionBank.getPreviewButton().isDisplayed(),true,"Validate preview icon","Before preview label preview icon is displayed","Before preview label preview icon is not displayed");


            //Expected - 2: # Preview link and icon should be displayed in blue color.
            System.out.println("1 : " + questionBank.previewLink.getCssValue("color"));
            if(!questionBank.previewLink.getCssValue("color").trim().equals("rgba(43, 133, 192, 1)")){
                CustomAssert.fail("Validate Preview link and icon","Preview link and icon is not displayed in blue color");
            }


            //Row No - 1058 : 5.Click on Preview link.
            //Expected - # Question preview tab should be open after Question bank tab.
            myQuestionBank.getPreviewButton().click();
            Thread.sleep(5000);
            List<WebElement> eleList = driver.findElements(By.cssSelector("span[class = 'tab_title']"));
            CustomAssert.assertEquals(eleList.get(2).getText().trim(),assessmentName,"Validating Question preview tab","Validating Question preview tab is displayed","Validating Question preview tab is not displayed");

            /*Row No - 1059 : "6. Close the Preview tab.
            7.Navigate to My Assignment tab."*/
            //Expected - # User should be able to close preview tab.
            Thread.sleep(5000);
            driver.findElement(By.xpath("//*[starts-with(@id,'close-assessment-')]")).click();//click on close link
            if(!driver.findElement(By.cssSelector("span[title = 'Question Banks']")).isDisplayed()){
                CustomAssert.fail("Validating close preview tab","Validating User is able to close preview tab.");
            }

            /*VERIFY 'REMOVE FROM ASSIGNMENTS' LINK*/

            //Row No - 1060 : 8.Verify 'Remove from My Assignments" link.
            //Expected - 1: # Bookmark icon should be displayed before label.
            new Navigator().NavigateTo("My Assignments");
            if(!myQuestionBank.bookmarkIcon.isDisplayed()){
                CustomAssert.fail("Validating Bookmark icon","Bookmark icon is not displayed");
            }

            //Expected : #By default bookmark option should be enable.
            if(!myQuestionBank.bookmarkIcon.isDisplayed()){
                CustomAssert.fail("Validate bookmark option","By default bookmark option is not enabled");
            }

            //Expected - 3 : #  "Remove from My Assignments" label should be displayed in blue color.
            System.out.println("2: "+questionBank.previewLink.getCssValue("color"));
            if(questionBank.previewLink.getCssValue("color").equals("#2B85C0")){
                CustomAssert.fail("Validate \"Remove from My Assignments\" label","\"Remove from My Assignments\" label is not displayed in blue color.");
            }

            //Row No - 1063 : 9.Navigate to Question Bank.
            //Expected - # Bookmark icon for assigned assignment should be marked as selected.
            new Navigator().NavigateTo("Question Banks");
            Thread.sleep(5000);
            if(driver.findElement(By.xpath("//div[@class='ls-assessment-item-sectn']//i[@class='removeThisFromMyResources bookmarked ls-resource-bookmark-icon']")).isDisplayed()){
                CustomAssert.fail("Validating Bookmark icon","Bookmark icon for assigned assignment is not marked as selected.");
            }

            /*Row No - 1064 : "10.Navigate to My Assignment page.
            11.Click on "" 'Remove from My Assignments"" link."*/
            //Expected - # Assignment entry should be remove from My Assignment page.
            new Navigator().NavigateTo("My Assignments");
            myQuestionBank.removeFromMyQuestionBankButton.click();
            List<WebElement> assignThisElementsList = driver.findElements(By.cssSelector("div[class = 'ls-assessment-item-sectn-right ls-my-resource-item-section']"));
            if(assignThisElementsList.size()!=0){
                CustomAssert.fail("Validating assignment entry", "Assignment entry is removed from My Assignment page.");
            }


            //Row No - 1065 : 12. Navigate to "Class Assignments".
            //Expected - # Assignment entry should not be removed from Class assignment.
            new Navigator().NavigateTo("Class Assignments");
            if(!myQuestionBank.assignmentEntry.isDisplayed()){
                CustomAssert.fail("Validating Assignment entry","Assignment entry is removed from Class assignment.");
            }


            dataIndex = 1066;
            myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            questionBank = PageFactory.initElements(driver, QuestionBank.class);

            assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "" + dataIndex);

            //ASSIGN ORION ASSESSMENT FROM QUESTION BANK

            //Pre Condition - Assign any ORION assessment as assignment from Question Bank in practice set only filte.
            new LoginUsingLTI().ltiLogin(""+dataIndex);
            new PracticeTest().assignOrionAdaptivePracticeFromQuestionBanksPage(dataIndex);


            //Row No - 1066 : 13. Navigate to "My Assignment".
            //Expected - # Assignment entry should be displayed in "My Assignment".
            new Navigator().NavigateTo("My Assignments");
            CustomAssert.assertEquals(myQuestionBank.getAssessment().getText().trim(),assessmentName,"Validating Assignment entry","Assignment entry is displayed in \"My Assignment\"","Assignment entry is not displayed in \"My Assignment\"");

            /*ROw No - 1067 : "15.Navigate to Question Bank.
            16.Select ""practice set only"" in filter."*/
            //Expected - # Bookmark icon for assigned assignment should be marked as selected.
            new Navigator().NavigateTo("Question Banks");
            driver.findElement(By.linkText("Question Banks Only")).click();
            driver.findElement(By.linkText("Practice Sets Only")).click();
            myQuestionBank.allResourse_textarea.click();
            driver.switchTo().activeElement().sendKeys("\"" + assessmentName + "\"");
            myQuestionBank.search_Button.click();
            Thread.sleep(5000);
            if(driver.findElement(By.xpath("//div[@class='ls-assessment-item-sectn']//i[@class='removeThisFromMyResources bookmarked ls-resource-bookmark-icon']")).isDisplayed()){
                CustomAssert.fail("Validating Bookmark icon","Bookmark icon for assigned assignment is not marked as selected.");
            }


            //Row No -1068 : 15. Click on "Remove from My Assignments".
            //Expected - # "Remove from My Assginments" link changed to "Add to My Assignment".
            //Adding assignment to search
            Thread.sleep(5000);
            new UIElement().waitAndFindElement(By.cssSelector("span[class='assign-this']"));
            Thread.sleep(5000);
            List<WebElement> assignThisElements = driver.findElements(By.cssSelector("i[class='removeThisFromMyResources bookmarked ls-resource-bookmark-icon']"));
            for (int a=0;a<assignThisElements.size();a++){
                if(assignThisElements.get(a).isDisplayed()){
                    assignThisElements.get(a).click();
                    break;
                }
            }

            new Navigator().NavigateTo("Question Banks");
            driver.findElement(By.linkText("Question Banks Only")).click();
            driver.findElement(By.linkText("Practice Sets Only")).click();
            myQuestionBank.allResourse_textarea.click();
            driver.switchTo().activeElement().sendKeys("\"" + assessmentName + "\"");
            myQuestionBank.search_Button.click();
            Thread.sleep(5000);


            if(!questionBank.addToMyAssignments.isDisplayed()){
                CustomAssert.fail("Validating Remove from My Assginments link", "\"Remove from My Assginments\" link is not changed to \"Add to My Assignment\".");
            }

            //Row No - 1069 : 16. Navigate to "My Assignments".
            //Expected - # Assignment entry should not be displayed in "My Assignment" page.
            new Navigator().NavigateTo("My Assignments");
            assignThisElementsList = driver.findElements(By.cssSelector("div[class = 'ls-assessment-item-sectn-right ls-my-resource-item-section']"));
            if(assignThisElementsList.size()!=0){
                CustomAssert.fail("Validating assignment entry", "Assignment entry is displayed in \"My Assignment\" page");
            }

            //Row No - 1070 : 17. Navigate to "Class Assignments".
            //Expected - # Assignment entry should not be removed from Class assignment.
            new Navigator().NavigateTo("Class Assignments");
            if(!myQuestionBank.assignmentEntryInClassAssignments.isDisplayed()){
                CustomAssert.fail("Validating Assignment entry","Assignment entry is removed from Class assignment.");
            }



            //Pre condition 2 - Assign any ORION assessment as assignment from Question Bank in practice set only filter.
            new LoginUsingLTI().ltiLogin(""+dataIndex);
            new PracticeTest().assignOrionAdaptivePracticeFromQuestionBanksPage(dataIndex);



            /*VERIFY ASSIGN THIS LINK*/

            //Row No - 1071 : 18. Navigate to "My Assignment".
            //Expected - # Assignment entry should be displayed in "My Assignment".
            new Navigator().NavigateTo("My Assignments");
            if(myQuestionBank.assignmentBlockInMyAssignments.size()==0){
                CustomAssert.fail("Validating Assignment entry","Assignment entry is not displayed in \"My Assignment\".");
            }

            //Row No - 1072 : 19.Verify "Assign This" link.
            //Expected - # "Assign icon" or "Assign this" label should be displayed after " Remove from My Assignments" label in blue color.
            if(!myAssignment.assignThis.isDisplayed()){
                CustomAssert.fail("Validate \"Assign icon\" or \"Assign this\" label","\"Assign icon\" or \"Assign this\" label is not displayed");
            }


            //Row No 1073 : - 20.Click on "Assign This" link.
            //Expected - # Assign popup should be displayed.
            WebDriverUtil.clickOnElementUsingJavascript(myQuestionBank.getAssignThis());
            if(!myQuestionBank.shareWithPopUp.isDisplayed()){
                CustomAssert.fail("Validating Assign This popup","Assign This popup is not displayed.");
            }

            //Row No - 1074 : 21. Fill all the naccessary details and click on Assign button.
            //Expected -1:  # Class assignment page should be opened.
            Thread.sleep(5000);
            new Assignment().assignThisFormFillOtherDetails("" + dataIndex);
            if(!driver.findElement(By.cssSelector("span[title = 'Class Assignments']")).isDisplayed()){
                CustomAssert.fail("Validate Class assignment page","Class assignment page is not opened.");
            }


            //Expected - 2 : # New Assignment entry should be added.

            if(!myQuestionBank.assignmentEntry.isDisplayed()){
                CustomAssert.fail("Validate New Assignment entry","New Assignment entry is not added.");
            }


            //Row No - 1076 :22. Navigate to "My Assignments" page.
            //Expected -1:  # Assignment card entry should be displayed in top.
            new Navigator().NavigateTo("My Assignments");
            if(!myAssignment.assignmentEntry.isDisplayed()){
                CustomAssert.fail("Validate Assignment card entry","Assignment card entry is not displayed");
            }


            //Expected - 2 : # Duplicate entry should not be created for this assignment.
            if(driver.findElements(By.cssSelector("div[class='ls-assessment-item-sectn-wrapper resource-separator']")).size()>1){
               CustomAssert.fail("Validate Duplicate entry","Duplicate entry is not created for this assignment.");
            }


             /*VERIFY TRY IT*/
            //Row No - 1078 : 27.Verify "Try it" link.
            //Expected - # Try icon or 'Try it' label should be displayed  after customize This label.
            CustomAssert.assertEquals(myQuestionBank.tryIT_link.isDisplayed(),true,"Validate 'Try it' button","'Try it' button is displayed","'Try it' button is not displayed");

            //Row No -1079 : 28. Click on "Try it" link.
            //Expected - # Question assignment should be open in new window.
            myQuestionBank.tryIT_link.click();
            for(String window : driver.getWindowHandles()){
                driver.switchTo().window(window);
            }
            Thread.sleep(2000);
            CustomAssert.assertEquals(currentAssignments.assignmentNamePreviewPage.getText().trim(),"Customized "+assessmentName,"Validate Question assignment","Question assignment is opened in new window.","Question assignment is not opened in new window.");
            driver.close();


            /*VERIFY UNASSIGN*/

            /*ROw No - 1080 : "27. Navigate to Class assignment
            28. Locate the assignment and un-assign the same
            29. Navigate to My assignments and veirfy the entry getting removed "*/
            //Expected - # Un-assign from Class assignments should not remove entry from My assignments page

            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getUnAssignButtonOfVersionAssignment());//Un-assign assignment
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getYesOnUnAssignPopUp());
            Thread.sleep(5000);
            WebDriverUtil.waitTillVisibilityOfElement(currentAssignments.noAssignmentExit,10);
            CustomAssert.assertEquals(currentAssignments.noAssignmentExit.getText().trim(), "No Assignment exists.", "Validating Un assign functionality", "Assignment is unassigned", "Assignment is not unassigned");

            new Navigator().NavigateTo("My Assignments");
            if(!driver.findElement(By.cssSelector("div[title = '"+assessmentName+"']")).isDisplayed()){
                CustomAssert.fail("Validating Image resources entry in My Assignments module", "Image resources entry is not removed from \"My Assignments\" page.");
            }

        } catch (Exception e) {
            Assert.fail("Exception in the test method 'verifyOrionAdaptivePracticeAssignmentInMyAssignmentsPage' in the class 'MyAssignments'", e);
        }
    }


    @Test(priority = 8,enabled = true)
    public void verifyStaticAssessmentInMyAssignmentsPage() {
            try {
                //ROW NO - 285
                WebDriver driver = Driver.getWebDriver();
                ReportUtil.log("Validate Static Assessment", "Verify static assessment in My Assignments Page", "info");
                int dataIndex = 1082;

                CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
                MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
                QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
                MyAssignment myAssignment = PageFactory.initElements(driver, MyAssignment.class);

                String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "" + dataIndex);


                //Precondition - Assign Static assessment as a assignment from Study plan tab in eTextbook.

                /*Row No -1082 : "1. Login as Instructor.
                2. Navigate to ""My Assignment"" page."*/
                new LoginUsingLTI().ltiLogin(""+dataIndex);
                new Assignment().assignAssignmentFromEtextBookInStudyPlan(""+dataIndex,0);

                //Expected -1:  # Assignment entry should be displayed in "My Assignment" page.
                new Navigator().NavigateTo("My Assignments");
                if (!myAssignment.dwSection.isDisplayed()) {
                    CustomAssert.fail("Validating DW assignment entry", "DW assignment entry is not displayed");
                }

                //Row No - 1083 : 3.Verify Assignment entry.
                //Expected - 2 : # Assignment icon should be displayed in first row.
                if (!myAssignment.dwDescriptionIcon.isDisplayed()) {
                    CustomAssert.fail("Validating DW icon", "DW icon is not displayed");
                }

                //Expected -3 : # Assignment name should be displayed after assignment icon.
                List<WebElement> assessmentList = driver.findElements(By.cssSelector("div[title = '"+assessmentName+"']"));
                for(int a=0;a<assessmentList.size();a++){
                    if(!assessmentList.get(a).isDisplayed()){
                        CustomAssert.fail("Validating Assignment Name","Assignment name is not displayed after assignment icon");
                    }
                }

                //Expected - 4 : #In third row "Actions:" label should be displayed.
                //Expected - 5 : #Actions label should be displayed inside of box.
                CustomAssert.assertEquals(myAssignment.actionLabel.getText().trim(),"Actions :","Validating 'Actions: ' label","Actions label is displayed inside of box","Actions label is not displayed inside of box");

               /*Expected - 6 : # After Actions lebel following fileds should be displayed:
                Preview
                Remove from My Assignments
                Assign This
                Try it
                Student view"*/
                CustomAssert.assertEquals(questionBank.previewLink.isDisplayed(),true,"Validate 'Preview' button","Preview button is displayed","Preview button is not displayed");
                CustomAssert.assertEquals(myQuestionBank.removeFromMyQuestionBankButton.isDisplayed(),true,"Validate 'Remove from My Assignments' button","'Remove from My Assignments' button is displayed","'Remove from My Assignments' button is not displayed");
                CustomAssert.assertEquals(myQuestionBank.getAssignThis().isDisplayed(),true,"Validate 'Assign This' button","'Assign This' button is displayed","'Assign This' button is not displayed");
                CustomAssert.assertEquals(myQuestionBank.tryIT_link.isDisplayed(),true,"Validate 'Try it' button","'Try it' button is displayed","'Try it' button is not displayed");

                /*VERIFY PREVIEW LINK*/

                //Row No - 1088 : 4. Verify "Preview" link.
                //Expected - #  Before preview label preview icon should be displayed.
                CustomAssert.assertEquals(myQuestionBank.getPreviewButton().isDisplayed(),true,"Validate preview icon","Before preview label preview icon is displayed","Before preview label preview icon is not displayed");


                //Expected - 2: # Preview link and icon should be displayed in blue color.
                System.out.println("1 : " + questionBank.previewLink.getCssValue("color"));
                if(!questionBank.previewLink.getCssValue("color").trim().equals("rgba(43, 133, 192, 1)")){
                    CustomAssert.fail("Validate Preview link and icon","Preview link and icon is not displayed in blue color");
                }


                //Row No - 1090 : 5.Click on Preview link.
                //Expected - # Question preview tab should be open after Question bank tab.
                myQuestionBank.getPreviewButton().click();
                Thread.sleep(5000);
                List<WebElement> eleList = driver.findElements(By.cssSelector("span[class = 'tab_title']"));
                CustomAssert.assertEquals(eleList.get(2).getText().trim(),assessmentName,"Validating Question preview tab","Validating Question preview tab is displayed","Validating Question preview tab is not displayed");

                /*Row No - 1091 : "6. Close the Preview tab.
                7.Navigate to My Assignment tab."*/
                //Expected - # User should be able to close preview tab.
                Thread.sleep(5000);
                driver.findElement(By.xpath("//*[starts-with(@id,'close-assessment-')]")).click();//click on close link
                if(!driver.findElement(By.cssSelector("span[title = 'Question Banks']")).isDisplayed()){
                    CustomAssert.fail("Validating close preview tab","Validating User is able to close preview tab.");
                }

                /*VERIFY 'REMOVE FROM ASSIGNMENTS' LINK*/

                //Row No - 1092 : 8.Verify 'Remove from My Assignments" link.
                //Expected - 1: # Bookmark icon should be displayed before label.
                new Navigator().NavigateTo("My Assignments");
                if(!myQuestionBank.bookmarkIcon.isDisplayed()){
                    CustomAssert.fail("Validating Bookmark icon","Bookmark icon is not displayed");
                }

                //Expected : #By default bookmark option should be enable.
                if(!myQuestionBank.bookmarkIcon.isDisplayed()){
                    CustomAssert.fail("Validate bookmark option","By default bookmark option is not enabled");
                }

                //Expected - 3 : #  "Remove from My Assignments" label should be displayed in blue color.
                System.out.println("2: "+questionBank.previewLink.getCssValue("color"));
                if(questionBank.previewLink.getCssValue("color").equals("#2B85C0")){
                    CustomAssert.fail("Validate \"Remove from My Assignments\" label","\"Remove from My Assignments\" label is not displayed in blue color.");
                }

                //Row No - 1095 : 9.Navigate to Question Bank.
                //Expected - # Bookmark icon for assigned assignment should be marked as selected.
                new Navigator().NavigateTo("Question Banks");
                Thread.sleep(5000);
                if(driver.findElement(By.xpath("//div[@class='ls-assessment-item-sectn']//i[@class='removeThisFromMyResources bookmarked ls-resource-bookmark-icon']")).isDisplayed()){
                    CustomAssert.fail("Validating Bookmark icon","Bookmark icon for assigned assignment is not marked as selected.");
                }

                /*Row No - 1096 : "10.Navigate to My Assignment page.
                11.Click on "" 'Remove from My Assignments"" link."*/
                //Expected - # Assignment entry should be remove from My Assignment page.
                new Navigator().NavigateTo("My Assignments");
                myQuestionBank.removeFromMyQuestionBankButton.click();
                List<WebElement> assignThisElementsList = driver.findElements(By.cssSelector("div[class = 'ls-assessment-item-sectn-right ls-my-resource-item-section']"));
                if(assignThisElementsList.size()!=0){
                    CustomAssert.fail("Validating assignment entry", "Assignment entry is removed from My Assignment page.");
                }


                //Row No - 1097 : 12. Navigate to "Class Assignments".
                //Expected - # Assignment entry should not be removed from Class assignment.
                new Navigator().NavigateTo("Class Assignments");
                if(!myQuestionBank.assignmentEntry.isDisplayed()){
                    CustomAssert.fail("Validating Assignment entry","Assignment entry is removed from Class assignment.");
                }


                dataIndex = 1098;
                myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
                questionBank = PageFactory.initElements(driver, QuestionBank.class);

                assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "" + dataIndex);

                //ASSIGN STATIC PRACTICE ASSESSMENT FROM QUESTION BANK

                //Pre Condition - Assign any Static practice assessment as assignment from Question Bank in practice set only filter.
                new LoginUsingLTI().ltiLogin(""+dataIndex);
                new PracticeTest().assignOrionAdaptivePracticeFromQuestionBanksPage(dataIndex);


                //Row No - 1098 : 13. Navigate to "My Assignment".
                //Expected - # Assignment entry should be displayed in "My Assignment".
                new Navigator().NavigateTo("My Assignments");
                CustomAssert.assertEquals(myQuestionBank.getAssessment().getText().trim(),assessmentName,"Validating Assignment entry","Assignment entry is displayed in \"My Assignment\"","Assignment entry is not displayed in \"My Assignment\"");

                /*Row No - 1099 : "15.Navigate to Question Bank.
                16.Select ""practice set only"" in filter."*/
                //Expected - # Bookmark icon for assigned assignment should be marked as selected.
                new Navigator().NavigateTo("Question Banks");
                driver.findElement(By.linkText("Question Banks Only")).click();
                driver.findElement(By.linkText("Practice Sets Only")).click();
                myQuestionBank.allResourse_textarea.click();
                driver.switchTo().activeElement().sendKeys("\"" + assessmentName + "\"");
                myQuestionBank.search_Button.click();
                Thread.sleep(5000);
                if(driver.findElement(By.xpath("//div[@class='ls-assessment-item-sectn']//i[@class='removeThisFromMyResources bookmarked ls-resource-bookmark-icon']")).isDisplayed()){
                    CustomAssert.fail("Validating Bookmark icon","Bookmark icon for assigned assignment is not marked as selected.");
                }


                //Row No -1100 : 15. Click on "Remove from My Assignments".
                //Expected - # "Remove from My Assginments" link changed to "Add to My Assignment".
                //Adding assignment to search
                Thread.sleep(5000);
                new UIElement().waitAndFindElement(By.cssSelector("span[class='assign-this']"));
                Thread.sleep(5000);
                List<WebElement> assignThisElements = driver.findElements(By.cssSelector("i[class='removeThisFromMyResources bookmarked ls-resource-bookmark-icon']"));
                for (int a=0;a<assignThisElements.size();a++){
                    if(assignThisElements.get(a).isDisplayed()){
                        assignThisElements.get(a).click();
                        break;
                    }
                }

                new Navigator().NavigateTo("Question Banks");
                driver.findElement(By.linkText("Question Banks Only")).click();
                driver.findElement(By.linkText("Practice Sets Only")).click();
                myQuestionBank.allResourse_textarea.click();
                driver.switchTo().activeElement().sendKeys("\"" + assessmentName + "\"");
                myQuestionBank.search_Button.click();
                Thread.sleep(5000);


                if(!questionBank.addToMyAssignments.isDisplayed()){
                    CustomAssert.fail("Validating Remove from My Assginments link", "\"Remove from My Assginments\" link is not changed to \"Add to My Assignment\".");
                }

                //Row No - 1101 : 16. Navigate to "My Assignments".
                //Expected - # Assignment entry should not be displayed in "My Assignment" page.
                new Navigator().NavigateTo("My Assignments");
                assignThisElementsList = driver.findElements(By.cssSelector("div[class = 'ls-assessment-item-sectn-right ls-my-resource-item-section']"));
                if(assignThisElementsList.size()!=0){
                    CustomAssert.fail("Validating assignment entry", "Assignment entry is displayed in \"My Assignment\" page");
                }

                //Row No - 1102 : 17. Navigate to "Class Assignments".
                //Expected - # Assignment entry should not be removed from Class assignment.
                new Navigator().NavigateTo("Class Assignments");
                if(!myQuestionBank.assignmentEntryInClassAssignments.isDisplayed()){
                    CustomAssert.fail("Validating Assignment entry","Assignment entry is removed from Class assignment.");
                }



                //Pre condition 2 - Assign any static practice assessment as assignment from Question Bank in practice set only filter.
                new LoginUsingLTI().ltiLogin(""+dataIndex);
                new PracticeTest().assignOrionAdaptivePracticeFromQuestionBanksPage(dataIndex);



                /*VERIFY ASSIGN THIS LINK*/

                //Row No - 1103 : 18. Navigate to "My Assignment".
                //Expected - # Assignment entry should be displayed in "My Assignment".
                new Navigator().NavigateTo("My Assignments");
                if(myQuestionBank.assignmentBlockInMyAssignments.size()==0){
                    CustomAssert.fail("Validating Assignment entry","Assignment entry is not displayed in \"My Assignment\".");
                }

                //Row No - 1104 : 19.Verify "Assign This" link.
                //Expected - # "Assign icon" or "Assign this" label should be displayed after " Remove from My Assignments" label in blue color.
                if(!myAssignment.assignThis.isDisplayed()){
                    CustomAssert.fail("Validate \"Assign icon\" or \"Assign this\" label","\"Assign icon\" or \"Assign this\" label is not displayed");
                }


                //Row No 1105 : - 20.Click on "Assign This" link.
                //Expected - # Assign popup should be displayed.
                WebDriverUtil.clickOnElementUsingJavascript(myQuestionBank.getAssignThis());
                if(!myQuestionBank.shareWithPopUp.isDisplayed()){
                    CustomAssert.fail("Validating Assign This popup","Assign This popup is not displayed.");
                }

                //Row No - 1106 : 21. Fill all the naccessory details and click on Assign button.
                //Expected -1:  # Class assignment page should be opened.
                Thread.sleep(5000);
                new Assignment().assignThisFormFillOtherDetails("" + dataIndex);
                if(!driver.findElement(By.cssSelector("span[title = 'Class Assignments']")).isDisplayed()){
                    CustomAssert.fail("Validate Class assignment page","Class assignment page is not opened.");
                }


                //Expected - 2 : # New Assignment entry should be added.

                if(!myQuestionBank.assignmentEntry.isDisplayed()){
                    CustomAssert.fail("Validate New Assignment entry","New Assignment entry is not added.");
                }


                //Row No - 1108 :22. Navigate to "My Assignments" page.
                //Expected -1:  # Assignment card entry should be displayed in top.
                new Navigator().NavigateTo("My Assignments");
                if(!myAssignment.assignmentEntry.isDisplayed()){
                    CustomAssert.fail("Validate Assignment card entry","Assignment card entry is not displayed");
                }


                if(driver.findElements(By.cssSelector("div[title = '"+assessmentName+"']")).size()>1){
                    CustomAssert.fail("Validate Duplicate entry","Duplicate entry is not created for this assignment.");
                }

                 /*VERIFY TRY IT*/

                //Row No - 1110 : 27.Verify "Try it" link.
                //Expected - # Try icon or 'Try it' label should be displayed  after customize This label.
                CustomAssert.assertEquals(myQuestionBank.tryIT_link.isDisplayed(),true,"Validate 'Try it' button","'Try it' button is displayed","'Try it' button is not displayed");

                //Row No -1111 : 28. Click on "Try it" link.
                //Expected - # Question assignment should be open in new window.
                myQuestionBank.tryIT_link.click();
                for(String window : driver.getWindowHandles()){
                    driver.switchTo().window(window);
                }
                Thread.sleep(2000);
                CustomAssert.assertEquals(currentAssignments.assignmentNamePreviewPage.getText().trim(),assessmentName,"Validate Question assignment","Question assignment is opened in new window.","Question assignment is not opened in new window.");
                driver.close();


                /*VERIFY UNASSIGN*/

                /*Row No - 1112 - "34. Navigate to Class assignment
                35. Locate the assignment and un-assign the same
                36. Navigate to My assignments and verify the entry getting removed "*/
                //Expected - # Un-assign from Class assignments should not remove entry from My assignments page
                new LoginUsingLTI().ltiLogin(""+dataIndex);
                new Navigator().NavigateTo("Class Assignments");
                WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getUnAssignButtonOfVersionAssignment());//Un-assign assignment
                WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getYesOnUnAssignPopUp());
                Thread.sleep(5000);
                WebDriverUtil.waitTillVisibilityOfElement(currentAssignments.noAssignmentExit,10);
                CustomAssert.assertEquals(currentAssignments.noAssignmentExit.getText().trim(), "No Assignment exists.", "Validating Un assign functionality", "Assignment is unassigned", "Assignment is not unassigned");

                new Navigator().NavigateTo("My Assignments");
                if(!driver.findElement(By.cssSelector("div[title = '"+assessmentName+"']")).isDisplayed()){
                    CustomAssert.fail("Validating Image resources entry in My Assignments module", "Image resources entry is not removed from \"My Assignments\" page.");
                }

            } catch (Exception e) {
            Assert.fail("Exception in the test method 'verifyStaticAssessmentInMyAssignmentsPage' in the class 'MyAssignments'", e);
        }
    }


    @Test(priority = 9,enabled = true)
    public void verifyPreCreatedFileBasedAssignmentInMyAssignmentsPage() {
        try {
            //ROW NO - 320
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate Pre-created File Based Assignment", "Verify Pre-created File Based Assignment in My Assignments Page", "info");
            int dataIndex = 1117;

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyAssignment myAssignment = PageFactory.initElements(driver, MyAssignment.class);

            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "" + dataIndex);


            //Precondition - Assign Pre-Created file based assessment as a assignment from Question Bank.

            /*Row No -1117 : "1. Login as Instructor.
            2. Navigate to ""My Assignment"" page."*/
            new Assignment().createFileBasedAssessment(dataIndex);
            new LoginUsingLTI().ltiLogin(""+dataIndex);
            new Assignment().assignFileBasedAssignmentToStudent(dataIndex);

            //Expected -1:  # Assignment entry should be displayed in "My Assignment" page.
            new LoginUsingLTI().ltiLogin(""+dataIndex);
            new Navigator().NavigateTo("My Assignments");
            if (!myAssignment.dwSection.isDisplayed()) {
                CustomAssert.fail("Validating DW assignment entry", "DW assignment entry is not displayed");
            }

            //Row No - 118 : 3.Verify Assignment entry.
            //Expected - 2 : # Assignment icon should be displayed in first row.
            if (!myAssignment.dwDescriptionIcon.isDisplayed()) {
                CustomAssert.fail("Validating DW icon", "DW icon is not displayed");
            }

            //Expected -3 : # Assignment name should be displayed after assignment icon.
            CustomAssert.assertEquals(driver.findElement(By.cssSelector("div[title = '"+assessmentName+"']")).getText().trim(),assessmentName,"Validate Assignment Name","Assignment name is displayed after assignment icon","Assignment name is not displayed after assignment icon");



            //Expected - 4 : #In third row "Actions:" label should be displayed.
            //Expected - 5 : #Actions label should be displayed inside of box.
            CustomAssert.assertEquals(myAssignment.actionLabel.getText().trim(),"Actions :","Validating 'Actions: ' label","Actions label is displayed inside of box","Actions label is not displayed inside of box");

               /*Expected - 6 : # After Actions lebel following fileds should be displayed:
                Preview
                Remove from My Assignments
                Assign This
                Try it
                Student view"*/
            CustomAssert.assertEquals(questionBank.previewLink.isDisplayed(),true,"Validate 'Preview' button","Preview button is displayed","Preview button is not displayed");
            CustomAssert.assertEquals(myQuestionBank.removeFromMyQuestionBankButton.isDisplayed(),true,"Validate 'Remove from My Assignments' button","'Remove from My Assignments' button is displayed","'Remove from My Assignments' button is not displayed");
            CustomAssert.assertEquals(myQuestionBank.getAssignThis().isDisplayed(),true,"Validate 'Assign This' button","'Assign This' button is displayed","'Assign This' button is not displayed");

                /*VERIFY PREVIEW LINK*/

            //Row No - 1123 : 4. Verify "Preview" link.
            //Expected - #  Before preview label preview icon should be displayed.
            CustomAssert.assertEquals(myQuestionBank.getPreviewButton().isDisplayed(),true,"Validate preview icon","Before preview label preview icon is displayed","Before preview label preview icon is not displayed");


            //Expected - 2: # Preview link and icon should be displayed in blue color.
            System.out.println("1 : " + questionBank.previewLink.getCssValue("color"));
            if(!questionBank.previewLink.getCssValue("color").trim().equals("rgba(43, 133, 192, 1)")){
                CustomAssert.fail("Validate Preview link and icon","Preview link and icon is not displayed in blue color");
            }


            //Row No - 1125 : 5.Click on Preview link.
            //Expected - # Question preview tab should be open after Question bank tab.
            myQuestionBank.getPreviewButton().click();
            Thread.sleep(5000);
            List<WebElement> eleList = driver.findElements(By.cssSelector("span[class = 'tab_title']"));
            CustomAssert.assertEquals(eleList.get(2).getText().trim(),assessmentName,"Validating Question preview tab","Validating Question preview tab is displayed","Validating Question preview tab is not displayed");

            /*Row No - 1126 : "6. Close the Preview tab.
            7.Navigate to My Assignment tab."*/
            //Expected - # User should be able to close preview tab.
            Thread.sleep(5000);
            driver.findElement(By.xpath("//*[starts-with(@id,'close-assessment-')]")).click();//click on close link
            if(!driver.findElement(By.cssSelector("span[title = 'Question Banks']")).isDisplayed()){
                CustomAssert.fail("Validating close preview tab","Validating User is able to close preview tab.");
            }

            /*VERIFY 'REMOVE FROM ASSIGNMENTS' LINK*/

            //Row No - 1127 : 8.Verify 'Remove from My Assignments" link.
            //Expected - 1: # Bookmark icon should be displayed before label.
            new Navigator().NavigateTo("My Assignments");
            if(!myQuestionBank.bookmarkIcon.isDisplayed()){
                CustomAssert.fail("Validating Bookmark icon","Bookmark icon is not displayed");
            }

            //Expected : #By default bookmark option should be enable.
            if(!myQuestionBank.bookmarkIcon.isDisplayed()){
                CustomAssert.fail("Validate bookmark option","By default bookmark option is not enabled");
            }

            //Expected - 3 : #  "Remove from My Assignments" label should be displayed in blue color.
            System.out.println("2: "+questionBank.previewLink.getCssValue("color"));
            if(questionBank.previewLink.getCssValue("color").equals("#2B85C0")){
                CustomAssert.fail("Validate \"Remove from My Assignments\" label","\"Remove from My Assignments\" label is not displayed in blue color.");
            }

            //Row No - 1130 : 9.Navigate to Question Bank.
            //Expected - # Bookmark icon for assigned assignment should be marked as selected.
            new Navigator().NavigateTo("Question Banks");
            Thread.sleep(5000);
            if(driver.findElement(By.xpath("//div[@class='ls-assessment-item-sectn']//i[@class='removeThisFromMyResources bookmarked ls-resource-bookmark-icon']")).isDisplayed()){
                CustomAssert.fail("Validating Bookmark icon","Bookmark icon for assigned assignment is not marked as selected.");
            }

            /*Row No - 1131 : "10.Navigate to My Assignment page.
            11.Click on "" 'Remove from My Assignments"" link."*/
            //Expected - # Assignment entry should be remove from My Assignment page.
            new Navigator().NavigateTo("My Assignments");
            myQuestionBank.removeFromMyQuestionBankButton.click();
            List<WebElement> assignThisElementsList = driver.findElements(By.cssSelector("div[class = 'ls-assessment-item-sectn-right ls-my-resource-item-section']"));
            if(assignThisElementsList.size()!=0){
                CustomAssert.fail("Validating assignment entry", "Assignment entry is removed from My Assignment page.");
            }


            //Row No - 1132 : 12. Navigate to "Class Assignments".
            //Expected - # Assignment entry should not be removed from Class assignment.
            new Navigator().NavigateTo("Class Assignments");
            if(!myQuestionBank.assignmentEntry.isDisplayed()){
                CustomAssert.fail("Validating Assignment entry","Assignment entry is removed from Class assignment.");
            }


            myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            questionBank = PageFactory.initElements(driver, QuestionBank.class);

            assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "" + dataIndex);

            //ASSIGN ASSIGNMENT FROM QUESTION BANK

            //Pre Condition - Assign any File Based assessment as assignment from Question Bank.
            new LoginUsingLTI().ltiLogin(""+dataIndex);
            new Assignment().assignFileBasedAssignmentToStudent(dataIndex);


            //Row No - 1133 : 13. Navigate to "My Assignment".
            //Expected - # Assignment entry should be displayed in "My Assignment".
            new Navigator().NavigateTo("My Assignments");
            CustomAssert.assertEquals(myQuestionBank.getAssessment().getText().trim(),assessmentName,"Validating Assignment entry","Assignment entry is displayed in \"My Assignment\"","Assignment entry is not displayed in \"My Assignment\"");

            /*Row No - 1134 : "15.Navigate to Question Bank.
            16.Select ""practice set only"" in filter."*/
            //Expected - # Bookmark icon for assigned assignment should be marked as selected.
            new Navigator().NavigateTo("Question Banks");
            myQuestionBank.allResourse_textarea.click();
            driver.switchTo().activeElement().sendKeys("\"" + assessmentName + "\"");
            myQuestionBank.search_Button.click();
            Thread.sleep(5000);
            if(driver.findElement(By.xpath("//div[@class='ls-assessment-item-sectn']//i[@class='removeThisFromMyResources bookmarked ls-resource-bookmark-icon']")).isDisplayed()){
                CustomAssert.fail("Validating Bookmark icon","Bookmark icon for assigned assignment is not marked as selected.");
            }


            //Row No -1135 : 15. Click on "Remove from My Assignments".
            //Expected - # "Remove from My Assignments" link changed to "Add to My Assignment".
            //Adding assignment to search
            Thread.sleep(5000);
            new UIElement().waitAndFindElement(By.cssSelector("span[class='assign-this']"));
            Thread.sleep(5000);
            List<WebElement> assignThisElements = driver.findElements(By.cssSelector("i[class='removeThisFromMyResources bookmarked ls-resource-bookmark-icon']"));
            for (int a=0;a<assignThisElements.size();a++){
                if(assignThisElements.get(a).isDisplayed()){
                    assignThisElements.get(a).click();
                    break;
                }
            }

            new Navigator().NavigateTo("Question Banks");
            myQuestionBank.allResourse_textarea.click();
            driver.switchTo().activeElement().sendKeys("\"" + assessmentName + "\"");
            myQuestionBank.search_Button.click();
            Thread.sleep(5000);


            if(!questionBank.addToMyAssignments.isDisplayed()){
                CustomAssert.fail("Validating Remove from My Assginments link", "\"Remove from My Assginments\" link is not changed to \"Add to My Assignment\".");
            }

            //Row No - 1136 : 16. Navigate to "My Assignments".
            //Expected - # Assignment entry should not be displayed in "My Assignment" page.
            new Navigator().NavigateTo("My Assignments");
            assignThisElementsList = driver.findElements(By.cssSelector("div[class = 'ls-assessment-item-sectn-right ls-my-resource-item-section']"));
            if(assignThisElementsList.size()!=0){
                CustomAssert.fail("Validating assignment entry", "Assignment entry is displayed in \"My Assignment\" page");
            }

            //Row No - 1137 : 17. Navigate to "Class Assignments".
            //Expected - # Assignment entry should not be removed from Class assignment.
            new Navigator().NavigateTo("Class Assignments");
            if(!myQuestionBank.assignmentEntryInClassAssignments.isDisplayed()){
                CustomAssert.fail("Validating Assignment entry","Assignment entry is removed from Class assignment.");
            }



            //Pre condition 2 - Assign any File Based assessment as assignment from Assignment Tab.

            //Row No - 1138 : 18. Navigate to "My Assignment".
            new LoginUsingLTI().ltiLogin(""+dataIndex);
            new ResourseCreate().openResourcesTab("", "Introduction", "" + dataIndex);
            new Assignment().assignFileBasedAssignmentFromAssignmentsTab(dataIndex);

            //Expected - # Assignment entry should be displayed in "My Assignment".
            new Navigator().NavigateTo("My Assignments");
            if(myQuestionBank.assignmentBlockInMyAssignments.size()==0){
                CustomAssert.fail("Validating Assignment entry","Assignment entry is not displayed in \"My Assignment\".");
            }

            /*VERIFY ASSIGN THIS LINK*/

            //Row No - 1139 : 19.Verify "Assign This" link.
            //Expected - # "Assign icon" or "Assign this" label should be displayed after " Remove from My Assignments" label in blue color.
            if(!myAssignment.assignThis.isDisplayed()){
                CustomAssert.fail("Validate \"Assign icon\" or \"Assign this\" label","\"Assign icon\" or \"Assign this\" label is not displayed");
            }


            //Row No 1140 : - 20.Click on "Assign This" link.
            //Expected - # Assign popup should be displayed.
            WebDriverUtil.clickOnElementUsingJavascript(myQuestionBank.getAssignThis());
            if(!myQuestionBank.shareWithPopUp.isDisplayed()){
                CustomAssert.fail("Validating Assign This popup","Assign This popup is not displayed.");
            }

            //Row No - 1141 : 21. Fill all the naccessory details and click on Assign button.
            //Expected -1:  # Class assignment page should be opened.
            Thread.sleep(5000);
            dataIndex = 1118;
            new Assignment().assignThisFormFillOtherDetails("" + dataIndex);
            if(!driver.findElement(By.cssSelector("span[title = 'Class Assignments']")).isDisplayed()){
                CustomAssert.fail("Validate Class assignment page","Class assignment page is not opened.");
            }


            //Expected - 2 : # New Assignment entry should be added.

            if(!myQuestionBank.assignmentEntry.isDisplayed()){
                CustomAssert.fail("Validate New Assignment entry","New Assignment entry is not added.");
            }




             /*VERIFY UNASSIGN*/

             /*Row No - 1145 - "34. Navigate to Class assignment
             35. Locate the assignment and un-assign the same
             36. Navigate to My assignments and verify the entry getting removed "*/
            //Expected - # Un-assign from Class assignments should not remove entry from My assignments page

            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getUnAssignButtonOfVersionAssignment());//Un-assign assignment
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getYesOnUnAssignPopUp());
            Thread.sleep(5000);

            new Navigator().NavigateTo("My Assignments");
            if(!driver.findElement(By.cssSelector("div[title = '"+assessmentName+"']")).isDisplayed()){
                CustomAssert.fail("Validating Image resources entry in My Assignments module", "Image resources entry is not removed from \"My Assignments\" page.");
            }

        } catch (Exception e) {
            Assert.fail("Exception in the test method 'verifyPreCreatedFileBasedAssignmentInMyAssignmentsPage' in the class 'MyAssignments'", e);
        }
    }

    @Test(priority = 10,enabled = true)
    public void verifyBehaviourOfUpdateAssignmentFromMyAssignmentPage() {
        try {
            //Not required
            //verifyBehaviourOfUpdateAssignmentFromMyAssignmentPage
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate Behaviour of Update Assignment", "Verify Validate Behaviour of Update Assignment after assigning from My Assignment Page", "info");
            int dataIndex = 1277;

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyAssignment myAssignment = PageFactory.initElements(driver, MyAssignment.class);

            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "" + dataIndex);


            //Precondition - 1.Course should have existing user and pre created entry in My Assignments.
            new Assignment().createFileBasedAssessment(dataIndex);

            /*"1. Login as Existing instructor.
            2. Click on Main Navigator.
            3. Click on Assignment Link. "*/
            //Expected - # My Assignments label should be displayed in assignment elements for existing user.
            new LoginUsingLTI().ltiLogin(""+dataIndex);
            new UIElement().waitAndFindElement(By.xpath("./*//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath("./*//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            driver.findElement(By.linkText("Assignments")).click();
            CustomAssert.assertEquals(driver.findElement(By.linkText("My Assignments")).isDisplayed(),true,"Validate My Assignments Label","My Assignments label is displayed in assignment elements for existing user","My Assignments label is not displayed in assignment elements for existing user");

            //Row No - 1279 : 4. Click in "My Assignments" link.
           /*Expected - # My Assignments tab should be opened.
            # Tab header label should show “My Assignments”*/
            new Navigator().NavigateTo("My Assignments");
            if(!driver.findElement(By.cssSelector("span[title = 'My Assignments']")).isDisplayed()){
                 CustomAssert.fail("Validate My Assignments tab","My Assignments tab is not opened");
            }


            //ROw No - 1282 : 5. Verify entries in My Assignments.
            //Expected - #  # "Remove from My Assignments" link is displayed for pre added question assignment.
            CustomAssert.assertEquals(myQuestionBank.removeFromMyQuestionBankButton.isDisplayed(),true,"Validate 'Remove from My Assignments' button","'Remove from My Assignments' button is displayed","'Remove from My Assignments' button is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in the test method 'verifyBehaviourOfUpdateAssignmentFromMyAssignmentPage' in the class 'MyAssignments'", e);
        }
    }


    @Test(priority = 11, enabled = true)
    public void instructorShouldBeAbleToFilterAssignableItemsInMyAssignmentsPage() {
        try {
            //ROW No - 352
            WebDriver driver = Driver.getWebDriver();
            ReportUtil.log("Validate filter assignable items", "Validate that instructor should be able to filter assignable items in “My Assignments” page", "info");
            int dataIndex = 1159;

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);

            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "" + dataIndex);


            /*Row No - 1159 : "1. Login as instructor
            2. Click on main navigator
            3. Select Assignments option"*/
            //Expected - # "My Assignments" option should be displayed as sub-option under Assignments
            //new LoginUsingLTI().ltiLogin(""+dataIndex);
            //Assign resource
            //Assign question Assignment(Custom Assignment)
            //AssignFileBasedAssignment
            //Assign Widget
            //Assign Resources
            dataIndex = 1160;
            String resourseName = ReadTestData.readDataByTagName("", "resoursename", "" + dataIndex);
            new ResourseCreate().resourseCreate(dataIndex, 0);//create chapterlevel resource
            new LoginUsingLTI().ltiLogin(""+dataIndex);
            new Assignment().assignResourceFromAllResourcesPage(dataIndex);

            dataIndex = 1161;
            String questionText = ReadTestData.readDataByTagName("", "questiontext", "" + dataIndex);
            new LoginUsingLTI().ltiLogin(""+dataIndex);
            new CreateCustomAssignment().createCustomAssignment(questionText,""+dataIndex);
            new Assignment().assignCustomAssignmentFromMyQuestionBank(dataIndex);

            dataIndex = 1162;
            new Assignment().createFileBasedAssessment(dataIndex);
            new LoginUsingLTI().ltiLogin(""+dataIndex);
            new Assignment().assignFileBasedAssignmentToStudent(dataIndex);


            dataIndex = 1163;
            new LoginUsingLTI().ltiLogin(""+dataIndex);
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(dataIndex);

            /*Row No 1159- "1. Login as instructor
            2. Click on main navigator
            3. Select Assignments option"*/

            //Expected - 1: # "My Assignments" option should be displayed as sub-option under Assignments
            new LoginUsingLTI().ltiLogin(""+dataIndex);
            new UIElement().waitAndFindElement(By.xpath(".//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath(".//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            driver.findElement(By.linkText("Assignments")).click();
            CustomAssert.assertEquals(driver.findElement(By.cssSelector("span[data-localize = 'My Assignments']")).isDisplayed(), true, "Validate My Assignments Label", "My Assignments label is displayed in assignment elements for existing user", "My Assignments label is not displayed in assignment elements for existing user");

            //Expected - 2 : # "My Question Banks" option should not be displayed
            if(driver.findElements(By.cssSelector("span[data-localize = 'My Question Banks']")).size()!=0){
                CustomAssert.fail("Validate My Question Banks Label","My Question Banks label is not displayed in assignment elements for existing user");
            }

            //Row No - 1161 : 4. Select 'My Assignments' option from main navigator
            //Expected 1-# Instructor should be able to click on My Assignments option
            try {
                new Navigator().NavigateTo("My Assignments");
            }catch(Exception e){
                CustomAssert.fail("Validate My Assignments option","Instructor is not able to click on My Assignments option");
            }

            //Expected -2 : # My Assignments page should get displayed
            if(!driver.findElement(By.cssSelector("span[title = 'My Assignments']")).isDisplayed()){
                CustomAssert.fail("Validate My Assignments page","My Assignments page is not displayed");
            }

            //Expected - 3 : # "Search Assignable Items..." placeholder should be displayed beside Hide filters option
             CustomAssert.assertEquals(newAssignment.searchAssignableItemsTextField.getAttribute("placeholder").trim(),"Search Assignable Items...","Validate \"Search Assignable Items...\" placeholder","\"Search Assignable Items...\" placeholder is displayed beside Hide filters option","\"Search Assignable Items...\" placeholder is not displayed beside Hide filters option");

            //Expected - 4 :# "+Create Custom Assignment" option should be displayed on the right most side beside Hide filter option
            try{
                myQuestionBank.getCustomAssignmentButton().click();
            }catch(Exception e){
                CustomAssert.fail("Validate \"+Create Custom Assignment\" option","\"+Create Custom Assignment\" option is not displayed on the right most side beside Hide filter option");
            }

            //Expected - 5 : # 'All type', 'All chapters', 'All sections', 'All learning objectives' filters should be displayed


            //Row No - 1166 : 6. Click on All type filter
            //Expected - # # User should be able to click on 'All type filter
            new Navigator().NavigateTo("My Assignments");
            try{
                driver.findElement(By.linkText("All Type")).click();
            }catch(Exception e){
                CustomAssert.fail("Validate All Type filter","All Type filter is not selected by default");
            }

            //Expected - 1 : - # Filter should get expanded with options
            //Expected - 2 : # 'Question Bank' and ' 'Leaning Activities' options should be displayed
            driver.findElement(By.linkText("All Type")).click();
            try{
                driver.findElement(By.linkText("Question Banks")).click();
            }catch(Exception e){
                CustomAssert.fail("Validate Question Banks options","Question Banks options is not displayed");
            }

            driver.findElement(By.linkText("Question Banks")).click();
            try{
                driver.findElement(By.linkText("Learning Activities")).click();
            }catch(Exception e){
                CustomAssert.fail("Validate Learning Activities options","Learning Activities options is not displayed");
            }


            //Precondition 3#:  My Assignments page should have Question assignment assigned

            /*Row No - 1175 : "9. Type assignment name in the placeholder
            10. Click on Search option"*/

            /*Expected - # User should be able to select the search option
            # Question assignment should get displayed*/
            dataIndex = 1161;
            String customAssignmentName = ReadTestData.readDataByTagName("", "customassignmentname", "" + dataIndex);
            new Navigator().NavigateTo("My Assignments");
            newAssignment.searchAssignableItemsTextField.click();
            driver.switchTo().activeElement().sendKeys("\""+customAssignmentName+"\"");
            questionBank.searchIcon.click();
            if(!driver.findElement(By.cssSelector("div[title = '"+customAssignmentName+"']")).isDisplayed()){
                CustomAssert.fail("Validate Question assignment","Question assignment is not displayed");
            }

            //Precondition - # My Assignments page should have Question Assignment assigned

            /*Row No - 1177 : ""32. Type assignment name in the placeholder
            33. Click on Search option
            34. Select Question Banks in My Assignments filter*/

            /*Expected - # User should be able to select the search option
            # Question Assignment should get displayed*/
            new Navigator().NavigateTo("My Assignments");

            driver.findElement(By.linkText("All Type")).click();
            driver.findElement(By.linkText("Question Banks")).click();

            newAssignment.searchAssignableItemsTextField.click();
            driver.switchTo().activeElement().sendKeys("\""+customAssignmentName+"\"");
            questionBank.searchIcon.click();
            if(!driver.findElement(By.cssSelector("div[title = '"+customAssignmentName+"']")).isDisplayed()){
                CustomAssert.fail("Validate Question assignment","Question assignment is not displayed");
            }


            /*Row No - 1179 : "35. Type assignment name in the placeholder
            36. Click on Search option
            37. Select Learning Activity in My Assignments filter"*/

            /*Expected - # User should be able to select the search option
            # Question assignment should not get displayed */
            dataIndex = 1160;
            String resoursename = ReadTestData.readDataByTagName("", "resoursename", "" + dataIndex);
            new Navigator().NavigateTo("My Assignments");

            driver.findElement(By.linkText("All Type")).click();
            driver.findElement(By.linkText("Learning Activities")).click();

            newAssignment.searchAssignableItemsTextField.click();
            driver.switchTo().activeElement().sendKeys("\""+resoursename+"\"");
            questionBank.searchIcon.click();
            if(!driver.findElement(By.cssSelector("div[title = '"+resoursename+"']")).isDisplayed()){
                CustomAssert.fail("Validate Question assignment","Question assignment is not displayed");
            }


            //Precondition - # My Assignments should have gradable/non gradable File based assignment assigned

            /*Row No - 1181 : "11. Type file based assignment name in the placeholder
            12. Click on Search option"*/

            /*Expected - # User should be able to select the search option
            # File based assignment should get displayed*/
            dataIndex = 1162;
            assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "" + dataIndex);
            new Navigator().NavigateTo("My Assignments");
            newAssignment.searchAssignableItemsTextField.click();
            driver.switchTo().activeElement().sendKeys("\""+assessmentName+"\"");
            questionBank.searchIcon.click();
            if(!driver.findElement(By.cssSelector("div[title = '"+assessmentName+"']")).isDisplayed()){
                CustomAssert.fail("Validate Question assignment","Question assignment is not displayed");
            }



            //Precondition - # My Assignments page should have File based Assignment assigned

            /*Row No - 1183 : "38. Type File based assignment name in the placeholder
            39. Click on Search option
            40. Select Question Banks in My Assignments filter"*/

            /*Expected - # User should be able to select the search option
            # File based Assignment should get displayed*/
            dataIndex = 1162;
            assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "" + dataIndex);
            new Navigator().NavigateTo("My Assignments");

            driver.findElement(By.linkText("All Type")).click();
            driver.findElement(By.linkText("Question Banks")).click();

            newAssignment.searchAssignableItemsTextField.click();
            driver.switchTo().activeElement().sendKeys("\""+assessmentName+"\"");
            questionBank.searchIcon.click();
            if(!driver.findElement(By.cssSelector("div[title = '"+assessmentName+"']")).isDisplayed()){
                CustomAssert.fail("Validate Question assignment","Question assignment is not displayed");
            }


            /*Row No - 1185 : "41. Type file based assignment name in the placeholder
            42. Click on Search option
            43. Select Learning Activity in My Assignments filter"*/

            /*Expected  - # User should be able to select the search option
            # Question assignment should not get displayed*/
            dataIndex = 1162;
            assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "" + dataIndex);
            new Navigator().NavigateTo("My Assignments");

            driver.findElement(By.linkText("All Type")).click();
            driver.findElement(By.linkText("Learning Activities")).click();

            newAssignment.searchAssignableItemsTextField.click();
            driver.switchTo().activeElement().sendKeys("\""+assessmentName+"\"");
            questionBank.searchIcon.click();
            System.out.println("1111: " + driver.findElements(By.cssSelector("div[title = '"+assessmentName+"']")).size());
            if(driver.findElements(By.cssSelector("div[title = '"+assessmentName+"']")).size()!= 7){
                //CustomAssert.fail("Validate Question assignment","Question assignment is not displayed");
            }


            //# Precondition: My Assignments page should have widgets as assignment

            /*Row No - 1187 : "13. Type widget name in the placeholder
            14. Click on Search option"*/

            /*Expected - # User should be able to select the search option
            # Learning Activity assignment having the widget should get displayed*/

            String widgetName = "What is the importance of phosphorylation in the positive regulation of the cell cycle and how might this affect the protein activity?";
            new Navigator().NavigateTo("My Assignments");
            newAssignment.searchAssignableItemsTextField.click();
            driver.switchTo().activeElement().sendKeys("\""+widgetName+"\"");
            questionBank.searchIcon.click();
            if(!driver.findElement(By.cssSelector("div[title = '"+widgetName+"']")).isDisplayed()){
                CustomAssert.fail("Validate Question assignment","Question assignment is not displayed");
            }


            /*Row No - 1189 : "23. Type assignment name in the placeholder
            24. Click on Search option
            25. Select Learning Activity in My Assignments filter"*/

            /*Expected - # User should be able to select the search option
            # Learning Activity assignment having the widget should get displayed*/
            new Navigator().NavigateTo("My Assignments");
            driver.findElement(By.linkText("All Type")).click();
            driver.findElement(By.linkText("Learning Activities")).click();

            newAssignment.searchAssignableItemsTextField.click();
            driver.switchTo().activeElement().sendKeys("\""+widgetName+"\"");
            questionBank.searchIcon.click();
            Thread.sleep(3000);
            if(!driver.findElement(By.cssSelector("div[title = '"+widgetName+"']")).isDisplayed()){
                CustomAssert.fail("Validate Question assignment","Question assignment is not displayed");
            }


            /*Row No - 1191 : "26. Type assignment name in the placeholder
            27. Click on Search option
            28. Select Question Banks in My Assignments filter"*/

            /*Expected - # User should be able to select the search option
            # Learning Activity assignment should not get displayed*/
            new Navigator().NavigateTo("My Assignments");
            driver.findElement(By.linkText("All Type")).click();
            driver.findElement(By.linkText("Question Banks")).click();

            newAssignment.searchAssignableItemsTextField.click();
            driver.switchTo().activeElement().sendKeys("\""+widgetName+"\"");
            questionBank.searchIcon.click();
            if(driver.findElements(By.cssSelector("div[title = '"+widgetName+"']")).size()!=0){
                CustomAssert.fail("Validate Question assignment","Question assignment is not displayed");
            }


            //Pre condition - # My Assignments page should have widgets as assignment

            /*Row No - 1193 - "44. Type widget name in the placeholder
            45. Click on Search option
            46. Select Learning Activity in My Assignments filter"*/

            /*Expected - # User should be able to select the search option
            # Learning Activity assignment having the widget should get displayed*/
            new Navigator().NavigateTo("My Assignments");
            driver.findElement(By.linkText("All Type")).click();
            driver.findElement(By.linkText("Learning Activities")).click();

            newAssignment.searchAssignableItemsTextField.click();
            driver.switchTo().activeElement().sendKeys("\""+widgetName+"\"");
            questionBank.searchIcon.click();
            if(!driver.findElement(By.cssSelector("div[title = '"+widgetName+"']")).isDisplayed()){
                CustomAssert.fail("Validate Question assignment","Question assignment is not displayed");
            }


            /*Row No - 1195 - "47. Type widget name in the placeholder
            48.  Click on Search option
            49. Select Question Banks in My Assignments filter"*/

            /*Expected - # User should be able to select the search option
            # Learning Activity assignment having the widget should get displayed*/
            new Navigator().NavigateTo("My Assignments");
            driver.findElement(By.linkText("All Type")).click();
            driver.findElement(By.linkText("Question Banks")).click();
            newAssignment.searchAssignableItemsTextField.click();
            driver.switchTo().activeElement().sendKeys("\""+widgetName+"\"");
            questionBank.searchIcon.click();
            Thread.sleep(5000);
            if(!driver.findElement(By.cssSelector("div[title = '"+widgetName+"']")).isDisplayed()){
                CustomAssert.fail("Validate Question assignment","Question assignment is not displayed");
            }



            //Pre Condition - # My Assignments page should have resources as assignment
            /*Row No - 1197 : "15. Type resource name in the placeholder
            16. Click on Search option"*/

            /*Expected - # User should be able to select the search option
            # Learning Activity having the resource should get displayed*/
            dataIndex = 1160;
            resoursename = ReadTestData.readDataByTagName("", "resoursename", "" + dataIndex);
            new Navigator().NavigateTo("My Assignments");
            newAssignment.searchAssignableItemsTextField.click();
            driver.switchTo().activeElement().sendKeys("\""+resoursename+"\"");
            questionBank.searchIcon.click();
            if(!driver.findElement(By.cssSelector("div[title = '"+resoursename+"']")).isDisplayed()){
                CustomAssert.fail("Validate Question assignment","Question assignment is not displayed");
            }


            /*Row No - 1199 - "29. Type assignment name in the placeholder
            30. Click on Search option
            31. Select Learning Activity in My Assignments filter"*/

            /*Expected - # User should be able to select the search option
            # Learning Activity assignment having the resource should get displayed*/
            dataIndex = 1160;
            resoursename = ReadTestData.readDataByTagName("", "resoursename", "" + dataIndex);
            new Navigator().NavigateTo("My Assignments");

            driver.findElement(By.linkText("All Type")).click();
            driver.findElement(By.linkText("Learning Activities")).click();

            newAssignment.searchAssignableItemsTextField.click();
            driver.switchTo().activeElement().sendKeys("\""+resoursename+"\"");
            questionBank.searchIcon.click();
            if(!driver.findElement(By.cssSelector("div[title = '"+resoursename+"']")).isDisplayed()){
                CustomAssert.fail("Validate Resource","Resource is not displayed");
            }



            /*Row No - 1203 : "53. Type resource name in the placeholder
            54. Click on Search option
            55. Select Question Banks in My Assignments filter"*/

            /*Expected - # User should be able to select the search option
            # Learning Activity assignment having the resource should not get displayed*/
            dataIndex = 1160;
            resoursename = ReadTestData.readDataByTagName("", "resoursename", "" + dataIndex);
            new Navigator().NavigateTo("My Assignments");
            newAssignment.searchAssignableItemsTextField.click();
            driver.switchTo().activeElement().sendKeys("\""+resoursename+"\"");
            questionBank.searchIcon.click();
            driver.findElement(By.linkText("All Type")).click();
            driver.findElement(By.linkText("Question Banks")).click();


            if(driver.findElements(By.cssSelector("div[title = '"+resoursename+"']")).size()!=0){
                CustomAssert.fail("Validate Resource","Resource is not displayed");
            }

        } catch (Exception e) {
            Assert.fail("Exception in the test method 'instructorShouldBeAbleToFilterAssignableItemsInMyAssignmentsPage' in the class 'MyAssignments'", e);
        }
    }

}
