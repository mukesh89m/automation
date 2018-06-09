package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.assignmentcreationbyda;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Assignment;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Login;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Navigator;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.KeysSend;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Mukesh on 12/16/14.
 */
public class AssignmentCreationByDA extends Driver {
    @Test(priority = 1,enabled = true)
    public void assignmentCreationByDA()
    {
        try {

            //Tc row no 11
            //"1. Login as DA 2. Select a Grade and a Subject from the header 3. Select 'Assessments' from the side navigator dropdown "
            String email="daadmin@snapwiz.com";
            String teacherEmail="da1autoteacher@snapwiz.com ";
            int index=11;
            new Login().directLoginAsDATeacher(index, teacherEmail);
            new Navigator().logout();//logout
            new Login().loginAsDA(index, email); //login as District Admin
            new Navigator().selectGradeAndSubject(index);
            new Navigator().navigateTo("districtAssessments");//Navigate to assessment

            //Tc row no 12
            //4. Click on '+New assessment' button
            new Click().clickByid("new-assessment-button");//click on the New Assessment
            List<WebElement> assessmentField=driver.findElements(By.className("as-assignment-flow-link-title"));
            if(!assessmentField.get(0).getText().equals("Modify Existing Assessment"))
                Assert.fail("Modify Existing Assessment Field is not Present");
            if(!assessmentField.get(1).getText().equals("Create New Assessment"))
                Assert.fail("Create New Assessment Field is not Present");

            //Tc row no 13
            //5. Click on "Create New Assessment"
            assessmentField.get(1).click();//click on the create new Assignment

            new Click().clickByXpath("//div[@id='lsm-createAssignment-subject-dropDown']/select/option[contains(text(),'Math - Common Core')]");//click on the current subject
            new Click().clickByXpath(".//*[@id='lsm-createAssignment-grade-dropDown']/select/option[text()='Kindergarten']");//click on the current grade

            String ownerLabel=new TextFetch().textfetchbycssselector("span[class='lsm-createAssignment-owner-filter-title']");
            Assert.assertEquals(ownerLabel,"OWNER","OWNER label is not visible");
            String me=new TextFetch().textFetchByXpath("//span[.='Me']");
            Assert.assertEquals("Me",me,"Me label is not visible to the teacher");
            String community=new TextFetch().textFetchByXpath("//span[.='Community']");
            Assert.assertEquals("Community",community,"Community label is not  visible to the teacher");
            String standard=new TextFetch().textfetchbycssselector("span[class='lsm-createAssignment-chooseStd-text']");
            Assert.assertEquals(standard,"CHOOSE STANDARD","CHOOSE STANDARD is not visible to the teacher");
            //Tc row no 15
            int tloSize=driver.findElements(By.xpath("//ul[@class='lsm-createAssignment-all-standards-list']/li[text()]")).size();
            System.out.println("List of Tlos:"+tloSize);
            if(tloSize==0)
               Assert.fail("Choose standard' field with the list of all the TLOs is not visible");

            //Tc row no 16
            boolean isDisplayed= driver.findElement(By.xpath("//div[@class='lsm-tlo-info-wrapper']/span[text()='Auto Select']/following-sibling::span[text()='Select']")).isDisplayed();
            if(isDisplayed==false)
                Assert.fail("\"Auto select\" and \"select\" should not displayed for a TLO");

            boolean eloIsDisplayed=driver.findElement(By.xpath("(//span[@class='lsm-tlo-heading']//..)[1]//span[text()='Create']//following-sibling::span[text()='Auto Select']//following-sibling::span[text()='Select']")).isDisplayed();
            if(eloIsDisplayed==false)
                Assert.fail("\"Create\", \"Auto Select\" and \"Select\" is not displayed for respective ELOs");


        } catch (Exception e) {
            Assert.fail("Exception in test case assignmentCreationByDA of class AssignmentCreationByDA", e);
        }
    }
    @Test(priority = 2,enabled = true)
    public void clickOnCreateOfELo()
    {
        try {

            //Tc row no 17
            //"1. Login as DA 2. Select a Grade and a Subject from the header 3. Select 'Assessments' from the side navigator dropdown "
            String email="daadmin@snapwiz.com";
            int index=17;

            new Login().loginAsDA(index, email); //login as District Admin
            new Navigator().selectGradeAndSubject(index);
            new Navigator().navigateTo("districtAssessments");//Navigate to assessment
            new Navigator().clickOnCreateNewAssignment(index);//click on the new Create new Assignment
            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn']");//click on the create besides elos
            new Click().clickByid("qtn-true-false-type");//click on the true false question
            new TextSend().textsendbyid("sample true false question", "question-raw-content");
            new Click().clickByclassname("true-false-answer-select");
            new TextSend().textsendbyclass("assignmnet", "lsm-createAssignment-input-name");
            new Click().clickByid("saveQuestionDetails1");//click on the save button
            new Click().clickBycssselector("div[class='lsm-createAssignment-done selected']");//click on the review button
            new Click().clickByid("assessments-use-button");//click on the next button

            //Tc row no 18

            String shortLabel=new TextFetch().textfetchbyclass("lsm-short-label");
            Assert.assertEquals(shortLabel,"Short Label","Short Label field is not present");
            new TextSend().textsendbycssSelector("shor1","input[class='lsm-assignment-input total_point width50 lsm-assignment-short-label-input']");
            List<WebElement> totalPoint=driver.findElements(By.cssSelector("label[class='lsm-new-assignment-content-row width50']"));
              if(!totalPoint.get(0).getText().contains("Total points"))
                Assert.fail("Total points Text is not present");
            String point=new TextFetch().textfetchbycssselector("span[class='lsm-assignment-total-points total_point width50']");
            if(!point.equals("1"))
                Assert.fail("Total points is not present");
          //Tc row no 19 & 20
          //Additional Details' expansion link
          //"3. Description (optional)4. tag (Optional)  "
            new Click().clickBycssselector("i[class='lsm-new-assignment-addDetails-toggle lsm-new-assignment-expand-icon']");//click on the +icon
            String descriptionLabel=new TextFetch().textfetchbylistcssselector("span[class='width25 f-left']",0);
            if(!descriptionLabel.contains("Description"))
                Assert.fail("Description (optional) tag is not visible");
            String tag=new TextFetch().textfetchbylistcssselector("span[class='width25 f-left']",1);
            if(!tag.contains("Tag"))
                Assert.fail("tag (Optional) tag is not visible");

            //Tc row no 21
            boolean districtByDefault=driver.findElement(By.id("district")).isSelected();
            System.out.println("DistrictByDefault:"+districtByDefault);
            if(districtByDefault==false)
                Assert.fail("By Default District radio button is not selected");

            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("private")));//click on the private radio button
            Thread.sleep(2000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("public")));//click on the public radio button

            //Tc row no 22
            new Click().clickByid("share-assessment-button");//click on the share button
            String assignmentTitle=new TextFetch().textFetchByXpath("//div[text()='Assessments']");
            System.out.println("AssignmentTitle:"+assignmentTitle);

            Assert.assertEquals(assignmentTitle,"ASSESSMENTS","DA is not able to navigate the assignment page");
            new Click().clickByXpath("//div[contains(text(),'assignmnet')]");//click on the assignment name

        } catch (Exception e) {
            Assert.fail("Exception in test case clickOnCreateOfELo of class AssignmentCreationByDA", e);
        }
    }
    @Test(priority = 3,enabled = true)
    public void shareWithTeacherOfSameDistrict()
    {
        try {
            //Tc row no 23
            //"1. Login as DA 2. Select a Grade and a Subject from the header 3. Select 'Assessments' from the side navigator dropdown "
            String email="daadmin@snapwiz.com";
            String teacherEmail="da1autoteacher@snapwiz.com ";
            int index=23;
            new Login().directLoginAsDATeacher(index,teacherEmail);
            new Navigator().logout();//logout
            new Login().loginAsDA(index, email); //login as District Admin
            new Navigator().selectGradeAndSubject(index);
            new Navigator().navigateTo("districtAssessments");//Navigate to assessment
            new Navigator().clickOnCreateNewAssignment(index);//click on the new Create new Assignment
            frameQuestion(index);
            new TextSend().textsendbycssSelector("shor1","input[class='lsm-assignment-input total_point width50 lsm-assignment-short-label-input']");
            new Click().clickByid("share-assessment-button");//click on the share button
            String assignmentTitle=new TextFetch().textFetchByXpath("//div[text()='Assessments']");
            System.out.println("AssignmentTitle:"+assignmentTitle);
            Assert.assertEquals(assignmentTitle,"ASSESSMENTS","DA is not able to navigate the assignment page");
            new Click().clickByXpath("//div[contains(text(),'assignmnet')]");//click on the assignment name
            new Navigator().logout();//
            //Tc row no 24
            new Login().directLoginAsDATeacher(index,teacherEmail);
            new Navigator().navigateTo("assignment");//Navigate to the assignment page
            new Navigator().clickOnUseExistingAssignment(index);
            new Click().clickByXpath("//div[contains(text(),'assignmnet23')]");//click on the assignment name

            //Tc row no 25
            new Click().clickByid("assessments-back-link");//click on the back button
            new Click().clickByid("two");//click on the community
            boolean found = false;
            List<WebElement> assignmentName=driver.findElements(By.xpath("//div[@class='assessment-name ellipsis']"));
            for(WebElement ele:assignmentName)
            {
                if(ele.getText().contains("assignmnet23"))
                {
                    found = true;
                }
            }
            if(found == true)
                Assert.fail("The assessment shared with the district is visible in community page");



        } catch (Exception e) {
            Assert.fail("Exception in test case shareWithTeacherOfSameDistrict of class AssignmentCreationByDA", e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void selectGradeAndSelectFromTopDropDown()
    {
        try {

            //Tc row no 26
            //"1. Login as DA 2. Select a Grade and a Subject from the header 3. Select 'Assessments' from the side navigator dropdown "
            String email="daadmin@snapwiz.com";
            String teacherEmail="da1autoteacher@snapwiz.com";
            int index=26;
            new Login().directLoginAsDATeacher(index,teacherEmail);
            new Navigator().logout();//logout
            new Login().loginAsDA(index, email); //login as District Admin
            Select sel=new Select(driver.findElement(By.id("as-header-grade-selectbox")));
            sel.selectByIndex(0);//select grade1
            sel=new Select(driver.findElement(By.id("as-header-subject-selectbox")));
            sel.selectByIndex(0);//select a subject

            String dashBoardTiles=new TextFetch().textfetchbycssselector("div[class='center header-title']");
            System.out.println("DashBoardTiles:"+dashBoardTiles);
            Assert.assertEquals(dashBoardTiles,"DASHBOARD","DA is not Navigated to Dashboard page byDefault");

            String profileName=new TextFetch().textfetchbycssselector("span[class='ls-user-nav__username']");
            if(!profileName.contains("daadmin daad")){
                Assert.fail("Profile Name is not visible");
            }
            //Assert.assertEquals(profileName,"daadmin daadmin","Profile Name is not visible");
            new Click().clickByclassname("swhelp-restart");//click on the help button
            Thread.sleep(3000);
            Select sel1;
            for(int i=0;i<=12;i++) {
                 sel1 = new Select(driver.findElement(By.id("as-header-grade-selectbox")));
                sel1.selectByIndex(i);//select grade1
                Thread.sleep(2000);
            }
            for(int j=0;j<=19;j++) {
                sel1 = new Select(driver.findElement(By.id("as-header-subject-selectbox")));
                sel1.selectByIndex(j);//select a subject
                Thread.sleep(2000);
            }

            //Tc row no 32
            //6. Click on ' Assessment from the' side navigator

            new Navigator().navigateTo("districtAssessments");//navigate to the assessments
            String noAssessmentMsg=new TextFetch().textfetchbyclass("as-noData-title");
            System.out.println("NoAssessmentMsg:"+noAssessmentMsg);
            Assert.assertEquals(noAssessmentMsg,"Performance Summary Not Available","The assessment page of the selected grade and subject is not displayed accordingly");

            Thread.sleep(3000);
            //Tc row no 33
            new Navigator().selectGradeAndSubject(index);
            new Navigator().navigateTo("districtAssessments");//Navigate to assessment
            new Navigator().clickOnCreateNewAssignment(index);//click on the new Create new Assignment
            frameQuestion(index);
            new TextSend().textsendbycssSelector("shor1","input[class='lsm-assignment-input total_point width50 lsm-assignment-short-label-input']");
            new Click().clickByid("share-assessment-button");//click on the share button

            new Navigator().logout();//Logout

            new Login().directLoginAsDATeacher(index,teacherEmail);
            new Navigator().clickOnUseExistingAssignment(index);
            String assessmentName=new TextFetch().textFetchByXpath("//div[contains(text(),'assignmnet"+index+"')]");
            System.out.println("Assessment Name:"+assessmentName);
            String questionCount=new TextFetch().textfetchbyclass("as-assignments-questions");
            Assert.assertEquals(questionCount,"1","Question count is not present");
            String ownerName=new TextFetch().textfetchbycssselector("span[class='as-tags-list ellipsis']");
            System.out.println("Owner Name:"+ownerName);
            Assert.assertEquals(ownerName,"daadmin daadmin","Owner name is not visible");
            String tlo=new TextFetch().textFetchByXpath("//span[@class='as-tags-list']");
            Assert.assertEquals(tlo,"K.CC","Standard Tlos is not visible");


        } catch (Exception e) {
            Assert.fail("Exception in test case selectGradeAndSelectFromTopDropDown of class AssignmentCreationByDA", e);
        }
    }
    @Test(priority = 5,enabled = true)
    public void DADashboardAssessment()
    {
        try {

            //Tc row no 35
            String email="daadmin@snapwiz.com";
            String teacherEmail="da1autoteacher@snapwiz.com ";
            String studentEmail="da1autostudent@snapwiz.com";
            int index=35;
            new Login().directLoginAsDATeacher(index,teacherEmail);//login as teacher
            new Navigator().logout();//logout

            new Login().loginAsDA(index, email); //login as District Admin
            new Navigator().logout();//log out

            new Login().directLoginAsDAStudent(index,studentEmail); //login as student
            new Navigator().logout();//log out

            new Login().directLoginAsDATeacher(index,teacherEmail);//login as teacher
            new Assignment().create(index, "multiplechoice");//create an Assignment
            new Assignment().addQuestion(index, "multiplechoice");//add a question
            new Assignment().assignToStudent(index);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out

            new Login().directLoginAsDAStudent(index,studentEmail); //login as student
            new Assignment().submitAssignment(index);//submit the assignment*//*


            new Login().loginAsDA(index, email); //login as District Admin
            String dashBoardTiles=new TextFetch().textfetchbycssselector("div[class='center header-title']");
            System.out.println("DashBoardTiles:"+dashBoardTiles);
            Assert.assertEquals(dashBoardTiles,"DASHBOARD","DA is not Navigated to Dashboard page byDefault");
            new Navigator().logout();//logout

            //Tc row no 36
            new Login().loginAsDA(index, email); //login as District Admin
            Select sel=new Select(driver.findElement(By.id("as-header-grade-selectbox")));
            sel.selectByIndex(2);//select grade1
            Thread.sleep(2000);
            sel=new Select(driver.findElement(By.id("as-header-subject-selectbox")));
            sel.selectByIndex(2);//select a subject
            Thread.sleep(2000);
            String noAssessmentImage=driver.findElement(By.xpath("//img[contains(@src,'dashbaord-daAdmin-nodata.png')]")).getAttribute("src");
            System.out.println("ByDefalut Image:"+noAssessmentImage);
            if(!noAssessmentImage.contains("dashbaord-daAdmin-nodata.png"))
                Assert.fail("ByDefault image is not Displayed");

            String profileName=new TextFetch().textfetchbycssselector("span[class='ls-user-nav__username']");
            Assert.assertEquals(profileName,"daadmin daadmin","Profile Name is not visible");
            String dashBoardTiles1=new TextFetch().textfetchbycssselector("div[class='center header-title']");
            System.out.println("DashBoardTiles:"+dashBoardTiles1);
            Assert.assertEquals(dashBoardTiles1,"DASHBOARD"," Dashboard Tiles is not Present");
            sel=new Select(driver.findElement(By.id("as-header-grade-selectbox")));
            sel.selectByIndex(0);//select grade1
            sel=new Select(driver.findElement(By.id("as-header-subject-selectbox")));
            sel.selectByIndex(0);//select a subject
            new Click().clickByclassname("swhelp-restart");//click on the help button
            Thread.sleep(3000);
            String activeAssessmentName=new TextFetch().textfetchbycssselector("div[class='as-label ellipsis']");
            if(activeAssessmentName.equals("")||activeAssessmentName==null)
                Assert.fail("Active Assessment Name is not displayed");

            new Click().clickByclassname("btn-transition");//click on the see all button

        } catch (Exception e) {
            Assert.fail("Exception in test case DADashboardAssessment of class AssignmentCreationByDA", e);
        }
    }

    @Test(priority = 6,enabled = true)
    public void checkProfileFiled()
    {
        try {

            //Tc row no 40
            String email="daadmin@snapwiz.com";
            int index=40;
            new Login().loginAsDA(index, email); //login as District Admin
            new Click().clickByclassname("ls-user-nav__username");//click on the profile name
            String defaultImage=driver.findElement(By.id("as-user-profile-img")).getAttribute("src");
            System.out.println("Default Image:"+defaultImage);
            if(!defaultImage.contains("/webresources/images/as/as-default-profile.png"))
                Assert.fail("Default Profile image is not present");
            String firstName=driver.findElement(By.xpath("//div[text()='First Name']/../div[2]/input")).getAttribute("value");
            Assert.assertEquals(firstName,"daadmin","FirstName with corresponding value is not displayed ");
            String lastName=driver.findElement(By.xpath("//div[text()='Last Name']/../div[2]/input")).getAttribute("value");
            Assert.assertEquals(lastName,"daadmin","LastName with corresponding value is not displayed ");
            String email1=driver.findElement(By.xpath("//div[text()='Email']/../div[2]/input")).getAttribute("value");
            Assert.assertEquals(email1,"daadmin@snapwiz.com","Email with corresponding value is not displayed ");
            new Click().clickByclassname("as-profile-change-paswdLink");//click on the change password link
            String favSubArea=new TextFetch().textFetchByXpath("//div[contains(text(),'Favorite Subject Areas')]");
            System.out.println("Fav:"+favSubArea);
            if(!favSubArea.contains("Favorite Subject Areas"))
                Assert.fail(" Favorite Subject Areas  is not displayed");
            new Click().clickByXpath("//*[@id='updateForm']//div[3]/span");//click on the fabSubjectAreaIcon
            new TextSend().textsendbyid("fav Subject Area","user-academic-interest");
            new Click().clickByclassname("as-profile-cancel");//click on the cancel
            String saveButtonText=driver.findElement(By.id("save")).getAttribute("alt");
            Assert.assertEquals(saveButtonText,"save","Save button is not displayed");

           //Tc row no 49

            String filename = ReadTestData.readDataByTagName("", "filename", "45");
            new Click().clickBycssselector("span[class='ls-user-nav__username']");//click on profile link on header
            Thread.sleep(1000);
            new Click().clickByid("user-profile-img");//click on profile pic
            Thread.sleep(1000);
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(1000);
            new Click().clickByid("start_queue");//click on upload button
            Thread.sleep(15000);
            String img = driver.findElement(By.id("as-user-profile-img")).getAttribute("src");
            System.out.println("img:"+img);
            if(img.contains("/default/img-62968.png"))
                Assert.fail("Instructor is unable to change the profile pic.");

        } catch (Exception e) {
            Assert.fail("Exception in test case checkProfileFiled of class AssignmentCreationByDA", e);
        }
    }
    @Test(priority = 7,enabled = true)
    public void editProfileDetails()
    {
        try {

            //Tc row no 53
            String email="daadmin@snapwiz.com";
            int index=53;
            new Login().loginAsDA(index, email); //login as District Admin
            new Click().clickByclassname("ls-user-nav__username");//click on the profile name
           /* String defaultImage=driver.findElement(By.id("as-user-profile-img")).getAttribute("src");
            System.out.println("Default Image:"+defaultImage);
            if(!defaultImage.contains("/webresources/images/as/as-default.png"))
                Assert.fail("Default Profile image is not present");*/
            String firstName=driver.findElement(By.xpath("//div[text()='First Name']/../div[2]/input")).getAttribute("value");
            Assert.assertEquals(firstName,"daadmin","FirstName with corresponding value is not displayed ");
            String lastName=driver.findElement(By.xpath("//div[text()='Last Name']/../div[2]/input")).getAttribute("value");
            Assert.assertEquals(lastName,"daadmin","LastName with corresponding value is not displayed ");
            String email1=driver.findElement(By.xpath("//div[text()='Email']/../div[2]/input")).getAttribute("value");
            Assert.assertEquals(email1,"daadmin@snapwiz.com","Email with corresponding value is not displayed ");
            new Click().clickByclassname("as-profile-change-paswdLink");//click on the change password link
            String favSubArea=new TextFetch().textFetchByXpath("//div[contains(text(),'Favorite Subject Areas')]");
            System.out.println("Fav:"+favSubArea);
            if(!favSubArea.contains("Favorite Subject Areas"))
                Assert.fail(" Favorite Subject Areas  is not displayed");
            new Click().clickByXpath("//*[@id='updateForm']//div[3]/span");//click on the fabSubjectAreaIcon
            new TextSend().textsendbyid("fav Subject Area","user-academic-interest");
            new Click().clickByclassname("as-profile-cancel");//click on the cancel
            String saveButtonText=driver.findElement(By.id("save")).getAttribute("alt");
            Assert.assertEquals(saveButtonText,"save","Save button is not displayed");

            //Tc row no 57
           // 3. Click 'Edit' icon on profile Details field
            new Click().clickByXpath("(//*[@id='updateForm']//div[2]/span)[1]"); //click on the edit icon of profile details
            driver.findElement(By.id("user-first-name")).clear();
            new TextSend().textsendbyid("firstnamee", "user-first-name");
            driver.findElement(By.id("user-last-name")).clear();
            new TextSend().textsendbyid("lastnamee", "user-last-name");
            new Click().clickByid("save");//click on save
            String notificationMsg=new TextFetch().textfetchbyclass("as-notification-message");
            System.out.println("NotificationMsg:" + notificationMsg);
            if(!notificationMsg.contains("Your changes have been saved successfully."))
                Assert.fail("Notification Message is not displayed");



        } catch (Exception e) {
            Assert.fail("Exception in test case checkProfileFiled of class AssignmentCreationByDA", e);
        }
    }
    @Test(priority = 8,enabled = true)
    public void resetProfileDetails()
    {
        try {
            String email="daadmin@snapwiz.com";
            int index=68;
            new Login().loginAsDA(index, email); //login as District Admin
            new Click().clickByclassname("ls-user-nav__username");//click on the profile name

            // 3. Click 'Edit' icon on profile Details field
            new Click().clickByXpath("(//*[@id='updateForm']//div[2]/span)[1]"); //click on the edit icon of profile details
            driver.findElement(By.id("user-first-name")).clear();
            new TextSend().textsendbyid("daadmin", "user-first-name");
            driver.findElement(By.id("user-last-name")).clear();
            new TextSend().textsendbyid("daadmin","user-last-name");
            new Click().clickByid("save");//click on save
            String notificationMsg=new TextFetch().textfetchbyclass("as-notification-message");
            System.out.println("NotificationMsg:"+notificationMsg);
            if(!notificationMsg.contains("Your changes have been saved successfully."))
                Assert.fail("Notification Message is not displayed");



        } catch (Exception e) {
            Assert.fail("Exception in test case resetProfileDetails of class AssignmentCreationByDA", e);
        }
    }
    @Test(priority = 9,enabled = true)
    public void editFavSubjectField()
    {
        try {
            //Tc row no 64
            String email="daadmin@snapwiz.com";
            int index=64;
            new Login().loginAsDA(index, email); //login as District Admin
            new Click().clickByclassname("ls-user-nav__username");//click on the profile name

            // 3. Click 'Edit' icon on profile Details field
            new Click().clickByXpath("(//*[@id='updateForm']//div[2]/span)[1]"); //click on the edit icon of profile details
            new Click().clickByXpath("//*[@id='updateForm']//div[3]/span");//click on the fabSubjectAreaIcon
            new TextSend().textsendbyid("fav Subject Area", "user-academic-interest");
            new Click().clickByid("save");//click on save

            String notificationMsg=new TextFetch().textfetchbyclass("as-notification-message");
            System.out.println("NotificationMsg:"+notificationMsg);
            if(!notificationMsg.contains("Your changes have been saved successfully."))
                Assert.fail("Notification Message is not displayed");



        } catch (Exception e) {
            Assert.fail("Exception in test case editFavSubjectField of class AssignmentCreationByDA", e);
        }
    }
    @Test(priority = 10,enabled = true)
    public void resetFavSubjectField()
    {
        try {
            //Tc row no 65
            String email="daadmin@edulastic.com";
            int index=65;
            new Login().loginAsDA(index, email); //login as District Admin
            new Click().clickByclassname("ls-user-nav__username");//click on the profile name

            // 3. Click 'Edit' icon on profile Details field
            new Click().clickByXpath("(//*[@id='updateForm']//div[2]/span)[1]"); //click on the edit icon of profile details
            new Click().clickByXpath("//*[@id='updateForm']//div[3]/span");//click on the fabSubjectAreaIcon
            new TextSend().textsendbyid(" ","user-academic-interest");
            new Click().clickByid("save");//click on save

        } catch (Exception e) {
            Assert.fail("Exception in test case resetFavSubjectField of class AssignmentCreationByDA", e);
        }
    }
    @Test(priority = 11,enabled = true)
    public void createAssessmentWithAllTypeQuestion()
    {
        try {
            //Tc row no 90
            String email="daadmin@snapwiz.com";
            int index=90;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname","90");
            new Login().loginAsDA(index, email); //login as District Admin
            new Assignment().create(index, "all");//create an Assignment

            System.out.println("AssessmentName:" + assessmentname);
            new Navigator().navigateTo("districtAssessments");//navigate to the assessments
            new Click().clickByXpath("//div[contains(text(),'"+assessmentname+"')]");//click on the assignment name
            new Navigator().logout();//logout

            //Tc row no 91
            new Login().loginAsDA(index,email);//login as DA
            new Navigator().navigateTo("districtAssessments");//navigate to the assessments
            new Navigator().selectGradeAndSubject(index);
            Thread.sleep(3000);
            new Click().clickByXpath("//div[contains(text(),'"+assessmentname+"')]");//click on the assignment name
            new Click().clickByid("assessments-back-link");//click on the back button
            //Tc row no 92&93

            new Click().clickByid("new-assessment-button");//click on the +New Assessment
            List<WebElement> assessmentField=driver.findElements(By.className("as-assignment-flow-link-title"));

            if(!assessmentField.get(0).getText().equals("Modify Existing Assessment"))
                Assert.fail("Modify Existing Assessment Field is not Present");
            if(!assessmentField.get(1).getText().equals("Create New Assessment"))
                Assert.fail("Create New Assessment Field is not Present");
            new Click().clickByid("cancel-assessment");//click on the back button


        } catch (Exception e) {
            Assert.fail("Exception in test case createAssessmentWithAllTypeQuestion of class AssignmentCreationByDA", e);
        }
    }
    @Test(priority = 12,enabled = true)
    public void createNewAssessmentByModifying()
    {
        try {
            //Tc row no 94
            String email="daadmin@snapwiz.com";
            int index=94;
            new Login().loginAsDA(index, email); //login as District Admin
            new Assignment().createAssessmentAndShareWithDistrict(index,"district"); //create assignment and share with district
            new Navigator().navigateTo("districtAssessments");//navigate to the assessments
            new Navigator().clickOnModifyExistingAssessment(index,"one"); //select me
           //new Click().clickByid("one");//click on the me
            new Click().clickByXpath("//div[contains(text(),'assignmnet94')]");//click on the assignment name
            String assessmentNameTitle=new TextFetch().textfetchbycssselector("div[class='as-assessmentquestionsView-title ellipsis']");
            System.out.println("AssessmentNameTitle:"+assessmentNameTitle);
            if(assessmentNameTitle.equals("")||assessmentNameTitle==null)
                Assert.fail("Assessment Name Title is not displayed");
            String step1=new TextFetch().textfetchbyclass("lsm-createAssignment-steps");
            System.out.println("Step:"+step1);
            Assert.assertEquals(step1,"(Step 1 of 2: Select assessment)","Assessment step status (Step 1 of 2) is not displayed ");
            String back=new TextFetch().textfetchbyid("assessments-back-link");
            Assert.assertEquals(back,"Back","Back button is not available");
            new Click().clickByid("assessments-customised-button");//click on the customize button

            String saveForLater=new TextFetch().textfetchbyid("assessments-save-later-button");//verify save for later
            if (!saveForLater.contains("Save for later")){
                Assert.fail("Save for later is not visible");
            }
            String addMore=new TextFetch().textfetchbyid("assessments-back-button");//verify addmore
            if (!addMore.contains("Add more")){
                Assert.fail("Add more is not visible");
            }
            new Click().clickByid("assessments-use-button");//click on the next

            String sharePage=new TextFetch().textfetchbyclass("lsm-assignment-assigning-status");
            if(!sharePage.contains("Step 2 of 2: Share Assessment"))
                Assert.fail("Share page is not displayed");

            new TextSend().textsendbycssSelector("shor1","input[class='lsm-assignment-input total_point width50 lsm-assignment-short-label-input']");
            new Click().clickByid("share-assessment-button");//click on the share button

            String assignmentTitle=new TextFetch().textFetchByXpath("//div[text()='Assessments']");
            System.out.println("AssignmentTitle:"+assignmentTitle);
            Assert.assertEquals(assignmentTitle,"ASSESSMENTS","DA is not able to navigate the assignment page");
            new Click().clickByXpath("//div[contains(text(),'assignmnet94')]");//click on the assignment name

        } catch (Exception e) {
            Assert.fail("Exception in test case createNewAssessmentByModifying of class AssignmentCreationByDA", e);
        }
    }
    @Test(priority = 13,enabled = true)
    public void createNewAssessmentByModifyingAndSelectDistrict()
    {
        try {
            //Tc row no 104
            String email="daadmin@snapwiz.com";
            String secondAdminEmail="daadmin1@snapwiz.com";
            int index=104;
            new Login().loginAsDA(index, email); //login as District Admin
            new Navigator().logout();//logout firstDaAdmin

            new Login().loginAsDA(index,secondAdminEmail);//login as second District Admin
            new Navigator().navigateTo("districtAssessments");//navigate to the assessments
            new Navigator().clickOnModifyExistingAssessment(index,"two");//select community

            new Click().clickByXpath("//div[contains(text(),'assignmnet')]");//click on the assignment name
            String assessmentNameTitle=new TextFetch().textfetchbycssselector("div[class='as-assessmentquestionsView-title ellipsis']");
            System.out.println("AssessmentNameTitle:"+assessmentNameTitle);
            if(assessmentNameTitle.equals("")||assessmentNameTitle==null)
                Assert.fail("Assessment Name Title is not displayed");
            String step1=new TextFetch().textfetchbyclass("lsm-createAssignment-steps");
            System.out.println("Step:"+step1);
            Assert.assertEquals(step1,"(Step 1 of 2: Select assessment)","Assessment step status (Step 1 of 2) is not displayed ");
            String back=new TextFetch().textfetchbyid("assessments-back-link");
            Assert.assertEquals(back,"Back","Back button is not available");
            new Click().clickByid("assessments-customised-button");//click on the customize button

            String saveForLater=new TextFetch().textfetchbyid("assessments-save-later-button");//verify save for later
            if (!saveForLater.contains("Save for later")){
                Assert.fail("Save for later is not visible");
            }
            String addMore=new TextFetch().textfetchbyid("assessments-back-button");//verify addmore
            if (!addMore.contains("Add more")){
                Assert.fail("Add more is not visible");
            }
            new Click().clickByid("assessments-use-button");//click on the next
            String sharePage=new TextFetch().textfetchbyclass("lsm-assignment-assigning-status");
            if(!sharePage.contains("Step 2 of 2: Share Assessment"))
                Assert.fail("Share page is not displayed");

            new TextSend().textsendbycssSelector("shor1","input[class='lsm-assignment-input total_point width50 lsm-assignment-short-label-input']");
            new Click().clickByid("share-assessment-button");//click on the share button

            String assignmentTitle=new TextFetch().textFetchByXpath("//div[text()='Assessments']");
            System.out.println("AssignmentTitle:"+assignmentTitle);
            Assert.assertEquals(assignmentTitle,"ASSESSMENTS","DA is not able to navigate the assignment page");
            new Click().clickByXpath("//div[contains(text(),'assignmnet')]");//click on the assignment name

        } catch (Exception e) {
            Assert.fail("Exception in test case createNewAssessmentByModifyingAndSelectDistrict of class AssignmentCreationByDA", e);
        }
    }
    @Test(priority = 14,enabled = true)
    public void instructorViewAssessmentOfDifferentClassSection()
    {
        try {
            //Tc row no 179
            String email="daadmin@snapwiz.com";
            String teacherEmail="da1autoteacher@snapwiz.com ";
            int index=181;
            new Login().directLoginAsDATeacher(index,teacherEmail);//teacher with two class section
            new Navigator().logout();//logout
            new Login().loginAsDA(index, email); //login as District Admin
            new Assignment().createAssessmentAndShareWithDistrict(index,"district"); //create assignment and share with district
            new Navigator().logout();//logout
            new Login().directLoginAsDATeacher(index,teacherEmail);
            new Navigator().clickOnUseExistingAssignment(index);
            new Click().clickByXpath("//div[contains(text(),'assignmnet181')]");//click on the assignment name
            new Click().clickByid("assessments-back-link");//click on the back
            new Click().clickByid("assessments-cancel-button");//click on the cancel button
            new Navigator().navigateTo("dashboard");//navigate to dashboard
            Select classes=new Select(driver.findElement(By.id("as-header-classes-selectbox")));
            classes.selectByIndex(1);
            new Navigator().clickOnUseExistingAssignment(index);
            new Click().clickByXpath("//div[contains(text(),'assignmnet181')]");//click on the assignment name

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorViewAssessmentOfDifferentClassSection of class AssignmentCreationByDA", e);
        }
    }
    @Test(priority = 15,enabled = true)
    public void assessmentSharedByDANotVisibleToStudent()
    {
        try {
           //Tc row no 182
            String email="daadmin@snapwiz.com";
            String teacherEmail="da1autoteacher@snapwiz.com ";
            String studentEmail="da1autostudent@snapwiz.com";
            int index=182;
            new Login().directLoginAsDATeacher(index,teacherEmail);//login as teacher
            new Navigator().logout();//logout
            new Login().loginAsDA(index, email); //login as District Admin
            new Navigator().logout();//log out
            new Login().directLoginAsDAStudent(index,studentEmail); //login as student
            new Navigator().logout();//logout

            new Login().loginAsDA(index, email); //login as District Admin
            new Navigator().navigateTo("districtAssessments");//Navigate to assessment
            //4. Click on '+New assessment' button
            new Click().clickByid("new-assessment-button");//click on the New Assessment
            List<WebElement> assessmentField=driver.findElements(By.className("as-assignment-flow-link-title"));
            if(!assessmentField.get(0).getText().equals("Modify Existing Assessment"))
                Assert.fail("Modify Existing Assessment Field is not Present");
            if(!assessmentField.get(1).getText().equals("Create New Assessment"))
                Assert.fail("Create New Assessment Field is not Present");

            //Tc row no 13
            //5. Click on "Create New Assessment"
            assessmentField.get(1).click();//click on the create new Assignment

            new Click().clickByXpath("//div[@id='lsm-createAssignment-subject-dropDown']/select/option[contains(text(),'Math - Common Core')]");//click on the current subject
            new Click().clickByXpath("./*//*[@id='lsm-createAssignment-grade-dropDown']/select/option[text()='Kindergarten']");//click on the current grade

            String ownerLabel=new TextFetch().textfetchbycssselector("span[class='lsm-createAssignment-owner-filter-title']");
            Assert.assertEquals(ownerLabel,"OWNER","OWNER label is not visible");
            String me=new TextFetch().textFetchByXpath("//span[.='Me']");
            Assert.assertEquals("Me",me,"Me label is not visible to the teacher");
            String community=new TextFetch().textFetchByXpath("//span[.='Community']");
            Assert.assertEquals("Community",community,"Community label is not  visible to the teacher");
            String standard=new TextFetch().textfetchbycssselector("span[class='lsm-createAssignment-chooseStd-text']");
            Assert.assertEquals(standard,"CHOOSE STANDARD","CHOOSE STANDARD is not visible to the teacher");
            //Tc row no 15
            int tloSize=driver.findElements(By.xpath("//ul[@class='lsm-createAssignment-all-standards-list']/li[text()]")).size();
            System.out.println("List of Tlos:"+tloSize);
            if(tloSize==0)
                Assert.fail("Choose standard' field with the list of all the TLOs is not visible");

            //Tc row no 16
            boolean tloIsDisplayed= driver.findElement(By.xpath("//div[@class='lsm-tlo-info-wrapper']/span[text()='Auto Select']/following-sibling::span[text()='Select']")).isDisplayed();
            if(tloIsDisplayed==false)
                Assert.fail("\"Auto select\" and \"select\" should not displayed for a TLO");

            boolean eloIsDisplayed=driver.findElement(By.xpath("(//span[@class='lsm-tlo-heading']//..)[1]//span[text()='Create']//following-sibling::span[text()='Auto Select']//following-sibling::span[text()='Select']")).isDisplayed();
            if(eloIsDisplayed==false)
                Assert.fail("\"Create\", \"Auto Select\" and \"Select\" is not displayed for respective ELOs");


            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn']");//click on the create besides elos
            new Click().clickByid("qtn-true-false-type");//click on the true false question
            new TextSend().textsendbyid("sample true false question", "question-raw-content");
            new Click().clickByclassname("true-false-answer-select");
            new TextSend().textsendbyclass("assignmnet", "lsm-createAssignment-input-name");
            new Click().clickByid("saveQuestionDetails1");//click on the save button
            new Click().clickBycssselector("div[class='lsm-createAssignment-done selected']");//click on the review button
            new Click().clickByid("assessments-use-button");//click on the next button

            //Tc row no 18

            String shortLabel=new TextFetch().textfetchbyclass("lsm-short-label");
            Assert.assertEquals(shortLabel,"Short Label","Short Label field is not present");
            new TextSend().textsendbycssSelector("shor1","input[class='lsm-assignment-input total_point width50 lsm-assignment-short-label-input']");
            List<WebElement> totalPoint=driver.findElements(By.cssSelector("label[class='lsm-new-assignment-content-row width50']"));
            if(!totalPoint.get(0).getText().contains("Total points"))
                Assert.fail("Total points Text is not present");
            String point=new TextFetch().textfetchbycssselector("span[class='lsm-assignment-total-points total_point width50']");
            if(!point.equals("1"))
                Assert.fail("Total points is not present");
            //Tc row no 19 & 20
            //Additional Details' expansion link
            //"3. Description (optional)4. tag (Optional)  "
            new Click().clickBycssselector("i[class='lsm-new-assignment-addDetails-toggle lsm-new-assignment-expand-icon']");//click on the +icon
            String descriptionLabel=new TextFetch().textfetchbylistcssselector("span[class='width25 f-left']",0);
            if(!descriptionLabel.contains("Description"))
                Assert.fail("Description (optional) tag is not visible");
            String tag=new TextFetch().textfetchbylistcssselector("span[class='width25 f-left']",1);
            if(!tag.contains("Tag"))
                Assert.fail("tag (Optional) tag is not visible");

            //Tc row no 21
            boolean districtByDefault=driver.findElement(By.id("district")).isSelected();
            System.out.println("DistrictByDefault:"+districtByDefault);
            if(districtByDefault==false)
                Assert.fail("By Default District radio button is not selected");

            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("private")));//click on the private radio button
            Thread.sleep(2000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("public")));//click on the public radio button

            //Tc row no 22
            new Click().clickByid("share-assessment-button");//click on the share button
            String assignmentTitle=new TextFetch().textFetchByXpath("//div[text()='Assessments']");
            System.out.println("AssignmentTitle:"+assignmentTitle);

            Assert.assertEquals(assignmentTitle,"ASSESSMENTS","DA is not able to navigate the assignment page");
            new Click().clickByXpath("//div[contains(text(),'assignmnet')]");//click on the assignment name

            new Navigator().logout();//logout from DA
            new Login().directLoginAsDAStudent(index,studentEmail); //login as student
            new Navigator().navigateTo("assignment"); //navigate to assignment
            String defaultIcon=driver.findElement(By.xpath("//span[@class='as-noData-icon']/img")).getAttribute("src");
            System.out.println("DefaultIcon::"+defaultIcon);
            if(!defaultIcon.contains("/webresources/images/as/robo.png"))
                Assert.fail("The Assessment created by the DA is listed ");



        } catch (Exception e) {
            Assert.fail("Exception in test case assessmentSharedByDANotVisibleToStudent of class AssignmentCreationByDA", e);
        }
    }

    @Test(priority = 16,enabled = true)
    public void createNewAssessmentByModifyingExistingAssessmentOfOtherDASameDistrict()
    {
        try {
            //Tc row no 99
            String email="daadmin@snapwiz.com";
            String secondDA="daadmin11@snapwiz.com";
            int index=99;
            new Login().loginAsDA(index, email); //login as District Admin
            new Assignment().createAssessmentAndShareWithDistrict(index,"district"); //create assignment and share with district
            new Navigator().logout();//logout first da


            new Login().loginAsDA(index,secondDA);//login as second da of same district

            new Navigator().navigateTo("districtAssessments");//navigate to the assessments
            new Navigator().clickOnModifyExistingAssessment(index,"three"); //select district

            //new Click().clickByid("three");//click on the district
            new Click().clickByXpath("//div[contains(text(),'assignmnet99')]");//click on the assignment name
            String assessmentNameTitle=new TextFetch().textfetchbycssselector("div[class='as-assessmentquestionsView-title ellipsis']");
            System.out.println("AssessmentNameTitle:"+assessmentNameTitle);
            if(assessmentNameTitle.equals("")||assessmentNameTitle==null)
                Assert.fail("Assessment Name Title is not displayed");
            String step1=new TextFetch().textfetchbyclass("lsm-createAssignment-steps");
            System.out.println("Step:"+step1);
            Assert.assertEquals(step1,"(Step 1 of 2: Select assessment)","Assessment step status (Step 1 of 2) is not displayed ");
            String back=new TextFetch().textfetchbyid("assessments-back-link");
            Assert.assertEquals(back,"Back","Back button is not available");
            new Click().clickByid("assessments-customised-button");//click on the customize button

            String saveForLater=new TextFetch().textfetchbyid("assessments-save-later-button");//verify save for later
            if (!saveForLater.contains("Save for later")){
                Assert.fail("Save for later is not visible");
            }
            String addMore=new TextFetch().textfetchbyid("assessments-back-button");//verify addmore
            if (!addMore.contains("Add more")){
                Assert.fail("Add more is not visible");
            }
            new Click().clickByid("assessments-use-button");//click on the next

            String sharePage=new TextFetch().textfetchbyclass("lsm-assignment-assigning-status");
            if(!sharePage.contains("Step 2 of 2: Share Assessment"))
                Assert.fail("Share page is not displayed");

            new TextSend().textsendbycssSelector("shor1","input[class='lsm-assignment-input total_point width50 lsm-assignment-short-label-input']");
            new Click().clickByid("share-assessment-button");//click on the share button

            String assignmentTitle=new TextFetch().textFetchByXpath("//div[text()='Assessments']");
            System.out.println("AssignmentTitle:"+assignmentTitle);
            Assert.assertEquals(assignmentTitle,"ASSESSMENTS","DA is not able to navigate the assignment page");
            new Click().clickByXpath("//div[contains(text(),'assignmnet99')]");//click on the assignment name

        } catch (Exception e) {
            Assert.fail("Exception in test case createNewAssessmentByModifyingExistingAssessmentOfOtherDASameDistrict of class AssignmentCreationByDA", e);
        }
    }
    @Test(priority = 17,enabled = true)
    public void changePassword()
    {
        try {
            //Tc row no 67
            String email="daadmin@snapwiz.com";
            int index=65;
            new Login().loginAsDA(index, email); //login as District Admin
            new Click().clickByclassname("ls-user-nav__username");//click on the profile name

            // 3. Click 'Edit' icon on profile Details field
            new Click().clickByXpath("(//*[@id='updateForm']//div[2]/span)[1]"); //click on the edit icon of profile details
            new Click().clickByclassname("as-profile-change-paswdLink");//click on the change password
            String newPasswordLabel=new TextFetch().textFetchByXpath("//div[text()='New Password']");
            Assert.assertEquals(newPasswordLabel,"New Password","New Password' text field is not displayed");
            String confirmPassword=new TextFetch().textFetchByXpath("//div[text()='Confirm Password']");
            Assert.assertEquals(confirmPassword,"Confirm Password","Confirm Password' text field is not displayed");
            new TextSend().textsendbyid("edulastic","user-password");
            new TextSend().textsendbyid("edulastic","user-confirm-password");
            new Click().clickByid("save");//click on save

            String notificationMsg=new TextFetch().textfetchbyclass("as-notification-message");
            System.out.println("NotificationMsg:"+notificationMsg);
            if(!notificationMsg.contains("Your changes have been saved successfully."))
                Assert.fail("Notification Message is not displayed");
            new Navigator().logout();//logot
            new Login().loginAsDA(index,email);
            String passMismatch=new TextFetch().textfetchbyclass("notification-msg");
            Assert.assertEquals(passMismatch,"Email or Password do not match","Email or Password do not match Messgge is not displaying");

            new TextSend().textsendbyid(email,"login-email");
            new TextSend().textsendbyid("edulastic","login-password");
            driver.findElement(By.cssSelector("input[type='submit']")).click();


        } catch (Exception e) {
            Assert.fail("Exception in test case changePassword of class AssignmentCreationByDA", e);
        }
    }
    @Test(priority = 18,enabled = true)
    public void resetPassword()
    {
        try {
            //Tc row no 67
            String email="daadmin@snapwiz.com";
            int index=65;
            driver.get(Config.baseURL);
            new TextSend().textsendbyid(email,"login-email");
            new TextSend().textsendbyid("edulastic","login-password");
            driver.findElement(By.cssSelector("input[type='submit']")).click();
            new Click().clickByclassname("ls-user-nav__username");//click on the profile name

            // 3. Click 'Edit' icon on profile Details field
            new Click().clickByXpath("(//*[@id='updateForm']//div[2]/span)[1]"); //click on the edit icon of profile details
            new Click().clickByclassname("as-profile-change-paswdLink");//click on the change password

            new TextSend().textsendbyid("snapwiz","user-password");
            new TextSend().textsendbyid("snapwiz","user-confirm-password");
            new Click().clickByid("save");//click on save


        } catch (Exception e) {
            Assert.fail("Exception in test case resetPassword of class AssignmentCreationByDA", e);
        }
    }
    public void frameQuestion(int index)
    {
        new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn']");//click on the create besides elos
        new Click().clickByid("qtn-true-false-type");//click on the true false question
        new TextSend().textsendbyid("sample true false question23", "question-raw-content");
        new Click().clickByclassname("true-false-answer-select");
        new TextSend().textsendbyclass("assignmnet"+index+"", "lsm-createAssignment-input-name");
        new Click().clickByid("saveQuestionDetails1");//click on the save button
        new Click().clickBycssselector("div[class='lsm-createAssignment-done selected']");//click on the review button
        new Click().clickByid("assessments-use-button");//click on the next button


    }

}
