package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.editpoints;

import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.preview.PreviewPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by pragyas on 13-07-2016.
 */
public class EditPoints extends Driver {

    @Test(priority = 1,enabled = true)
    public void editPointTrueFalse(){
        try{

            ReportUtil.log("Description", "This test case validates the edited points while true false question creation and points at student attempt screen view", "info");

            int dataIndex = 1;
            String appendChar = "a"+ StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            WebDriver driver = getWebDriver();

            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            String questiontext = "True False "+ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            PreviewPage previewPage = PageFactory.initElements(driver,PreviewPage.class);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().enterAndSelectSchool("98765496",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode =  new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Register student
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);
            new Assignment().create(dataIndex,"truefalse");//Create a true false question
            EditPointVerification.questionEditPointVerification(dataIndex,"2",questiontext,"True / False");

            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment to class
            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            //No point should be displayed at student attempt view

            new Navigator().navigateTo("assignment");//Navigate to assignment
            assignments.getAssessmentName().click();//Click on assignment

            WebDriverUtil.waitTillVisibilityOfElement(previewPage.button_next,60);
            boolean pointFoundAtStudentSide = new BooleanValue().presenceOfElementByWebElement(dataIndex,previewPage.getQuestionPoint_previewPage());
            CustomAssert.assertEquals(pointFoundAtStudentSide, false, "Verify the point on student attempt screen", "Point is not displayed as expected", "Point is displayed");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'editPointTrueFalse' in class 'EditPoints'", e);

        }
    }


    @Test(priority = 2,enabled = true)
    public void editPointMultipleChoice(){
        try{

            ReportUtil.log("Description", "This test case validates the edited points while multiple choice question creation and point at student attempt screen", "info");

            int dataIndex = 2;
            String appendChar = "a"+ StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            WebDriver driver = getWebDriver();

            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            String questiontext = "Multiple Choice "+ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            PreviewPage previewPage = PageFactory.initElements(driver,PreviewPage.class);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().enterAndSelectSchool("98765496",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode =  new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Register student
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);
            new Assignment().create(dataIndex,"multiplechoice");//Create a multiple choice question
            EditPointVerification.questionEditPointVerification(dataIndex,"3",questiontext,"Multiple Choice");

            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment to class
            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            //No point should be displayed at student attempt view

            new Navigator().navigateTo("assignment");//Navigate to assignment
            assignments.getAssessmentName().click();//Click on assignment

            WebDriverUtil.waitTillVisibilityOfElement(previewPage.button_next,60);
            boolean pointFoundAtStudentSide = new BooleanValue().presenceOfElementByWebElement(dataIndex,previewPage.getQuestionPoint_previewPage());
            CustomAssert.assertEquals(pointFoundAtStudentSide, false, "Verify the point on student attempt screen", "Point is not displayed as expected", "Point is displayed");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'editPointMultipleChoice' in class 'EditPoints'", e);

        }
    }


    @Test(priority = 3,enabled = true)
    public void editPointMultipleSelection(){
        try{

            ReportUtil.log("Description", "This test case validates the edited points while multiple selection question creation and point at student attempt screen", "info");

            int dataIndex = 3;
            String appendChar = "a"+ StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            WebDriver driver = getWebDriver();

            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            String questiontext = "Multi Selection "+ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            PreviewPage previewPage = PageFactory.initElements(driver,PreviewPage.class);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().enterAndSelectSchool("98765496",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode =  new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Register student
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);
            new Assignment().create(dataIndex,"multipleselection");//Create a multiple choice question
            EditPointVerification.questionEditPointVerification(dataIndex,"10",questiontext,"Multiple Selection");

            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment to class
            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            //No point should be displayed at student attempt view

            new Navigator().navigateTo("assignment");//Navigate to assignment
            assignments.getAssessmentName().click();//Click on assignment

            WebDriverUtil.waitTillVisibilityOfElement(previewPage.button_next,60);
            boolean pointFoundAtStudentSide = new BooleanValue().presenceOfElementByWebElement(dataIndex,previewPage.getQuestionPoint_previewPage());
            CustomAssert.assertEquals(pointFoundAtStudentSide, false, "Verify the point on student attempt screen", "Point is not displayed as expected", "Point is displayed");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'editPointMultipleSelection' in class 'EditPoints'", e);

        }
    }

