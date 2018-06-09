package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT17.R1714;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;

/*
 * Created by sumit on 5/12/14.
 */
public class AuthorShouldBeAbleToViewMPQInSummaryPage extends Driver {

    @Test(priority = 1, enabled = true)
    public void authorShouldBeAbleToViewMPQInSummaryPage()
    {
        try
        {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "146");
            String questionset = ReadTestData.readDataByTagName("", "questionset", "146");
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", "146");
            //Driver.startDriver();
            new DBConnect().Connect();
            ResultSet rs=DBConnect.st.executeQuery("select username from t_user where firstname = 'Alexander' AND lastname ='Fomin';");
            String userName= "";
            while (rs.next())
            {
                userName=rs.getString("userName");
            }

            DBConnect.st.executeUpdate("update t_user set password = '6ab14d3be1858b6e8f9f223b6980c7a6dc087a1d4871255f7fc366535762f2a90213d242f2eaa0ab' where firstname = 'Alexander' AND lastname ='Fomin';");
            driver.get(Config.baseURL);
            System.out.println("User Name : " + userName);
            driver.findElement(By.id("username")).sendKeys(userName);
            driver.findElement(By.id("password")).sendKeys("snapwiz");
            driver.findElement(By.id("loginSubmitBtn")).click();
            Thread.sleep(3000);
            new SelectCourse().selectcourse(); //select a course
            new SelectCourse().selectChapterByIndex(0);//select chapter
            driver.findElement(By.cssSelector("div.create-practice")).click();//click on Create Practice
            Thread.sleep(3000);
            new Click().clickByclassname("create-regular-assessment-popup-item");
            Thread.sleep(3000);
            new ComboBox().selectValue(3, "Static Practice");
            driver.findElement(By.id("assessmentName")).click();
            driver.findElement(By.id("assessmentName")).clear();
            driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
            driver.findElement(By.id("questionSetName")).clear();
            driver.findElement(By.id("questionSetName")).sendKeys(questionset);
            new QuestionCreate().multiPartQuestion(146);//Create MPQ
            new Click().clickByid("deliver-course");//click on Summary
            driver.findElement(By.cssSelector("tspan")).click();
            new Click().clickbylinkText("All Authors");//click on All Authors
            WebElement element=driver.findElement(By.cssSelector("a[title = 'Fomin, Alexander']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(5000);
            new Click().clickbylinkText("Fomin, Alexander");//click on Alexander Fomin

            driver.findElement(By.cssSelector("tspan")).click();
            new Click().clickbyxpath("//div[@class='cms-course-content-bar-content']");//click on Ch 1
            Thread.sleep(5000);

            /*List<WebElement>  svgElement = driver.findElements(By.cssSelector("svg"));
            System.out.println("svgElement Size : " + svgElement.size());
            List<WebElement> gElementList = svgElement.get(1).findElements(By.cssSelector("g"));
            int speCount = gElementList.size();
            System.out.println("speCount : " + speCount);
            List<WebElement> rectElementsList = gElementList.get(5).findElements(By.tagName("rect"));
            System.out.println(".........rectElementsList size : " + rectElementsList.size());
            for(int a=0;a<rectElementsList.size();a++){
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                System.out.println("x : " + rectElementsList.get(a).getAttribute("x"));
                System.out.println("y : " + rectElementsList.get(a).getAttribute("y"));
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            }
            Actions action = new Actions(driver);
            WebElement we = rectElementsList.get(1);//Click on a bar chart
            action.moveToElement(we).build().perform();
            Thread.sleep(15000);
            //new ExplicitWait().explicitWaitByClass("cms-ccs-show-questions", 60);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("cms-ccs-show-questions")));//Click on link 'Show Questions'
            Thread.sleep(5000);
            //Review Content page should display with all the questions associated with the chapter
            String question = new TextFetch().textfetchbycssselector("label[class='control-label redactor_editor']");
            Assert.assertEquals(question.trim(), questiontext, "Multi-part question does not appear under Summary page drilling.");

            String questionGroup = new TextFetch().textfetchbyid("cms-content-review-question-type-wrapper");
            Assert.assertEquals(questionGroup, "QuestionType: Multi Part", "\"QuestionType: Multi Part\" is not added to the summary page");

            String qId = new TextFetch().textfetchbyclass("cms-preview-stem-no");
            if(!qId.trim().contains("QId -"))
                Assert.fail("Question label as <Q Id> + text followed by the first question stem is not shown for the multipart question in the first line.");

            String comment = new TextFetch().textfetchbyclass("question-comments-link");
            Assert.assertEquals(comment, "Comments (0)", "Comment link is absent in Launch Review page.");

            String points = new TextFetch().textfetchbyclass("multi-part-question-points");
            Assert.assertEquals(points, "Points Available: 1", "Points Available: 1 is absent in MPQ Launch Review page.");

            new Click().clickbylist("question-toggle-arrow-icon", 1);//click on expand second question

            String points1 = new TextFetch().textfetchbylistclass("multi-part-question-points", 1);
            Assert.assertEquals(points1, "Points Available: 1", "Points Available: 1 is absent in Summary page for 2nd question part.");

            new Click().clickByclassname("question-comments-link");//click on comment link
            String commentText = new RandomString().randomstring(5);
            driver.findElement(By.className("al-question-discussion-input-section")).sendKeys(commentText+ Keys.RETURN);
            String comment1 = new TextFetch().textfetchbyid("al-user-discussion-question-content");
            Assert.assertEquals(comment1, "Q: "+commentText, "Author unable to add comment for MPQ in summary page.");

            //TC row no. 154
            // "5. Go to Summary page and click on the pie chart based on Question Status Count filter
            //6. Select the chapter bar to which multipart questionis associated
            //7. Mouse hover the bar and click on Show Questions"

            new Click().clickByid("deliver-course");//click on Summary
            driver.findElement(By.cssSelector("tspan")).click();
            new Click().clickbylinkText("All Authors");//click on All Authors
            new Click().clickbylinkText("Alexander Fomin");//click on Alexander Fomin
            driver.findElement(By.cssSelector("tspan")).click();
            WebElement we1 = driver.findElement(By.cssSelector("rect[fill='rgb(192,192,192)']"));
            Actions action1 = new Actions(driver);
            action1.moveToElement(we1).build().perform();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("cms-ccs-show-questions")));
            Thread.sleep(5000);
            //Review Content page should display with all the questions associated with the chapter
            String question1 = new TextFetch().textfetchbycssselector("label[class='control-label redactor_editor']");
            Assert.assertEquals(question1.trim(), questiontext, "Multi-part question does not appear under Summary page drilling.");

            String questionGroup1 = new TextFetch().textfetchbyid("cms-content-review-question-type-wrapper");
            Assert.assertEquals(questionGroup1, "QuestionType: Multi Part", "\"QuestionType: Multi Part\" is not added to the summary page");

            String qId1 = new TextFetch().textfetchbyclass("cms-preview-stem-no");
            if(!qId1.trim().contains("QId -"))
                Assert.fail("Question label as <Q Id> + text followed by the first question stem is not shown for the multipart question in the first line.");

            String comment2 = new TextFetch().textfetchbyclass("question-comments-link");
            Assert.assertEquals(comment2, "Comments (2)", "Comment link is absent in Launch Review page.");

            String points2 = new TextFetch().textfetchbyclass("multi-part-question-points");
            Assert.assertEquals(points2, "Points Available: 1", "Points Available: 1 is absent in MPQ Launch Review page.");

            new Click().clickbylist("question-toggle-arrow-icon", 1);//click on expand second question

            String points3 = new TextFetch().textfetchbylistclass("multi-part-question-points", 1);
            Assert.assertEquals(points3, "Points Available: 1", "Points Available: 1 is absent in Summary page for 2nd question part.");

            new Click().clickByclassname("question-comments-link");//click on comment link
            String commentText1 = new RandomString().randomstring(5);
            driver.findElement(By.className("al-question-discussion-input-section")).sendKeys(commentText1+ Keys.RETURN);
            String comment3 = new TextFetch().textfetchbyid("al-user-discussion-question-content");
            Assert.assertEquals(comment3, "Q: "+commentText1, "Author unable to add comment for MPQ in summary page.");*/
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase authorShouldBeAbleToViewMPQInSummaryPage in  class AuthorShouldBeAbleToViewMPQInSummaryPage.", e);
        }
    }

}
