
        package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.instructorsharingtheassessmentwithschoolanddistrict;

        import com.snapwiz.selenium.tests.staf.edulastic.Driver;
        import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
        import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
        import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentLibrary;
        import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
        import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
        import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
        import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.MatchingTablesQuestionCreation;
        import com.snapwiz.selenium.tests.staf.edulastic.uihelper.ShareWith;
        import org.openqa.selenium.By;
        import org.openqa.selenium.JavascriptExecutor;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.PageFactory;
        import org.testng.Assert;
        import org.testng.annotations.Test;

        import java.util.List;

/**
 * Created by pragya on 16-02-2015.
 */
public class InstructorSharingTheAssessment extends Driver {

    @Test(priority = 1,enabled = true)
    //Instructor should be able to share the assessments with their peers within the school, by selecting the 'School' radio button while assigning the assessment
    public void instructorSharingTheAssessmentWithSchool(){
        try {
            String appendChar = "a";
            String appendChar1="b";
            int dataIndex = 18;
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            Assign assign = PageFactory.initElements(driver,Assign.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            MatchingTablesQuestionCreation matchingTables = PageFactory.initElements(driver, MatchingTablesQuestionCreation.class);
            AssessmentLibrary assessmentLibrary = PageFactory.initElements(driver,AssessmentLibrary.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar,dataIndex);//SignUp as a 1st teacher
            String zipcode=new School().createWithOnlyName(appendChar, dataIndex);//createWithOnlyName school
            new Classes().createClass(appendChar, dataIndex);//createWithOnlyName class
            new Navigator().logout();//Log out

            new SignUp().teacher(appendChar1,dataIndex);//SignUp with 2nd teacher
            new School().enterAndSelectSchool(zipcode,dataIndex,false);//Select the same school created by 1st instructor
            schoolPage.getContinueButton().click(); //clicking on save button
            new Classes().createClass(appendChar1,dataIndex);//Create a class
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as 1st instructor
            new Assignment().create(dataIndex, "truefalse");//Create a true false questions
            matchingTables.getButton_review().click();//Click on review button
            listPage.getButton_next().click();//click on next button to assign

            //TC row no - 20 : Instructor should be able to share the assessment as long as he owns all the questions in the assessment
            //Expected - 1 : Assign page must be displayed with a Radio button by the name 'School'
            //Expected - Instructor should get the following radio buttons while assigning the assessment
            //1.District
            //2.Private
            //3.Public
            //4.School
            Assert.assertEquals(assign.getRadioButton_private().isDisplayed(),true,"Private radio button is not present");
            Assert.assertEquals(assign.getRadioButton_public().isDisplayed(),true,"Public radio button is not present");
            Assert.assertEquals(assign.getRadioButton_district().isDisplayed(),true,"District radio button is not present");
            Assert.assertEquals(assign.getRadioButton_school().isDisplayed(),true,"School radio button is not present");

            //TC row no. 19 : Clicking on 'school' and assigning the assessment
            //Expected - 1 : The assessment must be assigned to the class but shared with the instructors within the school
            assign(dataIndex,appendChar);//Assign to class at school level
            new Assignment().whiteListUSer(assessmentname,dataIndex,"Grade 1");
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as 2nd instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.getButton_newAssessment().click();//Click on new assessment
            assignments.getSearch().click();//Click on search
            assessmentLibrary.getRadioButtons_owner().get(3).click();//Click on School Users radio button
            Thread.sleep(5000);

            //Expected - 2 : The instructor from the same school should be able to view the assessment and assign it to his class
            Assert.assertEquals(assessmentLibrary.getList_assessment().get(0).getText(), assessmentname, "2nd Instructor from the same school is not able to view the same assessment assigned to school");

            assessmentLibrary.getList_assessment().get(0).click();//Select the assessment
            assessmentLibrary.getList_createNewVersion().get(0).click();//Click on customize button
            listPage.getButton_next().click();//Click on Next button
            assign.getRadio_button_rightNow().click();

            //TC row no. - 21 : If an instructor uses an assessment shared at school level, he should be able to share it with the school level only. OR can use it as a private assessment
            //Expected - Instructor should get the following radio buttons in disabled state
            //1. District
            //2. With all
            //The following radio buttons should be active.
            //1. School
            //2. Private
            Assert.assertEquals(assign.getRadioButton_district().getAttribute("class"),"iradio_square-green disabled","District button is not disabled");
            Assert.assertEquals(assign.getRadioButton_public().getAttribute("class"),"iradio_square-green disabled","Public button is not disabled");
            Assert.assertEquals(assign.getRadioButton_school().getAttribute("class"),"iradio_square-green","School button is not enabled");
            Assert.assertEquals(assign.getRadioButton_private().getAttribute("class"),"iradio_square-green checked","Private button is not enabled");

            assign(dataIndex,appendChar1);//Assign to class

        }catch(Exception e){
            Assert.fail("Exception in testcase 'instructorSharingTheAssessmentWithSchool' in class 'InstructorSharingTheAssessment'",e);

        }

    }

    @Test(priority = 2,enabled = true)
    //If an instructor uses an assessment shared at District level, he should be able to share it with the District level only. OR can use it as a private assessment
    public void instructorSharingTheAssessmentWithDistrict(){
        try{String appendChar = "a";
            String appendChar1= "b";
            int dataIndex = 111;
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            Assign assign = PageFactory.initElements(driver,Assign.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            MatchingTablesQuestionCreation matchingTables = PageFactory.initElements(driver, MatchingTablesQuestionCreation.class);
            AssessmentLibrary assessmentLibrary = PageFactory.initElements(driver,AssessmentLibrary.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar,dataIndex);//SignUp as a 1st teacher
            new School().createWithOnlyName(appendChar, dataIndex);//Create a school with district named as 'Automation district'
            new Classes().createClass(appendChar, dataIndex);//createWithOnlyName class
            new Navigator().logout();//Log out

            new SignUp().teacher(appendChar1,dataIndex);//Sign up as 2nd instructor
            new School().createWithOnlyName(appendChar1, dataIndex);//Create different school within same district named as 'Automation district'
            new Classes().createClass(appendChar1,dataIndex);//Create a class
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as 1st instructor
            new Assignment().create(dataIndex, "truefalse");//Create a true false questions
            matchingTables.getButton_review().click();//Click on review button
            listPage.getButton_next().click();//click on next button to assign
            assign(dataIndex,appendChar);//Assign the assignment to class at district level
            new Assignment().whiteListUSer(assessmentname,dataIndex,"Grade 1");
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as 2nd instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.getButton_newAssessment().click();//Click on new assessment
            assignments.getSearch().click();//Click on search
            assessmentLibrary.getRadioButtons_owner().get(2).click();//Click on District radio button
            Thread.sleep(2000);

            //Expected - The instructor should be able to view the assessment and assign it to his class
            Assert.assertEquals(assessmentLibrary.getList_assessment().get(0).getText(), assessmentname, "2nd Instructor from the same school is not able to view the same assessment assigned to school");

            assessmentLibrary.getList_assessment().get(0).click();//Select the assessment
            assessmentLibrary.getList_createNewVersion().get(0).click();//Click on customize button
            listPage.getButton_next().click();//Click on Next button

            //Expected - Instructor should get the following radio buttons in disabled state
            //1. With All
            //The following radio buttons should be active.
            //1. District
            //2. Private
            //3. School
            Assert.assertEquals(assign.getRadioButton_district().getAttribute("class"),"iradio_square-green","District button is not enabled");
            Assert.assertEquals(assign.getRadioButton_public().getAttribute("class"),"iradio_square-green disabled","Public button is not disabled");
            Assert.assertEquals(assign.getRadioButton_school().getAttribute("class"),"iradio_square-green","School button is not enabled");
            Assert.assertEquals(assign.getRadioButton_private().getAttribute("class"),"iradio_square-green checked","Private button is not enabled");

            assign(dataIndex,appendChar1);//Assign to class

        }catch (Exception e){
            Assert.fail("Exception in testcase 'instructorSharingTheAssessmentWithDistrict' in class 'InstructorSharingTheAssessment'",e);
        }
    }


    @Test(priority = 3,enabled = true)
    //If an instructor uses an assessment shared at Public level, he should be able to share it with the Public level only. OR can use it as a private assessment
    public void instructorSharingTheAssessmentWithPublic(){
        try{String appendChar = "a";
            String appendChar1= "b";
            int dataIndex = 174;
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            Assign assign = PageFactory.initElements(driver,Assign.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            MatchingTablesQuestionCreation matchingTables = PageFactory.initElements(driver, MatchingTablesQuestionCreation.class);
            AssessmentLibrary assessmentLibrary = PageFactory.initElements(driver,AssessmentLibrary.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar,dataIndex);//SignUp as a 1st teacher
            new School().createWithOnlyName(appendChar, dataIndex);//Create a school
            new Classes().createClass(appendChar,dataIndex,"Grade 3","Mathematics","Math - Common Core");//Create a class
            new Navigator().logout();//Log out

            new SignUp().teacher(appendChar1,dataIndex);//SignUp as a 2nd teacher
            new School().createWithOnlyName(appendChar1, dataIndex);//Create a school
            new Classes().createClass(appendChar1,dataIndex,"Grade 3","Mathematics","Math - Common Core");//Create a class
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as 1st instructor
            new Assignment().create(dataIndex, "truefalse");//Create a true false questions
            matchingTables.getButton_review().click();//Click on review button
            listPage.getButton_next().click();//click on next button to assign
            assign(dataIndex,appendChar);//Assign the assignment to class at Public level
            new Assignment().whiteListUSer(assessmentname,dataIndex,"Grade 3");
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as 2nd instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.getButton_newAssessment().click();//Click on new assessment
            assignments.getSearch().click();//Click on search
            Thread.sleep(2000);

                ((JavascriptExecutor) driver)
                        .executeScript("window.scrollTo(0, document.body.scrollHeight)");


            Thread.sleep(2000);

            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);

            ((JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);


            Thread.sleep(5000);
            List<WebElement> assessments = assessmentLibrary.getList_assessment();
            System.out.println("list "+assessments.size());
            int index = 0;
            for(WebElement assessment : assessments){
                if (assessments.get(index).getText().equals(assessmentname))
                    break;
                else {
                    ((JavascriptExecutor) driver)
                            .executeScript("window.scrollTo(0, document.body.scrollHeight)");
                }
                index++;
            }
            //Expected - The instructor should be able to view the assessment and assign it to his class
            Assert.assertEquals(assessmentLibrary.getList_assessment().get(index).getText(), assessmentname, "2nd Instructor is not able to view the assessment assigned to public by 1st instructor");

            assessmentLibrary.getList_assessment().get(index).click();//Select the assessment
            assessmentLibrary.getList_createNewVersion().get(0).click();//Click on customize button
            listPage.getButton_next().click();//Click on Next button

            //Expected - The following radio buttons should be active.
            //1. Public
            //2. Private
            //3. District
            //4. School
            Assert.assertEquals(assign.getRadioButton_district().getAttribute("class"),"iradio_square-green","District button is not enabled");
            Assert.assertEquals(assign.getRadioButton_public().getAttribute("class"),"iradio_square-green","Public button is not enabled");
            Assert.assertEquals(assign.getRadioButton_school().getAttribute("class"),"iradio_square-green","School button is not enabled");
            Assert.assertEquals(assign.getRadioButton_private().getAttribute("class"),"iradio_square-green checked","Private button is not enabled");

            assign(dataIndex,appendChar1);//Assign to class

        }catch (Exception e){
            Assert.fail("Exception in testcase 'instructorSharingTheAssessmentWithPublic' in class 'InstructorSharingTheAssessment'",e);
        }
    }

    @Test(priority = 4,enabled = true)
    //Instructors should be able to view the assessments shared by others based on the filter selected- " Me"
    public void instructorSharingTheAssessmentWithMe(){
        try{String appendChar = "a";
            int dataIndex = 235;
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            Assign assign = PageFactory.initElements(driver,Assign.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            MatchingTablesQuestionCreation matchingTables = PageFactory.initElements(driver, MatchingTablesQuestionCreation.class);
            AssessmentLibrary assessmentLibrary = PageFactory.initElements(driver,AssessmentLibrary.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar,dataIndex);//SignUp a teacher
            new School().createWithOnlyName(appendChar, dataIndex);
            new Classes().createClass(appendChar, dataIndex);//create class
            new Assignment().create(dataIndex, "truefalse");//Create a true false questions
            matchingTables.getButton_review().click();//Click on review button
            listPage.getButton_next().click();//click on next button to assign
            assign(dataIndex,appendChar);//Assign the assignment to class as Private
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.getButton_newAssessment().click();//Click on create new assignment
            assignments.getSearch().click();//Click on search
            assessmentLibrary.getRadioButtons_owner().get(0).click();//Click on Me radio button
            Thread.sleep(5000);

            //Expected - Instructors should be able to view and share the assessments created by himself
            Assert.assertEquals(assessmentLibrary.getList_assessment().get(0).getText(), assessmentname, "2nd Instructor from the same school is not able to view the same assessment assigned to school");

            assessmentLibrary.getList_assessment().get(0).click();//Select the assessment
            assessmentLibrary.getList_createNewVersion().get(0).click();//Click on customize button
            listPage.getButton_next().click();//Click on Next button

            Assert.assertEquals(assign.getRadioButton_district().isEnabled(),true,"District button is not enabled");
            Assert.assertEquals(assign.getRadioButton_public().isEnabled(),true,"Public button is not enabled");
            Assert.assertEquals(assign.getRadioButton_school().isEnabled(),true,"School button is not enabled");
            Assert.assertEquals(assign.getRadioButton_private().isEnabled(),true,"Private button is not enabled");

            assign(dataIndex,appendChar);//Assign to class

        }catch (Exception e){
            Assert.fail("Exception in testcase 'instructorSharingTheAssessmentWithMe' in class 'InstructorSharingTheAssessment'",e);
        }
    }


    public  void assign(int index,String... appendChar) {
        try {
            int dataIndex=index;
            String[] appendCharacter = appendChar;
            String shareWithClass = ReadTestData.readDataByTagName("", "shareWithClass", Integer.toString(dataIndex));
            String className = ReadTestData.readDataByTagName("", "className", Integer.toString(dataIndex));
            String gradable = ReadTestData.readDataByTagName("", "gradable", Integer.toString(dataIndex));
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(dataIndex));
            String duetime = ReadTestData.readDataByTagName("", "duetime", Integer.toString(dataIndex));
            String accessibleafter = ReadTestData.readDataByTagName("", "accessibleafter", Integer.toString(dataIndex));
            String gradereleaseoption = ReadTestData.readDataByTagName("", "gradereleaseoption", Integer.toString(dataIndex));
            String description = ReadTestData.readDataByTagName("", "description", Integer.toString(dataIndex));
            String tags = ReadTestData.readDataByTagName("", "tags", Integer.toString(dataIndex));
            String privacy = ReadTestData.readDataByTagName("", "privacy", Integer.toString(dataIndex));

            String appendCharacterBuild=System.getProperty("UCHAR");
            if (appendCharacterBuild!=null)
                appendCharacter[0]=appendCharacter[0]+appendCharacterBuild;


            new Click().clickByXpath("//input[@id='assign-now']/..");//Click on right now
            String methodName = new Exception().getStackTrace()[1].getMethodName();
            String shareName;
            boolean removeExistingShare = true;
           if(appendCharacter.length == 0) {
                shareName = className;
                if (shareWithClass != null) {
                    if (shareWithClass.equalsIgnoreCase("true")) {
                        shareName = className;
                    }
                }
                new ShareWith().share(dataIndex, shareName, removeExistingShare);
            }
            else {
                for (int i = 0; i < appendCharacter.length; i++) {
                    if (shareWithClass != null) {
                        if (shareWithClass.equalsIgnoreCase("true")) {
                            shareName = methodName + "class" + appendCharacter[i];
                        } else
                            shareName = methodName + "st" + appendCharacter[i];
                    } else
                        shareName = methodName + "st" + appendCharacter[i];
                    if (i > 0) removeExistingShare = false;
                    new ShareWith().share(dataIndex, shareName, removeExistingShare);
                }
            }
            driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).clear();//short label clear
            driver.findElement(By.xpath("//input[starts-with(@class,'form-control input-lg lsm-assignment-input total_point')]")).sendKeys("sh"+new RandomString().randominteger(2));//short label name
            if (gradable.equals("true")) {
                new Click().clickByclassname("icheckbox_square-green");//click on Gradable Checkbox
                if (gradereleaseoption != null) {
                    if (gradereleaseoption.equals("On assignment submission"))
                        new Click().clickByXpath("//input[@id='as-grade-release-on-submission']/parent::div");//click on On assignment submission
                    if (gradereleaseoption.equals("Explicitly by teacher"))
                        new Click().clickByXpath("//input[@id='as-grade-release-by-teacher']/parent::div");//click on Explicitly by teacher
                }
            }
            if (accessibleafter != null) {

                new Click().clickByXpath("//input[@id='start-later']/parent::div");//Click on Later
                new Click().clickByid("lsm_assignment_accessible_date");
                new Click().clickBycssselector("span[class='ui-icon ui-icon-circle-triangle-e']");
                new Click().clickbylinkText(accessibleafter);
            }
            new Click().clickByid("lsm_assignment_due_date");//click on Due Date field
            new Click().clickBycssselector("span[class='ui-icon ui-icon-circle-triangle-e']");
            new Click().clickbylinkText(duedate);//select due date
            new Click().clickByid("lsm_assignment_due_time");//click on Due Time field
            List<WebElement> elements = driver.findElements(By.xpath("//li"));
            for (WebElement time : elements) {
                if (time.getText().equals(duetime)) {
                    time.click();
                    break;
                }
            }
            driver.findElement(By.id("as-description")).click();
            driver.findElement(By.id("as-description")).sendKeys(description);//add description
            driver.findElement(By.id("as-tag")).click();
            driver.findElement(By.id("as-tag")).sendKeys(tags);//add tags
            if (privacy != null) {
                if (privacy.equalsIgnoreCase("Private"))
                    new Click().clickByXpath("//input[@id='private']/parent::div");//click on Private Option

                if (privacy.equalsIgnoreCase("Public"))
                    new Click().clickByXpath("//input[@id='public']/parent::div");//click on With All Option

                if (privacy.equalsIgnoreCase("district")){
                    new Click().clickByXpath("//input[@id='district']/parent::div");//click on District Option
                }

                if (privacy.equalsIgnoreCase("school")) {
                    new Click().clickByXpath("//input[@id='school']/parent::div");//click on school Option
                }
            }
            new Click().clickByid("assign-button");//click on Assign button


        } catch (Exception e1) {
            Assert.fail("Exception in testcase 'assign' in class 'InstructorSharingTheAssessment'", e1);
        }


    }
}