package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.expressgrader;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.commonassessments.CommonAssessments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.detailedResponse.DetailedResponse;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.districtassessmentresponse.DistrictAssessmentResponse;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse.StudentResponse;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.ExplicitWait;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.KeysSend;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import gherkin.lexer.Th;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.List;

import static com.snapwiz.selenium.tests.staf.framework.controller.Driver.getWebDriver;

/**
 * Created by mukesh on 17/11/16.
 */
public class CharacteristicsOfExpressGrader extends Driver{

    Assessments assessments;
    MyAssessments myAssessments;
    AssessmentDetailsPage assessmentDetailsPage;
    AssignmentReview assignmentReview;
    Assignments assignments;
    StudentResponse studentResponse;
    AssignmentDetails assignmentDetails;
    AddQuestion addQuestion;
    SchoolPage schoolPage;
    String actual;
    String expected;

    @BeforeMethod
    public void init(){
        WebDriver driver= Driver.getWebDriver();
        assessments = PageFactory.initElements(driver, Assessments.class);
        myAssessments = PageFactory.initElements(driver,MyAssessments.class);;
        assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);;
        assignmentReview = PageFactory.initElements(driver,AssignmentReview.class);
        assignments = PageFactory.initElements(driver,Assignments.class);
        studentResponse = PageFactory.initElements(driver,StudentResponse.class);
        assignmentDetails = PageFactory.initElements(driver,AssignmentDetails.class);
        schoolPage = PageFactory.initElements(driver,SchoolPage.class);
        addQuestion = PageFactory.initElements(driver,AddQuestion.class);

    }


    @Test(priority = -1,enabled = true)
    public void createAssessment(){
        try{
            WebDriver driver = getWebDriver();
            int dataIndex = 1;
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            ReportUtil.log("Description", "This test case will create assessment", "info");

            String email = "testsa@snapwiz.com";

            new Login().loginAsDA(dataIndex, email); //login as District Admin
            new Assignment().createByDA(dataIndex, "multiplechoice");// includes true false and essay
            new Assignment().addQuestion(dataIndex, "multipleselection");
            new Assignment().addQuestion(dataIndex, "truefalse");
            new Assignment().addQuestion(dataIndex, "textentry");
            new Assignment().addQuestion(dataIndex, "numeric");
            new Assignment().addQuestion(dataIndex, "expressionevaluator");
            new Assignment().addQuestion(dataIndex, "multipart");
            new Assignment().addQuestion(dataIndex, "labelanimagedropdown");
            new Assignment().addQuestion(dataIndex, "matchingtables");
            new Assignment().addQuestion(dataIndex, "graphplotter");
            new Assignment().addQuestion(dataIndex, "matchthefollowing");

            new Common().waitForToastBlockerToClose(120);
            WebDriverUtil.clickOnElementUsingJavascript(addQuestion.reviewAssessment);
            WebDriverUtil.waitForAjax(getWebDriver(),60);
            assignmentReview.btn_AssignOrPublish.click();
            WebDriverUtil.waitForAjax(getWebDriver(),60);

            getWebDriver().findElement(By.cssSelector(".p-l-md>.radio>div")).click();
             WebDriverUtil.executeJavascript("$('#school+ins').click()"); //click on the ryan international
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#publish-to-district-btn"))); //click on the publish



        }catch(Exception e){
            Assert.fail("Exception in TC createAssessment of class CharacteristicsOfExpressGrader", e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void characteristicsOfExpressGrader(){
        try{
            WebDriver driver = getWebDriver();
            int dataIndex = 10;

            ReportUtil.log("Description", "This test case will verify characteristics of Express Grader tab", "info");

            String appendChar = "a" + StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA);
//            String appendChar = "aWU" ;

            System.out.println("appendChar:" + appendChar);

            StudentResponse studentResponse = PageFactory.initElements(driver, StudentResponse.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);


            new SignUp().teacher(appendChar, dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("654321369", dataIndex, false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode = new Classes().createClass(appendChar, dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, dataIndex);//Sign up as a student
            new Navigator().logout();//log out


            new Login().loginAsInstructor(appendChar, dataIndex);//Login as an instructor
            new Assignment().useExistingAssignment(dataIndex, appendChar);//Assign assessment to class created by DAAdmin i above test case
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.getAssessmentName().click();//Click on open
            new Navigator().logout();//log out


            new Login().loginAsStudent(appendChar, dataIndex);//Login as a student
            new Assignment().submitAssignment(dataIndex);//Submit assignment
            Thread.sleep(1000);
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            new Navigator().logout();


            new Login().loginAsInstructor(appendChar, dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view response
            Thread.sleep(1000);
            studentResponse.expressGraderTab.click();//click on express grader tab

            List<String> expectedCellData=new ArrayList<>();
            expectedCellData.add("A");expectedCellData.add("AB");expectedCellData.add("T");expectedCellData.add("Correct Answer");expectedCellData.add("3");
            expectedCellData.add("5");expectedCellData.add("TEI (2/2.00)");expectedCellData.add("TEI (1/1.00)");expectedCellData.add("TEI (1/1.00)");expectedCellData.add("TEI (1/1.00)");
            expectedCellData.add("TEI (1/1.00)");

            for (int i = 0; i <expectedCellData.size()-1 ; i++) {
                if(i==5){
                    WebDriverUtil.executeJavascript("document.getElementsByClassName('dataTables_scrollBody')[0].scrollLeft=600");
                    i=i+1;
                }
                System.out.println(studentResponse.cell_content.get(i).getText().trim());
                if(!studentResponse.cell_content.get(i).getText().trim().equals(expectedCellData.get(i))){
                    CustomAssert.fail("Verify cell data","Cell data are not according to what the student has selected");
                }
            }


            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view response
            Thread.sleep(1000);
            studentResponse.expressGraderTab.click();//click on express grader tab
            Thread.sleep(5000);
            WebDriverUtil.waitForAjax(driver,60);
            for (int i = 0; i <expectedCellData.size()-1 ; i++) {
                String mainWindow=driver.getWindowHandle();
                Actions action = new Actions(driver);
                action.moveToElement(studentResponse.cell_content.get(i)).click().build().perform();
                WebDriverUtil.switchToNewWindow();
                WebDriverUtil.waitTillVisibilityOfElement(studentResponse.express_response_close,60);
                WebDriverUtil.clickOnElementUsingJavascript(studentResponse.express_response_close);////click on the x icon
                driver.switchTo().window(mainWindow);
                Thread.sleep(5000);
            }

            Thread.sleep(4000);
            WebDriverUtil.clickOnElementUsingJavascript(studentResponse.label_student_name);// click on the student name


        }catch(Exception e){
            Assert.fail("Exception in TC characteristicsOfExpressGrader of class CharacteristicsOfExpressGrader", e);
        }
    }


    @Test(priority = 3,enabled = true)
    public void showAllClassSection(){
        try{


            ReportUtil.log("Description", "This test case will show all class section if student is part of different class section", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 12;
            int dataIndex1 = 13;
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            SchoolPage schoolPage = PageFactory.initElements(Driver.getWebDriver(), SchoolPage.class);
            DistrictAssessmentResponse districtAssessmentResponse = PageFactory.initElements(driver, DistrictAssessmentResponse.class);
            Assignments assignments = PageFactory.initElements(Driver.getWebDriver(), Assignments.class);

            String appendChar1 = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar2 = "b" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar3 = "c" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

           /* String appendChar1="aOca";
            String appendChar2="bzDb";
            String appendChar3="cOKb";*/

            System.out.println("appendChar1:"+appendChar1);
            System.out.println("appendChar2:"+appendChar2);
            System.out.println("appendChar3:"+appendChar3);

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String stuemail = methodName + "st" + appendChar3 + "@snapwiz.com";

            //Teacher signup
            new SignUp().teacher(appendChar1,dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("654321369",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode =  new Classes().createClass(appendChar1,dataIndex);//Create class
            System.out.println("classCode :" + classCode);
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar3,classCode,dataIndex);//Sign up as a student2
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);
            String classCode2 = new Classes().createNewClass(appendChar2,dataIndex1,"Grade 3","Mathematics","Math - Common Core");//Create a grade3 class
            System.out.println("classcode2:" +classCode2);
            new Assignment().addStudentFromManageClass(stuemail);
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);
            new Assignment().create(dataIndex,"truefalse");//Create an assignment2
            new Navigator().navigateTo("myAssessments");//Navigate to my assessment page
            WebDriverUtil.waitForAjax(driver,60);
            new WebDriverWait(driver,60).until(ExpectedConditions.invisibilityOfElementLocated(By.className("blockUI")));
            WebDriverUtil.waitForAjax(driver,60);
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.className("select2-search__field")));
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[contains(@id,'Grade 1')]")).click();


            int index = 0;
            if(myAssessments.assignmentList.size()==0)
            {
                driver.navigate().refresh();
                Thread.sleep(8000);
                WebDriverUtil.clickOnElementUsingJavascript(myAssessments.draft);//Click on Draft
                WebDriverUtil.waitForAjax(driver,60);
                if(myAssessments.assignmentList.size()==0){
                    driver.findElement(By.xpath("//input[@id='published']/..")).click(); //click on the publish
                    WebDriverUtil.waitForAjax(driver,60);
                    myAssessments.draft.click();//Click on Draft
                    WebDriverUtil.waitForAjax(driver,60);
                }
                Thread.sleep(9000);
                new Click().clickByXpath("//input[@id='draft']/..");//Click on Draft
            }


            List<WebElement> allAssessment = myAssessments.assignmentList;
            for (WebElement assessment : allAssessment)
            {
                if (assessment.getText().equals(assignmentname))
                    break;
                else
                    index++;

            }


            WebDriverUtil.clickOnElementUsingJavascript(myAssessments.assignmentList.get(index));//click on Assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignmentDetails.btnEdit);//Click on edit button

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("assessments-use-button")));//click on next button to assign
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@id='assign-now']/parent::div")));


            driver.findElement(By.className("maininput")).sendKeys(methodName+"st"+appendChar3);
            Thread.sleep(3000);
            List<WebElement> suggestname1=driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]"));
            for (WebElement name : suggestname1) {
                if (name.getText().trim().equals(methodName+"st"+appendChar3)) {
                    name.click();
                    Thread.sleep(4000);
                    //break;
                }
            }

            driver.findElement(By.className("maininput")).sendKeys(methodName+"class"+appendChar1);
            Thread.sleep(5000);
            try {
                new KeysSend().sendKeyBoardKeys("^");
                Thread.sleep(1000);
                new KeysSend().sendKeyBoardKeys("^");
                Thread.sleep(2000);
            } catch (Exception e) {

            }


            driver.findElement(By.id("assign-button")).click();  //click on the redirect button
            new Navigator().navigateTo("myAssessments");//Navigate to my assessment page
            new Navigator().logout();//log out


            new Login().loginAsStudent(appendChar1,dataIndex);//student 1 submitted
            new Assignment().submitAssignment(dataIndex);//Submit assignment
            new Navigator().navigateTo("assignment");
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar3,dataIndex);//student 1 submitted
            new Assignment().submitAssignment(dataIndex);//Submit assignment
            new Navigator().navigateTo("assignment");
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view response
            Thread.sleep(1000);
            studentResponse.expressGraderTab.click();//click on express grader tab
            Thread.sleep(3000);
            studentResponse.showAllClassSection_checkbok.click(); //click on the show all class section checkbox
            Thread.sleep(4000);
            CustomAssert.assertEquals(studentResponse.classSectionData.size(),3,"Verify all class section","Showing all class sections","Not showing all class sections");
            CustomAssert.assertEquals(studentResponse.classSectionData.get(0).getText().trim(),classCode,"Verify all class section","Showing all class sections","Not showing all class sections");
            CustomAssert.assertEquals(studentResponse.classSectionData.get(2).getText().trim(),classCode2,"Verify all class section","Showing all class sections","Not showing all class sections");

        }catch(Exception e){
            Assert.fail("Exception in TC showAllClassSection of class CharacteristicsOfExpressGrader", e);
        }
    }


    @Test(priority = 4,enabled = true)
    public void displayExpressGraderTabPremiumDistrict(){
        try {
            ReportUtil.log("Description", "This test case validates display of express grader tab for premium district.", "info");

            WebDriver driver = getWebDriver();
            int dataIndex = 1;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String appendChar1 = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar2 = "b" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar3 = "c" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            System.out.println("appendChar1:"+appendChar1);
            System.out.println("appendChar2:"+appendChar2);
            System.out.println("appendChar3:"+appendChar3);

            /*String appendChar1 = "aPjY";
            String appendChar2 = "bsQk";
            String appendChar3 = "cRIf";*/

            String email = "testsa@snapwiz.com";
            String methodName = new Exception().getStackTrace()[0].getMethodName();
            System.out.println(methodName);


            new SignUp().teacher(appendChar1, dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("654321369", dataIndex, false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode = new Classes().createClass(appendChar1, dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar1, classCode, dataIndex);//Sign up as a student
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar2, classCode, dataIndex);//Sign up as a student
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar3, classCode, dataIndex);//Sign up as a student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);
            new Assignment().useExistingAssignment(dataIndex,appendChar1);
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar2,dataIndex);
            new Assignment().submitAssignmentWithMixResponse(dataIndex,0,11);
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar3,dataIndex);
            new Assignment().submitAssignmentWithMixResponse(dataIndex,6,11);
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, dataIndex);
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view response
            Thread.sleep(1000);
            actual = studentResponse.liveClassBoardTab.getText();
            expected = "Live Class board";
            CustomAssert.assertEquals(actual, expected, "Verify live class board tab", "Live class board tab is displayed", "Live class board tab is not displayed");
            actual = studentResponse.expressGraderTab.getText();
            expected = "Express Grader";
            CustomAssert.assertEquals(actual, expected, "Verify express grader tab", "Express grader tab is displayed", "Express grader tab is not displayed");
            actual = studentResponse.liveClass_Tab.getAttribute("class");
            CustomAssert.assertEquals(actual, "active", "Verify default selected tab", "Live class tab is selected bydefault", "Live class tab is not selected by default");

            studentResponse.expressGraderTab.click();//click on express grader tab
            actual = studentResponse.allClassSection.getText();
            expected = "SHOW ALL CLASS SECTIONS";
            CustomAssert.assertEquals(actual, expected, "Verify class section option", "All class section option is displayed", "All class section option is not displayed");
            actual = studentResponse.questionStandards.getText();
            expected = "Questions & Standards";
            CustomAssert.assertEquals(actual, expected, "Verify student detail main header", "Header is as per expected", "Header is not as per expected");
            actual = studentResponse.questionPerformanceRow.get(0).getText();
            expected = "Student";
            CustomAssert.assertEquals(actual, expected, "Verify header as Student", "header as Student is displayed", "header as student is not displayed");
            actual = studentResponse.questionPerformanceRow.get(1).getText();
            expected = "Score";
            CustomAssert.assertEquals(actual, expected, "Verify header as Score", "header as Score is displayed", "header as Score is not displayed");
            WebDriverUtil.clickOnElementUsingJavascript(studentResponse.allClassSectionCheckBox);//click on all class section check box
            Thread.sleep(4000);
            actual = studentResponse.questionPerformanceRow.get(1).getText();
            expected = "Class";
            CustomAssert.assertEquals(actual, expected, "Verify header as Class", "header as Class is displayed", "header as Class is not displayed");

            List<String> students = new ArrayList<String>();
            List<WebElement> studentName = studentResponse.student_Name;
            for (WebElement we : studentName) {
                students.add(we.getText());
            }
            // System.out.println(students);
            boolean isSorted = true;
            for (int i = 0; i < students.size() - 1; i++) {
                if (students.get(i).compareTo(students.get(i + 1)) > 0) {
                    isSorted = false;
                    break;
                }
            }
            if (isSorted == false) {
                CustomAssert.fail("verify order", "Values are not in ascending order");
            } else {
                System.out.println("sorted");
            }

            WebDriverUtil.clickOnElementUsingJavascript(studentResponse.questionPerformanceRow.get(0));
            List<String> studentsdescending = new ArrayList<String>();
            List<WebElement> studentNamedescending = studentResponse.student_Name;
            for (WebElement we : studentNamedescending) {
                studentsdescending.add(we.getText());
            }
            boolean isdescending = true;
            for (int i = 0; i < students.size() - 1; i++) {
                if (studentsdescending.get(i).compareTo(studentsdescending.get(i + 1)) < 0) {
                    isdescending = false;
                    break;
                }
            }
            if (isdescending == false) {
                CustomAssert.fail("verify order", "Values are not in descending order");
            } else {
                System.out.println("descending");
            }

            if (!(studentResponse.scores.get(0).getText().equals("50%\n" + "( 6/12)")) && !(studentResponse.scores.get(1).getText().equals("0%\n" + "( 0/12)")) && !(studentResponse.scores.get(2).getText().equals("100%\n" + "( 12/12)"))) {
                CustomAssert.fail("Verify score of each student", "Scores are not as per expected");
            }

            WebDriverUtil.clickOnElementUsingJavascript(studentResponse.questionPerformanceRow.get(2));

            List<Integer> percentage = new ArrayList<Integer>();
            List<WebElement> allPercentage = studentResponse.percentageScore;
            for (WebElement we : allPercentage) {
                String str = we.getText();
                percentage.add(Integer.parseInt(we.getText().substring(0, str.indexOf("%"))));
            }
            System.out.println(percentage);

            boolean isScoreSorted = true;
            for (int i = 0; i < percentage.size() - 1; i++) {
                if (percentage.get(i) > percentage.get(i + 1)) {
                    isScoreSorted = false;
                    break;
                }
            }
            if (isScoreSorted == false) {
                CustomAssert.fail("verify order", "Values are not in ascending order");
            } else {
                System.out.println("ascending");
            }

            WebDriverUtil.clickOnElementUsingJavascript(studentResponse.questionPerformanceRow.get(2));

            List<Integer> percentageDescending = new ArrayList<Integer>();
            List<WebElement> scorePercentageDescending = studentResponse.percentageScore;

            for (WebElement we : scorePercentageDescending) {
                String str = we.getText();
                percentageDescending.add(Integer.parseInt(we.getText().substring(0, str.indexOf("%"))));
            }

            boolean isScoreDescending=true;
            for (int i = 0; i <percentageDescending.size()-1; i++) {
                if (percentageDescending.get(i)<percentageDescending.get(i+1))
                {
                    isScoreDescending = false;
                    break;
                }
            }
            if(isScoreDescending==false){
                CustomAssert.fail("verify order", "Values are not in descending order");
            }
            else{
                System.out.println("descending");
            }

            for(int i =1 ;i<=11;i++){
                if(studentResponse.questionBox.get(i).getAttribute("data-criteria").equals("")){
                    CustomAssert.fail("Verify question label","Question label is displayed");
                }
            }

            for(int i =0 ;i<5;i++){

                System.out.println(studentResponse.standards.get(i).getText());
                if(studentResponse.standards.get(i).getText().equals("")){
                    CustomAssert.fail("Verify standards","Standards are displayed");
                    JavascriptExecutor js = (JavascriptExecutor)driver;
                    js.executeScript("arguments[0].scrollIntoView()", studentResponse.questionBox.get(i));
                }
            }

            studentResponse.liveClass_Tab.click();//click on live class tab
            Thread.sleep(3000);
            studentResponse.checkboxInStudentCard.get(0).click();//click on student responses checkbox
            studentResponse.button_redirect.click();
            Thread.sleep(3000);
            driver.findElement(By.id("assign-button")).click();  //click on the redirect button
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            Thread.sleep(3000);
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar2,dataIndex);
            new Navigator().navigateTo("assignment");
            int index = 0;
            new ExplicitWait().explicitWaitByCssSelector("h4.as-title", 30);

            List<WebElement> allAssignment = driver.findElements(By.cssSelector("h4.as-title"));
            for (WebElement assignment : allAssignment) {

                if (assignment.getText().contains(assessmentname)) {
                    break;
                } else
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", assignment);
                index++;
            }
            allAssignment.get(index).click();
            Thread.sleep(15000);

            Thread.sleep(3000);
            for (int i =0 ;i<11;i++){
                driver.findElement(By.id("as-take-next-question")).click();//click on next
                Thread.sleep(5000);
            }
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[starts-with(@class,'btn sty-blue submit-button')]")));
            driver.findElement(By.xpath("//span[starts-with(@class,'btn sty-blue submit-button')]")).click();//click on Submit
            new Navigator().navigateTo("assignment");
            new Navigator().logout();//log out


            new Login().loginAsStudent(appendChar3,dataIndex);
            new Navigator().navigateTo("assignment");
            int index1 = 0;
            new ExplicitWait().explicitWaitByCssSelector("h4.as-title", 30);

            List<WebElement> allAssignment1 = driver.findElements(By.cssSelector("h4.as-title"));
            for (WebElement assignment : allAssignment1) {

                if (assignment.getText().contains(assessmentname)) {
                    break;
                } else
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", assignment);
                index1++;
            }
            allAssignment1.get(index1).click();
            Thread.sleep(15000);
            new Click().clickbylist("choice-value", 1);
            Thread.sleep(3000);
            for (int i =0 ;i<11;i++){
                driver.findElement(By.id("as-take-next-question")).click();//click on next
                Thread.sleep(5000);
            }
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[starts-with(@class,'btn sty-blue submit-button')]")));
            driver.findElement(By.xpath("//span[starts-with(@class,'btn sty-blue submit-button')]")).click();//click on Submit
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, dataIndex);
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view response
            Thread.sleep(1000);
            actual = studentResponse.scoreAndPercentageOnStudentCard.get(2).getText();
            System.out.println(studentResponse.scoreAndPercentageOnStudentCard.get(2).getText());
            if(!((actual.trim().equals("0/12"))||(actual.trim().equals("0.4/12")))) {
                CustomAssert.fail("Verify the score on student card","Score is not displayed as expected");
            }
            actual = studentResponse.scoreAndPercentageOnStudentCard.get(3).getText();
            if(!((actual.trim().equals("0%"))||(actual.trim().equals("3.33%")))) {
                CustomAssert.fail("Verify the score on student card","Score is not displayed as expected");
            }
            CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(4).getText(), "5/12", "Verify the score on student card", "Score is displayed as expected", "Score is not displayed as expected");
            CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(5).getText(), "41.67%", "Verify the score on student card", "Score is displayed as expected", "Score is not displayed as expected");




        }catch(Exception e){
            Assert.fail("Exception in TC displayExpressGraderTabPremiumDistrict of class CharacteristicsOfExpressGrader", e);
        }
    }


    @Test(priority = 5,enabled = true)
    public void questionAssociatedWithDifferentTlo(){
        try {
            ReportUtil.log("Description", "This test case validates display of express grader tab for premium district.", "info");
            WebDriver driver = getWebDriver();
            int dataIndex = 4;
            String appendChar1 = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar2 = "b" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar3 = "c" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            System.out.println("appendChar1:"+appendChar1);
            System.out.println("appendChar2:"+appendChar2);
            System.out.println("appendChar3:"+appendChar3);

            //String appendChar1 = "aVxx";
            String methodName = new Exception().getStackTrace()[0].getMethodName();
            System.out.println(methodName);

            new SignUp().teacher(appendChar1, dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("654321369", dataIndex, false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode = new Classes().createClass(appendChar1, dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar1, classCode, dataIndex);//Sign up as a student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);
            new Assignment().create(dataIndex,"truefalse");
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            new Assignment().assignToStudent(dataIndex,appendChar1);
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar1,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, dataIndex);
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view response
            Thread.sleep(1000);
            studentResponse.expressGraderTab.click();//click on express grader tab

            if(studentResponse.tlos.size()-1 !=6){
                CustomAssert.fail("Verify tlos","Tlos are displaying");
            }

        }catch(Exception e){
            Assert.fail("Exception in TC displayExpressGraderTabPremiumDistrict of class CharacteristicsOfExpressGrader", e);
        }
    }

}

