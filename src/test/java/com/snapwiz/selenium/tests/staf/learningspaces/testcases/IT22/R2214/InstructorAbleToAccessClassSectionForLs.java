package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT22.R2214;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.EngagementReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.MetacognitiveReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProductivityReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
import com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R199.ImpactsOnAssignmentDiscussionOnTheStudentSide;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

    /**
     * Created by priyanka on 10/13/2015.
     */
    public class InstructorAbleToAccessClassSectionForLs extends Driver {

        @Test(priority = 1,enabled = true)
        public void instructorAbleToAccessClassSection()
        {
            try {
                Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
                LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
                String contexttitle = ReadTestData.readDataByTagName("", "context_title", Integer.toString(13));
                System.out.println(contexttitle);

                new LoginUsingLTI().ltiLogin("13"); //Login as instructor
                lessonPage.getClassSectionDropDown().click();//click on class section dropdown
                Assert.assertEquals(lessonPage.getClassName().getText(), "Biology's 101", "Active class section name is not dispalying");
                lessonPage.getClassName().click();//click on active class section
                Assert.assertEquals(lessonPage.classSectionName.getText(), "Biology's 101", "Instructor is not able to access Active class section");

                new LoginUsingLTI().ltiLogin("13_1"); //Login as student
                Assert.assertEquals(dashboard.defaultSectionName.getText(), contexttitle, "student is not able to access Active class section");

            } catch (Exception e) {
                Assert.fail("Exception in testcase instructorAbleToAccessClassSection of class InstructorAbleToAccessClassSectionForLs", e);
            }
        }

        @Test(priority = 2,enabled = true)
        public void instructorAbleToAccessInActiveClassSection()
        {
            try {
                Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
                LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
                String contexttitle = ReadTestData.readDataByTagName("", "context_title", Integer.toString(25));
                System.out.println(contexttitle);

                new LoginUsingLTI().ltiLogin("25"); //Login as instructor
                lessonPage.getClassSectionDropDown().click();//click on class section dropdown
                Assert.assertEquals(dashboard.classSectionDropDown.get(0).getText(), "Inactive", "InActive class section name is not dispalying");
                dashboard.classSectionDropDown.get(0).click();//click on active class section
                Assert.assertEquals(lessonPage.classSectionName.getText(), "Inactive", "Instructor is not able to access InActive class section");

                new LoginUsingLTI().ltiLogin("25_2"); //Login as inactive instructor
                Assert.assertEquals(lessonPage.classSectionName.getText(), "Inactive", "Instructor is not able to access InActive class section");

                new LoginUsingLTI().ltiLogin("25_1"); //Login as student
                Assert.assertEquals(dashboard.defaultSectionName.getText(), contexttitle, "student is not able to access InActive class section");

            } catch (Exception e) {
                Assert.fail("Exception in testcase instructorAbleToAccessInActiveClassSection of class InstructorAbleToAccessClassSectionForLs", e);
            }
        }

        @Test(priority = 3,enabled = true)
        public void instructorNotAbleToAccessFinishedClassSection()
        {
            try {
                Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
                String contexttitle = ReadTestData.readDataByTagName("", "context_title", Integer.toString(33));
                System.out.println(contexttitle);

                new LoginUsingLTI().ltiLogin("33"); //Login as instructor
                Assert.assertEquals(new BooleanValue().presenceOfElement(12, By.xpath("//a[@title='Finished']")),false,"Finish class section is displaying");

                new LoginUsingLTI().ltiLogin("33_2"); //Login as instructor finished class section
                Assert.assertEquals(dashboard.disabledClassSection.getCssValue("background-color"), "rgba(196, 196, 196, 1)", "Finished class section is not grayed-out");

                new LoginUsingLTI().ltiLogin("33_1"); //Login as student
                Assert.assertEquals(dashboard.defaultSectionName.getText(), contexttitle, "student is not able to access InActive class section");

            } catch (Exception e) {
                Assert.fail("Exception in testcase instructorNotAbleToAccessFinishedClassSection of class InstructorAbleToAccessClassSectionForLs", e);
            }
        }


        @Test(priority = 4,enabled = true)
        public void realTimeCommunicationOfRosterToLearningSpaceForRemovedStatus()
        {
            try {
                //tc row no 40
                String contexttitle = ReadTestData.readDataByTagName("", "context_title", Integer.toString(33));
                System.out.println(contexttitle);

                new LoginUsingLTI().ltiLogin("40"); //Login as instructor

                new LoginUsingLTI().ltiLogin("40_1"); //Login as student
                new LoginUsingLTI().ltiLogin("40_2"); //Login as student
                String updateRoster=driver.findElement(By.xpath("html/body")).getText();
                Assert.assertEquals(updateRoster,"Custom Roster Changes have been updated successfully.", "student is not removed");

                new LoginUsingLTI().ltiLogin("40_1"); //Login as student
                new Navigator().NavigateTo("Course Stream");

            } catch (Exception e) {
                Assert.fail("Exception in testcase realTimeCommunicationOfRosterToLearningSpaceForRemovedStatus of class InstructorAbleToAccessClassSectionForLs", e);
            }
        }


        @Test(priority = 19,enabled = true)
        public void removedStatusWihTwoStudent()
        {
            try {
                //tc row no 47
                Dashboard dashboard;
                CurrentAssignments currentAssignments;
                ProficiencyReport proficiencyReport;
                MyQuestionBank myQuestionBank ;
                NewAssignment newAssignment;
                QuestionBank questionBank;
                LessonPage lessonPage;
                Assignments assignments;

                currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
                String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "47");
                String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "47");
                String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "47");
                assignments = PageFactory.initElements(driver, Assignments.class);



                new LoginUsingLTI().ltiLogin("47"); //Login as instructor
                new Assignment().assignToStudent(47);
                Assert.assertEquals(currentAssignments.status.getText(),"Available for Students","status is not available for student");

                new LoginUsingLTI().ltiLogin("47_1"); //Login as student1
                new Assignment().submitAssignmentAsStudent(47);

                new LoginUsingLTI().ltiLogin("47_2"); //Login as student2
                new Navigator().NavigateTo("Assignments"); //navigate to assignment
                assignments.getAssignmentName().click();//click on the assignment name
                Thread.sleep(3000);
                new AttemptQuestion().trueFalse(false, "correct", 47);
                new Assignment().nextButtonInQuestionClick(); //click on the next button
                Thread.sleep(2000);
                new AttemptQuestion().trueFalse(false, "correct", 47);
                new Assignment().nextButtonInQuestionClick(); //click on the next button
                Thread.sleep(2000);
                new Assignment().nextButtonInQuestionClick(); //click on the next button
                assignments.getFinishAssignment().click();


                driver.quit();
                driver= new FirefoxDriver();
                driver.manage().window().maximize();

                new LoginUsingLTI().ltiLogin("47"); //Login as instructor
                currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

                new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
                currentAssignments.getViewGrade_link().click();//click on view student responces
                new Assignment().releaseGrades(47, "Release Grade for All");

                new RunScheduledJobs().runScheduledJobsForDashboard();

                new LoginUsingLTI().ltiLogin("47"); //Login as instructor
                dashboard=PageFactory.initElements(driver, Dashboard.class);
                proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
                String questionAttempt=dashboard.dashboardPerformance.get(2).getText();
                System.out.println("questionAttempt :"+questionAttempt);

                String questionPerformance=dashboard.dashboardPerformance.get(1).getText();
                System.out.println("questionPerformance :"+questionPerformance);

                String avgTimeSpent=dashboard.dashboard_AvgTime.getText();
                System.out.println("avgTimeSpent :"+avgTimeSpent);


                String overallScore=  proficiencyReport.courseProficiencys.get(1).getText().trim();
                System.out.println("overallScore:"+overallScore);

                String height=dashboard.getGradedBarChart().getAttribute("height");
                int recentlyGraded= Integer.parseInt(height);
                System.out.println("recentlyGraded:"+recentlyGraded);

                new LoginUsingLTI().ltiLogin("47_3"); //removed student1

                new LoginUsingLTI().ltiLogin("47_2"); //Login as student2
                new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
                new SelectCourse().selectInvisibleAssignment("1.1 Concept Check"); //open static assessments of fourth chapter
                new AttemptTest().StaticTest();// start static test


                new RunScheduledJobs().runScheduledJob("ClassSectionActivityJob");
                new RunScheduledJobs().runScheduledJob("ClassSectionAssignmentPercentileJob");
                new RunScheduledJobs().runScheduledJob("ClassSectionPerformanceJob");
                new RunScheduledJobs().runScheduledJob("ProedStudentDashboardJob");
                new RunScheduledJobs().runScheduledJob("QuestionMetricsReportJob");

                new LoginUsingLTI().ltiLogin("47"); //Login as instructor
                dashboard=PageFactory.initElements(driver, Dashboard.class);
                currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
                proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
                myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
                newAssignment = PageFactory.initElements(driver, NewAssignment.class);
                questionBank = PageFactory.initElements(driver, QuestionBank.class);
                lessonPage = PageFactory.initElements(driver, LessonPage.class);

                String questionAttempt1=dashboard.dashboardPerformance.get(2).getText();
                System.out.println("questionAttempt :"+questionAttempt1);

                String questionPerformance1=dashboard.dashboardPerformance.get(1).getText();
                System.out.println("questionPerformance :"+questionPerformance1);

                String avgTimeSpent1=dashboard.timeSpent.getText();
                System.out.println("avgTimeSpent :" + avgTimeSpent1);

                String overallScore1=  proficiencyReport.courseProficiencys.get(1).getText().trim();
                System.out.println("overallScore:"+overallScore1);

                String height1=dashboard.getGradedBarChart().getAttribute("height");
                int recentlyGraded1= Integer.parseInt(height1);
                System.out.println("recentlyGraded:"+recentlyGraded1);

                if(questionAttempt.equals(questionAttempt))
                    Assert.fail("No of question attempt is same after deleting one student");

                if(questionPerformance.equals(questionPerformance1))
                    Assert.fail("questionPerformance is same after deleting one student");

                if(avgTimeSpent.equals(avgTimeSpent1))
                    Assert.fail("No of question attempt is same after deleting one student");

                if(overallScore.equals(overallScore1))
                    Assert.fail("No of question attempt is same after deleting one student");

                if(recentlyGraded==recentlyGraded1)
                    Assert.fail("No of question attempt is same after deleting one student");

                new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
                currentAssignments.getViewGrade_link().click();//click on view student responces
                if(currentAssignments.enterGrade_mouseOver.size() !=1){
                    Assert.fail("Deleted student name is also displaying in the list");
                }

                new LoginUsingLTI().ltiLogin("47"); //Login as instructor
                new Navigator().NavigateTo("My Question Bank");//click on my question bank
                myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
                Thread.sleep(2000);
                new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
                new AssignLesson().selectQuestionForCustomAssignment("47");//select two question
                myQuestionBank.getAssignmentNameField().click();//click on name field
                myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
                newAssignment.assignNowButton.click();//click on assign now
                lessonPage.close_Button.click();//click on close symbol
                lessonPage.close_Button.click();//click on close symbol
                lessonPage.close_Button.click();//click on close symbol
                lessonPage.assignTo_textBox.sendKeys("family1");
                Assert.assertEquals(currentAssignments.noResultMessage.getText(),"No results found","deleted student name is available");

                new Navigator().NavigateTo("My Question Bank");//click on my question bank
                questionBank.getQuestionBankTitle().click();//click on question bank
                Thread.sleep(2000);
                questionBank.getSearchInputFieldOnQuestionBank().get(1).sendKeys("\"" + assignmentname + "\"");
                questionBank.getSearchButtonOnQuestionBank().get(1).click();
                Thread.sleep(2000);
                questionBank.getAssignThisButtton().click();
                lessonPage.close_Button.click();//click on close symbol
                lessonPage.close_Button.click();//click on close symbol
                lessonPage.close_Button.click();//click on close symbol
                lessonPage.assignTo_textBox.sendKeys("family1");
                Assert.assertEquals(currentAssignments.noResultMessage.getText(),"No results found","deleted student name is available");

                new Navigator().NavigateTo("Current Assignments");//click on current assignment
                currentAssignments.newAssignment_Button.click();//click on new assignment;
                currentAssignments.createFileBasedAssignmentButton.click();//click on file based assignment
                newAssignment.assessmentNameTextBox.click();//click on the text box
                Thread.sleep(2000);
                newAssignment.assessmentName_TextBox.sendKeys("Assessment");
                driver.findElement(By.id("question-prompt-raw-content")).click();
                new ImpactsOnAssignmentDiscussionOnTheStudentSide().fileUploadNotificationMessageValidate("47", "Your file upload request is being processed...");
                new UIElement().waitAndFindElement(By.className("ls-uploaded-file"));
                newAssignment.assignNowButton.click();//click on assign now
                lessonPage.close_Button.click();//click on close symbol
                lessonPage.close_Button.click();//click on close symbol
                lessonPage.close_Button.click();//click on close symbol
                driver.findElement(By.className("maininput")).sendKeys("family1");
                Assert.assertEquals(currentAssignments.noResultMessage.getText(),"No results found","deleted student name is available");
                newAssignment.assignNowButton.click();//click on assign now
                newAssignment.saveForLater_Button.click(); //click on save for later
                Thread.sleep(5000);
                new Navigator().NavigateTo("Current Assignments");//click on current assignment
                new Assignment().assignFileBasedAssignmentFromMyQuestionBank(47);
                currentAssignments.getUpdateAssignment_button().click();
                Thread.sleep(2000);
                lessonPage.assignTo_textBox.sendKeys("family1");
                Assert.assertEquals(currentAssignments.noResultMessage.getText(),"No results found","deleted student name is available");

            } catch (Exception e) {
                Assert.fail("Exception in testcase removedStatusWihTwoStudent of class InstructorAbleToAccessClassSectionForLs", e);
            }
        }



        @Test(priority = 6,enabled = true)
        public void statusOfTheAssignment()
        {
            try {
                //tc row no 88
                Dashboard dashboard;
                dashboard=PageFactory.initElements(driver, Dashboard.class);

                new Assignment().create(88);
                new LoginUsingLTI().ltiLogin("88"); //Login as instructor

                new LoginUsingLTI().ltiLogin("88_3"); //removed student1

                new LoginUsingLTI().ltiLogin("88"); //Login as instructor
                new Assignment().assignToStudent(88);

                new LoginUsingLTI().ltiLogin("88_1"); //Login as student1
                new Assignment().submitAssignmentAsStudent(88);

                new LoginUsingLTI().ltiLogin("88"); //Login as instructor
                new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
                new LoginUsingLTI().ltiLogin("88_2"); //log in as student2

                new LoginUsingLTI().ltiLogin("88_4"); //removed student2

                new LoginUsingLTI().ltiLogin("88"); //Login as instructor
                Assert.assertEquals(dashboard.submittedStatus.get(0).getText(),"1","status is not showing submitted");


            } catch (Exception e) {
                Assert.fail("Exception in testcase statusOfTheAssignment of class InstructorAbleToAccessClassSectionForLs", e);
            }
        }

        @Test(priority = 7,enabled = true)
        public void studyPlanPage()
        {
            try {
                //tc row no 95

                new LoginUsingLTI().ltiLogin("95"); //Login as instructor
                new LoginUsingLTI().ltiLogin("95_1"); //Login as student1
                new LoginUsingLTI().ltiLogin("95_2"); //Login as student2
                new LoginUsingLTI().ltiLogin("95_3"); //removed student1

            } catch (Exception e) {
                Assert.fail("Exception in testcase studyPlanPage of class InstructorAbleToAccessClassSection", e);
            }
        }

        @Test(priority = 7,enabled = true)
        public void studyPlanPageInstructor()
        {
            try {

                LessonPage lessonPage;
                CurrentAssignments currentAssignments;
                AssignmentTab assignmentTab;
                assignmentTab= PageFactory.initElements(driver, AssignmentTab.class);
                currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
                lessonPage = PageFactory.initElements(driver, LessonPage.class);


                new LoginUsingLTI().ltiLogin("95"); //Login as instructor
                new Navigator().NavigateTo("eTextbook");
                lessonPage.getAllLessonAssign().click();//click on assign
                lessonPage.assignButton.click();//click on assign
                lessonPage.close_Button.click();//click on close symbol
                lessonPage.assignTo_textBox.sendKeys("family1");
                Assert.assertEquals(currentAssignments.noResultMessage.getText(), "No results found", "deleted student name is available");


                new Navigator().NavigateTo("e-Textbook");
                new TopicOpen().chapterOpen(3);
                new TopicOpen().clickOnStaticAssessmentArrow("4.1 Concept Check");
                lessonPage.assignThis_link.click();//Click on assign this link
                lessonPage.assignTo_textBox.sendKeys("family1");
                Assert.assertEquals(currentAssignments.noResultMessage.getText(), "No results found", "deleted student name is available");

                new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
                new TOCShow().tocHide();
                new Navigator().navigateToTab("Assignments");//navigate to resource tab
                assignmentTab.rightArrow.click();//click on right arrow
                assignmentTab.assignThis.click();//click on assign this
                lessonPage.assignTo_textBox.sendKeys("family1");
                Assert.assertEquals(currentAssignments.noResultMessage.getText(), "No results found", "deleted student name is available");

                new Navigator().navigateToResourceTab();
                lessonPage.arrow_resourceTab.click();//click on right arrow
                List<WebElement> allAssign = driver.findElements(By.xpath("//a[@title='Assign this']"));
                for(WebElement link:allAssign){
                    if(link.isDisplayed()){
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",link);
                    }
                }
                lessonPage.assignTo_textBox.sendKeys("family1");
                Assert.assertEquals(currentAssignments.noResultMessage.getText(), "No results found", "deleted student name is available");


                new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
                new TOCShow().tocHide();
                lessonPage.getAssignForImage().click();     //click on Assign icon for image widget
                lessonPage.getAssignThisLinkForImage().click(); //click on Assign This link for image widget
                lessonPage.assignTo_textBox.sendKeys("family1");
                Assert.assertEquals(currentAssignments.noResultMessage.getText(), "No results found", "deleted student name is available");


                new Navigator().navigateToResourceTab();
                lessonPage.arrow_resourceTab.click();//click on right arrow
                List<WebElement> allOpenLink = driver.findElements(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-forward-bg']"));
                for(WebElement link:allOpenLink){
                    if(link.isDisplayed()){
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",link);
                    }
                }

                lessonPage.bookmarkResource.click();//click on bookmark

                new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
                new TOCShow().tocHide();
                new Navigator().navigateToTab("Fav");
                lessonPage.arrow_resourceTab.click();//click on right arrow
                List<WebElement> allAssign1 = driver.findElements(By.xpath("//a[@title='Assign this']"));
                for(WebElement link:allAssign1){
                    if(link.isDisplayed()){
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",link);
                    }
                }
                lessonPage.assignTo_textBox.sendKeys("family1");
                Assert.assertEquals(currentAssignments.noResultMessage.getText(), "No results found", "deleted student name is available");


                new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
                new TopicOpen().chapterOpen(1);//Open chapter 2
                lessonPage.static_arrow1.click();//Click on static arrow of chapter 2
                lessonPage.assignThis_link.click();//Click on assign this link
                lessonPage.assignTo_textBox.sendKeys("family1");
                Assert.assertEquals(currentAssignments.noResultMessage.getText(), "No results found", "deleted student name is available");

                new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
                new TopicOpen().clickOnAdaptivePracticeArrow();
                new TopicOpen().clickOnAssignThisIcon();
                lessonPage.assignTo_textBox.sendKeys("family1");
                Assert.assertEquals(currentAssignments.noResultMessage.getText(), "No results found", "deleted student name is available");

            } catch (Exception e) {
                Assert.fail("Exception in testcase studyPlanPageInstructor of class InstructorAbleToAccessClassSectionForLs", e);
            }
        }



        @Test(priority = 9,enabled = true)
        public void statusOfTheAssignmentGradeBookPage()
        {
            try {
                //tc row no 124

                new Assignment().create(124);

                new LoginUsingLTI().ltiLogin("124_1"); //Login as student1
                new LoginUsingLTI().ltiLogin("124_2"); //Login as student2

            } catch (Exception e) {
                Assert.fail("Exception in testcase statusOfTheAssignmentGradeBookPage of class InstructorAbleToAccessClassSectionForLs", e);
            }
        }

        @Test(priority = 10,enabled = true)
        public void statusOfTheAssignmentGradeBookPageInstructor()

        {
            try {
                //tc row no 124
                Gradebook gradebook;
                LessonPage lessonPage;
                CurrentAssignments currentAssignments;
                CourseStreamPage courseStreamPage ;
                courseStreamPage= PageFactory.initElements(driver, CourseStreamPage.class);
                currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
                lessonPage = PageFactory.initElements(driver, LessonPage.class);
                gradebook = PageFactory.initElements(driver, Gradebook.class);


                new LoginUsingLTI().ltiLogin("124"); //Login as instructor
                new Assignment().assignToStudent(124);

                new LoginUsingLTI().ltiLogin("124_3"); //removed student1

                new LoginUsingLTI().ltiLogin("124"); //Login as instructor
                new Navigator().NavigateTo("Gradebook");//Navigate to gradebook
                if(gradebook.getLeftContentCount().get(0).getText().contains("family1, givenname1"))
                    Assert.fail("Deleted student name is also displaying");

                new Navigator().NavigateTo("Course Stream");
                courseStreamPage.getPost_button().click();//click on post
                lessonPage.assignTo_textBox.sendKeys("family1");
                Assert.assertEquals(currentAssignments.noResultMessage.getText(), "No results found", "deleted student name is available");

                new Navigator().NavigateTo("Resources");
                new Click().clickbylist("assign-this", 0);
                lessonPage.assignTo_textBox.sendKeys("family1");
                Assert.assertEquals(currentAssignments.noResultMessage.getText(), "No results found", "deleted student name is available");

            } catch (Exception e) {
                Assert.fail("Exception in testcase statusOfTheAssignmentGradeBookPageInstructor of class InstructorAbleToAccessClassSectionForLs", e);
            }
        }



        @Test(priority = 11,enabled = true)
        public void assignmentFlowAndTheirBehaviour()
        {
            try {
                //tc row no 166

                new Assignment().create(166);
                new Assignment().addQuestions(166, "truefalse", "");
                new Assignment().addQuestions(166,"truefalse","");
                new Assignment().addQuestions(166,"truefalse","");

                new LoginUsingLTI().ltiLogin("166_1"); //Login as student1
                new LoginUsingLTI().ltiLogin("166_2"); //Login as student2


            } catch (Exception e) {
                Assert.fail("Exception in testcase assignmentFlowAndTheirBehaviour of class InstructorAbleToAccessClassSectionForLs", e);
            }
        }


        @Test(priority = 12,enabled = true)
        public void assignmentFlowAndTheirBehaviourInstructor()
        {
            try {
                //tc row no 166
                Dashboard dashboard;
                CurrentAssignments currentAssignments;
                dashboard=PageFactory.initElements(driver, Dashboard.class);

                new LoginUsingLTI().ltiLogin("166"); //Login as instructor
                new Assignment().assignToStudent(166);

                new Navigator().NavigateTo("Dashboard");
                Assert.assertEquals(dashboard.availableForStudent.get(1).getText(),"Available for Students","status is not available for student");


                new LoginUsingLTI().ltiLogin("166_1"); //Login as student1
                new Assignment().submitAssignmentAsStudent(166);

                new LoginUsingLTI().ltiLogin("166_3"); //removed as student2

                driver.quit();
                driver= new FirefoxDriver();
                driver.manage().window().maximize();

                new LoginUsingLTI().ltiLogin("166"); //Login as instructor
                currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

                new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
                currentAssignments.getViewGrade_link().click();//click on view student responces
                Thread.sleep(2000);
                driver.findElement(By.xpath("//div[@title='Release Grade for All']")).click();

                new Navigator().NavigateTo("Dashboard");


            } catch (Exception e) {
                Assert.fail("Exception in testcase assignmentFlowAndTheirBehaviourInstructor of class InstructorAbleToAccessClassSectionForLs", e);
            }
        }


        @Test(priority = 13,enabled = true)
        public void assignmentFlowAndTheirBehaviourPolicyOne()
        {
            try {
                //tc row no 170
                String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "170");
                CurrentAssignments currentAssignments;
                Dashboard dashboard;

                new LoginUsingLTI().ltiLogin("170"); //login as instructor
                new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
                new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//till save policy
                new Assignment().assignToStudent(170);



                new LoginUsingLTI().ltiLogin("170_1"); //Login as student1
                new Assignment().submitAssignmentAsStudent(170);

                new LoginUsingLTI().ltiLogin("170_2"); //Login as student2
                new LoginUsingLTI().ltiLogin("170_3"); //removed as student2

                driver.quit();
                driver= new FirefoxDriver();
                driver.manage().window().maximize();

                new LoginUsingLTI().ltiLogin("170"); //login as instructor
                currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
                dashboard=PageFactory.initElements(driver, Dashboard.class);

                new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
                Assert.assertEquals(currentAssignments.gradedStatus.getText(), "Graded", "status is not graded");

                new Navigator().NavigateTo("Dashboard");
                Assert.assertEquals(dashboard.submittedStatus.get(3).getText(),"1","status is not showing submitted");

            } catch (Exception e) {
                Assert.fail("Exception in testcase assignmentFlowAndTheirBehaviour of class InstructorAbleToAccessClassSectionForLs", e);
            }
        }

        @Test(priority = 14,enabled = true)
        public void assignmentFlowAndTheirBehaviourPolicyThree()
        {
            try {
                //tc row no 174
                String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "174");
                CurrentAssignments currentAssignments;
                Dashboard dashboard;

                new Assignment().create(174);
                new Assignment().addQuestions(174, "essay", "");
                new Assignment().addQuestions(174, "essay", "");

                new LoginUsingLTI().ltiLogin("174"); //login as instructor
                new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
                new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "");//policy three
                new Assignment().assignToStudent(174);



                new LoginUsingLTI().ltiLogin("174_1"); //Login as student1
                new Assignment().submitAssignmentAsStudent(174);

                new LoginUsingLTI().ltiLogin("174_2"); //Login as student2
                new LoginUsingLTI().ltiLogin("174_3"); //removed as student2

                driver.quit();
                driver= new FirefoxDriver();
                driver.manage().window().maximize();

                new LoginUsingLTI().ltiLogin("174"); //login as instructor
                currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
                dashboard=PageFactory.initElements(driver, Dashboard.class);

                new Assignment().provideGradeToStudentForMultipleQuestions(174);

                new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
                Assert.assertEquals(currentAssignments.gradedStatus.getText(), "Graded", "status is not graded");

                new Navigator().NavigateTo("Dashboard");
                Assert.assertEquals(dashboard.submittedStatus.get(3).getText(),"1","status is not showing submitted");

            } catch (Exception e) {
                Assert.fail("Exception in testcase assignmentFlowAndTheirBehaviour of class InstructorAbleToAccessClassSectionForLs", e);
            }
        }

        @Test(priority = 15,enabled = true)
        public void assignmentFlowAndTheirBehaviourPolicyFour()
        {
            try {
                //tc row no 186
                String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "186");
                CurrentAssignments currentAssignments;
                Dashboard dashboard;

                new LoginUsingLTI().ltiLogin("186"); //login as instructor
                new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
                new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release explicitly by the instructor", "", "", "");//policy four
                new Assignment().assignToStudent(186);


                new LoginUsingLTI().ltiLogin("186_1"); //Login as student1
                new Assignment().submitAssignmentAsStudent(186);

                new LoginUsingLTI().ltiLogin("186_2"); //Login as student2
                new LoginUsingLTI().ltiLogin("186_3"); //removed as student2

                driver.quit();
                driver= new FirefoxDriver();
                driver.manage().window().maximize();

                new LoginUsingLTI().ltiLogin("186"); //login as instructor
                currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
                dashboard=PageFactory.initElements(driver, Dashboard.class);


                new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
                currentAssignments.getViewGrade_link().click();//click on view student responces
                new Assignment().releaseGrades(186, "Release Grade for All");
                new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
                Assert.assertEquals(currentAssignments.gradedStatus.getText(), "Graded", "status is not graded");


                new Navigator().NavigateTo("Dashboard");
                Assert.assertEquals(dashboard.submittedStatus.get(3).getText(),"1","status is not showing submitted");

            } catch (Exception e) {
                Assert.fail("Exception in testcase assignmentFlowAndTheirBehaviourPolicyFour of class InstructorAbleToAccessClassSectionForLs", e);
            }
        }

        @Test(priority = 16,enabled = true)
        public void assignmentFlowAndTheirBehaviourNonGradeAble()
        {
            try {
                //tc row no 190
                CurrentAssignments currentAssignments;
                Dashboard dashboard;

                new LoginUsingLTI().ltiLogin("190"); //login as instructor
                new Assignment().assignToStudent(190);


                new LoginUsingLTI().ltiLogin("190_1"); //Login as student1
                new Assignment().submitAssignmentAsStudent(190);

                new LoginUsingLTI().ltiLogin("190_2"); //Login as student2
                new LoginUsingLTI().ltiLogin("190_3"); //removed as student2

                driver.quit();
                driver= new FirefoxDriver();
                driver.manage().window().maximize();

                new LoginUsingLTI().ltiLogin("190"); //login as instructor
                currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
                dashboard=PageFactory.initElements(driver, Dashboard.class);


                new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
                currentAssignments.getViewGrade_link().click();//click on view student responces
                new Assignment().releaseGrades(190, "Release Feedback for All");

                new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
                Assert.assertEquals(currentAssignments.gradedStatus.getText(), "Reviewed", "status is not graded");


                new Navigator().NavigateTo("Dashboard");
                Assert.assertEquals(dashboard.submittedStatus.get(3).getText(),"1","status is not showing submitted");

            } catch (Exception e) {
                Assert.fail("Exception in testcase assignmentFlowAndTheirBehaviourNonGradeAble of class InstructorAbleToAccessClassSectionForLs", e);
            }
        }


        @Test(priority = 17,enabled = true)
        public void removeStudentFromClassSection()
        {
            try {
                //tc row no 198

                new LoginUsingLTI().ltiLogin("198"); //login as instructor

                new LoginUsingLTI().ltiLogin("198_1"); //Login as student1

                new LoginUsingLTI().ltiLogin("198_2"); //Login as student2
                String updateRoster=driver.findElement(By.xpath("html/body")).getText();
                Assert.assertEquals(updateRoster, "BAD REQUEST :: No students were removed", "student is not removed");

                new LoginUsingLTI().ltiLogin("198_3"); //Login as student
                String updateRoster1=driver.findElement(By.xpath("html/body")).getText();
                Assert.assertEquals(updateRoster1,"Custom Roster Changes have been updated successfully.", "student is not removed");


            } catch (Exception e) {
                Assert.fail("Exception in testcase removeStudentFromClassSction of class InstructorAbleToAccessClassSectionForLs", e);
            }
        }



        @Test(priority = 18,enabled = true)
        public void reportPage()
        {
            try {
                //tc row no 140
                ProficiencyReport proficiencyReport;
                MetacognitiveReport metacognitiveReport;
                ProductivityReport productivityReport;
                EngagementReport engagementReport;

                new LoginUsingLTI().ltiLogin("140"); //login as instructor

                new LoginUsingLTI().ltiLogin("140_1"); //Login as student1
                new LoginUsingLTI().ltiLogin("140_2"); //Login as student2

                new LoginUsingLTI().ltiLogin("140_1"); //Login as student1
                new Navigator().NavigateTo("e-Textbook");
                new DiagnosticTest().startTest(4);
                new DiagnosticTest().attemptAllCorrect(0, false, false);
                new Navigator().NavigateTo("e-Textbook");
                new TopicOpen().chapterOpen(1);
                new DiagnosticTest().startTest(4);
                new DiagnosticTest().attemptAllCorrect(0, false, false);

                new LoginUsingLTI().ltiLogin("140_2"); //Login as student2
                new Navigator().NavigateTo("e-Textbook");
                new DiagnosticTest().startTest(4);
                new DiagnosticTest().attemptAllCorrect(0, false, false);

                new LoginUsingLTI().ltiLogin("140_3"); //remove student2

                driver.quit();
                driver= new FirefoxDriver();
                driver.manage().window().maximize();

                new LoginUsingLTI().ltiLogin("140"); //login as instructor
                proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
                metacognitiveReport = PageFactory.initElements(driver, MetacognitiveReport.class);
                productivityReport = PageFactory.initElements(driver, ProductivityReport.class);
                engagementReport = PageFactory.initElements(driver, EngagementReport.class);

                new Navigator().NavigateTo("Proficiency Report");
                if(proficiencyReport.studentName.get(0).getText().contains("family2, givenname2"))
                    Assert.fail("Deleted student name is also displaying");

                new Navigator().NavigateTo("Metacognitive Report");
                if(metacognitiveReport.studentName.get(0).getText().contains("family2, givenname2"))
                    Assert.fail("Deleted student name is also displaying");

                new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
                if(metacognitiveReport.studentNameOnToolTip.getText().contains("family2, givenname2"))
                    Assert.fail("Deleted student name is also displaying");

                new Navigator().NavigateTo("Productivity Report");
                if(productivityReport.studentName.get(0).getText().contains("family2, givenname2"))
                    Assert.fail("Deleted student name is also displaying");

                new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
                if(productivityReport.studentNameOnToolTip.getText().contains("family2, givenname2"))
                    Assert.fail("Deleted student name is also displaying");

                new Navigator().NavigateTo("Engagement Report");
                if(engagementReport.studentName.get(0).getText().contains("family2, givenname2"))
                    Assert.fail("Deleted student name is also displaying");

            } catch (Exception e) {
                Assert.fail("Exception in testcase reportPage of class InstructorAbleToAccessClassSectionForLs", e);
            }
        }
    }


