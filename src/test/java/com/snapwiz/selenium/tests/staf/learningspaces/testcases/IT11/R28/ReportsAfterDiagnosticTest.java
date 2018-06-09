package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT11.R28;


import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

public class ReportsAfterDiagnosticTest extends Driver {

    @Test(priority = 1, enabled=true)
    public void attemptDiagnosticTest()                     // LS+Adaptive show reports after Diagnostic Test
    {
        try
        {
            new LoginUsingLTI().ltiLogin("6");
            new Navigator().NavigateTo("eTextbook");
            new DiagnosticTest().startTest(1);
            new DiagnosticTest().attemptAllCorrect(0,false,false);
            new Navigator().NavigateTo("Proficiency Report");
            String text = driver.findElement(By.className("ls-proficiency-report-barchart-block")).getText();
            if(!text.contains("Course Proficiency by Chapter"))
                Assert.fail("Proficiency report page does not have the appropriate title.");

            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")); // For Proficiency Report  click on Chapter graph.

            new Navigator().NavigateTo("Metacognitive Report");
            new Navigator().NavigateTo("Dashboard");
            new Navigator().NavigateTo("Metacognitive Report");
            driver.findElement(By.cssSelector("g.highcharts-markers > path")).click();          //For metacognitive report Click on Chapter's Dot on Graph.

            new Navigator().NavigateTo("Productivity Report");
            driver.findElement(By.className("highcharts-markers")).click();                  //For productivity report Click on Chapter's Dot on Graph.

            new Navigator().NavigateTo("Most Challenging Activities Report");	             //For Most Challenging Activities

            String text1 =  driver.findElement(By.className("ls-report-chapter-title")).getText();
            System.out.println("Output"+ text1);
            if(!text1.contains("Chapters"))
                Assert.fail("No Message is found-2");

            driver.findElement(By.id("ls-most-challenging-activities-tlo")).click();

        }
        catch(Exception e)
        {
            Assert.fail("Exception in test for attempting diagnostic test",e);
        }
    }

    @Test(priority = 2, enabled=true)
    public void startStaticPracticeTest()                  //      LS+Adaptive show reports after Static Practice.
    {
        try
        {
            new LoginUsingLTI().ltiLogin("10");
            new Navigator().NavigateTo("eTextbook");
            new SelectCourse().selectInvisibleAssignment("1.1 Concept Check");
            new AttemptTest().StaticTest();//attempt static test

            new Navigator().NavigateTo("eTextbook");
            new SelectCourse().selectInvisibleAssignment("1.2 Concept Check");
            new AttemptTest().StaticTest();//attempt static test

            new Navigator().NavigateTo("eTextbook");
            new TopicOpen().chapterOpen(1);
            new SelectCourse().selectInvisibleAssignment("2.2 Concept Check");
            new AttemptTest().StaticTest();//attempt static test

            new Navigator().NavigateTo("eTextbook");
            new TopicOpen().chapterOpen(1);
            new SelectCourse().selectInvisibleAssignment("2.3 Concept Check");
            new AttemptTest().StaticTest();//attempt static test

            new Navigator().NavigateTo("Proficiency Report");
            String text = driver.findElement(By.className("ls-proficiency-report-barchart-block")).getText();
            if(!text.contains("Course Proficiency by Chapter"))
                Assert.fail("Message not found-1");
            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")); // For Proficiency Report

            new Navigator().NavigateTo("Metacognitive Report");
            driver.findElement(By.className("ls-reports-confidence-level-metacognitive-almost")).click();
            driver.findElement(By.cssSelector("g.highcharts-markers.highcharts-tracker > path")).click();        //For metacognitive report

            new Navigator().NavigateTo("Productivity Report");
            driver.findElement(By.className("ls-reports-proficiency-wrapper")).click();
            driver.findElement(By.cssSelector("g.highcharts-markers.highcharts-tracker > path")).click();                  //For productivity report

            new Navigator().NavigateTo("Most Challenging Activities Report");	             //For Most Challenging Activities
            String text1 =  driver.findElement(By.className("ls-report-chapter-title")).getText();
            System.out.println("Output"+ text1);
            if(!text1.contains("Chapters"))
                Assert.fail("No Message is found-2");

            driver.findElement(By.id("ls-most-challenging-activities-tlo")).click();
        }
        catch (Exception e)
        {
            Assert.fail("Exception in Login in class repotsAccess",e);
        }
    }

