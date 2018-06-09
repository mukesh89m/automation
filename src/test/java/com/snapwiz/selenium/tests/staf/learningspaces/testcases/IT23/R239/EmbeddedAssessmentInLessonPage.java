package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R239;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ScrollElement;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.lang.model.element.Element;
import java.util.List;

/**
 * Created by durgapathi on 26/8/15.
 */
public class EmbeddedAssessmentInLessonPage extends Driver {

    @Test(priority = 1,enabled = true)
    public void embeddedAssessmentInLessonPage()
    {
        try
        {
            new DirectLogin().directLoginWithCreditial("11"); // direct login as instructor
            new Navigator().NavigateTo("eTextBook"); // navigate e-Textbook
            List<WebElement>  lessonsList = driver.findElements(By.cssSelector("a[data-type='lesson']"));
            lessonsList.get(1).click();
            // Verify lesson page opened
            boolean lessonPage = driver.findElement(By.cssSelector("div[class='lesson ebook']")).isDisplayed();
            if(lessonPage==false)
            {
                Assert.fail("Chapter Section/Lesson is not opened" );
            }
            // Verify Quiz widget present
            boolean assessmentInLessonPage = driver.findElement(By.className("ls-static-assessment-wrapper")).isDisplayed();
            if(assessmentInLessonPage==false)
            {
                Assert.fail("Assessment is not present in the lesson Page" );
            }
            // Scroll down to assessment in lesson page
            WebElement element = driver.findElement(By.className("ls-static-assessment-wrapper"));
            /*((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
            Thread.sleep(1000);*/
            new ScrollElement().scrollToViewOfElement(element);
            // Verify Like option present for Quiz widget
            boolean likeAssessment = driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon']")).isDisplayed();
            if(likeAssessment==false)
            {
                Assert.fail("Like option is not present in the assessment" );
            }
            // Verify Comment option present for Quiz widget
            boolean commentAssessment = driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--comments-icon']")).isDisplayed();
            if(commentAssessment==false)
            {
                Assert.fail("Comment option is not present in the assessment" );
            }
            // Verify Assign option present for Quiz widget
            boolean assignAssessment = driver.findElement(By.xpath(".//*[@widgettype='assessment']/span[@class='assign-options']")).isDisplayed();
            if(assignAssessment==false)
            {
                Assert.fail("Assign option is not present in the assessment" );
            }
            // Like assessment
            driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon']")).click();
            String likeAssessmentCount = driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/span[@class='ls-right-post-like-count']")).getText();
            if(likeAssessmentCount=="0")
            {
                Assert.fail("Instructor is not able to like in the assessment" );
            }
            // unlike assessment
            driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon']")).click();
            String unlikeAssessmentCount = driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/span[@class='ls-right-post-like-count']")).getText();
            if(unlikeAssessmentCount==likeAssessmentCount)
            {
                Assert.fail("Instructor is not able to unlike in the assessment" );
            }
            // Post a comment
            String randomText = new RandomString().randomstring(10);
            driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--comments-icon']")).click();
            Thread.sleep(2000);
            WebElement elementScroll = driver.findElement(By.xpath(".//*[@class='widget-social-element-container bg-white']//div[@class='post-comment post-widget-comment']"));
            new ScrollElement().scrollToViewOfElement(elementScroll);
            Thread.sleep(2000);
            driver.findElement(By.xpath(".//*[@class='widget-social-element-container bg-white']//div[@placeholder='Write your comment']")).click();
            driver.findElement(By.xpath(".//*[@class='widget-social-element-container bg-white']//div[@placeholder='Write your comment']")).sendKeys(randomText);
            new Click().clickbyxpath(".//*[@class='widget-social-element-container bg-white']//div[@class='post-comment post-widget-comment']");
            boolean commentPost = false;
            List<WebElement> commentTextList = driver.findElements(By.className("ls-stream-post__comment-text"));
            System.out.println("randomText::"+randomText);
            for(int i=0;i<commentTextList.size();i++)
            {
                String commentText = commentTextList.get(i).getText();
                if(commentText.equals(randomText))
                {
                    System.out.println("commentText::"+commentText);
                    commentPost=true;
                    break;
                }
            }
            if(commentPost=false)
            {
                Assert.fail("Not able to post comment for quiz assessment" );
            }
            //Assign assessment
            driver.findElement(By.xpath(".//*[@widgettype='assessment']/span[@class='assign-options']")).click();
            driver.findElement(By.xpath(".//*[@class='widget assessment-widget']//span[@class='assign-this-text']")).click();
            new AssignLesson().assignTOCToSpecificStudent(11);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase embeddedAssessmentInLessonPage in class EmbeddedAssessmentInLessonPage", e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void embeddedAssessmentWithOneNonMPQuestion()
    {
        try
        {
            new DirectLogin().directLoginWithCreditial("21"); // Login as student
            new Click().clickByclassname("study-course-link"); // Click on Study
            Thread.sleep(3000);
            // Verify e-Textbook load
            boolean eTextBook = driver.findElement(By.cssSelector("i[class='close-study-plan-icon close-study-plan']")).isDisplayed();
            if(eTextBook==false)
            {
                Assert.fail("Click on Study button is not loaded e-Textbook" );
            }
            // Click on lesson
            List<WebElement>  lessonsList = driver.findElements(By.cssSelector("a[data-type='lesson']"));
            lessonsList.get(3).click();
            boolean lessonPage = driver.findElement(By.cssSelector("div[class='lesson ebook']")).isDisplayed();
            if(lessonPage==false)
            {
                Assert.fail("Lesson is not opened" );
            }
            // Scroll down to the assessment
            WebElement element = driver.findElement(By.xpath("(//span[text()='P1.3:'])[2]"));
            new ScrollElement().scrollToViewOfElement(element);
           // Verify Like option present for Quiz widget
            boolean likeAssessment = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon'])[2]")).isDisplayed();
            if(likeAssessment==false)
            {
                Assert.fail("Like option is not present in the assessment" );
            }
            // Like assessment
            driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon'])[2]")).click();
            String likeAssessmentCount = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/span[@class='ls-right-post-like-count'])[2]")).getText();
            if(likeAssessmentCount=="0")
            {
                Assert.fail("Student is not able to like in the assessment" );
            }
            // unlike assessment
            driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon'])[2]")).click();
            String unlikeAssessmentCount = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/span[@class='ls-right-post-like-count'])[2]")).getText();
            if(unlikeAssessmentCount==likeAssessmentCount)
            {
                Assert.fail("Student is not able to unlike in the assessment" );
            }
            String randomText = new RandomString().randomstring(10);
            // Verify Comment option present for Quiz widget
            boolean commentAssessment = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--comments-icon'])[2]")).isDisplayed();
            if(commentAssessment==false)
            {
                Assert.fail("Comment option is not present in the assessment" );
            }
            // Post a comment
            driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--comments-icon'])[2]")).click();
            Thread.sleep(2000);
            WebElement elementScroll = driver.findElement(By.xpath(".//*[@class='widget-social-element-container bg-white']//div[@class='post-comment post-widget-comment']"));
            new ScrollElement().scrollToViewOfElement(elementScroll);
            driver.findElement(By.xpath(".//*[@class='widget assessment-widget widget-expanded']//div[@placeholder='Write your comment']")).click();
            driver.findElement(By.xpath(".//*[@class='widget assessment-widget widget-expanded']//div[@placeholder='Write your comment']")).sendKeys(randomText);
            new Click().clickbyxpath(".//*[@class='widget-social-element-container bg-white']//div[@class='post-comment post-widget-comment']");
            boolean commentPost = false;
            List<WebElement> commentTextList = driver.findElements(By.className("ls-stream-post__comment-text"));
            System.out.println("randomText::"+randomText);
            for(int i=0;i<commentTextList.size();i++)
            {
                String commentText = commentTextList.get(i).getText();
                if(commentText.equals(randomText))
                {
                    System.out.println("commentText::"+commentText);
                    commentPost=true;
                    break;
                }
            }
            if(commentPost=false)
            {
                Assert.fail("Not able to post comment for quiz assessment" );
            }

            // BookMark Quiz Widget
            new Click().clickbyxpath("(.//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-unbookmarked'])[2]");
            boolean bookMark = driver.findElement(By.xpath(".//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-bookmarked']")).isDisplayed();
            if(bookMark==false)
            {
                Assert.fail("Not able to bookmark for quiz assessment" );
            }
            // Un BookMark Quiz Widget
            new Click().clickbyxpath(".//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-bookmarked']");
            boolean unbookMark = driver.findElement(By.xpath("(.//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-unbookmarked'])[2]")).isDisplayed();
            if(unbookMark==false)
            {
                Assert.fail("Not able to unbookmark for quiz assessment" );
            }
            new Click().clickBycssselector("div[class='toggle-widget-size toggle-widget-size-collapse']"); // collapse widget
            boolean expandWidget = driver.findElement(By.xpath("(.//*[@widgettype='assessment']//div[@class='toggle-widget-size toggle-widget-size-expand'])[2]")).isDisplayed();
            if(expandWidget==false)
            {
                Assert.fail("Expand widget is not present for quiz assessment" );
            }
            // expand quiz widget
            new Click().clickbyxpath("(.//*[@widgettype='assessment']//div[@class='toggle-widget-size toggle-widget-size-expand'])[2]");
            Thread.sleep(1000);
            boolean expandAssessmentWidget = driver.findElement(By.className("widget-close")).isDisplayed();
            if(expandAssessmentWidget==false)
            {
                Assert.fail("Not able to expand quiz assessment" );
            }
            new Click().clickBycssselector("div[class='toggle-widget-size toggle-widget-size-collapse']"); // collapse widget
            // Summary drop down
            new Click().clickbyxpath("(.//div[@class='static-assessment-question-summery'])[2]");
            boolean summaryDropDown = driver.findElement(By.className("question-summary-header")).isDisplayed();
            if(summaryDropDown==false)
            {
                Assert.fail("Not able to Click on summary dropdown" );
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase embeddedAssessmentWithOneNonMPQuestion in class EmbeddedAssessmentInLessonPage", e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void attemptQuestionInTheWidget()
    {
        try
        {
            new DirectLogin().directLoginWithCreditial("33"); // Login as student
            new Click().clickByclassname("study-course-link"); // Click on Study
            Thread.sleep(3000);
            // Click on lesson
            List<WebElement>  lessonsList = driver.findElements(By.cssSelector("a[data-type='lesson']"));
            lessonsList.get(3).click();
            boolean lessonPage = driver.findElement(By.cssSelector("div[class='lesson ebook']")).isDisplayed();
            if(lessonPage==false)
            {
                Assert.fail("Lesson is not opened" );
            }
            // Scroll down to the assessment
            WebElement element = driver.findElement(By.xpath("(//span[text()='P1.3:'])[2]"));
            new ScrollElement().scrollToViewOfElement(element);
            // Attempt the first Question
            Thread.sleep(1000);

            new Click().clickbyxpath("(.//*[@class='true-false-student-answer-label'])[1]");
            Thread.sleep(1000);
            new Click().clickbyxpath("(.//*[@title='Submit Answer'])[2]");
            Thread.sleep(2000);
            System.out.println("nextQuestion::" + driver.findElement(By.className("ls-embedded-static-next-button")));
            boolean nextQuestion = driver.findElement(By.xpath("(.//*[@class='ls-embedded-static-next-button'])[2]")).isDisplayed();
            if(nextQuestion==false)
            {
                Assert.fail("Student is not able to attempt the Embedded assessment question" );
            }
            new Click().clickbyxpath("(.//*[@class='ls-embedded-static-next-button'])[2]"); // Next Question
            Thread.sleep(1000);
            // Hint in Quiz Widget
            boolean hint = driver.findElement(By.className("al-diag-test-hint-drop-down-wrapper")).isDisplayed();
            if(hint==false)
            {
                Assert.fail("Hint is not showing for Embedded assessment question" );
            }
            // Click on Hint
            new Click().clickByclassname("al-diag-test-hint-drop-down-wrapper");
            boolean hintNotify = driver.findElement(By.xpath("(.//*[@id='al-hint-robo-wrapper-sidebar-scroll'])[2]")).isDisplayed();
            if(hintNotify==false)
            {
                Assert.fail("Click on Hint button is not displaying the notification" );
            }
            // Confidence Level
            boolean confidentLevel = driver.findElement(By.xpath("(.//*[@class='al-self-rating'])[3]")).isDisplayed();
            if(confidentLevel==false)
            {
                Assert.fail("Confidence panel is not isplayed in the question part" );
            }
            // Select Confident Level
            new Click().clickbyxpath("(.//*[@class='confidence-level-block']//a[@id='3'])[3]");
            String cfLevelSelect = driver.findElement(By.xpath("(.//*[@class='confidence-level-block']//a[@id='3'])[3]")).getAttribute("class");
            if(!cfLevelSelect.contains("selected"))
            {
                Assert.fail("Not able to select confidence level" );
            }

            List<WebElement> answerChoices = driver.findElements(By.xpath(".//*[@class='single-select-choice-icon-preview single-select-choice-icon-deselect']"));
            answerChoices.get(0).click();
            boolean submitQuestion = driver.findElement(By.xpath("(.//*[@title='Submit Answer'])[2]")).isDisplayed();
            if(submitQuestion==false)
            {
                Assert.fail("Submit button is not present" );
            }
            boolean questionReviewPage = driver.findElement(By.xpath("(.//*[@id='cms-question-preview-question-content'])[2]")).isDisplayed();
            if(questionReviewPage==false)
            {
                Assert.fail("Question review page is not displayed" );
            }
            new Click().clickbyxpath("(.//*[@title='Submit Answer'])[2]");
            boolean solution = driver.findElement(By.cssSelector("img[title='Solution']")).isDisplayed();
            if(solution==false)
            {
                Assert.fail("Solution button is not present in review page" );
            }
            driver.findElement(By.cssSelector("img[title='Solution']")).click();//Click on Solution Button
            boolean solutionContent = driver.findElement(By.xpath("(.//*[@class='al-diag-test-solution-content'])[1]")).isDisplayed();
            if(solutionContent==false)
            {
                Assert.fail("Solution Content is not displayed" );
            }
            new Click().clickbyxpath("(.//*[@class='ls-embedded-static-next-button'])[2]"); // Next Question
            // class = assessment-widget-retake
            int retakeQuiz = driver.findElements(By.className("ls-embedded-static-retake-button")).size();
            System.out.println("retakeQuiz::" + retakeQuiz);
            while (retakeQuiz==0)
            {
                new Click().clickbyxpath("(.//*[@title='Submit Answer'])[2]");
                Thread.sleep(1000);
                int nextButton = driver.findElements(By.xpath("(.//*[@class='ls-embedded-static-next-button'])[2]")).size();
                if(nextButton>0)
                {
                    new Click().clickbyxpath("(.//*[@class='ls-embedded-static-next-button'])[2]"); // Next Question
                }
                retakeQuiz = driver.findElements(By.className("ls-embedded-static-retake-button")).size();
            }
            boolean retakeButton = driver.findElement(By.className("ls-embedded-static-retake-button")).isDisplayed();
            if(retakeButton==false)
            {
                Assert.fail("Retake button is not present" );
            }
            Thread.sleep(1000);
            new Click().clickByclassname("assessment-widget-retake");
            Thread.sleep(1000);
            String firstQuestion = driver.findElement(By.xpath("(.//*[@id='display-label'])[1]")).getText();
            System.out.println("firstQuestion::"+firstQuestion);
            if(!firstQuestion.trim().contains("Q 1:"))
            {
                Assert.fail("Click on Retake button is not navigated to first Question" );
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase attemptQuestionInTheWidget in class EmbeddedAssessmentInLessonPage", e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void embeddedAssessmentWithOneMPQuestion()
    {
        try
        {
            new DirectLogin().directLoginWithCreditial("49"); // Login as student
            new Click().clickByclassname("study-course-link"); // Click on Study
            Thread.sleep(3000);
            // Verify e-Textbook load
            boolean eTextBook = driver.findElement(By.cssSelector("i[class='close-study-plan-icon close-study-plan']")).isDisplayed();
            if(eTextBook==false)
            {
                Assert.fail("Click on Study button is not loaded e-Textbook" );
            }
            // Click on lesson
            List<WebElement>  lessonsList = driver.findElements(By.cssSelector("a[data-type='lesson']"));
            lessonsList.get(4).click();
            boolean lessonPage = driver.findElement(By.cssSelector("div[class='lesson ebook']")).isDisplayed();
            if(lessonPage==false)
            {
                Assert.fail("Lesson is not opened" );
            }
            // Scroll down to the assessment
            WebElement element = driver.findElement(By.className("ls-static-assessment-wrapper"));
            new ScrollElement().scrollToViewOfElement(element);
            // Verify Like option present for Quiz widget
            boolean likeAssessment = driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon']")).isDisplayed();
            if(likeAssessment==false)
            {
                Assert.fail("Like option is not present in the assessment" );
            }
            // Verify Comment option present for Quiz widget

            // Like assessment
            driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon']")).click();
            String likeAssessmentCount = driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/span[@class='ls-right-post-like-count']")).getText();
            if(likeAssessmentCount=="0")
            {
                Assert.fail("Student is not able to like in the assessment" );
            }
            // unlike assessment
            driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon']")).click();
            String unlikeAssessmentCount = driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/span[@class='ls-right-post-like-count']")).getText();
            if(unlikeAssessmentCount==likeAssessmentCount)
            {
                Assert.fail("Student is not able to unlike in the assessment" );
            }
            String randomText = new RandomString().randomstring(10);
            // Verify Comment option present for Quiz widget
            boolean commentAssessment = driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--comments-icon']")).isDisplayed();
            if(commentAssessment==false)
            {
                Assert.fail("Comment option is not present in the assessment" );
            }
            // Post a comment
            driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--comments-icon']")).click();
            Thread.sleep(2000);
            WebElement elementScroll = driver.findElement(By.xpath(".//*[@class='widget-social-element-container bg-white']//div[@class='post-comment post-widget-comment']"));
            new ScrollElement().scrollToViewOfElement(elementScroll);
            driver.findElement(By.xpath(".//*[@class='widget assessment-widget widget-expanded']//div[@placeholder='Write your comment']")).click();
            driver.findElement(By.xpath(".//*[@class='widget assessment-widget widget-expanded']//div[@placeholder='Write your comment']")).sendKeys(randomText);
            new Click().clickbyxpath(".//*[@class='widget-social-element-container bg-white']//div[@class='post-comment post-widget-comment']");
            boolean commentPost = false;
            List<WebElement> commentTextList = driver.findElements(By.className("ls-stream-post__comment-text"));
            System.out.println("randomText::" + randomText);
            for(int i=0;i<commentTextList.size();i++)
            {
                String commentText = commentTextList.get(i).getText();
                if(commentText.equals(randomText))
                {
                    System.out.println("commentText::"+commentText);
                    commentPost=true;
                    break;
                }
            }
            if(commentPost=false)
            {
                Assert.fail("Not able to post comment for quiz assessment" );
            }

            // BookMark Quiz Widget
            new Click().clickbyxpath(".//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-unbookmarked']");
            boolean bookMark = driver.findElement(By.xpath(".//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-bookmarked']")).isDisplayed();
            if(bookMark==false)
            {
                Assert.fail("Not able to bookmark for quiz assessment" );
            }
            // Un BookMark Quiz Widget
            new Click().clickbyxpath(".//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-bookmarked']");
            boolean unbookMark = driver.findElement(By.xpath(".//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-unbookmarked']")).isDisplayed();
            if(unbookMark==false)
            {
                Assert.fail("Not able to unbookmark for quiz assessment" );
            }
            // Expand option present in assessment
            new Click().clickBycssselector("div[class='toggle-widget-size toggle-widget-size-collapse']"); // collapse widget
            boolean expandWidget = driver.findElement(By.xpath(".//*[@widgettype='assessment']//div[@class='toggle-widget-size toggle-widget-size-expand']")).isDisplayed();
            if(expandWidget==false)
            {
                Assert.fail("Expand widget is not present for quiz assessment" );
            }
            // expand quiz widget
            new Click().clickbyxpath(".//*[@widgettype='assessment']//div[@class='toggle-widget-size toggle-widget-size-expand']");
            Thread.sleep(1000);
            boolean expandAssessmentWidget = driver.findElement(By.className("widget-close")).isDisplayed();
            if(expandAssessmentWidget==false)
            {
                Assert.fail("Not able to expand quiz assessment");
            }
            new Click().clickBycssselector("div[class='toggle-widget-size toggle-widget-size-collapse']"); // collapse widget
            // Summary drop down
            new Click().clickbyxpath(".//div[@class='static-assessment-question-summery']");
            boolean summaryDropDown = driver.findElement(By.className("question-summary-header")).isDisplayed();
            if(summaryDropDown==false)
            {
                Assert.fail("Not able to Click on summary dropdown" );
            }
            // MP Question expand
            int mpQuestionUI = driver.findElements(By.className("question-title-wrapper")).size();
            if(mpQuestionUI<3)
            {
                Assert.fail("UI for the MPQuestion is not correct");
            }
            int mpQuestionExpand = driver.findElements(By.className("question-toggle-arrow-icon")).size();
            if(mpQuestionExpand<3)
            {
                Assert.fail("UI for the MPQuestion in the expanded widget is not correct" );
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase embeddedAssessmentWithOneMPQuestion in class EmbeddedAssessmentInLessonPage", e);
        }
    }
    // pending
    @Test(priority = 5,enabled = true)
    public void attemptTheQuestionPartInTheWidget()
    {
        try
        {
            new DirectLogin().directLoginWithCreditial("49"); // Login as student
            new Click().clickByclassname("study-course-link"); // Click on Study
            Thread.sleep(3000);
            // Verify e-Textbook load
            boolean eTextBook = driver.findElement(By.cssSelector("i[class='close-study-plan-icon close-study-plan']")).isDisplayed();
            if(eTextBook==false)
            {
                Assert.fail("Click on Study button is not loaded e-Textbook" );
            }
            // Click on lesson
            List<WebElement>  lessonsList = driver.findElements(By.cssSelector("a[data-type='lesson']"));
            lessonsList.get(4).click();
            boolean lessonPage = driver.findElement(By.cssSelector("div[class='lesson ebook']")).isDisplayed();
            if(lessonPage==false)
            {
                Assert.fail("Lesson is not opened" );
            }
            // Scroll down to the assessment
            WebElement element = driver.findElement(By.className("ls-static-assessment-wrapper"));
            new ScrollElement().scrollToViewOfElement(element);
            Thread.sleep(2000);
            driver.findElement(By.xpath("(.//*[@class = 'question-toggle-arrow-icon'])[1]")).click();
            Thread.sleep(2000);
            boolean hint = driver.findElement(By.className("part-question-hint")).isDisplayed();
            System.out.println("hint::" + hint);
            if(hint==false)
            {
                Assert.fail("Hint is not present in assessment" );
            }
            Thread.sleep(1000);
            new Click().clickByclassname("part-question-hint");
            Thread.sleep(1000);
            boolean hintHighlight = driver.findElement(By.xpath(".//*[@class='part-question-hint highlight-btn']")).isDisplayed();
            if(hintHighlight==false)
            {
                Assert.fail("Not able to access hint in assessment" );
            }
            boolean hintContent = driver.findElement(By.className("multi-part-question-hint-label")).isDisplayed();
            if(hintContent==false)
            {
                Assert.fail("Contents on the hint option is not displayed" );
            }
            boolean confidentLevel = driver.findElement(By.className("al-self-rating")).isDisplayed();
            if(confidentLevel==false)
            {
                Assert.fail("Confidence panel is not isplayed in the question part" );
            }
            new Click().clickbyxpath("(.//*[@class='confidence-level-block']//a[@id='3'])[1]");
            String cfLevelSelect = driver.findElement(By.xpath("(.//*[@class='confidence-level-block']//a[@id='3'])[1]")).getAttribute("class");
            if(!cfLevelSelect.contains("selected"))
            {
                Assert.fail("Not able to select confidence level" );
            }
            //new Click().clickBycssselector("div[class='question-toggle-arrow-icon collapse']");
            // new Assignment().attemptQuizWidgetWithMPQ();
            Thread.sleep(1000);
            int trueFalse = driver.findElements(By.className("true-false-student-answer-label")).size();
            if(trueFalse>0)
            {
                List<WebElement> attemptTrue = driver.findElements(By.className("true-false-student-answer-label"));
                attemptTrue.get(0).click();
            }
            new Click().clickBycssselector("div[class='question-toggle-arrow-icon collapse']");
            new Click().clickByclassname("ls-embedded-static-submit-button");
            boolean reTake = driver.findElement(By.className("assessment-widget-retake")).isDisplayed();
            if(reTake==false)
            {
                Assert.fail("Not able to Submit the Assessment, Retake is not present" );
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase attemptTheQuestionPartInTheWidget in class EmbeddedAssessmentInLessonPage", e);
        }
    }

    @Test(priority = 6,enabled = true)
    public void attemptAllTheAuestionPartInTheMPQ()
    {
        try
        {
            new DirectLogin().directLoginWithCreditial("49"); // direct login as student
            new Navigator().NavigateTo("eTextBook"); // navigate e-Textbook
            List<WebElement>  lessonsList = driver.findElements(By.cssSelector("a[data-type='lesson']"));
            lessonsList.get(4).click();
            // Verify lesson page opened
            boolean lessonPage = driver.findElement(By.cssSelector("div[class='lesson ebook']")).isDisplayed();
            if(lessonPage==false)
            {
                Assert.fail("Chapter Section/Lesson is not opened" );
            }
            // Verify Quiz widget present
            boolean assessmentInLessonPage = driver.findElement(By.className("ls-static-assessment-wrapper")).isDisplayed();
            if(assessmentInLessonPage==false)
            {
                Assert.fail("Assessment is not present in the lesson Page" );
            }
            // Scroll down to assessment in lesson page
            WebElement element = driver.findElement(By.className("ls-static-assessment-wrapper"));
            new ScrollElement().scrollToViewOfElement(element);
            List<WebElement> mpQuestions = driver.findElements(By.xpath(".//*[starts-with(@class, 'question-toggle-arrow-icon')]"));
            mpQuestions.get(0).click();
            int trueFalse = driver.findElements(By.className("true-false-student-answer-label")).size();
            if(trueFalse>0)
            {
                List<WebElement> attemptTrue = driver.findElements(By.className("true-false-student-answer-label"));
                attemptTrue.get(0).click();
            }
            driver.findElement(By.xpath(".//*[starts-with(@class, 'question-toggle-arrow-icon')]")).click();
            Thread.sleep(2000);
            int hints = driver.findElements(By.className("part-question-hint")).size();
            System.out.println("hint::" + hints);
            if(hints<5)
            {
                Assert.fail("Hint is not present in assessment");
            }
            Thread.sleep(1000);
            new Click().clickByclassname("part-question-hint");
            Thread.sleep(1000);
            boolean hintHighlight = driver.findElement(By.xpath("./*//*[@class='part-question-hint highlight-btn']")).isDisplayed();
            if(hintHighlight==false)
            {
                Assert.fail("Not able to access hint in assessment" );
            }
            boolean hintContent = driver.findElement(By.className("multi-part-question-hint-label")).isDisplayed();
            if(hintContent==false)
            {
                Assert.fail("Contents on the hint option is not displayed" );
            }
            int confidentLevel = driver.findElements(By.className("al-self-rating")).size();
            System.out.println("confidentLevel::"+confidentLevel);
            if(confidentLevel<6)
            {
                Assert.fail("Confidence panel is not isplayed in the question part");
            }
            Thread.sleep(2000);
            new Click().clickbyxpath("(.//*[@class='confidence-level-almost'])[1]");
            String cfLevelSelect = driver.findElement(By.xpath("(//*[starts-with(@class, 'confidence-level-almost')])[1]")).getAttribute("class");
            if(!cfLevelSelect.contains("selected"))
            {
                Assert.fail("Not able to select confidence level" );
            }
            List<WebElement> nextQuestionPart = driver.findElements(By.className("next-question-part"));
            for(int i=0;i<4;i++)
            {

                nextQuestionPart.get(i).click();
                System.out.println("i:::" + i);
            }
            // Submit Answer
            new Click().clickByclassname("last-question-part");
            boolean reTake = driver.findElement(By.className("assessment-widget-retake")).isDisplayed();
            if(reTake==false)
            {
                Assert.fail("Not able to Submit the Assessment, Retake is not present" );
            }
            // Question Submitted or Not
            boolean solution = driver.findElement(By.className("part-question-solution")).isDisplayed();
            if(solution==false)
            {
                Assert.fail("Question are not submitted" );
            }
            // Review Page
            boolean reviewPage = driver.findElement(By.className("report-points-container")).isDisplayed();
            if(reviewPage==false)
            {
                Assert.fail("Question review page is not displayed" );
            }
            // Question Card Expand
            int questionCard = driver.findElements(By.xpath(".//*[@class='question-toggle-arrow-icon collapse']")).size();
            System.out.println("questionCardSize::"+questionCard);
            if(questionCard<5)
            {
                Assert.fail("All question cards are not in expanded format");
            }
            int reviewPageForAllCards = driver.findElements(By.className("report-points-container")).size();
            if(reviewPageForAllCards<5)
            {
                Assert.fail("Review page for all the question card is not dispalyed" );
            }
            // Solution
            int solutionInReviewPage = driver.findElements(By.className("part-question-solution")).size();
            if(solutionInReviewPage<5)
            {
                Assert.fail("Solution option is not displayed in the review page" );
            }
            boolean finish = driver.findElement(By.className("assessment-widget-retake")).isDisplayed();
            if(finish==false)
            {
                Assert.fail("Ratake is not present" );
            }
            // Solution expand
            List<WebElement> solutionExpand = driver.findElements(By.className("part-question-solution"));
            solutionExpand.get(0).click();
            boolean solutionClick = driver.findElement(By.cssSelector("div[class='part-question-solution highlight-btn']")).isDisplayed();
            if(solutionClick==false)
            {
                Assert.fail("Not able to Click on Solution");
            }
            boolean solutionContent = driver.findElement(By.className("multi-part-question-solution-content")).isDisplayed();
            if(solutionContent==false)
            {
                Assert.fail("Solution Content is not dispalying" );
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase attemptAllTheAuestionPartInTheMPQ in class EmbeddedAssessmentInLessonPage", e);
        }
    }

    @Test(priority = 7,enabled = true)
    public void assessmentRatake()
    {
        try
        {
            new DirectLogin().directLoginWithCreditial("87"); // direct login as instructor
            new Navigator().NavigateTo("eTextBook"); // navigate e-Textbook
            // Click on Lesson
            List<WebElement>  lessonsList = driver.findElements(By.cssSelector("a[data-type='lesson']"));
            lessonsList.get(4).click();
            WebElement element = driver.findElement(By.className("ls-static-assessment-wrapper"));
            new ScrollElement().scrollToViewOfElement(element);
            List<WebElement> mpQuestions = driver.findElements(By.xpath("(.//*[@class = 'question-toggle-arrow-icon'])[1]"));
            mpQuestions.get(0).click();
            int trueFalse = driver.findElements(By.className("true-false-student-answer-label")).size();
            if(trueFalse>0)
            {
                List<WebElement> attemptTrue = driver.findElements(By.className("true-false-student-answer-label"));
                attemptTrue.get(0).click();
            }
            List<WebElement> nextQuestionPart = driver.findElements(By.className("next-question-part"));
            for(int i=0;i<4;i++)
            {

                nextQuestionPart.get(i).click();
                System.out.println("i:::"+i);
            }
            // Submit Answer
            new Click().clickByclassname("last-question-part");
            boolean reTake = driver.findElement(By.className("assessment-widget-retake")).isDisplayed();
            if(reTake==false)
            {
                Assert.fail("Not able to Submit the Assessment, Retake is not present");
            }
            new Click().clickByclassname("assessment-widget-retake");// click on Retake
            Thread.sleep(1000);
            // Click on Ratake, starts with first question
            String ansCount =  driver.findElement(By.className("ans-count")).getText();
            System.out.println("ansCount::" + ansCount);
            if(!ansCount.contains("0"))
            {
                Assert.fail("Click on Retake button is not started with first question " );
            }
            new DirectLogin().directLoginWithCreditial("87"); // direct login as instructor
            new Navigator().NavigateTo("eTextBook"); // navigate e-Textbook
            // Click on Lesson
            lessonsList = driver.findElements(By.cssSelector("a[data-type='lesson']"));
            lessonsList.get(4).click();
            element = driver.findElement(By.className("ls-static-assessment-wrapper"));
            new ScrollElement().scrollToViewOfElement(element);
            mpQuestions = driver.findElements(By.xpath(".//*[starts-with(@class, 'question-toggle-arrow-icon')]"));
            mpQuestions.get(0).click();
            trueFalse = driver.findElements(By.className("true-false-student-answer-label")).size();
            if(trueFalse>0)
            {
                List<WebElement> attemptTrue = driver.findElements(By.className("true-false-student-answer-label"));
                attemptTrue.get(0).click();
            }
            new Click().clickByclassname("last-question-part");
            reTake = driver.findElement(By.className("assessment-widget-retake")).isDisplayed();
            if(reTake==false)
            {
                Assert.fail("Not able to Submit the Assessment, Retake is not present" );
            }
            // Question Submitted or Not
            boolean solution = driver.findElement(By.className("part-question-solution")).isDisplayed();
            if(solution==false)
            {
                Assert.fail("Question are not submitted" );
            }
            // Review Page
            boolean reviewPage = driver.findElement(By.className("report-points-container")).isDisplayed();
            if(reviewPage==false)
            {
                Assert.fail("Question review page is not displayed");
            }
            int solutionInReviewPage = driver.findElements(By.className("part-question-solution")).size();
            if(solutionInReviewPage<5)
            {
                Assert.fail("Solution option is not displayed in the review page" );
            }
            boolean finish = driver.findElement(By.className("assessment-widget-retake")).isDisplayed();
            if(finish==false)
            {
                Assert.fail("Ratake is not present" );
            }
            // Solution expand
            List<WebElement> solutionExpand = driver.findElements(By.className("part-question-solution"));
            solutionExpand.get(0).click();
            boolean solutionClick = driver.findElement(By.cssSelector("div[class='part-question-solution highlight-btn']")).isDisplayed();
            if(solutionClick==false)
            {
                Assert.fail("Not able to Click on Solution" );
            }
            boolean solutionContent = driver.findElement(By.className("multi-part-question-solution-content")).isDisplayed();
            if(solutionContent==false)
            {
                Assert.fail("Solution Content is not dispalying" );
            }
            // Submit Answer
            reTake = driver.findElement(By.className("assessment-widget-retake")).isDisplayed();
            if(reTake==false)
            {
                Assert.fail("Not able to Submit the Assessment, Retake is not present" );
            }
            new Click().clickByclassname("assessment-widget-retake");// click on Retake
            Thread.sleep(1000);
            // Click on Retake, starts with first question
            ansCount =  driver.findElement(By.className("ans-count")).getText();
            if(!ansCount.contains("0"))
            {
                Assert.fail("Click on Retake button is not started with first question " );
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase assessmentRatake in class EmbeddedAssessmentInLessonPage", e);
        }
    }

    @Test(priority = 8,enabled = true)
    public void embeddedAssessmentWithMPQAndNonMPQuestionTypes()
    {
        try
        {
            new DirectLogin().directLoginWithCreditial("98"); // Login as student
            new Click().clickByclassname("study-course-link"); // Click on Study
            Thread.sleep(3000);
            // Verify e-Textbook load
            boolean eTextBook = driver.findElement(By.cssSelector("i[class='close-study-plan-icon close-study-plan']")).isDisplayed();
            if(eTextBook==false)
            {
                Assert.fail("Click on Study button is not loaded e-Textbook" );
            }
            // Click on lesson
            List<WebElement>  lessonsList = driver.findElements(By.cssSelector("a[data-type='lesson']"));
            lessonsList.get(3).click();
            boolean lessonPage = driver.findElement(By.cssSelector("div[class='lesson ebook']")).isDisplayed();
            if(lessonPage==false)
            {
                Assert.fail("Lesson is not opened" );
            }
            // Scroll down to the assessment
            WebElement element = driver.findElement(By.xpath("(//span[text()='P1.3:'])[1]"));
            new ScrollElement().scrollToViewOfElement(element);
            // Verify Like option present for Quiz widget
            boolean likeAssessment = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon'])[1]")).isDisplayed();
            if(likeAssessment==false)
            {
                Assert.fail("Like option is not present in the assessment" );
            }
            // Like assessment
            driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon'])[1]")).click();
            String likeAssessmentCount = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/span[@class='ls-right-post-like-count'])[1]")).getText();
            if(likeAssessmentCount=="0")
            {
                Assert.fail("Student is not able to like in the assessment" );
            }
            // unlike assessment
            driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon'])[1]")).click();
            String unlikeAssessmentCount = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/span[@class='ls-right-post-like-count'])[1]")).getText();
            if(unlikeAssessmentCount==likeAssessmentCount)
            {
                Assert.fail("Student is not able to unlike in the assessment" );
            }
            String randomText = new RandomString().randomstring(10);
            // Verify Comment option present for Quiz widget
            boolean commentAssessment = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--comments-icon'])[1]")).isDisplayed();
            if(commentAssessment==false)
            {
                Assert.fail("Comment option is not present in the assessment" );
            }
            // Post a comment
            driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--comments-icon'])[1]")).click();
            Thread.sleep(2000);
            WebElement elementScroll = driver.findElement(By.xpath(".//*[@class='widget-social-element-container bg-white']//div[@class='post-comment post-widget-comment']"));
            new ScrollElement().scrollToViewOfElement(elementScroll);
            driver.findElement(By.xpath(".//*[@class='widget assessment-widget widget-expanded']//div[@placeholder='Write your comment']")).click();
            driver.findElement(By.xpath(".//*[@class='widget assessment-widget widget-expanded']//div[@placeholder='Write your comment']")).sendKeys(randomText);
            new Click().clickbyxpath(".//*[@class='widget-social-element-container bg-white']//div[@class='post-comment post-widget-comment']");
            boolean commentPost = false;
            List<WebElement> commentTextList = driver.findElements(By.className("ls-stream-post__comment-text"));
            System.out.println("randomText::"+randomText);
            for(int i=0;i<commentTextList.size();i++)
            {
                String commentText = commentTextList.get(i).getText();
                if(commentText.equals(randomText))
                {
                    System.out.println("commentText::"+commentText);
                    commentPost=true;
                    break;
                }
            }
            if(commentPost=false)
            {
                Assert.fail("Not able to post comment for quiz assessment" );
            }

            // BookMark Quiz Widget
            new Click().clickbyxpath("(.//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-unbookmarked'])[1]");
            boolean bookMark = driver.findElement(By.xpath(".//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-bookmarked']")).isDisplayed();
            if(bookMark==false)
            {
                Assert.fail("Not able to bookmark for quiz assessment");
            }
            // Un BookMark Quiz Widget
            new Click().clickbyxpath(".//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-bookmarked']");
            boolean unbookMark = driver.findElement(By.xpath("(.//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-unbookmarked'])[1]")).isDisplayed();
            if(unbookMark==false)
            {
                Assert.fail("Not able to unbookmark for quiz assessment" );
            }
            new Click().clickBycssselector("div[class='toggle-widget-size toggle-widget-size-collapse']"); // collapse widget
            boolean expandWidget = driver.findElement(By.xpath("(.//*[@widgettype='assessment']//div[@class='toggle-widget-size toggle-widget-size-expand'])[1]")).isDisplayed();
            if(expandWidget==false)
            {
                Assert.fail("Expand widget is not present for quiz assessment" );
            }
            // expand quiz widget
            new Click().clickbyxpath("(.//*[@widgettype='assessment']//div[@class='toggle-widget-size toggle-widget-size-expand'])[1]");
            Thread.sleep(1000);
            boolean expandAssessmentWidget = driver.findElement(By.className("widget-close")).isDisplayed();
            if(expandAssessmentWidget==false)
            {
                Assert.fail("Not able to expand quiz assessment");
            }
            new Click().clickBycssselector("div[class='toggle-widget-size toggle-widget-size-collapse']"); // collapse widget
            // Summary drop down
            new Click().clickbyxpath("(.//div[@class='static-assessment-question-summery'])[1]");
            boolean summaryDropDown = driver.findElement(By.className("question-summary-header")).isDisplayed();
            if(summaryDropDown==false)
            {
                Assert.fail("Not able to Click on summary dropdown" );
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase embeddedAssessmentWithMPQAndNonMPQuestionTypes in class EmbeddedAssessmentInLessonPage", e);
        }
    }

    @Test(priority = 9,enabled = true)
    public void attemptTheQuestionInTheWidgetMPQAndNonMPQ()
    {
        try
        {
            new DirectLogin().directLoginWithCreditial("112"); // Login as student
            new Click().clickByclassname("study-course-link"); // Click on Study
            Thread.sleep(3000);
            // Click on lesson
            List<WebElement>  lessonsList = driver.findElements(By.cssSelector("a[data-type='lesson']"));
            lessonsList.get(3).click();
            // Scroll down to the assessment
            WebElement element = driver.findElement(By.xpath("(//span[text()='P1.3:'])[1]"));
            new ScrollElement().scrollToViewOfElement(element);
            // Attempt a Question
            Thread.sleep(1000);
            new Click().clickbyxpath("(.//*[@class='static-assessment-question-summery'])[1]");
            List<WebElement> questionsInDropDown = driver.findElements(By.className("s--check-enable"));
            questionsInDropDown.get(2).click();
            // Hint in Qiiz Widget
            boolean hint = driver.findElement(By.xpath("(.//*[@class='al-diag-test-hint-drop-down-wrapper'])[1]")).isDisplayed();
            if(hint==false)
            {
                Assert.fail("Hint is not showing for Embedded assessment question" );
            }
            // Confident Level
            boolean confidentLevel = driver.findElement(By.xpath("(.//*[@class='al-self-rating'])[1]")).isDisplayed();
            if(confidentLevel==false)
            {
                Assert.fail("Confidence panel is not isplayed in the question part" );
            }
            // Click on Hint
            new Click().clickbyxpath("(.//*[@class='al-diag-test-hint-drop-down-wrapper'])[1]");
            boolean hintNotify = driver.findElement(By.xpath("(.//*[@id='al-hint-robo-wrapper-sidebar-scroll'])[1]")).isDisplayed();
            if(hintNotify==false)
            {
                Assert.fail("Click on Hint button is not displaying the notification");
            }
            // Select Confident Level
            new Click().clickbyxpath("(.//*[@class='confidence-level-block']//a[@id='3'])[1]");
            String cfLevelSelect = driver.findElement(By.xpath("(.//*[@class='confidence-level-block']//a[@id='3'])[1]")).getAttribute("class");
            if(!cfLevelSelect.contains("selected"))
            {
                Assert.fail("Not able to select confidence level" );
            }
            // Verify question Review Page
            new Click().clickbyxpath("(.//*[@class='ls-embedded-static-submit-button'])[1]");
            boolean questionReviewPage = driver.findElement(By.xpath("(.//*[@class='ls-embedded-static-next-button'])[1]")).isDisplayed();
            if (questionReviewPage == false) {
                Assert.fail("Question review page is not displayed / Next Question option is not displayed" );
            }
            // Solution in Review Page
            boolean SolutionInReviewPage = driver.findElement(By.id("al-solution-button")).isDisplayed();
            if(SolutionInReviewPage==false)
            {
                Assert.fail("Solution is not present in Question review page" );
            }
            // Click on Solution
            new Click().clickByid("al-solution-button");
            boolean solutionContent = driver.findElement(By.xpath("(.//*[@class='diag-test-solution-raw-content'])[1]")).isDisplayed();
            if(solutionContent==false)
            {
                Assert.fail("Solution Content is not displayed in Question review page" );
            }
            // Click on next question
            driver.findElement(By.xpath("(.//*[@class='ls-embedded-static-next-button'])[1]")).click();
            boolean nextQuestionDelivered = driver.findElement(By.xpath("(.//*[@class = 'ls-embedded-static-submit-button'])[1]")).isDisplayed();
            if(nextQuestionDelivered==false)
            {
                Assert.fail("Next question is not delivered" );
            }
            // Hint in Qiiz Widget
            hint = driver.findElement(By.xpath("(.//*[@class='al-diag-test-hint-drop-down-wrapper'])[1]")).isDisplayed();
            if(hint==false)
            {
                Assert.fail("Hint is not showing for Embedded assessment question" );
            }
            // Confident Level
            confidentLevel = driver.findElement(By.xpath("(.//*[@class='al-self-rating'])[1]")).isDisplayed();
            if(confidentLevel==false)
            {
                Assert.fail("Confidence panel is not isplayed in the question part" );
            }
            new Click().clickbyxpath("(.//*[@class='ls-embedded-static-submit-button'])[1]");
            int attemptAllQuestions = driver.findElements(By.className("assessment-widget-retake")).size();
            while (attemptAllQuestions==0)
            {
                new Click().clickbyxpath("(.//*[@class = 'ls-embedded-static-submit-button'])[1]");
                Thread.sleep(1000);
                int nextQuestion = driver.findElements(By.xpath("(.//*[@class = 'ls-embedded-static-next-button'])[1]")).size();
                if(nextQuestion>0)
                {
                    new Click().clickbyxpath("(.//*[@class = 'ls-embedded-static-next-button'])[1]");
                }
                attemptAllQuestions = driver.findElements(By.className("assessment-widget-retake")).size();
            }
            // Retake
            boolean retakeButton = driver.findElement(By.className("ls-embedded-static-retake-button")).isDisplayed();
            if(retakeButton==false)
            {
                Assert.fail("Last question is not submitted" );
            }
            SolutionInReviewPage = driver.findElement(By.xpath("(.//*[@class='part-question-solution'])[2]")).isDisplayed();
            if(SolutionInReviewPage==false)
            {
                Assert.fail("Solution is not present in Question review page after last question submit" );
            }
            // Click on Solution
            new Click().clickbyxpath("(.//*[@class='part-question-solution'])[2]");
            solutionContent = driver.findElement(By.xpath("(.//*[@class='multi-part-question-solution-content'])[2]")).isDisplayed();
            if(solutionContent==false)
            {
                Assert.fail("Solution Content is not displayed in Question review page" );
            }
            if(retakeButton==false)
            {
                Assert.fail("Retake button is not present" );
            }
            Thread.sleep(1000);
            new Click().clickByclassname("assessment-widget-retake");
            Thread.sleep(1000);
            String firstQuestion = driver.findElement(By.xpath("(./*//*[@id='display-label'])[1]")).getText();
            System.out.println("firstQuestion::"+firstQuestion);
            if(!firstQuestion.trim().contains("Q 1.1:"))
            {
                Assert.fail("Click on Retake button is not navigated to first Question" );
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase attemptTheQuestionInTheWidgetMPQAndNonMPQ in class EmbeddedAssessmentInLessonPage", e);
        }
    }

    @Test(priority = 10,enabled = true)
    public void attemptMPQAsTheLastQuestionWithWoreThanOnePart()
    {
        try
        {
            new DirectLogin().directLoginWithCreditial("136"); // Login as student
            new Click().clickByclassname("study-course-link"); // Click on Study
            Thread.sleep(3000);
            // Click on lesson
            List<WebElement>  lessonsList = driver.findElements(By.cssSelector("a[data-type='lesson']"));
            lessonsList.get(3).click();
            // Scroll down to the assessment
            WebElement element = driver.findElement(By.xpath("(//span[text()='P1.3:'])[1]"));
            new ScrollElement().scrollToViewOfElement(element);
            new Click().clickbyxpath("(.//*[@class='static-assessment-question-summery'])[1]");
            element = driver.findElement(By.xpath("(.//*[@class='s--check-enable'])[8]"));
            new ScrollElement().scrollToViewOfElement(element);
            Thread.sleep(1000);
            driver.findElement(By.xpath("(.//*[@class='s--check-enable'])[8]")).click();
            List<WebElement> arrowOfSecondQuestionPart = driver.findElements(By.xpath(".//div[starts-with(@class,'question-toggle-arrow-icon')]"));
            Thread.sleep(2000);
            arrowOfSecondQuestionPart.get(1).click();
            Thread.sleep(1000);
            // Hint
            boolean hint = driver.findElement(By.xpath("(.//div[@class='part-question-hint'])[2]")).isDisplayed();
            if(hint==false)
            {
                Assert.fail("Hint is not displayed in the question part" );
            }
            // Confidence Level
            boolean confidenceLevel = driver.findElement(By.xpath("(.//div[@class='al-self-rating-message'])[2]")).isDisplayed();
            if(confidenceLevel==false)
            {
                Assert.fail("ConfidenceLevel is not displayed in the question part" );
            }
            // Click on Hint
            new Click().clickbyxpath("(.//div[@class='part-question-hint'])[2]");
            boolean hintNotify = driver.findElement(By.xpath("(.//*[@class='multi-part-question-hint-content'])[2]")).isDisplayed();
            if(hintNotify==false)
            {
                Assert.fail("Click on Hint button is not displaying the notification" );
            }
            // Select Confident Level
            new Click().clickbyxpath("(.//*[@class='confidence-level-block']//a[@id='3'])[2]");
            String cfLevelSelect = driver.findElement(By.xpath("(.//*[@class='confidence-level-block']//a[@id='3'])[2]")).getAttribute("class");
            if(!cfLevelSelect.contains("selected"))
            {
                Assert.fail("Not able to select confidence level" );
            }
            // Verify question Review Page
            new Click().clickbyxpath("(.//*[@class='next-question-part'])[2]");
            boolean questionReviewPage = driver.findElement(By.className("last-question-part")).isDisplayed();
            if(questionReviewPage==false)
            {
                Assert.fail("Next question part is not opened" );
            }
            // Submit Button
            boolean submitButton = driver.findElement(By.xpath("(.//*[@class='ls-embedded-static-submit-button'])[1]")).isDisplayed();
            if(submitButton==false)
            {
                Assert.fail("Submit anwer for the entre question is not displayed" );
            }
            // Click Submit button
            driver.findElement(By.xpath("(.//*[@class='ls-embedded-static-submit-button'])[1]")).click();
            Thread.sleep(1000);
            // verify Question review page
            boolean correctAnswerResponse = driver.findElement(By.className("yourresponse-correctanswer")).isDisplayed();
            if(correctAnswerResponse==false)
            {
                Assert.fail("Question review page is not displayed" );
            }
            // Verify question card ecpand mode
            int questionCardExpandMode = driver.findElements(By.xpath(".//*[@class='question-toggle-arrow-icon collapse']")).size();
            if(questionCardExpandMode<3)
            {
                Assert.fail("All question cards are not in expanded format" );
            }
            // Solution in Review page
            Thread.sleep(2000);
            boolean solution = driver.findElement(By.xpath("(.//*[@class='part-question-solution'])[3]")).isDisplayed();
            if(solution==false)
            {
                Assert.fail("Solution option is not displayed in the review page" );
            }
            Thread.sleep(1000);
            // Retake
            boolean retakeButton = driver.findElement(By.className("ls-embedded-static-retake-button")).isDisplayed();
            if(retakeButton==false)
            {
                Assert.fail("Retake option is not displayed instead of finish option" );
            }
            Thread.sleep(1000);
            // Click on Solution
            new Click().clickbyxpath("(.//*[@class='part-question-solution'])[2]");
            boolean solutionContent = driver.findElement(By.xpath("(.//*[@class='multi-part-question-solution-content'])[2]")).isDisplayed();
            if(solutionContent==false)
            {
                Assert.fail("Solution Content is not displayed in Question review page");
            }
            Thread.sleep(1000);
            new Click().clickByclassname("assessment-widget-retake");
            Thread.sleep(1000);
            String firstQuestion = driver.findElement(By.xpath("(.//*[@id='display-label'])[1]")).getText();
            System.out.println("firstQuestion::"+firstQuestion);
            if(!firstQuestion.trim().contains("Q 1.1:"))
            {
                Assert.fail("Click on Retake button is not navigated to first Question" );
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase attemptMPQAsTheLastQuestionWithWoreThanOnePart in class EmbeddedAssessmentInLessonPage", e);
        }
    }

    @Test(priority = 11,enabled = true)
    public void lessonPageHavingOneEmbeddedAssessment()
    {
        try
        {
            new DirectLogin().directLoginWithCreditial("157"); // direct login as instructor
            new Navigator().NavigateTo("eTextBook"); // navigate e-Textbook
            List<WebElement>  lessonsList = driver.findElements(By.cssSelector("a[data-type='lesson']"));
            lessonsList.get(1).click();
            // Verify lesson page opened
            boolean lessonPage = driver.findElement(By.cssSelector("div[class='lesson ebook']")).isDisplayed();
            if(lessonPage==false)
            {
                Assert.fail("Chapter Section/Lesson is not opened" );
            }
            // Verify Quiz widget present
            boolean assessmentInLessonPage = driver.findElement(By.className("ls-static-assessment-wrapper")).isDisplayed();
            if(assessmentInLessonPage==false)
            {
                Assert.fail("Assessment is not present in the lesson Page" );
            }
            // Scroll down to assessment in lesson page
            WebElement element = driver.findElement(By.className("ls-static-assessment-wrapper"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
            Thread.sleep(1000);
            new ScrollElement().scrollToViewOfElement(element);
            // Verify Like option present for Quiz widget
            boolean likeAssessment = driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon']")).isDisplayed();
            if(likeAssessment==false)
            {
                Assert.fail("Like option is not present in the assessment" );
            }
            // Verify Comment option present for Quiz widget
            boolean commentAssessment = driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--comments-icon']")).isDisplayed();
            if(commentAssessment==false)
            {
                Assert.fail("Comment option is not present in the assessment" );
            }

            // Verify Assign option present for Quiz widget
            boolean assignAssessment = driver.findElement(By.xpath(".//*[@widgettype='assessment']/span[@class='assign-options']")).isDisplayed();
            if(assignAssessment==false)
            {
                Assert.fail("Assign option is not present in the assessment" );
            }
            // Like assessment
            driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon']")).click();
            String likeAssessmentCount = driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/span[@class='ls-right-post-like-count']")).getText();
            if(likeAssessmentCount=="0")
            {
                Assert.fail("Instructor is not able to like in the assessment" );
            }
            // unlike assessment
            driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon']")).click();
            String unlikeAssessmentCount = driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/span[@class='ls-right-post-like-count']")).getText();
            if(unlikeAssessmentCount==likeAssessmentCount)
            {
                Assert.fail("Instructor is not able to unlike in the assessment" );
            }
            // Post a comment
            String randomText = new RandomString().randomstring(10);
            driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--comments-icon']")).click();
            Thread.sleep(2000);
            WebElement elementScroll = driver.findElement(By.xpath(".//*[@class='widget-social-element-container bg-white']//div[@class='post-comment post-widget-comment']"));
            new ScrollElement().scrollToViewOfElement(elementScroll);
            Thread.sleep(2000);
            driver.findElement(By.xpath(".//*[@class='widget-social-element-container bg-white']//div[@placeholder='Write your comment']")).click();
            driver.findElement(By.xpath(".//*[@class='widget-social-element-container bg-white']//div[@placeholder='Write your comment']")).sendKeys(randomText);
            new Click().clickbyxpath(".//*[@class='widget-social-element-container bg-white']//div[@class='post-comment post-widget-comment']");
            boolean commentPost = false;
            List<WebElement> commentTextList = driver.findElements(By.className("ls-stream-post__comment-text"));
            System.out.println("randomText::"+randomText);
            for(int i=0;i<commentTextList.size();i++)
            {
                String commentText = commentTextList.get(i).getText();
                if(commentText.equals(randomText))
                {
                    System.out.println("commentText::"+commentText);
                    commentPost=true;
                    break;
                }
            }
            if(commentPost=false)
            {
                Assert.fail("Not able to post comment for quiz assessment" );
            }
            //Assign assessment
            driver.findElement(By.xpath(".//*[@widgettype='assessment']/span[@class='assign-options']")).click();
            driver.findElement(By.xpath(".//*[@class='widget assessment-widget']//span[@class='assign-this-text']")).click();
            new AssignLesson().assignTOCToSpecificStudent(157);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase lessonPageHavingOneEmbeddedAssessment in class EmbeddedAssessmentInLessonPage", e);
        }
    }

    @Test(priority = 12,enabled = true)
    public void lessonPageHavingOneEmbeddedAssessmentStudent()
    {
        try
        {
            new DirectLogin().directLoginWithCreditial("165"); // direct login as Student
            new Navigator().NavigateTo("eTextBook"); // navigate e-Textbook
            List<WebElement>  lessonsList = driver.findElements(By.cssSelector("a[data-type='lesson']"));
            lessonsList.get(1).click();
            WebElement element = driver.findElement(By.className("ls-static-assessment-wrapper"));
            new ScrollElement().scrollToViewOfElement(element);
            // Verify Like option present for Quiz widget
            boolean likeAssessment = driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon']")).isDisplayed();
            if(likeAssessment==false)
            {
                Assert.fail("Like option is not present in the assessment" );
            }
            // Verify Comment option present for Quiz widget
            boolean commentAssessment = driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--comments-icon']")).isDisplayed();
            if(commentAssessment==false)
            {
                Assert.fail("Comment option is not present in the assessment" );
            }
            // Like assessment
            driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon']")).click();
            String likeAssessmentCount = driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/span[@class='ls-right-post-like-count']")).getText();
            if(likeAssessmentCount=="0")
            {
                Assert.fail("Instructor is not able to like in the assessment" );
            }
            // unlike assessment
            driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon']")).click();
            String unlikeAssessmentCount = driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/span[@class='ls-right-post-like-count']")).getText();
            if(unlikeAssessmentCount==likeAssessmentCount)
            {
                Assert.fail("Instructor is not able to unlike in the assessment" );
            }
            // Post a comment
            String randomText = new RandomString().randomstring(10);
            driver.findElement(By.xpath(".//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--comments-icon']")).click();
            Thread.sleep(2000);
            WebElement elementScroll = driver.findElement(By.xpath("./*//*[@class='widget-social-element-container bg-white']//div[@class='post-comment post-widget-comment']"));
            new ScrollElement().scrollToViewOfElement(elementScroll);
            driver.findElement(By.xpath(".//*[@class='widget assessment-widget widget-expanded']//div[@placeholder='Write your comment']")).click();
            driver.findElement(By.xpath(".//*[@class='widget assessment-widget widget-expanded']//div[@placeholder='Write your comment']")).sendKeys(randomText);
            new Click().clickbyxpath(".//*[@class='widget-social-element-container bg-white']//div[@class='post-comment post-widget-comment']");
            boolean commentPost = false;
            List<WebElement> commentTextList = driver.findElements(By.className("ls-stream-post__comment-text"));
            System.out.println("randomText::"+randomText);
            for(int i=0;i<commentTextList.size();i++)
            {
                String commentText = commentTextList.get(i).getText();
                if(commentText.equals(randomText))
                {
                    System.out.println("commentText::"+commentText);
                    commentPost=true;
                    break;
                }
            }
            if(commentPost=false)
            {
                Assert.fail("Not able to post comment for quiz assessment" );
            }
            // BookMark Quiz Widget
            new Click().clickbyxpath(".//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-unbookmarked']");
            boolean bookMark = driver.findElement(By.xpath(".//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-bookmarked']")).isDisplayed();
            if(bookMark==false)
            {
                Assert.fail("Not able to bookmark for quiz assessment" );
            }
            // Un BookMark Quiz Widget
            new Click().clickbyxpath(".//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-bookmarked']");
            boolean unbookMark = driver.findElement(By.xpath(".//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-unbookmarked']")).isDisplayed();
            if(unbookMark==false)
            {
                Assert.fail("Not able to unbookmark for quiz assessment" );
            }
            // Expand option present in assessment
            new Click().clickBycssselector("div[class='toggle-widget-size toggle-widget-size-collapse']"); // collapse widget
            boolean expandWidget = driver.findElement(By.xpath(".//*[@widgettype='assessment']//div[@class='toggle-widget-size toggle-widget-size-expand']")).isDisplayed();
            if(expandWidget==false)
            {
                Assert.fail("Expand widget is not present for quiz assessment" );
            }
            // expand quiz widget
            new Click().clickbyxpath(".//*[@widgettype='assessment']//div[@class='toggle-widget-size toggle-widget-size-expand']");
            Thread.sleep(1000);
            boolean expandAssessmentWidget = driver.findElement(By.className("widget-close")).isDisplayed();
            if(expandAssessmentWidget==false)
            {
                Assert.fail("Not able to expand quiz assessment" );
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase lessonPageHavingOneEmbeddedAssessmentStudent in class EmbeddedAssessmentInLessonPage", e);
        }
    }

    @Test(priority = 13,enabled = true)
    public void attemptEmbeddedAssessmentAsStudentInlessonPage()
    {
        try
        {
            new DirectLogin().directLoginWithCreditial("176"); // Login as student
            new Click().clickByclassname("study-course-link"); // Click on Study
            Thread.sleep(3000);
            // Click on lesson
            List<WebElement>  lessonsList = driver.findElements(By.cssSelector("a[data-type='lesson']"));
            lessonsList.get(1).click();
            boolean lessonPage = driver.findElement(By.cssSelector("div[class='lesson ebook']")).isDisplayed();
            if(lessonPage==false)
            {
                Assert.fail("Lesson is not opened" );
            }
            // Scroll down to the assessment
            WebElement element = driver.findElement(By.className("ls-static-assessment-wrapper"));
            new ScrollElement().scrollToViewOfElement(element);
            Thread.sleep(1000);
            new Click().clickByclassname("question-toggle-arrow-icon");
            List<WebElement> trueFlase = driver.findElements(By.className("true-false-student-answer-label"));
            trueFlase.get(0).click();
            Thread.sleep(1000);
           /* int lastQuestion = driver.findElements(By.className("last-question-part")).size();
            System.out.println("lastQuestionSize::"+lastQuestion);
            while(lastQuestion==0)
            {
                new Click().clickByclassname("next-question-part");
                lastQuestion = driver.findElements(By.className("last-question-part")).size();
                System.out.println("lastQuestionSizeInLoop::"+lastQuestion);
            }*/
            for(int i=0;i<3;i++)
            {
                //new Click().clickByclassname("next-question-part");
                List<WebElement> nextQuestionPart = driver.findElements(By.className("next-question-part"));
                nextQuestionPart.get(i).click();
            }
            // Hint
            boolean hint = driver.findElement(By.xpath("(.//*[@class='part-question-hint'])[4]")).isDisplayed();
            if(hint==false)
            {
                Assert.fail("Hint is not displayed in the question page" );
            }
            // Confidence Level
            boolean confidence = driver.findElement(By.xpath("(.//*[@class='al-self-rating-message'])[4]")).isDisplayed();
            if(confidence==false)
            {
                Assert.fail("Confidence Level is not displayed in the question page" );
            }
            new Click().clickbyxpath("(.//*[@class='part-question-hint'])[4]");
            boolean hintNotify = driver.findElement(By.xpath("(.//*[@class='multi-part-question-hint-content'])[4]")).isDisplayed();
            if(hintNotify==false)
            {
                Assert.fail("Click on Hint button is not displaying the notification" );
            }
            new Click().clickbyxpath("(.//*[@class='confidence-level-block']//a[@id='3'])[4]");
            String cfLevelSelect = driver.findElement(By.xpath(".//a[starts-with(@class,'confidence-level-almost confidence')]")).getAttribute("class");
            if(!cfLevelSelect.contains("selected"))
            {
                Assert.fail("Not able to select confidence level" );
            }
            Thread.sleep(1000);
            new Click().clickByclassname("last-question-part");
            // verify Question review page
            boolean correctAnswerResponse = driver.findElement(By.className("your-answer-wrapper")).isDisplayed();
            if(correctAnswerResponse==false)
            {
                Assert.fail("Question review page is not displayed" );
            }
            // Solution in Review page
            Thread.sleep(2000);
            boolean solution = driver.findElement(By.xpath("(.//*[@class='part-question-solution'])[4]")).isDisplayed();
            if(solution==false)
            {
                Assert.fail("Solution option is not displayed in the review page" );
            }
            // Next question
            boolean nextQuestion = driver.findElement(By.className("ls-embedded-static-next-button")).isDisplayed();
            if(nextQuestion==false)
            {
                Assert.fail("Next question option is not displayed instead of finish option" );
            }
            Thread.sleep(1000);
            // Click on Solution
            new Click().clickbyxpath("(.//*[@class='part-question-solution'])[4]");
            boolean solutionClick = driver.findElement(By.cssSelector("div[class='part-question-solution highlight-btn']")).isDisplayed();
            if(solutionClick==false)
            {
                Assert.fail("Not able to Click on Solution" );
            }
            boolean solutionContent = driver.findElement(By.xpath("(.//div[@class='multi-part-question-solution-content'])[4]")).isDisplayed();
            if(solutionContent==false)
            {
                Assert.fail("Solution Content is not dispalying" );
            }
            Thread.sleep(1000);
            // Click Next question
            driver.findElement(By.className("ls-embedded-static-next-button")).click();
            Thread.sleep(1000);
            boolean goToNextQuestionPart = driver.findElement(By.xpath("(.//*[@class='next-question-part'])[1]")).isDisplayed();
            if(goToNextQuestionPart==false)
            {
                Assert.fail("Next question is not delivered" );
            }
            Thread.sleep(1000);
           /* // Hint
            boolean hint = driver.findElement(By.xpath("(./*//*[@class='part-question-hint'])[4]")).isDisplayed();
            if(hint==false)
            {
                Assert.fail("Hint is not displayed in the question page" );
            }*/
            // Confidence Level
            confidence = driver.findElement(By.xpath("(.//*[@class='al-self-rating-message'])[1]")).isDisplayed();
            if(confidence==false)
            {
                Assert.fail("Confidence Level is not displayed in the question page" );
            }
            new Click().clickByclassname("ls-embedded-static-submit-button");
            // verify Question review page
            correctAnswerResponse = driver.findElement(By.className("your-answer-wrapper")).isDisplayed();
            if(correctAnswerResponse==false)
            {
                Assert.fail("Question review page is not displayed" );
            }
            // Solution in Review page
            Thread.sleep(2000);
           /* solution = driver.findElement(By.xpath("(./*//*[@class='part-question-solution'])[4]")).isDisplayed();
            if(solution==false)
            {
                Assert.fail("Solution option is not displayed in the review page" );
            }*/
            // Retake click
            new Click().clickByclassname("assessment-widget-retake");
            Thread.sleep(1000);
            String firstQuestion = driver.findElement(By.xpath("(.//*[@id='display-label'])[1]")).getText();
            System.out.println("firstQuestion::"+firstQuestion);
            if(!firstQuestion.trim().contains("Q 1.1:"))
            {
                Assert.fail("Click on Retake button is not navigated to first Question" );
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase attempEmbeddedAssessmentAsStudentInlessonPage in class EmbeddedAssessmentInLessonPage", e);
        }
    }

    @Test(priority = 14,enabled = true)
    public void lessonPageHavingMultipleEmbeddedAssessment()
    {
        try
        {
            new DirectLogin().directLoginWithCreditial("201"); // direct login as instructor
            new Navigator().NavigateTo("eTextBook"); // navigate e-Textbook
            List<WebElement>  lessonsList = driver.findElements(By.cssSelector("a[data-type='lesson']"));
            lessonsList.get(3).click();
            // Verify lesson page opened
            boolean lessonPage = driver.findElement(By.cssSelector("div[class='lesson ebook']")).isDisplayed();
            if(lessonPage==false)
            {
                Assert.fail("Chapter Section/Lesson is not opened" );
            }
            // Verify Quiz widget present
            boolean assessmentInLessonPage = driver.findElement(By.className("ls-static-assessment-wrapper")).isDisplayed();
            if(assessmentInLessonPage==false)
            {
                Assert.fail("Assessment is not present in the lesson Page" );
            }
            // multiple quiz widgets
            int multipleQuizWidgets = driver.findElements(By.id("ls-assessment-wrapper")).size();
            System.out.println("multipleQuizWidgetsSize::"+multipleQuizWidgets);
            if(multipleQuizWidgets<2)
            {
                Assert.fail("All the Embedded assessments are not displayed in the lesson pagee" );
            }
            // First Widget

            // Scroll down to the assessment
            WebElement element = driver.findElement(By.xpath("(//span[text()='P1.3:'])[1]"));
            new ScrollElement().scrollToViewOfElement(element);
            // Verify Like option present for Quiz widget
            boolean likeAssessment = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon'])[1]")).isDisplayed();
            if(likeAssessment==false)
            {
                Assert.fail("Like option is not present in the assessment" );
            }
            // Like assessment
            driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon'])[1]")).click();
            String likeAssessmentCount = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/span[@class='ls-right-post-like-count'])[1]")).getText();
            if(likeAssessmentCount=="0")
            {
                Assert.fail("Student is not able to like in the assessment" );
            }
            // unlike assessment
            driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon'])[1]")).click();
            String unlikeAssessmentCount = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/span[@class='ls-right-post-like-count'])[1]")).getText();
            if(unlikeAssessmentCount==likeAssessmentCount)
            {
                Assert.fail("Student is not able to unlike in the assessment" );
            }
            String randomText = new RandomString().randomstring(10);
            // Verify Comment option present for Quiz widget
            boolean commentAssessment = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--comments-icon'])[1]")).isDisplayed();
            if(commentAssessment==false)
            {
                Assert.fail("Comment option is not present in the assessment" );
            }
            // Post a comment
            driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--comments-icon'])[1]")).click();
            Thread.sleep(2000);
            WebElement elementScroll = driver.findElement(By.xpath(".//*[@class='widget-social-element-container bg-white']//div[@class='post-comment post-widget-comment']"));
            new ScrollElement().scrollToViewOfElement(elementScroll);
            driver.findElement(By.xpath(".//*[@class='widget-social-element-container bg-white']//div[@placeholder='Write your comment']")).click();
            driver.findElement(By.xpath(".//*[@class='widget-social-element-container bg-white']//div[@placeholder='Write your comment']")).sendKeys(randomText);
            new Click().clickbyxpath(".//*[@class='widget-social-element-container bg-white']//div[@class='post-comment post-widget-comment']");
            boolean commentPost = false;
            List<WebElement> commentTextList = driver.findElements(By.className("ls-stream-post__comment-text"));
            System.out.println("randomText::"+randomText);
            for(int i=0;i<commentTextList.size();i++)
            {
                String commentText = commentTextList.get(i).getText();
                if(commentText.equals(randomText))
                {
                    System.out.println("commentText::"+commentText);
                    commentPost=true;
                    break;
                }
            }
            if(commentPost=false)
            {
                Assert.fail("Not able to post comment for quiz assessment" );
            }
            //Assign assessment
            driver.findElement(By.xpath(".//*[@widgettype='assessment']//span[@class='assign-options']")).click();
            driver.findElement(By.xpath(".//*[@class='widget assessment-widget']//span[@class='assign-this-text']")).click();
            new AssignLesson().assignTOCToSpecificStudent(201);

            Thread.sleep(3000);
            // Second Widget

            // Scroll down to the assessment
            element = driver.findElement(By.xpath("(//span[text()='P1.3:'])[2]"));
            new ScrollElement().scrollToViewOfElement(element);
            // Verify Like option present for Quiz widget
            likeAssessment = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon'])[2]")).isDisplayed();
            if(likeAssessment==false)
            {
                Assert.fail("Like option is not present in the assessment" );
            }
            // Like assessment
            driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon'])[2]")).click();
            likeAssessmentCount = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/span[@class='ls-right-post-like-count'])[2]")).getText();
            if(likeAssessmentCount=="0")
            {
                Assert.fail("Student is not able to like in the assessment" );
            }
            // unlike assessment
            driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon'])[2]")).click();
            unlikeAssessmentCount = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/span[@class='ls-right-post-like-count'])[2]")).getText();
            if(unlikeAssessmentCount==likeAssessmentCount)
            {
                Assert.fail("Student is not able to unlike in the assessment" );
            }
            randomText = new RandomString().randomstring(10);
            // Verify Comment option present for Quiz widget
            commentAssessment = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--comments-icon'])[2]")).isDisplayed();
            if(commentAssessment==false)
            {
                Assert.fail("Comment option is not present in the assessment" );
            }
            // Post a comment
            driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--comments-icon'])[2]")).click();
            Thread.sleep(2000);
            elementScroll = driver.findElement(By.xpath(".//*[@class='widget-social-element-container bg-white']//div[@class='post-comment post-widget-comment']"));
            new ScrollElement().scrollToViewOfElement(elementScroll);
            driver.findElement(By.xpath(".//*[@class='widget-social-element-container bg-white']//div[@placeholder='Write your comment']")).click();
            driver.findElement(By.xpath(".//*[@class='widget-social-element-container bg-white']//div[@placeholder='Write your comment']")).sendKeys(randomText);
            new Click().clickbyxpath(".//*[@class='widget-social-element-container bg-white']//div[@class='post-comment post-widget-comment']");
            commentPost = false;
            commentTextList = driver.findElements(By.className("ls-stream-post__comment-text"));
            System.out.println("randomText::"+randomText);
            for(int i=0;i<commentTextList.size();i++)
            {
                String commentText = commentTextList.get(i).getText();
                if(commentText.equals(randomText))
                {
                    System.out.println("commentText::"+commentText);
                    commentPost=true;
                    break;
                }
            }
            if(commentPost=false)
            {
                Assert.fail("Not able to post comment for quiz assessment" );
            }
            //Assign assessment
            driver.findElement(By.xpath(".//*[@widgettype='assessment']//span[@class='assign-options']")).click();
            driver.findElement(By.xpath(".//*[@class='widget assessment-widget']//span[@class='assign-this-text']")).click();
            new AssignLesson().assignTOCToSpecificStudent(201);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase lessonPageHavingMultipleEmbeddedAssessment in class EmbeddedAssessmentInLessonPage", e);
        }
    }

    @Test(priority = 15,enabled = true)
    public void lessonPageHavingMultipleEmbeddedAssessmentStudent()
    {
        try
        {
            new DirectLogin().directLoginWithCreditial("204"); // direct login as Student
            new Navigator().NavigateTo("eTextBook"); // navigate e-Textbook
            List<WebElement> lessonsList = driver.findElements(By.cssSelector("a[data-type='lesson']"));
            lessonsList.get(3).click();
            Thread.sleep(1000);
            // multiple quiz widgets
            int multipleQuizWidgets = driver.findElements(By.id("ls-assessment-wrapper")).size();
            System.out.println("multipleQuizWidgetsSize::"+multipleQuizWidgets);
            if(multipleQuizWidgets<2)
            {
                Assert.fail("All the Embedded assessments are not displayed in the lesson pagee" );
            }
            boolean quizWidgetOne = driver.findElement(By.xpath("(//span[text()='P1.3:'])[1]")).isDisplayed();
            if(quizWidgetOne==false)
            {
                Assert.fail("First quiz widget is not displayed in the lesson pagee" );
            }
            boolean quizWidgetSecond = driver.findElement(By.xpath("(//span[text()='P1.3:'])[2]")).isDisplayed();
            if(quizWidgetSecond==false)
            {
                Assert.fail("Second quiz widget is not displayed in the lesson pagee" );
            }
            //Quiz Widget 1

            // Scroll down to the assessment
            WebElement element = driver.findElement(By.xpath("(//span[text()='P1.3:'])[1]"));
            new ScrollElement().scrollToViewOfElement(element);
            // Verify Like option present for Quiz widget
            boolean likeAssessment = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon'])[1]")).isDisplayed();
            if(likeAssessment==false)
            {
                Assert.fail("Like option is not present in the assessment" );
            }
            // Like assessment
            driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon'])[1]")).click();
            String likeAssessmentCount = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/span[@class='ls-right-post-like-count'])[1]")).getText();
            if(likeAssessmentCount=="0")
            {
                Assert.fail("Student is not able to like in the assessment" );
            }
            // unlike assessment
            driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon'])[1]")).click();
            String unlikeAssessmentCount = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/span[@class='ls-right-post-like-count'])[1]")).getText();
            if(unlikeAssessmentCount==likeAssessmentCount)
            {
                Assert.fail("Student is not able to unlike in the assessment" );
            }
            String randomText = new RandomString().randomstring(10);
            // Verify Comment option present for Quiz widget
            boolean commentAssessment = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--comments-icon'])[1]")).isDisplayed();
            if(commentAssessment==false)
            {
                Assert.fail("Comment option is not present in the assessment" );
            }
            // Post a comment
            driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--comments-icon'])[1]")).click();
            Thread.sleep(2000);
            WebElement elementScroll = driver.findElement(By.xpath(".//*[@class='widget-social-element-container bg-white']//div[@class='post-comment post-widget-comment']"));
            new ScrollElement().scrollToViewOfElement(elementScroll);
            driver.findElement(By.xpath(".//*[@class='widget assessment-widget widget-expanded']//div[@placeholder='Write your comment']")).click();
            driver.findElement(By.xpath(".//*[@class='widget assessment-widget widget-expanded']//div[@placeholder='Write your comment']")).sendKeys(randomText);
            new Click().clickbyxpath(".//*[@class='widget-social-element-container bg-white']//div[@class='post-comment post-widget-comment']");
            boolean commentPost = false;
            List<WebElement> commentTextList = driver.findElements(By.className("ls-stream-post__comment-text"));
            System.out.println("randomText::"+randomText);
            for(int i=0;i<commentTextList.size();i++)
            {
                String commentText = commentTextList.get(i).getText();
                if(commentText.equals(randomText))
                {
                    System.out.println("commentText::"+commentText);
                    commentPost=true;
                    break;
                }
            }
            if(commentPost=false)
            {
                Assert.fail("Not able to post comment for quiz assessment" );
            }

            // BookMark Quiz Widget
            new Click().clickbyxpath("(.//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-unbookmarked'])[1]");
            boolean bookMark = driver.findElement(By.xpath(".//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-bookmarked']")).isDisplayed();
            if(bookMark==false)
            {
                Assert.fail("Not able to bookmark for quiz assessment" );
            }
            // Un BookMark Quiz Widget
            new Click().clickbyxpath(".//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-bookmarked']");
            boolean unbookMark = driver.findElement(By.xpath("(.//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-unbookmarked'])[1]")).isDisplayed();
            if(unbookMark==false)
            {
                Assert.fail("Not able to unbookmark for quiz assessment" );
            }
            new Click().clickBycssselector("div[class='toggle-widget-size toggle-widget-size-collapse']"); // collapse widget
            boolean expandWidget = driver.findElement(By.xpath("(.//*[@widgettype='assessment']//div[@class='toggle-widget-size toggle-widget-size-expand'])[1]")).isDisplayed();
            if(expandWidget==false)
            {
                Assert.fail("Expand widget is not present for quiz assessment" );
            }
            // expand quiz widget
            new Click().clickbyxpath("(.//*[@widgettype='assessment']//div[@class='toggle-widget-size toggle-widget-size-expand'])[1]");
            Thread.sleep(1000);
            boolean expandAssessmentWidget = driver.findElement(By.className("widget-close")).isDisplayed();
            if(expandAssessmentWidget==false)
            {
                Assert.fail("Not able to expand quiz assessment" );
            }
            new Click().clickBycssselector("div[class='toggle-widget-size toggle-widget-size-collapse']"); // collapse widget
            Thread.sleep(1000);
           /* // class = assessment-widget-retake
            int retakeQuiz = driver.findElements(By.className("ls-embedded-static-retake-button")).size();
            System.out.println("retakeQuiz::" + retakeQuiz);
            while (retakeQuiz==0)
            {
                new Click().clickbyxpath("(./*//*[@title='Submit Answer'])[1]");
                Thread.sleep(1000);
                int nextButton = driver.findElements(By.xpath("(./*//*[@title='Next Question'])[1]")).size();
                if(nextButton>0)
                {
                    new Click().clickbyxpath("(./*//*[@title='Next Question'])[1]"); // Next Question
                }
                retakeQuiz = driver.findElements(By.className("ls-embedded-static-retake-button")).size();
            }
            boolean retakeButton = driver.findElement(By.className("ls-embedded-static-retake-button")).isDisplayed();
            if(retakeButton==false)
            {
                Assert.fail("Retake button is not present" );
            }*/

            // QUiz widget 2
            // Scroll down to the assessment
            element = driver.findElement(By.xpath("(.//span[text()='P1.3:'])[1]"));
            new ScrollElement().scrollToViewOfElement(element);
            // Verify Like option present for Quiz widget
            likeAssessment = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon'])[2]")).isDisplayed();
            if(likeAssessment==false)
            {
                Assert.fail("Like option is not present in the assessment" );
            }
            // Like assessment
            driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon'])[2]")).click();
            likeAssessmentCount = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/span[@class='ls-right-post-like-count'])[2]")).getText();
            if(likeAssessmentCount=="0")
            {
                Assert.fail("Student is not able to like in the assessment" );
            }
            // unlike assessment
            driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--like-icon'])[2]")).click();
            unlikeAssessmentCount = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/span[@class='ls-right-post-like-count'])[2]")).getText();
            if(unlikeAssessmentCount==likeAssessmentCount)
            {
                Assert.fail("Student is not able to unlike in the assessment" );
            }
            randomText = new RandomString().randomstring(10);
            // Verify Comment option present for Quiz widget
            commentAssessment = driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--comments-icon'])[2]")).isDisplayed();
            if(commentAssessment==false)
            {
                Assert.fail("Comment option is not present in the assessment" );
            }
            // Post a comment
            driver.findElement(By.xpath("(.//*[@widgettype='assessment']/li/a/i[@class='ls-icon-img ls--comments-icon'])[2]")).click();
            Thread.sleep(2000);
            elementScroll = driver.findElement(By.xpath(".//*[@class='widget-social-element-container bg-white']//div[@class='post-comment post-widget-comment']"));
            new ScrollElement().scrollToViewOfElement(elementScroll);
            driver.findElement(By.xpath(".//*[@class='widget assessment-widget widget-expanded']//div[@placeholder='Write your comment']")).click();
            driver.findElement(By.xpath(".//*[@class='widget assessment-widget widget-expanded']//div[@placeholder='Write your comment']")).sendKeys(randomText);
            new Click().clickbyxpath(".//*[@class='widget-social-element-container bg-white']//div[@class='post-comment post-widget-comment']");
            commentPost = false;
            commentTextList = driver.findElements(By.className("ls-stream-post__comment-text"));
            System.out.println("randomText::"+randomText);
            for(int i=0;i<commentTextList.size();i++)
            {
                String commentText = commentTextList.get(i).getText();
                if(commentText.equals(randomText))
                {
                    System.out.println("commentText::"+commentText);
                    commentPost=true;
                    break;
                }
            }
            if(commentPost=false)
            {
                Assert.fail("Not able to post comment for quiz assessment" );
            }

            // BookMark Quiz Widget
            new Click().clickbyxpath("(.//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-unbookmarked'])[2]");
            bookMark = driver.findElement(By.xpath(".//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-bookmarked']")).isDisplayed();
            if(bookMark==false)
            {
                Assert.fail("Not able to bookmark for quiz assessment" );
            }
            // Un BookMark Quiz Widget
            new Click().clickbyxpath(".//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-bookmarked']");
            unbookMark = driver.findElement(By.xpath("(.//*[@widgettype='assessment']//a[@class='ls-bookmark-widget ls-widget-unbookmarked'])[2]")).isDisplayed();
            if(unbookMark==false)
            {
                Assert.fail("Not able to unbookmark for quiz assessment" );
            }
            new Click().clickBycssselector("div[class='toggle-widget-size toggle-widget-size-collapse']"); // collapse widget
            expandWidget = driver.findElement(By.xpath("(.//*[@widgettype='assessment']//div[@class='toggle-widget-size toggle-widget-size-expand'])[2]")).isDisplayed();
            if(expandWidget==false)
            {
                Assert.fail("Expand widget is not present for quiz assessment" );
            }
            // expand quiz widget
            new Click().clickbyxpath("(.//*[@widgettype='assessment']//div[@class='toggle-widget-size toggle-widget-size-expand'])[2]");
            Thread.sleep(1000);
            expandAssessmentWidget = driver.findElement(By.className("widget-close")).isDisplayed();
            if(expandAssessmentWidget==false)
            {
                Assert.fail("Not able to expand quiz assessment" );
            }
            new Click().clickBycssselector("div[class='toggle-widget-size toggle-widget-size-collapse']"); // collapse widget
            // class = assessment-widget-retake
            int retakeQuiz = driver.findElements(By.className("ls-embedded-static-retake-button")).size();
            System.out.println("retakeQuiz::" + retakeQuiz);
            while (retakeQuiz==0)
            {
                new Click().clickbyxpath("(.//*[@title='Submit Answer'])[2]");
                Thread.sleep(1000);
                int nextButton = driver.findElements(By.xpath("(.//*[@title='Next Question'])[2]")).size();
                if(nextButton>0)
                {
                    new Click().clickbyxpath("(.//*[@title='Next Question'])[2]"); // Next Question
                }
                retakeQuiz = driver.findElements(By.className("ls-embedded-static-retake-button")).size();
            }
            boolean retakeButton = driver.findElement(By.className("ls-embedded-static-retake-button")).isDisplayed();
            if(retakeButton==false)
            {
                Assert.fail("Student is not able to attempt the assessment" );
            }
            element = driver.findElement(By.className("fig-caption"));
            new ScrollElement().scrollToViewOfElement(element);
            /*element = driver.findElement(By.xpath("(//span[text()='P1.3:'])[1]"));
            new ScrollElement().scrollToViewOfElement(element);*/
            Thread.sleep(1000);
            String firstQuestion = driver.findElement(By.xpath(".//div[@class='question-display-label']")).getText();
            System.out.println("firstQuestion::"+firstQuestion);
            if(!firstQuestion.trim().contains("Q 1.1:"))
            {
                Assert.fail("First assessment is not present in its status" );
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase lessonPageHavingMultipleEmbeddedAssessmentStudent in class EmbeddedAssessmentInLessonPage", e);
        }
    }

    @Test(priority = 16, enabled = true)
    public void studentNotStartedAssessment()
    {
        try
        {
            new DirectLogin().directLoginWithCreditial("11"); // direct login as instructor
            new Navigator().NavigateTo("eTextBook"); // navigate e-Textbook
            List<WebElement>  lessonsList = driver.findElements(By.cssSelector("a[data-type='lesson']"));
            lessonsList.get(1).click();
            // Verify lesson page opened
            WebElement element = driver.findElement(By.className("ls-static-assessment-wrapper"));
            new ScrollElement().scrollToViewOfElement(element);
            Thread.sleep(1000);
            // Assign Assignment
            driver.findElement(By.xpath(".//*[@widgettype='assessment']//span[@class='assign-options']")).click();
            driver.findElement(By.xpath(".//*[@class='widget assessment-widget']//span[@class='assign-this-text']")).click();
            new AssignLesson().assignTOCToSpecificStudent(11);
            new DirectLogin().directLoginWithCreditial("212"); // direct login as student
            new Navigator().NavigateTo("Assignments"); // navigate Assignments
            List<WebElement> assignmentsList = driver.findElements(By.className("learning-activity-title"));
            assignmentsList.get(0).click();
            /*WebDriverWait wait = new WebDriverWait(driver,20);
            wait.until(ExpectedConditions.elementSelectionStateToBe(By.className()));*/
            Thread.sleep(3000);
            boolean assignmentExpand = driver.findElement(By.cssSelector("div[class='widget assessment-widget widget-expanded']")).isDisplayed();
            if(assignmentExpand==false)
            {
                Assert.fail("Assignment is not opened in expanded widget format" );
            }
            String startWithFirstQuestion = driver.findElement(By.className("al-diag-chapter-details")).getText();
            System.out.println("startWithFirstQuestion::"+startWithFirstQuestion);
            if(!startWithFirstQuestion.equals("Question (1 of 2)"))
            {
                Assert.fail("Assignment is not started from the first question" );
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase studentNotStartedAssessment in class EmbeddedAssessmentInLessonPage", e);
        }
    }

    @Test(priority = 17, enabled = true)
    public void studentStartedAAssessment()
    {
        try
        {
            new DirectLogin().directLoginWithCreditial("11"); // direct login as instructor
            new Navigator().NavigateTo("eTextBook"); // navigate e-Textbook
            List<WebElement>  lessonsList = driver.findElements(By.cssSelector("a[data-type='lesson']"));
            lessonsList.get(1).click();
            // Verify lesson page opened
            WebElement element = driver.findElement(By.className("ls-static-assessment-wrapper"));
            new ScrollElement().scrollToViewOfElement(element);
            Thread.sleep(1000);
            // Assign Assignment
            driver.findElement(By.xpath(".//*[@widgettype='assessment']//span[@class='assign-options']")).click();
            driver.findElement(By.xpath(".//*[@class='widget assessment-widget']//span[@class='assign-this-text']")).click();
            new AssignLesson().assignTOCToSpecificStudent(11);
            new DirectLogin().directLoginWithCreditial("216"); // direct login as student
            new Navigator().NavigateTo("Assignments"); // navigate Assignments
            List<WebElement> assignmentsList = driver.findElements(By.className("learning-activity-title"));
            assignmentsList.get(0).click();
            Thread.sleep(3000);
            boolean assignmentExpand = driver.findElement(By.cssSelector("div[class='widget assessment-widget widget-expanded']")).isDisplayed();
            if(assignmentExpand==false)
            {
                Assert.fail("Assignment is not opened in expanded widget format" );
            }
            String startWithFirstQuestion = driver.findElement(By.className("al-diag-chapter-details")).getText();
            System.out.println("startWithFirstQuestion::"+startWithFirstQuestion);
            if(!startWithFirstQuestion.equals("Question (1 of 2)"))
            {
                Assert.fail("Assignment is not started from the first question" );
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase studentStartedAAssessment in class EmbeddedAssessmentInLessonPage", e);
        }
    }

    @Test(priority = 18, enabled = true)
    public void studentAttemptsAsAnAssessmentAndThenAsAssignment()
    {
        try
        {
            new DirectLogin().directLoginWithCreditial("11"); // direct login as instructor
            new Navigator().NavigateTo("eTextBook"); // navigate e-Textbook
            List<WebElement>  lessonsList = driver.findElements(By.cssSelector("a[data-type='lesson']"));
            lessonsList.get(1).click();
            // Verify lesson page opened
            WebElement element = driver.findElement(By.className("ls-static-assessment-wrapper"));
            new ScrollElement().scrollToViewOfElement(element);
            Thread.sleep(1000);
            // Assign Assignment
            driver.findElement(By.xpath(".//*[@widgettype='assessment']//span[@class='assign-options']")).click();
            driver.findElement(By.xpath(".//*[@class='widget assessment-widget']//span[@class='assign-this-text']")).click();
            new AssignLesson().assignTOCToSpecificStudent(11);
            new DirectLogin().directLoginWithCreditial("220"); // direct login as student
            new Navigator().NavigateTo("Assignments"); // navigate Assignments
            List<WebElement> assignmentsList = driver.findElements(By.className("learning-activity-title"));
            assignmentsList.get(0).click();
            /*WebDriverWait wait = new WebDriverWait(driver,20);
            wait.until(ExpectedConditions.elementSelectionStateToBe(By.className()));*/
            Thread.sleep(3000);
            boolean assignmentExpand = driver.findElement(By.cssSelector("div[class='widget assessment-widget widget-expanded']")).isDisplayed();
            if(assignmentExpand==false)
            {
                Assert.fail("Assignment is not opened in expanded widget format" );
            }
            String startWithFirstQuestion = driver.findElement(By.className("al-diag-chapter-details")).getText();
            System.out.println("startWithFirstQuestion::"+startWithFirstQuestion);
            if(!startWithFirstQuestion.equals("Question (1 of 2)"))
            {
                Assert.fail("Assignment is not started from the first question" );
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase studentAttemptsAsAnAssessmentAndThenAsAssignment in class EmbeddedAssessmentInLessonPage", e);
        }
    }

    @Test(priority = 19,enabled = true)
    public void accessingEmbeddedAssessmentsFromDifferentPages()
    {
        try
        {
            new DirectLogin().directLoginWithCreditial("224"); // direct login as student
            new Click().clickbyxpath(".//*[@class='news news2']//div[@class='middle']");
            List<WebElement> assessmentInHistory = driver.findElements(By.cssSelector("span[class='ls-lesson-title ellipsis']"));
            assessmentInHistory.get(0).click();
            Thread.sleep(3000);
            boolean assessmentExpand = driver.findElement(By.cssSelector("div[class='widget assessment-widget widget-expanded']")).isDisplayed();
            if(assessmentExpand==false)
            {
                Assert.fail("Student is not navigated to the assessment From recent History" );
            }
            new UIElement().waitAndFindElement(By.xpath(".//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath(".//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            new Click().clickBycssselector("span[data-localize='My Activity']");
            Thread.sleep(1000);
            List<WebElement> assignmentInMyActivity = driver.findElements(By.cssSelector("a[class='assessment-title QWERTY']"));
            assignmentInMyActivity.get(0).click();
            boolean assignmentFromMyActivity = driver.findElement(By.cssSelector("span[class='tab_icon assessment-icon']")).isDisplayed();
            if(assignmentFromMyActivity==false)
            {
                Assert.fail("Student is not navigated to the assessment from My Activity page" );
            }
            new DirectLogin().directLoginWithCreditial("11"); // direct login as instructor
            new Navigator().NavigateTo("eTextBook"); // navigate e-Textbook
            List<WebElement>  lessonsList = driver.findElements(By.cssSelector("a[data-type='lesson']"));
            lessonsList.get(1).click();
            // Verify lesson page opened
            WebElement element = driver.findElement(By.className("ls-static-assessment-wrapper"));
            new ScrollElement().scrollToViewOfElement(element);
            Thread.sleep(1000);
            // Assign Assignment
            driver.findElement(By.xpath(".//*[@widgettype='assessment']//span[@class='assign-options']")).click();
            driver.findElement(By.xpath(".//*[@class='widget assessment-widget']//span[@class='assign-this-text']")).click();
            new AssignLesson().assignTOCToSpecificStudent(11);
            new DirectLogin().directLoginWithCreditial("224"); // direct login as student
            new Click().clickbyxpath(".//*[@class='news news2']//div[@class='middle']");
            List<WebElement> assignmentInHistory = driver.findElements(By.cssSelector("span[class='ls-lesson-title ellipsis']"));
            assignmentInHistory.get(0).click();
            Thread.sleep(3000);
            boolean assignmentExpand = driver.findElement(By.cssSelector("div[class='widget assessment-widget widget-expanded']")).isDisplayed();
            if(assignmentExpand==false)
            {
                Assert.fail("Student is not navigated to the Assignemnt From recent History" );
            }
            new UIElement().waitAndFindElement(By.xpath(".//*[@class='ls-site-nav-drop-down']/a"));
            new Click().clickbyxpath(".//*[@class='ls-site-nav-drop-down']/a");//click on Navigator icon
            new Click().clickBycssselector("span[data-localize='Course Stream']");
            Thread.sleep(1000);
            List<WebElement> assignmentInCS = driver.findElements(By.cssSelector("span[class='ls-lesson-title ellipsis']"));
            assignmentInCS.get(0).click();
            Thread.sleep(2000);
            boolean assignmentInLessonPage = driver.findElement(By.cssSelector("div[class='widget assessment-widget widget-expanded']")).isDisplayed();
            if(assignmentInLessonPage==false)
            {
                Assert.fail("Student is not navigated to the Assignemnt From Course Stream" );
            }
            new Navigator().NavigateTo("eTextBook"); // navigate e-Textbook
            List<WebElement> assignmentInTOC = driver.findElements(By.className("toc-assignment-ellipsis"));
            assignmentInTOC.get(0).click();
            Thread.sleep(1000);
            boolean assignmentTab = driver.findElement(By.cssSelector("span[class='tab_icon resource-icon']")).isDisplayed();
            if(assignmentTab==false)
            {
                Assert.fail("Assignment tab is not opened" );
            }
            new Click().clickbyxpath(".//*[@class='ls-stream-learing-activity-level ls-assignment-learning-activity ellipsis']");
            Thread.sleep(2000);
            boolean assignmentFromassignmentTab= driver.findElement(By.cssSelector("div[class='widget assessment-widget widget-expanded']")).isDisplayed();
            if(assignmentFromassignmentTab==false)
            {
                Assert.fail("Student is not navigated to the Assignemnt From Assignment tab" );
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase accessingEmbeddedAssessmentsFromDifferentPages in class EmbeddedAssessmentInLessonPage", e);
        }
    }

    @Test(priority = 20,enabled = true)
    public void accessingEmbeddedAssessmentsFromDifferentTabs()
    {
        try
        {
            new DirectLogin().directLoginWithCreditial("230"); // direct login as Student
            new Navigator().NavigateTo("eTextBook"); // navigate e-Textbook
            Thread.sleep(1000);
            List<WebElement> lessonsList = driver.findElements(By.cssSelector("a[data-type='lesson']"));
            lessonsList.get(1).click();
            new Navigator().NavigateTo("eTextBook"); // navigate e-Textbook
            List<WebElement> assignmentInTOC = driver.findElements(By.className("toc-assignment-ellipsis"));
            assignmentInTOC.get(0).click();
            Thread.sleep(1000);
            boolean assignmentTab = driver.findElement(By.cssSelector("span[class='tab_icon resource-icon']")).isDisplayed();
            if(assignmentTab==false)
            {
                Assert.fail("Embedded assessment is not opened in a new tab" );
            }
            new Click().clickbyxpath(".//*[@class='ls-stream-learing-activity-level ls-assignment-learning-activity ellipsis']");
            Thread.sleep(2000);
            boolean assignmentFromAssignmentTab= driver.findElement(By.cssSelector("div[class='widget assessment-widget widget-expanded']")).isDisplayed();
            if(assignmentFromAssignmentTab==false)
            {
                Assert.fail("Student is not navigated to the Assignemnt From Assignment tab" );
            }
            new Click().clickBycssselector("div[class='question-toggle-arrow-icon']");
            new Click().clickbyxpath("(.//*[@class='true-false-student-answer-label'])[1]");
            new Click().clickByclassname("ls-embedded-static-submit-button");
            new Click().clickByclassname("widget-close");
            new Click().clickBycssselector("span[class='tab_icon resource-icon']");
            Thread.sleep(1000);
            boolean accessContentInOtherTab = driver.findElement(By.id("ls-right-post-comment-link")).isDisplayed();
            new Click().clickbyxpath(".//*[@id='resource-post-comment']/span[@class='redactor_placeholder']");
            driver.findElement(By.xpath(".//*[@id='resource-post-comment']/span[@class='redactor_placeholder']")).sendKeys("Added test Comment");
            new Click().clickBycssselector("div[class='post-comment resource-post-comment']");
            Thread.sleep(2000);
            new Click().clickbyxpath(".//*[@class='ls-stream-learing-activity-level ls-assignment-learning-activity ellipsis']");
            boolean nextQuestion = driver.findElement(By.className("ls-embedded-static-next-button")).isDisplayed();
            if(nextQuestion==false)
            {
                Assert.fail("User is not able to switch back to the Embedded assessment tab" );
            }
            new Click().clickByclassname("ls-embedded-static-next-button");
            boolean submitQuestion = driver.findElement(By.className("ls-embedded-static-submit-button")).isDisplayed();
            if(submitQuestion==false)
            {
                Assert.fail("User is not able to access the Embedded assessment" );
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase accessingEmbeddedAssessmentsFromDifferentTabs in class EmbeddedAssessmentInLessonPage", e);
        }
    }
}
