package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/*
 * Created by Sumit on 8/2/2014.
 */
public class OpenAssignmentFromCMS extends Driver {

    //open an Assignment from CMS
    public void openAssignmentFromCMS(String dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", dataIndex);
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", dataIndex);
            new DirectLogin().CMSLogin();
            String title=driver.getTitle();
            if (title.equals("Course Content Details")) {
                driver.findElement(By.cssSelector("img[alt=\"" + Config.course + "\"]")).click();
                if (chapterName == null) {
                    driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
                } else if (courselevel != null) {
                    new Click().clickBycssselector("div[class='course-label node']");
                } else {
                    List<WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
                    for (WebElement chapters : allChapters) {
                        if (chapters.getText().contains(chapterName)) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chapters);
                            Thread.sleep(4000);
                            break;
                        }

                    }

                }
                List<WebElement> elements = driver.findElements(By.className("collection-assessment-name"));
                for (WebElement content : elements) {
                    if (content.getText().trim().equals(assignmentname)) {

                        Thread.sleep(3000);
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", content);
                        Thread.sleep(3000);
                        break;
                    }
                }
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase openAssignmentFromCMS in class OpenAssignmentFromCMS.", e);
        }
    }
    //open a lesson from  CMS
    public void openLessonFromCMS(int chapterIndex, int topicIndex, int lessonIndex)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            new DirectLogin().CMSLogin();
            String title = driver.getTitle();
            if (title.equals("Course Content Details")) {
                driver.findElement(By.cssSelector("img[alt=\"" + Config.course + "\"]")).click();
                List<WebElement> allChapter = driver.findElements(By.cssSelector("div[class='expand-chapter-tree expand']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allChapter.get(chapterIndex));//open chapter + icon
                Thread.sleep(3000);
                List<WebElement> allTopic = driver.findElements(By.cssSelector("div[class='course-topic-label node']"));//click on topic
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allTopic.get(topicIndex));
                Thread.sleep(3000);
                List<WebElement> allLessons = driver.findElements(By.className("collection-lesson-name"));//click on lesson
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", allLessons.get(lessonIndex));
                Thread.sleep(3000);
            }
        } catch (Exception e) {
            Assert.fail("Exception in testcase openAssignmentFromCMS in class OpenAssignmentFromCMS.", e);
        }
    }
}