    @Test(priority = 3, enabled=true)
    public void startChapterLevelPracticeTest()                  //      LS+Adaptive show reports after ChapterLevel Practice.
    {
        try
        {
            new LoginUsingLTI().ltiLogin("14");
            new Navigator().NavigateTo("eTextbook");

            new DiagnosticTest().startTest(1);
            new DiagnosticTest().attemptAllCorrect(0,false,false);

            new Navigator().NavigateTo("eTextbook");

            new PracticeTest().startTest();
            for(int i = 0 ; i <5 ; i++)
                new PracticeTest().AttemptCorrectAnswer(0,"14");

            driver.findElement(By.className("al-quit-diag-test-icon")).click();

            driver.findElement(By.className("ls-practice-test-view-report")).click();
            Thread.sleep(3000);
            new Navigator().NavigateTo("Proficiency Report");
            Thread.sleep(1000);
            String text = driver.findElement(By.className("ls-proficiency-report-barchart-block")).getText();
            if(!text.contains("Course Proficiency by Chapter"))
                Assert.fail("Message not found-1");

            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")); // For Proficiency Report

            new Navigator().NavigateTo("Metacognitive Report");
            Thread.sleep(1000);
            new Navigator().NavigateTo("Productivity Report");
            new Navigator().NavigateTo("Metacognitive Report");
            driver.findElement(By.cssSelector("g.highcharts-markers > path")).click();          //For metacognitive report

            new Navigator().NavigateTo("Productivity Report");
            Thread.sleep(1000);
            driver.findElement(By.className("highcharts-markers")).click();                  //For productivity report

            new Navigator().NavigateTo("Most Challenging Activities Report");	             //For Most Challenging Activities
            Thread.sleep(2000);
            String text1 =  driver.findElement(By.className("ls-report-chapter-title")).getText();
            System.out.println("Output"+ text1);
            if(!text1.contains("Chapters"))
                Assert.fail("No Message is found-2");

            Thread.sleep(2000);
            driver.findElement(By.id("ls-most-challenging-activities-tlo")).click();
            Thread.sleep(2000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in Login in class repotsAccess",e);
        }
    }

    @Test(priority = 4, enabled=true)
    public void startTLOLevelPracticeTest()                  //      LS+Adaptive show reports after TLO level Practice.
    {
        try
        {
            new LoginUsingLTI().ltiLogin("18");
            new Navigator().NavigateTo("eTextbook");

            new DiagnosticTest().startTest(1);
            new DiagnosticTest().attemptAllCorrect(0,false,false);

            new Navigator().NavigateTo("eTextbook");

            new PracticeTest().startTest();
            for(int i = 0 ; i <5 ; i++)
                new PracticeTest().AttemptCorrectAnswer(0,"18");

            driver.findElement(By.className("al-quit-diag-test-icon")).click();

            driver.findElement(By.className("ls-practice-test-view-report")).click();
            Thread.sleep(2000);

            new Navigator().NavigateTo("Proficiency Report");
            Thread.sleep(1000);
            String text = driver.findElement(By.className("ls-proficiency-report-barchart-block")).getText();
            if(!text.contains("Course Proficiency by Chapter"))
                Assert.fail("Message not found-1");

            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")); // For Proficiency Report

            new Navigator().NavigateTo("Metacognitive Report");
            Thread.sleep(1000);
            new Navigator().NavigateTo("Productivity Report");
            new Navigator().NavigateTo("Metacognitive Report");
            driver.findElement(By.cssSelector("g.highcharts-markers.highcharts-tracker > path")).click();          //For metacognitive report

            new Navigator().NavigateTo("Productivity Report");
            Thread.sleep(1000);
            new Navigator().NavigateTo("Metacognitive Report");
            new Navigator().NavigateTo("Productivity Report");
            driver.findElement(By.cssSelector("g.highcharts-markers.highcharts-tracker > path")).click();                  //For productivity report

            new Navigator().NavigateTo("Most Challenging Activities Report");	             //For Most Challenging Activities
            Thread.sleep(2000);
            String text1 =  driver.findElement(By.className("ls-report-chapter-title")).getText();
            System.out.println("Output"+ text1);
            if(!text1.contains("Chapters"))
                Assert.fail("No Message is found-2");

            driver.findElement(By.className("ls-report-chapter-title")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("ls-most-challenging-activities-tlo")).click();
            Thread.sleep(2000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in Login in class repotsAccess",e);
        }
    }

    @Test(priority = 5, enabled=true)                                                     // staticpracticetestfornonadaptive
    public void staticpracticetestfornonadaptive()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("30");
            new Navigator().NavigateTo("eTextbook");
            new SelectCourse().selectInvisibleAssignment("0.2 Concept Check");

            new PracticeTest().attemptStaticPracticeTest(0);

            new Navigator().NavigateTo("Performance Report");
            Thread.sleep(1000);
            String text = driver.findElement(By.className("ls-performance-report-barchart-block")).getText();
            if(!text.contains("Course Performance by Chapter"))
                Assert.fail("Message not found-1");

            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")); // For Performance Report

            new Navigator().NavigateTo("Most Challenging Chapters Report");	             //For Most Challenging Chapters Report
            Thread.sleep(2000);


            String text1 =  driver.findElement(By.className("ls-report-chapter-title")).getText();
            System.out.println("Output"+ text1);
            if(!text1.contains("Chapters"))
                Assert.fail("No Message is found-2");

            Thread.sleep(2000);
            driver.findElement(By.id("ls-most-challenging-activities-tlo")).click();
            Thread.sleep(2000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in Login in class repotsAccess",e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void accessStudyLinksLSandAdaptive()                        // Access Study Links in Reports.
    {
        try
        {
            new LoginUsingLTI().ltiLogin("36");
            new Navigator().NavigateTo("eTextbook");
            new DiagnosticTest().startTest(1);
            new DiagnosticTest().attemptAllCorrect(0,false,false);

            new Navigator().NavigateTo("Metacognitive Report");
            Thread.sleep(1000);
            WebElement menuitem = driver.findElement(By.className("ls-terminal-objective-title"));               // Mousehover on Chapter name
            Locatable hoverItem = (Locatable) menuitem;
            Mouse mouse = ((HasInputDevices) driver).getMouse();
            mouse.mouseMove(hoverItem.getCoordinates());

            driver.findElement(By.className("ls-report-row-right-arrow"));
            new Navigator().NavigateTo("Metacognitive Report");
            Thread.sleep(1000);
            new Navigator().NavigateTo("Productivity Report");                                                               // Productivity Report
            new Navigator().NavigateTo("Metacognitive Report");
            driver.findElement(By.cssSelector("g.highcharts-markers.highcharts-tracker > path")).click();
            Thread.sleep(3000);

            WebElement menuitem3 = driver.findElement(By.cssSelector("g.highcharts-markers.highcharts-tracker > path"));               // Click on Chapter name.
            Locatable hoverItem3 = (Locatable) menuitem3;
            Mouse mouse3 = ((HasInputDevices) driver).getMouse();
            mouse3.mouseMove(hoverItem3.getCoordinates());

            driver.findElement(By.id("ls-metacognitive-chapter-study")).click();   			// Click on Study link.


            new Navigator().NavigateTo("Productivity Report");                                                               // Productivity Report
            Thread.sleep(1000);
            WebElement menuitem2 = driver.findElement(By.className("highcharts-markers"));               // Mousehover on Chapter name and click on study[Topic-10.1]
            Locatable hoverItem2 = (Locatable) menuitem2;
            Mouse mouse2 = ((HasInputDevices) driver).getMouse();
            mouse2.mouseMove(hoverItem2.getCoordinates());
            driver.findElement(By.className("ls-report-row-right-arrow")).click();
            driver.findElement(By.xpath("//div[@title='Study']")).click();

            new Navigator().NavigateTo("Productivity Report");                                                               // Productivity Report
            Thread.sleep(1000);

            driver.findElement(By.className("highcharts-markers")).click();

            WebElement menuitem1 = driver.findElement(By.cssSelector("g.highcharts-markers.highcharts-tracker > path"));               // Click on Chapter name.
            Locatable hoverItem1 = (Locatable) menuitem1;
            Mouse mouse1 = ((HasInputDevices) driver).getMouse();
            mouse1.mouseMove(hoverItem1.getCoordinates());
            driver.findElement(By.className("ls-report-row-right-arrow")).click();
            driver.findElement(By.xpath("//div[@title='Study']")).click();

            new Navigator().NavigateTo("Most Challenging Activities Report");	             //For Most Challenging Activities
            Thread.sleep(2000);

            driver.findElement(By.id("ls-productivity-report")).click();
            Thread.sleep(3000);

            WebElement menuitem4 = driver.findElement(By.className("highcharts-markers"));               // Mousehover on Chapter name and click on study[Topic-10.1]
            Locatable hoverItem4 = (Locatable) menuitem4;
            Mouse mouse4 = ((HasInputDevices) driver).getMouse();
            mouse4.mouseMove(hoverItem4.getCoordinates());

            driver.findElement(By.id("ls-tlo-chapter-study")).click();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in Login in class repotsAccess",e);
        }
    }

    @Test(priority = 7, enabled = true)
    public void accessStudyLinksNonAdaptive()                             // Twice Mousehover and click on StudyLinks.
    {
        try
        {
            new LoginUsingLTI().ltiLogin("48");
            new Navigator().NavigateTo("eTextbook");
            new TopicOpen().chapterOpen(15);

            new SelectCourse().selectInvisibleAssignment("8A.2 Concept Check");
            new AttemptTest().StaticTest();


            new Navigator().NavigateTo("Most Challenging Chapters Report");	             //For Most Challenging Chapters Report
            Thread.sleep(1000);
            WebElement menuitem = driver.findElement(By.className("ls-preformance-report"));               // Mousehover on Chapter name and click on study[Topic-10.1]
            Locatable hoverItem = (Locatable) menuitem;
            Mouse mouse = ((HasInputDevices) driver).getMouse();
            mouse.mouseMove(hoverItem.getCoordinates());
            driver.findElement(By.className("ls-report-row-right-arrow")).click();
            driver.findElement(By.cssSelector("div[title='Study']")).click();            // Click on Study.


            new Navigator().NavigateTo("Most Challenging Chapters Report");	             //For Most Challenging Chapters Report
            Thread.sleep(1000);
            driver.findElement(By.id("ls-most-challenging-activities-tlo")).click();
            driver.findElement(By.xpath("(//div[@class='ls-report-row-right-arrow'])[2]")).click();
            driver.findElement(By.xpath("(//div[@title='Study'])[2]")).click();            // Click on Study.



        }

        catch(Exception e)
        {
            Assert.fail("Exception in Login in class repotsAccess",e);
        }

    }

    @Test(priority = 8, enabled = true)                                               // LS + Adaptive Click on Practice Links in Metacognitive,Productivity,Most Challenging Chapters Report reports.
    public void accessPracticeLinksLSandAdaptive()
    {
        try
        {

             new LoginUsingLTI().ltiLogin("52");
            new Navigator().NavigateTo("eTextbook");

            new DiagnosticTest().startTest(1);
            new DiagnosticTest().attemptAllCorrect(0,false,false);

            new Navigator().NavigateTo("Metacognitive Report");
            Thread.sleep(1000);

            WebElement menuitem = driver.findElement(By.cssSelector("g.highcharts-markers > path"));               // Mousehover on Chapter name
            Locatable hoverItem = (Locatable) menuitem;
            Mouse mouse = ((HasInputDevices) driver).getMouse();
            mouse.mouseMove(hoverItem.getCoordinates());

            driver.findElement(By.id("ls-metacognitive-chapter-start-practice")).click();           // Click on Practice link takes to Practice Test.

            new Navigator().NavigateTo("Metacognitive Report");
            Thread.sleep(1000);
            new Navigator().NavigateTo("Productivity Report");                                                               // Productivity Report
            new Navigator().NavigateTo("Metacognitive Report");
            driver.findElement(By.cssSelector("g.highcharts-markers > path")).click();
            Thread.sleep(2000);

            WebElement menuitem1 = driver.findElement(By.cssSelector("g.highcharts-markers.highcharts-tracker > path"));               // Click on Chapter name.
            Locatable hoverItem1 = (Locatable) menuitem1;
            Mouse mouse1 = ((HasInputDevices) driver).getMouse();
            mouse1.mouseMove(hoverItem1.getCoordinates());
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("ls-metacognitive-chapter-study")));
            driver.findElement(By.id("ls-metacognitive-chapter-study")).click();   			// Click on Study link.*/
            new LoginUsingLTI().ltiLogin("52");

            new Navigator().NavigateTo("Productivity Report");                                                               // Productivity Report
            Thread.sleep(1000);
            WebElement menuitem2 = driver.findElement(By.className("highcharts-markers"));               // Mousehover on Chapter name and click on study[Topic-10.1]
            Locatable hoverItem2 = (Locatable) menuitem2;
            Mouse mouse2 = ((HasInputDevices) driver).getMouse();
            mouse2.mouseMove(hoverItem2.getCoordinates());

            driver.findElement(By.id("ls-tlo-start-practice")).click();
            new LoginUsingLTI().ltiLogin("52");

            new Navigator().NavigateTo("Productivity Report");                                                               // Productivity Report
            Thread.sleep(1000);

            driver.findElement(By.className("highcharts-markers")).click();

            WebElement menuitem3 = driver.findElement(By.cssSelector("g.highcharts-markers.highcharts-tracker > path"));               // Click on Chapter name.
            Locatable hoverItem3 = (Locatable) menuitem3;
            Mouse mouse3 = ((HasInputDevices) driver).getMouse();
            mouse3.mouseMove(hoverItem3.getCoordinates());

            driver.findElement(By.id("ls-tlo-start-practice")).click();               // Takes to Practice.
            new LoginUsingLTI().ltiLogin("52");

            new Navigator().NavigateTo("Most Challenging Activities Report");	             //For Most Challenging Activities
            Thread.sleep(2000);

            WebElement menuitem4 = driver.findElement(By.className("ls-preformance-report"));               // Mousehover on Chapter name and click on study[Topic-10.1]
            Locatable hoverItem4 = (Locatable) menuitem4;
            Mouse mouse4 = ((HasInputDevices) driver).getMouse();
            mouse4.mouseMove(hoverItem4.getCoordinates());
            driver.findElement(By.className("ls-report-row-right-arrow")).click();
            driver.findElement(By.cssSelector("div[title='Practice']")).click();            // Click on Study.

            new LoginUsingLTI().ltiLogin("52");

            new Navigator().NavigateTo("Most Challenging Activities Report");	             //For Most Challenging Activities
            Thread.sleep(2000);

            driver.findElement(By.id("ls-most-challenging-activities-tlo")).click();

            List<WebElement> viewByObjectives = driver.findElements(By.className("ls-preformance-report"));

            Locatable hoverItem5 = (Locatable) viewByObjectives.get(1);
            Mouse mouse5 = ((HasInputDevices) driver).getMouse();
            mouse5.mouseMove(hoverItem5.getCoordinates());
            List<WebElement> arrow=driver.findElements(By.xpath("//div[@class='ls-report-row-right-arrow']"));
            arrow.get(1).click();
            List<WebElement> clickPracticeLink = driver.findElements(By.cssSelector("div[title='Practice']"));
            clickPracticeLink.get(1).click();
        }

        catch(Exception e)
        {
            Assert.fail("Exception in Login in class repotsAccess",e);
        }
    }

    @Test(priority = 9, enabled = true)
    public void accessPracticeLinksNonAdaptive()
    {
        try
        {

            new LoginUsingLTI().ltiLogin("48");
            new Navigator().NavigateTo("eTextbook");

            new TopicOpen().chapterOpen(1);

            new SelectCourse().selectInvisibleAssignment("1A.2 Concept Check");
            new AttemptTest().StaticTest();

            new Navigator().NavigateTo("Most Challenging Chapters Report");	             //For Most Challenging Chapters Report
            Thread.sleep(2000);


            WebElement menuitem = driver.findElement(By.className("ls-preformance-report"));               //
            Locatable hoverItem = (Locatable) menuitem;
            Mouse mouse = ((HasInputDevices) driver).getMouse();
            mouse.mouseMove(hoverItem.getCoordinates());

            int studySize = driver.findElements(By.cssSelector("div[title='Practice']")).size();
            System.out.println(studySize);
            if(studySize>0)
            {
                Assert.fail("Exception in class if Preactice Present");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in Login in class repotsAccess",e);
        }
    }

    @Test(priority = 10, enabled = false)
    public void accessChapterLevelAssessmentNonAdaptive()
    {
        try
        {

            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "78");
            new LoginUsingLTI().ltiLogin("78");
            new Assignment().create(78);
            for(int i =0; i< 5; i++)
                new Assignment().addQuestions(78, "qtn-type-true-false-img", assignmentname);
            new LoginUsingLTI().ltiLogin("78_Instructor");
            new Assignment().assignToStudent(78);
            // Creates a new assessment.


        }
        catch(Exception e)
        {
            Assert.fail("Exception in Login in class repotsAccess",e);
        }
    }


}
