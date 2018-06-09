package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT21.R214;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Mukesh on 6/19/15.
 */
public  class StudentDashboardParticipationTile extends Driver {
    @Test(priority = 1, enabled = true)
    public void studentDashboardParticipationTileForExistingStudent() {
        try {
            new LoginUsingLTI().ltiLogin("11_1");//login as student 1
            new LoginUsingLTI().ltiLogin("11_2");//login as student 2

            new LoginUsingLTI().ltiLogin("11");//login as instructor
            new Assignment().create(11); //create assignment
            for (int i = 0; i < 4; i++) {
                new Assignment().addQuestions(11, "truefalse", "");
            }


            new LoginUsingLTI().ltiLogin("11");//login as instructor
            new Assignment().assignToStudent(11); //assign to the student

            new LoginUsingLTI().ltiLogin("11_1"); //login as student
            new Assignment().submitAssignmentAsStudent(11); //submit assignment

            new LoginUsingLTI().ltiLogin("11");//login as instructor
            new Assignment().provideGradeToStudentForMultipleQuestions(11);
           // new Assignment().releaseGrades(11, "Release Grade for All");//click on the release Grade


            new LoginUsingLTI().ltiLogin("11_1");//login as student 1
            new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Discussion");
            String discussionText = new RandomString().randomstring(4);
            new Discussion().postDiscussion(discussionText);//post a discussion
            new CommentOnPost().commentOnPost(discussionText, 0);
            new AssignmentSocialElement().clickonlike(0);

            new RunScheduledJobs().runScheduledJobsForDashboard();

            new LoginUsingLTI().ltiLogin("11_1");//login as student 1
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);

            //Tc row no 11
            if (!driver.getCurrentUrl().trim().contains("/lsStudentDashBoard")) {
                Assert.fail("Dashboard is not displayed.");
            }
            //Tc row no 12
            Assert.assertEquals(new TileTextInDashboard().participationRating(1), "PARTICIPATION RATING", "Student is not able to view “PARTICIPATION RATING” tile in the dashboard");
            //Tc ro no 13
            if (new TileTextInDashboard().participationRating(3).equals(" ") || new TileTextInDashboard().participationRating(3).equals("0")) {
                Assert.fail("The percentile score in not having the percentile value.");
            }
            //Tc row no 14
            Assert.assertEquals(dashboard.percentageSymbol.getText().trim(), "%", "After percentile value not showing \" %\" symbol");
            //Tc row no 15 & 16
            Assert.assertEquals(new TileTextInDashboard().participationRating(4), "Compared to your classmates", "The text in the bottom tile is not a “Compared to your classmates”.");

            //Tc row no 17
            dashboard.participationRating_tile.click(); //click on the participationRating_tile
            Thread.sleep(3000);
            if (!driver.getCurrentUrl().trim().contains("/coursestream")) {
                Assert.fail("studnet is not navigated to Course Stream page.");
            }

            new Navigator().NavigateTo("Dashboard"); //navigate to dashboard

            //Tc row no 18
            dashboard.participationRating_help.click(); //click on the participationRating help icon
            String participationRating_helpText = dashboard.participationRating_helpText.getText().trim();
            String helpText = "This shows your level of involvement in class discussions from the Course Stream.";
            if (!participationRating_helpText.equals(helpText)) {
                Assert.fail("This shows your level of involvement in class discussions from the Course Stream is help text is not displaying");

            }


        } catch (Exception e) {
            Assert.fail("Exception in testcase studentDashboardParticipationTileForExistingStudent of class StudentDashboardParticipationTile ", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void studentDashboardParticipationTile() {
        try {
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            new LoginUsingLTI().ltiLogin("19");//login as student 1

            //Tc row no 12
            Assert.assertEquals(new TileTextInDashboard().participationRating(1), "PARTICIPATION RATING", "Student is not able to view “PARTICIPATION RATING” tile in the dashboard");
            //Tc ro no 19
            System.out.println();
            if (!new TileTextInDashboard().participationRating(3).equals("0\n%")) {
                Assert.fail("The percentile score is not showing \"0%\".");
            }
            //Tc row no 20
            Assert.assertEquals(new TileTextInDashboard().participationRating(4), "Compared to your classmates", "The text in the bottom tile is not a “Compared to your classmates”.");

            //Tc row no 22
            dashboard.participationRating_tile.click(); //click on the participationRating_tile
            Thread.sleep(3000);
            if (!driver.getCurrentUrl().trim().contains("/coursestream")) {
                Assert.fail("studnet is not navigated to Course Stream page.");
            }

            new Navigator().NavigateTo("Dashboard"); //navigate to dashboard

            //Tc row no 23
            dashboard.participationRating_help.click(); //click on the participationRating help icon
            String participationRating_helpText = dashboard.participationRating_helpText.getText().trim();
            String helpText = "This shows your level of involvement in class discussions from the Course Stream.";
            if (!participationRating_helpText.equals(helpText)) {
                Assert.fail("This shows your level of involvement in class discussions from the Course Stream is help text is not displaying");

            }


        } catch (Exception e) {
            Assert.fail("Exception in testcase studentDashboardParticipationTile of class StudentDashboardParticipationTile ", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void instructorDashboardAvgParticipationTileForExistingInstructor() {
        try {
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            new LoginUsingLTI().ltiLogin("11");//login as existing instructor

            //Tc row no 25
            if (!driver.getCurrentUrl().trim().contains("/learningSpaceInstructorDashboard")) {
                Assert.fail("Dashboard is not displayed.");
            }

            //Tc row no 26
            Assert.assertEquals(new TileTextInDashboard().participationRating(1), "AVG CLASS PARTICIPATION", "Instructor is not able to view “PARTICIPATION RATING” tile in the dashboard");

            //Tc row no 27
            String classContributionValue = dashboard.percent_value.get(0).getText().trim();
            int classContributionValue1 = Integer.parseInt(classContributionValue);
            String performance = dashboard.getAvgQuestionPerformance().get(0).getText().trim().substring(0, 1);
            int performance1 = Integer.parseInt(performance);
            if (classContributionValue1 % 2 == 0) {
                if (classContributionValue1 / 2 != performance1) {
                    Assert.fail("Not showing the average count of all the contributions1");
                }
            } else {
                if ((classContributionValue1 / 2) + 1 != performance1) {
                    Assert.fail("Not showing the average count of all the contributions2");
                }
            }

            //Tc row no 28
            Assert.assertEquals(dashboard.contribution_perStudent.getText().trim(), "Contributions per student", "“Contributions per Student” below the average count is not below to average count");

            //Tc row no 29
            if (dashboard.contribution_value.getText().equals(" ") || dashboard.contribution_value == null) {
                Assert.fail("The bottom bar is not showing static text - “Total class contributions:<xx> ");
            }

        } catch (Exception e) {
            Assert.fail("Exception in testcase instructorDashboardAvgParticipationTileForExistingInstructor of class StudentDashboardParticipationTile ", e);
        }

    }

    @Test(priority = 4, enabled = true)
    public void calculationOfTotalClassContribution() {
        try {
            Dashboard dashboard = null;
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            new LoginUsingLTI().ltiLogin("30_1");//login as student 1
            new LoginUsingLTI().ltiLogin("30_2");//login as student 2
            new LoginUsingLTI().ltiLogin("30_3");//login as student 3

            new LoginUsingLTI().ltiLogin("30");//login as instructor

            //Tc row no 30
            String classContributionValue=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue,"0","Not showing Total class contributions: 0");

            //Tc row no 31
            String naMessage=new TileTextInDashboard().participationRating(2).substring(0,2);
            Assert.assertEquals(naMessage,"NA","Not showing Avg Class Participation value : NA");


            //Tc row no 32
            new LoginUsingLTI().ltiLogin("30_1");//login as student 1
            new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Discussion");
            String discussionText = new RandomString().randomstring(4);
            new Discussion().postDiscussion(discussionText);//post a discussion
            new RunScheduledJobs().runScheduledJobsForDashboard();

             new LoginUsingLTI().ltiLogin("30");//login as instructor
             dashboard = PageFactory.initElements(driver, Dashboard.class);

            String classContributionValue1=null;
            classContributionValue1=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue1,"1","Not showing Total class contributions: 1");
            Assert.assertEquals(new TileTextInDashboard().participationRating(2).substring(0,2).trim(),"1");

            new LoginUsingLTI().ltiLogin("30_2");//login as student 2
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            new AssignmentSocialElement().clickonlike(0);

            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("30");//login as instructor
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            String classContributionValue2=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue2,"2","Not showing Total class contributions: 2");
            Assert.assertEquals(new TileTextInDashboard().participationRating(2).substring(0,2).trim(),"1");

            //Tc row no 37
           new LoginUsingLTI().ltiLogin("30_3");//login as student 3
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            new CommentOnPost().commentOnPost("comment",0);


            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("30");//login as instructor
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            String classContributionValue3=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue3,"3","Not showing Total class contributions: 3");
            Assert.assertEquals(new TileTextInDashboard().participationRating(2).substring(0,2).trim(),"1");



            //Tc row no 38

            new LoginUsingLTI().ltiLogin("30_1");//login as student 1
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            String random=new RandomString().randomstring(3);
            new PostMessage().postMessageAndShare(random, "studtitle", "studentnametag", "30_1", "true");

            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("30");//login as instructor
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            String classContributionValue4=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue4,"4","Not showing Total class contributions: 4");
            Assert.assertEquals(new TileTextInDashboard().participationRating(2).substring(0,2).trim(),"1");



            //Tc row no 40
            new LoginUsingLTI().ltiLogin("30_1");//login as student 1
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            new PostMessage().postLinkAndShareWithDefaultClassSection("www.google.com");


            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("30");//login as instructor
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            String classContributionValue5=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue5,"5","Not showing Total class contributions: 5");
            Assert.assertEquals(new TileTextInDashboard().participationRating(2).substring(0,2).trim(),"2");


            //Tc row no 42

            new LoginUsingLTI().ltiLogin("30_2");//login as student 2
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "30_2");
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            new FileUpload().fileUploadAndShare(shareWithInitialString, "studentnametag", "true", "30_2");


            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("30");//login as instructor
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            String classContributionValue6=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue6,"6","Not showing Total class contributions: 6");
            Assert.assertEquals(new TileTextInDashboard().participationRating(2).substring(0,2).trim(),"2");

            //Tc row no 44
            new LoginUsingLTI().ltiLogin("30_3");//login as student 3
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TopicOpen().openLessonWithDiscussionWidget();
            new Widget().perspectiveAdd();

            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("30");//login as instructor
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            String classContributionValue7=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue7,"7","Not showing Total class contributions: 7");
            Assert.assertEquals(new TileTextInDashboard().participationRating(2).substring(0,2).trim(),"2");

           //Tc row no 46

            //Tc row no 58
            new LoginUsingLTI().ltiLogin("30_2");//login as student 2
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            new AssignmentSocialElement().clickonlike(4);

            new RunScheduledJobs().runScheduledJobsForDashboard();


            new LoginUsingLTI().ltiLogin("30");//login as instructor
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            String classContributionValue8=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue8,"7","Not showing Total class contributions: 7");
            Assert.assertEquals(new TileTextInDashboard().participationRating(2).substring(0,2).trim(),"2");

            //Tc row no 60
            new LoginUsingLTI().ltiLogin("30_3");//login as student
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            new Discussion().deletePost(4);

            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("30");//login as instructor
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            String classContributionValue9=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue9,"7","Not showing Total class contributions: 7");
            Assert.assertEquals(new TileTextInDashboard().participationRating(2).substring(0,2).trim(),"2");

            //Tc row no 62

            new LoginUsingLTI().ltiLogin("30");//login as instructor
            new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Discussion");
            String discussionText1 = new RandomString().randomstring(4);
            new Discussion().postDiscussion(discussionText1);//post a discussion

            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            new AssignmentSocialElement().clickonlike(0);

            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            new CommentOnPost().commentOnPost("comment",0);

            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("30");//login as instructor
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            String classContributionValue11=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue11,"7","Not showing Total class contributions: 7");
            Assert.assertEquals(new TileTextInDashboard().participationRating(2).substring(0,2).trim(),"2");



            //Tc row no 65

            new LoginUsingLTI().ltiLogin("30");//login as instructor
            String context_title = ReadTestData.readDataByTagName("", "context_title", "30");
            String id="";
            new DBConnect().Connect();
            ResultSet resultSet=  DBConnect.st.executeQuery("select id from t_class_section where name = '" + context_title + "';");
            while (resultSet.next()) {
                id = resultSet.getString("id");
            }
            int d=Integer.parseInt(id);
            DBConnect.setTotalParticipationCountMongo(d,999);
            driver.navigate().refresh();

            String classContributionValue12=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue12,"999","Not showing Total class contributions: 999");

            //Tc row no 66
            DBConnect.setTotalParticipationCountMongo(d,1000);
            driver.navigate().refresh();

            String classContributionValue13=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue13,"1K","Not showing Total class contributions: 1k");

            //Tc row no 67
            DBConnect.setTotalParticipationCountMongo(d,1500);
            driver.navigate().refresh();

            String classContributionValue14=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue14,"1.5K","Not showing Total class contributions: 1.5k");

            //Tc row no 68
            DBConnect.setTotalParticipationCountMongo(d,1560);
            driver.navigate().refresh();

            String classContributionValue15=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue15,"1.6K","Not showing Total class contributions: 1.6k");

            //Tc row no 69

            DBConnect.setTotalParticipationCountMongo(d,1540);
            driver.navigate().refresh();

            String classContributionValue16=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue16,"1.5K","Not showing Total class contributions: 1.4k");

            //Tc row no 70
            DBConnect.setTotalParticipationCountMongo(d,100000);
            driver.navigate().refresh();

            String classContributionValue17=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue17,"1M","Not showing Total class contributions: 1M");

           //Tc row no 72
            Assert.assertEquals(new TileTextInDashboard().participationRating(3).substring(0,new TileTextInDashboard().participationRating(3).length()-2), "Total class contributions:", "\"Top 10% of the class\" is not replaced with \"Total class contributions\"");



        } catch (Exception e) {
            Assert.fail("Exception in testcase calculationOfTotalClassContribution of class StudentDashboardParticipationTile ", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void studentDashboardParticipationTileForLSExistingStudent() {
        try {

            //Tc row no 74
            new LoginUsingLTI().ltiLogin("74_1");//login as student 1
            new LoginUsingLTI().ltiLogin("74_2");//login as student 2

            new LoginUsingLTI().ltiLogin("74");//login as instructor
            new Assignment().create(74); //create assignment
            for (int i = 0; i < 4; i++) {
                new Assignment().addQuestions(74, "truefalse", "");
            }

            new LoginUsingLTI().ltiLogin("74");//login as instructor
            new Assignment().assignToStudent(74); //assign to the student

            new LoginUsingLTI().ltiLogin("74_1"); //login as student
            new Assignment().submitAssignmentAsStudent(74); //submit assignment

            new LoginUsingLTI().ltiLogin("74");//login as instructor
            new Assignment().provideGradeToStudentForMultipleQuestions(74);
            //new Assignment().releaseGrades(74, "Release Grade for All");//click on the release Grade

            new LoginUsingLTI().ltiLogin("74_1");//login as student 1
            new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Discussion");
            String discussionText = new RandomString().randomstring(4);
            new Discussion().postDiscussion(discussionText);//post a discussion
            new CommentOnPost().commentOnPost(discussionText, 0);
            new AssignmentSocialElement().clickonlike(0);

            new RunScheduledJobs().runScheduledJobsForDashboard();

            new LoginUsingLTI().ltiLogin("74_1");//login as student 1
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);

            //Tc row no 75
            if (!driver.getCurrentUrl().trim().contains("/lsStudentDashBoard")) {
                Assert.fail("Dashboard is not displayed.");
            }
            //Tc row no 76
            Assert.assertEquals(new TileTextInDashboard().participationRating(1), "PARTICIPATION RATING", "Student is not able to view “PARTICIPATION RATING” tile in the dashboard");
            //Tc ro no 77
            if (new TileTextInDashboard().participationRating(3).equals(" ") || new TileTextInDashboard().participationRating(3).equals("0")) {
                Assert.fail("The percentile score in not having the percentile value.");
            }
            //Tc row no 78
            Assert.assertEquals(dashboard.percentageSymbol.getText().trim(), "%", "After percentile value not showing \" %\" symbol");
            //Tc row no 79 & 79
            Assert.assertEquals(new TileTextInDashboard().participationRating(4), "Compared to your classmates", "The text in the bottom tile is not a “Compared to your classmates”.");

            //Tc row no 81
            dashboard.participationRating_tile.click(); //click on the participationRating_tile
            Thread.sleep(3000);
            if (!driver.getCurrentUrl().trim().contains("/coursestream")) {
                Assert.fail("studnet is not navigated to Course Stream page.");
            }

            new Navigator().NavigateTo("Dashboard"); //navigate to dashboard

            //Tc row no 82
            dashboard.participationRating_help.click(); //click on the participationRating help icon
            String participationRating_helpText = dashboard.participationRating_helpText.getText().trim();
            String helpText = "This shows your level of involvement in class discussions from the Course Stream.";
            if (!participationRating_helpText.equals(helpText)) {
                Assert.fail("This shows your level of involvement in class discussions from the Course Stream is help text is not displaying");

            }


        } catch (Exception e) {
            Assert.fail("Exception in testcase studentDashboardParticipationTileForLSExistingStudent of class StudentDashboardParticipationTile ", e);
        }
    }
    @Test(priority = 6, enabled = true)
    public void studentDashboardParticipationTileForLS() {
        try {
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            new LoginUsingLTI().ltiLogin("83");//login as student 1

            //Tc row no 83
            Assert.assertEquals(new TileTextInDashboard().participationRating(1), "PARTICIPATION RATING", "Student is not able to view “PARTICIPATION RATING” tile in the dashboard");
            //Tc ro no 83
            if (!new TileTextInDashboard().participationRating(3).equals("0%")) {
                Assert.fail("The percentile score is not showing \"0%\".");
            }
            Assert.assertEquals(new TileTextInDashboard().participationRating(4), "Compared to your classmates", "The text in the bottom tile is not a “Compared to your classmates”.");

            dashboard.participationRating_tile.click(); //click on the participationRating_tile
            Thread.sleep(3000);
            if (!driver.getCurrentUrl().trim().contains("/coursestream")) {
                Assert.fail("studnet is not navigated to Course Stream page.");
            }

            new Navigator().NavigateTo("Dashboard"); //navigate to dashboard

            //Tc row no 87
            dashboard.participationRating_help.click(); //click on the participationRating help icon
            String participationRating_helpText = dashboard.participationRating_helpText.getText().trim();
            String helpText = "This shows your level of involvement in class discussions from the Course Stream.";
            if (!participationRating_helpText.equals(helpText)) {
                Assert.fail("This shows your level of involvement in class discussions from the Course Stream is help text is not displaying");

            }


        } catch (Exception e) {
            Assert.fail("Exception in testcase studentDashboardParticipationTileForLS of class StudentDashboardParticipationTile ", e);
        }
    }
    @Test(priority = 7, enabled = true)
    public void instructorDashboardAvgParticipationTileForExistingInstructorForLS() {
        try {
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            new LoginUsingLTI().ltiLogin("74");//login as existing instructor

            //Tc row no 89 ...93
            if (!driver.getCurrentUrl().trim().contains("/learningSpaceInstructorDashboard")) {
                Assert.fail("Dashboard is not displayed.");
            }

            Assert.assertEquals(new TileTextInDashboard().participationRating(1), "AVG CLASS PARTICIPATION", "Instructor is not able to view “PARTICIPATION RATING” tile in the dashboard");

            String classContributionValue = dashboard.percent_value.get(0).getText().trim();
            int classContributionValue1 = Integer.parseInt(classContributionValue);
            String performance = dashboard.getAvgQuestionPerformance().get(0).getText().trim().substring(0, 1);
            int performance1 = Integer.parseInt(performance);
            if (classContributionValue1 % 2 == 0) {
                if (classContributionValue1 / 2 != performance1) {
                    Assert.fail("Not showing the average count of all the contributions1");
                }
            } else {
                if ((classContributionValue1 / 2) + 1 != performance1) {
                    Assert.fail("Not showing the average count of all the contributions2");
                }
            }

            Assert.assertEquals(dashboard.contribution_perStudent.getText().trim(), "Contributions per student", "“Contributions per Student” below the average count is not below to average count");

            if (dashboard.contribution_value.getText().equals(" ") || dashboard.contribution_value == null) {
                Assert.fail("The bottom bar is not showing static text - “Total class contributions:<xx> ");
            }

        } catch (Exception e) {
            Assert.fail("Exception in testcase instructorDashboardAvgParticipationTileForExistingInstructorForLS of class StudentDashboardParticipationTile ", e);
        }

    }
    @Test(priority = 8, enabled = true)
    public void calculationOfTotalClassContributionForLS() {
        try {
            Dashboard dashboard = null;
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            new LoginUsingLTI().ltiLogin("94_1");//login as student 1
            new LoginUsingLTI().ltiLogin("94_2");//login as student 2
            new LoginUsingLTI().ltiLogin("94_3");//login as student 3

            new LoginUsingLTI().ltiLogin("94");//login as instructor

            String classContributionValue=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue,"0","Not showing Total class contributions: 0");

            String naMessage=new TileTextInDashboard().participationRating(2).substring(0,2);
            Assert.assertEquals(naMessage,"NA","Not showing Avg Class Participation value : NA");

            new LoginUsingLTI().ltiLogin("94_1");//login as student 1
            new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Discussion");
            String discussionText = new RandomString().randomstring(4);
            new Discussion().postDiscussion(discussionText);//post a discussion
            new RunScheduledJobs().runScheduledJobsForDashboard();

            new LoginUsingLTI().ltiLogin("94");//login as instructor
            dashboard = PageFactory.initElements(driver, Dashboard.class);

            String classContributionValue1=null;
            classContributionValue1=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue1,"1","Not showing Total class contributions: 1");
            Assert.assertEquals(new TileTextInDashboard().participationRating(2).substring(0,2).trim(),"1");

            new LoginUsingLTI().ltiLogin("94_2");//login as student 2
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            new AssignmentSocialElement().clickonlike(0);

            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("94");//login as instructor
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            String classContributionValue2=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue2,"2","Not showing Total class contributions: 2");
            Assert.assertEquals(new TileTextInDashboard().participationRating(2).substring(0,2).trim(),"1");

            new LoginUsingLTI().ltiLogin("94_3");//login as student 3
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            new CommentOnPost().commentOnPost("comment",0);


            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("94");//login as instructor
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            String classContributionValue3=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue3,"3","Not showing Total class contributions: 3");
            Assert.assertEquals(new TileTextInDashboard().participationRating(2).substring(0,2).trim(),"1");

            new LoginUsingLTI().ltiLogin("94_1");//login as student 1
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            String random=new RandomString().randomstring(3);
            new PostMessage().postMessageAndShare(random, "studtitle", "studentnametag", "94_1", "true");

            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("94");//login as instructor
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            String classContributionValue4=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue4,"4","Not showing Total class contributions: 4");
            Assert.assertEquals(new TileTextInDashboard().participationRating(2).substring(0,2).trim(),"1");

            new LoginUsingLTI().ltiLogin("94_1");//login as student 1
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            new PostMessage().postLinkAndShareWithDefaultClassSection("www.google.com");

            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("94");//login as instructor
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            String classContributionValue5=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue5,"5","Not showing Total class contributions: 5");
            Assert.assertEquals(new TileTextInDashboard().participationRating(2).substring(0,2).trim(),"2");

            new LoginUsingLTI().ltiLogin("94_2");//login as student 2
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "94_2");
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            new FileUpload().fileUploadAndShare(shareWithInitialString, "studentnametag", "true", "94_2");


            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("94");//login as instructor
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            String classContributionValue6=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue6,"6","Not showing Total class contributions: 6");
            Assert.assertEquals(new TileTextInDashboard().participationRating(2).substring(0,2).trim(),"2");

            new LoginUsingLTI().ltiLogin("94_3");//login as student 3
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TopicOpen().chapterOpen(15);
            new SelectCourse().selectInvisibleAssignment("8A.3: South Asia’s Physiography");
            String random1 = new RandomString().randomstring(2);
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
            new Click().clickBycssselector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']"); //click on the perspective
            List<WebElement> perspectives = driver.findElements(By.name("perspective"));
            for(WebElement perspective : perspectives)
            {
                if(perspective.isDisplayed())
                {
                    perspective.sendKeys(random1+Keys.RETURN); //entering text in the perspective textarea which is currently displayed
                }
            }
            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("94");//login as instructor
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            String classContributionValue7=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue7,"7","Not showing Total class contributions: 7");
            Assert.assertEquals(new TileTextInDashboard().participationRating(2).substring(0,2).trim(),"2");


            new LoginUsingLTI().ltiLogin("94_2");//login as student 2
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            new AssignmentSocialElement().clickonlike(4);

            new RunScheduledJobs().runScheduledJobsForDashboard();


            new LoginUsingLTI().ltiLogin("94");//login as instructor
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            String classContributionValue8=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue8,"7","Not showing Total class contributions: 7");
            Assert.assertEquals(new TileTextInDashboard().participationRating(2).substring(0,2).trim(),"2");

            new LoginUsingLTI().ltiLogin("94_3");//login as student
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            new Discussion().deletePost(4);

            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("94");//login as instructor
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            String classContributionValue9=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue9,"7","Not showing Total class contributions: 7");
            Assert.assertEquals(new TileTextInDashboard().participationRating(2).substring(0,2).trim(),"2");


            new LoginUsingLTI().ltiLogin("94");//login as instructor
            new Navigator().NavigateTo("eTextbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Discussion");
            String discussionText1 = new RandomString().randomstring(4);
            new Discussion().postDiscussion(discussionText1);//post a discussion

            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            new AssignmentSocialElement().clickonlike(0);

            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            new CommentOnPost().commentOnPost("comment",0);

            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("94");//login as instructor
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            String classContributionValue11=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue11,"7","Not showing Total class contributions: 7");
            Assert.assertEquals(new TileTextInDashboard().participationRating(2).substring(0,2).trim(),"2");




            new LoginUsingLTI().ltiLogin("94");//login as instructor
            String context_title = ReadTestData.readDataByTagName("", "context_title", "94");
            String id="";
            new DBConnect().Connect();
            ResultSet resultSet=  DBConnect.st.executeQuery("select id from t_class_section where name = '" + context_title + "';");
            while (resultSet.next()) {
                id = resultSet.getString("id");
            }
            int d=Integer.parseInt(id);
            DBConnect.setTotalParticipationCountMongo(d,999);
            driver.navigate().refresh();

            String classContributionValue12=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue12,"999","Not showing Total class contributions: 999");

            DBConnect.setTotalParticipationCountMongo(d,1000);
            driver.navigate().refresh();

            String classContributionValue13=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue13,"1K","Not showing Total class contributions: 1k");

            DBConnect.setTotalParticipationCountMongo(d,1500);
            driver.navigate().refresh();

            String classContributionValue14=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue14,"1.5K","Not showing Total class contributions: 1.5k");

            DBConnect.setTotalParticipationCountMongo(d,1560);
            driver.navigate().refresh();

            String classContributionValue15=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue15,"1.6K","Not showing Total class contributions: 1.6k");


            DBConnect.setTotalParticipationCountMongo(d,1540);
            driver.navigate().refresh();

            String classContributionValue16=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue16,"1.5K","Not showing Total class contributions: 1.4k");

            DBConnect.setTotalParticipationCountMongo(d,100000);
            driver.navigate().refresh();

            String classContributionValue17=dashboard.percent_value.get(0).getText().trim();
            Assert.assertEquals(classContributionValue17,"1M","Not showing Total class contributions: 1M");


            Assert.assertEquals(new TileTextInDashboard().participationRating(3).substring(0,new TileTextInDashboard().participationRating(3).length()-2), "Total class contributions:", "\"Top 10% of the class\" is not replaced with \"Total class contributions\"");



        } catch (Exception e) {
            Assert.fail("Exception in testcase calculationOfTotalClassContributionForLS of class StudentDashboardParticipationTile ", e);
        }
    }

}