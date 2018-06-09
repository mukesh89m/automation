package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT22.R229;


import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.TocSearch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by shashank on 01-07-2015.
 */
public class AssignDifferentDates extends Driver {

    @Test(priority = 1,enabled = true)
    public void assignDifferentDatesLink()
    {
        try {
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "10");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "10");
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            TocSearch tocSearch=PageFactory.initElements(driver,TocSearch.class);
            new LoginUsingLTI().ltiLogin("10");
            new Navigator().NavigateTo("New Assignment");
            //click on Custom assignment
            newAssignment.createCustomAssignment.click();
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);//search question
            new AssignLesson().selectQuestionForCustomAssignment("10");//select question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);//Type name
            newAssignment.assignNowButton.click();//click on assign now button
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
            //verify  link 'Asssign different dates'
            Assert.assertEquals(newAssignment.assignDifferentDates.getText(), "Assign different dates", "Assign different dates link is not present");
            newAssignment.assignDifferentDates.click();
            Thread.sleep(1000);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
            new Navigator().NavigateTo("New Assignment");
            //click on file based assignment
            newAssignment.createFileBasedAssignment.click();
            newAssignment.assessmentNameTextBox.click();
            newAssignment.assessmentName_TextBox.sendKeys("File based assignment");
            Thread.sleep(1000);
            newAssignment.assignNowButton.click();//click on assign now button
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
            //verify  link 'Asssign different dates'
            Assert.assertEquals(newAssignment.assignDifferentDates.getText(), "Assign different dates", "Assign different dates link is not present");
            newAssignment.assignDifferentDates.click();
            Thread.sleep(1000);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
            new Navigator().NavigateTo("New Assignment");
            newAssignment.getUsePreCreatedAssignment().click();
            new UIElement().waitAndFindElement(assignmentTab.addToMyQuestionBank);
            Thread.sleep(3000);
            myQuestionBank.getAssignThis().click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
            //verify  link 'Asssign different dates'
            Assert.assertEquals(newAssignment.assignDifferentDates.getText(), "Assign different dates", "Assign different dates link is not present");
            newAssignment.assignDifferentDates.click();
            Thread.sleep(1000);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
            new Navigator().NavigateTo("New Assignment");
            newAssignment.getUsePreCreatedAssignment().click();
            new UIElement().waitAndFindElement(assignmentTab.addToMyQuestionBank);
            assignmentTab.addToMyQuestionBank.click();
            new Navigator().NavigateTo("My Question Bank"); //navigate to Question Banks
            Thread.sleep(3000);
            myQuestionBank.getAssignThis().click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
            //verify  link 'Asssign different dates'
            Assert.assertEquals(newAssignment.assignDifferentDates.getText(), "Assign different dates", "Assign different dates link is not present");
            newAssignment.assignDifferentDates.click();
            Thread.sleep(1000);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
            new Navigator().NavigateTo("e-Textbook");
            newAssignment.assignAllLessons.click();
            Thread.sleep(3000);
            myQuestionBank.getAssignThis().click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
            //verify  link 'Asssign different dates'
            Assert.assertEquals(newAssignment.assignDifferentDates.getText(), "Assign different dates", "Assign different dates link is not present");
            newAssignment.assignDifferentDates.click();
            Thread.sleep(1000);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(), true, "Assign to field is not displaying");
            new Navigator().NavigateTo("e-Textbook");
            new UIElement().waitAndFindElement(lessonPage.closeButton);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", lessonPage.closeButton);;
            new Navigator().navigateToTab("Assignments");
            assignmentTab.rightArrow.click();
            Thread.sleep(2000);
            assignmentTab.assignThis.click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
            //verify  link 'Asssign different dates'
            Assert.assertEquals(newAssignment.assignDifferentDates.getText(), "Assign different dates", "Assign different dates link is not present");
            newAssignment.assignDifferentDates.click();
            Thread.sleep(1000);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
            new Navigator().NavigateTo("All Resources");

            assignmentTab.assignThisUnderResources.click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            //verify  link 'Asssign different dates'
            Assert.assertEquals(newAssignment.assignDifferentDates.getText(), "Assign different dates", "Assign different dates link is not present");
            //upload new resource
            new UploadResources().uploadResources("10", true, false, true); //upload a resource
            Thread.sleep(3000);
            driver.findElement(By.cssSelector("span.assign-this")).click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
            //verify  link 'Asssign different dates'
            Assert.assertEquals(newAssignment.assignDifferentDates.getText(), "Assign different dates", "Assign different dates link is not present");
            newAssignment.assignDifferentDates.click();
            Thread.sleep(1000);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
            //assign from lesson page

            new Navigator().NavigateTo("e-Textbook");
            tocSearch.hideTOC.click();
            lessonPage.getAssignForImage().click();
            driver.findElement(By.cssSelector("span.assign-this-text")).click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
            //verify  link 'Asssign different dates'
            Assert.assertEquals(newAssignment.assignDifferentDates.getText(), "Assign different dates", "Assign different dates link is not present");
            newAssignment.assignDifferentDates.click();
            Thread.sleep(1000);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in class AssignDifferentDates in method assignDifferentDatesLink",e);
        }
    }


    @Test(priority =2,enabled = true)
    public void autoSuggestFuctionality()
    {
        try {
            String classSection=null;
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "10");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "10");
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            TocSearch tocSearch=PageFactory.initElements(driver,TocSearch.class);
            new LoginUsingLTI().ltiLogin("70");
            new LoginUsingLTI().ltiLogin("71");
            new LoginUsingLTI().ltiLogin("68");
            new LoginUsingLTI().ltiLogin("69");
            new LoginUsingLTI().ltiLogin("68");
            new Navigator().NavigateTo("New Assignment");
            //click on Custom assignment
            newAssignment.createCustomAssignment.click();
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);//search question
            new AssignLesson().selectQuestionForCustomAssignment("10");//select question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);//Type name
            newAssignment.assignNowButton.click();//click on assign now button
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            newAssignment.helpIconAssignDifferentDates.click();
            Thread.sleep(500);
            Assert.assertEquals(newAssignment.helpMessage.getText(),"Use this to assign different dates for different class sections.","Help message not displaying");
            //verify  link 'Asssign different dates'
            Assert.assertEquals(newAssignment.assignDifferentDates.getText(), "Assign different dates", "Assign different dates link is not present");
            newAssignment.assignDifferentDates.click();
            Thread.sleep(1000);
            classSection=newAssignment.shareWith.getText();
            newAssignment.removeClassSection.click();//remove class section

            driver.findElement(By.className("maininput")).sendKeys(classSection);
            Thread.sleep(2000);
            newAssignment.shareWithClass.click();

            //CLICK ON ADD ROW
            newAssignment.addRow.click();
            Thread.sleep(1000);
            newAssignment.classSection.get(newAssignment.classSection.size()-1).sendKeys("family");
            try {
                boolean isPresent = newAssignment.shareWithClass.isDisplayed();
                Assert.assertEquals(isPresent, false, "Auto suggestion is displaying");
            }
            catch(Exception e)
            {}
            newAssignment.removeClass.get(newAssignment.removeClass.size()-1).click();

            newAssignment.removeClassSection.click();//remove class section
            newAssignment.classSection.get(0).sendKeys("family");
            Thread.sleep(2000);
            newAssignment.shareWithClass.click();


            new Navigator().NavigateTo("New Assignment");
            //click on file based assignment
            newAssignment.createFileBasedAssignment.click();
            newAssignment.assessmentNameTextBox.click();
            newAssignment.assessmentName_TextBox.sendKeys("File based assignment");
            Thread.sleep(1000);
            newAssignment.assignNowButton.click();//click on assign now button
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
            newAssignment.helpIconAssignDifferentDates.click();
            Thread.sleep(500);
            Assert.assertEquals(newAssignment.helpMessage.getText(),"Use this to assign different dates for different class sections.","Help message not displaying");
            //verify  link 'Asssign different dates'
            Assert.assertEquals(newAssignment.assignDifferentDates.getText(), "Assign different dates", "Assign different dates link is not present");
            newAssignment.assignDifferentDates.click();
            Thread.sleep(1000);
            classSection=newAssignment.shareWith.getText();
            newAssignment.removeClassSection.click();//remove class section

            driver.findElement(By.className("maininput")).sendKeys(classSection);
            Thread.sleep(2000);
            newAssignment.shareWithClass.click();

            //CLICK ON ADD ROW
            newAssignment.addRow.click();
            Thread.sleep(1000);
            newAssignment.classSection.get(newAssignment.classSection.size()-1).sendKeys("family");
            try {
                boolean isPresent = newAssignment.shareWithClass.isDisplayed();
                Assert.assertEquals(isPresent, false, "Auto suggestion is displaying");
            }
            catch(Exception e)
            {}
            newAssignment.removeClass.get(newAssignment.removeClass.size()-1).click();

            newAssignment.removeClassSection.click();//remove class section
            newAssignment.classSection.get(0).sendKeys("family");
            Thread.sleep(2000);
            newAssignment.shareWithClass.click();

            new Navigator().NavigateTo("New Assignment");
            newAssignment.getUsePreCreatedAssignment().click();
            Thread.sleep(3000);
            ListIterator<WebElement> listIt=myQuestionBank.getAssignThisList().listIterator();
            while(listIt.hasNext())
            {

                if(listIt.next().isDisplayed())

                {
                    listIt.next().click();
                    break;
                }

            }
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
            newAssignment.helpIconAssignDifferentDates.click();
            Thread.sleep(500);
            Assert.assertEquals(newAssignment.helpMessage.getText(),"Use this to assign different dates for different class sections.","Help message not displaying");
            //verify  link 'Asssign different dates'
            Assert.assertEquals(newAssignment.assignDifferentDates.getText(), "Assign different dates", "Assign different dates link is not present");
            newAssignment.assignDifferentDates.click();
            Thread.sleep(1000);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
            classSection=newAssignment.shareWith.getText();
            newAssignment.removeClassSection.click();//remove class section

            driver.findElement(By.className("maininput")).sendKeys(classSection);
            Thread.sleep(2000);
            newAssignment.shareWithClass.click();

            //CLICK ON ADD ROW
             newAssignment.addRow.click();
            Thread.sleep(1000);
            newAssignment.classSection.get(newAssignment.classSection.size()-1).sendKeys("family");
           try {
               boolean isPresent = newAssignment.shareWithClass.isDisplayed();
               Assert.assertEquals(isPresent, false, "Auto suggestion is displaying");
           }
           catch(Exception e)
           {}
            newAssignment.removeClass.get(newAssignment.removeClass.size()-1).click();

            newAssignment.removeClassSection.click();//remove class section
            newAssignment.classSection.get(0).sendKeys("family");
            Thread.sleep(2000);
            newAssignment.shareWithClass.click();
            new Navigator().NavigateTo("New Assignment");
            newAssignment.getUsePreCreatedAssignment().click();
            new UIElement().waitAndFindElement(assignmentTab.addToMyQuestionBank);
            assignmentTab.addToMyQuestionBank.click();
            new Navigator().NavigateTo("My Question Bank"); //navigate to Question Banks
            Thread.sleep(3000);
            myQuestionBank.getAssignThis().click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
            newAssignment.helpIconAssignDifferentDates.click();
            Thread.sleep(500);
            Assert.assertEquals(newAssignment.helpMessage.getText(),"Use this to assign different dates for different class sections.","Help message not displaying");
            //verify  link 'Asssign different dates'
            Assert.assertEquals(newAssignment.assignDifferentDates.getText(), "Assign different dates", "Assign different dates link is not present");
            newAssignment.assignDifferentDates.click();
            Thread.sleep(1000);
            classSection=newAssignment.shareWith.getText();
            newAssignment.removeClassSection.click();//remove class section

            driver.findElement(By.className("maininput")).sendKeys(classSection);
            Thread.sleep(2000);
            newAssignment.shareWithClass.click();

            //CLICK ON ADD ROW
            newAssignment.addRow.click();
            Thread.sleep(1000);
            newAssignment.classSection.get(newAssignment.classSection.size()-1).sendKeys("family");
            try {
                boolean isPresent = newAssignment.shareWithClass.isDisplayed();
                Assert.assertEquals(isPresent, false, "Auto suggestion is displaying");
            }
            catch(Exception e)
            {}
            newAssignment.removeClass.get(newAssignment.removeClass.size()-1).click();

            newAssignment.removeClassSection.click();//remove class section
            newAssignment.classSection.get(0).sendKeys("family");
            Thread.sleep(2000);
            newAssignment.shareWithClass.click();

            new Navigator().NavigateTo("e-Textbook");
            newAssignment.assignAllLessons.click();
            Thread.sleep(3000);
            //myQuestionBank.getAssignOnPopUp().click();
            new Click().clickBycssselector("span[class='btn sty-green submit-assign']");
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
            newAssignment.helpIconAssignDifferentDates.click();
            Thread.sleep(500);
            Assert.assertEquals(newAssignment.helpMessage.getText(),"Use this to assign different dates for different class sections.","Help message not displaying");
            //verify  link 'Asssign different dates'
            Assert.assertEquals(newAssignment.assignDifferentDates.getText(), "Assign different dates", "Assign different dates link is not present");
            newAssignment.assignDifferentDates.click();
            Thread.sleep(1000);
            classSection=newAssignment.shareWith.getText();
            newAssignment.removeClassSection.click();//remove class section

            driver.findElement(By.className("maininput")).sendKeys(classSection);
            Thread.sleep(2000);
            newAssignment.shareWithClass.click();
            Thread.sleep(1000);
           // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", newAssignment.addRow);
            Thread.sleep(1000);
            //CLICK ON ADD ROW
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input.add-row-button")));
            newAssignment.addRow.click();
            Thread.sleep(1000);
            newAssignment.classSection.get(newAssignment.classSection.size()-1).sendKeys("family");
            try {
                boolean isPresent = newAssignment.shareWithClass.isDisplayed();
                Assert.assertEquals(isPresent, false, "Auto suggestion is displaying");
            }
            catch(Exception e)
            {

            }
            Thread.sleep(2000);
            newAssignment.removeClass.get(newAssignment.removeClass.size()-1).click();
            Thread.sleep(4000);
            newAssignment.removeClassSection.click();//remove class section
            newAssignment.classSection.get(0).sendKeys("family");
            Thread.sleep(2000);
            newAssignment.shareWithClass.click();

            new Navigator().NavigateTo("e-Textbook");
            new UIElement().waitAndFindElement(lessonPage.closeButton);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", lessonPage.closeButton);;
            new Navigator().navigateToTab("Assignments");
            assignmentTab.rightArrow.click();
            assignmentTab.assignThis.click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
            newAssignment.helpIconAssignDifferentDates.click();
            Thread.sleep(500);
            Assert.assertEquals(newAssignment.helpMessage.getText(),"Use this to assign different dates for different class sections.","Help message not displaying");
            //verify  link 'Asssign different dates'
            Assert.assertEquals(newAssignment.assignDifferentDates.getText(), "Assign different dates", "Assign different dates link is not present");
            newAssignment.assignDifferentDates.click();
            Thread.sleep(1000);
            classSection=newAssignment.shareWith.getText();
            newAssignment.removeClassSection.click();//remove class section

            driver.findElement(By.className("maininput")).sendKeys(classSection);
            Thread.sleep(2000);
            newAssignment.shareWithClass.click();

            //CLICK ON ADD ROW
            newAssignment.addRow.click();
            Thread.sleep(1000);
            newAssignment.classSection.get(newAssignment.classSection.size()-1).sendKeys("family");
            try {
                boolean isPresent = newAssignment.shareWithClass.isDisplayed();
                Assert.assertEquals(isPresent, false, "Auto suggestion is displaying");
            }
            catch(Exception e)
            {}
            newAssignment.removeClass.get(newAssignment.removeClass.size()-1).click();

            newAssignment.removeClassSection.click();//remove class section
            newAssignment.classSection.get(0).sendKeys("family");
            Thread.sleep(2000);
            newAssignment.shareWithClass.click();

            new Navigator().NavigateTo("All Resources");

            assignmentTab.assignThisUnderResources.click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            //verify  link 'Asssign different dates'
            Assert.assertEquals(newAssignment.assignDifferentDates.getText(), "Assign different dates", "Assign different dates link is not present");
            newAssignment.helpIconAssignDifferentDates.click();
            Thread.sleep(500);
            Assert.assertEquals(newAssignment.helpMessage.getText(),"Use this to assign different dates for different class sections.","Help message not displaying");
            //upload new resource
            new UploadResources().uploadResources("10", true, false, true); //upload a resource
            Thread.sleep(3000);
            driver.findElement(By.cssSelector("span.assign-this")).click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
            newAssignment.helpIconAssignDifferentDates.click();
            Thread.sleep(500);
            Assert.assertEquals(newAssignment.helpMessage.getText(),"Use this to assign different dates for different class sections.","Help message not displaying");
            //verify  link 'Asssign different dates'
            Assert.assertEquals(newAssignment.assignDifferentDates.getText(), "Assign different dates", "Assign different dates link is not present");
            newAssignment.assignDifferentDates.click();
            Thread.sleep(1000);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
            //assign from lesson page
            new Navigator().NavigateTo("e-Textbook");
            tocSearch.hideTOC.click();
            lessonPage.getAssignForImage().click();
            driver.findElement(By.cssSelector("span.assign-this-text")).click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            Assert.assertEquals(newAssignment.shareWith.isDisplayed(),true,"Assign to field is not displaying");
            newAssignment.helpIconAssignDifferentDates.click();
            Thread.sleep(500);
            Assert.assertEquals(newAssignment.helpMessage.getText(),"Use this to assign different dates for different class sections.","Help message not displaying");
            //verify  link 'Asssign different dates'
            newAssignment.assignDifferentDates.click();
            Thread.sleep(1000);
            classSection=newAssignment.shareWith.getText();
            newAssignment.removeClassSection.click();//remove class section

            driver.findElement(By.className("maininput")).sendKeys(classSection);
            Thread.sleep(2000);
            newAssignment.shareWithClass.click();

            //CLICK ON ADD ROW
            newAssignment.addRow.click();
            Thread.sleep(1000);
            newAssignment.classSection.get(newAssignment.classSection.size()-1).sendKeys("family");
            try {
                boolean isPresent = newAssignment.shareWithClass.isDisplayed();
                Assert.assertEquals(isPresent, false, "Auto suggestion is displaying");
            }
            catch(Exception e)
            {}
            newAssignment.removeClass.get(newAssignment.removeClass.size()-1).click();

            newAssignment.removeClassSection.click();//remove class section
            newAssignment.classSection.get(0).sendKeys("family");
            Thread.sleep(2000);
            newAssignment.shareWithClass.click();

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class AssignDifferentDates in method assignDifferentDatesLink",e);
        }
    }

    @Test(priority =3,enabled = true)
    public void removeClassFuctionality()
    {
        try {
            int count=0;
            String classSection=null;
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "10");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "10");
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            TocSearch tocSearch=PageFactory.initElements(driver,TocSearch.class);
            new LoginUsingLTI().ltiLogin("70");
            new LoginUsingLTI().ltiLogin("71");
            new LoginUsingLTI().ltiLogin("68");
            new LoginUsingLTI().ltiLogin("69");

            new LoginUsingLTI().ltiLogin("68");
            new Navigator().NavigateTo("New Assignment");
            //click on Custom assignment
            newAssignment.createCustomAssignment.click();
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);//search question
            new AssignLesson().selectQuestionForCustomAssignment("10");//select question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);//Type name
            newAssignment.assignNowButton.click();//click on assign now button
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            newAssignment.assignDifferentDates.click();
            count=newAssignment.assignTo.size();
            newAssignment.addRow.click();//click on add row link
            if(count>=newAssignment.assignTo.size())
                Assert.fail("New row not added");
            newAssignment.removeClass.get(newAssignment.removeClass.size()-1).click();
            if(count!=newAssignment.assignTo.size())
                Assert.fail("Row not deleted");
            int counter=newAssignment.removeClass.size()-1;
            while(newAssignment.removeClass.size()-1>0)
            {
                newAssignment.removeClass.get(counter).click();
                counter--;
                Thread.sleep(1000);
            }
            if(1!=newAssignment.assignTo.size())
                Assert.fail("New row not added");



            new Navigator().NavigateTo("New Assignment");
            //click on file based assignment
            newAssignment.createFileBasedAssignment.click();
            newAssignment.assessmentNameTextBox.click();
            newAssignment.assessmentName_TextBox.sendKeys("File based assignment");
            Thread.sleep(1000);
            newAssignment.assignNowButton.click();//click on assign now button
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            newAssignment.assignDifferentDates.click();
            count=newAssignment.assignTo.size();
            newAssignment.addRow.click();//click on add row link
            if(count>=newAssignment.assignTo.size())
                Assert.fail("New row not added");
            newAssignment.removeClass.get(newAssignment.removeClass.size()-1).click();
            if(count!=newAssignment.assignTo.size())
                Assert.fail("Row not deleted");
            counter=newAssignment.removeClass.size()-1;
            while(newAssignment.removeClass.size()-1>0)
            {
                newAssignment.removeClass.get(counter).click();
                counter--;
                Thread.sleep(1000);
            }
            if(1!=newAssignment.assignTo.size())
                Assert.fail("New row not added");


            new Navigator().NavigateTo("New Assignment");
            newAssignment.getUsePreCreatedAssignment().click();
            Thread.sleep(6000);

            ListIterator<WebElement> listIt=myQuestionBank.getAssignThisList().listIterator();
            while(listIt.hasNext())
            {

                if(listIt.next().isDisplayed())

                {
                    listIt.next().click();
                    break;
                }

            }
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            newAssignment.assignDifferentDates.click();
            count=newAssignment.assignTo.size();
            newAssignment.addRow.click();//click on add row link
            if(count>=newAssignment.assignTo.size())
                Assert.fail("New row not added");
            newAssignment.removeClass.get(newAssignment.removeClass.size()-1).click();
            if(count!=newAssignment.assignTo.size())
                Assert.fail("Row not deleted");
            counter=newAssignment.removeClass.size()-1;
            while(newAssignment.removeClass.size()-1>0)
            {
                newAssignment.removeClass.get(counter).click();
                counter--;
                Thread.sleep(1000);
            }
            if(1!=newAssignment.assignTo.size())
                Assert.fail("New row not added");


            new Navigator().NavigateTo("New Assignment");
            newAssignment.getUsePreCreatedAssignment().click();
            //new UIElement().waitAndFindElement(assignmentTab.addToMyQuestionBank);
            assignmentTab.addToMyQuestionBank.click();
            new Navigator().NavigateTo("My Question Bank"); //navigate to Question Banks
            Thread.sleep(3000);
            myQuestionBank.getAssignThis().click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            new Navigator().NavigateTo("e-Textbook");
            newAssignment.assignAllLessons.click();
            Thread.sleep(3000);
            myQuestionBank.getAssignThis().click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            newAssignment.assignDifferentDates.click();
            count=newAssignment.assignTo.size();
            newAssignment.addRow.click();//click on add row link
            if(count>=newAssignment.assignTo.size())
                Assert.fail("New row not added");
            newAssignment.removeClass.get(newAssignment.removeClass.size()-1).click();
            if(count!=newAssignment.assignTo.size())
                Assert.fail("Row not deleted");
             counter=newAssignment.removeClass.size()-1;
            while(newAssignment.removeClass.size()-1>0)
            {
                newAssignment.removeClass.get(counter).click();
                counter--;
                Thread.sleep(1000);
            }
            if(1!=newAssignment.assignTo.size())
                Assert.fail("New row not added");


            new Navigator().NavigateTo("e-Textbook");
            new UIElement().waitAndFindElement(lessonPage.closeButton);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", lessonPage.closeButton);;
            new Navigator().navigateToTab("Assignments");
            assignmentTab.rightArrow.click();
            assignmentTab.assignThis.click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            new Navigator().NavigateTo("All Resources");
            ListIterator<WebElement> listIt1=assignmentTab.assignThisUnderResourcesList.listIterator();
            while(listIt1.hasNext())
            {

                if(listIt1.next().isDisplayed())

                {
                    listIt1.next().click();
                    break;
                }

            }

            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            newAssignment.assignDifferentDates.click();
            Thread.sleep(2000);
            count=newAssignment.assignTo.size();
            System.out.println("count:"+count);
            newAssignment.addRow.click();//click on add row link
            if(count>=newAssignment.assignTo.size())
                Assert.fail("New row not added");
            newAssignment.removeClass.get(newAssignment.removeClass.size()-1).click();
            if(count!=newAssignment.assignTo.size())
                Assert.fail("Row not deleted");
            counter=newAssignment.removeClass.size()-1;
            while(newAssignment.removeClass.size()-1>0)
            {
                newAssignment.removeClass.get(counter).click();
                counter--;
                Thread.sleep(1000);
            }
            if(1!=newAssignment.assignTo.size())
                Assert.fail("New row not added");


            //verify  link 'Asssign different dates'
            //assign from lesson page
            new Navigator().NavigateTo("e-Textbook");
            tocSearch.hideTOC.click();
            lessonPage.getAssignForImage().click();
            driver.findElement(By.cssSelector("span.assign-this-text")).click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            newAssignment.assignDifferentDates.click();
            count=newAssignment.assignTo.size();
            newAssignment.addRow.click();//click on add row link
            if(count>=newAssignment.assignTo.size())
                Assert.fail("New row not added");
            newAssignment.removeClass.get(newAssignment.removeClass.size()-1).click();
            if(count!=newAssignment.assignTo.size())
                Assert.fail("Row not deleted");
             counter=newAssignment.removeClass.size()-1;
            while(newAssignment.removeClass.size()-1>0)
            {
                newAssignment.removeClass.get(counter).click();
                counter--;
                Thread.sleep(1000);
            }
            if(1!=newAssignment.assignTo.size())
                Assert.fail("New row not added");



        }
        catch (Exception e)
        {
            Assert.fail("Exception in class AssignDifferentDates in method assignDifferentDatesLink",e);
        }
    }

    @Test(priority =4,enabled = true)
    public void dateTimeFuctionality()
    {
        try {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
            SimpleDateFormat timeFormat= new SimpleDateFormat("hh:mm a");
            int count=0;
            int counter=0;
            String classSection=null;
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "10");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "10");
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            TocSearch tocSearch=PageFactory.initElements(driver,TocSearch.class);
            new LoginUsingLTI().ltiLogin("93");
            new LoginUsingLTI().ltiLogin("91");
            new LoginUsingLTI().ltiLogin("92");
            new LoginUsingLTI().ltiLogin("91");
            new Navigator().NavigateTo("New Assignment");
            //click on Custom assignment
            newAssignment.createCustomAssignment.click();
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);//search question
            new AssignLesson().selectQuestionForCustomAssignment("10");//select question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);//Type name
            newAssignment.assignNowButton.click();//click on assign now button
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            newAssignment.assignDifferentDates.click();
            ListIterator<WebElement> listIt=newAssignment.datePickerAssignDifferentDates.listIterator();
            while(listIt.hasNext())
            {
                String datePicker=listIt.next().getAttribute("value");
                if(!datePicker.equals(dateFormat.format(date)))
                    Assert.fail("Not displaying current date");
            }
            new Navigator().NavigateTo("New Assignment");
            //click on file based assignment
            newAssignment.createFileBasedAssignment.click();
            newAssignment.assessmentNameTextBox.click();
            newAssignment.assessmentName_TextBox.sendKeys("File based assignment");
            Thread.sleep(1000);
            newAssignment.assignNowButton.click();//click on assign now button
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            newAssignment.assignDifferentDates.click();
            listIt=newAssignment.datePickerAssignDifferentDates.listIterator();
            while(listIt.hasNext())
            {
                String datePicker=listIt.next().getAttribute("value");
                if(!datePicker.equals(dateFormat.format(date)))
                    Assert.fail("Not displaying current date");
            }
            new Navigator().NavigateTo("New Assignment");
            newAssignment.getUsePreCreatedAssignment().click();
            new UIElement().waitAndFindElement(assignmentTab.addToMyQuestionBank);
            Thread.sleep(3000);
            myQuestionBank.getAssignThis().click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            newAssignment.assignDifferentDates.click();
            listIt=newAssignment.datePickerAssignDifferentDates.listIterator();
            while(listIt.hasNext())
            {
                String datePicker=listIt.next().getAttribute("value");
                if(!datePicker.equals(dateFormat.format(date)))
                    Assert.fail("Not displaying current date");
            }

            new Navigator().NavigateTo("New Assignment");
            newAssignment.getUsePreCreatedAssignment().click();
            new UIElement().waitAndFindElement(assignmentTab.addToMyQuestionBank);
            assignmentTab.addToMyQuestionBank.click();
            new Navigator().NavigateTo("My Question Bank"); //navigate to Question Banks
            Thread.sleep(3000);
            myQuestionBank.getAssignThis().click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            new Navigator().NavigateTo("e-Textbook");
            newAssignment.assignAllLessons.click();
            Thread.sleep(3000);
            myQuestionBank.getAssignThis().click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            newAssignment.assignDifferentDates.click();
            listIt=newAssignment.datePickerAssignDifferentDates.listIterator();
            while(listIt.hasNext())
            {
                String datePicker=listIt.next().getAttribute("value");
                if(!datePicker.equals(dateFormat.format(date)))
                    Assert.fail("Not displaying current date");
            }
            new Navigator().NavigateTo("e-Textbook");
            new UIElement().waitAndFindElement(lessonPage.closeButton);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", lessonPage.closeButton);;
            new Navigator().navigateToTab("Assignments");
            assignmentTab.rightArrow.click();
            assignmentTab.assignThis.click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            new Navigator().NavigateTo("All Resources");

            assignmentTab.assignThisUnderResources.click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            newAssignment.assignDifferentDates.click();
            listIt=newAssignment.datePickerAssignDifferentDates.listIterator();
            while(listIt.hasNext())
            {
                String datePicker=listIt.next().getAttribute("value");
                if(!datePicker.equals(dateFormat.format(date)))
                    Assert.fail("Not displaying current date");
            }


            //verify  link 'Asssign different dates'
            //assign from lesson page
            new Navigator().NavigateTo("e-Textbook");
            tocSearch.hideTOC.click();
            lessonPage.getAssignForImage().click();
            driver.findElement(By.cssSelector("span.assign-this-text")).click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            newAssignment.assignDifferentDates.click();
            listIt=newAssignment.datePickerAssignDifferentDates.listIterator();
            while(listIt.hasNext())
            {
                String datePicker=listIt.next().getAttribute("value");
                if(!datePicker.equals(dateFormat.format(date)))
                    Assert.fail("Not displaying current date");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in class AssignDifferentDates in method assignDifferentDatesLink",e);
        }
    }



    @Test(priority =5,enabled = true)
    public void dateUpdatedAssignDifferentDates()
    {
        try {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM yyyy");

            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "10");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "10");
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            TocSearch tocSearch=PageFactory.initElements(driver,TocSearch.class);
            new LoginUsingLTI().ltiLogin("93");
            new LoginUsingLTI().ltiLogin("91");
            new LoginUsingLTI().ltiLogin("92");
            new LoginUsingLTI().ltiLogin("91");
            new Navigator().NavigateTo("New Assignment");
            //click on Custom assignment
            newAssignment.createCustomAssignment.click();
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);//search question
            new AssignLesson().selectQuestionForCustomAssignment("10");//select question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);//Type name
            newAssignment.assignNowButton.click();//click on assign now button
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            newAssignment.dueDate.click();
            new UIElement().waitAndFindElement(By.linkText("1"));
            driver.findElement(By.linkText("1")).click();
            newAssignment.dueTime.click();
            driver.findElement(By.xpath("//li[text()='12:00 AM']")).click();
            newAssignment.assignDifferentDates.click();
            ListIterator<WebElement> listIt=newAssignment.dueDateClassWise.listIterator();




            listIt=newAssignment.datePickerAssignDifferentDates.listIterator();
            while(listIt.hasNext())
            {
                String datePicker=listIt.next().getAttribute("value");
                if(!datePicker.contains(dateFormat.format(date)))
                    Assert.fail("Not displaying current date");
            }
            new Navigator().NavigateTo("New Assignment");
            //click on file based assignment
            newAssignment.createFileBasedAssignment.click();
            newAssignment.assessmentNameTextBox.click();
            newAssignment.assessmentName_TextBox.sendKeys("File based assignment");
            Thread.sleep(1000);
            newAssignment.assignNowButton.click();//click on assign now button
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            newAssignment.assignDifferentDates.click();
            listIt=newAssignment.datePickerAssignDifferentDates.listIterator();
            while(listIt.hasNext())
            {
                String datePicker=listIt.next().getAttribute("value");
                if(!datePicker.contains(dateFormat.format(date)))
                    Assert.fail("Not displaying current date");
            }
            new Navigator().NavigateTo("New Assignment");
            newAssignment.getUsePreCreatedAssignment().click();
            Thread.sleep(3000);

            ListIterator<WebElement> listIt1=myQuestionBank.getAssignThisList().listIterator();
            while(listIt1.hasNext())
            {

                if(listIt1.next().isDisplayed())

                {
                    listIt1.next().click();
                    break;
                }

            }
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            newAssignment.assignDifferentDates.click();
            listIt1=newAssignment.datePickerAssignDifferentDates.listIterator();
            while(listIt1.hasNext())
            {
                String datePicker=listIt1.next().getAttribute("value");
                if(!datePicker.contains(dateFormat.format(date)))
                    Assert.fail("Not displaying current date");
            }

            new Navigator().NavigateTo("New Assignment");
            newAssignment.getUsePreCreatedAssignment().click();
            new UIElement().waitAndFindElement(assignmentTab.addToMyQuestionBank);
            assignmentTab.addToMyQuestionBank.click();
            new Navigator().NavigateTo("My Question Bank"); //navigate to Question Banks
            Thread.sleep(3000);
            myQuestionBank.getAssignThis().click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            new Navigator().NavigateTo("e-Textbook");
            newAssignment.assignAllLessons.click();
            Thread.sleep(3000);
            myQuestionBank.getAssignThis().click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            newAssignment.assignDifferentDates.click();
            listIt1=newAssignment.datePickerAssignDifferentDates.listIterator();
            while(listIt1.hasNext())
            {
                String datePicker=listIt1.next().getAttribute("value");
                if(!datePicker.contains(dateFormat.format(date)))
                    Assert.fail("Not displaying current date");
            }
            new Navigator().NavigateTo("e-Textbook");
            new UIElement().waitAndFindElement(lessonPage.closeButton);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", lessonPage.closeButton);;
            new Navigator().navigateToTab("Assignments");
            assignmentTab.rightArrow.click();
            assignmentTab.assignThis.click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            new Navigator().NavigateTo("All Resources");

            assignmentTab.assignThisUnderResources.click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            newAssignment.assignDifferentDates.click();
            listIt1=newAssignment.datePickerAssignDifferentDates.listIterator();
            while(listIt1.hasNext())
            {
                String datePicker=listIt1.next().getAttribute("value");
                if(!datePicker.contains(dateFormat.format(date)))
                    Assert.fail("Not displaying current date");
            }


            //verify  link 'Asssign different dates'
            //assign from lesson page
            new Navigator().NavigateTo("e-Textbook");
            tocSearch.hideTOC.click();
            lessonPage.getAssignForImage().click();
            driver.findElement(By.cssSelector("span.assign-this-text")).click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            newAssignment.assignDifferentDates.click();
            listIt1=newAssignment.datePickerAssignDifferentDates.listIterator();
            while(listIt1.hasNext())
            {
                String datePicker=listIt1.next().getAttribute("value");
                if(!datePicker.contains(dateFormat.format(date)))
                    Assert.fail("Not displaying current date");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in class AssignDifferentDates in method assignDifferentDatesLink",e);
        }
    }

    @Test(priority =6,enabled = true)
    public void verifyUpdateAssignmentLinkOnCurrentAssignment()
    {
        try {
            Date date = new Date();
            Calendar calendar=Calendar.getInstance();
            String month =  new SimpleDateFormat("MMMM").format(new Date());
            SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
            String duedate=null;

            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "10");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "10");
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            TocSearch tocSearch=PageFactory.initElements(driver,TocSearch.class);
            CurrentAssignments currentAssignment=PageFactory.initElements(driver, CurrentAssignments.class);

            new LoginUsingLTI().ltiLogin("108");
            new Navigator().NavigateTo("New Assignment");
            //click on Custom assignment
            newAssignment.createCustomAssignment.click();
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);//search question
            new AssignLesson().selectQuestionForCustomAssignment("10");//select question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);//Type name
            newAssignment.assignNowButton.click();//click on assign now button
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            new AssignLesson().assignTOCWithDefaultClassSection(108);//assign toclass using default value
            //fetch due date from ui
            int year = Integer.parseInt(yearFormat.format(date));
            System.out.println("Year: "+ year);
            duedate=new Calender().getCurrentMonthName(new Calender().getCurrentMonthIndex(month)).substring(0,3)+" 01, "+ (year+1)+", 12:00 AM";
            Assert.assertEquals(currentAssignment.getOriginalDueDate().getText(),duedate,"Due date dose not match as per assign page");//assert due date
            currentAssignment.updateAssignment_link.get(0).click();//click on update link
            new UIElement().waitAndFindElement(currentAssignment.reAssign_link);//wait for reassign link
            currentAssignment.reAssign_link.click();//click on reassign link
            Assert.assertEquals(newAssignment.assignDifferentDatesList.size(),0,"Assign different dates is present on screen");//validate assign different date should not present on UI
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignments
            new UIElement().waitAndFindElement(currentAssignment.getUnAssignButtonOfVersionAssignment());
            currentAssignment.getUnAssignButtonOfVersionAssignment().click();//click on unassign button
            new UIElement().waitAndFindElement(currentAssignment.getNoForUnAssign_button());
            //validate alert message on Pop up
            Assert.assertEquals(currentAssignment.getUnAssignAssignment_popup().getText(),"You are un-assigning the assignment for ALL students. Are you sure?","Notification on un assign Current assignment");
            currentAssignment.getCloseForUnAssign_icon().click();//click on close icon
            Thread.sleep(1000);
            currentAssignment.getUnAssignButtonOfVersionAssignment().click();//click on unassign button
            new UIElement().waitAndFindElement(currentAssignment.getNoForUnAssign_button());
            //validate alert message on Pop up
            Assert.assertEquals(currentAssignment.getUnAssignAssignment_popup().getText(),"You are un-assigning the assignment for ALL students. Are you sure?","Notification on un assign Current assignment");
            currentAssignment.getYesOnUnAssignPopUp().click();//click on yes button
            Thread.sleep(2000);
            //validate text 'No assignment exits.'
            Assert.assertEquals(currentAssignment.noAssignmentExit.getText(),"No Assignment exists.","Assignment not un assigned");

            new Navigator().NavigateTo("New Assignment");
            //click on file based assignment
            newAssignment.createFileBasedAssignment.click();
            newAssignment.assessmentNameTextBox.click();
            newAssignment.assessmentName_TextBox.sendKeys("File based assignment");
            Thread.sleep(1000);
            newAssignment.assignNowButton.click();//click on assign now button
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            new AssignLesson().assignTOCWithDefaultClassSection(108);//assign toclass using default value
            //fetch due date from ui
            duedate=new Calender().getCurrentMonthName(new Calender().getCurrentMonthIndex(month)).substring(0,3)+" 01, "+ (year+1)+", 12:00 AM";
            Assert.assertEquals(currentAssignment.getOriginalDueDate().getText(),duedate,"Due date dose not match as per assign page");//assert due date
            currentAssignment.updateAssignment_link.get(0).click();//click on update link
            new UIElement().waitAndFindElement(currentAssignment.reAssign_link);//wait for reassign link
            Assert.assertEquals(newAssignment.assignDifferentDatesList.size(),0,"Assign different dates is present on screen");//validate assign different date should not present on UI
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignments
            new UIElement().waitAndFindElement(currentAssignment.getUnAssignButtonOfVersionAssignment());
            currentAssignment.getUnAssignButtonOfVersionAssignment().click();//click on unassign button
            new UIElement().waitAndFindElement(currentAssignment.getNoForUnAssign_button());
            //validate alert message on Pop up
            Assert.assertEquals(currentAssignment.getUnAssignAssignment_popup().getText(),"You are un-assigning the assignment for ALL students. Are you sure?","Notification on un assign Current assignment");
            currentAssignment.getCloseForUnAssign_icon().click();//click on close icon
            Thread.sleep(1000);
            currentAssignment.getUnAssignButtonOfVersionAssignment().click();//click on unassign button
            new UIElement().waitAndFindElement(currentAssignment.getNoForUnAssign_button());
            //validate alert message on Pop up
            Assert.assertEquals(currentAssignment.getUnAssignAssignment_popup().getText(),"You are un-assigning the assignment for ALL students. Are you sure?","Notification on un assign Current assignment");
            currentAssignment.getYesOnUnAssignPopUp().click();//click on yes button
            Thread.sleep(2000);
            //validate text 'No assignment exits.'
            Assert.assertEquals(currentAssignment.noAssignmentExit.getText(),"No Assignment exists.","Assignment not un assigned");

            new Navigator().NavigateTo("New Assignment");
            newAssignment.getUsePreCreatedAssignment().click();
            new UIElement().waitAndFindElement(assignmentTab.addToMyQuestionBank);
            Thread.sleep(3000);
            Iterator<WebElement> itr=myQuestionBank.getAssignThisList().iterator();
            while(itr.hasNext())
            {
                if(itr.next().isDisplayed())
                {
                    itr.next().click();
                    break;
                }
            }
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            new AssignLesson().assignTOCWithDefaultClassSection(108);//assign toclass using default value
            //fetch due date from ui
            duedate=new Calender().getCurrentMonthName(new Calender().getCurrentMonthIndex(month)).substring(0,3)+" 01, "+ (year+1)+", 12:00 AM";
            Assert.assertEquals(currentAssignment.getOriginalDueDate().getText(),duedate,"Due date dose not match as per assign page");//assert due date
            currentAssignment.updateAssignment_link.get(0).click();//click on update link
            new UIElement().waitAndFindElement(currentAssignment.reAssign_link);//wait for reassign link
            currentAssignment.reAssign_link.click();
            Assert.assertEquals(newAssignment.assignDifferentDatesList.size(),0,"Assign different dates is present on screen");//validate assign different date should not present on UI
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignments
            new UIElement().waitAndFindElement(currentAssignment.getUnAssignButtonOfVersionAssignment());
            currentAssignment.getUnAssignButtonOfVersionAssignment().click();//click on unassign button
            new UIElement().waitAndFindElement(currentAssignment.getNoForUnAssign_button());
            //validate alert message on Pop up
            Assert.assertEquals(currentAssignment.getUnAssignAssignment_popup().getText(),"You are un-assigning the assignment for ALL students. Are you sure?","Notification on un assign Current assignment");
            currentAssignment.getCloseForUnAssign_icon().click();//click on close icon
            Thread.sleep(1000);
            currentAssignment.getUnAssignButtonOfVersionAssignment().click();//click on unassign button
            new UIElement().waitAndFindElement(currentAssignment.getNoForUnAssign_button());
            //validate alert message on Pop up
            Assert.assertEquals(currentAssignment.getUnAssignAssignment_popup().getText(),"You are un-assigning the assignment for ALL students. Are you sure?","Notification on un assign Current assignment");
            currentAssignment.getYesOnUnAssignPopUp().click();//click on yes button
            Thread.sleep(2000);
            //validate text 'No assignment exits.'
            Assert.assertEquals(currentAssignment.noAssignmentExit.getText(),"No Assignment exists.","Assignment not un assigned");

            new Navigator().NavigateTo("New Assignment");
            newAssignment.getUsePreCreatedAssignment().click();
            new UIElement().waitAndFindElement(assignmentTab.addToMyQuestionBank);
            assignmentTab.addToMyQuestionBank.click();
            new Navigator().NavigateTo("My Question Bank"); //navigate to Question Banks
            Thread.sleep(3000);
            myQuestionBank.getAssignThis().click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            new AssignLesson().assignTOCWithDefaultClassSection(108);//assign toclass using default value
            //fetch due date from ui
            duedate=new Calender().getCurrentMonthName(new Calender().getCurrentMonthIndex(month)).substring(0,3)+" 01, "+ (year+1)+", 12:00 AM";
            Assert.assertEquals(currentAssignment.getOriginalDueDate().getText(),duedate,"Due date dose not match as per assign page");//assert due date
            currentAssignment.updateAssignment_link.get(0).click();//click on update link
            new UIElement().waitAndFindElement(currentAssignment.reAssign_link);//wait for reassign link
            currentAssignment.reAssign_link.click();
            Assert.assertEquals(newAssignment.assignDifferentDatesList.size(),0,"Assign different dates is present on screen");//validate assign different date should not present on UI
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignments
            new UIElement().waitAndFindElement(currentAssignment.getUnAssignButtonOfVersionAssignment());
            currentAssignment.getUnAssignButtonOfVersionAssignment().click();//click on unassign button
            new UIElement().waitAndFindElement(currentAssignment.getNoForUnAssign_button());
            //validate alert message on Pop up
            Assert.assertEquals(currentAssignment.getUnAssignAssignment_popup().getText(),"You are un-assigning the assignment for ALL students. Are you sure?","Notification on un assign Current assignment");
            currentAssignment.getCloseForUnAssign_icon().click();//click on close icon
            Thread.sleep(1000);
            currentAssignment.getUnAssignButtonOfVersionAssignment().click();//click on unassign button
            new UIElement().waitAndFindElement(currentAssignment.getNoForUnAssign_button());
            //validate alert message on Pop up
            Assert.assertEquals(currentAssignment.getUnAssignAssignment_popup().getText(),"You are un-assigning the assignment for ALL students. Are you sure?","Notification on un assign Current assignment");
            currentAssignment.getYesOnUnAssignPopUp().click();//click on yes button
            Thread.sleep(2000);
            //validate text 'No assignment exits.'
            Assert.assertEquals(currentAssignment.noAssignmentExit.getText(),"No Assignment exists.","Assignment not un assigned");
            new Navigator().NavigateTo("e-Textbook");
            Thread.sleep(6000);
            newAssignment.assignAllLessons.click();
            Thread.sleep(3000);
            new Click().clickBycssselector("span[class='btn sty-green submit-assign']");
            //myQuestionBank.getAssignOnPopUp().click();
            Thread.sleep(2000);
            new AssignLesson().assignTOCWithDefaultClassSection(108);//assign toclass using default value
            //fetch due date from ui
            Thread.sleep(4000);
            new Navigator().NavigateTo("e-Textbook");
            Thread.sleep(3000);
            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            Thread.sleep(3000);
            duedate=Calender.getCurrentMonthName(calendar.get(Calendar.MONTH)+1).substring(0,3)+" 01, "+yearFormat.format(date);
            Assert.assertEquals(currentAssignment.OriginalDueDate.getText(),duedate,"Due date dose not match as per assign page");//assert due date
            new Navigator().NavigateTo("Current Assignments");
            html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
            currentAssignment.updateAssignment_link.get(0).click();//click on update link
            new UIElement().waitAndFindElement(currentAssignment.reAssign_link);//wait for reassign link
           // currentAssignment.reAssign_link.click();//click on reassign link
            Assert.assertEquals(newAssignment.assignDifferentDatesList.size(),0,"Assign different dates is present on screen");//validate assign different date should not present on UI
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignments
            new UIElement().waitAndFindElement(currentAssignment.getUnAssignButtonOfVersionAssignment());
            currentAssignment.getUnAssignButtonOfVersionAssignment().click();//click on unassign button
            new UIElement().waitAndFindElement(currentAssignment.getNoForUnAssign_button());
            //validate alert message on Pop up
            Assert.assertEquals(currentAssignment.getUnAssignAssignment_popup().getText(),"You are un-assigning the assignment for ALL students. Are you sure?","Notification on un assign Current assignment");
            currentAssignment.getCloseForUnAssign_icon().click();//click on close icon
            Thread.sleep(1000);
            currentAssignment.getUnAssignButtonOfVersionAssignment().click();//click on unassign button
            new UIElement().waitAndFindElement(currentAssignment.getNoForUnAssign_button());
            //validate alert message on Pop up
            Assert.assertEquals(currentAssignment.getUnAssignAssignment_popup().getText(),"You are un-assigning the assignment for ALL students. Are you sure?","Notification on un assign Current assignment");
            currentAssignment.getYesOnUnAssignPopUp().click();//click on yes button
            Thread.sleep(2000);
            //validate text 'No assignment exits.'
            Assert.assertEquals(currentAssignment.noAssignmentExit.getText(),"No Assignment exists.","Assignment not un assigned");
            new Navigator().NavigateTo("e-Textbook");
            new UIElement().waitAndFindElement(lessonPage.closeButton);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", lessonPage.closeButton);;
            new Navigator().navigateToTab("Assignments");
            assignmentTab.rightArrow.click();
            assignmentTab.assignThis.click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            new Navigator().NavigateTo("All Resources");

            assignmentTab.assignThisUnderResources.click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            new AssignLesson().assignTOCWithDefaultClassSection(108);//assign toclass using default value
            //fetch due date from ui
            duedate=new Calender().getCurrentMonthName(new Calender().getCurrentMonthIndex(month)).substring(0,3)+" 01, "+ (year+1)+", 12:00 AM";
            Assert.assertEquals(currentAssignment.getOriginalDueDate().getText(),duedate,"Due date dose not match as per assign page");//assert due date
            currentAssignment.updateAssignment_link.get(0).click();//click on update link
            new UIElement().waitAndFindElement(currentAssignment.reAssign_link);//wait for reassign link
         // currentAssignment.reAssign_link.click();//click on reassign link
            Assert.assertEquals(newAssignment.assignDifferentDatesList.size(),0,"Assign different dates is present on screen");//validate assign different date should not present on UI
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignments
            new UIElement().waitAndFindElement(currentAssignment.getUnAssignButtonOfVersionAssignment());
            currentAssignment.getUnAssignButtonOfVersionAssignment().click();//click on unassign button
            new UIElement().waitAndFindElement(currentAssignment.getNoForUnAssign_button());
            //validate alert message on Pop up
            Assert.assertEquals(currentAssignment.getUnAssignAssignment_popup().getText(),"You are un-assigning the assignment for ALL students. Are you sure?","Notification on un assign Current assignment");
            currentAssignment.getCloseForUnAssign_icon().click();//click on close icon
            Thread.sleep(1000);
            currentAssignment.getUnAssignButtonOfVersionAssignment().click();//click on unassign button
            new UIElement().waitAndFindElement(currentAssignment.getNoForUnAssign_button());
            //validate alert message on Pop up
            Assert.assertEquals(currentAssignment.getUnAssignAssignment_popup().getText(),"You are un-assigning the assignment for ALL students. Are you sure?","Notification on un assign Current assignment");
            currentAssignment.getYesOnUnAssignPopUp().click();//click on yes button
            Thread.sleep(2000);
            //validate text 'No assignment exits.'
            Assert.assertEquals(currentAssignment.noAssignmentExit.getText(),"No Assignment exists.","Assignment not un assigned");
            //verify  link 'Asssign different dates'
            //assign from lesson page
            new Navigator().NavigateTo("e-Textbook");
            tocSearch.hideTOC.click();
            lessonPage.getAssignForImage().click();
            driver.findElement(By.cssSelector("span.assign-this-text")).click();
            new UIElement().waitAndFindElement(myQuestionBank.cancelPopUp);
            new AssignLesson().assignTOCWithDefaultClassSection(108);//assign toclass using default value
            //fetch due date from ui
            duedate=Calender.getCurrentMonthName(calendar.get(Calendar.MONTH)+1).substring(0,3)+" 01, "+yearFormat.format(date)+", 12:00 AM";
            Assert.assertEquals(currentAssignment.date_AssignmentsTab.getText(),duedate,"Due date dose not match as per assign page");//assert due date*/
            new Navigator().NavigateTo("Current Assignments");
            currentAssignment.updateAssignment_link.get(0).click();//click on update link
            new UIElement().waitAndFindElement(currentAssignment.reAssign_link);//wait for reassign link
           // currentAssignment.reAssign_link.click();//click on reassign link
            Assert.assertEquals(newAssignment.assignDifferentDatesList.size(),0,"Assign different dates is present on screen");//validate assign different date should not present on UI
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignments
            new UIElement().waitAndFindElement(currentAssignment.getUnAssignButtonOfVersionAssignment());
            currentAssignment.getUnAssignButtonOfVersionAssignment().click();//click on unassign button
            new UIElement().waitAndFindElement(currentAssignment.getNoForUnAssign_button());
            //validate alert message on Pop up
            Assert.assertEquals(currentAssignment.getUnAssignAssignment_popup().getText(),"You are un-assigning the assignment for ALL students. Are you sure?","Notification on un assign Current assignment");
            currentAssignment.getCloseForUnAssign_icon().click();//click on close icon
            Thread.sleep(1000);
            currentAssignment.getUnAssignButtonOfVersionAssignment().click();//click on unassign button
            new UIElement().waitAndFindElement(currentAssignment.getNoForUnAssign_button());
            //validate alert message on Pop up
            Assert.assertEquals(currentAssignment.getUnAssignAssignment_popup().getText(),"You are un-assigning the assignment for ALL students. Are you sure?","Notification on un assign Current assignment");
            currentAssignment.getYesOnUnAssignPopUp().click();//click on yes button
            Thread.sleep(2000);
            //validate text 'No assignment exits.'
            Assert.assertEquals(currentAssignment.noAssignmentExit.getText(),"No Assignment exists.","Assignment not un assigned");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in class AssignDifferentDates in method assignDifferentDatesLink",e);
        }
    }

}