    @Test(priority = 4,enabled = true)
    public void editPointTextEntry(){
        try{

            ReportUtil.log("Description", "This test case validates the edited points while text entry question creation and point at student attempt screen", "info");

            int dataIndex = 4;
            String appendChar = "a"+ StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            WebDriver driver = getWebDriver();

            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            String questiontext = "Text Entry "+ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            PreviewPage previewPage = PageFactory.initElements(driver,PreviewPage.class);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().enterAndSelectSchool("98765496",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode =  new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Register student
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);
            new Assignment().create(dataIndex,"textentry");//Create a multiple choice question
            EditPointVerification.questionEditPointVerification(dataIndex,"9",questiontext,"Text Entry");

            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment to class
            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            //No point should be displayed at student attempt view

            new Navigator().navigateTo("assignment");//Navigate to assignment
            assignments.getAssessmentName().click();//Click on assignment

            WebDriverUtil.waitTillVisibilityOfElement(previewPage.button_next,60);
            boolean pointFoundAtStudentSide = new BooleanValue().presenceOfElementByWebElement(dataIndex,previewPage.getQuestionPoint_previewPage());
            CustomAssert.assertEquals(pointFoundAtStudentSide, false, "Verify the point on student attempt screen", "Point is not displayed as expected", "Point is displayed");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'editPointTextEntry' in class 'EditPoints'", e);

        }
    }

    @Test(priority = 5,enabled = true)
    public void editPointTextDropDown(){
        try{

            ReportUtil.log("Description", "This test case validates the edited points while text drop down question creation and point at student attempt screen", "info");

            int dataIndex = 5;
            String appendChar = "a"+ StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            WebDriver driver = getWebDriver();

            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            String questiontext = "Text Drop Down "+ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            PreviewPage previewPage = PageFactory.initElements(driver,PreviewPage.class);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().enterAndSelectSchool("98765496",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode =  new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Register student
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);
            new Assignment().create(dataIndex,"textdropdown");//Create a multiple choice question
            EditPointVerification.questionEditPointVerification(dataIndex,"2",questiontext,"Text Drop Down");

            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment to class
            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            //No point should be displayed at student attempt view

            new Navigator().navigateTo("assignment");//Navigate to assignment
            assignments.getAssessmentName().click();//Click on assignment

            WebDriverUtil.waitTillVisibilityOfElement(previewPage.button_next,60);
            boolean pointFoundAtStudentSide = new BooleanValue().presenceOfElementByWebElement(dataIndex,previewPage.getQuestionPoint_previewPage());
            CustomAssert.assertEquals(pointFoundAtStudentSide, false, "Verify the point on student attempt screen", "Point is not displayed as expected", "Point is displayed");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'editPointTextDropDown' in class 'EditPoints'", e);

        }
    }


    @Test(priority = 6,enabled = true)
    public void editPointNumericEntryWithUnits(){
        try{

            ReportUtil.log("Description", "This test case validates the edited points while numeric entry with units question creation and point at student attempt screen", "info");

            int dataIndex = 6;
            String appendChar = "a"+ StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            WebDriver driver = getWebDriver();

            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            String questiontext = "Numeric Entry With Units "+ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            PreviewPage previewPage = PageFactory.initElements(driver,PreviewPage.class);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().enterAndSelectSchool("98765496",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode =  new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Register student
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);
            new Assignment().create(dataIndex,"numericentrywithunits");//Create a multiple choice question
            EditPointVerification.questionEditPointVerification(dataIndex,"7",questiontext,"Numeric Entry w/units");

            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment to class
            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            //No point should be displayed at student attempt view

            new Navigator().navigateTo("assignment");//Navigate to assignment
            assignments.getAssessmentName().click();//Click on assignment

            WebDriverUtil.waitTillVisibilityOfElement(previewPage.button_next,60);
            boolean pointFoundAtStudentSide = new BooleanValue().presenceOfElementByWebElement(dataIndex,previewPage.getQuestionPoint_previewPage());
            CustomAssert.assertEquals(pointFoundAtStudentSide, false, "Verify the point on student attempt screen", "Point is not displayed as expected", "Point is displayed");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'editPointNumericEntryWithUnits' in class 'EditPoints'", e);

        }
    }

    @Test(priority = 7,enabled = true)
    public void editPointNumeric(){
        try{

            ReportUtil.log("Description", "This test case validates the edited points while numeric question creation and point at student attempt screen", "info");

            int dataIndex = 7;
            String appendChar = "a"+ StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            WebDriver driver = getWebDriver();

            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            String questiontext = "Numeric Type "+ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            PreviewPage previewPage = PageFactory.initElements(driver,PreviewPage.class);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().enterAndSelectSchool("98765496",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode =  new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Register student
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);
            new Assignment().create(dataIndex,"numeric");//Create a multiple choice question
            EditPointVerification.questionEditPointVerification(dataIndex,"7",questiontext,"Numeric");

            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment to class
            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            //No point should be displayed at student attempt view

            new Navigator().navigateTo("assignment");//Navigate to assignment
            assignments.getAssessmentName().click();//Click on assignment

            WebDriverUtil.waitTillVisibilityOfElement(previewPage.button_next,60);
            boolean pointFoundAtStudentSide = new BooleanValue().presenceOfElementByWebElement(dataIndex,previewPage.getQuestionPoint_previewPage());
            CustomAssert.assertEquals(pointFoundAtStudentSide, false, "Verify the point on student attempt screen", "Point is not displayed as expected", "Point is displayed");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'editPointNumeric' in class 'EditPoints'", e);

        }
    }


