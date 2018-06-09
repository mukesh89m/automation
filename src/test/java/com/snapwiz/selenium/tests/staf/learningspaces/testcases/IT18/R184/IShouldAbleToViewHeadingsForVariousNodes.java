package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R184;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 1/2/2015.
 */
public class IShouldAbleToViewHeadingsForVariousNodes extends Driver{
    @Test(priority = 1,enabled = true)
    public void resourceUploadedByAuthorAsStudent() {
        try {
            //tc row no 10
            String resoursename = ReadTestData.readDataByTagName("", "resoursename", Integer.toString(11));

            LessonPage lessonPage;
            lessonPage=PageFactory.initElements(driver,LessonPage.class);
           new ResourseCreate().resourseCreate(10,0);//create chpterlevel resource
           new ResourseCreate().createResourceAtCourseLevel(11, Config.course);//create courselevel resource
           new ResourseCreate().creatresourcesattopiclevel(12, 0, 0);//create sourcelevel resource

            new LoginUsingLTI().ltiLogin("10");//login as student
            lessonPage= PageFactory.initElements(driver,LessonPage.class);
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            WebElement element = driver.findElement(By.xpath("(.//*[@title='Introduction'])[2]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("(.//*[@title='Introduction'])[2]")));
            Thread.sleep(3000);
            new Navigator().navigateToResourceTab();
            String sectionHeaderName=lessonPage.getSectionLevelHeader().getText();
            WebElement scroll1=driver.findElement(By.id("ls-resource-chapter-level"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll1);
            String chapterHeaderName=lessonPage.getChapterLevelHeader().getText();
            Assert.assertEquals(chapterHeaderName,"Chapter Resources","Header name Chapter Resources is not displaying");
            Assert.assertEquals(sectionHeaderName,"Section Resources","Header name Section Resources is not displaying");
            Thread.sleep(1000);
            WebElement scroll=driver.findElement(By.id("ls-resource-course-level"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            String courseHeaderName=lessonPage.getCourseLevelHeader().getText();
            Assert.assertEquals(courseHeaderName,"Course Resources","Header name Course Resources is not displaying");
            new TOCShow().chaptertree();
            //tc row no 14
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lessonPage.getSecondLesson());
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",lessonPage.getSecondLesson());
            Thread.sleep(3000);
            new Navigator().navigateToTab("Resources");
            boolean elementFound = false;
            try{
                driver.findElement(By.id("ls-resource-section-level"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"SectionLevel header is displayed");
            Assert.assertEquals(chapterHeaderName,"Chapter Resources","Header name Chapter Resources is not displaying");
            Assert.assertEquals(courseHeaderName,"Course Resources","Header name Course Resources is not displaying");
            //tc row no 16
            new TOCShow().chaptertree();
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getFourthChapter().click();
            lessonPage.getFourthChapterFirstLesson().click();
            new Navigator().navigateToResourceTab();
            boolean elementFound1 = false;
            try{
                driver.findElement(By.id("ls-resource-chapter-level"));
                elementFound1 = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound1,true,"ChapterLevel header is not displayed");
            Assert.assertEquals(courseHeaderName,"Course Resources","Header name Course Resources is not displaying");
            //tc row no 18
            new TOCShow().chaptertree();
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getFourthChapterSecondLesson().click();
            Thread.sleep(2000);
            new Navigator().navigateToResourceTab();

            WebElement element1=driver.findElement(By.xpath("//a[text()='"+resoursename+"']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element1);
            Thread.sleep(3000);
            boolean found = false;
            List<WebElement> resources = driver.findElements(By.cssSelector("div[class='ls-right-section-status ls-right-section-resource']"));
            for(WebElement res : resources)
            {
                System.out.println("Resource Name: "+res.getText());
                if(res.getText().contains(resoursename))
                {
                    found=true;
                    break;
                }

            }
            if(found==false)
                Assert.fail("Resourcename is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case resourceUploadedByAuthorAsStudent in class IShouldAbleToViewHeadingsForVariousNodes", e);
        }

    }
        @Test(priority = 2,enabled = true)
        public void resourceUploadedByAuthorAsInstructor() {
        try {
            //tc row no 19
            LessonPage lessonPage;
            lessonPage=PageFactory.initElements(driver,LessonPage.class);
            new ResourseCreate().resourseCreate(19,0);//create chpterlevel resource
            new ResourseCreate().createResourceAtCourseLevel(20, Config.course);//create courselevel resource
            new ResourseCreate().creatresourcesattopiclevel(21, 0, 0);//create sourcelevel resource

            new LoginUsingLTI().ltiLogin("19");//login as instructor
            lessonPage= PageFactory.initElements(driver,LessonPage.class);
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            WebElement element = driver.findElement(By.xpath("(.//*[@title='Introduction'])[2]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("(.//*[@title='Introduction'])[2]")));
            Thread.sleep(3000);
            new Navigator().navigateToResourceTab();
            String sectionHeaderName=lessonPage.getSectionLevelHeader().getText();
            WebElement scroll1=driver.findElement(By.id("ls-resource-chapter-level"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll1);
            String chapterHeaderName=lessonPage.getChapterLevelHeader().getText();
            Assert.assertEquals(chapterHeaderName,"Chapter Resources","Header name Chapter Resources is not displaying");
            Assert.assertEquals(sectionHeaderName,"Section Resources","Header name Section Resources is not displaying");
            Thread.sleep(1000);
            WebElement scroll=driver.findElement(By.id("ls-resource-course-level"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            String courseHeaderName=lessonPage.getCourseLevelHeader().getText();
            Assert.assertEquals(courseHeaderName,"Course Resources","Header name Course Resources is not displaying");
            Thread.sleep(1000);
            new TOCShow().chaptertree();//click on toc
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lessonPage.getSecondLesson());
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",lessonPage.getSecondLesson());
            Thread.sleep(3000);
            //tc row no 23
            new Navigator().navigateToTab("Resources");
            boolean elementFound = false;
            try{
                driver.findElement(By.id("ls-resource-section-level"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"SectionLevel header is displayed");
            Assert.assertEquals(chapterHeaderName,"Chapter Resources","Header name Chapter Resources is not displaying");
            Assert.assertEquals(courseHeaderName,"Course Resources","Header name Course Resources is not displaying");
            //tc row no 25
            new TOCShow().chaptertree();//click on toc
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getFourthChapter().click();//click on fourth chapter
            lessonPage.getFourthChapterFirstLesson().click();//click on fourthchapter firstlesson
            new Navigator().navigateToTab("Resources");//click on resource
            Assert.assertEquals(courseHeaderName,"Course Resources","Header name Course Resources is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case resourceUploadedByAuthorAsInstructor in class IShouldAbleToViewHeadingsForVariousNodes", e);
        }

    }
    @Test(priority = 3,enabled = true)
    public void resourceUploadedByAuthorAsMentor() {
        try {
            //tc row no 27
            LessonPage lessonPage;
            lessonPage=PageFactory.initElements(driver,LessonPage.class);
            new ResourseCreate().resourseCreate(27,0);//create chpterlevel resource
            new ResourseCreate().createResourceAtCourseLevel(28, Config.course);//create courselevel resource
            new ResourseCreate().creatresourcesattopiclevel(29, 0, 0);//create sourcelevel resource

            new LoginUsingLTI().ltiLogin("27");//login as mentor
            lessonPage= PageFactory.initElements(driver,LessonPage.class);
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            WebElement element = driver.findElement(By.xpath("(.//*[@title='Introduction'])[2]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("(.//*[@title='Introduction'])[2]")));
            Thread.sleep(3000);

            new Navigator().navigateToTab("Resources");//click on resource tab
            String sectionHeaderName=lessonPage.getSectionLevelHeader().getText();

            WebElement scroll1=driver.findElement(By.id("ls-resource-chapter-level"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll1);
            String chapterHeaderName=lessonPage.getChapterLevelHeader().getText();
            Assert.assertEquals(chapterHeaderName,"Chapter Resources","Header name Chapter Resources is not displaying");
            Assert.assertEquals(sectionHeaderName,"Section Resources","Header name Section Resources is not displaying");
            Thread.sleep(1000);
            WebElement scroll=driver.findElement(By.id("ls-resource-course-level"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            String courseHeaderName=lessonPage.getCourseLevelHeader().getText();
            Assert.assertEquals(courseHeaderName,"Course Resources","Header name Course Resources is not displaying");
            Thread.sleep(1000);
            new TOCShow().chaptertree();//click on toc
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lessonPage.getSecondLesson());
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",lessonPage.getSecondLesson());
            Thread.sleep(3000);

             //tc row no 31
            new Navigator().navigateToTab("Resources");
            boolean elementFound = false;
            try{
                driver.findElement(By.id("ls-resource-section-level"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"SectionLevel header is displayed");
            Assert.assertEquals(chapterHeaderName,"Chapter Resources","Header name Chapter Resources is not displaying");
            Assert.assertEquals(courseHeaderName,"Course Resources","Header name Course Resources is not displaying");
            //tc row no 33
            new TOCShow().chaptertree();//click on toc
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getFourthChapter().click();//click on fourth chapter
            lessonPage.getFourthChapterFirstLesson().click();//click on fourthvhapter firstlesson
            new Navigator().navigateToTab("Resources");//click on resource
            Assert.assertEquals(courseHeaderName,"Course Resources","Header name Course Resources is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in test case resourceUploadedByAuthorAsMentor in class IShouldAbleToViewHeadingsForVariousNodes", e);
        }
    }
    @Test(priority = 4,enabled = true)
    public void resourceUploadedByInstructorLoginAsStudent() {
        try {
            //tc row no 35
            LessonPage lessonPage;
            lessonPage=PageFactory.initElements(driver,LessonPage.class);
            new LoginUsingLTI().ltiLogin("35");//login as instructor
            new UploadResources().uploadResources("35", false, false, true);//upload chapterlevel resource
            new UploadResources().uploadResources("36", false, false, true);//upload section level resource
            new UploadResources().uploadResourcesInCourseLevel("37", false, false, true);//upload course level resource
            new LoginUsingLTI().ltiLogin("35_1");//login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getChapterName().click();//click on 1st chapter

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",  lessonPage.getForScienceAndBiology());
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", lessonPage.getForScienceAndBiology());
            Thread.sleep(3000);

            new Navigator().navigateToTab("Resources");//click on resource
            String sectionHeaderName=lessonPage.getSectionLevelHeader().getText();
            Thread.sleep(4000);
            WebElement scroll1=driver.findElement(By.id("ls-resource-chapter-level"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll1);
            String chapterHeaderName=lessonPage.getChapterLevelHeader().getText();
            Assert.assertEquals(chapterHeaderName,"Chapter Resources","Header name Chapter Resources is not displaying");
            Assert.assertEquals(sectionHeaderName,"Section Resources","Header name Section Resources is not displaying");
            WebElement scroll=driver.findElement(By.id("ls-resource-course-level"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            String courseHeaderName=lessonPage.getCourseLevelHeader().getText();
            Assert.assertEquals(courseHeaderName,"Course Resources","Header name Course Resources is not displaying");
            Thread.sleep(1000);
            new TOCShow().chaptertree();//click on toc
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            //tc row no 39
            lessonPage.getSecondLesson().click();//click on 2nd lesson
            new Navigator().navigateToTab("Resources");//click on resource
            boolean elementFound = false;
            try{
                driver.findElement(By.id("ls-resource-section-level"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"SectionLevel header is displayed");
            Assert.assertEquals(chapterHeaderName,"Chapter Resources","Header name Chapter Resources is not displaying");
            Assert.assertEquals(courseHeaderName,"Course Resources","Header name Course Resources is not displaying");
            //tc row no 41
            new TOCShow().chaptertree();//click on toc
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getFourthChapter().click();//click on fourth chapter
            lessonPage.getFourthChapterFirstLesson().click();//click on fourthvhapter firstlesson
            new Navigator().navigateToTab("Resources");//click on resource
            boolean elementFound1 = false;
            try{
                driver.findElement(By.id("ls-resource-chapter-level"));
                elementFound1 = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound1,true,"ChapterLevel header is displayed");
            Assert.assertEquals(courseHeaderName,"Course Resources","Header name Course Resources is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case resourceUploadedByInstructorLoginAsStudent in class IShouldAbleToViewHeadingsForVariousNodes", e);
        }
    }
    @Test(priority = 5,enabled = true)
    public void resourceUploadedByInstructorLoginAsInstructor() {
        try {
            //tc row no 43
            LessonPage lessonPage;
            lessonPage=PageFactory.initElements(driver,LessonPage.class);
            new LoginUsingLTI().ltiLogin("43");//login as instructor
            new UploadResources().uploadResources("43",false,false,true);//upload chapterlevel resource
            new UploadResources().uploadResources("44",false,false,true);//upload section level resource
            new UploadResources().uploadResourcesInCourseLevel("45",false,false,true);//upload course level resource
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getChapterName().click();//click on 1st chapter
            lessonPage.getForScienceAndBiology().click();//click on science and biology
            new Navigator().navigateToTab("Resources");//click on resource
            String sectionHeaderName=lessonPage.getSectionLevelHeader().getText();
            Thread.sleep(1000);
            WebElement scroll1=driver.findElement(By.id("ls-resource-chapter-level"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll1);
            String chapterHeaderName=lessonPage.getChapterLevelHeader().getText();
            Assert.assertEquals(chapterHeaderName,"Chapter Resources","Header name Chapter Resources is not displaying");
            Assert.assertEquals(sectionHeaderName,"Section Resources","Header name Section Resources is not displaying");
            WebElement scroll=driver.findElement(By.id("ls-resource-course-level"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            String courseHeaderName=lessonPage.getCourseLevelHeader().getText();
            Assert.assertEquals(courseHeaderName,"Course Resources","Header name Course Resources is not displaying");
            Thread.sleep(1000);
            new TOCShow().chaptertree();//click on toc
            //tc row no 46
            lessonPage.getSecondLesson().click();//click on 2nd lesson
            new Navigator().navigateToTab("Resources");//click on resource
            boolean elementFound = false;
            try{
                driver.findElement(By.id("ls-resource-section-level"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"SectionLevel header is displayed");
            Assert.assertEquals(chapterHeaderName,"Chapter Resources","Header name Chapter Resources is not displaying");
            Assert.assertEquals(courseHeaderName,"Course Resources","Header name Course Resources is not displaying");
            //tc row no 49
            new TOCShow().chaptertree();//click on toc
            lessonPage.getFourthChapter().click();//click on fourth chapter
            lessonPage.getFourthChapterFirstLesson().click();//click on fourthvhapter firstlesson
            new Navigator().navigateToTab("Resources");//click on resource
            Assert.assertEquals(courseHeaderName,"Course Resources","Header name Course Resources is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case resourceUploadedByInstructorLoginAsInstructor in class IShouldAbleToViewHeadingsForVariousNodes", e);
        }
    }
    @Test(priority = 6,enabled = true)
    public void resourceUploadedByInstructorLoginAsMentor() {
        try {
            //tc row no 51
            LessonPage lessonPage;
            lessonPage=PageFactory.initElements(driver,LessonPage.class);
            new LoginUsingLTI().ltiLogin("51");//login as instructor
            new UploadResources().uploadResources("52",false,false,true);//upload chapterlevel resource
            new UploadResources().uploadResources("53",false,false,true);//upload section level resource
            new UploadResources().uploadResourcesInCourseLevel("45",false,false,true);//upload course level resource
            new LoginUsingLTI().ltiLogin("51_1");//login as mentor
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            WebElement element = driver.findElement(By.xpath("(.//*[@title='Introduction'])[2]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("(.//*[@title='Introduction'])[2]")));
            Thread.sleep(3000);
            new Navigator().navigateToTab("Resources");//click on resource
            String sectionHeaderName=lessonPage.getSectionLevelHeader().getText();
            Thread.sleep(1000);
            WebElement scroll1=driver.findElement(By.id("ls-resource-chapter-level"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll1);
            String chapterHeaderName=lessonPage.getChapterLevelHeader().getText();
            Assert.assertEquals(chapterHeaderName,"Chapter Resources","Header name Chapter Resources is not displaying");
            Assert.assertEquals(sectionHeaderName,"Section Resources","Header name Section Resources is not displaying");
            WebElement scroll=driver.findElement(By.id("ls-resource-course-level"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            String courseHeaderName=lessonPage.getCourseLevelHeader().getText();

            Assert.assertEquals(courseHeaderName,"Course Resources","Header name Course Resources is not displaying");
            Thread.sleep(1000);
            new TOCShow().chaptertree();//click on toc
            //tc row no 55
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lessonPage.getSecondLesson());
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",lessonPage.getSecondLesson());
            Thread.sleep(3000);

            new Navigator().navigateToTab("Resources");//click on resource
            boolean elementFound = false;
            try{
                driver.findElement(By.id("ls-resource-section-level"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"SectionLevel header is displayed");
            Assert.assertEquals(chapterHeaderName,"Chapter Resources","Header name Chapter Resources is not displaying");
            Assert.assertEquals(courseHeaderName,"Course Resources","Header name Course Resources is not displaying");
            //tc row no 57
            new TOCShow().chaptertree();//click on toc
            lessonPage.getFourthChapter().click();//click on fourth chapter
            lessonPage.getFourthChapterFirstLesson().click();//click on fourthvhapter firstlesson
            new Navigator().navigateToTab("Resources");//click on resource
            Assert.assertEquals(courseHeaderName,"Course Resources","Header name Course Resources is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case resourceUploadedByInstructorLoginAsMentor in class IShouldAbleToViewHeadingsForVariousNodes", e);
        }
    }
    @Test(priority = 7,enabled = true)
    public void assessmentCreatedByAuthorLoginAsInstructor() {
        try {
            //tc row no 59
            LessonPage lessonPage;
            lessonPage=PageFactory.initElements(driver,LessonPage.class);
             new Assignment().create(59);
            new LoginUsingLTI().ltiLogin("59");//log in as instructor
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getChapterName().click();//click on 1st chapter
            lessonPage.getCrossIcon().click();//click on 'x' icon
            new Navigator().navigateToTab("Assignments");//click on Assignment
            Thread.sleep(2000);
            String assessname=driver.findElement(By.xpath("//a[@title='Assessment_59']")).getAttribute("title");
            Assert.assertEquals(assessname,"Assessment_59","Assessmet name is not displaying");
            String image=lessonPage.getwImage().getAttribute("src");
            if(!image.contains("/webresources/images/ls/wiley-W.png")){
                Assert.fail("W-Icon is not displaying");
            }

            String learningSpace=lessonPage.getLearningSpace().getText();
            Assert.assertEquals(learningSpace,"Learning Space","Publishername Learningspace is not displaying");
            String wileyLabel=lessonPage.getWileyLabel().getText();
            Assert.assertEquals(wileyLabel,"Wiley","Wileylabel is not displaying");

           } catch (Exception e) {
            Assert.fail("Exception in test case assessmentCreatedByAuthorLoginAsInstructor in class IShouldAbleToViewHeadingsForVariousNodes", e);
        }
    }
    @Test(priority = 8,enabled = true)
    public void assessmentCreatedByAuthorLoginAsMentor() {
        try {
            //tc row no 63
            LessonPage lessonPage;
            lessonPage = PageFactory.initElements(driver, LessonPage.class);
            new Assignment().create(63);
            new LoginUsingLTI().ltiLogin("64");//log in as mentor
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getChapterName().click();//click on 1st chapter
            lessonPage.getCrossIcon().click();//click on cross icon
            new Navigator().navigateToTab("Assignments");//click on assignment
            Thread.sleep(2000);
            String assessname = driver.findElement(By.xpath("//a[@title='Assessment_63']")).getAttribute("title");
            Assert.assertEquals(assessname, "Assessment_63", "Assessmet name is not displaying");
            String image = lessonPage.getwImage().getAttribute("src");
            if (!image.contains("/webresources/images/ls/wiley-W.png")) {
                Assert.fail("W-Icon is not displaying");
            }
            String learningSpace = lessonPage.getLearningSpace().getText();
            Assert.assertEquals(learningSpace, "Learning Space", "Publishername Learningspace is not displaying");
            String wileyLabel = lessonPage.getWileyLabel().getText();
            Assert.assertEquals(wileyLabel, "Wiley", "Wileylabel is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case assessmentCreatedByAuthorLoginAsMentor in class IShouldAbleToViewHeadingsForVariousNodes", e);
        }
    }

}
