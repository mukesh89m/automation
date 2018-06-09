package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Config;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;

public class SelectCourse {
    public void selectcourse() {
        try {
            Driver.driver.findElement(By.cssSelector("img[title='" + Config.course + "']")).click();//.select course
            Thread.sleep(2000);
        } catch (Exception e) {
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in testcase selectcourse in class SelectCourse", e);
        }
    }

    public void selectchapter(String chaptername) {
        try {
            Driver.driver.findElement(By.cssSelector("div[title='" + chaptername + "']")).click();//select chapter name
        } catch (Exception e) {
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in testcase selectchapter in class SelectCourse", e);
        }
    }

    public void selectassignment(String assignmentname) {
        try {
            Driver.driver.findElement(By.cssSelector("div[title='" + assignmentname + "']")).click();//hover assignment name
        } catch (Exception e) {
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in testcase selectassignment in class SelectCourse", e);
        }
    }

    public void selectChapterByIndex(int zeroBasedIndex) {
        try {
            List<WebElement> chapters = Driver.driver.findElements(By.cssSelector("div[class='course-chapter-label node']"));
            chapters.get(zeroBasedIndex).click();
        } catch (Exception e) {
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in testcase selectChapterByIndex in class SelectCourse", e);
        }

    }

    public void selectAssessmentByIndex(int zeroBasedIndex) {
        try {
            List<WebElement> assessment = Driver.driver.findElements(By.className("collection-assessment-name"));
            assessment.get(zeroBasedIndex).click();
        } catch (Exception e) {
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in testcase selectAssessmentByIndex in class SelectCourse", e);
        }

    }


    public void expandChapterTreeByIndex(String dataIndex, int chapterIndex) {
        try {
            new Click().clickbylistxpath("//div[@class = 'expand-chapter-tree expand']", chapterIndex);
            Thread.sleep(1000);
        } catch (Exception e) {
            Assert.fail("Exception in Method 'expandChapterTreeByIndex' in class 'ResourseCreate'");
        }
    }

    public void expandTopicTreeByIndex(int dataIndex, int topicIndex) {
        try {
            new Click().clickbylistxpath("//div[@class = 'expand-topic-tree expand']", topicIndex);
            Thread.sleep(1000);
        } catch (Exception e) {
            Assert.fail("Exception in Method 'expandTopicTreeByIndex' in class 'ResourseCreate'");
        }
    }


    public void selectTopicByIndex(String dataIndex, int topicIndex) {
        try {
            new Click().clickbylistxpath("//div[@class = 'course-topic-label node']", topicIndex);
            Thread.sleep(1000);
        } catch (Exception e) {
            Assert.fail("Exception in Method 'selectTopicByIndex' in class 'ResourseCreate'");
        }
    }

    public void selectSubTopicByIndex(int dataIndex, int subTopicIndex) {
        try {
            new Click().clickbylistxpath("//div[@class = 'course-subtopic-label node']", subTopicIndex);
            Thread.sleep(1000);
        } catch (Exception e) {
            Assert.fail("Exception in Method 'selectSubTopicByIndex' in class 'ResourseCreate'");
        }
    }

    public void selectInvisibleAssignment(String assignmentname) {
        try {
            new WebDriverWait(Driver.driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@title='" + assignmentname + "']")));
            WebElement element = Driver.driver.findElement(By.xpath(".//*[@title='" + assignmentname + "']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath(".//*[@title='" + assignmentname + "']")));
        } catch (Exception e) {
            Assert.fail("Exception in testcase selectInvisibleAssignment in class SelectCourse", e);
        }
    }


    public void selectLSCourse() throws InterruptedException {
        try {
            Driver.driver.findElement(By.xpath("//a[@title='All Course Types']")).click();
            Thread.sleep(2000);
            Driver.driver.findElement(By.xpath("//a[@title='Learning Space']")).click();
            Thread.sleep(2000);

            Driver.driver.findElement(By.cssSelector("img[title='" + Config.lscourse + "']")).click();//.select course
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Assert.fail("Exception while selecting LS Course");
        }

    }

    public void navigateTODifferentCourse(String course) throws InterruptedException {
        try {
            Driver.driver.findElement(By.xpath("//div[@class='product-dropdown']//div/a[1]")).click();
            Thread.sleep(2000);
            Driver.driver.findElement(By.xpath("//a[@title='" + course + "']")).click();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Assert.fail("Exception while Navigating Course");
        }
    }
}