    @Test(priority = 8,enabled = true)
    public void editPointMultipart(){
        try{

            ReportUtil.log("Description", "This test case validates the edited points while multipart question creation and point at student attempt screen", "info");

            int dataIndex = 8;
            String appendChar = "a"+ StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            WebDriver driver = getWebDriver();

            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            String questiontext = "Multipart : MultipleSelection Question   Multipart : MultipleChoice Question";
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            PreviewPage previewPage = PageFactory.initElements(driver,PreviewPage.class);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().enterAndSelectSchool("98765496",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode =  new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Register student
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);
            new Assignment().create(dataIndex,"multipart");//Create a multiple choice question
            EditPointVerification.questionEditPointVerification(dataIndex,"11",questiontext,"Multipart");

            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment to class
            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            //No point should be displayed at student attempt view

            new Navigator().navigateTo("assignment");//Navigate to assignment
            assignments.getAssessmentName().click();//Click on assignment

            WebDriverUtil.waitTillVisibilityOfElement(previewPage.button_next,60);
            boolean pointFoundAtStudentSide = new BooleanValue().presenceOfElementByWebElement(dataIndex,previewPage.getQuestionPoint_previewPage());
            CustomAssert.assertEquals(pointFoundAtStudentSide, false, "Verify the point on student attempt screen", "Point is not displayed as expected", "Point is displayed");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'editPointMultipart' in class 'EditPoints'", e);

        }
    }


    @Test(priority = 9,enabled = true)
    public void editPointGraphplotter(){
        try{

            ReportUtil.log("Description", "This test case validates the edited points while Graphplotter question creation and point at student attempt screen", "info");

            int dataIndex = 9;
            String appendChar = "a"+ StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            WebDriver driver = getWebDriver();

            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            String questiontext = "Graph Plotter "+ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            PreviewPage previewPage = PageFactory.initElements(driver,PreviewPage.class);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().enterAndSelectSchool("98765496",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode =  new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Register student
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);
            new Assignment().create(dataIndex,"graphplotter");//Create a graph plotter question
            EditPointVerification.questionEditPointVerification(dataIndex,"11",questiontext,"Graph Plotter");

            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment to class
            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            //No point should be displayed at student attempt view

            new Navigator().navigateTo("assignment");//Navigate to assignment
            assignments.getAssessmentName().click();//Click on assignment

            WebDriverUtil.waitTillVisibilityOfElement(previewPage.button_next,60);
            boolean pointFoundAtStudentSide = new BooleanValue().presenceOfElementByWebElement(dataIndex,previewPage.getQuestionPoint_previewPage());
            CustomAssert.assertEquals(pointFoundAtStudentSide, false, "Verify the point on student attempt screen", "Point is not displayed as expected", "Point is displayed");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'editPointGraphplotter' in class 'EditPoints'", e);

        }
    }

    @Test(priority = 10,enabled = true)
    public void editPointMatchthefollowing(){
        try{

            ReportUtil.log("Description", "This test case validates the edited points while Matchthefollowing question creation and point at student attempt screen", "info");

            int dataIndex = 10;
            String appendChar = "a"+ StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            WebDriver driver = getWebDriver();

            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            String questiontext = "Match the Following "+ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            PreviewPage previewPage = PageFactory.initElements(driver,PreviewPage.class);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().enterAndSelectSchool("98765496",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode =  new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Register student
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);
            new Assignment().create(dataIndex,"matchthefollowing");//Create a graph plotter question
            EditPointVerification.questionEditPointVerification(dataIndex,"6",questiontext,"Match the following");

            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment to class
            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            //No point should be displayed at student attempt view

            new Navigator().navigateTo("assignment");//Navigate to assignment
            assignments.getAssessmentName().click();//Click on assignment

            WebDriverUtil.waitTillVisibilityOfElement(previewPage.button_next,60);
            boolean pointFoundAtStudentSide = new BooleanValue().presenceOfElementByWebElement(dataIndex,previewPage.getQuestionPoint_previewPage());
            CustomAssert.assertEquals(pointFoundAtStudentSide, false, "Verify the point on student attempt screen", "Point is not displayed as expected", "Point is displayed");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'editPointMatchthefollowing' in class 'EditPoints'", e);

        }
    }

}
