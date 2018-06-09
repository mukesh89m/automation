package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R182;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CourseOutline;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.ClassSectionDropDown;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 12/29/2014.
 */
public class EnhancementToStudyPlanForInstructorAndMentor extends Driver{
    @Test(priority = 1,enabled = true)
    public void enhancementToStudyPlanForInstructor() {
        try {
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            //tc row no 9
            new LoginUsingLTI().ltiLogin("9");//log in as instructor
            WebElement html =driver.findElement(By.tagName("html"));
            for(int i=0; i<2;i++) {
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                Thread.sleep(3000);
            }
            new Navigator().NavigateTo("eTextBook");//navigate to etextbook
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getChapterName().click();//click on chapter name
            Thread.sleep(2000);
            lessonPage.getLessonIcon().click();//click on '>' icon next to the lesson name
            new MouseHover().mouserhoverbywebelement(lessonPage.getOpenTabNewLessonIcon());
            String openTabNewLessonIcon=lessonPage.getOpenTabNewLessonIcon().getAttribute("title");
            Assert.assertEquals(openTabNewLessonIcon,"Open in new lesson tab","Open in new lesson tab message is not displayed");
            Thread.sleep(4000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",lessonPage.getOpenTabNewLessonIcon());
            String lessonTitle=lessonPage.getLessonTitle().getText();
            if(!lessonTitle.contains("1.1 THE SCIENCE OF BIOLOGY")){
                Assert.fail("Lesson is not opened in a new tab");
            }
            new TOCShow().chaptertree();//click on tocicon

            //html.sendKeys(Keys.chord(Keys.CONTROL, "0"));

            lessonPage.orionAdaptive_link.get(1).click(); // select orion adaptive link
            Thread.sleep(3000);
            lessonPage.diagnostic_Arrow1.click();//click on dianostic arrow
            Thread.sleep(2000);
            new TopicOpen().openInnerTopic();
            Thread.sleep(2000);
            String diagnosticTitle=lessonPage.getDiagnosticTestTitle().getText();
            if(!diagnosticTitle.contains("(P1) Diagnostic - Ch 1: The Study of Life")){
                Assert.fail("Lesson is not opened in a new tab");
            }
            new TOCShow().chaptertree();//click on tocicon
            lessonPage.practice_arow1.click();//click on practicetest '>' icon
            Thread.sleep(3000);
            new TopicOpen().openInnerTopic();
            String practiceTestTitle=lessonPage.getPracticeTestTitle().getText();
            if(!practiceTestTitle.contains("(P1) Personalized Practice - Ch 1: The Study of Life")){
                Assert.fail("practice page is not opened in a new tab");
            }
            new TOCShow().chaptertree();
            new TopicOpen().clickOnArrow(0); //click on the tlo arrow
            Thread.sleep(2000);
            new TopicOpen().openInnerTopic();

            String tloTestTitle = lessonPage.getTloTestTitle().getText();
            if (!tloTestTitle.contains("1.1: Discuss the scientific basis for the study of biology - Practice")) {
                Assert.fail(" TLO practice page is not opened in a new tab");
            }
            new TOCShow().chaptertree();
            lessonPage.orionAdaptive_link.get(0).click(); // select orion adaptive link
            lessonPage.getStaticPracticeIcon().click();
            Thread.sleep(2000);
            new TopicOpen().openInnerTopic();
            Thread.sleep(2000);
            String staticTestTitle=lessonPage.getStaticTestTitle().getText();
            if(!staticTestTitle.contains("(P1.1) 1.1 Concept Check")){
                Assert.fail("Static assessment is not opened in a new tab");
            }
        } catch (Exception e) {
            Assert.fail("Exception in test case enhancementToStudyPlanForInstructor in class EnhancementToStudyPlanForInstructorAndMentor", e);
        }
    }
    @Test(priority = 2,enabled = true)
    public void enhancementToStudyPlanForInstructorForGradable() {
        try {
            //tc row no 20
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            new LoginUsingLTI().ltiLogin("20_1");//login as student
            new LoginUsingLTI().ltiLogin("20");//login as instructor
            new Assignment().create(20); //create assignment
            new LoginUsingLTI().ltiLogin("20");//login as instructor
            new Assignment().assignToStudent(20); //assign to the student
            new TOCShow().chaptertree();
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getGradeableAssignmentIcon1().click();
            new TopicOpen().openInnerTopic();
            String question=lessonPage.getQuestion().getText();
            if(!question.contains("True False Test Question for IT18_20")){
                Assert.fail("Question is not opened in a new tab");
            }

        }catch (Exception e) {
            Assert.fail("Exception in test case enhancementToStudyPlanForInstructorForGradable in class EnhancementToStudyPlanForInstructorAndMentor", e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void enhancementToStudyPlanForInstructorForNonGradable() {
        try {
            //tc row no 22
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            new LoginUsingLTI().ltiLogin("22_1");//login as student
            new LoginUsingLTI().ltiLogin("22");//login as instructor
            new Assignment().create(22); //create assignment
            new LoginUsingLTI().ltiLogin("22");//login as instructor
            new Assignment().assignToStudent(22); //assign to the student
            new TOCShow().chaptertree();
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getGradeableAssignmentIcon().click();
            new TopicOpen().openInnerTopic();
            String question=lessonPage.getQuestionNonGradable().getText();
            if(!question.contains("True False Test Question for IT18_22")){
                Assert.fail("Question is not opened in a new tab");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case enhancementToStudyPlanForInstructorForNonGradable in class EnhancementToStudyPlanForInstructorAndMentor", e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void enhancementToStudyPlanForMentor() {
        try {
            //tc row no 24
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            new LoginUsingLTI().ltiLogin("24");//log in as mentor
            new Navigator().NavigateTo("eTextBook");//navigate to etextbook
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getChapterName().click();//click on chapter name
            Thread.sleep(3000);
            lessonPage.getLessonIcon().click();//click on '>' icon next to the lesson name
            new MouseHover().mouserhoverbywebelement(lessonPage.getOpenTabNewLessonIcon());
            String openTabNewLessonIcon=lessonPage.getOpenTabNewLessonIcon().getAttribute("title");
            Assert.assertEquals(openTabNewLessonIcon,"Open in new lesson tab","Open in new lesson tab message is not displayed");
            lessonPage.getOpenTabNewLessonIcon().click();
            String lessonTitle = lessonPage.getLessonTitle().getText();
            if (!lessonTitle.contains("1.1 THE SCIENCE OF BIOLOGY")) {
                Assert.fail("Lesson is not opened in a new tab");
            }
            new TOCShow().chaptertree();//click on tocicon
            lessonPage.orionAdaptive_link.get(1).click(); // select orion adaptive link
            lessonPage.diagnostic_Arrow1.click();//click on dianostic arrow
            new TopicOpen().openInnerTopic();
            Thread.sleep(2000);
            String diagnosticTitle = lessonPage.getDiagnosticTestTitle().getText();
            if (!diagnosticTitle.contains("(P1) Diagnostic - Ch 1: The Study of Life")) {
                Assert.fail("Lesson is not opened in a new tab");
            }
            new TOCShow().chaptertree();//click on tocicon
            lessonPage.practice_arow1.click();//click on practicetest '>' icon
            new TopicOpen().openInnerTopic();
            String practiceTestTitle = lessonPage.getPracticeTestTitle().getText();
            if (!practiceTestTitle.contains("(P1) Personalized Practice - Ch 1: The Study of Life")) {
                Assert.fail("practice page is not opened in a new tab");
            }
            new TOCShow().chaptertree();
            new TopicOpen().clickOnArrow(0); //click on the tlo arrow
            Thread.sleep(2000);
            new TopicOpen().openInnerTopic();
            String tloTestTitle = lessonPage.getTloTestTitle().getText();
            if (!tloTestTitle.contains("1.1: Discuss the scientific basis for the study of biology - Practice")) {
                Assert.fail(" TLO practice page is not opened in a new tab");
            }
            new TOCShow().chaptertree();
            lessonPage.orionAdaptive_link.get(0).click(); // select orion adaptive link
            lessonPage.getStaticPracticeIcon().click();
            Thread.sleep(2000);
            new TopicOpen().openInnerTopic();
            Thread.sleep(2000);
            String staticTestTitle=lessonPage.getStaticTestTitle().getText();
            if(!staticTestTitle.contains("(P1.1) 1.1 Concept Check")){
                Assert.fail("Static assessment is not opened in a new tab");
            }



        } catch (Exception e) {
        Assert.fail("Exception in test case enhancementToStudyPlanForMentor in class EnhancementToStudyPlanForInstructorAndMentor", e);
    }

}
    @Test(priority = 5,enabled = true)
    public void enhancementToStudyPlanForMentorForGradable() {
        try {
            //tc row no 34
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
             new LoginUsingLTI().ltiLogin("34_1");//login as student
            new LoginUsingLTI().ltiLogin("34");//login as instructor
            new Assignment().create(34); //create assignment
            new LoginUsingLTI().ltiLogin("34");//login as instructor
            new Assignment().assignToStudent(34); //assign to the student
            new TOCShow().chaptertree();
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getGradeableAssignmentIcon2().click();
            new TopicOpen().openInnerTopic();
            String question=lessonPage.getQuestion().getText();
            if(!question.contains("True False Test Question for IT18_34")){
                Assert.fail("Question is not opened in a new tab");
            }

        }catch (Exception e) {
            Assert.fail("Exception in test case enhancementToStudyPlanForMentorForGradable in class EnhancementToStudyPlanForInstructorAndMentor", e);
        }
    }

    @Test(priority = 6,enabled = true)
    public void enhancementToStudyPlanForMentorForNonGradable() {
        try {
            //tc row no 36
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            new LoginUsingLTI().ltiLogin("36_1");//login as student
            new LoginUsingLTI().ltiLogin("36");//login as instructor
            new Assignment().create(36); //create assignment
            new LoginUsingLTI().ltiLogin("36");//login as instructor
            new Assignment().assignToStudent(36); //assign to the student
            new TOCShow().chaptertree();
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getGradeableAssignmentIcon3().click();
            new TopicOpen().openInnerTopic();
            String question=lessonPage.getQuestionNonGradable().getText();
            if(!question.contains("True False Test Question for IT18_36")){
                Assert.fail("Question is not opened in a new tab");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case enhancementToStudyPlanForMentorForNonGradable in class EnhancementToStudyPlanForInstructorAndMentor", e);
        }
    }
    @Test(priority = 7,enabled = true)
    public void tocForStudent() {
        try {
            //tc row no 38
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            new LoginUsingLTI().ltiLogin("38");//login as student
            WebElement html =driver.findElement(By.tagName("html"));
            for(int i=0; i<2;i++) {
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                Thread.sleep(3000);
            }
            new Navigator().NavigateTo("eTextBook");//navigate to etextbook
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getChapterName().click();//click on chapter name
            new MouseHover().mouserhoverbywebelement(driver.findElement(By.xpath("//div[@class='ls-inner-arw']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//a[contains(@title,'Chapter Summary')])")));
            boolean elementFound =driver.findElement(By.className("ls-inner-arw")).isDisplayed();
            Assert.assertEquals(elementFound, true, "'>' is not displayed");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",lessonPage.getLessonIcon());
            new MouseHover().mouserhoverbywebelement(lessonPage.getOpenTabNewLessonIcon());
            String openTabNewLessonIcon = lessonPage.getOpenTabNewLessonIcon().getText();
            if (openTabNewLessonIcon.contains("Open in new lesson tab")) {
                Assert.fail("Open in new lesson tab message is not displayed");
            }



        } catch (Exception e) {
            Assert.fail("Exception in test case tocForStudent in class EnhancementToStudyPlanForInstructorAndMentor", e);
        }
    }
    @Test(priority = 8,enabled = true)
    public void clickOnTheEntireRowOfLessonAsInstructor() {
        try {
            //tc row no 41
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            new LoginUsingLTI().ltiLogin("41");//log in as instructor
            new Navigator().NavigateTo("eTextBook");//navigate to etextbook
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getChapterName().click();//click on chapter name
            lessonPage.getForScienceAndBiology().click();
            Thread.sleep(1000);
            String lessonTitle=lessonPage.getLessonTitle().getText();
            if(!lessonTitle.contains("1.1 THE SCIENCE OF BIOLOGY")){
                Assert.fail("Lesson is not opened in a new tab");
            }
            Thread.sleep(1000);
            new TOCShow().chaptertree();
            lessonPage.orionAdaptive_link.get(1).click(); // select orion adaptive link
            lessonPage.getForDiagnostic().click();
            String diagnosticTitle=lessonPage.getDiagnosticTestTitle().getText();
            if(!diagnosticTitle.contains("(P1) Diagnostic - Ch 1: The Study of Life")){
                Assert.fail("Lesson is not opened in a new tab");
            }
            new TOCShow().chaptertree();
            lessonPage.getForTloPractice().click();
            String tloTestTitle = lessonPage.getTloTestTitle().getText();
            if (!tloTestTitle.contains("1.1: Discuss the scientific basis for the study of biology - Practice")) {
                Assert.fail(" TLO practice page is not opened in a new tab");
            }

            new TOCShow().chaptertree();
            lessonPage.orionAdaptive_link.get(0).click(); // select orion adaptive link
            lessonPage.getForStaticPractice().click();
            String staticTestTitle=lessonPage.getStaticTestTitle().getText();
            if(!staticTestTitle.contains("(P1.1) 1.1 Concept Check")){
                Assert.fail("Static assessment is not opened in a new tab");
            }
            new LoginUsingLTI().ltiLogin("41_1");//login as student
            new LoginUsingLTI().ltiLogin("41");//login as instructor
            new Assignment().create(41); //create assignment
            new LoginUsingLTI().ltiLogin("41");//login as instructor
            new Assignment().assignToStudent(41); //assign to the student
            new TOCShow().chaptertree();
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getForAssignmentName().click();
            Thread.sleep(2000);
            String question=lessonPage.getQuestion().getText();
            if(!question.contains("True False Test Question for IT18_41")){
                Assert.fail("Question is not opened in a new tab");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case clickOnTheEntireRowOfLessonAsInstructor in class EnhancementToStudyPlanForInstructorAndMentor", e);
        }
    }
    @Test(priority = 9,enabled = true)
     public void clickOnTheEntireRowOfLessonAsInstructorNonGradAble() {
        try {
            //tc row no 45
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            new LoginUsingLTI().ltiLogin("45_1");//login as student
            new LoginUsingLTI().ltiLogin("45");//login as instructor
            new Assignment().create(45); //create assignment
            new LoginUsingLTI().ltiLogin("45");//login as instructor
            new Assignment().assignToStudent(45); //assign to the student
            new TOCShow().chaptertree();
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getForAssignmentName().click();
            Thread.sleep(2000);
            String question=lessonPage.getQuestion().getText();
            if(!question.contains("True False Test Question for IT18_45")){
                Assert.fail("Question is not opened in a new tab");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case clickOnTheEntireRowOfLessonAsInstructorNonGradAble in class EnhancementToStudyPlanForInstructorAndMentor", e);
        }
    }
    @Test(priority = 10,enabled = true)
    public void clickOnTheEntireRowOfLessonAsMentor() {
        try {
            //tc row no 46
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            new LoginUsingLTI().ltiLogin("46");//log in as mentor
            new Navigator().NavigateTo("eTextBook");//navigate to etextbook
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getChapterName().click();//click on chapter name
            int pos = 0;
            List<WebElement> allOpenLink = driver.findElements(By.xpath("//a[@data-type='lesson']"));
            for(int a=0;a<allOpenLink.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", allOpenLink.get(a));
                if((allOpenLink.get(a).getText().equals("1.1: The Science of Biology"))){
                    allOpenLink.get(a).click();
                    break;
                }
                pos++;

            }
            Thread.sleep(1000);
            String lessonTitle=lessonPage.getLessonTitle().getText();
            if(!lessonTitle.contains("1.1 THE SCIENCE OF BIOLOGY")){
                Assert.fail("Lesson is not opened in a new tab");
            }
            Thread.sleep(1000);
            new TOCShow().chaptertree();
            lessonPage.orionAdaptive_link.get(1).click(); // select orion adaptive link
            lessonPage.getForDiagnostic().click();
            new UIElement().waitAndFindElement(lessonPage.getDiagnosticTestTitle());
            String diagnosticTitle=lessonPage.getDiagnosticTestTitle().getAttribute("title");
            if(!diagnosticTitle.contains("(P1) Diagnostic - Ch 1: The Study of Life")){
                Assert.fail("Lesson is not opened in a new tab");
            }
            new TOCShow().chaptertree();
            lessonPage.getForTloPractice().click();
            String tloTestTitle = lessonPage.getTloTestTitle().getText();
            if (!tloTestTitle.contains("1.1: Discuss the scientific basis for the study of biology - Practice")) {
                Assert.fail(" TLO practice page is not opened in a new tab");
            }
            new TOCShow().chaptertree();
            lessonPage.orionAdaptive_link.get(0).click(); // select orion adaptive link
            WebElement element = driver.findElement(By.xpath("(//a[@title='1.1 Concept Check'])[1]"));
            Coordinates coordinate = ((Locatable)element).getCoordinates();
            coordinate.onPage();
            coordinate.inViewPort();
            lessonPage.getForStaticPractice().click();

            new UIElement().waitAndFindElement(lessonPage.getStaticTestTitle());
            String staticTestTitle=lessonPage.getStaticTestTitle().getText();
            if(!staticTestTitle.contains("(P1.1) 1.1 Concept Check")){
                Assert.fail("Static assessment is not opened in a new tab");
            }
           new LoginUsingLTI().ltiLogin("46_1");//login as student
            new LoginUsingLTI().ltiLogin("46");//login as mentor
            new Assignment().create(46); //create assignment
            new LoginUsingLTI().ltiLogin("46");//login as mentor
            new Assignment().assignToStudent(46); //assign to the student
            new TOCShow().chaptertree();
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getForAssignmentName().click();
            Thread.sleep(3000);
            String question=lessonPage.getQuestion().getText();
            if(!question.contains("True False Test Question for IT18_46")){
                Assert.fail("Question is not opened in a new tab");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case clickOnTheEntireRowOfLessonAsMentor in class EnhancementToStudyPlanForInstructorAndMentor", e);
        }
    }
    @Test(priority = 11,enabled = true)
    public void clickOnTheEntireRowOfLessonAsMentorNonGradAble() {
        try {
            //tc row no 50
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            new LoginUsingLTI().ltiLogin("50_1");//login as student
            new LoginUsingLTI().ltiLogin("50");//login as mentor
            new Assignment().create(50); //create assignment*/
            new LoginUsingLTI().ltiLogin("50");//login as mentor
            new Assignment().assignToStudent(50); //assign to the student
            new TOCShow().chaptertree();
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getForAssignmentName().click();
            new UIElement().waitAndFindElement(By.className("cms-content-question-review-list-label"));
           String question=lessonPage.getQuestion().getText();
            if(!question.contains("True False Test Question for IT18_50")){
                Assert.fail("Question is not opened in a new tab");
            }
        } catch (Exception e) {
            Assert.fail("Exception in test case clickOnTheEntireRowOfLessonAsMentorNonGradAble in class EnhancementToStudyPlanForInstructorAndMentor", e);
        }
    }
    @Test(priority = 12,enabled = true)
    public void instructorHavingTheAccessToMultipleClassSection() {
        try {
            //tc row no 51
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            ClassSectionDropDown classSectionDropDown = PageFactory.initElements(driver,ClassSectionDropDown.class);
            new LoginUsingLTI().ltiLogin("51");//log in as instructor
            WebElement html =driver.findElement(By.tagName("html"));
            for(int i=0; i<2;i++) {
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                Thread.sleep(3000);
            }
            new Navigator().NavigateTo("eTextBook");//navigate to etextbook
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getChapterName().click();//click on chapter name

            lessonPage.getLessonIcon().click();//click on '>' icon next to the lesson name
            lessonPage.getOpenTabNewLessonIcon().click();
            String lessonTitle = lessonPage.getLessonTitle().getText();
            if (!lessonTitle.contains("1.1 THE SCIENCE OF BIOLOGY")) {
                Assert.fail("Lesson is not opened in a new tab");
            }
            new TOCShow().chaptertree();
            classSectionDropDown.defaultClassSection.click();
            classSectionDropDown.classSectionName_list.get(0).click();
            new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@class='ls-site-nav-drop-down']/a")));
            new Navigator().NavigateTo("eTextBook");//navigate to etextbook
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getChapterName().click();//click on chapter name
            boolean elementFound = false;
            try{
                driver.findElement(By.className("ls-inner-arw"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,true,"'>' is not displayed");
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            new Navigator().NavigateTo("eTextBook");//navigate to etextbook
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            try{
                driver.findElement(By.className("ls-inner-arw"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,true,"'>' is not displayed");
            new Navigator().NavigateTo("Course Stream");//navigate to course stream
            new Navigator().NavigateTo("eTextBook");//navigate to etextbook
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            try{
                driver.findElement(By.className("ls-inner-arw"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,true,"'>' is not displayed");
            new Navigator().NavigateTo("Proficiency Report");//Navigate to proficiency report
            new Navigator().NavigateTo("eTextBook");//navigate to etextbook
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            try{
                driver.findElement(By.className("ls-inner-arw"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,true,"'>' is not displayed");
        } catch (Exception e) {
            Assert.fail("Exception in test case instructorHavingTheAccessToMultipleClassSection in class EnhancementToStudyPlanForInstructorAndMentor", e);
        }
    }
    @Test(priority = 13,enabled = true)
    public void mentorHavingTheAccessToMultipleClassSection() {
        try {
            //tc row no 57
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            ClassSectionDropDown classSectionDropDown = PageFactory.initElements(driver,ClassSectionDropDown.class);
            new LoginUsingLTI().ltiLogin("56");//log in as mentor
            new Navigator().NavigateTo("eTextBook");//navigate to etextbook
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getChapterName().click();//click on chapter name
            lessonPage.getLessonIcon().click();//click on '>' icon next to the lesson name
            lessonPage.getOpenTabNewLessonIcon().click();
            String lessonTitle = lessonPage.getLessonTitle().getText();
            if (!lessonTitle.contains("1.1 THE SCIENCE OF BIOLOGY")) {
                Assert.fail("Lesson is not opened in a new tab");
            }
            new TOCShow().chaptertree();
            classSectionDropDown.defaultClassSection.click();
            classSectionDropDown.classSectionName_list.get(0).click();
            Thread.sleep(3000);
            new Navigator().NavigateTo("eTextBook");//navigate to etextbook
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            lessonPage.getChapterName().click();//click on chapter name
            boolean elementFound = false;
            try{
                driver.findElement(By.className("ls-inner-arw"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,true,"'>' is not displayed");
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            new Navigator().NavigateTo("eTextBook");//navigate to etextbook
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            try{
                driver.findElement(By.className("ls-inner-arw"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,true,"'>' is not displayed");
            new Navigator().NavigateTo("Course Stream");//navigate to course stream
            new Navigator().NavigateTo("eTextBook");//navigate to etextbook
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            try{
                driver.findElement(By.className("ls-inner-arw"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,true,"'>' is not displayed");
            new Navigator().NavigateTo("Proficiency Report");//Navigate to proficiency report
            new Navigator().NavigateTo("eTextBook");//navigate to etextbook
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
            try{
                driver.findElement(By.className("ls-inner-arw"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,true,"'>' is not displayed");
        } catch (Exception e) {
            Assert.fail("Exception in test case mentorHavingTheAccessToMultipleClassSection in class EnhancementToStudyPlanForInstructorAndMentor", e);
        }
    }


}

